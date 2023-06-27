package com.arcesi.cliniquemedical.security.services;

public interface IEmailSenderService {

	void send (final String to,final  String email,final String subject);
}
