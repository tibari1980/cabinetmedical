 package com.arcesi.cliniquemedical.security.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
	   
	SECRETAIRE_READ("secretaire:read"),
	SECRETAIRE_UPDATE("secretaire:update"),
	SECRETAIRE_CREATE("secretaire:create"),
	SECRETAIRE_DELETE("secretaire:delete"),
	   
	ADMIN_READ("admin:read"),
	ADMIN_UPDATE("admin:update"),
	ADMIN_CREATE("admin:create"),
	ADMIN_DELETE("admin:delete"),
	    
	
	PATIENT_READ("patient:read"),
	PATIENT_UPDATE("patient:update"),
	PATIENT_CREATE("patient:create"),
	PATIENT_DELETE("patient:delete"),
	    
	   
	AMS_READ("assitant-medico-social:read"),
	AMS_UPDATE("assitant-medico-social:update"),
	AMS_CREATE("assitant-medico-social:create"),
	AMS_DELETE("assitant-medico-social:delete"),
	
	MEDECIN_READ("medecin:read"),
	MEDECIN_UPDATE("medecin:update"),
	MEDECIN_CREATE("medecin:create"),
	MEDECIN_DELETE("medecin:delete");
	
	    @Getter
	    private final String permission;
	    

}
