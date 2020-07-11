package it.business.service.messaging.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author Simone Lungarella
 * */

public interface MessageFactory {

	/**
	 * Genera l'elemento root per una qualsiasi richiesta epp
	 * @param Document
	 * */
	Element buildRootElement(Document document);
	
	Element buildCreateDomainElement(Document doc);
	Element buildGenericElementWithValue(Document doc, String entity, String property, String value);
	Element buildDnsSecExtension(Document doc);
}
