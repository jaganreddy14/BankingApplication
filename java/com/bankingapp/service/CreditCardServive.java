package com.bankingapp.service;

import com.bankingapp.model.CreditCard;

public interface CreditCardServive {

	Boolean cancleCreditCardOrHotlistCreditCard(String cardNumber);
	
//	Boolean blockOrHotlistCreditCard(String cardNumber);
	
	Boolean payDueAmount(String cardNumber, Long amount);
	
	Long checkCreditCardBalance(String cardNumber);

	CreditCard CheckEligibilityAndGenerateCredit(Integer creditScore, String userName,String bankName);
	
	CreditCard findByCardNumber(String cardNumber);
	
	
	
}
