package it.business.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.business.dao.IContractDAO;
import it.business.dto.ContractDTO;

public class ContractSRV extends AbstractService implements IContractSRV{

	private static final long serialVersionUID = 7586912192025747113L;

	@Autowired
	private IContractDAO contractDAO;

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
