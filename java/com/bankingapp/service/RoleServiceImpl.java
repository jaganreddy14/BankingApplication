package com.bankingapp.service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.dto.RoleDto;
import com.bankingapp.model.Privilege;
import com.bankingapp.model.Role;
import com.bankingapp.model.SuperAdmin;
import com.bankingapp.repository.PrivilegeRepo;
import com.bankingapp.repository.RoleRepo;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepo roleRepository;

	@Autowired
	private PrivilegeRepo privilegeRepository;

	@Autowired
	private SuperAdmin superAdmin;

	@Override
	public void addNewRole(RoleDto role) {
		Set<Privilege> privileges = (Set<Privilege>) privilegeRepository.findAll();
		if (role != null) {
			Role roles = new Role(role);
			roles.setCreatedDate(new Date());
			roles.setPrivileges(privileges);
			roleRepository.save(roles);
		}

	}

	@Override
	public boolean deleteRole(String roleName) {
		List<Role> foundRole = roleRepository.findByRoleName(roleName);
		if (foundRole != null) {
			try {
				roleRepository.deleteAll(foundRole);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public List<Role> getAllRolesWithPrivileges() {

		List<Role> roles = roleRepository.findAll();
		roles.forEach(role -> {
			Set<Privilege> privileges = role.getPrivileges();
			role.setPrivileges(privileges);
		});
		return roles;

	}

	@Override
	public List<Role> getByRoleName(String name) {

		return roleRepository.findByRoleName(name);

	}

	@Override
	public Role getById(long id) {
		return roleRepository.findById(id).orElse(null);
	}

	@Override
	public boolean updateRole(RoleDto role, long id) {
		Role roles = roleRepository.findById(id).orElse(null);
		if (roles != null) {
			roles.setRoleName(role.getRoleName());
			roles.setModifiedDate(new Date());
			roles.setModifiedBy(superAdmin.admin);

			List<String> privileges = role.getPrivileges();
			Set<Privilege> privilegeList = (Set<Privilege>) privileges.stream()
					.map(privilegeRepository::findByPrivilegeName)
					.filter(existingPrivilege -> !existingPrivilege.isEmpty()).flatMap(List::stream)
					.collect(Collectors.toList());

			roles.setPrivileges(privilegeList);

			roleRepository.save(roles);
			return true;
		}
		return false;
	}

}
