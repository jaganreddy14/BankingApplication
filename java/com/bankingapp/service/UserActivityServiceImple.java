package com.bankingapp.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankingapp.Exception.NoActivityFoundByUser;
import com.bankingapp.dto.UserActivityDto;
import com.bankingapp.enums.UserActivityType;
import com.bankingapp.model.UserActivities;
import com.bankingapp.repository.UserActivityRepo;

@Service
public class UserActivityServiceImple implements UserActivityService {

	@Autowired
	private UserActivityRepo activityRepository;

	@Override
	public UserActivities insertUserActivities(UserActivityDto activitiesDto) {

		if (activitiesDto == null) {
			try {
				throw new NoActivityFoundByUser("No activity found");
			} catch (NoActivityFoundByUser e) {
				e.printStackTrace();
			}
		}

		UserActivities newActivity = new UserActivities();
		newActivity.setUserId(activitiesDto.getUserId());
		newActivity.setUserActivity(UserActivityType.of(activitiesDto.getUserActivity()));
		newActivity.setCreatedDate(new Date());

		return activityRepository.save(newActivity);
	}

}
