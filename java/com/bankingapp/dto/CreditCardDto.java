package com.bankingapp.dto;

import java.util.Date;

import com.bankingapp.model.BankDetails;

public class CreditCardDto extends Dto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String cardNumber;

	private String cvv;

	private String cardHolderName;

	private Date createdDate;

	private Date renewedDate;

	private Date expirationDate;

	private Long creditLimit;

	private BankDetails issuedBank;

	private Long balance;

	private Long usedLimit;
	
	private Boolean cancleCard;

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

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getRenewedDate() {
		return renewedDate;
	}

	public void setRenewedDate(Date renewedDate) {
		this.renewedDate = renewedDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
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

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public Long getUsedLimit() {
		return usedLimit;
	}

	public void setUsedLimit(Long usedLimit) {
		this.usedLimit = usedLimit;
	}

	public Boolean getCancleCard() {
		return cancleCard;
	}

	public void setCancleCard(Boolean cancleCard) {
		this.cancleCard = cancleCard;
	}

	
	

}
