package com.bankingapp.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "billing")
public class Billing {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "invoice_id", nullable = false,length = 45)
	private String invoiceId;
	
	@Column(name = "billing_address",nullable = false,length = 150)
	private String billingAddress;
	
	@Column(name = "email",nullable = false,length = 45)
	private String email;
	
	@Column(name = "phone",nullable = false,length = 20)
	private String phone;
	
	@ManyToMany
	@JoinColumn(name = "transHistory_id", referencedColumnName = "id")
	private Set<TransactionHistories> transactionHistories;
	
	@ManyToMany
	@JoinColumn(name = "trans_id", referencedColumnName = "id")
	private Set<Transaction> transactions;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<TransactionHistories> getTransactionHistories() {
		return transactionHistories;
	}

	public void setTransactionHistories(Set<TransactionHistories> transactionHistories) {
		this.transactionHistories = transactionHistories;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}

	

}
