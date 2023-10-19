package com.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankingapp.model.UserTokens;

@Repository
public interface UserTokenRepo extends JpaRepository<UserTokens, Long>{


	UserTokens findByAuthTokenAndUserName(String token,String userName);

	UserTokens findByUserName(String userName);
	
}
