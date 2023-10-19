package com.bankingapp.enums;

import java.util.HashMap;
import java.util.Map;

public enum SeniorJuniorCitizen {
	
	SENIORCITIZEN("0"), CITIZEN("1");
	
	
	private static Map<String,SeniorJuniorCitizen> map= new HashMap<String, SeniorJuniorCitizen>();
	private final String  citizenship;

	SeniorJuniorCitizen(String citizenship) {
		this.citizenship=citizenship;
	}
	
	static {
		for (SeniorJuniorCitizen citizenType : values() ) {
			map.put(citizenType.citizenship, citizenType);
		}
	}
	
	public static SeniorJuniorCitizen of(String value) {
		SeniorJuniorCitizen seniorJuniorCitizen=map.get(value);
		return seniorJuniorCitizen;
	}

}
