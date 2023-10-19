//package com.bankingapp.util;
//
//import java.util.Date;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import com.bankingapp.service.UserService;
//
//@Component
//public class ScheduledTask {
//
//	@Value("${reset.failedLogin.cron}")
//	private String cronExpression;
//
//	@Autowired
//	private UserService userService;
//
//	
//
//	@Scheduled(fixedRate = 10000,initialDelay = 4000)
//	public void resetFailedLoginWithDefaultCron() {
//		if (cronExpression.isEmpty()) {
//			userService.handleFailedLogin();
//		}
//	}
//
//	@Scheduled(cron = "${reset.failedLogin.cron}")
//	public void resetFailedLogin() {
//		System.out.println("test " + new Date());
//		if (!cronExpression.isEmpty()) {
//			userService.handleFailedLogin();
//		}
//	}
//
//}
