package com.bankingapp.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "creditcard")
public class CreditCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "card_number", nullable = false, length = 45, updatable = false)
	private String cardNumber;

	@Column(name = "card_holder_name", nullable = false, length = 45)
	private String cardHolderName;

	@Column(name = "expiration_date", nullable = false)
	private Date expirationDate;

	@Column(name = "created_date", nullable = false)
	private Date createdDate;

	@Column(name = "cvv")
	private String cvv;

	@Column(name = "renew_date")
	private Date renewDate;
	
	@Column(name = "credit_limit")
	private Long creditLimit;
	
	@Column(name = "used_limit")
	private Long userdLimit;
	
	@Column(name = "current_balance")
	private Long balance;
	
	@Column(name = "cancle_card")
	private Boolean cancleCard;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Issued_bank", referencedColumnName = "id")
	private BankDetails issuedBank;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public Date getRenewDate() {
		return renewDate;
	}

	public void setRenewDate(Date renewDate) {
		this.renewDate = renewDate;
	}

	public BankDetails getIssuedBank() {
		return issuedBank;
	}

	public void setIssuedBank(BankDetails issuedBank) {
		this.issuedBank = issuedBank;
	}

	public Long getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Long creditLimit) {
		this.creditLimit = creditLimit;
	}

	public Long getUserdLimit() {
		return userdLimit;
	}

	public void setUserdLimit(Long userdLimit) {
		this.userdLimit = userdLimit;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public Boolean getCancleCard() {
		return cancleCard;
	}

	public void setCancleCard(Boolean cancleCard) {
		this.cancleCard = cancleCard;
	}

	
}
