package it.business.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import it.business.dto.ContactDTO;
import it.business.enums.ContactTypeEnum;
import it.business.service.ContactSRV;
import it.business.service.IContactSRV;
import it.business.utils.ApplicationContextProvider;

/**
 * @author Simone Lungarella
 * */

//@Named("contactBean")
@ManagedBean(name = "contactBean")
@ViewScoped
public class ContactBean {

	private List<ContactDTO> users;
	private List<ContactDTO> searchedUsers;
	private ContactDTO contactToRollBack;
	private String contactTypeString;
	private ContactDTO contact;
	private ContactDTO selectedUser;
	private ContactDTO searchedUser;
	private List<ContactTypeEnum> types;
	private boolean renderedDiagram = false;
	
	private IContactSRV contactSRV;

	@PostConstruct
	protected void postConstruct() {
		contactSRV = ApplicationContextProvider.getApplicationContext().getBean(ContactSRV.class);
		loadContactTypes();
		loadUsers();
		searchedUsers = new ArrayList<>();
		searchedUser = new ContactDTO();
		contact = new ContactDTO();
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

	public void saveContact() {
		if(!contact.getFirstName().isEmpty() && !contact.getLastName().isEmpty() && !contactTypeString.isEmpty() && !contact.getContactId().isEmpty()) {
			if(contact.getContactId().length() == 16) {
				contact= new ContactDTO(contact.getContactId().toUpperCase(), contact.getFirstName(),  contact.getLastName(), ContactTypeEnum.valueOf(contactTypeString.toUpperCase()));
				if(users.contains(contact)) {
					showInfoMessage("Un utente con l'identificativo scelto già esiste.");
				}else {
					contactSRV.addContact(contact);
					showInfoMessage("Utente creato con successo");
					loadUsers();
				}
			}else {
				showInfoMessage("Il codice fiscale deve avere una lunghezza di 16 caratteri");
			}
		}
		else if(contact.getFirstName().isEmpty()){
			showInfoMessage("Il nome utente è obbligatorio");
		}else if(contact.getLastName().isEmpty()) {
			showInfoMessage("Il cognome è obbligatorio");
		}else if(contact.getContactId().isEmpty()) {
			showInfoMessage("Il codice fiscale è obbligatorio");
		}else if(contact.getContactId().length()!=16) {
			showInfoMessage("Il codice fiscale deve avere una lunghezza di 16 caratteri");
		}
	}
	
	public void saveEditedContact() {
		if(!contact.getFirstName().isEmpty() && !contact.getLastName().isEmpty() && !contactTypeString.isEmpty() && !contact.getContactId().isEmpty()) {
			if(contact.getContactId().length() == 16) {
				contact= new ContactDTO(contact.getContactId().toUpperCase(), contact.getFirstName(),  contact.getLastName(), ContactTypeEnum.valueOf(contactTypeString.toUpperCase()));
				contactSRV.addContact(contact);
				showInfoMessage("Utente modificato correttamente");
			}else showInfoMessage("Il codice fiscale deve avere una lunghezza di 16 caratteri");
			
		}else if(contact.getFirstName().isEmpty()){
			showInfoMessage("Il nome utente è obbligatorio");
			contactSRV.addContact(contactToRollBack);
		}else if(contact.getLastName().isEmpty()) {
			showInfoMessage("Il cognome è obbligatorio");
			contactSRV.addContact(contactToRollBack);
		}else if(contact.getContactId().isEmpty()) {
			showInfoMessage("Il codice fiscale è obbligatorio");
			contactSRV.addContact(contactToRollBack);
		}else if(contact.getContactId().length()!=16) {
			showInfoMessage("Il codice fiscale deve avere una lunghezza di 16 caratteri");
			contactSRV.addContact(contactToRollBack);
		}
	}
	
	private void loadUsers() {
		users = contactSRV.findAll();
	}
	
	public void loadSearchedUsers() {
		if(searchedUser.getContactId() != "") {
			searchedUsers.add(contactSRV.findById(searchedUser.getContactId()));
		}else if(searchedUser.getFirstName() != "") {
			searchedUsers = contactSRV.findByFirstName(searchedUser.getFirstName());
		}else if(searchedUser.getLastName() != "") {
			searchedUsers = contactSRV.findByLastName(searchedUser.getLastName());
		}else if(ContactTypeEnum.valueOf(contactTypeString) != null) {
			searchedUsers = contactSRV.findByContactType(ContactTypeEnum.valueOf(contactTypeString.toUpperCase()));
		}
	}
	
	public void print()	{
		System.out.println("print");
	}
	
	public void editContact() {
		contactToRollBack = new ContactDTO(selectedUser);
		contactSRV.removeContact(selectedUser.getContactId());
		saveEditedContact();
		loadUsers();
	}
	
	public void onRowSelect(SelectEvent event) {
		selectedUser = (ContactDTO) event.getObject();
//		selectedUser.setContactType(ContactTypeEnum.valueOf(contactTypeString.toUpperCase()));
		contact = new ContactDTO(selectedUser);
		contactTypeString = contact.getContactType().toString();
		loadContactTypes();
	}
	
	/**
	 * Metodo di utility per la renderizzazione di messaggi informativi per l'utente
	 * */
	protected final void showInfoMessage(final String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", msg));
	}
	
	public void toggleDiagram() {
		if(isRenderedDiagram())
			setRenderedDiagram(false);
		else
			setRenderedDiagram(true);
		System.out.println(renderedDiagram);
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

	public List<ContactDTO> getSearchedUsers() {
		return searchedUsers;
	}

	public void setSearchedUsers(List<ContactDTO> searchedUsers) {
		this.searchedUsers = searchedUsers;
	}

	public ContactDTO getSearchedUser() {
		return searchedUser;
	}

	public void setSearchedUser(ContactDTO searchedUser) {
		this.searchedUser = searchedUser;
	}

	public boolean isRenderedDiagram() {
		return renderedDiagram;
	}

	public void setRenderedDiagram(boolean renderedDiagram) {
		this.renderedDiagram = renderedDiagram;
	}

	public ContactDTO getContactToRollBack() {
		return contactToRollBack;
	}

	public void setContactToRollBack(ContactDTO contactToRollBack) {
		this.contactToRollBack = contactToRollBack;
	}

}
