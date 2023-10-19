package com.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingapp.model.Faq;

public interface FaqRepository extends JpaRepository<Faq, Long>{
	
	Faq findByUserType(String userType);
	
	Faq findByQuestion(String question);

}
