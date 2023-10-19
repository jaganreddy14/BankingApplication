package com.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingapp.model.BankDetails;

public interface BankDetailsRepo extends JpaRepository<BankDetails, Long> {
	
	BankDetails findByBankName(String bankName);
	
	BankDetails findByAccountNumber(Long acc);

}
