package it.business.service.messaging;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import it.business.dto.ContactDTO;
import it.business.dto.DomainDTO;
import it.business.service.AbstractService;
import it.business.service.IContactSRV;
import it.business.service.messaging.utils.RequestBuilder;

/**
 * @author Simone Lungarella
 * */

@Service
@Component
public class XmlRequestGeneratorSRV extends AbstractService implements IXmlRequestGeneratorSRV{

	private static final long serialVersionUID = 8931171031798205976L;
	
	@Autowired
	private IContactSRV contactSRV;

	@Override
	public String getCreateDomainXmlRequest(DomainDTO domain, List<String> contactIds) {
		String xml = "";
		List<ContactDTO> contacts = new ArrayList<>();

		try {
			for (String s : contactIds) {
				contacts.add(contactSRV.findById(s));
			}

			RequestBuilder reqBuilder = new RequestBuilder();
			xml = reqBuilder.createDomain(domain, contacts);
		} catch (Exception e) {
			System.out.print("Errore riscontrato nel service XmlGeneratorSRV: ");
			e.printStackTrace();
		}

		return xml;
	}
	
	@Override
	public String getCreateDomainXmlRequest(DomainDTO domain, String... contactIds) {
		List<String> ids = new ArrayList<>();
		for(String id : contactIds) {
			ids.add(id);
		}
		
		return getCreateDomainXmlRequest(domain, ids);
	}
	
}
