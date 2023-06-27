package com.arcesi.cliniquemedical.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
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
@Table(name = "PATHOLOGIE", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODE_PATHOLOGIE", name = "CODE_PATHOLOGIE_SEQUENCE"),
		@UniqueConstraint(columnNames = "CODE_PATHOLOGIE_UNIQUE", name = "CODE_PATHOLOGIE_UNIQUE_SEQUENCE") })
public class PathologieBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@SequenceGenerator(allocationSize = 1, sequenceName = "pathologie_sequence", name = "pathologie_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pathologie_sequence")
	@Column(name = "CODE_PATHOLOGIE", nullable = false, unique = true)
	private Long codePathologie;
	@Column(name = "CODE_PATHOLOGIE_UNIQUE", unique = true, insertable = true)
	private String codeUniquePathologie;
	@Column(name = "NOM_PATHOLOGIE")
	private String nomPathologie;
	@Column(name="DESCRIPTION_PATHOLOGIE",insertable = true,updatable = true)
	@Lob
	private String descriptionPathologie;
	@OneToMany(mappedBy = "pathologieBean")
	private Set<PathoConstaterBean> pathoConstaterBeans;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.MERGE }, mappedBy = "pathologieBeans")
	@JsonIgnore
	@Builder.Default
	private Set<MedicamentBean> medicamentBeans = new HashSet<>();
}
