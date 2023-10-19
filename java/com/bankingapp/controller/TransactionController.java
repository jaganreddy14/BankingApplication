package com.bankingapp.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.dto.TransactionDto;
import com.bankingapp.enums.TemplateType;
import com.bankingapp.model.Atm;
import com.bankingapp.model.EmailTemplates;
import com.bankingapp.model.Transaction;
import com.bankingapp.model.User;
import com.bankingapp.model.UserAccountInfo;
import com.bankingapp.service.AtmService;
import com.bankingapp.service.DebitCardDetailsService;
import com.bankingapp.service.EmailTemplateService;
import com.bankingapp.service.TransHistService;
import com.bankingapp.service.TransactionService;
import com.bankingapp.service.UserAccInfoService;
import com.bankingapp.service.UserService;
import com.bankingapp.twilio.TwilioSmsUtil;
import com.bankingapp.util.Responses;
import com.bankingapp.util.simpleMail;

@RestController
@RequestMapping("/api/bank")
public class TransactionController {

	private TransactionService transService;

	private UserAccInfoService accountInfo;

	private TransHistService transHistService;

	private EmailTemplateService emailTemplateService;

	private AtmService atmService;

	private UserService userService;

	private DebitCardDetailsService cardService;

	private simpleMail mailService;

	private TwilioSmsUtil smsUtil;

	@Autowired
	public TransactionController(TransactionService transService, UserAccInfoService accountInfo,
			TransHistService transHistService, AtmService atmService, UserService userService,
			DebitCardDetailsService cardService, simpleMail mailService, TwilioSmsUtil smsUtil) {
		super();
		this.transService = transService;
		this.accountInfo = accountInfo;
		this.transHistService = transHistService;
		this.atmService = atmService;
		this.userService = userService;
		this.cardService = cardService;
		this.mailService = mailService;
		this.smsUtil = smsUtil;

	}

	@Autowired
	public void setEmailTemplateService(EmailTemplateService emailTemplateService) {
		this.emailTemplateService = emailTemplateService;
	}

	@PostMapping("/getTrasaction")
	@PreAuthorize("hasAnyRole('User','ADMIN')")
	public ResponseEntity<Object> getTransactionDetails(@RequestBody TransactionDto transDto) {
		if (transDto == null) {
			return ResponseEntity.badRequest().body(new Responses("No Response found"));
		}
		Transaction foundTransaction = transService.getTransactionWithId(transDto);
		return ResponseEntity.ok().body(foundTransaction);

	}

	@PostMapping("/Transaction/{transactionType}")
	@PreAuthorize("hasAnyRole('User','ADMIN')")
	public ResponseEntity<Object> uploadWithdrawTransactionInfo(@PathVariable("transactionType") String transactionType,
			@RequestBody TransactionDto transDto) {

		Long randonNumber = 419806213l;
		if (transDto == null) {
			return ResponseEntity.badRequest().body(new Responses("No request body found."));
		}

		User findUser = userService.findByAccountNumber(transDto.getAccountNumber());
		UserAccountInfo findAccount = accountInfo.GetUserDetailsWithAccNo(transDto.getAccountNumber());

		if (transactionType.equals("withdraw")) {
			transDto.setTransactionType("1");
			transDto.setTransactionId("TRS-W-" + randonNumber++);
			transDto.setTransactionNote("<h2> An amount of INR " + transDto.getTransactionAmount()
					+ "has been DEBITED to your account " + transDto.getAccountNumber() + " on " + new Date()
					+ ". Total Avail.bal: INR " + findAccount.getBalance() + "</h2>");

			accountInfo.updateBalance(transDto, transactionType);
			transHistService.insertintoTransactionHistories(transDto);

			String phone = findUser.getPhone();
			String note = transDto.getTransactionNote();
			smsUtil.sendSmsTOPhone(String.format("%s", phone), String.format("%s", note));
			mailService.sendSimpleMail(transDto, transDto.getTransactionNote(), "text/html");

		} else {
			transDto.setTransactionType("0");
			transDto.setTransactionId("TRS-D-" + randonNumber++);
			transDto.setTransactionNote("An amount of INR " + transDto.getTransactionAmount()
					+ "has been CREDITED to your account " + transDto.getAccountNumber() + " on " + new Date()
					+ ". Total Avail.bal: INR " + findAccount.getBalance());

			accountInfo.updateBalance(transDto, transactionType);
			transHistService.insertintoTransactionHistories(transDto);

			String phone = findUser.getPhone();
			String note = transDto.getTransactionNote();
			smsUtil.sendSmsTOPhone(String.format("%s", phone), String.format("%s", note));
			mailService.sendSimpleMail(transDto, transDto.getTransactionNote(), "text/html");

		}

		transService.insertTransactionDetails(transDto);
		return ResponseEntity.ok().body(new Responses("Transaction Inserted successfully"));
	}

	@PostMapping("/atmTransaction")
	@PreAuthorize("hasAnyRole('User','ADMIN')")
	public ResponseEntity<Object> atmWithdrawal(@RequestParam("cardNumber") String cardNumber,
			@RequestParam("pin") String pin, @RequestBody TransactionDto transDto) {

		if (cardNumber.isEmpty() || pin.isEmpty()) {
			return ResponseEntity.badRequest().body(new Responses("Please enter card details"));
		}

		if (cardService.checkCardCredentials(cardNumber, pin)) {
			UserAccountInfo findAccount = accountInfo.GetUserDetailsWithAccNo(transDto.getAccountNumber());

			if ("0".equals(transDto.getTransactionStatus())) {
				transDto.setTransactionType("1");
				Long randomNumber = 419806290L;
				Atm findAtm = atmService.findByAtmId(transDto.getAtm());
				transDto.setTransactionId("TRS-W-" + randomNumber++);
				transDto.setTransactionNote(" An amount of INR " + transDto.getTransactionAmount()
						+ " has been DEBITED to your account " + transDto.getAccountNumber() + " on " + new Date()
						+ ". Total Avail.bal: INR " + findAccount.getBalance() + " near " + findAtm.getAtmName() + " "
						+ findAtm.getLocation());
				EmailTemplates templates = emailTemplateService.findTemplate(TemplateType.of("0"));
				accountInfo.updateBalance(transDto, "withdraw");
				transHistService.insertintoTransactionHistories(transDto);
				mailService.sendSimpleMail(transDto, templates.getBody(), "text/html");
			}

			if ("1".equals(transDto.getTransactionStatus())) {
				transDto.setTransactionType("1");
				Long randomNumber = 419806219L;
				transDto.setTransactionId("TRS-W-" + randomNumber++);
				EmailTemplates templates = emailTemplateService.findTemplate(TemplateType.of("1"));

				accountInfo.updateBalance(transDto, "withdraw");
				transHistService.insertintoTransactionHistories(transDto);
				mailService.sendSimpleMail(transDto, templates.getBody(), "text/html");
			}

			return ResponseEntity.ok().body(new Responses("Transaction Successful"));
		} else {
			return ResponseEntity.badRequest().body(new Responses("Invalid pin."));
		}
	}

}
