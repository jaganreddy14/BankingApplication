package com.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingapp.model.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long>{
	
	CreditCard findByCardHolderName(String name);
	
	CreditCard findByCardNumber(String cardNumber);

}
