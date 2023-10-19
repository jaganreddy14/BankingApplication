package com.bankingapp.service;

import com.bankingapp.dto.DebitCardDto;
import com.bankingapp.model.DebitDetails;

public interface DebitCardDetailsService {

	Boolean isCardHotlisted(DebitCardDto dto);
	
	DebitDetails registerNewCard(DebitCardDto carDetailsDto,Long accountNumber);
	
	DebitDetails blockExistingCard(DebitCardDto cardDetailsDto);
	
	DebitDetails renewUserCard(DebitCardDto cardDetailsDto);
	
	DebitDetails changeCardPin(Long accountNumber, String dob,String newPin);
	
	DebitDetails findCardDetails(Long accountNumber, String dob);

	boolean checkCardCredentials(String cardNumber, String pin);
}
