package com.bankingapp.service;

import com.bankingapp.dto.UserDto;
import com.bankingapp.model.UserTokens;

public interface UserTokenService {

	void insertToken(UserTokens token);
	
	Boolean UpdateToken(UserTokens userToken,String userName);
	
	boolean findByTokenAndUserName(String token, String userName);

	UserTokens findUserByUserName(String userName);

	void insertTokenAfterSuccessfulLogin(UserDto user);
	
}
