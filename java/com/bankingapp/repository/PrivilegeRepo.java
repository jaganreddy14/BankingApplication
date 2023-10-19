package com.bankingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankingapp.model.Privilege;

@Repository
public interface PrivilegeRepo extends JpaRepository<Privilege, Long> {

	List<Privilege> findByPrivilegeName(String name);
}
