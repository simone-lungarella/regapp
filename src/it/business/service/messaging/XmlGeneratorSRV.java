package it.business.service.messaging;

import org.springframework.beans.factory.annotation.Autowired;

import it.business.service.AbstractService;
import it.business.service.IContractSRV;
import it.business.service.IDomainSRV;

public class XmlGeneratorSRV extends AbstractService{

	@Autowired
	private IContractSRV contractSRV;
	
	@Autowired
	private IDomainSRV domainSRV;
	
	private static final long serialVersionUID = 8931171031798205976L;

	public String getCreateDomainXmlRequest() {
		String xml = "";
		
//		contractSRV.fin
		
		return xml;
	}
}
