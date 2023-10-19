package com.bankingapp.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserType {
	
	USER("0"),ADMIN("1");
	
	private final String userType;
	private static Map<String, UserType> map= new HashMap<String, UserType>();

	UserType(String userType) {
		this.userType=userType;
	}
	
	static {
		for (UserType type : values()) {
			      map.put(type.userType, type);
		}
	}
	
	public static UserType of(String value) {
		UserType type= map.get(value);
		return type;
	}

}
