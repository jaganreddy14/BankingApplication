package com.bankingapp.dto;

import java.util.Date;

public class DebitCardDto extends Dto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String cardNumber;

	private String cardHolder;

	private String pin;

	private Integer rewardPoints;

	private Boolean hotlistingCard;

	private Date cardExpiration;

	private Date renewedDate;

	private Boolean isExpired;
	

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

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public Integer getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(Integer rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	public Boolean getHotlistingCard() {
		return hotlistingCard;
	}

	public void setHotlistingCard(Boolean hotlistingCard) {
		this.hotlistingCard = hotlistingCard;
	}

	public Date getCardExpiration() {
		return cardExpiration;
	}

	public void setCardExpiration(Date cardExpiration) {
		this.cardExpiration = cardExpiration;
	}

	public Date getRenewedDate() {
		return renewedDate;
	}

	public void setRenewedDate(Date renewedDate) {
		this.renewedDate = renewedDate;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	public Boolean getIsExpired() {
		return isExpired;
	}

	public void setIsExpired(Boolean isExpired) {
		this.isExpired = isExpired;
	}


	

	
	
}
