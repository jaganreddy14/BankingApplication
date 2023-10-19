package com.bankingapp.Exception;

public class TransactionAmountExceededThenLimit extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransactionAmountExceededThenLimit(String msg) {
		super(msg);
	}
}
