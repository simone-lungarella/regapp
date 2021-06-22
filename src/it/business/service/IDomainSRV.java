package it.business.service;

import java.util.List;

import it.business.dto.DomainDTO;

/**
 * Interfaccia del servizio di gestione domini.
 * 
 * @author Simone Lungarella
 */
public interface IDomainSRV{
	
	/**
	 * Restituisce il dominio identificato dal <code> domainName </code>.
	 * 
	 * @param domainName Identificativo univoco del dominio ricercato.
	 * @return Dominio recuperato.
	 */
	DomainDTO findByDomainName(String domainName);
	
	/**
	 * Restituisce la lista dei domini che hanno come Registrant il contatto identificato dal codice fiscale: <code> registrant </code>.
	 * 
	 * @param registrant Identificativo del contatto Registrant per il quale vengono recuperati i domini.
	 * @return Lista dei domini recuperati.
	 */
	List<DomainDTO> findByRegistrant(String registrant);

	/**
	 * Restituisce la lista dei domini in base al livello di sicurezza fornita dalle specifiche <em> DNSSEC </em>.
	 * 
	 * @param dnssec Se <code> true </code> vengono recuperati tutti i domini intesi come sicuri.
	 * @return Lista dei domini che rispettano il vincolo imposto dal parametro.
	 */
	List<DomainDTO> findBySecurity(boolean dnssec);
	
	/**
	 * Restituisce tutti i domini memorizzati sulla base dati.
	 * @return Lista dei domini esistenti.
	 */
	List<DomainDTO> findAll();
	
	/**
	 * Consente di rendere persistente sulla base dati il dominio le cui caratteristiche sono specificate nel DTO: <code> domainToSave </code>.
	 * 
	 * @param domainToSave Dominio da memorizzare.
	 */
	void addDomain(DomainDTO domainToSave);
	
	/**
	 * Consente l'eliminazione del dominio identificato dal <code> domainName </code> in maniera permanente.
	 * 
	 * @param domainName Dominio da eliminare.
	 */
	void removeDomain(String domainName);
	
}
