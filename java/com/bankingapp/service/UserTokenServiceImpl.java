package com.bankingapp.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.dto.UserDto;
import com.bankingapp.jwt.JwtUtil;
import com.bankingapp.model.SuperAdmin;
import com.bankingapp.model.UserTokens;
import com.bankingapp.repository.UserTokenRepo;

@Service
public class UserTokenServiceImpl implements UserTokenService {

	@Autowired
	private UserTokenRepo tokenRepository;

	@Autowired
	private SuperAdmin superAdmin;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public void insertToken(UserTokens token) {
		if (token != null) {
			UserTokens tokens = new UserTokens();
			tokens.setAuthToken(token.getAuthToken());
			tokens.setExpirationTime(token.getExpirationTime());
			tokens.setUserName(token.getUserName());
			tokens.setCreatedDate(new Date());
			token.setCreatedBy(superAdmin.admin);
			tokenRepository.save(tokens);
		}
	}

	@Override
	public Boolean UpdateToken(UserTokens userTokens, String userName) {

		UserTokens userToken = tokenRepository.findByUserName(userName);
		if (userToken != null) {
			userToken.setAuthToken(userTokens.getAuthToken());
			userToken.setExpirationTime(userToken.getExpirationTime());
			userToken.setModifiedDate(new Date());
			tokenRepository.save(userToken);
			return true;
		}

		return false;
	}

	@Override
	public void insertTokenAfterSuccessfulLogin(UserDto user) {

		try {

			String token = jwtUtil.generateToken(user.getUserName());
			Date expirationTime = jwtUtil.getExpirationTime(token);

			UserTokens findUser = tokenRepository.findByUserName(user.getUserName());
			if (findUser == null) {
				UserTokens insertToken = new UserTokens();
				insertToken.setAuthToken(token);
				insertToken.setExpirationTime(expirationTime);
				insertToken.setUserName(user.getUserName());
				insertToken.setCreatedBy(superAdmin.admin);

				insertToken(insertToken);
			} else {
				findUser.setAuthToken(token);
				findUser.setExpirationTime(expirationTime);
				
				UpdateToken(findUser, findUser.getUserName());
			}
		} catch (Exception e) {

		}

	}

	@Override
	public boolean findByTokenAndUserName(String token, String userName) {
		UserTokens foundUserWithToken = tokenRepository.findByAuthTokenAndUserName(token, userName);
		if (foundUserWithToken != null) {
			return true;
		}
		return false;
	}

	@Override
	public UserTokens findUserByUserName(String userName) {
		return tokenRepository.findByUserName(userName);
	}



}
