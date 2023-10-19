package com.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.dto.DebitCardDto;
import com.bankingapp.model.DebitDetails;
import com.bankingapp.service.DebitCardDetailsService;
import com.bankingapp.util.Responses;

@RestController
@RequestMapping("/api/bank")
public class UserCardController {
	
	@Autowired
	private DebitCardDetailsService cardService;
	
	@PostMapping("/NewCard")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<Object> addNewCard(@RequestParam("accountNumber") Long accountNumber,@RequestBody DebitCardDto cardDetails){
		
		if(cardDetails == null) {
			return ResponseEntity.badRequest().body(new Responses("Please enter the card details"));
		}
		
		cardService.registerNewCard(cardDetails,accountNumber);
		return ResponseEntity.ok().body(new Responses("New card added sucessfully"));
	}
	
	@PostMapping("/regenerateCard")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<Object> regeneratenewCard(@RequestBody DebitCardDto cardDetailsDto){
		
		if(cardDetailsDto == null) {
			return ResponseEntity.badRequest().body(new Responses("Please enter the card details"));
		}
		
		cardService.renewUserCard(cardDetailsDto);
		return ResponseEntity.ok().body(new Responses("Card renewed sucessfully"));
	}
	@PostMapping("/getcard")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<Object> getCardDetails(@RequestParam("accountNumber") Long accountNumber,@RequestParam("dateOfBirth") String dateOfBirth){
		
		if( accountNumber != null && dateOfBirth.isEmpty()) {
			return ResponseEntity.badRequest().body(new Responses("Please enter your accountNumber and date of birth"));
		}
		
		DebitDetails foundCard= cardService.findCardDetails(accountNumber, dateOfBirth);
		return ResponseEntity.ok().body(foundCard);
	}
	
	public ResponseEntity<Object> changeCardPin(@RequestParam("accountNumber") Long accountNumber, @RequestParam("dateOfBith") String dob){
		if( accountNumber != null && dob.isEmpty()) {
			return ResponseEntity.badRequest().body(new Responses("Please enter your accountNumber and date of birth"));
		}
		return null;
	}
}
