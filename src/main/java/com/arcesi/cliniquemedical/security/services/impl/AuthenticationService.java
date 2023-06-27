package com.arcesi.cliniquemedical.security.services.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.arcesi.cliniquemedical.security.config.JwtService;
import com.arcesi.cliniquemedical.security.dtos.AuthenticationRequest;
import com.arcesi.cliniquemedical.security.dtos.AuthenticationResponse;
import com.arcesi.cliniquemedical.security.dtos.ForgotPasswordRequest;
import com.arcesi.cliniquemedical.security.dtos.PasswordResetRequest;
import com.arcesi.cliniquemedical.security.dtos.RegisterRequest;
import com.arcesi.cliniquemedical.security.entities.PasswordResetToken;
import com.arcesi.cliniquemedical.security.entities.RoleBean;
import com.arcesi.cliniquemedical.security.entities.TokenBean;
import com.arcesi.cliniquemedical.security.entities.UserBean;
import com.arcesi.cliniquemedical.security.enums.AppUserRole;
import com.arcesi.cliniquemedical.security.enums.ErrorsCodeUserRole;
import com.arcesi.cliniquemedical.security.enums.MessageEnum;
import com.arcesi.cliniquemedical.security.enums.MessageErreursApiConstants;
import com.arcesi.cliniquemedical.security.enums.TokenType;
import com.arcesi.cliniquemedical.security.enums.UrlMessageConstants;
import com.arcesi.cliniquemedical.security.exceptions.EntityNotFoundException;
import com.arcesi.cliniquemedical.security.exceptions.InvalidEntityException;
import com.arcesi.cliniquemedical.security.repositories.PasswordResetTokenRepository;
import com.arcesi.cliniquemedical.security.repositories.RoleBeanRepository;
import com.arcesi.cliniquemedical.security.repositories.TokenBeanRepository;
import com.arcesi.cliniquemedical.security.repositories.UserBeanRepository;
import com.arcesi.cliniquemedical.security.services.IAuthenticationService;
import com.arcesi.cliniquemedical.security.services.IEmailSenderService;
import com.arcesi.cliniquemedical.security.templateemail.TemplateEmail;
import com.arcesi.cliniquemedical.security.validatorssecurity.ValidatorsUserRole;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService implements IAuthenticationService {
	private final UserBeanRepository repository;
	private final TokenBeanRepository tokenRepository;
	private final RoleBeanRepository roleBeanRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final ModelMapper modelMapper;

	@Autowired
	private ValidatorsUserRole<RegisterRequest> validatorsUserRole;
	@Autowired
	private ValidatorsUserRole<ForgotPasswordRequest> forgotPassword;

	@Autowired
	private ValidatorsUserRole<PasswordResetRequest> passwordRequest;
	@Autowired
	private ValidatorsUserRole<AuthenticationRequest> validatorsUserRequest;
	private final AuthenticationManager authenticationManager;
	@Autowired
	private final IEmailSenderService emailSenderService;
	private final PasswordResetTokenRepository passwordResetTokenRepository;

	private void saveUserToken(UserBean user, String jwtToken) {
		var token = TokenBean.builder().userBean(user).token(jwtToken).tokenType(TokenType.BEARER).expired(false)
				.revoked(false).build();
		tokenRepository.save(token);
	}

	private void revokeAllUserTokens(UserBean user) {
		var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
		if (validUserTokens.isEmpty())
			return;
		validUserTokens.forEach(token -> {
			token.setExpired(true);
			token.setRevoked(true);
		});
		tokenRepository.saveAll(validUserTokens);
	}

	@Override
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		final String refreshToken;
		final String userEmail;
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			return;
		}
		refreshToken = authHeader.substring(7);
		userEmail = jwtService.extractUsername(refreshToken);
		if (userEmail != null) {
			var user = this.repository.findUserBeanByEmail(userEmail).orElseThrow();
			if (jwtService.isTokenValid(refreshToken, user)) {
				var accessToken = jwtService.generateToken(user);
				revokeAllUserTokens(user);
				saveUserToken(user, accessToken);
				var authResponse = AuthenticationResponse.builder().accessToken(accessToken).refreshToken(refreshToken)
						.build();
				new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
			}
		}
	}

	@Override
	public AuthenticationResponse register(RegisterRequest request) {
		log.info("Inside methode register of AuthenticationService RegisterRequest : {}", request.toString());
		Map<String, String> violations = validatorsUserRole.validate(request);
		if (!violations.isEmpty()) {

			log.error(MessageErreursApiConstants.MESSAGE_ERRORS_VIOLATIONS, violations);
			throw new InvalidEntityException(MessageErreursApiConstants.MESSAGE_ERRORS_VIOLATIONS,
					ErrorsCodeUserRole.USER_NOT_VALIDE, violations);
		}
		// check if user email exist
		Optional<UserBean> checkIuserExist = repository.findUserBeanByEmail(request.getEmail());
		if (checkIuserExist.isPresent()) {
			log.info(String.format(MessageErreursApiConstants.USER_WITH_EMAIL_EXIST, request.getEmail()));
			throw new EntityNotFoundException(
					String.format(MessageErreursApiConstants.USER_WITH_EMAIL_EXIST, request.getEmail()),
					ErrorsCodeUserRole.USER_EMAIL_EXISTS);
		}
		// Vérify password and password confirmation
		if (!request.getPassword().equals(request.getConfirmerPassword())) {
			log.info(MessageErreursApiConstants.PASSWORD_AND_CONFIRMATION_NOT_EQUALS);
			throw new InvalidEntityException(MessageErreursApiConstants.PASSWORD_AND_CONFIRMATION_NOT_EQUALS);
		}
		var user = modelMapper.map(request, UserBean.class);
		user.setCreatedAt(Instant.now());
		user.setUidUser(UUID.randomUUID().toString());
		// user.setEnabled(Boolean.TRUE);
		// user.setLocked(Boolean.TRUE);
		user.setTokenActivationEmail(UUID.randomUUID().toString());
		user.setExpiresAt(LocalDateTime.now().plusDays(7));
		try {
			user.setIpAdresse(InetAddress.getLocalHost().getHostAddress().toString());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		RoleBean rolePatient = roleBeanRepository.findRoleBeanByRoleName(AppUserRole.PATIENT.getId()).get();
		user.getRoleBeans().add(rolePatient);
		// Encode password
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		var savedUser = repository.save(user);
		// Send Email to Patient
		String link = UrlMessageConstants.URL_CONFIRMATION_PATIENT + savedUser.getTokenActivationEmail();
		// send email
		emailSenderService.send(savedUser.getEmail(),
				TemplateEmail.buildEmailRegistration(savedUser.getFirstName() + " " + savedUser.getLastName(), link),
				MessageEnum.CREATE_ACCOUNT_SUBJECT.getId());
		var jwtToken = jwtService.generateToken(user);
		var refreshToken = jwtService.generateRefreshToken(user);
		saveUserToken(savedUser, jwtToken);
		return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
	}

	@Override
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		log.info("Inside methode authenticate ofAuthenticationService email : {}", request.getEmail());
		Map<String, String> violations = validatorsUserRequest.validate(request);
		if (!violations.isEmpty()) {

			log.error(MessageErreursApiConstants.MESSAGE_ERRORS_VIOLATION_AUTHENTICATE, violations);
			throw new InvalidEntityException(MessageErreursApiConstants.MESSAGE_ERRORS_VIOLATION_AUTHENTICATE,
					ErrorsCodeUserRole.USER_EMAIL_PASSWORD_NOT_VALID, violations);
		}
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		var user = repository.findUserBeanByEmail(request.getEmail()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);
		var refreshToken = jwtService.generateRefreshToken(user);
		revokeAllUserTokens(user);
		saveUserToken(user, jwtToken);
		return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
	}

	@Override
	public int enableUserBean(String email) {
		log.info("Inside methode enableUserBean ofAuthenticationService email : {}", email);
		return repository.enableAppUser(email);
	}

	@Override
	public void confirm_email(@RequestParam(name = "token") String token) {
		log.info("Inside methode confirm_email of AuthenticationService  token : {}", token);
		Optional<UserBean> findUserByTokenActivation = repository.findByTokenActivationEmail(token);
		if (findUserByTokenActivation.isEmpty()) {
			log.error(String.format(MessageErreursApiConstants.TOKEN_ACTIVATION, token));
			throw new EntityNotFoundException(MessageErreursApiConstants.TOKEN_ACTIVATION);

		}
		if (findUserByTokenActivation.get().getConfirmedAt() != null) {
			log.error(MessageErreursApiConstants.EMIAL_ACTIVATED);
			throw new EntityNotFoundException(MessageErreursApiConstants.EMIAL_ACTIVATED);
		}
		LocalDateTime expiresAt = findUserByTokenActivation.get().getExpiresAt();
		if (expiresAt.isBefore(LocalDateTime.now())) {
			log.error(MessageErreursApiConstants.LINK_EXPIRED);
			throw new EntityNotFoundException(MessageErreursApiConstants.LINK_EXPIRED);
		}
		// save Confirmed At
		findUserByTokenActivation.get().setConfirmedAt(LocalDateTime.now());
		repository.saveAndFlush(findUserByTokenActivation.get());
		// Enable user
		repository.enableAppUser(findUserByTokenActivation.get().getEmail());
		// Send Email confirmation validation email
		// Send Email to Patient
		String link = "http://localhost:8080/api/v1/auth/authenticate";
		// send email
		emailSenderService.send(findUserByTokenActivation.get().getEmail(),
				TemplateEmail.buildEmailConfirmation(findUserByTokenActivation.get().getFirstName() + " "
						+ findUserByTokenActivation.get().getLastName(), link),
				MessageEnum.CONFIRMATION_VALIDATION_ACCOUNT.getId());
		log.info(MessageErreursApiConstants.USER_ENABLED);
	}

	@Override
	public void forgotPassword(ForgotPasswordRequest forgot) {
		log.info("Inside methode forgotPassword of AuthenticationService , email : {}", forgot.getEmail());
		Map<String, String> violations = forgotPassword.validate(forgot);
		if (!violations.isEmpty()) {

			log.error(MessageErreursApiConstants.FORGOT_PASSWORD_VIOLATIONS, violations);
			throw new InvalidEntityException(MessageErreursApiConstants.FORGOT_PASSWORD_VIOLATIONS,
					ErrorsCodeUserRole.USER_EMAIL_NOT_VALIDE, violations);
		}

		// Check if user email exist
		Optional<UserBean> userBean = repository.findUserBeanByEmail(forgot.getEmail());
		if (userBean.isEmpty()) {
			log.info(String.format(MessageErreursApiConstants.USER_NOT_FOUND, forgot.getEmail()));
			throw new EntityNotFoundException(
					String.format(MessageErreursApiConstants.USER_NOT_FOUND, forgot.getEmail()));
		}

		// proceed to send email with link to reset password to this email address

		PasswordResetToken token = PasswordResetToken.builder().userBean(userBean.get())
				.expirationDate(LocalDateTime.now().plusMinutes(60))
				.token(UUID.randomUUID().toString() + "" + UUID.randomUUID().toString()).build();
		token = passwordResetTokenRepository.save(token);
		if (token == null) {
			log.error("Token not saved ", token);
			throw new EntityNotFoundException("Error saved token !!!");

		}

		String link = UrlMessageConstants.URL_RESET_PASSWORD + token.getToken();
		// send email
		emailSenderService.send(userBean.get().getEmail(),
				TemplateEmail.buildReinitialisationPassword(
						userBean.get().getFirstName() + " " + userBean.get().getLastName(), link),
				MessageEnum.REINITIALISATION_PASSWORD.getId());
	}

	@Override
	public void resetPassword(PasswordResetRequest passwordResetRequest) {
		log.info("Inside methode ResetPassword of AuthenticationService , email : {}", passwordResetRequest.toString());
		Map<String, String> violations = passwordRequest.validate(passwordResetRequest);
		if (!violations.isEmpty()) {
			log.error(MessageErreursApiConstants.RESET_PASSWORD_VIOLATIONS, violations);
			throw new InvalidEntityException(MessageErreursApiConstants.RESET_PASSWORD_VIOLATIONS,
					ErrorsCodeUserRole.USER_PASSWORD_TOKEN, violations);
		}

		Optional<PasswordResetToken> passwordResetToken = passwordResetTokenRepository
				.findByToken(passwordResetRequest.getToken());
		if (passwordResetToken.isEmpty()) {
			log.error(String.format(MessageErreursApiConstants.TOKEN_NOT_FOUND, passwordResetRequest.getToken()));
			throw new EntityNotFoundException(
					String.format(MessageErreursApiConstants.TOKEN_NOT_FOUND, passwordResetRequest.getToken()));
		}

		if (passwordResetToken.get().getExpirationDate().isBefore(LocalDateTime.now())) {
			log.error(String.format(MessageErreursApiConstants.TOKEN_EXPIRED, passwordResetToken.get().getToken()));
			throw new InvalidEntityException(
					String.format(MessageErreursApiConstants.TOKEN_EXPIRED, passwordResetToken.get().getToken()));
		}

		if (!passwordResetRequest.getPassword().equalsIgnoreCase(passwordResetRequest.getConfirmPassword())) {
			log.error(MessageErreursApiConstants.PASSWORD_NOT_EQUALS);
			throw new InvalidEntityException(MessageErreursApiConstants.PASSWORD_NOT_EQUALS);
		}
		UserBean user = passwordResetToken.get().getUserBean();
		user.setPassword(passwordEncoder.encode(passwordResetRequest.getPassword()));
		repository.saveAndFlush(user);
		log.info("Password updated successfully !!!!");
	}
}
