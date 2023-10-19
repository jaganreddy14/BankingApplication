package com.bankingapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.bankingapp.enums.PaymentStatus;
import com.bankingapp.enums.PaymentType;
import com.bankingapp.enums.TransactionType;

@Entity
@Table(name = "transaction")
public class Transaction extends BaseModel {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "account_number", nullable = false)
	private Long accountNumber;

	@Column(name = "transaction_id", nullable = false, length = 45, unique = true)
	private String transactionId;

	@Column(name = "transaction_mode", nullable = false, length = 45)
	@Enumerated(EnumType.STRING)
	private PaymentType transactionMode;

	@Column(name = "transaction_type", nullable = false, length = 45)
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;

	@Column(name = "transaction_status", nullable = false, length = 45)
	@Enumerated(EnumType.STRING)
	private PaymentStatus transactionStatus;

	@Column(name = "transaction_amount", nullable = false)
	private Double transactionAmount;

	@Column(name = "transaction_limit", nullable = true)
	private Integer transactionLimit;

	@Column(name = "transaction_notes")
	private String transactionNotes;

	@Column(name = "total_transactions")
	private Integer totalTransactions;

	@Column(name = "atm")
	private Long atm;

	public Transaction() {
		// TODO Auto-generated constructor stub
	}

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

	public PaymentType getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(PaymentType paymentType) {
		this.transactionMode = paymentType;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
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

	public Integer getTransactionLimit() {
		return transactionLimit;
	}

	public void setTransactionLimit(Integer transactionLimit) {
		this.transactionLimit = transactionLimit;
	}

	public String getTransactionNotes() {
		return transactionNotes;
	}

	public void setTransactionNotes(String transactionNotes) {
		this.transactionNotes = transactionNotes;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Integer getTotalTransactions() {
		return totalTransactions;
	}

	public void setTotalTransactions(Integer totalTransactions) {
		this.totalTransactions = totalTransactions;
	}

	public Long getAtm() {
		return atm;
	}

	public void setAtm(Long atm) {
		this.atm = atm;
	}
}
