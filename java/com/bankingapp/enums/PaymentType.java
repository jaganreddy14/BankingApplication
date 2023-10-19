package com.bankingapp.enums;

import java.util.HashMap;
import java.util.Map;

public enum PaymentType {

	UPIPAYMENT("0"), CARDPAYMENT("1"), INTERNETBANKING("2");

	private static final Map<String, PaymentType> map = new HashMap<String, PaymentType>();
	private final String paymentType;

	PaymentType(String transactionType) {
		this.paymentType = transactionType;
	}

	static {
		for (PaymentType type : values()) {
			map.put(type.paymentType, type);

		}
	}

	public static PaymentType of(String value) {
		PaymentType type = map.get(value);
		return type;

	}

}
