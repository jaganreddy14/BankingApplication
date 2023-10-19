package com.bankingapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingapp.model.TransactionHistories;

public interface TransHistoryRepo extends JpaRepository<TransactionHistories, Long> {
	
	List<TransactionHistories> findByUserName(String userName);
	
	List<TransactionHistories> findByAccountNumber(Long accountNumber);
	
	List<TransactionHistories> findByCreatedDate(Long accountNumber, Date startDate, Date endDate);

}
