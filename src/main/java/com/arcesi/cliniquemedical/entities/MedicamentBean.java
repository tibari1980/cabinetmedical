package com.arcesi.cliniquemedical.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
@Table(name = "MEDICAMENTS", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODE_MEDICAMENT", name = "CODE_MEDICAMENT_SEQUENCE"),
		@UniqueConstraint(columnNames = "CODE_MEDICAMENT_UNIQUE", name = "CODE_MEDICAMENT_UNIQUE_SEQUENCE") })
public class MedicamentBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SequenceGenerator(allocationSize = 1, sequenceName = "medicament_sequence", name = "medicament_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicament_sequence")
	@Column(name = "CODE_MEDICAMENT", nullable = false, unique = true)
	private Long codeMedicament;
	@Column(name = "CODE_MEDICAMENT_UNIQUE", unique = true, insertable = true)
	private String codeUniqueMedicament;
	@Column(name = "DESIGNATION_MEDICAMENT", insertable = true, nullable = true)
	private String designationMedicament;
	@Column(name = "DESCRIPTION_MEDICAMENT", insertable = true, nullable = true)
	private String descriptionMedicament;

	@OneToMany(mappedBy = "medicamentBean")
	private Set<PrescriptionBean> prescriptionBeans;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "medicaments_pathologies", joinColumns = {
			@JoinColumn(name = "medicament_id") }, inverseJoinColumns = { @JoinColumn(name = "pathologie_id") })
	@Builder.Default
	private Set<PathologieBean> pathologieBeans = new HashSet<>();

	
	
	public void addPathologie(PathologieBean patho) {
		this.pathologieBeans.add(patho);
		patho.getMedicamentBeans().add(this);
	}

	public void removePathologie(long pathoId) {
		PathologieBean patho = this.pathologieBeans.stream().filter(t -> t.getCodePathologie() == pathoId).findFirst()
				.orElse(null);
		if (patho != null) {
			this.pathologieBeans.remove(patho);
			patho.getMedicamentBeans().remove(this);
		}
	}
}
