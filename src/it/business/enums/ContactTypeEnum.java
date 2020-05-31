package it.business.enums;

public enum ContactTypeEnum {
	REGISTRAR("Registrar"),
	REGISTRANT("Registrant"),
	ADMIN("Admin"),
	TECH("Tech");

	private String label;
	
	private ContactTypeEnum(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
