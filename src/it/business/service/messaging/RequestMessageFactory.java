package it.business.service.messaging;

import javax.xml.crypto.dsig.XMLObject;

import org.apache.commons.lang.StringEscapeUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import it.business.dto.DomainDTO;

/**
 * @author Simone Lungarella
 * */

public abstract class RequestMessageFactory {
	/**
	 * Le stringhe utilizzate negli attributi degli elementi principali delle request
	 * */
	private final String xmlns="urn:ietf:params:xml:ns:epp-1.0";
	private final String xmlns_xsi="http://www.w3.org/2001/XMLSchema-instance";
	private final String xsi_schema_location="xsi:schemaLocation=urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd";
	
	private final String xmlns_domain ="urn:ietf:params:xml:ns:domain-1.0"; 
	
	abstract XMLObject createDomain(DomainDTO domain);
	
	/**
	 * Genera l'elemento root per una qualsiasi richiesta epp
	 * */
	public Element buildRootElement(Document document) {
		Element root = document.createElement("epp");
		document.appendChild(root);
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
	
	public Element buildCreateDomainElement(Document doc, Element parentNode) {
		Element domainElement = doc.createElement("domain:create");
		parentNode.appendChild(domainElement);
		
		Attr attr = doc.createAttribute("xmlns:domain");
		attr.setValue(StringEscapeUtils.escapeXml(xmlns_domain));
		domainElement.setAttributeNode(attr);
		Attr attr2 = doc.createAttribute("xsi:schemaLocation");
		attr2.setValue(StringEscapeUtils.escapeXml(xsi_schema_location));
		domainElement.setAttributeNode(attr2);
		
		return domainElement;
	}
}
