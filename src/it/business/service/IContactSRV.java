package it.business.service;

import java.util.List;

import it.business.dto.ContactDTO;
import it.business.enums.ContactTypeEnum;

/**
 * Interfaccia del servizio di gestione contatti.
 * 
 * @author Simone Lungarella
 */
public interface IContactSRV {
	
	/**
	 * Restituisce il contatto identificato dall' <code> id </code>.
	 * 
	 * @param id Identificativo univoco del contatto.
	 * @return Contatto recuperato.
	 */
	ContactDTO findById(String id);

	/**
	 * Restituisce la lista dei contatti identificati dal <code> firstName </code>.
	 * 
	 * @param firstName Nome dei contatti da recuperare.
	 * @return Contatti recuperati dalla ricerca.
	 */
	List<ContactDTO> findByFirstName(String firstName);

	/**
	 * Restituisce la lista dei contatti identificati dal <code> lastName </code>.
	 * 
	 * @param lastName Cognome dei contatti da recuperare.
	 * @return Contatti recuperati dalla ricerca.
	 */
	List<ContactDTO> findByLastName(String lastName);

	/**
	 * Restituisce la lista dei contatti del tipo: <code> contactType </code>.
	 * @see ContactTypeEnum.
	 * 
	 * @param contactType Tipologia contatti da ricercare.
	 * @return Contatti recuperati dalla ricerca.
	 */
	List<ContactDTO> findByContactType(ContactTypeEnum contactType);

	/**
	 * Restituisce tutti i contatti esistenti sulla base dati.
	 * 
	 * @return Contatti recuperati dalla ricerca.
	 */
	List<ContactDTO> findAll();

	/**
	 * Consente di rendere persistenti sulla base dati le informazioni sul contatto.
	 * 
	 * @param contactToSave Contatto da memorizzare.
	 */
	void addContact(ContactDTO contactToSave);

	/**
	 * Consente di eliminare un contatto identificato dall' <code> id </code>.
	 * 
	 * @param id Identificativo del contatto da eliminare.
	 */
	void removeContact(String id);

}
