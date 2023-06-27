package com.arcesi.cliniquemedical.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "CONSULTATION", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODE_CONSULTATION", name = "CODE_CONSULTATION_SEQUENCE"),
		@UniqueConstraint(columnNames = "CODE_CONSULTATION_UNIQUE", name = "CODE_CONSULTATION_UNIQUE_SEQUENCE")})
public class ConsultationBean implements Serializable {
	
	 
	private static final long serialVersionUID = 1L;
	@SequenceGenerator(allocationSize = 1, sequenceName = "consultation_sequence", name = "consultation_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consultation_sequence")
	@Column(name = "CODE_CONSULTATION", nullable = false, unique = true)
	private Long codeConsultation;
	@Column(name="CODE_CONSULTATION_UNIQUE",unique = true,insertable = true)
	private String codeUniqueConsultation;
	@Column(name="DATE_CONSULTATION",insertable = true,nullable = false)
	private LocalDate dateConsultation;
	@Column(name="RAPPORT_CONSULTATION",insertable = true,nullable = false)
	private String rapportConsultation;
	@OneToOne(mappedBy = "consultationBean")
	private RendezVousBean rendezVousBean;
	
	@OneToMany(mappedBy = "consultationBean")
	private Set<PrescriptionBean> prescriptionBeans;
	
	@OneToMany(mappedBy = "consultationBean")
	private Set<PathoConstaterBean> pathoConstaterBeans;

}
