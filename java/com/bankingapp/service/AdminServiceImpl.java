package com.bankingapp.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bankingapp.dto.AdminDto;
import com.bankingapp.model.Admin;
import com.bankingapp.model.Privilege;
import com.bankingapp.model.User;
import com.bankingapp.model.UserAccountInfo;
import com.bankingapp.repository.AdminRepository;
import com.bankingapp.repository.PrivilegeRepo;
import com.bankingapp.repository.UserAccountInfoRepo;
import com.bankingapp.repository.UserRepo;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private UserAccountInfoRepo accountInfoRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepo userRepository;

	@Autowired
	private PrivilegeRepo privilegeRepository;

	@Override
	public Admin registerAdmin(AdminDto adminDto) {

		Admin newAdmin = new Admin(adminDto);
		newAdmin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
		newAdmin.setCreatedDate(new Date());
		List<String> privilege = adminDto.getPrivileges();
		List<Privilege> privilegeList = privilege.stream().map(privilegeRepository::findByPrivilegeName)
				.filter(existingPrivilege -> !existingPrivilege.isEmpty()).flatMap(List::stream)
				.collect(Collectors.toList());

		newAdmin.setPrivileges(privilegeList);
		return adminRepository.save(newAdmin);
	}

	@Override
	public Admin addPrivilegeToAdmin(AdminDto adminDto, List<String> privileges) {

		Admin findAdmin = adminRepository.findByUserName(adminDto.getUserName());
		
		List<String> privilege = adminDto.getPrivileges();
		
		List<Privilege> privilegeList = privilege.stream().map(privilegeRepository::findByPrivilegeName)
				.filter(existingPrivilege -> !existingPrivilege.isEmpty()).flatMap(List::stream)
				.collect(Collectors.toList());
		
		findAdmin.setPrivileges(privilegeList);
		return adminRepository.save(findAdmin);
	}

	@Override
	public Admin removePrivilegeFromAdmin(AdminDto adminDto, List<Privilege> privileges) {
		
		return null;
	}

	@Override
	public User getAllUserDetails(Long accountNumber) {	
		return userRepository.findByAccountNumber(accountNumber);
	}

	@Override
	public UserAccountInfo approveUserBankAccount(Long accountNumber, Boolean approval) {
		
		UserAccountInfo findAccount = accountInfoRepo.findByAccountNumber(accountNumber);
		User findUser= userRepository.findByAccountNumber(accountNumber);
		findAccount.setApproval(approval);
		findAccount.setApprovedBy(findUser);
		if(approval== false) {
			findAccount.setDeletedBy(findUser);
		}
		
		return accountInfoRepo.save(findAccount);
	}

}
