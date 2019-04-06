package com.startup.java.training.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleMailController {

	@Autowired
	private JavaMailSender sender;

	@RequestMapping("/sendMail")
	public String sendMail() {
		System.out.println("Alhamdulillah!");
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setTo("abdm03142014@gmail.com");
			helper.setText("Greetings :)");
			helper.setSubject("Mail From Spring Boot");
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Error while sending mail ..";
		}
		sender.send(message);
		return "Mail Sent Successfully!";
	}
	
	@RequestMapping("sendMailAttachment")
	public String sendMailAttachment() throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		try {
			helper.setTo("abdm03142014@gmail.com");
			helper.setText("Greetings :)");
			helper.setSubject("Mail From Spring Boot");
			ClassPathResource file = new ClassPathResource("attachment.txt");
			helper.addAttachment("attachment.txt", file);
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Error while sending mail ..";
		}
		sender.send(message);
		return "Mail Sent Successfully!";
	}
}
