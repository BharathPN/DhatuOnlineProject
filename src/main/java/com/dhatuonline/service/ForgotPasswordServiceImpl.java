package com.dhatuonline.service;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dhatuonline.dto.UserDto;
import com.dhatuonline.model.User;
import com.dhatuonline.repo.UserRepository;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean createSecurityCodeAndSendEmail(UserDto userDto) {
		try {
			User user = userRepository.findByUserNameAndEmail(userDto.getUserName(), userDto.getEmail());
			if (userDto.getEmail() != null && userDto.getUserName() != null && user != null) {
				String randomNumber = Double.toString(Math.random() * 1000000).substring(0, 6);

				user.setSecurityCode(randomNumber);
				userRepository.save(user);
				sendEmail("Your secret code is " + randomNumber, "bharathpn95@gmail.com");
				return true;
			} else {
				throw new UsernameNotFoundException("User not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	private void sendEmail(String message, String toEmailId) throws AddressException, MessagingException, IOException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("<your-email>", "<your-password>");
			}
		});
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("bharathnag44@gmail.com", false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmailId));
		msg.setSubject("Confidential");
		msg.setContent(message, "text/html");
		msg.setSentDate(new Date());

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(message, "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		msg.setContent(multipart);
		Transport.send(msg);
	}

}
