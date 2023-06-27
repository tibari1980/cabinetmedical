package com.arcesi.cliniquemedical.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

import com.arcesi.cliniquemedical.enums.SexEnumeration;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "PATIENT", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODE_PATIENT", name = "CODE_PATIENT_SEQUENCE"),
		@UniqueConstraint(columnNames = "CODE_PATIENT_UNIQUE", name = "CODE_PATIENT_UNIQUE_SEQUENCE"),
		@UniqueConstraint(columnNames = "NSS_PATIENT",name="NSS_PATIENT_SEQUENCE"),
		@UniqueConstraint(columnNames = "EMAIL_PATIENT",name="EMAIL_PATIENT_SEQUENCE")})

public class PatientBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@SequenceGenerator(allocationSize = 1, sequenceName = "patient_sequence", name = "patient_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_sequence")
	@Column(name = "CODE_PATIENT", nullable = false, unique = true)
	private Long codePatient;
	@Column(name = "CODE_PATIENT_UNIQUE", length = 40, unique = true, nullable = false)
	private String uidPatient;
	@Column(name = "SEX_PATIENT", nullable = false, insertable = true, updatable = true)
	@Enumerated(EnumType.STRING)
	private SexEnumeration sex;
	@Column(name = "NOM_PATIENT", length = 40, insertable = true, updatable = true, nullable = false)
	private String nomPatient;
	@Column(name = "PRENOM_PATIENT", length = 60, insertable = true, updatable = true, nullable = false)
	private String prenomPatient;
	@Column(name = "DATENAISSANCE_PATIENT", nullable = false, insertable = true, updatable = true)
	private LocalDate dateNaissance;
	@Column(name = "AGE_PATIENT")
	private Integer agePatient;
	@Column(name = "EMAIL_PATIENT", length = 100, nullable = false, unique = true, insertable = true, updatable = true)
	private String emailPatient;
	@Column(name = "TELEPHONE_PATIENT", insertable = true, nullable = false)
	private String telephonePatient;
	@Column(name = "CIN_PATIENT", insertable = true, nullable = true)
	private String cinPatient;
	@Column(name = "NSS_PATIENT", insertable = true, unique = true, nullable = false)
	private String nssPatient;
	@Column(name = "PAYS_NAISSANCE", insertable = true, updatable = true, nullable = false)
	private String paysNaissance;
	@Column(name = "VILLE_NAISSANCE", insertable = true, updatable = true, nullable = false)
	private String villeNaissance;
	@Column(name = "DEPARTEMENT_NAISSANCE", length = 3)
	private String departementNaissance;
	@Column(name="PHOTO_PATIENT")
	private String photoPatient;
	@Embedded
	private AdresseBean adresseBean;
	
	@OneToMany(mappedBy = "patientBean",fetch = FetchType.LAZY)
	private Collection<RendezVousBean> rendezVousBeans;

}
