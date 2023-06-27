package com.arcesi.cliniquemedical.security.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.arcesi.cliniquemedical.security.enums.TokenType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TOKENS")
public class TokenBean {

	@SequenceGenerator(name = "token_sequence", allocationSize = 1, sequenceName = "token_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_sequence")
	@Column(name = "CODE_USER", unique = true)
	public Long idUser;

	@Column(unique = true)
	public String token;

	@Builder.Default
	@Enumerated(EnumType.STRING)
	public TokenType tokenType = TokenType.BEARER;

	@Column(name = "REVOKED", insertable = true)
	public boolean revoked;

	@Column(name = "EXPIRED_AT", insertable = true)
	public boolean expired;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "CODE_USER_fk", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	// @JsonIgnore
	public UserBean userBean;

}
