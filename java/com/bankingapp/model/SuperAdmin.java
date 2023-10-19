package com.bankingapp.model;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bankingapp.repository.UserRepo;
import com.bankingapp.util.Responses;

@Component
public class SuperAdmin {

	public  User admin = null;

	@Autowired
	private UserRepo userRepository;

	@PostConstruct
	private  void postConstruct() {
		admin =userRepository.findByUserName("superAdmin");
		
		if(admin== null) {
			admin= new User();
			admin.setUserName("superAdmin");
			admin.setPwd("admin@123");
			admin.setEmail("admin@gmail.com");
			admin.setPhone("87526765267");
			admin.setAccountnumber(782568224l);
			admin=userRepository.save(admin);
		}
		Responses.userType = admin;
        
	}
	
	

}
