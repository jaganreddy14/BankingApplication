package com.bankingapp.dto;

import java.util.List;

import com.bankingapp.model.Transaction;
import com.bankingapp.model.TransactionHistories;

public class AdminResponseDto extends Dto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userName;

	private String email;

	private Long accountNumber;

	private double balance;

	private Boolean isActive;

	private List<TransactionHistories> transactionsHistories;
	
	private List<Transaction> transactions;
	
	private String clickToGetAllTransactions;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public List<TransactionHistories> getTransactionsHistories() {
		return transactionsHistories;
	}

	public void setTransactionsHistories(List<TransactionHistories> transactionsHistories) {
		this.transactionsHistories = transactionsHistories;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public String getClickToGetAllTransactions() {
		return clickToGetAllTransactions;
	}

	public void setClickToGetAllTransactions(String clickToGetAllTransactions) {
		this.clickToGetAllTransactions = clickToGetAllTransactions;
	}

	


	

	

	

}
