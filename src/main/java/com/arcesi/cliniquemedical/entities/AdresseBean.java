package com.arcesi.cliniquemedical.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
public class AdresseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "NUMERO_RUE", nullable = false, updatable = true, insertable = true)
	private Integer numeroRue;
	@Column(name = "NOM_RUE", nullable = false, updatable = true, insertable = true, length = 40)
	private String nomRue;
	@Column(name = "CODE_POSTALE", insertable = true, updatable = true, length = 5)
	private String codePostale;
	@Column(name = "VILLE", length = 40)
	private String ville;
	@Column(name = "PAYS", insertable = true, updatable = true, length = 40)
	private String pays;

}
