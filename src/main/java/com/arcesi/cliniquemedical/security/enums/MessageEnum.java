package com.arcesi.cliniquemedical.security.enums;

public enum MessageEnum {
	

	
	CREATE_ACCOUNT_SUBJECT("Creation de votre compte LA SANTE AVANT TOUT / Create your HEALTH BEFORE ALL account [1/2]"),
	CONFIRMATION_VALIDATION_ACCOUNT("Confirmation de validation de votre Email / Confirmation of validation of your Email"),
	REINITIALISATION_PASSWORD("Réinitialisation de votre mot de passe / Réinitialisation de votre mot de passe");
	
	private final String id;
	
	private MessageEnum(final String id) {
	this.id=id;
	}
	
	public String getId() {
		return id;
	}

}
