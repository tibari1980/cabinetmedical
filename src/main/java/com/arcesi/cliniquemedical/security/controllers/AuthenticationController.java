package com.arcesi.cliniquemedical.security.controllers;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arcesi.cliniquemedical.security.dtos.AuthenticationRequest;
import com.arcesi.cliniquemedical.security.dtos.AuthenticationResponse;
import com.arcesi.cliniquemedical.security.dtos.ForgotPasswordRequest;
import com.arcesi.cliniquemedical.security.dtos.PasswordResetRequest;
import com.arcesi.cliniquemedical.security.dtos.RegisterRequest;
import com.arcesi.cliniquemedical.security.services.IAuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

  private final IAuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ) {
	  log.info("Inside methode register of AuthenticationController register : {}",request.toString());
    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
	  log.info("Inside methode authenticate of AuthenticationController authentication : {} ",request.toString());
    return ResponseEntity.ok(service.authenticate(request));
  }

  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    service.refreshToken(request, response);
  }

  @GetMapping("/confirm_email")
  public ResponseEntity<String> confirm_email(@RequestParam(name="token") String token){
	  log.info("Inside methode confirm_email of AuthenticationController  token : {},",token);
	  service.confirm_email(token);
	  log.info("end Methode confirm_email :Email confirmed successfully ");
	  return new ResponseEntity<String>("Email  confirmed successfully !!!",HttpStatus.ACCEPTED);
  }
  
  @PostMapping("/check-email")
  public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest forgot ){
	  
	  service.forgotPassword(forgot);
	  return new ResponseEntity<String>("Vous allez recevoir un e-mail de notre part contenant des instructions pour réinitialiser votre mot de passe si votre compte existe. Si vous n'avez pas reçu cet e-mail, consultez notre page d'Aide.",HttpStatus.ACCEPTED);
  }
  
  @PostMapping("/reset-password")
  public ResponseEntity<String> resetPassword(@RequestParam(name="token",required = false) String token, @RequestBody PasswordResetRequest passwordResetRequest ){
	  log.info("Inside methode resetPassword of AuthenticationController passwordRequest: {}",passwordResetRequest);
      service.resetPassword(passwordResetRequest);
	  return new ResponseEntity<String>("Password updated ",HttpStatus.ACCEPTED);
  }
}
