package it.business.service;

import java.util.List;

import it.business.dto.ContactDTO;
import it.business.enums.ContactTypeEnum;

/**
 * @author Simone Lungarella
 * */

public interface IContactSRV {
	
	ContactDTO findById(String id);

	List<ContactDTO> findByFirstName(String firstName);

	List<ContactDTO> findByLastName(String lastName);

	List<ContactDTO> findByContactType(ContactTypeEnum contactType);

	List<ContactDTO> findAll();

	void addContact(ContactDTO contactToSave);

	void removeContact(String id);

}
