package it.business.service;

import java.util.List;

import it.business.dto.DomainDTO;

/**
 * @author Simone Lungarella
 * */

public interface IDomainSRV{
	
	DomainDTO findByDomainName(String domainName);
	
	List<DomainDTO> findByRegistrant(String registrant);
	
	List<DomainDTO> findBySecurity(boolean dnssec);
	
	List<DomainDTO> findAll();
	
	void addDomain(DomainDTO domainToSave);
	
	void removeDomain(String domainName);
}
