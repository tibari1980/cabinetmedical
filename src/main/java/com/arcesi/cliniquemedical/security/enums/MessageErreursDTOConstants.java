package com.arcesi.cliniquemedical.security.enums;

public class MessageErreursDTOConstants {

	public static final String FIRSTNAME_NOT_EMPTY = "First name  must not be empty.";
	public static final String FIRST_NAME_SIZE_MIN_MAX = "First name must have a minimum of 5 characters and a maximum of 40 characters.";
	public static final String FIRST_NAME_PATTERN = "First name  require alphanumiric and space .";

	public static final String LAST_NAME_NOT_BLANK = "Last name  must not be empty.";
	public static final String LAST_NAME_SIZE_MIN_MAX = "Last name must have a minimum of 5 characters and a maximum of 40 characters.";
	public static final String LAST_NAME_PATTERN = "Last name require alphanumiric and space .";

	public static final String EMAIL_PATTERN = "Email is not valid!!";
	
	public static final String PASSWORD_NOT_BLANK="Password must not be empty.";
	public static final String PASSWORD_PATTERN="Password must have: uppercase letter, lowercase letter, special characters. between 8 and 20.";

	public static final String PASSWORD_CONFIRMATION_NOT_BLANK="Confirmation password must not be empty.";
	public static final String PASSWORD_CONFIRMATION_PATTERN="Password confirmation must have: uppercase letter, lowercase letter, special characters. between 8 and 20";
	
}

