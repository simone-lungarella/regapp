package it.business.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import it.business.dto.ContactDTO;
import it.business.dto.DomainDTO;
import it.business.enums.ContactTypeEnum;
import it.business.service.DomainSRV;
import it.business.service.IContactSRV;
import it.business.service.ContactSRV;
import it.business.service.IDomainSRV;
import it.business.utils.ApplicationContextProvider;

/**
 * @author Simone Lungarella
 * */

@ManagedBean(name = "domainBean")
@ViewScoped
public class DomainBean extends EntityBean{

	private IDomainSRV domainSRV;
	private IContactSRV contactSRV;
	private DomainDTO domain;
	private List<ContactTypeEnum> types;
	private String contactTypeString;
	private List<DomainDTO> searchedDomains;
	private ContactDTO contact;
	private List<ContactDTO> registrants;
	private List<ContactDTO> admins;
	private ContactDTO registrant;
	private ContactDTO admin;
	
	@PostConstruct
	protected void postConstruct() {
		domainSRV = ApplicationContextProvider.getApplicationContext().getBean(DomainSRV.class);
		contactSRV = ApplicationContextProvider.getApplicationContext().getBean(ContactSRV.class);
		loadContactTypes();
		loadRegistrants();
		loadAdmins();
		contact = new ContactDTO();
		domain = new DomainDTO();
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
	
	public void loadRegistrants() {
		setRegistrants(contactSRV.findByContactType(ContactTypeEnum.REGISTRANT));
	}
	
	public void loadAdmins() {
		setAdmins(contactSRV.findByContactType(ContactTypeEnum.ADMIN));
	}
	
	public void search() {
		if(contact.getContactId() != null || !contact.getContactId().isEmpty())
			domainSRV.findByRegistrant(contact.getContactId());
		else showInfoMessage("Il codice fiscale deve avere 16 caratteri");
	}
	
	public void onRegistrantSelect(SelectEvent event) {
		setRegistrant((ContactDTO) event.getObject());
//		PrimeFaces pf = PrimeFaces.current();
//		if (pf.isAjaxRequest()) {
//		    pf.ajax().update("pickRegistrantDialog");
//		}
	}
	
	public void createDomain() {
		if(domain.getDomainName() == null || domain.getDomainName().length() == 0) {
			showInfoMessage("Non è possibile creare un dominio senza nome");
		}else {
			domain.setAdmin(admin.getContactId());
			domain.setRegistrant(registrant.getContactId());
			domainSRV.addDomain(domain);
		}
			
	}
	
	public void onAdminSelect(SelectEvent event) {
		setAdmin((ContactDTO) event.getObject());
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

	public List<ContactDTO> getRegistrants() {
		return registrants;
	}

	public void setRegistrants(List<ContactDTO> registrants) {
		this.registrants = registrants;
	}

	public List<ContactDTO> getAdmins() {
		return admins;
	}

	public void setAdmins(List<ContactDTO> admins) {
		this.admins = admins;
	}

	public ContactDTO getRegistrant() {
		return registrant;
	}

	public void setRegistrant(ContactDTO registrant) {
		this.registrant = registrant;
	}

	public ContactDTO getAdmin() {
		return admin;
	}

	public void setAdmin(ContactDTO admin) {
		this.admin = admin;
	}

	
	
}
