package it.business.dto;

/**
 * DTO per la gestione dell'entity <em> contract </em>, questo DTO aiuta a
 * gestire la relazione tra contatti e domini, sul DB e salvata nella tabella
 * CONTRACTS e permette di trasferire le informazioni dallo strato di
 * persistenza al business al front-end.
 * 
 * @author Simone Lungarella
 */
public class ContractDTO {

	/**
	 * Identificativo del Registrar vincolato dal contratto.
	 */
	private String registrar;

	/**
	 * Identificativo del Registrant vincolato dal contratto.
	 */
	private String registrant;

	/**
	 * Identificativo Admin vincolato dal contratto.
	 */
	private String admin;

	/**
	 * Nome del dominio gestito con il contratto.
	 */
	private String domain;

	/**
	 * Costrutto vuoto.
	 */
	public ContractDTO() {
		this(null, null, null, null);
	}

	/**
	 * Costruttore completo.
	 * 
	 * @param registrar  Identificativo del Registrar.
	 * @param registrant Identificativo del Registrant.
	 * @param admin      Identificativo dell' Admin.
	 * @param domain     Nome del dominio.
	 */
	public ContractDTO(String registrar, String registrant, String admin, String domain) {
		setRegistrar(registrar);
		setRegistrant(registrant);
		setAdmin(admin);
		setDomain(domain);
	}

	/**
	 * Restituisce l'identificativo del Registrar.
	 * 
	 * @return Identificativo del Registrar.
	 */
	public String getRegistrar() {
		return registrar;
	}

	/**
	 * Imposta l'identificativo del Registrar.
	 * 
	 * @param registrar Identificativo del Registrar.
	 */
	public void setRegistrar(String registrar) {
		this.registrar = registrar;
	}

	/**
	 * Restituisce l'identificativo del Registrant.
	 * 
	 * @return Identificativo del Registrant.
	 */
	public String getRegistrant() {
		return registrant;
	}

	/**
	 * Imposta l'identificativo del Registrant.
	 * 
	 * @param registrant Idetificativo del Registrant.
	 */
	public void setRegistrant(String registrant) {
		this.registrant = registrant;
	}

	/**
	 * Restituisce l'identificativo dell'Admin.
	 * 
	 * @return Identificativo Admin.
	 */
	public String getAdmin() {
		return admin;
	}

	/**
	 * Imposta l'identificativo dell'Admin.
	 * 
	 * @param admin Identificativo admin.
	 */
	public void setAdmin(String admin) {
		this.admin = admin;
	}

	/**
	 * Restituisce il nome di dominio gestito dal contratto.
	 * 
	 * @return Nome del dominio.
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * Imposta il nome del dominio.
	 * 
	 * @param domain Nome dominio da impostare.
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

}
