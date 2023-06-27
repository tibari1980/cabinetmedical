package com.arcesi.cliniquemedical.enums;

public enum SexEnumeration {
	/**
	 * HOMME
	 */
	HOMME("HOMME"),
	/**
	 * FEMME
	 */
	FEMME("FEMME");

	private final String id;

	private SexEnumeration(final String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	@Override
	public String toString() {

		return id;
	}

	/**
	 * returning a string containg the values of class members
	 * 
	 * @param includeClassName
	 * @return
	 */
	protected String toString(boolean includeClassName) {
		StringBuffer str = new StringBuffer();
		if (includeClassName) {
			str.append("Sex du client, ");
		}
		str.append("HOMME='" + SexEnumeration.HOMME + "',");
		str.append("HOMME='" + SexEnumeration.FEMME + "',");
		return (str.toString());
	}
}
