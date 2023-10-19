package com.bankingapp.service;

import java.util.List;

import com.bankingapp.dto.PrivilegeDto;
import com.bankingapp.model.Privilege;

public interface PrivilegeService {

	void addNewPrivilege(PrivilegeDto dto);
	
	boolean deletePrivilege(long id);
	
	boolean updatePrivilege(long id,PrivilegeDto dto);
	
	List<Privilege> getAllPrivileges();
	
	List<Privilege> getPrivilegeByName(String PrivilegeName);
	
	
}
