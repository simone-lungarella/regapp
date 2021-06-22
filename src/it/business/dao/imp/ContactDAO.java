package it.business.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import it.business.dao.AbstractDAO;
import it.business.dao.IContactDAO;
import it.business.dto.ContactDTO;
import it.business.enums.ContactTypeEnum;

/**
 * Dao per la gestione dei contatti.
 * 
 * @author Simone Lungarella
 */
@Repository
public class ContactDAO extends AbstractDAO implements IContactDAO {

	/**
	 * La costante serial version UID.
	 */
	private static final long serialVersionUID = -9110222349730022857L;

	/**
	 * Recupera i contatti identificati dal <code> firstName </code>.
	 * 
	 * @param connection Connessione al database.
	 * @param firstName  Nome dei contatti da recuperare.
	 * @return Lista dei contatti recuperati.
	 */
	@Override
	public List<ContactDTO> findByFirstName(Connection connection, String firstName) {
		List<ContactDTO> contacts = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ContactDTO contact = new ContactDTO();
		try {
			String query = "SELECT * FROM contacts WHERE firstName = ?";
			ps = connection.prepareStatement(query);
			ps.setString(1, firstName);
			rs = ps.executeQuery();
			while (rs.next()) {
				contact.setContactId(rs.getString("contactId"));
				contact.setContactType(ContactTypeEnum.valueOf(rs.getString("contactType")));
				contact.setFirstName(rs.getString("firstName"));
				contact.setLastName(rs.getString("lastName"));
				contacts.add(contact);
			}
		} catch (SQLException e) {
			System.out.println("Errore riscontrato nell'esecuzione della query nel metodo findByFirstName");
			e.printStackTrace();
		} finally {
			closeStatement(ps, rs);
		}

		return contacts;
	}

	/**
	 * Recupera i contatti identificati dal <code> lastName </code>.
	 * 
	 * @param connection Connessione al database.
	 * @param lastName   Cognome dei contatti da recuperare.
	 * @return Lista dei contatti recuperati.
	 */
	@Override
	public List<ContactDTO> findByLastName(Connection connection, String lastName) {
		List<ContactDTO> contacts = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ContactDTO contact = new ContactDTO();
		try {
			String query = "SELECT * FROM contacts WHERE lastName = ?";
			ps = connection.prepareStatement(query);
			ps.setString(1, lastName);
			rs = ps.executeQuery();
			while (rs.next()) {
				contact.setContactId(rs.getString("contactId"));
				contact.setContactType(ContactTypeEnum.valueOf(rs.getString("contactType")));
				contact.setFirstName(rs.getString("firstName"));
				contact.setLastName(rs.getString("lastName"));
				contacts.add(contact);
			}
		} catch (SQLException e) {
			System.out.println("Errore riscontrato nell'esecuzione della query nel metodo findByLastName");
			e.printStackTrace();
		} finally {
			closeStatement(ps, rs);
		}
		return contacts;
	}

	/**
	 * Recupera tutti i contatti del tipo specificato.
	 * 
	 * @param connection  Connessione al database.
	 * @param contactType Tipologia dei contatti da recuperare.
	 * @return Lista dei contatti recuperati.
	 */
	@Override
	public List<ContactDTO> findByContactType(Connection connection, ContactTypeEnum contactType) {
		List<ContactDTO> contacts = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ContactDTO contact = new ContactDTO();
		try {
			String query = "SELECT * FROM contacts WHERE contactType = ?";
			ps = connection.prepareStatement(query);
			ps.setString(1, contactType.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				contact.setContactId(rs.getString("contactId"));
				contact.setContactType(ContactTypeEnum.valueOf(rs.getString("contactType")));
				contact.setFirstName(rs.getString("firstName"));
				contact.setLastName(rs.getString("lastName"));
				contacts.add(contact);
			}
		} catch (SQLException e) {
			System.out.println("Errore riscontrato nell'esecuzione della query nel metodo findByContactType");
			e.printStackTrace();
		} finally {
			closeStatement(ps, rs);
		}

		return contacts;
	}

