package it.business.service.messaging.utils;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import it.business.dto.ContactDTO;
import it.business.dto.DomainDTO;
import it.business.enums.ContactTypeEnum;
import it.business.utils.GenericUtils;

/**
 * Builder delle Request che rispettano il protocollo EPP per la comunicazione
 * di un Registrar con il Registro .it.
 * 
 * @author Simone Lungarella
 */
public class RequestBuilder extends RequestMessageFactory {

	/**
	 * Crea una request per la richiesta di creazione di un dominio rispettando il seguento formato:
	 * <code>
	 * 		<?xml version="1.0" encoding="UTF-8" standalone="no"?> <epp
	 * 		xmlns="urn:ietf:params:xml:ns:epp-1.0"
	 * 		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	 * 		xsi:schemaLocation="urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd"> <command>
	 * 		<create> <domain:create xmlns:domain="urn:ietf:params:xml:ns:domain-1.0"
	 * 		xsi:schemaLocation="urn:ietf:params:xml:ns:domain-1.0 domain-1.0.xsd">
	 * 		<domain:name>esempio.it</domain:name> <domain:period 
	 * 		unit="y">1</domain:period> <domain:ns> <domain:hostAttr>
	 * 		<domain:hostName>ns3174.dns.dyn.com</domain:hostName> </domain:hostAttr>
	 * 		<domain:hostAttr> <domain:hostName>ns3174.dns.dyn.com</domain:hostName>
	 * 		</domain:hostAttr> </domain:ns> <domain:registrant>mm001</domain:registrant>
	 * 		<domain:contact type="admin">mm001</domain:contact> <domain:contact
	 * 		type="tech">mb001</domain:contact> <domain:authInfo>
	 * 		<domain:pw>22fooBAR</domain:pw> </domain:authInfo> </domain:create> </create>
	 * 		<clTRID>ABC-12345</clTRID> </command> </epp>
	 * </code>.
	 */
	@Override
	public String createDomain(DomainDTO domain, List<ContactDTO> contacts) {
		String request = "";
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

		// Inizializzazione dei contatti utili alla creazione della request
		ContactDTO registrant = new ContactDTO();
		List<ContactDTO> otherContacts = new ArrayList<>();
		for (ContactDTO c : contacts) {
			if (c.getContactType().equals(ContactTypeEnum.REGISTRANT)) {
				registrant = new ContactDTO(c);
			} else {
				otherContacts.add(c);
			}
		}

		DocumentBuilder documentBuilder;

		try {
			documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			Element root = buildRootElement(document);
			document.appendChild(root);
			Element command = document.createElement("command");
			root.appendChild(command);
			Element create = document.createElement("create");
			command.appendChild(create);
			Element cmdCreate = buildCreateDomainElement(document);
			create.appendChild(cmdCreate); // Fin qui tutto ok
			Element name = buildGenericElementWithValue(document, "domain", "name", domain.getDomainName());
			cmdCreate.appendChild(name);
			Element period = buildGenericElementWithValue(document, "domain", "period",
					String.valueOf((Math.random() * 10) + 1));
			cmdCreate.appendChild(period);
			Element domain_ns = document.createElement("domain:ns");
			cmdCreate.appendChild(domain_ns);
			Element host_attr = document.createElement("domain:hostAttr");
			domain_ns.appendChild(host_attr);

			// Genero alcuni hostname per la gestione del dominio
			int randomBound = (int) (Math.round(Math.random() * 10 / 3 + 1));
			for (int i = 1; i <= randomBound; i++) {
				domain_ns.appendChild(buildGenericElementWithValue(document, "domain", "hostname",
						"ns" + String.valueOf((Math.random() * 1000) + 1) + ".dns.dyn.com"));
			}
			Element hostname = buildGenericElementWithValue(document, "domain", "registrant",
					registrant.getContactId());
			domain_ns.appendChild(hostname);

			// Popolo i tag contenenti le informazioni sugli admin e sui tech del dominio
			for (ContactDTO c : otherContacts) {
				domain_ns.appendChild(buildGenericElementWithValue(document, "domain",
						c.getContactType().toString().toLowerCase(), c.getContactId()));
			}
			Element authInfo = document.createElement("domain:authInfo");
			cmdCreate.appendChild(authInfo);
			Element pw = buildGenericElementWithValue(document, "domain", "pw", GenericUtils.randomAlphaNumeric(8));
			authInfo.appendChild(pw);

			// Se il dominio prevede l'estensione di sicurezza, la request deve prevedere un
			// tag di estensione
			if (domain.isDnssec()) {
				Element dnssecExtension = buildDnsSecExtension(document);
				command.appendChild(dnssecExtension);

			}

			// Trasformazione in stringa dell'XML generato
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(document), new StreamResult(writer));
			request = writer.getBuffer().toString().replaceAll(">", ">\n");

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		return request;
	}

}
