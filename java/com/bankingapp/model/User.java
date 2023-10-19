package com.bankingapp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bankingapp.dto.UserDto;
import com.bankingapp.enums.SeniorJuniorCitizen;
import com.bankingapp.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
//@NamedQuery(name = "User.findByAccountNumberAndDob", 
//query = "SELECT u FROM User u WHERE u.accountNumber = :accountNumber AND u.dob = :dob")
public class User extends BaseModel {

	@Column(name = "user_name", nullable = false, length = 10)
	private String userName;

	@Column(name = "first_name", nullable = true, length = 20)
	private String firstName;

	@Column(name = "last_Name", nullable = true, length = 20)
	private String lastName;

	@Column(name = "password", nullable = false, length = 150)
	private String pwd;

	@Column(name = "email", nullable = false, length = 50)
	private String email;

	@Column(name = "account_number", nullable = false)
	private Long accountNumber;

	@Column(name = "phone", nullable = false, length = 15)
	private String phone;

	@Column(name = "is_active", nullable = false)
	private boolean isActive;

	@Column(name = "login_count", nullable = false)
	private int loginCount;

	@Column(name = "failed_login_count", nullable = true)
	private int failedLoginCount;

	@Column(name = "date_of_birth", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dob;

	@Column(name = "is_locked", nullable = true)
	private Boolean isLocked;

	@Column(name = "citizen", nullable = false)
	@Enumerated(EnumType.STRING)
	private SeniorJuniorCitizen citizen;

	@Column(name = "age", nullable = false)
	private int age;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	@JsonIgnore
	private List<Role> roles;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "accountDetails_id", referencedColumnName = "id")
	private UserAccountInfo accountDetails;

	@Column(name = "usertype", nullable = false)
	@Enumerated(EnumType.STRING)
	private UserType userType;

	public User(UserDto user) {
		this.userName = user.getUserName();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.accountNumber = user.getAccountnumber();
		this.phone = user.getPhone();
		this.isActive = user.isActive();
		this.loginCount = user.getLoginCount();
		this.dob = user.getDob();
		this.isLocked = user.getIsLocked();
	}

	public User() {
		// TODO Auto-generated constructor stub

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getAccountnumber() {
		return accountNumber;
	}

	public void setAccountnumber(Long accountnumber) {
		this.accountNumber = accountnumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public int getFailedLoginCount() {
		return failedLoginCount;
	}

	public void setFailedLoginCount(int failedLoginCount) {
		this.failedLoginCount = failedLoginCount;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public SeniorJuniorCitizen getCitizen() {
		return citizen;
	}

	public void setCitizen(SeniorJuniorCitizen citizen) {
		this.citizen = citizen;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Boolean getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public UserAccountInfo getAccountDetails() {
		return accountDetails;
	}

	public void setAccountDetails(UserAccountInfo accountDetails) {
		this.accountDetails = accountDetails;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName + ", pwd=" + pwd
				+ ", email=" + email + ", accountnumber=" + accountNumber + ", phone=" + phone + ", isActive="
				+ isActive + ", loginCount=" + loginCount + ", failedLoginCount=" + failedLoginCount + ", dob=" + dob
				+ ", isLocked=" + isLocked + ", citizen=" + citizen + ", age=" + age + ", roles=" + roles
				+ ", accountDetails=" + accountDetails + "]";
	}

}
