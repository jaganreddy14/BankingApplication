package com.bankingapp.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.dto.AuthImgDto;
import com.bankingapp.model.AuthImage;
import com.bankingapp.model.User;
import com.bankingapp.repository.AuthImgrepository;
import com.bankingapp.repository.UserRepo;

@Service
public class AuthImageServiceImpl implements AuthImgService {

	@Autowired
	private AuthImgrepository authImgRepositoty;

	@Autowired
	private UserRepo userRepository;

	@Override
	public AuthImage saveImage(AuthImgDto authImgDto) {
		AuthImage newAuthImage = new AuthImage();

		newAuthImage.setImgName(authImgDto.getImage());
		newAuthImage.setLocation(authImgDto.getLocation());
		newAuthImage.setCreatedDate(new Date());
		User findUser = userRepository.findByUserName(authImgDto.getUserName());
		newAuthImage.setUser(findUser);
		newAuthImage.setUserName(authImgDto.getUserName());
		return authImgRepositoty.save(newAuthImage);
	}

	@Override
	public String getFileExtension(String fileName) {
		int dotIndex = fileName.lastIndexOf(".");
		if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
			return fileName.substring(dotIndex + 1).toLowerCase();
		}
		return "";
	}

	@Override
	public AuthImage getImageByUserName(String userName) {
		return authImgRepositoty.findByUserName(userName);
	}

}
