package com.bankingapp.controller;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.dto.UserActivityDto;
import com.bankingapp.dto.UserDto;
import com.bankingapp.model.User;
import com.bankingapp.model.UserTokens;
import com.bankingapp.service.UserActivityService;
import com.bankingapp.service.UserService;
import com.bankingapp.service.UserTokenService;
import com.bankingapp.util.Responses;

@RestController
@RequestMapping("/api/bank/user")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserActivityService activityService;

	@Autowired
	private UserTokenService tokenService;

	@PostMapping("/login")
	public ResponseEntity<Object> userLogin(@RequestBody UserDto user) {
		if (user == null) {
			return ResponseEntity.badRequest().body(new Responses("Please enter credentials"));
		}

		User users = userService.findUserByUserName(user.getUserName());
		String encrytedPassword = users.getPwd();

		if (users != null && userService.checkEncryptedPwd(encrytedPassword, user.getPwd())) {

			tokenService.insertTokenAfterSuccessfulLogin(user);

			UserTokens founTokens = tokenService.findUserByUserName(user.getUserName());

			String Auth_Token = founTokens.getAuthToken();
			Date Exp_Time = founTokens.getExpirationTime();

			HashMap<String, Object> response = new HashMap<String, Object>();
			response.put("Auth_Token ", Auth_Token);
			response.put("Expiration ", Exp_Time);
			UserActivityDto activityDto = new UserActivityDto();
			
			User findUserId = userService.findUserByUserName(user.getUserName());
			activityDto.setUserId(findUserId);
			activityDto.setUserActivity("1");
			activityService.insertUserActivities(activityDto);
			
			return ResponseEntity.ok().body(response);

		} else {
			userService.failedLogin(user);
			return ResponseEntity.badRequest().body(new Responses("Invalid credentials."));
		}

	}

}
