package com.bankingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.dto.AdminDto;
import com.bankingapp.dto.AdminResponseDto;
import com.bankingapp.dto.UserActivityDto;
import com.bankingapp.enums.UserActivityType;
import com.bankingapp.model.Transaction;
import com.bankingapp.model.TransactionHistories;
import com.bankingapp.model.User;
import com.bankingapp.model.UserAccountInfo;
import com.bankingapp.repository.UserRepo;
import com.bankingapp.service.AdminService;
import com.bankingapp.service.TransHistService;
import com.bankingapp.service.TransactionService;
import com.bankingapp.service.UserAccInfoService;
import com.bankingapp.service.UserActivityService;
import com.bankingapp.util.Responses;

@RestController
@RequestMapping("/api/bank/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private TransHistService transactionHistories;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private UserAccInfoService accInfoService;

	@Autowired
	private UserRepo userRepository;

	@Autowired
	private UserActivityService activityService;

	@PostMapping("/registerAdmin")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> registerAdmin(@RequestBody AdminDto admin) {

		if (admin == null) {
			return ResponseEntity.badRequest().body(new Responses("Please enter admin details to register"));
		}

		adminService.registerAdmin(admin);
		return ResponseEntity.ok().body(new Responses("Admin registered successfully."));

	}

	@PostMapping("/setNewAdminPrivilege")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> setNewPrivilege(@RequestBody AdminDto adminDto, List<String> privileges) {

		if (adminDto == null && privileges == null) {
			return ResponseEntity.badRequest().body(new Responses("Please enter the privileges you wanted to add"));
		}

		adminService.addPrivilegeToAdmin(adminDto, privileges);
		return ResponseEntity.ok().body(new Responses("Assigned new privilege successfully"));
	}

	@PostMapping("/getAllUserAccountDetails")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> getAllUserAccounts(@RequestParam("accountNumber") Long accountNumber,
			@RequestParam("from") String from, @RequestParam("to") String to) {

		if (accountNumber == null) {
			return ResponseEntity.badRequest().body(new Responses("please select a account"));
		}

		User foundUser = adminService.getAllUserDetails(accountNumber);
		UserAccountInfo findAccount = accInfoService.GetUserDetailsWithAccNo(accountNumber);
		List<TransactionHistories> periodicTrasactions = transactionHistories.filterTransactions(accountNumber, from,
				to);

		AdminResponseDto responseDto = new AdminResponseDto();
		responseDto.setAccountNumber(accountNumber);
		responseDto.setUserName(foundUser.getUserName());
		responseDto.setBalance(findAccount.getBalance());
		responseDto.setEmail(foundUser.getEmail());
		responseDto.setClickToGetAllTransactions("http://localhost:8080/api/bank/allTransactions/" + accountNumber);
		
		UserActivityDto newactivity = new UserActivityDto();
		newactivity.setUserActivity("5");
		User findUser = userRepository.findByAccountNumber(accountNumber);
		newactivity.setUserId(findUser);
		activityService.insertUserActivities(newactivity);


		responseDto.setTransactionsHistories(periodicTrasactions);
		return ResponseEntity.ok().body(responseDto);

	}

	@PostMapping("/allTransactions/{accountNumber}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> getAllTransactions(@RequestParam("accountNumber") Long accountNumber) {

		if (accountNumber == null) {
			return ResponseEntity.badRequest().body(new Responses("please select a account"));
		}
		UserAccountInfo findAccount = accInfoService.GetUserDetailsWithAccNo(accountNumber);

		User foundUser = adminService.getAllUserDetails(accountNumber);
		List<Transaction> transactions = transactionService.getAllTransactions(accountNumber);
		AdminResponseDto responseDto = new AdminResponseDto();
		responseDto.setAccountNumber(accountNumber);
		responseDto.setUserName(foundUser.getUserName());
		responseDto.setBalance(findAccount.getBalance());
		responseDto.setEmail(foundUser.getEmail());
		responseDto.setTransactions(transactions);
		
		UserActivityDto newactivity = new UserActivityDto();
		newactivity.setUserActivity("11");
		User findUser = userRepository.findByAccountNumber(accountNumber);
		newactivity.setUserId(findUser);
		activityService.insertUserActivities(newactivity);

		return ResponseEntity.ok().body(responseDto);

	}

	@PostMapping("/approveUserAccount")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> approveUserAccount(@RequestParam("accountNumber") Long accountNumber,
			@RequestParam("approval") Boolean approval) {

		if (accountNumber != null && approval.equals(true)) {

			adminService.approveUserBankAccount(accountNumber, approval);

			UserActivityDto newactivity = new UserActivityDto();
			newactivity.setUserActivity("3");
			User findUser = userRepository.findByAccountNumber(accountNumber);
			newactivity.setUserId(findUser);
			activityService.insertUserActivities(newactivity);
			return ResponseEntity.ok().body(new Responses("User bank account approved"));
		}
		return ResponseEntity.badRequest().body(new Responses("No Response found"));
	}

}
