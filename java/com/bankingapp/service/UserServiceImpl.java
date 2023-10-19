package com.bankingapp.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bankingapp.Exception.InvalidEmailOrPassword;
import com.bankingapp.dto.UserDto;
import com.bankingapp.enums.SeniorJuniorCitizen;
import com.bankingapp.enums.UserType;
import com.bankingapp.model.Role;
import com.bankingapp.model.SuperAdmin;
import com.bankingapp.model.User;
import com.bankingapp.model.UserAccountInfo;
import com.bankingapp.repository.RoleRepo;
import com.bankingapp.repository.UserAccountInfoRepo;
import com.bankingapp.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepository;

	@Autowired
	private RoleRepo roleRepositoy;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserAccountInfoRepo accInfo;

	@Autowired
	private SuperAdmin superAdmin;

	@Value("${allowed.extensions}")
	private List<String> allowedExtentions;

	@Override
	public User registerUser(UserDto user) {

		User newUser = new User(user);

		newUser.setEmail(user.getEmail());
		newUser.setPwd(passwordEncoder.encode(user.getPwd()));
		Date dateOfBirth = user.getDob();
		Date currentDate = new Date();

		DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		int dob = Integer.parseInt(formatter.format(dateOfBirth));
		int today = Integer.parseInt(formatter.format(currentDate));
		int age = (today - dob) / 10000;

		newUser.setAge(age);
		newUser.setCreatedDate(new Date());
		newUser.setUserType(UserType.of(user.getUserType()));

		if (superAdmin.admin != null) {
			newUser.setCreatedBy(superAdmin.admin);
		}

		if (newUser.getAge() > 50) {
			newUser.setCitizen(SeniorJuniorCitizen.SENIORCITIZEN);
		} else {
			newUser.setCitizen(SeniorJuniorCitizen.CITIZEN);
		}

		UserAccountInfo accountInfo = accInfo.findByUserName(user.getUserName());
		newUser.setAccountDetails(accountInfo);

		return userRepository.save(newUser);

	}

	@Override
	public List<User> getAllUsersWithRole() {
		List<User> users = userRepository.findAll();
		users.forEach(user -> {
			List<Role> roles = user.getRoles();
			user.setRoles(roles);
		});
		return users;
	}

	@Override
	public void deleteUserById(long id) {
		userRepository.deleteById(id);
	}

	@Override
	public User findUserById(long id) {
		return userRepository.findById(id);
	}

	@Override
	public User findUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public List<User> findActiveUser(boolean active) {
		List<User> activeUsers = userRepository.findAll();
		if (activeUsers != null) {
			for (User user : activeUsers) {
				int isequal = Boolean.compare(user.isActive(), active);
				if (isequal == 0) {
					return activeUsers;
				}
			}
		}
		return null;
	}

	@Override
	public boolean updateUserDetails(UserDto user) {
		User foundUser = userRepository.findByUserName(user.getUserName());

		if (foundUser == null) {
			return false;
		}

		foundUser.setEmail(user.getEmail());
		foundUser.setPhone(user.getPhone());
		foundUser.setFailedLoginCount(user.getFailedLoginCount());
		foundUser.setAccountnumber(user.getAccountnumber());
		foundUser.setActive(user.isActive());
		foundUser.setFirstName(user.getFirstName());
		foundUser.setLastName(user.getLastName());
		foundUser.setModifiedDate(new Date());

		if (superAdmin.admin != null) {
			foundUser.setModifiedBy(superAdmin.admin);// change name

		}

		List<String> rolenames = user.getRoles();
		List<Role> roleList = rolenames.stream().map(roleRepositoy::findByRoleName)
				.filter(existingRole -> !existingRole.isEmpty()).flatMap(List::stream).collect(Collectors.toList());

		foundUser.setRoles(roleList);
		userRepository.save(foundUser);
		return true;

	}

	@Override
	public void failedLogin(UserDto user) {// proper name
		User user2 = userRepository.findByUserName(user.getUserName());
		if (user != null) {
			if (user2.getPwd() != user.getPwd()) { // use
				user2.setFailedLoginCount(user2.getFailedLoginCount() + 1);
				user2.setLoginCount(user2.getLoginCount() + 1);
			}
			if (user2.getFailedLoginCount() > 3) {
				user2.setIsLocked(true);
			}
			userRepository.save(user2);
		}
	}

	@Override
	public boolean checkEncryptedPwd(String encrypted, String pwd) {

		return passwordEncoder.matches(pwd, encrypted);
	}

	@Override
	public boolean emailAndPasswordValidation(String email, String password, String phone) {

		if (email == null && password == null) {
			return false;
		}

		String passRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
		String phRegex = "^(\\+2)?[0-9]{10}$";
		String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";

		Pattern pwdpattern = Pattern.compile(passRegex);
		Pattern phpattern = Pattern.compile(phRegex);
		Pattern emailPattern = Pattern.compile(emailRegex);
		Matcher pwdmatcher = pwdpattern.matcher(password);
		Matcher phmatcher = phpattern.matcher(phone);
		Matcher emailMatcher = emailPattern.matcher(email);

		System.out.println(emailMatcher.matches() && pwdmatcher.matches());

		if (emailMatcher.matches() && pwdmatcher.matches() && phmatcher.matches()) {
			try {
				throw new InvalidEmailOrPassword("Invalid email or password");
			} catch (InvalidEmailOrPassword e) {
				e.printStackTrace();
			}
			return true;
		}

		return false;

	}

	@Override
	public Boolean isValidExtension(String extension) {
		return allowedExtentions.contains(extension);

	}

	@Override
	public String getFileExtension(String fileName) {
		int dotIndex = fileName.lastIndexOf(".");
		if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
			return fileName.substring(dotIndex + 1).toLowerCase();
		}
		return "";
	}

	@Override
	public User findByAccountNumber(Long accountNumber) {
		return userRepository.findByAccountNumber(accountNumber);
	}

	@Override
	public void handleFailedLogin() {

		List<User> allUsers= userRepository.findAll();
		for (User user : allUsers) {
			user.setFailedLoginCount(0);
			user.setIsLocked(false);
			userRepository.save(user);
		}
	}

}
