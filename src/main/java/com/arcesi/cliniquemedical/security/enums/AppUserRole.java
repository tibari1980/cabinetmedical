package com.arcesi.cliniquemedical.security.enums;

public enum AppUserRole {
	

	MEDECIN("MEDECIN"),
	PATIENT("PATIENT"),
	SECRETAIRE("SECRETAIRE"),
	ASSISTANTE_MEDICO_SOCIALE("AMS"), //Assistante-médico-social
	ADMINISTRATEUR("ADMIN");
	
	private final String id;
	
	private AppUserRole(final String id) {
	this.id=id;
	}
	
	public String getId() {
		return id;
	}

}
