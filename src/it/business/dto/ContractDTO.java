package it.business.dto;


/**
 * @author Simone Lungarella
 * 
 * DTO per la gestione dell'entità CONTRACT, questo DTO aiuta a gestire la relazione tra contatti e domini, sul DB è salvata nella tabella CONTRACTS 
 * e permette di trasferire le informazioni dallo strato di persistenza al business al front-end
 * */

public class ContractDTO {
	private ContactDTO registrar;
	private ContactDTO registrant;
	private ContactDTO admin;
	private DomainDTO domainName;
	
	public ContractDTO() {
		this(null, null, null, null);
	}
	
	public ContractDTO(ContactDTO registrar, ContactDTO registrant, ContactDTO admin, DomainDTO domainName) {
		setRegistrar(registrar);
		setRegistrant(registrant);
		setAdmin(admin);
		setDomainName(domainName);
	}
	
	public ContactDTO getRegistrar() {
		return registrar;
	}
	public void setRegistrar(ContactDTO registrar) {
		this.registrar = registrar;
	}
	public ContactDTO getRegistrant() {
		return registrant;
	}
	public void setRegistrant(ContactDTO registrant) {
		this.registrant = registrant;
	}
	public ContactDTO getAdmin() {
		return admin;
	}
	public void setAdmin(ContactDTO admin) {
		this.admin = admin;
	}

	public DomainDTO getDomainName() {
		return domainName;
	}

	public void setDomainName(DomainDTO domainName) {
		this.domainName = domainName;
	}
	
}
