package com.bankingapp.enums;

import java.util.HashMap;
import java.util.Map;

public enum AccountType {

	SAVINGACCOUNT("0"),CURRENTACCOUNT("1"),SALARYACCOUNT("2"),FIXEDDEPOSITACCOUNT("3");
	
	private final String accountType;
	private static Map<String, AccountType> map= new HashMap<String, AccountType>();

	AccountType(String accountType) {
		this.accountType=accountType;
	}
	
	static {
		for (AccountType type : values()) {
			      map.put(type.accountType, type);
		}
	}
	
	public static AccountType of(String value) {
		AccountType type= map.get(value);
		return type;
	}
	
}
