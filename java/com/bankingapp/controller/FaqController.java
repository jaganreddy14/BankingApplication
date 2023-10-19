package com.bankingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.dto.FaqDto;
import com.bankingapp.model.Faq;
import com.bankingapp.service.FaqService;
import com.bankingapp.util.Responses;

@RestController
@RequestMapping("/api/bank")
public class FaqController {

	@Autowired
	private FaqService faqService;

	@PostMapping("/newfaq")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> addNewFAQ(@RequestBody FaqDto faqDto) {

		if (faqDto == null) {
			return ResponseEntity.badRequest().body(new Responses(" No response from user"));
		}

		faqService.addNewFAQ(faqDto);
		return ResponseEntity.ok().body(new Responses("New FAQ added Successfully for type " + faqDto.getUserType()));

	}

	@PostMapping("/updateFaq")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> updateFaq(@RequestBody FaqDto faq) {

		if (faq == null) {
			return ResponseEntity.badRequest().body(new Responses(" No response from user"));
		}

		faqService.updateExistingFAQ(faq);
		return ResponseEntity.ok().body(new Responses("update successful."));
	}

	@PostMapping("/deleteFaq")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> deleteFaqById(@RequestParam("id") Long id) {

		if (id == null) {
			return ResponseEntity.badRequest().body(new Responses(" No response from user"));
		}

		faqService.deleteFAQById(id);
		return ResponseEntity.ok().body(new Responses("Deleted successful."));
	}

	@GetMapping("getAll")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> getAllFaq() {
		List<Faq> findAll = faqService.getAll();
		return ResponseEntity.ok().body(findAll);
	}
	

}
