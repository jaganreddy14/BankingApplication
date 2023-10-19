package com.bankingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.dto.PrivilegeDto;
import com.bankingapp.model.Privilege;
import com.bankingapp.service.PrivilegeService;
import com.bankingapp.util.Responses;

@RestController
@RequestMapping("/api/bank")
public class PrivilegeController {

	@Autowired
	private PrivilegeService privilegeService;

	@PostMapping("/addprivilege")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> addPrivilege(@RequestBody PrivilegeDto dto) {
		if (dto == null) {
			return ResponseEntity.badRequest().body(new Responses("Something went wrong."));
		}
		try {
			privilegeService.addNewPrivilege(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().body(new Responses("Privilege added."));
	}

	@DeleteMapping("/deleteprivilege/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> deleteprivilege(@PathVariable("id") long id) {
		if (privilegeService.deletePrivilege(id)) {

			return ResponseEntity.ok().body(new Responses("Deleted successfully."));
		} else {
			return ResponseEntity.ok().body(new Responses("Something went wrong."));
		}

	}

	@PostMapping("/updatePrivilegeById/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> updateprivilege(@PathVariable("id") long id, PrivilegeDto dto) {
		if (privilegeService.updatePrivilege(id, dto)) {

			return ResponseEntity.ok().body(new Responses("Updated successfully."));
		} else {
			return ResponseEntity.ok().body(new Responses("Update failed."));
		}
	}

	@PostMapping("/getprivileges")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> getAllPrivileges() {
		List<Privilege> privileges = privilegeService.getAllPrivileges();
		if (privileges != null) {
			return ResponseEntity.ok().body(privileges);
		} else {
			return ResponseEntity.ok().body(new Responses("Something went wrong."));
		}
	}
}
