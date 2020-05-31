package it.business.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import it.business.dto.ContactDTO;
import it.business.enums.ContactTypeEnum;
import it.business.service.ContactSRV;
import it.business.service.IContactSRV;
import it.business.utils.ApplicationContextProvider;

/**
 * @author Simone Lungarella
 * */

@ManagedBean(name = "contactBean")
@ViewScoped
public class ContactBean {

	private String contactId;
	private String firstName;
	private String lastName;
	private List<ContactDTO> users;
	
	private String contactTypeString;
	private ContactDTO contact;
	private ContactDTO selectedUser;
	private List<ContactTypeEnum> types;

	private IContactSRV contactSRV;

	@PostConstruct
	protected void postConstruct() {
		contactSRV = ApplicationContextProvider.getApplicationContext().getBean(ContactSRV.class);
		loadContactTypes();
		loadUsers();
	}

	/**
	 * Questo metodo viene utilizzato per popolare la combobox per la selzione del tipo contatto
	 * */
	private void loadContactTypes() {
		types = new ArrayList<ContactTypeEnum>();
		for (ContactTypeEnum type : ContactTypeEnum.values()) {
			types.add(type);
		}
	}

	public void saveContact() {
		if(firstName != null && lastName != null && contactTypeString!= null && contactId != null) {
			contact= new ContactDTO(contactId.toUpperCase(), firstName, lastName, ContactTypeEnum.valueOf(contactTypeString.toUpperCase()));
			if(users.contains(contact)) {
				showInfoMessage("Un utente con l'identificativo scelto già esiste.");
			}else {
				contactSRV.addContact(contact);
				showInfoMessage("Utente creato con successo");
			}
		}
		else if(firstName.isEmpty()){
			showInfoMessage("Il nome utente è obbligatorio");
		}else if(lastName.isEmpty()) {
			showInfoMessage("Il cognome è obbligatorio");
		}else if(contactId.isEmpty()) {
			showInfoMessage("Il codice fiscale è obbligatorio");
		}else if(contactId.length()!=16) {
			showInfoMessage("Il codice fiscale deve avere una lunghezza di 16 caratteri");
		}
	}
	
	private void loadUsers() {
		users = contactSRV.findAll();
	}
	
	private void editContact() {
		//TODO: completare metodo
	}
	
	/**
	 * Metodo di utility per la renderizzazione di messaggi informativi per l'utente
	 * */
	protected final void showInfoMessage(final String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", msg));
	}
	
	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public IContactSRV getContactSRV() {
		return contactSRV;
	}

	public void setContactSRV(IContactSRV contactSRV) {
		this.contactSRV = contactSRV;
	}

	public ContactDTO getContact() {
		return contact;
	}

	public void setContact(ContactDTO contact) {
		this.contact = contact;
	}

	public void setContact(String id, String firstName, String lastName, ContactTypeEnum contactType) {
		this.contact.setContactId(id);
		this.contact.setFirstName(firstName);
		this.contact.setLastName(lastName);
		this.contact.setContactType(contactType);
	}

	public String getContactTypeString() {
		return contactTypeString;
	}

	public void setContactTypeString(String contactTypeString) {
		this.contactTypeString = contactTypeString;
	}

	public List<ContactTypeEnum> getTypes() {
		return types;
	}

	public void setTypes(List<ContactTypeEnum> types) {
		this.types = types;
	}

	public List<ContactDTO> getUsers() {
		return users;
	}

	public void setUsers(List<ContactDTO> users) {
		this.users = users;
	}

	public ContactDTO getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(ContactDTO selectedUser) {
		this.selectedUser = selectedUser;
	}
	
}
