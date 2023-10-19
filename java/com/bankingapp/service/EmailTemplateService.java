package com.bankingapp.service;

import org.springframework.stereotype.Repository;

import com.bankingapp.enums.TemplateType;
import com.bankingapp.model.EmailTemplates;

@Repository
public interface EmailTemplateService {

	EmailTemplates findTemplate(TemplateType templateType);

}
