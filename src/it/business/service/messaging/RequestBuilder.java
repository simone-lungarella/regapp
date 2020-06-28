package it.business.service.messaging;

import javax.xml.crypto.dsig.XMLObject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import it.business.dto.DomainDTO;

public class RequestBuilder extends RequestMessageFactory{
	
	
	@Override
	public XMLObject createDomain(DomainDTO domain) {
		XMLObject request = null;
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		 
        DocumentBuilder documentBuilder;
        
		try {
			documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			Element root = buildRootElement(document);
            Element openName = document.createElement("<domain:name>");
            openName.appendChild(document.createTextNode(domain.getDomainName()));
            
			System.out.println(document);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
		return request;
	}

}
