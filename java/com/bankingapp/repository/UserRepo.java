package com.bankingapp.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankingapp.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	
	User findById(long id);
	
	User findByUserName(String username);
	
	User findByIsActive(boolean isactive);
	
	User findByAccountNumber(Long accountNumber);
	
	User findByAccountNumberAndDob(Long accountNumber,Date dob);

}
