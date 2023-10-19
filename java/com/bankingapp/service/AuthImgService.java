package com.bankingapp.service;

import com.bankingapp.dto.AuthImgDto;
import com.bankingapp.model.AuthImage;

public interface AuthImgService {

	AuthImage saveImage(AuthImgDto authImgDto);

	String getFileExtension(String fileName);
	
	AuthImage getImageByUserName(String userName);

}
