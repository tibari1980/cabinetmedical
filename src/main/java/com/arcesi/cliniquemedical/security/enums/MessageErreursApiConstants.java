
package com.arcesi.cliniquemedical.security.enums;

public class MessageErreursApiConstants {

	public static final String MESSAGE_ERRORS_VIOLATIONS = "There are errors while saving user try again !!!";

	public static final String USER_WITH_EMAIL_EXIST = "User with email : ` %s `  exist in our data base try with another email !";

	public static final String PASSWORD_AND_CONFIRMATION_NOT_EQUALS = "Password and password confirmation are different try again !!!";
	public static final String MESSAGE_ERRORS_VIOLATION_AUTHENTICATE = "there are errors when connecting, please check the email and password thank you :) ";

	public static final String TOKEN_ACTIVATION = " User with  token activation  : %s not found in our data base";
	public static final String EMIAL_ACTIVATED = "Email already activated !!!!";
	public static final String LINK_EXPIRED = "link to activate email address is expired";
	public static final String USER_ENABLED = "User enbled and email activated successfully !!!";
	public static final String FORGOT_PASSWORD_VIOLATIONS = "There are errors while retrieving the password";
	public static final String USER_NOT_FOUND = "user with email: %s not found try again";
	public static final String RESET_PASSWORD_VIOLATIONS = "There are errors while retrieving the token";
	public static final String TOKEN_NOT_FOUND="Token not found in our data base token : % ";
	public static final String TOKEN_EXPIRED="Token : %s expired !!!";
	public static final String PASSWORD_NOT_EQUALS="The password is different from the confirmation";
}
