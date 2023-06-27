package com.arcesi.cliniquemedical.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "PRESCRIPTION", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODE_PRESCRIPTION", name = "CODE_PRESCRIPTION_SEQUENCE"),
		@UniqueConstraint(columnNames = "CODE_PRESCRIPTION_UNIQUE", name = "CODE_PRESCRIPTION_UNIQUE_SEQUENCE")})
public class PrescriptionBean implements Serializable {

	 
	private static final long serialVersionUID = 1L;
	@SequenceGenerator(allocationSize = 1, sequenceName = "prescription_sequence", name = "prescription_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prescription_sequence")
	@Column(name = "CODE_PRESCRIPTION", nullable = false, unique = true)
	private Long codePrescription;
	@Column(name="CODE_PRESCRIPTION_UNIQUE",unique = true,insertable = true,nullable = false)
	private String codePrescriptionUnique;
	@Column(name="NOMBRE_UNITE_PAR_JOUR",insertable = true)
	private int nombreUniteParJour;
	@Column(name="DESCRIPTION",insertable = true,nullable = true)
	private String description;
	@ManyToOne
	@JoinColumn(name="CODE_CONSULTATION")
	private ConsultationBean consultationBean;
	@ManyToOne
	@JoinColumn(name="CODE_MEDICAMENT")
	private MedicamentBean medicamentBean;

}
