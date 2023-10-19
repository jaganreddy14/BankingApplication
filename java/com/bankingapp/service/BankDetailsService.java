package com.bankingapp.service;

import com.bankingapp.dto.BankDetailsDto;
import com.bankingapp.model.BankDetails;

public interface BankDetailsService {
	
	BankDetails addNewBank(BankDetailsDto bankDto);

	BankDetails findByAccountNumber(Long acc);

}
