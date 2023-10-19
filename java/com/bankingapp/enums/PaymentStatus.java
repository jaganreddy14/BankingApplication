package com.bankingapp.enums;

import java.util.HashMap;
import java.util.Map;

public enum PaymentStatus {
	
	SUCCESS("0"),FAILED("1");
	
	private static final Map<String, PaymentStatus> map = new HashMap<String, PaymentStatus>();
	private final String status;


	PaymentStatus(String Status) {
		this.status=Status;
	}
	
	static {
		 for (PaymentStatus status: values()) {
			map.put(status.status,status );
		}
	}
	
	public static PaymentStatus of(String value) {
		PaymentStatus status = map.get(value);
		return status;

	}

}
