package com.bankingapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import com.bankingapp.Exception.UsernameNotFoundException;
import com.bankingapp.model.User;
import com.bankingapp.repository.UserRepo;

@Component
public class UserAuthenticationErrorHandler implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

	@Autowired
	private UserRepo userRepository;

	@Override
	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {

		Object userName = event.getAuthentication().getPrincipal();
		System.out.println("Failed login using USERNAME " + userName);

		User findUser = userRepository.findByUserName(userName.toString());

		if (findUser == null) {

			// do something
		
		}
		if (findUser.getFailedLoginCount() >= 3) {

			findUser.setIsLocked(true);
			userRepository.save(findUser);
		}

		findUser.setFailedLoginCount(findUser.getFailedLoginCount() + 1);
		userRepository.save(findUser);

	}

}
