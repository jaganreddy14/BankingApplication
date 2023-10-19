package com.bankingapp.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.bankingapp.dto.RoleDto;

@Entity
@Table(name = "role")
public class Role extends BaseModel {

	@Column(name = "role_name", nullable = false, length = 20)
	private String roleName;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "privilege_id", referencedColumnName = "id")
	private Set<Privilege> privileges;

	public Role(RoleDto role) {
		this.roleName = role.getRoleName();
	}

	public Role() {
		// TODO Auto-generated constructor stub
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

	

}
