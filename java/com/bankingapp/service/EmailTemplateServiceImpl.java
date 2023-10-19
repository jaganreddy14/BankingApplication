package com.bankingapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.enums.TemplateType;
import com.bankingapp.model.EmailTemplates;
import com.bankingapp.repository.EmailTemplateRepository;

@Service
public class EmailTemplateServiceImpl implements EmailTemplateService {

	@Autowired
	private EmailTemplateRepository emailTemplateRepo;

	@Override
	public EmailTemplates findTemplate(TemplateType templateType) {
		return emailTemplateRepo.findByTemplateType(templateType);	
		}

}
