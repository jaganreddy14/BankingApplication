package com.bankingapp.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.Exception.NoAccountFound;
import com.bankingapp.dto.TransactionDto;
import com.bankingapp.dto.UserAccInfoDto;
import com.bankingapp.model.SuperAdmin;
import com.bankingapp.model.User;
import com.bankingapp.model.UserAccountInfo;
import com.bankingapp.repository.UserAccountInfoRepo;
import com.bankingapp.repository.UserRepo;

@Service
public class UserAccInfoServiceImpl implements UserAccInfoService {

	@Autowired
	private UserAccountInfoRepo accInfoRepository;

	@Autowired
	private UserRepo userRepository;

	@Autowired
	private SuperAdmin superAdmin;

	@Override
	public UserAccountInfo insertUserAccountInfo(UserAccInfoDto dto) {
		if(dto != null) {
			UserAccountInfo accountInfo= new UserAccountInfo(dto);
			User foundUser= userRepository.findByUserName(dto.getUserName());
		
			accountInfo.setAccountNumber(foundUser.getAccountnumber());
			accountInfo.setCreatedBy(superAdmin.admin);
			accountInfo.setCreatedDate(new Date());
			accInfoRepository.save(accountInfo);
			return accountInfo;
		}
		return null;
	}

	@Override
	public UserAccountInfo updateUserAccountInfo(UserAccInfoDto accInfoDto) {

		return null;
	}

	@Override
	public UserAccountInfo GetUserDetailsWithAccNo(Long accountNumber) {
		return accInfoRepository.findByAccountNumber(accountNumber);
	}
	
	@Override
	public void updateBalance(TransactionDto dto,String transactionType) {
		UserAccountInfo findAccount= accInfoRepository.findByAccountNumber(dto.getAccountNumber());
		try {
			if( findAccount == null) {
				throw new NoAccountFound("No user found with that account Number ");
			}
		} catch (NoAccountFound e) {
			e.printStackTrace();
		}
		
		if(transactionType.equals("withdraw")) {           
			findAccount.setBalance((findAccount.getBalance())- (dto.getTransactionAmount()));
		}
		else {
			findAccount.setBalance((findAccount.getBalance())+ (dto.getTransactionAmount()));
		}
		accInfoRepository.save(findAccount);
		 
	}

}
