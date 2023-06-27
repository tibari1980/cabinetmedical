package com.arcesi.cliniquemedical.security.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PasswordResetRequest {

	@NotBlank(message = "Password must not be empty.")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "Password must have: uppercase letter, lowercase letter, special characters. between 8 and 20")
	private String password;
	@NotBlank(message = "Password must not be empty.")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "Password must have: uppercase letter, lowercase letter, special characters. between 8 and 20")
	private String confirmPassword;
	@NotEmpty(message="Token must not be empty !!!")
	private String token;
}
