package com.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingapp.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

	Admin findByUserName(String userName);
}
