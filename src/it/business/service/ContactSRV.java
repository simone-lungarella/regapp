package it.business.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import it.business.dao.IContactDAO;
import it.business.dto.ContactDTO;
import it.business.enums.ContactTypeEnum;

/**
 * Questo service permette di gestire tutte le operazioni sui contatti.
 * 
 * @author Simone Lungarella
 */
@Service
@Component
public class ContactSRV extends AbstractService implements IContactSRV {

	/**
	 * La costante serial version UID.
	 */
	private static final long serialVersionUID = -1374170442695067665L;

	/**
	 * DAO per la gestione dei contatti sullo strato di persistenza.
	 */
	@Autowired
	private IContactDAO contactDAO;

	/**
	 * Consente di memorizzare sulla base dati il contatto.
	 * 
	 * @param contactToSave Contatto da memorizzare.
	 */
	@Override
	public void addContact(ContactDTO contactToSave) {
		Connection connection = null;
		try {
			connection = setupConnection(getDataSource().getConnection(), false);
			contactDAO.addContact(connection, contactToSave);
		} catch (SQLException e) {
			System.out.println("Errore riscontrato durante il setup della connessione");
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

	/**
	 * Restituisce i contatti identificati dal <code> firstName </code>.
	 * 
	 * @param firstName Nome dei contatti da recuperare.
	 * @return Lista dei contatti recuperati.
	 */
	@Override
	public List<ContactDTO> findByFirstName(String firstName) {
		Connection connection = null;
		List<ContactDTO> contacts = new ArrayList<>();
		try {
			connection = setupConnection(getDataSource().getConnection(), false);
			contacts = contactDAO.findByFirstName(connection, firstName);
		} catch (Exception e) {
			System.out.println("Errore riscontrato durante il setup della connessione");
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return contacts;
	}

	/**
	 * Restituisce la lista dei contatti identificati dal <code> lastName </code>.
	 * 
	 * @param lastName Cognome dei contatti recuperati.
	 * @return Lista dei contatti recuperati.
	 */
	@Override
	public List<ContactDTO> findByLastName(String lastName) {
		Connection connection = null;
		List<ContactDTO> contacts = new ArrayList<>();
		try {
			connection = setupConnection(getDataSource().getConnection(), false);
			contacts = contactDAO.findByLastName(connection, lastName);
		} catch (Exception e) {
			System.out.println("Errore riscontrato durante il setup della connessione");
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return contacts;
	}

	/**
	 * Restituisce tutti i contatti che appartengono ad una specifica tipologia.
	 * 
	 * @param contactType Tipologia dei contatti da recuperare.
	 * @return Lista dei contatti recuperati.
	 */
	@Override
	public List<ContactDTO> findByContactType(ContactTypeEnum contactType) {
		Connection connection = null;
		List<ContactDTO> contacts = new ArrayList<>();
		try {
			connection = setupConnection(getDataSource().getConnection(), false);
			contacts = contactDAO.findByContactType(connection, contactType);
		} catch (Exception e) {
			System.out.println("Errore riscontrato durante il setup della connessione");
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return contacts;
	}

	/**
	 * Restituisce il contatto identificato dall' <code> id </code>.
	 * 
	 * @param id Identificativo del contatto recuperato.
	 * @return Contatto recuperato.
	 */
	@Override
	public ContactDTO findById(String id) {
		Connection connection = null;
		ContactDTO contact = new ContactDTO();
		try {
			connection = setupConnection(getDataSource().getConnection(), false);
			contact = contactDAO.findById(connection, id);
		} catch (Exception e) {
			System.out.println("Errore riscontrato durante il setup della connessione");
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return contact;
	}

	/**
	 * Restituisce tutti i contatti esistenti sulla base dati.
	 * 
	 * @return Lista dei contatti recuperati.
	 */
	@Override
	public List<ContactDTO> findAll() {
		Connection connection = null;
		List<ContactDTO> contacts = new ArrayList<>();
		try {
			connection = setupConnection(getDataSource().getConnection(), false);
			contacts = contactDAO.findAll(connection);
		} catch (Exception e) {
			System.out.println("Errore riscontrato durante il setup della connessione");
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return contacts;
	}

	/**
	 * Consente di eliminare dalla base dati il contatto identificato dall' <code> id </code>.
	 * 
	 * @param id Identificativo del contatto da eliminare.
	 */
	@Override
	public void removeContact(String id) {
		Connection connection = null;
		try {
			connection = setupConnection(getDataSource().getConnection(), false);
			contactDAO.removeById(connection, id);
		} catch (Exception e) {
			System.out.println("Errore riscontrato durante il setup della connessione");
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}
}
