package com.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.dto.CreditCardResponseDto;
import com.bankingapp.model.BankDetails;
import com.bankingapp.model.CreditCard;
import com.bankingapp.model.UserAccountInfo;
import com.bankingapp.service.BankDetailsService;
import com.bankingapp.service.CreditCardServive;
import com.bankingapp.service.UserAccInfoService;
import com.bankingapp.util.EncryptionAndDecryption;
import com.bankingapp.util.Responses;

@RestController
@RequestMapping("/api/bank")
public class CreditCardController {

	@Autowired
	private CreditCardServive cardServive;

	@Autowired
	private UserAccInfoService accountInfo;

	@Autowired
	private BankDetailsService bankDetailsService;

	@Autowired
	private EncryptionAndDecryption encryptionAndDecryption;

	@PostMapping("/applyCreditCard")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Object> applyForCreditCard(@RequestParam("userName") String userName,
			@RequestParam("accountNumber") Long accountNumber) {

		if (userName.isEmpty() || accountNumber == null) {
			return ResponseEntity.badRequest().body(new Responses("Please Enter account details."));
		}

		UserAccountInfo foundAccount = accountInfo.GetUserDetailsWithAccNo(accountNumber);
		BankDetails findBank = bankDetailsService.findByAccountNumber(accountNumber);

		CreditCard card = cardServive.CheckEligibilityAndGenerateCredit(foundAccount.getCreditscore(), userName,
				findBank.getBankName());

		CreditCardResponseDto cardDto = new CreditCardResponseDto();

		cardDto.setCardNumber(card.getCardNumber());
		cardDto.setCardHolderName(userName);
		cardDto.setExpirationDate(card.getExpirationDate());
		cardDto.setShowCvv("http://localhost:8080/api/bank/showCvv?cardNumber=8798373735628");

		return ResponseEntity.ok().body(cardDto);

	}

	@PostMapping("/showCvv")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Object> showCvv(@RequestParam("cardNumber") String cardNumber) {

		if (cardNumber.isEmpty()) {
			return ResponseEntity.badRequest().body(new Responses("Please enter your card Number"));
		}
		String encrptCard = encryptionAndDecryption.encryptCardNumber(cardNumber);
		CreditCard findCard = cardServive.findByCardNumber(encrptCard);
		String cvv = findCard.getCvv();

		return ResponseEntity.ok().body(new Responses(encryptionAndDecryption.decryptCardNumber(cvv)));
	}

}
