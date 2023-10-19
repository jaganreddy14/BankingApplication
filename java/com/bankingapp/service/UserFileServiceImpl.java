package com.bankingapp.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bankingapp.Exception.UsernameNotFoundException;
import com.bankingapp.dto.UserFileDto;
import com.bankingapp.model.SuperAdmin;
import com.bankingapp.model.User;
import com.bankingapp.model.UserFiles;
import com.bankingapp.repository.UserFileRepositoy;
import com.bankingapp.repository.UserRepo;

@Service
public class UserFileServiceImpl implements UserFileService {

	@Autowired
	private UserFileRepositoy fileRepositoy;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private SuperAdmin superAdmin;

	@Override
	public UserFiles fileUploadInfo(UserFileDto filedto) {

		if (filedto == null) {
			return null;
		}
		
		UserFiles newFile = new UserFiles();
		newFile.setFileName(filedto.getFileName());
		newFile.setLocation(filedto.getLocation());
		newFile.setSize(filedto.getSize());
		newFile.setCreatedDate(new Date());
		newFile.setCreatedBy(superAdmin.admin);
		newFile.setUserName(filedto.getUserName());
		
		try {
			User foundUser = userRepo.findByUserName(filedto.getUserName());
			if (foundUser == null) {
				throw new UsernameNotFoundException("User Not Found");
			}
			
			 newFile.setUser(foundUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
			fileRepositoy.save(newFile);
		return newFile;

	}

	@Override
	public UserFiles getbyUserName(String userName) {
		return fileRepositoy.findByUserName(userName);
	}

}
