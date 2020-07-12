package it.business.service.messaging;

import java.util.List;

import it.business.dto.DomainDTO;

/**
 * @author Simone Lungarella
 * */

public interface IXmlRequestGeneratorSRV extends IXMLGeneratorSRV{
	
	String getCreateDomainXmlRequest(DomainDTO domain, List<String> contactIds);
	
	String getCreateDomainXmlRequest(DomainDTO domain, String... contactIds);
	
}
