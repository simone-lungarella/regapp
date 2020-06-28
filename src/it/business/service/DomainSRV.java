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
 * @author Simone Lungarella
 * */

@Service
@Component
public class DomainSRV extends AbstractService implements IDomainSRV{

	private static final long serialVersionUID = -6139923363203091832L;
	
	@Autowired
	private IDomainDAO domainDAO;
	
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
