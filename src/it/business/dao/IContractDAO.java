package it.business.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import it.business.dto.ContractDTO;

/**
 * @author Simone Lungarella
 * 
 */

public interface IContractDAO extends Serializable{
	ContractDTO findByContractNumber(Connection connection, int contractNumber);
	List<ContractDTO> findByRegistrar(Connection connection, String idRegistrar);
	List<ContractDTO> findByRegistrant(Connection connection, String idRegistrant);
	List<ContractDTO> findAll(Connection connection);
	void addContract(Connection connection, ContractDTO contract);
	void removeContractByNumber(Connection connection, int contractNumber);
	
}
