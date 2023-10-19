package com.bankingapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bankingapp.enums.UserActivityType;

@Entity
@Table(name = "user_activity")
public class UserActivities {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User userId;

	@Column(name = "user_actions")
	@Enumerated(EnumType.STRING)
	private UserActivityType userActivity;

	@Column(name = "created_date")
	private Date createdDate;

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public UserActivityType getUserActivity() {
		return userActivity;
	}

	public void setUserActivity(UserActivityType userActivity) {
		this.userActivity = userActivity;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
