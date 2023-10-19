package com.bankingapp.service;

import java.util.List;

import com.bankingapp.dto.RoleDto;
import com.bankingapp.model.Role;

public interface RoleService {
	
	void addNewRole(RoleDto role);
	
	boolean deleteRole(String roleName);
	
	boolean updateRole(RoleDto role, long id);
	
	List<Role> getAllRolesWithPrivileges();
	
	List<Role> getByRoleName(String name);
	
	Role getById(long id);
	
	
	

}
