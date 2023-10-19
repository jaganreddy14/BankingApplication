package com.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingapp.model.AuthImage;

public interface AuthImgrepository extends JpaRepository<AuthImage, Long>{
	
	AuthImage findByUserName(String userName);

}
