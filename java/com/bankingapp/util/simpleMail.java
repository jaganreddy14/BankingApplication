package com.bankingapp.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.bankingapp.dto.TransactionDto;
import com.bankingapp.model.User;
import com.bankingapp.repository.UserRepo;

@Service
public class simpleMail {

//	@Autowired
//	private JavaMailSender mailSender;

	@Autowired
	private UserRepo userRepository;

	@Autowired
	private Environment environment;

	@Value("${spring.mail.username}")
	private String sender;

	public void sendSimpleMail(TransactionDto dto, String body, String text) {

		Properties props = new Properties();
		 props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", environment.getProperty("spring.mail.host"));
	      props.put("mail.smtp.port", "25");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(environment.getProperty("spring.mail.username"),
						environment.getProperty("spring.mail.password"));
			}
		});

		User findUser = userRepository.findByAccountNumber(dto.getAccountNumber());

		try {
			Message message = new MimeMessage(session);
			message.setFrom((new InternetAddress(sender)));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(findUser.getEmail()));
			message.setSubject("Transaction Alert!!!");
			message.setContent(body, text);

			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
		}

//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setFrom(sender);
//		message.setTo(findUser.getEmail());
//		message.setSubject("Alert!!!!");
//		message.setText(text);
//		mailSender.send(message);

	}
}
