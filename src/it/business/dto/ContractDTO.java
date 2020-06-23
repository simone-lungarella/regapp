package it.business.dto;

/**
 * @author Simone Lungarella
 * 
 *         DTO per la gestione dell'entità CONTRACT, questo DTO aiuta a gestire
 *         la relazione tra contatti e domini, sul DB è salvata nella tabella
 *         CONTRACTS e permette di trasferire le informazioni dallo strato di
 *         persistenza al business al front-end
 */

public class ContractDTO {
	private String registrar;
	private String registrant;
	private String admin;
	private String domain;

	public ContractDTO() {
		this(null, null, null, null);
	}

	public ContractDTO(String registrar, String registrant, String admin, String domain) {
		setRegistrar(registrar);
		setRegistrant(registrant);
		setAdmin(admin);
		setDomain(domain);
	}

	public String getRegistrar() {
		return registrar;
	}

	public void setRegistrar(String registrar) {
		this.registrar = registrar;
	}

	public String getRegistrant() {
		return registrant;
	}

	public void setRegistrant(String registrant) {
		this.registrant = registrant;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

}
