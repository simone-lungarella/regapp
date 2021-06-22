package it.business.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import it.business.dto.ContractDTO;

/**
 * Interfaccia del DAO che gestisce i contratti.
 * 
 * @author Simone Lungarella
 */
public interface IContractDAO extends Serializable {

	/**
	 * Restituisce il contratto identificato dal <code> contractNumber </code>.
	 * 
	 * @param connection     Connessione al database.
	 * @param contractNumber Identificativo del contratto.
	 * @return Contratto recuperato.
	 */
	ContractDTO findByContractNumber(Connection connection, int contractNumber);

	/**
	 * Recupera e restituisce la lista dei contratti che vincola il Registrar
	 * identificato dal <code> idRegistrar </code>.
	 * 
	 * @param connection  Connessione al database.
	 * @param idRegistrar Identifativo del Registrar.
	 * @return Lista dei contratti recuperati.
	 */
	List<ContractDTO> findByRegistrar(Connection connection, String idRegistrar);

	/**
	 * Recupera e restituisce la lista dei contratti che vincolano e tutelano un
	 * Registrant identificato dal <code> idRegistrant </code>.
	 * 
	 * @param connection   Connessione al database.
	 * @param idRegistrant Identificativo del Registrant.
	 * @return Lista dei contratti.
	 */
	List<ContractDTO> findByRegistrant(Connection connection, String idRegistrant);

	/**
	 * Restituisce tutti i contratti esistenti sullo strato di persistenza.
	 * 
	 * @param connection Connessione al database.
	 * @return Lista dei contratti recuperati.
	 */
	List<ContractDTO> findAll(Connection connection);

	/**
	 * Restituisce il contratto che determina i parametri e i vincoli legati al
	 * dominio identificato dal <code> domainName </code>.
	 * 
	 * @param connection Connessione al database.
	 * @param domainName Nome del dominio il cui contratto ne determina le
	 *                   caratteristiche.
	 * @return Contratto recuperato.
	 */
	ContractDTO findByDomainName(Connection connection, String domainName);

	/**
	 * Consente di rendere persistenti le informazioni su un contratto.
	 * 
	 * @param connection Connessione al database.
	 * @param contract   Contratto da memorizzare.
	 */
	void addContract(Connection connection, ContractDTO contract);

	/**
	 * Consente l'eliminazione del contratto identificato dal
	 * <code> contractNumber </code>.
	 * 
	 * @param connection     Connessione al database.
	 * @param contractNumber Identificativo del contratto da eliminare.
	 */
	void removeContractByNumber(Connection connection, int contractNumber);

}
