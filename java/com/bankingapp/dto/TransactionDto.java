package com.bankingapp.dto;

import java.util.Date;

import com.bankingapp.model.User;

public class TransactionDto extends Dto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long accountNumber;
	
	private String transactionId;

	private String transactionMode;

	private String transactionType;

	private String transactionStatus;

	private Double transactionAmount;
	
	private String transactionNote;
	
	private Integer total_Transactions;

	private Date createdDate;

	private User createdBy;

	private User modifiedBy;

	private Date modifiedDate;
	
	  private Long atm;
	

	private Integer transactionLimit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Integer getTransactionLimit() {
		return transactionLimit;
	}

	public void setTransactionLimit(Integer transactionLimit) {
		this.transactionLimit = transactionLimit;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getTransactionNote() {
		return transactionNote;
	}

	public void setTransactionNote(String transactionNote) {
		this.transactionNote = transactionNote;
	}

	public Integer getTotal_Transactions() {
		return total_Transactions;
	}

	public void setTotal_Transactions(Integer total_Transactions) {
		this.total_Transactions = total_Transactions;
	}

	public Long getAtm() {
		return atm;
	}

	public void setAtm(Long atm) {
		this.atm = atm;
	}
	
	

}
