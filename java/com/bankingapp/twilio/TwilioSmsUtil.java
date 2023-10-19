package com.bankingapp.twilio;

import org.springframework.stereotype.Component;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Component
public class TwilioSmsUtil {

	public static final String ACCOUNT_SID = "AC140f1e4e2af2449ac92ae29ce62fd442";
	public static final String AUTH_TOKEN = "2d0f8d7da8e4717b3ede293fd27631a6";

	public void sendSmsTOPhone(String phone,String msg) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		Message message = Message
				.creator(new PhoneNumber("+91"+phone), new PhoneNumber("+13393687033"),msg).create();
		
	}
	
	

}
