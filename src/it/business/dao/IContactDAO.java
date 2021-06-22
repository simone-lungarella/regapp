package it.business.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import it.business.dto.ContactDTO;
import it.business.enums.ContactTypeEnum;

/**
 * Interfaccia del DAO che gestisce i contatti.
 * 
 * @author Simone Lungarella
 */
public interface IContactDAO extends Serializable {

	/**
	 * Restituisce i contatti identificati dal <code> firstName </code>.
	 * 
	 * @param connection Connessione al database.
	 * @param firstName  Nome dei contatti da recuperare.
	 * @return Lista dei contatti con il nome specificato.
	 */
	List<ContactDTO> findByFirstName(Connection connection, String firstName);

	/**
	 * Restituisce i contatti identificati dal <code> lastName </code>.
	 * 
	 * @param connection Connessione al database.
	 * @param lastName   Cognome dei contatti da recuperare.
	 * @return Lista dei contatti con il cognome specificato.
	 */
	List<ContactDTO> findByLastName(Connection connection, String lastName);

	/**
	 * Restituisce i contatti della tipologia: <code> contactType </code>.
	 * 
	 * @param connection  Connessione al database.
	 * @param contactType Tipologia dei contatti da recuperare.
	 * @return Contatti recuperati della stessa tipologia.
	 */
	List<ContactDTO> findByContactType(Connection connection, ContactTypeEnum contactType);

	/**
	 * Restituisce tutti i contatti esistenti sulla base dati.
	 * 
	 * @param connection Connessione al database.
	 * @return Lista dei contatti.
	 */
	List<ContactDTO> findAll(Connection connection);

	/**
	 * Restituisce il contatto identificato univocamente dall' <code> id </code>.
	 * 
	 * @param connection Connessione al database.
	 * @param id         Identificativo del contatto.
	 * @return Contatto recuperato.
	 */
	ContactDTO findById(Connection connection, String id);

	/**
	 * Consente di memorizzare sulla base dati un nuovo contatto.
	 * 
	 * @param connection Connessione al database.
	 * @param contact    Contatto da memorizzare.
	 */
	void addContact(Connection connection, ContactDTO contact);

	/**
	 * Elimina in maniera permanente il contatto identificato dall'
	 * <code> id </code>.
	 * 
	 * @param connection Connessione al database.
	 * @param id         Identificativo del contatto da eliminare.
	 */
	void removeById(Connection connection, String id);

	/**
	 * Questo metodo consente la modifica di un Contact, le modifiche possono essere
	 * fatte su nome, cognome, e tipologia del contatto. Non è possibile modificare
	 * l'id di un contatto.
	 * 
	 * @param connection Connessione al database.
	 * @param newContact Contatto aggiornato.
	 */
	void update(Connection connection, ContactDTO newContact);
}
