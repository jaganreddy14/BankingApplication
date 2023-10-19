package com.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankingapp.model.Billing;

public interface BillingRepository extends JpaRepository<Billing, Long>{
	
	Billing findByInvoiceId(String invoiceId);

}
