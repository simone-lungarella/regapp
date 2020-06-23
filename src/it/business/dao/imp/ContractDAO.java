package it.business.dao.imp;

import java.sql.Connection;
import java.util.List;

import it.business.dao.IContractDAO;
import it.business.dto.ContractDTO;

public class ContractDAO implements IContractDAO{

	private static final long serialVersionUID = -7998466574893315972L;

	@Override
	public ContractDTO findByContractNumber(Connection connection, int contractNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContractDTO> findByRegistrar(Connection connection, String idRegistrar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContractDTO> findByRegistrant(Connection connection, String idRegistrant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ContractDTO> findAll(Connection connection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addContract(Connection connection, ContractDTO contract) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeContractByNumber(Connection connection, int contractNumber) {
		// TODO Auto-generated method stub
		
	}

}
