package com.bankingapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_files")
public class UserFiles extends BaseModel {

	@Column(name = "file_name", nullable = false, length = 45)
	private String fileName;

	@Column(name = "location", nullable = false, length = 150)
	private String location;

	@Column(name = "userName", nullable = false, length = 45)
	private String userName;
	
	@Column(name = "size", nullable = false)
	private Long size;

	@OneToOne
	private User user;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}
	
	

}
