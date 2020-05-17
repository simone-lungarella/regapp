package it.business.dto;

/**
 * @author Simone Lungarella
 * 
 * DTO per la gestione dell'entità DOMAIN, questo DTO aiuta a gestire l'entità sul DB salvata nella tabella DOMAINS e permette
 * di trasferire le informazioni dallo strato di persistenza al business al front-end
 * */


public class DomainDTO {
	private String domainName;
	private ContactDTO registrant;
	private ContactDTO admin;
	private boolean dnssec = false;
	
	
	
	public DomainDTO() {
		this("", null, null, false);
	}
	
	public DomainDTO(String domainName, ContactDTO registrant, ContactDTO admin, boolean dnssec) {
		setDomainName(domainName);
		setRegistrant(registrant);
		setAdmin(admin);
		setDnssec(dnssec);
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
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
	public boolean isDnssec() {
		return dnssec;
	}
	public void setDnssec(boolean dnssec) {
		this.dnssec = dnssec;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = domainName == null ? 0 : prime * result + domainName.hashCode();
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DomainDTO other = (DomainDTO) obj;
		if (hashCode() == 0) {
			if (other.hashCode() != 0)
				return false;
		} else if (hashCode()!= other.hashCode())
			return false;
		return true;
	}
	@Override
	public String toString() {
		if(isDnssec())
			return  "Nome di dominio: " + domainName + ", Registrant: " + registrant + ", Admin: " + admin + ", DNSSEC: Sì";
		else
			return "Nome di dominio: " + domainName + ", Registrant: " + registrant + ", Admin: " + admin + ", DNSSEC: No";
	}
	
}
