package com.arcesi.cliniquemedical.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Embeddable
@Builder

@Entity
@Table(name = "ASSISTANTEMEDICOSOCIALE", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODE_ASSISTANTEMEDICOSOCIALE", name = "CODE_ASSISTANTEMEDICOSOCIALE_SEQUENCE"),
		@UniqueConstraint(columnNames = "CODE_ASSISTANTEMEDICOSOCIALE_UNIQUE", name = "CODE_ASSISTANTEMEDICOSOCIALE_UNIQUE_SEQUENCE"),
		@UniqueConstraint(columnNames = "EMAIL_ASSISTANTEMEDICOSOCIALE",name="EMAIL_ASSISTANTEMEDICOSOCIALE_SEQUENCE")})
public class AssistantMedicoSocialeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SequenceGenerator(allocationSize = 1, sequenceName = "ASSISTANTEMEDICOSOCIALE_sequence", name = "ASSISTANTEMEDICOSOCIALE_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ASSISTANTEMEDICOSOCIALE_sequence")
	@Column(name = "CODE_ASSISTANTEMEDICOSOCIALE", nullable = false, unique = true)
	private Long code;
	@Column(name="CODE_ASSISTANTEMEDICOSOCIALE_UNIQUE",insertable = true,unique = true,nullable = false)
	private String uidAsm;
	@Column(name="FIRST_NAME",insertable = true,nullable = false)
	private String firstName;
	@Column(name="LAST_NAME",insertable = true,nullable = false)
	private String lastName;
	@Column(name="DATE_NAISSANCE",insertable = true,nullable = false)
	private LocalDate dateNaissance;
	@Column(name="EMAIL_ASSISTANTEMEDICOSOCIALE",unique = true,nullable = false)
	private String email;
	@Column(name="TELEPHONE_PORTABLE",insertable = true,nullable = false)
	private String telephonePortable;
	@Column(name="TELEPHONE_FIX",insertable = true,nullable = false)
	private String telephoneFix;
	@Column(name="PHOTO",insertable = true,nullable = true)
	private String photo;
	@Embedded
	private AdresseBean adresseBean;

}
