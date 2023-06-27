package com.arcesi.cliniquemedical.security.enums;

public enum PathologieEnums {
	

	DIABETE("Diabète"),
	DEPRESSION("Dépression"),
	ENCEPHALITE("Encéphalite"),
	FIBRILLATION("Fibrillation"),
	CARDIOPATHIE("Cardiopathie"),
	CATARACTE("Cataracte"),
	HEPATITE("Hépatite");
	
	private final String id;
	
	private PathologieEnums(final String id) {
	this.id=id;
	}
	
	public String getId() {
		return id;
	}

}
