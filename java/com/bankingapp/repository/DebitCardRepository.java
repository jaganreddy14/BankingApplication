package com.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingapp.model.DebitDetails;
import com.bankingapp.model.UserAccountInfo;

public interface DebitCardRepository extends JpaRepository<DebitDetails, Long> {
	
	DebitDetails findByHotlistingCard(boolean value);
	
	DebitDetails findByCardNumber(String cardNumber);
	
	DebitDetails findByAccountDetails(UserAccountInfo accountInfo);
	
	
	
	

}
