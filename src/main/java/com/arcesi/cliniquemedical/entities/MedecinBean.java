package com.arcesi.cliniquemedical.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
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
@Table(name = "MEDECIN", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODE_MEDECIN", name = "CODE_MEDECIN_SEQUENCE"),
		@UniqueConstraint(columnNames = "CODE_MEDECIN_UNIQUE", name = "CODE_MEDECIN_UNIQUE_SEQUENCE"),
		@UniqueConstraint(columnNames = "NA_MEDECIN",name="NA_MEDECIN_SEQUENCE"),
		@UniqueConstraint(columnNames = "EMAIL_MEDECIN",name="EMAIL_MEDECIN_SEQUENCE")})
public class MedecinBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@SequenceGenerator(allocationSize = 1, sequenceName = "medecin_sequence", name = "medecin_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medecin_sequence")
	@Column(name = "CODE_MEDECIN", nullable = false, unique = true)
	private Long codeMedecin;
	@Column(name="CODE_MEDECIN_UNIQUE",insertable = true,unique = true,nullable = false)
	private String codeMedecinUnique;
	@Column(name="FIRST_NAME",insertable = true,nullable = false)
	private String firstName;
	@Column(name="LAST_NAME",insertable = true,nullable = false)
	private String lastName;
	@Column(name="DATE_NAISSANCE",insertable = true,nullable = false)
	private LocalDate dateNaissance;
	@Column(name="EMAIL_MEDECIN",unique = true,nullable = false)
	private String emailMedecin;
	@Column(name="SPECIALITE_MEDECIN",insertable = true,nullable = false)
	private String specialiteMedecin;
	@Column(name="NA_MEDECIN",insertable = true,nullable = false,unique = true)
	private String numeroAgrement;
	@Column(name="TELEPHONE_PORTABLE",insertable = true,nullable = false)
	private String telephonePortable;
	@Column(name="TELEPHONE_FIX",insertable = true,nullable = false)
	private String telephoneFix;
	@Column(name="PHOTO",insertable = true,nullable = true)
	private String photoMedecin;
	@OneToMany(mappedBy = "medecinBean")
	private Collection<RendezVousBean>  rendezVousBeans;
	
}
