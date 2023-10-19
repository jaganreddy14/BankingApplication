package com.bankingapp.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.bankingapp.model.Role;
import com.bankingapp.model.User;

public class RoleDto extends Dto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String roleName;

	private Date createDate;

	private Date modifiedDate;

	private User createdBy;

	private User modifiedBy;

	private List<String> privileges;

	public RoleDto(Role role) {
		this.roleName = role.getRoleName();
		this.privileges = role.getPrivileges().stream().map(privilege -> privilege.getPrivilegeName())
				.collect(Collectors.toList());
	}
	
	public RoleDto() {
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}

	public void setId(Long Id) {
		this.id = Id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public List<String> getPrivileges() {
		return privileges;
	}


	public void setPrivileges(List<String> privileges) {
		this.privileges = privileges;
	}
	
	

}
