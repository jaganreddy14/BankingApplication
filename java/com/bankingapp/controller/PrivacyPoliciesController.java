package com.bankingapp.controller;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bank")
public class PrivacyPoliciesController {

	private String directory="C:\\Users\\ekambaram_r\\UserFiles";

	@GetMapping("/getPrivacyPolicies")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<Object> getPrivacyPolicies() {
		try {
			Path filePath = Path.of(directory,"privacypolicies.txt");
			Resource resource = new FileSystemResource(filePath);

			if (resource.exists() && resource.isReadable()) {
				HttpHeaders headers = new HttpHeaders();
				headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=privacypolicies.txt");

				return ResponseEntity.ok().headers(headers).contentLength(resource.contentLength())
						.contentType(MediaType.TEXT_PLAIN).body(resource);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

}
