package com.arcesi.cliniquemedical.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
@Table(name = "DOSSIERMEDICAL", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODE_DOSSIERMEDICAL", name = "CODE_DOSSIERMEDICAL_SEQUENCE"),
		@UniqueConstraint(columnNames = "CODE_DOSSIERMEDICAL_UNIQUE", name = "CODE_DOSSIERMEDICAL_UNIQUE_SEQUENCE")})
public class DossierMedicalBean implements Serializable{

	private static final long serialVersionUID = 1L;
	@SequenceGenerator(allocationSize = 1, sequenceName = "dossiermedical_sequence", name = "dossiermedical_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dossiermedical_sequence")
	@Column(name = "CODE_DOSSIERMEDICAL", nullable = false, unique = true)
	private Long codeDossierMedical;
	@Column(name = "CODE_DOSSIERMEDICAL_UNIQUE", length = 40, unique = true, nullable = false)
	private String uidDossierMedical;
	@Column(name="DIAGNOSTIC",insertable = true)
	private String diagnostic;
	@Column(name="TRAITEMENT",insertable = true)
	private String traitement;
	@Column(name="EVOLUTION")
	private String evolution;
}
