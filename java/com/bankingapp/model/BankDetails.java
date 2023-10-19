package com.bankingapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bankingapp.dto.BankDetailsDto;

@Entity
@Table(name = "bank_details")
public class BankDetails extends BaseModelDate {

	@Column(name = "bank_name", nullable = false, length = 45)
	private String bankName;

	@Column(name = "account_number", nullable = false, unique = true, updatable = false)
	private Long accountNumber;

	@Column(name = "location", nullable = false)
	private String location;

	@Column(name = "ifsc", nullable = false)
	private String ifsc;

	public BankDetails(BankDetailsDto bankDto) {
		this.bankName = bankDto.getBankName();
		this.accountNumber = bankDto.getAccountNumber();
		this.location = bankDto.getLocation();
		this.ifsc = bankDto.getIfsc();
	}

	public BankDetails() {
		// TODO Auto-generated constructor stub
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

}
