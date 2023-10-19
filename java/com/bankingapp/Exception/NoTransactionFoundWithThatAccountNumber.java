package com.bankingapp.Exception;

public class NoTransactionFoundWithThatAccountNumber extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoTransactionFoundWithThatAccountNumber(String msg) {
		super(msg);
	}

}
