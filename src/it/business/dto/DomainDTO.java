package it.business.dto;

/**
 * DTO per la gestione dell'entity <em> domain </em>, questo DTO aiuta a gestire
 * l'entity sul DB salvata nella tabella DOMAINS e permette di trasferire le
 * informazioni dallo strato di persistenza al business al front-end.
 * 
 * @author Simone Lungarella
 */
public class DomainDTO {

	/**
	 * Nome del dominio.
	 */
	private String domainName;

	/**
	 * Identificativo del Registrant che ha i diritti sul dominio.
	 */
	private String registrant;

	/**
	 * Identificativo Admin che gestisce il dominio.
	 */
	private String admin;

	/**
	 * Flag associato all'estensione di sicurezza del dominio.
	 */
	private boolean dnssec = false;

	/**
	 * Costruttore vuoto.
	 */
	public DomainDTO() {
		this("", null, null, false);
	}

	/**
	 * Costruttore completo.
	 * 
	 * @param domainName Nome del dominio.
	 * @param registrant Identificativo del Registrant.
	 * @param admin      Identificativo dell'Admin.
	 * @param dnssec     Flag associato all'estensione di sicurezza.
	 */
	public DomainDTO(String domainName, String registrant, String admin, boolean dnssec) {
		setDomainName(domainName);
		setRegistrant(registrant);
		setAdmin(admin);
		setDnssec(dnssec);
	}

	/**
	 * Restituisce il nome del dominio.
	 * 
	 * @return Nome dominio.
	 */
	public String getDomainName() {
		return domainName;
	}

	/**
	 * Imposta il nome del dominio.
	 * 
	 * @param domainName Nome dominio da impostare.
	 */
	public void setDomainName(String domainName) {
		this.domainName = domainName;
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
	 * @param registrant Identificativo del Registrant da impostare.
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
	 * @param admin Identificativo dell'amministratore del dominio.
	 */
	public void setAdmin(String admin) {
		this.admin = admin;
	}

	/**
	 * Restituisce <code> true </code> se il dominio ha l'estensione di sicurezza, <code> false </code> altrimenti.
	 * 
	 * @return Flag associato alla sicurezza DNSSEC del dominio.
	 */
	public boolean isDnssec() {
		return dnssec;
	}

	/**
	 * Imposta il flag associato all'estensione di sicurezza del dominio.
	 * 
	 * @param dnssec Flag da impostare.
	 */
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
					+ ", DNSSEC: Si";
		else
			return "Nome di dominio: " + domainName + ", Registrant: " + registrant + ", Admin: " + admin
					+ ", DNSSEC: No";
	}

}
