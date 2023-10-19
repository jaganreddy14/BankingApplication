package com.bankingapp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.dto.PrivilegeDto;
import com.bankingapp.model.Privilege;
import com.bankingapp.repository.PrivilegeRepo;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

	@Autowired
	private PrivilegeRepo privilegeRepository;

	@Override
	public void addNewPrivilege(PrivilegeDto dto) {
		List<Privilege> findPrivilege = privilegeRepository.findByPrivilegeName(dto.getPrivilegeName());
		Privilege privilege = new Privilege();
		if (findPrivilege==null) {
			privilege.setPrivilegeName(dto.getPrivilegeName());
			privilege.setCreatedDate(new Date());
			privilegeRepository.save(privilege);
		}
	}

	@Override
	public boolean deletePrivilege(long id) {
		try {
			privilegeRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean updatePrivilege(long id, PrivilegeDto dto) {

		Privilege findPrivilege = privilegeRepository.findById(dto.getPrivilegeId()).orElse(null);
		
		if (findPrivilege == null) {
			return false;
		} 
		findPrivilege.setPrivilegeName(dto.getPrivilegeName());
//		findPrivilege.setModifiedBy(dto.getModifiedBy());
		findPrivilege.setModifiedDate(new Date());
//		findPrivilege.setCreatedBy(dto.getCreatedBy());
		privilegeRepository.save(findPrivilege);
		return true;

	}

	@Override
	public List<Privilege> getAllPrivileges() {
		return privilegeRepository.findAll();
	}

	@Override
	public List<Privilege> getPrivilegeByName(String PrivilegeName) {
		return privilegeRepository.findByPrivilegeName(PrivilegeName);
	}

}
