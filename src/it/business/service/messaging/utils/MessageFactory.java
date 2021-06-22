package it.business.service.messaging.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Interfaccia della factory di creazione Request e Response.
 * 
 * @author Simone Lungarella
 */
public interface MessageFactory {

	/**
	 * Genera l'elemento root per una qualsiasi richiesta che rispetta il protocollo
	 * EPP.
	 * 
	 * @param document Request in fase di costruzione.
	 * @return Elemento root costruito.
	 */
	Element buildRootElement(Document document);

	/**
	 * Genera l'elemento createDomain, elemento standard di ogni request epp del
	 * Registro .it.
	 * 
	 * @param document Request in fase di costruzione.
	 * @exception Elemento costruito.
	 */
	Element buildCreateDomainElement(Document document);

	/**
	 * Genera un elemento generico della Request.
	 * 
	 * @param document Request completa in fase di costruzione.
	 * @param entity   Nome del tag dell'elemento xml da creare.
	 * @param property Affiancato al nome (e.g entity:property), consente di
	 *                 costruire un tag di tipo property. Se property è una stringa
	 *                 vuota allora viene creato un tag di default.
	 * @param value    Il valore che acquisisce il determinato tag in creazione.
	 * @return Elemento creato.
	 */
	Element buildGenericElementWithValue(Document document, String entity, String property, String value);

	/**
	 * Genera il tag per gestire le operazioni con estensione di sicurezza. Si
	 * occupa di definire tutte le caratteristiche relative alla sicurezza.
	 * 
	 * L'estensione di sicurezza prevede il seguente formato: <code>
	 *		<extension>
	 * 			<secDNS:create xmlns:secDNS="urn:ietf:params:xml:ns:secDNS-1.1">
	 * 			<secDNS:dsData>
	 *  			<secDNS:keyTag>12345</secDNS:keyTag>
	 * 				<secDNS:alg>3</secDNS:alg>
	 *				<secDNS:digestType>1</secDNS:digestType>
	 * 				<secDNS:digest>4347d0f8ba661234a8eadc005e2e1d1b646c9682</secDNS:digest>
	 * 			</secDNS:dsData>
	 * 			</secDNS:create>
	 *		</extension>
	 * 	</code>.
	 * 
	 * @param document Request completa in fase di costruzione.
	 * @return Elemento di estensione DNSSEC creato.
	 */
	Element buildDnsSecExtension(Document document);
}
