package com.arcesi.cliniquemedical.security.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "PASSWORDRESETTOKENS", uniqueConstraints = { 
		@UniqueConstraint(columnNames = "CODE_PASSWORD_RESET_TOKEN", name = "CODE_PASSWORD_RESET_TOKEN_unique")})
@Builder
public class PasswordResetToken {

	@SequenceGenerator(name = "passwordreset_sequence", allocationSize = 1, sequenceName = "passwordreset_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passwordreset_sequence")
	@Column(name = "CODE_PASSWORD_RESET_TOKEN", unique = true)
    private long id;
    @Column(nullable = false, unique = true)
    private String token;
    @OneToOne(targetEntity = UserBean.class, fetch = FetchType.EAGER)
    private UserBean userBean;
    @Column(nullable = false)
    private LocalDateTime expirationDate;
}
