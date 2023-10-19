package com.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingapp.model.UserFiles;

public interface UserFileRepositoy extends JpaRepository<UserFiles, Long> {
	
	UserFiles findByUserName(String userName);

}
