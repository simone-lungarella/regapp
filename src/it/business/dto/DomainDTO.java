package it.business.dto;

/**
 * @author Simone Lungarella
 * 
 *         DTO per la gestione dell'entità DOMAIN, questo DTO aiuta a gestire
 *         l'entità sul DB salvata nella tabella DOMAINS e permette di
 *         trasferire le informazioni dallo strato di persistenza al business al
 *         front-end
 */

public class DomainDTO {
	private String domainName;
	private String registrant;
	private String admin;
	private boolean dnssec = false;

	public DomainDTO() {
		this("", null, null, false);
	}

	public DomainDTO(String domainName, String registrant, String admin, boolean dnssec) {
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
		} else if (hashCode() != other.hashCode())
			return false;
		return true;
	}

	@Override
	public String toString() {
		if (isDnssec())
			return "Nome di dominio: " + domainName + ", Registrant: " + registrant + ", Admin: " + admin
					+ ", DNSSEC: Sì";
		else
			return "Nome di dominio: " + domainName + ", Registrant: " + registrant + ", Admin: " + admin
					+ ", DNSSEC: No";
	}

}
