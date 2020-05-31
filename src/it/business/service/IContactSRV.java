package it.business.service;

import java.util.List;

import it.business.dto.ContactDTO;
import it.business.enums.ContactTypeEnum;

public interface IContactSRV {

	void addContact(ContactDTO contactToSave);

	List<ContactDTO> findByFirstName(String firstName);

	List<ContactDTO> findByLastName(String lastName);

	List<ContactDTO> findByContactType(ContactTypeEnum contactType);

	List<ContactDTO> findAll();
	
	ContactDTO findById(String id);

	void removeContact(String id);

}
