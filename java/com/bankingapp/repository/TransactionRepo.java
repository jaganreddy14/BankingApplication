package com.bankingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankingapp.model.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

	Transaction findByTransactionId(String transactionId);
	
	List<Transaction> findByAccountNumber(Long accountNumber);

	
}
