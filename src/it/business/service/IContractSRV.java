package it.business.service;

import java.util.List;

import it.business.dto.ContractDTO;

/**
 * @author Simone Lungarella
 * */

public interface IContractSRV {
	
	ContractDTO findByContractNumber(int contractNumber);
	
	List<ContractDTO> findByRegistrar(String registrar);
	
	List<ContractDTO> findByRegistrant(String registrant);
	
	ContractDTO findByDomainName(String domainName);
	
	List<ContractDTO> findAll();
	
	void addContract (ContractDTO contract);
	
	void removeContract (int contractNumber);
	
}
