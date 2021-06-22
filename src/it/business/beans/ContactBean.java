package it.business.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import it.business.dto.ContactDTO;
import it.business.enums.ContactTypeEnum;
import it.business.service.ContactSRV;
import it.business.service.IContactSRV;
import it.business.utils.ApplicationContextProvider;

/**
 * Bean che gestisce la pagina di visualizzazione, modifica e creazione dei contatti che hanno un contratto
 * in essere con il Registrar in sessione.
 * 
 * @author Simone Lungarella
 */
 @ManagedBean(name = "contactBean")
@ViewScoped
public class ContactBean extends EntityBean{

	/**
	 * Lista degli utenti.
	 */
	private List<ContactDTO> users;

	/**
	 * Lista degli utenti recuperati da una ricerca.
	 */
	private List<ContactDTO> searchedUsers;

	/**
	 * Contatto memorizzato per l'esecuzione di un'azione <em> UNDO </em>.
	 */
	private ContactDTO contactToRollBack;

	/**
	 * Tipologia del contatto come Stringa.
	 */
	private String contactTypeString;

	/**
	 * Contatto in focus.
	 */
	private ContactDTO contact;

	/**
	 * Contatto selezionato.
	 */
	private ContactDTO selectedUser;

	/**
	 * Contatto evidenziato dalla ricerca.
	 */
	private ContactDTO searchedUser;

	/**
	 * Tipologia dei contatti esistenti. @see ContactTypeEnum.
	 */
	private List<ContactTypeEnum> types;

	/**
	 * Consente il <em> toggle </em> del diagramma.
	 */
	private boolean renderedDiagram = false;
	
	/**
	 * Servizio di gestione dei contatti.
	 */
	private IContactSRV contactSRV;


	/**
	 * Inizializza il bean quando viene instanziato. Garantisce il popolamento delle combobox e degli elementi della pagina di gestione dei contatti.
	 */
	@Override
	@PostConstruct
	protected void postConstruct() {
		contactSRV = ApplicationContextProvider.getApplicationContext().getBean(ContactSRV.class);
		loadContactTypes();
		loadUsers();
		searchedUsers = contactSRV.findAll();
		searchedUser = new ContactDTO();
		contact = new ContactDTO();
	}

	/**
	 * Questo metodo viene utilizzato per popolare la combobox per la selezione del tipo contatto
	 */
	private void loadContactTypes() {
		types = new ArrayList<ContactTypeEnum>();
		ContactTypeEnum currType = null;
		if(contactTypeString != null && !contactTypeString.isEmpty()) {
			currType = ContactTypeEnum.valueOf(contactTypeString.toUpperCase());
			types.add(currType);
		}
		for (ContactTypeEnum type : ContactTypeEnum.values()) {
			if(type!=currType)
				types.add(type);
		}
	}

