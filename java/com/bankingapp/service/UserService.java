package com.bankingapp.service;

import java.util.List;

import com.bankingapp.dto.UserDto;
import com.bankingapp.model.User;

public interface UserService {

	User registerUser(UserDto userDto);

	List<User> getAllUsersWithRole();

	void deleteUserById(long id);

	User findUserById(long id);

	User findUserByUserName(String userName);

	boolean updateUserDetails(UserDto user);

	List<User> findActiveUser(boolean active);

	void failedLogin(UserDto user);

	boolean checkEncryptedPwd(String encrypted, String pwd);

	boolean emailAndPasswordValidation(String email, String password, String phone);

	Boolean isValidExtension(String extension);

	String getFileExtension(String fileName);

	User findByAccountNumber(Long accountNumber);

	void handleFailedLogin();
	

}
