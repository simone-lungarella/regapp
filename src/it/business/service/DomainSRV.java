package it.business.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import it.business.dao.IDomainDAO;
import it.business.dto.DomainDTO;

/**
 * Servizio di gestione dei domini.
 * 
 * @author Simone Lungarella
 */
@Service
@Component
public class DomainSRV extends AbstractService implements IDomainSRV {

	/**
	 * La costante serial version UID.
	 */
	private static final long serialVersionUID = -6139923363203091832L;

	/**
	 * DAO per la gestione dei domini sullo strato di persistenza.
	 */
	@Autowired
	private IDomainDAO domainDAO;

	/**
	 * Restituisce il dominio identificato dal <code> domainName </code>.
	 * 
	 * @param domainName Nome dominio utilizzato come identificativo univoco.
	 * @return Dominio recuperato.
	 */
	@Override
	public DomainDTO findByDomainName(String domainName) {
		Connection connection = null;
		DomainDTO domain = null;
		try {
			connection = setupConnection(getDataSource().getConnection(), false);
			domain = domainDAO.findByDomainName(connection, domainName);
		} catch (SQLException e) {
			System.out.println("Errore riscontrato durante il setup della connessione");
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return domain;
	}

	/**
	 * Restituisce i domini associati al Registrant identificato dal
	 * <code> registrant </code>.
	 * 
	 * @param registrant Identificativo del Registrant.
	 * @return Lista dei domini recuperati.
	 */
	@Override
	public List<DomainDTO> findByRegistrant(String registrant) {
		Connection connection = null;
		List<DomainDTO> domains = new ArrayList<>();
		try {
			connection = setupConnection(getDataSource().getConnection(), false);
			domains = domainDAO.findByRegistrant(connection, registrant);
		} catch (SQLException e) {
			System.out.println("Errore riscontrato durante il setup della connessione");
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return domains;
	}

	/**
	 * Restituisce tutti i domini che hanno o meno l'estensione di sicurezza in base
	 * al flag: <code> dnssec </code>.
	 * 
	 * @param dnssec Flag che identifica la sicurezza del dominio.
	 * @return Lista dei domini.
	 */
	@Override
	public List<DomainDTO> findBySecurity(boolean dnssec) {
		Connection connection = null;
		List<DomainDTO> domains = new ArrayList<>();
		try {
			connection = setupConnection(getDataSource().getConnection(), false);
			domains = domainDAO.findBySecurity(connection, dnssec);
		} catch (SQLException e) {
			System.out.println("Errore riscontrato durante il setup della connessione");
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return domains;
	}

	/**
	 * Restituisce la lista di tutti i domini esistenti sulla base dati.
	 * 
	 * @return Lista dei domini recuperati.
	 */
	@Override
	public List<DomainDTO> findAll() {
		Connection connection = null;
		List<DomainDTO> domains = new ArrayList<>();
		try {
			connection = setupConnection(getDataSource().getConnection(), false);
			domains = domainDAO.findAll(connection);
		} catch (SQLException e) {
			System.out.println("Errore riscontrato durante il setup della connessione");
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return domains;
	}

	/**
	 * Consente di memorizzare sulla base dati il dominio.
	 * 
	 * @param domainToSave Dominio da memorizzare.
	 */
	@Override
	public void addDomain(DomainDTO domainToSave) {
		Connection connection = null;
		try {
			connection = setupConnection(getDataSource().getConnection(), false);
			domainDAO.addDomain(connection, domainToSave);
		} catch (SQLException e) {
			System.out.println("Errore riscontrato durante il setup della connessione");
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

	/**
	 * Consente di rimuovere in maniera definitiva il dominio identificato dal
	 * <code> domainName </code>.
	 * 
	 * @param domainName Nome del dominio da rimuovere.
	 */
	@Override
	public void removeDomain(String domainName) {
		Connection connection = null;
		try {
			connection = setupConnection(getDataSource().getConnection(), false);
			domainDAO.removeByDomainName(connection, domainName);
		} catch (SQLException e) {
			System.out.println("Errore riscontrato durante il setup della connessione");
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

	}

}
