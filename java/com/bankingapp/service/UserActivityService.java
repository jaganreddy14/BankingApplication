package com.bankingapp.service;

import com.bankingapp.dto.UserActivityDto;
import com.bankingapp.model.UserActivities;

public interface UserActivityService {
	
	UserActivities insertUserActivities(UserActivityDto ActivitiesDto);
	

}
