package com.arcesi.cliniquemedical.security.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "PRIVILEGES", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODE_PRIVILEGE", name = "CODE_PRIVILEGE_SEQUENCE")})

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PrivilegeBean {
	
	
	@SequenceGenerator(allocationSize = 1, sequenceName = "privilege_sequence", name = "privilge_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "privilege_sequence")
	@Column(name = "CODE_PRIVILEGE", nullable = false, unique = true)
	 
	private Long id;
    @Column(name="NAME_PRIVILEGE",nullable = false,insertable = true)
    private String name;

	

}
