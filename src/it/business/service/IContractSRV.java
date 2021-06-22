package it.business.service;

import java.util.List;

import it.business.dto.ContractDTO;

/**
 * Interfaccia del servizio di gestione dei contratti.
 * 
 * @author Simone Lungarella
 */
public interface IContractSRV {
	
	/**
	 * Restituisce le informazioni sul contratto identificato dal <code> contractNumber </code>.
	 * 
	 * @param contractNumber Identificativo del contratto ricercato.
	 * @return Contratto recuperato se esistente, <code> null </code> se non esiste un contratto identifciato dal <code> contractNumber </code>.
	 */
	ContractDTO findByContractNumber(int contractNumber);

	/**
	 * Restituisce tutti i contratti che detiene un Registrar identificato dal suo codice fiscale o P.Iva: <code> registrar </code>.
	 * 
	 * @param registrar Identificativo del Registrar.
	 * @return Lista dei contratti recuperati.
	 */
	List<ContractDTO> findByRegistrar(String registrar);
	
	/**
	 * Restituisce tutti i contratti registrati per un Registrant identificato dal codice fiscale: <code> registrant </code>.
	 * 
	 * @param registrant Identificativo del Registrant.
	 * @return Lista dei contratti recuperati.
	 */
	List<ContractDTO> findByRegistrant(String registrant);
	
	/**
	 * Restituisce un contratto che gestisce un dominio identificato dal <code> domainName </code>.
	 * 
	 * @param domainName Identificativo del dominio.
	 * @return Contratto recuperato.
	 */
	ContractDTO findByDomainName(String domainName);
	
	/**
	 * Restituisce la lista di tutti i contratti esistenti sulla base dati.
	 * 
	 * @return Lista di contratti esistenti.
	 */
	List<ContractDTO> findAll();
	
	/**
	 * Consente di rendere persistenti sulla base dati le informazioni sul contratto.
	 * 
	 * @param contract Contratto da memorizzare sullo strato di persistenza.
	 */
	void addContract (ContractDTO contract);
	
	/**
	 * Consente di eliminare un contratto identificato dal <code> contractNumber </code>.
	 * 
	 * @param contractNumber Identificativo del contratto da eliminare.
	 */
	void removeContract (int contractNumber);
	
}
