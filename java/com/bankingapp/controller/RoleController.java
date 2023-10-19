package com.bankingapp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapp.dto.RoleDto;
import com.bankingapp.model.Role;
import com.bankingapp.service.RoleService;
import com.bankingapp.util.Responses;

@RestController
@RequestMapping("/api/bank")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@PostMapping("/addNew")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> insertNewRole(@RequestBody RoleDto role) {
		if (role != null) {
			try {
				roleService.addNewRole(role);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return ResponseEntity.ok().body(new Responses("New role added successfully"));
		}
		return ResponseEntity.badRequest().body(new Responses("Something went wrong while adding role"));

	}

	@DeleteMapping("/deleteRoleName/{roleName}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> deleteExistingRole(@PathVariable("roleName") String roleName) {
		if (roleService.deleteRole(roleName)) {

			return ResponseEntity.ok().body(new Responses("role deleted"));
		}

		return ResponseEntity.ok().body(new Responses("Something went wrong while deleting role"));
	}

	@PostMapping("/findByRoleName/{rolename}")
	public ResponseEntity<Object> findByRoleName(@PathVariable("rolename") String roleName) {
		List<Role> foundRole = roleService.getByRoleName(roleName);
		if (foundRole != null) {
			return ResponseEntity.ok().body(foundRole);
		}
		return ResponseEntity.badRequest().body(new Responses("No roles are found"));
	}

	@PostMapping("/getAllRolesWithPrivilegs")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> getAllRoles() {
		try {
			List<Role> roles = roleService.getAllRolesWithPrivileges();
			if (roles != null) {
				List<RoleDto> roleWithPrivilege = roles.stream().map(role -> new RoleDto(role))
						.collect(Collectors.toList());
				return ResponseEntity.ok().body(roleWithPrivilege);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().body(new Responses("No roles found"));
	}

	@PostMapping("/updateRole/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> updaterole(@RequestBody RoleDto role, @PathVariable("id") long id) {
		if (role != null && roleService.updateRole(role, id)) {
			return ResponseEntity.ok().body(new Responses("Success"));
		}
		return ResponseEntity.badRequest().body(new Responses("Something went wrong"));
	}

}
