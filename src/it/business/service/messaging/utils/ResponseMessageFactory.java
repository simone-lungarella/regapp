package it.business.service.messaging.utils;

import org.apache.commons.lang.NotImplementedException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Factory per la costruzione delle response <em> xml </em>.
 * 
 * @author Simone Lungarella
 */
public class ResponseMessageFactory implements MessageFactory {

	/**
	 * Costruisce l'elemento root della response.
	 * 
	 * @param document Documento da costruire.
	 * @return Elemento root costruito.
	 */
	@Override
	public Element buildRootElement(Document document) {
		throw new NotImplementedException("I metodi per la costruzione delle response non sono implementati");
	}

	/**
	 * Crea e restituisce l'elemento <em> xml </em> di creazione dominio.
	 * 
	 * @param doc Document in fase di costruzione.
	 * @return Elemento costruito.
	 */
	@Override
	public Element buildCreateDomainElement(Document doc) {
		throw new NotImplementedException("I metodi per la costruzione delle response non sono implementati");
	}

	/**
	 * Costruisce e restituisce un elemento generico con i parametri stabiliti.
	 * 
	 * @param doc      Documento in creazione.
	 * @param entity   Entity dell'elemento.
	 * @param property Proprietà dell'elemento.
	 * @param value    Valore dell'elemento.
	 * @return Elemento costruito.
	 */
	@Override
	public Element buildGenericElementWithValue(Document doc, String entity, String property, String value) {
		throw new NotImplementedException("I metodi per la costruzione delle response non sono implementati");
	}

	/**
	 * Costruisce e restituisce l'elemento di costruzione estensione DNSSEC.
	 * 
	 * @param doc Documento in costruzione.
	 * @return Elemento costruito.
	 */
	@Override
	public Element buildDnsSecExtension(Document doc) {
		throw new NotImplementedException("I metodi per la costruzione delle response non sono implementati");
	}

}
