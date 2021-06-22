package it.business.enums;

/**
 * Enum che identifica tutte le tipologie dei contatti.
 * 
 * @author Simone Lungarella
 */
public enum ContactTypeEnum {

	/**
	 * Un Registrar gestisce per conto di un Registrant un dominio.
	 */
	REGISTRAR("Registrar"),

	/**
	 * Un Registrant detiene i diritti sul dominio secondo le regole stabilite in un @see {@link it.business.dto.ContractDTO}.
	 */
	REGISTRANT("Registrant"),

	/**
	 * Un Admin amministra per conto del Registrar il dominio.
	 */
	ADMIN("Admin"),

	/**
	 * Un Tech è un subordinato di un Admin e risponde a lui per l'esecuzione di attività di amministrazione.
	 */
	TECH("Tech");

	/**
	 * Label che descrive il tipo di contatto.
	 */
	private String label;
	
	/**
	 * Restituisce l'Enum identificata dalla <code> label </code>.
	 * 
	 * @param label Identificativo dell'Enum.
	 */
	private ContactTypeEnum(String label) {
		this.label = label;
	}
	
	/**
	 * Restituisce la descrizione dell'Enum.
	 * 
	 * @return Descrizione Enum.
	 */
	public String getLabel() {
		return label;
	}
}
