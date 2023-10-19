package com.bankingapp.service;

import java.util.List;

import com.bankingapp.dto.TransactionDto;
import com.bankingapp.model.Transaction;
import com.bankingapp.model.TransactionHistories;

public interface TransHistService {
	
	TransactionHistories insertintoTransactionHistories(TransactionDto dto);
	
	List<TransactionHistories> listAll();

	List<TransactionHistories> getTransactionHistoriesByUserName(String userName);
	
	List<TransactionHistories> filterTransactions(Long accountNumber,String from, String to);

	List<Transaction> getTransactionWithAccountNumber(Long AccountNumber);
	
	

}
