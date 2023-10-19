package com.bankingapp.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.dto.BankDetailsDto;
import com.bankingapp.model.BankDetails;
import com.bankingapp.repository.BankDetailsRepo;

@Service
public class BankDetailsServiceImpl implements BankDetailsService {

	@Autowired
	private BankDetailsRepo bankDetailsRepository;

	@Override
	public BankDetails addNewBank(BankDetailsDto bankDto) {
		if (bankDto != null) {
			BankDetails addnew = new BankDetails(bankDto);
			addnew.setCreatedDate(new Date());
			return bankDetailsRepository.save(addnew);

		}
		return null;

	}
	@Override
	public BankDetails findByAccountNumber(Long acc) {
		return bankDetailsRepository.findByAccountNumber(acc);
	}

}
