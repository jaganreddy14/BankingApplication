package com.bankingapp.service;

import java.util.List;

import com.bankingapp.dto.FaqDto;
import com.bankingapp.model.Faq;

public interface FaqService {

	Faq addNewFAQ(FaqDto faqDto);

	Faq updateExistingFAQ(FaqDto faqDto);

	void deleteFAQById(Long id);

	Faq getFAQById(Long id);

	List<Faq> getAll();

}
