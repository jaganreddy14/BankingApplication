package com.bankingapp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "privilege")
public class Privilege extends BaseModel {

	@Column(name = "privilege_name", nullable = false,length = 20)
	private String privilegeName;

	@ManyToMany(mappedBy = "privileges")
	private List<Role> roles;
	
	@ManyToOne
	@JoinColumn(name = "admin_id")
	private Admin admin;

	public String getPrivilegeName() {
		return privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
