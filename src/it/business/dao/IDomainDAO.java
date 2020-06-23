package it.business.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import it.business.dto.DomainDTO;

/**
 * @author Simone Lungarella
 * 
 */

public interface IDomainDAO extends Serializable {
	DomainDTO findByDomainName(Connection connection, String domainName);
	List<DomainDTO> findByRegistrant(Connection connection, String idRegistrant);
	List<DomainDTO> findBySecurity(Connection connection, boolean isSafe);
	List<DomainDTO> findAll(Connection connection);
	void addDomain(Connection connection, DomainDTO domain);
	void removeByDomainName(Connection connection, String domainName);
	void update (Connection connection, DomainDTO domain);
}
