package com.bankingapp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bankingapp.dto.AdminDto;
import com.bankingapp.enums.AdminType;

@Entity
@Table(name = "admin")
public class Admin extends BaseModelDate {

	@Column(name = "username", nullable = false, length = 45)
	private String userName;

	@Column(name = "password", nullable = false, length = 45)
	private String password;

	@Column(name = "admin_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private AdminType adminType;

	@OneToMany(mappedBy = "admin")
	private List<Privilege> privileges;
	
	public Admin() {
		// TODO Auto-generated constructor stub
	}
	
	public Admin(AdminDto adminDto) {
		this.userName= adminDto.getUserName();
		this.adminType=AdminType.of(adminDto.getAdminType());
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AdminType getAdminType() {
		return adminType;
	}

	public void setAdminType(AdminType adminType) {
		this.adminType = adminType;
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	

}
