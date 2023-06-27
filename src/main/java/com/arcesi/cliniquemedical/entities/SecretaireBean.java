package com.arcesi.cliniquemedical.entities;

import java.io.Serializable;
import java.time.LocalDate;

import com.arcesi.cliniquemedical.enums.SexEnumeration;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "SECRETAIRE", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODE_SECRETAIRE", name = "CODE_SECRETAIRE_SEQUENCE"),
		@UniqueConstraint(columnNames = "CODE_SECRETAIRE_UNIQUE", name = "CODE_SECRETAIRE_UNIQUE_SEQUENCE"),
		@UniqueConstraint(columnNames = "EMAIL_SECRETAIRE",name="EMAIL_SECRETAIRE_SEQUENCE")})
public class SecretaireBean implements Serializable{

	private static final long serialVersionUID = 1L;
	@SequenceGenerator(allocationSize = 1, sequenceName = "Secretaire_sequence", name = "Secretaire_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Secretaire_sequence")
	@Column(name = "CODE_SECRETAIRE", nullable = false, unique = true)
	private Long codeSecretaire;
	@Column(name = "CODE_SECRETAIRE_UNIQUE", length = 40, unique = true, nullable = false)
	private String uidSecretaire;
	@Column(name = "SEX_SECRETAIRE", nullable = false, insertable = true, updatable = true)
	@Enumerated(EnumType.STRING)
	private SexEnumeration sex;
	@Column(name = "NOM_SECRETAIRE", length = 40, insertable = true, updatable = true, nullable = false)
	private String nomSecretaire;
	@Column(name = "PRENOM_SECRETAIRE", length = 60, insertable = true, updatable = true, nullable = false)
	private String prenomSecretaire;
	@Column(name = "DATENAISSANCE_SECRETAIRE", nullable = false, insertable = true, updatable = true)
	private LocalDate dateNaissance;
	@Column(name = "AGE_SECRETAIRE")
	private Integer ageSecretaire;
	@Column(name = "EMAIL_SECRETAIRE", length = 100, nullable = false, unique = true, insertable = true, updatable = true)
	private String emailSecretaire;
	@Column(name = "TELEPHONE_SECRETAIRE", insertable = true, nullable = false)
	private String telephoneSecretaire;
	@Column(name = "CIN_SECRETAIRE", insertable = true, nullable = true)
	private String cinSecretaire;
	@Column(name = "NSS_SECRETAIRE", insertable = true, unique = true, nullable = false)
	private String nssSecretaire;
	@Column(name = "PAYS_NAISSANCE", insertable = true, updatable = true, nullable = false)
	private String paysNaissance;
	@Column(name = "VILLE_NAISSANCE", insertable = true, updatable = true, nullable = false)
	private String villeNaissance;
	@Column(name = "DEPARTEMENT_NAISSANCE", length = 3)
	private String departementNaissance;
	@Column(name="PHOTO_SECRETAIRE")
	private String photoSecretaire;
	@Embedded
	private AdresseBean adresseBean;
	
	
}
