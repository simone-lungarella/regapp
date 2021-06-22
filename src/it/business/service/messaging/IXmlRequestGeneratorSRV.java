package it.business.service.messaging;

import java.util.List;

import it.business.dto.DomainDTO;

/**
 * Interfaccia del servizio di creazione Request del protocollo EPP.
 * 
 * @author Simone Lungarella
 */
public interface IXmlRequestGeneratorSRV extends IXmlGeneratorSRV {

	/**
	 * Restituisce una Request di creazione dominio a partire dalle entità passate
	 * come parametri.
	 * 
	 * @param domain     Informazioni sul dominio da creare.
	 * @param contactIds Contatti associati e referenziati tramite Id che
	 *                   constituiscono i soggetti interessati al dominio.
	 * @return Request generata per la comunicazione con il Registro .it.
	 */
	String getCreateDomainXmlRequest(DomainDTO domain, List<String> contactIds);

	/**
	 * Restituisce una Request di creazione dominio a partire dalle entità passate
	 * come parametri.
	 * 
	 * @param domain     Informazioni sul dominio da creare.
	 * @param contactIds Contatti associati e referenziati tramite Id che
	 *                   constituiscono i soggetti interessati al dominio.
	 * @return Request generata per la comunicazione con il Registro .it.
	 */
	String getCreateDomainXmlRequest(DomainDTO domain, String... contactIds);

}
