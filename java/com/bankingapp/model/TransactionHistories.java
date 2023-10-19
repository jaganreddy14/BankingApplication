package com.bankingapp.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bankingapp.enums.PaymentStatus;
import com.bankingapp.enums.TransactionType;

@Entity
@Table(name = "transaction_histories")
@NamedQuery(
	    name = "TransactionHistories.findByCreatedDate",
	    query = "SELECT t FROM TransactionHistories t WHERE t.accountNumber = :accountNumber AND t.createdDate BETWEEN :startDate AND :endDate"
	)
public class TransactionHistories {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "transaction_id", nullable = false, unique = true, updatable = false)
	private String transactionId;

	@Column(name = "transaction_status", nullable = false)
	@Enumerated(EnumType.STRING)
	private PaymentStatus transactionStatus;

	@Column(name = "transaction_amount", nullable = false)
	private Double transactionAmount;

	@Column(name = "transaction_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;

	@Column(name = "account_number", nullable = false)
	private Long accountNumber;

	@Column(name = "username", nullable = false)
	private String userName;

	@Column(name = "transaction_date", nullable = false)
	private Date createdDate;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "atm",referencedColumnName = "id")
	private Atm atm;

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

	public PaymentStatus getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(PaymentStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Atm getAtm() {
		return atm;
	}

	public void setAtm(Atm atm) {
		this.atm = atm;
	}

	

	

	
	
}
