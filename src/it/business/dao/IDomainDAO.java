package it.business.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import it.business.dto.DomainDTO;

/**
 * Interfaccia del DAO che gestisce i domini.
 * 
 * @author Simone Lungarella
 */
public interface IDomainDAO extends Serializable {

	/**
	 * Recupera e restituisce il dominio identificato dal <code> domainName </code>.
	 * 
	 * @param connection Connessione al database.
	 * @param domainName Identificativo del dominio da recuperare.
	 * @return Dominio recuperato se esistente sulla base dati.
	 */
	DomainDTO findByDomainName(Connection connection, String domainName);

	/**
	 * Restituisce la lista dei domini il cui Registrant è identificato dal
	 * <code> idRegistrant </code>.
	 * 
	 * @param connection   Connessione al database.
	 * @param idRegistrant Identificativo del Registrant.
	 * @return Lista dei domini recuperati.
	 */
	List<DomainDTO> findByRegistrant(Connection connection, String idRegistrant);

	/**
	 * Restituisce la lista dei domini che rispettano la sicurezza imposta dal flag:
	 * <code> isSafe </code>.
	 * 
	 * @param connection Connessione al database.
	 * @param isSafe     Indica se è presente o meno l'estensione di sicurezza sul
	 *                   dominio.
	 * @return La lista dei domini recuperata.
	 */
	List<DomainDTO> findBySecurity(Connection connection, boolean isSafe);

	/**
	 * Restituisce tutti i domini esistenti sulla base dati.
	 * 
	 * @param connection Connessione al database.
	 * @return Lista dei domini recuperati.
	 */
	List<DomainDTO> findAll(Connection connection);

	/**
	 * Consente di memorizzare sulla base dati il dominio.
	 * 
	 * @param connection Connessione al database fornita dal servizio.
	 * @param domain     Dominio da memorizzare.
	 */
	void addDomain(Connection connection, DomainDTO domain);

	/**
	 * Consente l'eliminazione di un dominio identificato dal
	 * <code> domainName </code>.
	 * 
	 * @param connection Connessione al database.
	 * @param domainName Identificativo del dominio da eliminare.
	 */
	void removeByDomainName(Connection connection, String domainName);

	/**
	 * Aggiorna il dominio identificato dal <code> DomainDTO.getDomainName() <code>.
	 * 
	 * @param connection Connessione al database.
	 * @param domain     Dominio aggiornato.
	 */
	void update(Connection connection, DomainDTO domain);
}
