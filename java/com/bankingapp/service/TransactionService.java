package com.bankingapp.service;

import java.util.List;

import com.bankingapp.dto.TransactionDto;
import com.bankingapp.model.Transaction;

public interface TransactionService {
	
	Transaction insertTransactionDetails(TransactionDto transDto);
	
	Transaction getTransactionWithId(TransactionDto dto);
	
	List<Transaction> getAllTransactions(Long accountNumber);
	
	
	
	

	
}
