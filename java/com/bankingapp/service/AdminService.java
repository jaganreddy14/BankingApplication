package com.bankingapp.service;

import java.util.List;
import java.util.Set;

import com.bankingapp.dto.AdminDto;
import com.bankingapp.model.Admin;
import com.bankingapp.model.Privilege;
import com.bankingapp.model.User;
import com.bankingapp.model.UserAccountInfo;



public interface AdminService {
	
	Admin registerAdmin(AdminDto adminDto);
	
	Admin addPrivilegeToAdmin(AdminDto adminDto, List<String> privileges);
	
	Admin removePrivilegeFromAdmin(AdminDto adminDto, List<Privilege> privileges);
	
	User getAllUserDetails(Long accountNumber);
	
	UserAccountInfo approveUserBankAccount(Long accountNumber, Boolean approval);
	
	
	

}
