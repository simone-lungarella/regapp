package it.business.dto;

import it.business.enums.ContactTypeEnum;

/**
 * @author Simone Lungarella
 * 
 * DTO per la gestione dell'entità CONTACT, questo DTO aiuta a gestire l'entità sul DB salvata nella tabella CONTACTS e permette
 * di trasferire le informazioni dallo strato di persistenza al business al front-end
 * */

public class ContactDTO {
	private String contactId;
	private String firstName;
	private String lastName;
	private ContactTypeEnum contactType;

	public ContactDTO() {
		this("", "", "", null);
	}

	public ContactDTO(String contactId, String firstName, String lastName, ContactTypeEnum contactType) {
		setContactId(contactId);
		setFirstName(firstName);
		setLastName(lastName);
		setContactType(contactType);
	}
	
	public ContactDTO(ContactDTO contactCopy) {
		setContactId(contactCopy.getContactId());
		setFirstName(contactCopy.getFirstName());
		setLastName(contactCopy.getLastName());
		setContactType(contactCopy.getContactType());
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public ContactTypeEnum getContactType() {
		return contactType;
	}

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
