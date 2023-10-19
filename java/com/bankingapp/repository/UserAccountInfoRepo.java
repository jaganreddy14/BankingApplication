package com.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingapp.model.UserAccountInfo;

public interface UserAccountInfoRepo extends JpaRepository<UserAccountInfo, Long>{
	
	UserAccountInfo findByUserName(String userName);
	
	UserAccountInfo findByAccountNumber(Long accNumber);

}
