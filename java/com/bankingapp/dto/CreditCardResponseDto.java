package com.bankingapp.dto;

import java.util.Date;

public class CreditCardResponseDto extends Dto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cardNumber;

	private Date expirationDate;

	private String cardHolderName;

	private String showCvv;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getShowCvv() {
		return showCvv;
	}

	public void setShowCvv(String showCvv) {
		this.showCvv = showCvv;
	}

}
