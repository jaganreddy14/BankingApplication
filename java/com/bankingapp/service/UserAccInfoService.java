package com.bankingapp.service;

import com.bankingapp.dto.TransactionDto;
import com.bankingapp.dto.UserAccInfoDto;
import com.bankingapp.model.UserAccountInfo;

public interface UserAccInfoService {
	
	UserAccountInfo insertUserAccountInfo(UserAccInfoDto accInfoDto);
	
	
	UserAccountInfo updateUserAccountInfo(UserAccInfoDto accInfoDto);
	
	UserAccountInfo GetUserDetailsWithAccNo(Long accountNumber);
	
	void updateBalance(TransactionDto dto,String transactionType);
}
