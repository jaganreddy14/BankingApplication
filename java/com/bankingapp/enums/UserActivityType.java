package com.bankingapp.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserActivityType {

	REGISTERED("0"), LOGGEDIN("1"), USERDETAILSUPDATE("2"), USERACCOUNTINFOUPDATE("3"), USERACCOUNTDELETE("4"),
	GETUSERACCOUNTDETAILS("5"), UPDATEUSERACCOUNTDETAILS("6"), USERFILEUPLOAD("7"), USERFILEDOWNLOAD("8"),
	USERTRANSACTIONDETAILSEXCEL("9"), USERTRANSACTIONDETAILSPDF("10"),USERALLTRANSACTIONS("11");

	private static Map<String, UserActivityType> map = new HashMap<String, UserActivityType>();
	private final String activity;

	UserActivityType(String activity) {
		this.activity = activity;
	}

	static {
		for (UserActivityType elements : values()) {
			map.put(elements.activity, elements);
		}
	}

	public static UserActivityType of(String value) {
		UserActivityType type = map.get(value);
		return type;
	}
}
