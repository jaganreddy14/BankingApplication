package com.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.dto.UserAccInfoDto;
import com.bankingapp.service.UserAccInfoService;
import com.bankingapp.util.Responses;

@RestController
@RequestMapping("/api/bank")
public class AccountInfoController {
	
	@Autowired
	private UserAccInfoService accInfoService;
	
	@PostMapping("/AddUserAccInfo")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Object> insertUserAccountDetails(@RequestBody UserAccInfoDto accInfoDto){
		if(accInfoDto == null) {
			return ResponseEntity.badRequest().body(new Responses("Please Enter Account details."));
		}
		
		accInfoService.insertUserAccountInfo(accInfoDto);
		return ResponseEntity.ok().body(new Responses("Data inserted Successfully."));
		
	}

}
