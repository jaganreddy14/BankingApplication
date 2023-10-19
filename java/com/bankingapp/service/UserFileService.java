package com.bankingapp.service;

import com.bankingapp.dto.UserFileDto;
import com.bankingapp.model.UserFiles;

public interface UserFileService {

	UserFiles fileUploadInfo(UserFileDto fileDto);
	
	UserFiles getbyUserName(String userName);
}
