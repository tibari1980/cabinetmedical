package com.arcesi.cliniquemedical.security.services;

import java.io.IOException;

import com.arcesi.cliniquemedical.security.dtos.AuthenticationRequest;
import com.arcesi.cliniquemedical.security.dtos.AuthenticationResponse;
import com.arcesi.cliniquemedical.security.dtos.ForgotPasswordRequest;
import com.arcesi.cliniquemedical.security.dtos.PasswordResetRequest;
import com.arcesi.cliniquemedical.security.dtos.RegisterRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IAuthenticationService {

	public AuthenticationResponse register(RegisterRequest registerRequest);

	public AuthenticationResponse authenticate(AuthenticationRequest autRequest);

	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

	public int enableUserBean(final String email);

	public void confirm_email(final String token);

	public void forgotPassword(final ForgotPasswordRequest forgot);

	public void resetPassword(final PasswordResetRequest passwordResetRequest);
	
	

}
