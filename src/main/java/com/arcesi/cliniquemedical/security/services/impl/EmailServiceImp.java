package com.arcesi.cliniquemedical.security.services.impl;

//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.arcesi.cliniquemedical.security.exceptions.InvalidEntityException;
import com.arcesi.cliniquemedical.security.services.IEmailSenderService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Zeroual tibari
 *
 */

@Service
@AllArgsConstructor
@Slf4j
public class EmailServiceImp implements IEmailSenderService {

	private final JavaMailSender mailSender;
	private final static String EMAIL_SEND_FROM = "cabinet-medical-spt@tibari.com";

	@Override
	@Async
	public void send(final String to, final String email, final String subject) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setText(email, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setFrom(EMAIL_SEND_FROM);
			helper.setReplyTo(EMAIL_SEND_FROM);
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			log.error("Failed to send email ", e);
			throw new InvalidEntityException("Failed to send email !!!!");
		}

	}

}