	/**
	 * Recupera e restituisce il contatto identificato univocamente dall'
	 * <code> id </code>.
	 * 
	 * @param connection Connessione al database.
	 * @param id         Identificativo del contatto da recuperare.
	 * @return Contatto recuperato.
	 */
	@Override
	public ContactDTO findById(Connection connection, String id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ContactDTO contact = new ContactDTO();
		try {
			String query = "SELECT * FROM contacts WHERE contactId = ?";
			ps = connection.prepareStatement(query);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				contact.setContactId(rs.getString("contactId"));
				contact.setContactType(ContactTypeEnum.valueOf(rs.getString("contactType")));
				contact.setFirstName(rs.getString("firstName"));
				contact.setLastName(rs.getString("lastName"));
			}
		} catch (SQLException e) {
			System.out.println("Errore riscontrato nell'esecuzione della query nel metodo findById");
			e.printStackTrace();
		} finally {
			closeStatement(ps, rs);
		}

		return contact;

	}

	/**
	 * Recupera e restituisce tutti i contatti esistenti sulla base dati.
	 * 
	 * @param connection Connessione al database.
	 * @return Lista dei contatti esistenti.
	 */
	@Override
	public List<ContactDTO> findAll(Connection connection) {
		List<ContactDTO> contacts = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String query = "SELECT * From contacts";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				ContactDTO contact = new ContactDTO();
				contact.setContactId(rs.getString("contactId"));
				contact.setContactType(ContactTypeEnum.valueOf(rs.getString("contactType")));
				contact.setFirstName(rs.getString("firstName"));
				contact.setLastName(rs.getString("lastName"));
				contacts.add(contact);
			}
		} catch (SQLException e) {
			System.out.println("Errore riscontrato nell'esecuzione della query nel metodo findByContactType");
			e.printStackTrace();
		} finally {
			closeStatement(ps, rs);
		}
		return contacts;
	}

	/**
	 * Rende persistente il contatto e le sue informazioni sulla base dati.
	 * 
	 * @param connection Connessione al database.
	 * @param contact    Contatto da memorizzare.
	 */
	@Override
	public void addContact(Connection connection, ContactDTO contact) {
		PreparedStatement ps = null;

		int index = 1;
		try {
			String query = "INSERT INTO contacts (contactId, firstName, lastName, contactType) VALUES (?,?,?,?)";
			ps = connection.prepareStatement(query);
			ps.setString(index++, contact.getContactId());
			ps.setString(index++, contact.getFirstName());
			ps.setString(index++, contact.getLastName());
			ps.setString(index++, contact.getContactType().toString());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Errore riscontrato durante l'inserimento del nuovo utente");
			e.printStackTrace();
		} finally {
			closeStatement(ps);
		}
	}

	/**
	 * Elimina in maniera permanente il contatto identificato dall'
	 * <code> id </code>.
	 * 
	 * @param connection Connessione al database.
	 * @param id         Identificativo del contatto da eliminare.
	 */
	@Override
	public void removeById(Connection connection, String id) {
		PreparedStatement ps = null;

		try {
			String query = "DELETE FROM contacts WHERE contactId = ?";
			ps = connection.prepareStatement(query);
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Errore riscontrato durante l'eliminazione dell' utente");
			e.printStackTrace();
		} finally {
			closeStatement(ps);
		}
	}

	/**
	 * Effettua un aggiornamento del contatto impostandone i parametri specificati
	 * dal DTO.
	 * 
	 * @param connection Connessione al database.
	 * @param newContact Contatto aggiornato.
	 */
	@Override
	public void update(Connection connection, ContactDTO newContact) {
		PreparedStatement ps = null;
		int index = 1;
		try {
			String query = "UPDATE contacts SET firstName = ?, lastName = ?, contactType = ? WHERE contactId = ?";
			ps = connection.prepareStatement(query);
			ps.setString(index++, newContact.getFirstName());
			ps.setString(index++, newContact.getLastName());
			ps.setString(index++, newContact.getContactType().toString());
			ps.setString(index, newContact.getContactId());
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Errore durante l'update dell'utente");
		} finally {
			closeStatement(ps);
		}
	}
}
