package it.business.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import it.business.dao.IContractDAO;
import it.business.dto.ContractDTO;

/**
 * Servizio di gestione dei contratti.
 * 
 * @author Simone Lungarella
 */
@Service
@Component
public class ContractSRV extends AbstractService implements IContractSRV {

	/**
	 * La costante serial version UID.
	 */
	private static final long serialVersionUID = 7586912192025747113L;

	/**
	 * DAO per la gestione dei contratti sullo strato di persistenza.
	 */
	@Autowired
	private IContractDAO contractDAO;

	/**
	 * Restituisce il contratto identificato dal <code> contractNumber </code>.
	 * 
	 * @param contractNumber Numero identificativo del contratto.
	 * @return Contratto recuperato.
	 */
	@Override
	public ContractDTO findByContractNumber(int contractNumber) {
		Connection connection = null;
		ContractDTO contract = new ContractDTO();
		try {
			connection = setupConnection(getDataSource().getConnection(), false);
			contract = contractDAO.findByContractNumber(connection, contractNumber);
		} catch (Exception e) {
			System.out.println("Errore riscontrato durante il setup della connessione");
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return contract;
	}

	/**
	 * Restituisce il contratto associato al Registrar identificato dal
	 * <code> registrar </code>.
	 * 
	 * @param registrar Identificativo del Registrar.
	 * @return Lista dei contratti recuperati.
	 */
	@Override
	public List<ContractDTO> findByRegistrar(String registrar) {
		Connection connection = null;
		List<ContractDTO> contracts = new ArrayList<>();
		try {
			connection = setupConnection(getDataSource().getConnection(), false);
			contracts = contractDAO.findByRegistrar(connection, registrar);
		} catch (Exception e) {
			System.out.println("Errore riscontrato durante il setup della connessione");
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return contracts;
	}

	/**
	 * Restituisce la lista dei contratti associati al Registrant identificato dal
	 * <code> registrant </code>.
	 * 
	 * @param registrant Identificativo del Registrant.
	 * @return Lista dei contratti recuperati.
	 */
	@Override
	public List<ContractDTO> findByRegistrant(String registrant) {
		Connection connection = null;
		List<ContractDTO> contracts = new ArrayList<>();
		try {
			connection = setupConnection(getDataSource().getConnection(), false);
			contracts = contractDAO.findByRegistrant(connection, registrant);
		} catch (Exception e) {
			System.out.println("Errore riscontrato durante il setup della connessione");
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return contracts;
	}

	/**
	 * Restituisce il contratto che gestisce il dominio identificato dal
	 * <code> domainName </code>.
	 * 
	 * @param domainName Nome del dominio.
	 * @return Contratto recuperato.
	 */
	@Override
	public ContractDTO findByDomainName(String domainName) {
		Connection connection = null;
		ContractDTO contract = null;
		try {
			connection = setupConnection(getDataSource().getConnection(), false);
			contract = contractDAO.findByDomainName(connection, domainName);
		} catch (Exception e) {
			System.out.println("Errore riscontrato durante il setup della connessione");
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return contract;
	}

	/**
	 * Consente di rendere persistente il contratto sulla base dati.
	 * 
	 * @param contract Contratto da memorizzare.
	 */
	@Override
	public void addContract(ContractDTO contract) {
		Connection connection = null;
		try {
			connection = setupConnection(getDataSource().getConnection(), false);
			contractDAO.addContract(connection, contract);
		} catch (SQLException e) {
			System.out.println("Errore riscontrato durante il setup della connessione");
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

	/**
	 * Consente di eliminare il contratto identificato dal
	 * <code> contractNumber </code>.
	 * 
	 * @param contractNumber Identificativo del contratto.
	 */
	@Override
	public void removeContract(int contractNumber) {
		Connection connection = null;
		try {
			connection = setupConnection(getDataSource().getConnection(), false);
			contractDAO.removeContractByNumber(connection, contractNumber);
		} catch (Exception e) {
			System.out.println("Errore riscontrato durante il setup della connessione");
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

	}

	/**
	 * Recupera la lista dei contratti esistenti sul database.
	 * 
	 * @return Lista dei contratti recuperati.
	 */
	@Override
	public List<ContractDTO> findAll() {
		Connection connection = null;
		List<ContractDTO> contracts = new ArrayList<>();
		try {
			connection = setupConnection(getDataSource().getConnection(), false);
			contracts = contractDAO.findAll(connection);
		} catch (Exception e) {
			System.out.println("Errore riscontrato durante il setup della connessione");
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return contracts;
	}

}
