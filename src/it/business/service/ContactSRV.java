package it.business.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import it.business.dto.ContactDTO;

/**
 * @author Simone Lungarella
 * Questo service permette di gestire tutte le operazioni sui contatti
 * */

@Service
@Component
public class ContactSRV extends AbstractService{

	private static final long serialVersionUID = -1374170442695067665L;

	
	
	
	public void addContact(ContactDTO contactToSave) {
		try {
			Connection connection = setupConnection(getDataSource().getConnection(), false);
			//TODO: Chiamare il ContactDAO per gestire il salvataggio in db
		} catch (SQLException e) {
			System.out.println("Errore riscontrato durante il setup della connessione");
			e.printStackTrace();
		}
	}
}
