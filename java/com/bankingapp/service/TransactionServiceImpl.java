package com.bankingapp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.Exception.TransactionalNotfound;
import com.bankingapp.dto.TransactionDto;
import com.bankingapp.enums.PaymentStatus;
import com.bankingapp.enums.PaymentType;
import com.bankingapp.enums.TransactionType;
import com.bankingapp.model.SuperAdmin;
import com.bankingapp.model.Transaction;
import com.bankingapp.repository.TransactionRepo;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepo transRepository;

	@Autowired
	private SuperAdmin superAdmin;

//	private final double amountLimit = 10000.0;

	private final Integer noOfTransactions = 10;

	@Override
	public Transaction insertTransactionDetails(TransactionDto transDto) {

		Transaction transaction = new Transaction();

		if (transDto != null) {

			transaction.setTransactionId(transDto.getTransactionId());
			transaction.setAccountNumber(transDto.getAccountNumber());
			transaction.setTransactionMode(PaymentType.of(transDto.getTransactionMode()));
			transaction.setTransactionType(TransactionType.of(transDto.getTransactionType()));
			transaction.setTransactionNotes(transDto.getTransactionNote());
			transaction.setTransactionStatus(PaymentStatus.of(transDto.getTransactionStatus()));
			transaction.setCreatedBy(superAdmin.admin);
//			transaction.setTotalTransactions (transaction.getTotalTransactions()+1);
			transaction.setCreatedDate(new Date());
			transaction.setTransactionLimit(noOfTransactions);
			
			transaction.setTransactionAmount(transDto.getTransactionAmount());
			transRepository.save(transaction);

//			try {
//				if (transaction.getTotalTransactions() != transaction.getTransactionLimit()) {
//					
//				}
//				else {
//					throw new TransactionLimitExceeded("You reached daily transaction limit");
//				}
//			} catch (TransactionLimitExceeded e) {
//				System.err.println(e);
//			}
		}
		return transaction;

	}

	@Override
	public Transaction getTransactionWithId(TransactionDto dto) {
		Transaction foundTransaction = transRepository.findByTransactionId(dto.getTransactionId());
		if (foundTransaction == null) {
			try {
				throw new TransactionalNotfound("Transaction not found");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return foundTransaction;
	}

	@Override
	public List<Transaction> getAllTransactions(Long accountNumber) {
		List<Transaction> transactions= transRepository.findByAccountNumber(accountNumber);
		return transactions;
	}

}