	/**
	 * Consente di rendere persistente il contatto: {@link #contact} in fase di creazione se la validazione viene superata.
	 */
	public void saveContact() {
		if(!contact.getFirstName().isEmpty() && !contact.getLastName().isEmpty() && !contactTypeString.isEmpty() && !contact.getContactId().isEmpty()) {
			if(contact.getContactId().length() == 16) {
				contact= new ContactDTO(contact.getContactId().toUpperCase(), contact.getFirstName(),  contact.getLastName(), ContactTypeEnum.valueOf(contactTypeString.toUpperCase()));
				if(users.contains(contact)) {
					showInfoMessage("Un utente con l'identificativo scelto gi� esiste.");
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
			showInfoMessage("Il nome utente � obbligatorio");
		}else if(contact.getLastName().isEmpty()) {
			showInfoMessage("Il cognome � obbligatorio");
		}else if(contact.getContactId().isEmpty()) {
			showInfoMessage("Il codice fiscale � obbligatorio");
		}else if(contact.getContactId().length()!=16) {
			showInfoMessage("Il codice fiscale deve avere una lunghezza di 16 caratteri");
		}
	}

	/**
	 * Consente di rendere persistenti le modifiche fatte sul contatto selezionato e in fase di modifica che viene identificato dal {@link #contact}.
	 */
	public void saveEditedContact() {
		if(!contact.getFirstName().isEmpty() && !contact.getLastName().isEmpty() && !contactTypeString.isEmpty() && !contact.getContactId().isEmpty()) {
			if(contact.getContactId().length() == 16) {
				contact= new ContactDTO(contact.getContactId().toUpperCase(), contact.getFirstName(),  contact.getLastName(), ContactTypeEnum.valueOf(contactTypeString.toUpperCase()));
				contactSRV.addContact(contact);
				showInfoMessage("Utente modificato correttamente");
			}else showInfoMessage("Il codice fiscale deve avere una lunghezza di 16 caratteri");
			
		}else if(contact.getFirstName().isEmpty()){
			showInfoMessage("Il nome utente � obbligatorio");
			contactSRV.addContact(contactToRollBack);
		}else if(contact.getLastName().isEmpty()) {
			showInfoMessage("Il cognome � obbligatorio");
			contactSRV.addContact(contactToRollBack);
		}else if(contact.getContactId().isEmpty()) {
			showInfoMessage("Il codice fiscale � obbligatorio");
			contactSRV.addContact(contactToRollBack);
		}else if(contact.getContactId().length()!=16) {
			showInfoMessage("Il codice fiscale deve avere una lunghezza di 16 caratteri");
			contactSRV.addContact(contactToRollBack);
		}
	}

	/**
	 * Recupera tutti gli utenti esistenti sullo strato di persistenza.
	 */
	private void loadUsers() {
		users = contactSRV.findAll();
	}
	
	/**
	 * Popola la lista degli utenti che rispettano i parametri della ricerca popolati in maschera dall'utente.
	 * Popola {@link #searchedUsers}.
	 */
	public void loadSearchedUsers() {
		if(!searchedUser.getContactId().isEmpty()) {
			searchedUsers.add(contactSRV.findById(searchedUser.getContactId()));
		}else if(!searchedUser.getFirstName().isEmpty()) {
			searchedUsers = contactSRV.findByFirstName(searchedUser.getFirstName());
		}else if(!searchedUser.getLastName().isEmpty()) {
			searchedUsers = contactSRV.findByLastName(searchedUser.getLastName());
		}else if(ContactTypeEnum.valueOf(contactTypeString.toUpperCase()) != null) {
			searchedUsers = contactSRV.findByContactType(ContactTypeEnum.valueOf(contactTypeString.toUpperCase()));
		}
		
		if(!searchedUsers.isEmpty())
			showInfoMessage("La ricerca ha avuto un esito positivo");
		else
			showInfoMessage("La ricerca ha avuto un esito negativo");
	}

	/**
	 * Aggiorna le informazioni sul contatto in fase di modifica memorizzando la vecchia versione per un'eventuale
	 * <em> undo </em> e aggiorna il contatto modificato.
	 */
	public void editContact() {
		contactToRollBack = new ContactDTO(selectedUser);
		contactSRV.removeContact(selectedUser.getContactId());
		saveEditedContact();
		loadUsers();
	}

	/**
	 * Gestisce la selezione della riga dal datatable dei contatti.
	 * 
	 * @param event Evento di selezione della riga.
	 */
	public void onRowSelect(SelectEvent<?> event) {
		selectedUser = (ContactDTO) event.getObject();
		contact = new ContactDTO(selectedUser);
		contactTypeString = contact.getContactType().toString();
		loadContactTypes();
	}
	
	/**
	 * Consente di rendere visibile o invisibile il diagramma centrale in accordo con il flag: {@link #renderedDiagram}.
	 */
	public void toggleDiagram() {
		if(isRenderedDiagram())
			setRenderedDiagram(false);
		else
			setRenderedDiagram(true);
		System.out.println(renderedDiagram);
	}

	/**
	 * Restituisce il servizio di gestione dei contatti.
	 * 
	 * @return Servizio per la gestione dei contatti.
	 */
	public IContactSRV getContactSRV() {
		return contactSRV;
	}

	/**
	 * Imposta il servizio di gestione dei contatti.
	 * 
	 * @param contactSRV Servizio gestione contatti da impostare.
	 */
	public void setContactSRV(IContactSRV contactSRV) {
		this.contactSRV = contactSRV;
	}

	/**
	 * Restituisce il contatto in focus.
	 * 
	 * @return Contatto in focus che può essere un contatto in fase di salvataggio o modifica o ricerca.
	 */
	public ContactDTO getContact() {
		return contact;
	}

	/**
	 * Imposta il contatto in focus.
	 * 
	 * @param contact
	 */
	public void setContact(ContactDTO contact) {
		this.contact = contact;
	}

	/**
	 * Restituisce la tipologia del contatto come stringa.
	 * 
	 * @return Tipologia del contatto in focus.
	 */
	public String getContactTypeString() {
		return contactTypeString;
	}

	/**
	 * Imposta la tipologia del contatto in focus come stringa.
	 * 
	 * @param contactTypeString Tipologia del contatto da impostare.
	 */
	public void setContactTypeString(String contactTypeString) {
		this.contactTypeString = contactTypeString;
	}

	/**
	 * Restituisce la lista delle tipologia di contatti esistenti.
	 * 
	 * @return Lista delle tipologia contatti.
	 */
	public List<ContactTypeEnum> getTypes() {
		return types;
	}

	/**
	 * Imposta la lista delle tipologia di contatti esistenti.
	 * 
	 * @param types Lista tipologia contatti da impostare.
	 */
	public void setTypes(List<ContactTypeEnum> types) {
		this.types = types;
	}

	/**
	 * Restituisce tutti gli utenti esistenti che hanno un rapporto con il <em> Registrar </em>.
	 * 
	 * @return Utenti esistenti sulla base dati.
	 */
	public List<ContactDTO> getUsers() {
		return users;
	}

	/**
	 * Imposta la lista degli utenti esistenti che hanno un rapporto con il <em> Registrar </em>.
	 * 
	 * @param users Lista utenti da impostare.
	 */
	public void setUsers(List<ContactDTO> users) {
		this.users = users;
	}

	/**
	 * Restituisce il contatto selezionato dall'utente in sessione.
	 * 
	 * @return Contatto selezionato o <code> null </code> se nessun contatto è stato selezionato.
	 */
	public ContactDTO getSelectedUser() {
		return selectedUser;
	}

	/**
	 * Imposta il contatto selezionato dall'utente in sessione.
	 * 
	 * @param selectedUser Utente selezionato.
	 */
	public void setSelectedUser(ContactDTO selectedUser) {
		this.selectedUser = selectedUser;
	}

	/**
	 * Restituisce la lista degli utenti fornita dall'esecuzione di un ricerca.
	 * 
	 * @return Lista dei contatti ottenuti dalla ricerca, è vuota se la ricerca non ha prodotto risultati o non è mai stata eseguita.
	 */
	public List<ContactDTO> getSearchedUsers() {
		return searchedUsers;
	}

	/**
	 * Imposta la lista degli utenti ottenuti in fase di ricerca.
	 * 
	 * @param searchedUsers Lista dei contatti ottenuti dalla ricerca o una lista vuota se la ricerca non ha prodotto risultati.
	 */
	public void setSearchedUsers(List<ContactDTO> searchedUsers) {
		this.searchedUsers = searchedUsers;
	}

	/**
	 * Restituisce il contatto ricercato.
	 * 
	 * @return Contatto ricercato ottenuto o <code> null </code> se la ricerca non è stata eseguita o non ha prodotto risultati.
	 */
	public ContactDTO getSearchedUser() {
		return searchedUser;
	}

	/**
	 * Imposta il contatto ricercato.
	 * 
	 * @param searchedUser Contatto ottenuto dalla ricerca da impostare.
	 */
	public void setSearchedUser(ContactDTO searchedUser) {
		this.searchedUser = searchedUser;
	}

	/**
	 * Restituisce <code> true </em> se occorre rendere visibile il diagramma, 
	 * restituisce <code> false </code> se il diagramma deve essere nascosto dalla sessione.
	 * @return
	 */
	public boolean isRenderedDiagram() {
		return renderedDiagram;
	}

	/**
	 * Imposta la visibilità del diagramma.
	 * 
	 * @param renderedDiagram Flag associato alla visibilità del diagramma.
	 */
	public void setRenderedDiagram(boolean renderedDiagram) {
		this.renderedDiagram = renderedDiagram;
	}

	/**
	 * Restituisce il contatto memorizzato per l'azione di <em> undo </em> se esistente.
	 * Restituisce <code> null </code> se non è stato memorizzato alcun utente.
	 * 
	 * @return Contatto memorizzato prima di una modifica per consentire l'azione di <em> undo </em> all'utente in sessione.
	 */
	public ContactDTO getContactToRollBack() {
		return contactToRollBack;
	}

	/**
	 * Imposta il contatto da memorizzare per un'eventuale azione di <em> undo </em>.
	 * 
	 * @param contactToRollBack Contatto da memorizzare.
	 */
	public void setContactToRollBack(ContactDTO contactToRollBack) {
		this.contactToRollBack = contactToRollBack;
	}

}
