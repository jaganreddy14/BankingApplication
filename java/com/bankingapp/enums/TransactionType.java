package com.bankingapp.enums;

import java.util.HashMap;
import java.util.Map;

public enum TransactionType {
	
	DEPOSITE("0"),WITHDRAW("1");

	private final String transactionType;
	private static Map<String, TransactionType> map= new HashMap<String, TransactionType>();
	
	TransactionType(String transactionType ) {
		this.transactionType=transactionType;
	}
	
	static {
		for (TransactionType elements : values()) {
			map.put(elements.transactionType, elements);
		}
	}
	
	public static TransactionType of(String value) {
		TransactionType type= map.get(value);
		return type;
	}
	
	
	

}
