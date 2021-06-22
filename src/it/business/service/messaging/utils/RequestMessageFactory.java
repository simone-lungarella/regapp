package it.business.service.messaging.utils;

import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import it.business.dto.ContactDTO;
import it.business.dto.DomainDTO;
import it.business.utils.GenericUtils;

/**
 * Factory di creazione Request <em> xml </em> per la comunicazione con il
 * Registro .it tramite protocollo EPP.
 * 
 * @author Simone Lungarella
 */
public abstract class RequestMessageFactory implements MessageFactory {

	/**
	 * Name server.
	 */
	private final String xmlns = "urn:ietf:params:xml:ns:epp-1.0";

	/**
	 * Name server xsi.
	 */
	private final String xmlns_xsi = "http://www.w3.org/2001/XMLSchema-instance";

	/**
	 * Xsi schema location.
	 */
	private final String xsi_schema_location = "xsi:schemaLocation=urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd";

	/**
	 * Name server dominio.
	 */
	private final String xmlns_domain = "urn:ietf:params:xml:ns:domain-1.0";

	/**
	 * Name server dnssec.
	 */
	private final String xmlns_secDNS = "urn:ietf:params:xml:ns:secDNS-1.1";

	/**
	 * Metodo di creazione request di creazione dominio.
	 * 
	 * @param domain   Informazioni sul dominio.
	 * @param contacts Contatti legati al dominio da uno o più contratti.
	 * @return Request XML creata.
	 */
	abstract String createDomain(DomainDTO domain, List<ContactDTO> contacts);

	/**
	 * Costruisce l'elemento root per il <code> document </code>.
	 * 
	 * @param document Documento in fase di costruzione.
	 * @return Elemento root del protocollo EPP.
	 */
	@Override
	public Element buildRootElement(Document document) {
		Element root = document.createElement("epp");
		Attr attr = document.createAttribute("xmlns");
		attr.setValue(StringEscapeUtils.escapeXml(xmlns));
		root.setAttributeNode(attr);
		Attr attr2 = document.createAttribute("xmlns:xsi");
		attr2.setValue(StringEscapeUtils.escapeXml(xmlns_xsi));
		root.setAttributeNode(attr2);
		Attr attr3 = document.createAttribute("xsi:schemaLocation");
		attr3.setValue(StringEscapeUtils.escapeXml(xsi_schema_location));
		root.setAttributeNode(attr3);
		return root;
	}

	/**
	 * Crea e restituisce l'elemento <em> xml </em> per la richiesta di creazione
	 * dominio.
	 * 
	 * @param doc Request in costruzione.
	 * @return Elemento costruito che rispetta il protocollo EPP.
	 */
	@Override
	public Element buildCreateDomainElement(Document doc) {
		Element domainElement = doc.createElement("domain:create");

		Attr attr = doc.createAttribute("xmlns:domain");
		attr.setValue(StringEscapeUtils.escapeXml(xmlns_domain));
		domainElement.setAttributeNode(attr);
		Attr attr2 = doc.createAttribute("xsi:schemaLocation");
		attr2.setValue(StringEscapeUtils.escapeXml(xsi_schema_location));
		domainElement.setAttributeNode(attr2);

		return domainElement;
	}

	/**
	 * Costruisce un elemento generico impostandone il <code> value </code>.
	 * 
	 * @param doc      Request in costruzione.
	 * @param entity   Entity del tag.
	 * @param property Compone il tag se diversa da <code> null </code>.
	 * @param value    Valore da impostare sull'elemento.
	 * @return Elemento creato.
	 */
	@Override
	public Element buildGenericElementWithValue(Document doc, String entity, String property, String value) {
		Element genericElement = doc.createElement(property.isEmpty() ? entity : (entity + ":" + property));
		genericElement.appendChild(doc.createTextNode(value));

		return genericElement;
	}

	/**
	 * Crea e restituisce un elemento per l'estensione DNSSEC che va a completare la
	 * request di creazione dominio.
	 * 
	 * @param doc Request in fase di costruzione.
	 * @return Elemento costruito.
	 */
	@Override
	public Element buildDnsSecExtension(Document doc) {
		Element extension = doc.createElement("extension");
		Element secDNS_create = doc.createElement("secDNS:create");
		Attr attr = doc.createAttribute("xmlns:secDNS");
		attr.setValue(StringEscapeUtils.escapeXml(xmlns_secDNS));
		secDNS_create.setAttributeNode(attr);
		extension.appendChild(secDNS_create);
		Element secDNS_dsData = doc.createElement("secDNS:dsData");
		extension.appendChild(secDNS_dsData);
		Element keyTag = buildGenericElementWithValue(doc, "secDNS", "keyTag", GenericUtils.randomAlphaNumeric(5));
		secDNS_dsData.appendChild(keyTag);
		Element alg = buildGenericElementWithValue(doc, "secDNS", "alg", String.valueOf((Math.random() * 10 / 3) + 1));
		secDNS_dsData.appendChild(alg);
		Element digestType = buildGenericElementWithValue(doc, "secDNS", "digestType",
				String.valueOf((Math.random() * 10 / 3) + 1));
		secDNS_dsData.appendChild(digestType);
		Element digest = buildGenericElementWithValue(doc, "secDNS", "digest", GenericUtils.getDigest());
		secDNS_dsData.appendChild(digest);

		return extension;
	}

}
