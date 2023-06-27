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
@Table(name = "PATHOCONSTATER", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODE_CONSTATER", name = "CODE_CONSTATER_SEQUENCE"),
		@UniqueConstraint(columnNames = "CODE_CONSTATER_UNIQUE", name = "CODE_CONSTATER_UNIQUE_SEQUENCE")})
public class PathoConstaterBean implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@SequenceGenerator(allocationSize = 1, sequenceName = "constater_sequence", name = "constater_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "constater_sequence")
	@Column(name = "CODE_CONSTATER", nullable = false, unique = true)
    private Long codePathologieConstater;
	@Column(name="CODE_CONSTATER_UNIQUE",insertable = true,unique = true)
	private String codePathologieUnique;
	@Column(name="DESCRIPTION_DETAILLEES",insertable = true,nullable = true)
	private String descriptionDetaillees;
	@ManyToOne
	@JoinColumn(name="CODE_CONSULTATION")
	private ConsultationBean consultationBean;
	@ManyToOne
	@JoinColumn(name="CODE_PATHOLOGIE")
	private PathologieBean pathologieBean;
}
