package com.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingapp.enums.TemplateType;
import com.bankingapp.model.EmailTemplates;

public interface EmailTemplateRepository  extends JpaRepository<EmailTemplates, Long>{
	
	EmailTemplates findByTemplateType(TemplateType templateType);

}
