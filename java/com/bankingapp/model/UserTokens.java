package com.bankingapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user_tokens")
public class UserTokens extends BaseModel {

	@Column(name = "auth_token", nullable = false, length = 250)
	private String authToken;

	@Column(name = "expiration_time", nullable = false, length = 50)
	@Temporal(TemporalType.TIMESTAMP)
	private Date expirationTime;
	
	@Column(name = "user_name",nullable = false, length=50)
	private String userName;

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
