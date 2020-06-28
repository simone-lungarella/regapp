package it.business.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import it.business.dto.ContactDTO;
import it.business.dto.DomainDTO;
import it.business.enums.ContactTypeEnum;
import it.business.service.DomainSRV;
import it.business.service.IDomainSRV;
import it.business.utils.ApplicationContextProvider;

/**
 * @author Simone Lungarella
 * */

@ManagedBean(name = "domainBean")
@ViewScoped
public class DomainBean extends EntityBean{

	private IDomainSRV domainSRV;
	private DomainDTO domain;
	private List<ContactTypeEnum> types;
	private String contactTypeString;
	private List<DomainDTO> searchedDomains;
	private ContactDTO contact;
	
	@PostConstruct
	protected void postConstruct() {
		domainSRV = ApplicationContextProvider.getApplicationContext().getBean(DomainSRV.class);
		loadContactTypes();
		contact = new ContactDTO();
		searchedDomains = domainSRV.findAll();
		
	}

	/**
	 * Questo metodo viene utilizzato per popolare la combobox per la selzione del tipo contatto
	 * */
	private void loadContactTypes() {
		types = new ArrayList<ContactTypeEnum>();
		ContactTypeEnum currType = null;
		if(contactTypeString != null && !contactTypeString.isEmpty()) {
			currType = ContactTypeEnum.valueOf(contactTypeString);
			types.add(currType);
		}
		for (ContactTypeEnum type : ContactTypeEnum.values()) {
			if(type!=currType)
				types.add(type);
		}
	}
	
	public void search() {
		if(contact.getContactId() != null || !contact.getContactId().isEmpty())
			domainSRV.findByRegistrant(contact.getContactId());
		else showInfoMessage("Il codice fiscale deve avere 16 caratteri");
	}
	
	public DomainDTO getDomain() {
		return domain;
	}

	public void setDomain(DomainDTO domain) {
		this.domain = domain;
	}

	public List<ContactTypeEnum> getTypes() {
		return types;
	}

	public void setTypes(List<ContactTypeEnum> types) {
		this.types = types;
	}

	public String getContactTypeString() {
		return contactTypeString;
	}

	public void setContactTypeString(String contactTypeString) {
		this.contactTypeString = contactTypeString;
	}

	public List<DomainDTO> getSearchedDomains() {
		return searchedDomains;
	}

	public void setSearchedDomains(List<DomainDTO> searchedDomains) {
		this.searchedDomains = searchedDomains;
	}

	public ContactDTO getContact() {
		return contact;
	}

	public void setContact(ContactDTO contact) {
		this.contact = contact;
	}
	
	
}
