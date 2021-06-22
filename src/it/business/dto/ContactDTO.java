package it.business.dto;

import it.business.enums.ContactTypeEnum;

/**
 * DTO per la gestione dell'entit� CONTACT, questo DTO aiuta a gestire le entity
 * sul DB salvata nella tabella CONTACTS e permette di trasferire le
 * informazioni dallo strato di persistenza al business al front-end.
 * 
 * @author Simone Lungarella
 */
public class ContactDTO {

	/**
	 * Identificativo contatto.
	 */
	private String contactId;

	/**
	 * Nome contatto.
	 */
	private String firstName;

	/**
	 * Cognome contatto.
	 */
	private String lastName;

	/**
	 * Tipologia del contatto.
	 */
	private ContactTypeEnum contactType;

	/**
	 * Costruttore vuoto.
	 */
	public ContactDTO() {
		this("", "", "", null);
	}

	/**
	 * Costruttore completo.
	 * 
	 * @param contactId   Identificativo del contatto.
	 * @param firstName   Nome del contatto.
	 * @param lastName    Cognome del contatto.
	 * @param contactType Tipologia del contatto.
	 */
	public ContactDTO(String contactId, String firstName, String lastName, ContactTypeEnum contactType) {
		setContactId(contactId);
		setFirstName(firstName);
		setLastName(lastName);
		setContactType(contactType);
	}

	/**
	 * Costruttore copia.
	 * 
	 * @param contactCopy Contatto da copiare.
	 */
	public ContactDTO(ContactDTO contactCopy) {
		setContactId(contactCopy.getContactId());
		setFirstName(contactCopy.getFirstName());
		setLastName(contactCopy.getLastName());
		setContactType(contactCopy.getContactType());
	}

	/**
	 * Restituisce l'identificativo del contatto.
	 * 
	 * @return Identificativo del contatto.
	 */
	public String getContactId() {
		return contactId;
	}

	/**
	 * Imposta l'identificativo del contatto.
	 * 
	 * @param contactId Identificativo del contatto da impostare.
	 */
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	/**
	 * Restituisce il nome del contatto.
	 * 
	 * @return Nome del contatto.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Imposta il nome del contatto.
	 * 
	 * @param firstName Nome del contatto da impostare.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Restituisce il cognome del contatto.
	 * 
	 * @return Cognome contatto.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Imposta il cognome del contatto.
	 * 
	 * @param lastName Cognome contatto da impostare.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Restituisce la tipologia del contatto.
	 * 
	 * @return Tipologia contatto.
	 */
	public ContactTypeEnum getContactType() {
		return contactType;
	}

	/**
	 * Imposta la tipologia del contatto.
	 * 
	 * @param contactType Tipologia contatto da impostare.
	 */
	public void setContactType(ContactTypeEnum contactType) {
		this.contactType = contactType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (contactId == null) ? 0 : prime * result + contactId.hashCode();
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
		ContactDTO other = (ContactDTO) obj;
		if (hashCode() == 0) {
			if (other.hashCode() != 0)
				return false;
		} else if (this.hashCode() != other.hashCode())
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Codice fiscale: " + contactId + ", Nome: " + firstName + ", Cognome: " + lastName
				+ ", Tipo di contatto: " + contactType.toString();
	}

}
