package com.arcesi.cliniquemedical.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@Embeddable
@Builder

@Entity
@Table(name = "RENDEZVOUS", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODE_RENDEZVOUS", name = "CODE_RENDEZVOUS_SEQUENCE"),
		@UniqueConstraint(columnNames = "CODE_RENDEZVOUS_UNIQUE", name = "CODE_RENDEZVOUS_UNIQUE_SEQUENCE")})
public class RendezVousBean  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SequenceGenerator(allocationSize = 1, sequenceName = "rendezvous_sequence", name = "rendezvous_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rendezvous_sequence")
	@Column(name = "CODE_RENDEZVOUS", nullable = false, unique = true)
	private Long codeRendezvous;
	@Column(name="CODE_RENDEZVOUS_UNIQUE",insertable = true,unique = true,nullable = false)
	private String codeRendezVousUnique;
	@Column(name="DATE_RENDEZVOUS")
	private LocalDate dateRendezVous;
	
	@ManyToOne
	private PatientBean patientBean;
	@ManyToOne
	private MedecinBean medecinBean;
	@OneToOne
	private ConsultationBean consultationBean;
}
