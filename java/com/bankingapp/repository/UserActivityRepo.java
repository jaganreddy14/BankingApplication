package com.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingapp.model.UserActivities;

public interface UserActivityRepo extends JpaRepository<UserActivities, Long> {
	

}
