package com.bankingapp.enums;

import java.util.HashMap;
import java.util.Map;

public enum AdminType {
	
	SUPERADMIN("0"),ADMIN("1");

	private final String adminType;
	private static Map<String, AdminType> map= new HashMap<String, AdminType>();
	
	AdminType(String adminType) {
		this.adminType=adminType;
	}
	
	static {
		for (AdminType type : values()) {
			      map.put(type.adminType, type);
		}
	}
	
	public static AdminType of(String value) {
		AdminType type= map.get(value);
		return type;
	}

}
