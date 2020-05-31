package it.business.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import it.business.dto.ContactDTO;
import it.business.enums.ContactTypeEnum;

/**
 * @author Simone Lungarella
 * 
 */

public interface IContactDAO extends Serializable {
	
	List<ContactDTO> findByFirstName(Connection connection, String firstName);

	List<ContactDTO> findByLastName(Connection connection, String lastName);

	List<ContactDTO> findByContactType(Connection connection, ContactTypeEnum contactType);

	List<ContactDTO> findAll(Connection connection);
	
	ContactDTO findById(Connection connection, String id);
	
	void addContact(Connection connection, ContactDTO contact);

	void removeById(Connection connection, String id);

	/**
	 * Questo metodo consente la modifica di un Contact, le modifiche possono essere fatte su nome, cognome, e tipo_contatto.
	 * Non è possibile modificare l'id di un contatto perché esso rappresenterebbe un altro contatto
	 * */
	void update (Connection connection, ContactDTO newContact);
}
