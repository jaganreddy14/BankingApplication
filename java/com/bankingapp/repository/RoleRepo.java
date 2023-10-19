package com.bankingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankingapp.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long>{
	
	List<Role> findByRoleName(String roleName);
	
	
	


	
	

}
