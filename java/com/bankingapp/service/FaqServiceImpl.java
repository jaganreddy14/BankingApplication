package com.bankingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.dto.FaqDto;
import com.bankingapp.enums.UserType;
import com.bankingapp.model.Faq;
import com.bankingapp.repository.FaqRepository;

@Service
public class FaqServiceImpl implements FaqService {

	@Autowired
	private FaqRepository faqRepository;

	@Override
	public Faq addNewFAQ(FaqDto faqDto) {
		Faq newFaq = new Faq();
		newFaq.setQuestion(faqDto.getQuestion());
		newFaq.setAnswer(faqDto.getAnswer());
		newFaq.setUserType(UserType.of(faqDto.getUserType()));
		return faqRepository.save(newFaq);

	}

	@Override
	public Faq updateExistingFAQ(FaqDto faqDto) {
		
		Faq findFaq=faqRepository.findByQuestion(faqDto.getQuestion());
		findFaq.setAnswer(faqDto.getAnswer());
	    return faqRepository.save(findFaq);
	}

	@Override
	public void deleteFAQById(Long id) {
		faqRepository.deleteById(id);

	}

	@Override
	public Faq getFAQById(Long id) {
		return faqRepository.findById(id).orElse(null);

	}

	@Override
	public List<Faq> getAll() {
		return faqRepository.findAll();
	}

}
