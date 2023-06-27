package com.arcesi.cliniquemedical.security.dtos;

import com.arcesi.cliniquemedical.security.enums.MessageErreursDTOConstants;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

	@NotBlank(message = MessageErreursDTOConstants.FIRSTNAME_NOT_EMPTY)
	@Size(min = 4, max = 40, message = MessageErreursDTOConstants.FIRST_NAME_SIZE_MIN_MAX)
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = MessageErreursDTOConstants.FIRST_NAME_PATTERN)
	private String firstname;
	@NotBlank(message = MessageErreursDTOConstants.LAST_NAME_NOT_BLANK)
	@Size(min = 4, max = 40, message = MessageErreursDTOConstants.LAST_NAME_SIZE_MIN_MAX)
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = MessageErreursDTOConstants.LAST_NAME_PATTERN)
	private String lastname;
	
	@Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$", message = MessageErreursDTOConstants.EMAIL_PATTERN)
	private String email;
	
	@NotBlank(message = MessageErreursDTOConstants.PASSWORD_NOT_BLANK)
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = MessageErreursDTOConstants.PASSWORD_PATTERN)
	private String password;
	
	@NotBlank(message = MessageErreursDTOConstants.PASSWORD_CONFIRMATION_NOT_BLANK)
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = MessageErreursDTOConstants.PASSWORD_CONFIRMATION_PATTERN)
	private String confirmerPassword;

}
