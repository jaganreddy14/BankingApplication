package com.bankingapp.Exception;

public class TransactionLimitExceeded extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransactionLimitExceeded(String msg) {
		super(msg);
	}

}
