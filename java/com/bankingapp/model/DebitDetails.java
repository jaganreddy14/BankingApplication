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
@Table(name = "user_card_details")
public class DebitDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "card_number", nullable = false, length = 15)
	private String cardNumber;

	@Column(name = "card_holder_Name", nullable = false, length = 45)
	private String cardHolderName;

	@Column(name = "pin", nullable = false, length = 50)
	private String pin;

	@Column(name = "reward_points", nullable = true)
	private Integer rewardPoints;

	@Column(name = "hotlisting_card", nullable = true)
	private Boolean hotlistingCard;

	@Column(name = "card_expiration", nullable = false)
	private Date cardExpiration;

	@Column(name = "applied_date", nullable = false)
	private Date appliedDate;

	@Column(name = "renewed_date", nullable = true)
	private Date renewedDate;

	@Column(name = "is_expired")
	private Boolean isExpired;


	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_details", referencedColumnName = "id")
	private UserAccountInfo accountDetails;

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

	public Date getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(Date appliedDate) {
		this.appliedDate = appliedDate;
	}

	public Date getRenewedDate() {
		return renewedDate;
	}

	public void setRenewedDate(Date renewedDate) {
		this.renewedDate = renewedDate;
	}

	public UserAccountInfo getAccountDetails() {
		return accountDetails;
	}

	public void setAccountDetails(UserAccountInfo accountDetails) {
		this.accountDetails = accountDetails;
	}

	public Date getCardExpiration() {
		return cardExpiration;
	}

	public void setCardExpiration(Date cardExpiration) {
		this.cardExpiration = cardExpiration;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public Boolean getIsExpired() {
		return isExpired;
	}

	public void setIsExpired(Boolean isExpired) {
		this.isExpired = isExpired;
	}



	

}
