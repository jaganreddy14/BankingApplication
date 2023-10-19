package com.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.service.DebitCardDetailsService;

@RestController
@RequestMapping("/api/bank")
public class DebitCardController {
	
	@Autowired
	private DebitCardDetailsService cardDetailsService;
	


}
