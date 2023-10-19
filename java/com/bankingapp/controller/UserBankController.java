package com.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.dto.BankDetailsDto;
import com.bankingapp.service.BankDetailsService;
import com.bankingapp.util.Responses;

@RestController
@RequestMapping("/api/bank")
public class UserBankController {
	
	@Autowired
	private BankDetailsService bankDetailsService;
	
	
	@PostMapping("/addNewBank")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> addnewBankInfo(@RequestBody BankDetailsDto bankDetailsDto){
		if( bankDetailsDto == null) {
			return ResponseEntity.badRequest().body(new Responses("No details found"));
		}
		
		bankDetailsService.addNewBank(bankDetailsDto);
		return  ResponseEntity.ok().body(new Responses("New Bank information added successfully."));
	}

}
