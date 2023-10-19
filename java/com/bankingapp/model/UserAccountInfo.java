package com.bankingapp.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bankingapp.dto.UserAccInfoDto;
import com.bankingapp.enums.AccountType;

@Entity
@Table(name = "user_account_info")
public class UserAccountInfo extends BaseModel {

	@Column(name = "account_number", nullable = false, updatable = false)
	private Long accountNumber;

	@Column(name = "balance", nullable = false)
	private double balance;

	@Column(name = "account_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	@Column(name = "username", nullable = false)
	private String userName;

	@Column(name = "account_status", nullable = false)
	private String accountStatus;

	@Column(name = "credit_score")
	private Integer creditscore;

	@Column(name = "approval")
	private Boolean approval;

	@ManyToOne
	@JoinColumn(name = "approved_by")
	private User approvedBy;

	@ManyToOne
	@JoinColumn(name = "deleted_by")
	private User deletedBy;

	@ManyToMany
	@JoinColumn(name = "bank_id", referencedColumnName = "id")
	private Set<BankDetails> bank;

	public UserAccountInfo() {
		// TODO Auto-generated constructor stub
	}

	public UserAccountInfo(UserAccInfoDto accInfoDto) {
		this.accountStatus = accInfoDto.getAccountStatus();
		this.accountType = AccountType.of(accInfoDto.getAccountType());
		this.balance = accInfoDto.getBalance();
		this.userName = accInfoDto.getUserName();
		this.approval = accInfoDto.getApproval();

	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Set<BankDetails> getBank() {
		return bank;
	}

	public void setBank(Set<BankDetails> bank) {
		this.bank = bank;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getCreditscore() {
		return creditscore;
	}

	public void setCreditscore(Integer creditscore) {
		this.creditscore = creditscore;
	}

	public Boolean getApproval() {
		return approval;
	}

	public void setApproval(Boolean approval) {
		this.approval = approval;
	}

	public User getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(User approvedBy) {
		this.approvedBy = approvedBy;
	}

	public User getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(User deletedBy) {
		this.deletedBy = deletedBy;
	}

}
