package com.bankingapp.dto;

import java.util.Date;

import com.bankingapp.model.User;

public class UserActivityDto extends Dto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private User userId;

	private String userActivity;

	private Date createdDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public String getUserActivity() {
		return userActivity;
	}

	public void setUserActivity(String userActivity) {
		this.userActivity = userActivity;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
