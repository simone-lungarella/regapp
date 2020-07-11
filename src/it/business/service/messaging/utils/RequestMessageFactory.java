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
 * @author Simone Lungarella
 * */

public abstract class RequestMessageFactory implements MessageFactory{
	/**
	 * Le stringhe utilizzate negli attributi degli elementi principali delle request
	 * */
	private final String xmlns="urn:ietf:params:xml:ns:epp-1.0";
	private final String xmlns_xsi="http://www.w3.org/2001/XMLSchema-instance";
	private final String xsi_schema_location="xsi:schemaLocation=urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd";
	
	private final String xmlns_domain="urn:ietf:params:xml:ns:domain-1.0"; 
	private final String xmlns_secDNS="urn:ietf:params:xml:ns:secDNS-1.1";
//	private final String xmlns_extsecDNS="http://www.nic.it/ITNIC-EPP/extsecDNS-1.0";
	
	abstract String createDomain(DomainDTO domain, List<ContactDTO> contacts);
	
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
	
	@Override
	public Element buildGenericElementWithValue(Document doc, String entity, String property, String value) {
		// Se property è una stringa vuota allora il tag non sarà costituito da due parole ma solo dall'entità. Questo permette di avere un solo metodo
		// per gestire tutti i tipi generici di nodi
		Element genericElement = doc.createElement(property.isEmpty() ? entity : (entity+":"+property));
		genericElement.appendChild(doc.createTextNode(value));
		
		return genericElement;
	}
	
	/**
	 *	L'estensione di sicurezza prevede il seguente formato
	 *	<extension>
	 * 		<secDNS:create xmlns:secDNS="urn:ietf:params:xml:ns:secDNS-1.1">
	 * 		<secDNS:dsData>
	 *  		<secDNS:keyTag>12345</secDNS:keyTag>
	 * 			<secDNS:alg>3</secDNS:alg>
	 *			<secDNS:digestType>1</secDNS:digestType>
	 * 			<secDNS:digest>4347d0f8ba661234a8eadc005e2e1d1b646c9682</secDNS:digest>
	 * 		</secDNS:dsData>
	 * 		</secDNS:create>
	 *	</extension>
	 *
	 * */
	
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
		Element alg = buildGenericElementWithValue(doc, "secDNS", "alg", String.valueOf((Math.random()*10/3)+1));
		secDNS_dsData.appendChild(alg);
		Element digestType = buildGenericElementWithValue(doc, "secDNS", "digestType", String.valueOf((Math.random()*10/3)+1));
		secDNS_dsData.appendChild(digestType);
		Element digest = buildGenericElementWithValue(doc, "secDNS", "digest", GenericUtils.randomAlphaNumeric(40).toLowerCase());
		secDNS_dsData.appendChild(digest);
		
		return extension;
	}
	
	
	
	
	
}
