package it.business.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import it.business.dto.ContactDTO;
import it.business.dto.DomainDTO;
import it.business.enums.ContactTypeEnum;
import it.business.service.ContactSRV;
import it.business.service.DomainSRV;
import it.business.service.IContactSRV;
import it.business.service.IDomainSRV;
import it.business.service.messaging.IXmlRequestGeneratorSRV;
import it.business.service.messaging.XmlRequestGeneratorSRV;
import it.business.utils.ApplicationContextProvider;

/**
 * Bean che si occupa della gestione della pagina di visualizzazione, modifica e creazione dei domini 
 * gestiti dal Registrar in sessione.
 * 
 * @author Simone Lungarella
 */
@ManagedBean(name = "domainBean")
@ViewScoped
public class DomainBean extends EntityBean{

	/**
	 * Dominio in focus.
	 */
	private DomainDTO domain;

	/**
	 * Tipologia dei contatti per il popolamento della combobx.
	 */
	private List<ContactTypeEnum> types;

	/**
	 * Tipologia del contatto in focus sotto forma di String.
	 */
	private String contactTypeString;

	/**
	 * Lista dei risultati dei domini ricercati.
	 */
	private List<DomainDTO> searchedDomains;

	/**
	 * Contatto in focus.
	 */
	private ContactDTO contact;

	/**
	 * Lista dei contatti di tipo Registrant associati al dominio selezionato o in focus.
	 */
	private List<ContactDTO> registrants;

	/**
	 * Lista dei contatti amministratori associati al dominio selezionato o in focus.
	 */
	private List<ContactDTO> admins;

	/**
	 * Contatto Registrant principale del dominio selezionato o in focus.
	 */
	private ContactDTO registrant;

	/**
	 * Contatto Admin principale del dominio selezionato o in focus.
	 */
	private ContactDTO admin;

	/**
	 * Request <em> xml </em> per la visualizzazione di un Request di creazione dominio.
	 */
	private String createRequest;

	/**
	 * Lista di domini temporanea.
	 */
	private List<DomainDTO> tempDomains;

	/**
	 * Flag associato all'esistenza di una Request.
	 */
	private boolean existingRequest;

	/**
	 * Dominio in fase di verifca.
	 */
	private String domainToVerify;
	
	/**
	 * Servizio di gestione dei domini.
	 */
	private IDomainSRV domainSRV;

	/**
	 * Servizio di gestione dei contatti associati al dominio.
	 */
	private IContactSRV contactSRV;

	/**
	 * Servizio di creazione Request.
	 */
	private IXmlRequestGeneratorSRV xmlGenerator;
	
	/**
	 * Variabile boolean che comunica lo stato del nome di dominio in fase di ricerca per un utilizzo (VERIFICA).
	 * */
	private boolean free;

	/**
	 * Variabile boolean che comunica lo stato del nome di dominio in fase di ricerca per un utilizzo (VERIFICA).
	 * */
	private boolean NotFree;
	
	/**
	 * Inizializza il bean per la gestione dei domini.
	 */
	@Override
	@PostConstruct
	protected void postConstruct() {
		setExistingRequest(false);
		domainSRV = ApplicationContextProvider.getApplicationContext().getBean(DomainSRV.class);
		contactSRV = ApplicationContextProvider.getApplicationContext().getBean(ContactSRV.class);
		xmlGenerator = ApplicationContextProvider.getApplicationContext().getBean(XmlRequestGeneratorSRV.class);
		loadContactTypes();
		loadRegistrants();
		loadAdmins();
		contact = new ContactDTO();
		domain = new DomainDTO();
		searchedDomains = domainSRV.findAll();
		tempDomains = new ArrayList<>();
	}

	/**
	 * Questo metodo viene utilizzato per il caricamento delle tipologia dei contatti per popolare la combobox per la selzione del tipo contatto.
	 */
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
	
	/**
	 * Esegue la ricerca di tutti i contatti di tipo <em> Registrant </em> valorizzando {@link #registrants}.
	 */
	public void loadRegistrants() {
		setRegistrants(contactSRV.findByContactType(ContactTypeEnum.REGISTRANT));
	}
	
	/**
	 * Esegue la ricerca di tutti i contatti di tipo <em> Admin </em> valorizzando {@link #admins}.
	 */
	public void loadAdmins() {
		setAdmins(contactSRV.findByContactType(ContactTypeEnum.ADMIN));
	}

	/**
	 * Esegue la ricerca del dominio sulla base delle informazioni del contatto.
	 */
	public void search() {
		if(contact.getContactId() != null || !contact.getContactId().isEmpty()) {
			setSearchedDomains(domainSRV.findByRegistrant(contact.getContactId()));
		}
		else {
			showInfoMessage("Il codice fiscale deve avere 16 caratteri");
		} 
	}
	
	/**
	 * Gestisce la selezione di una riga dal datatable dei Registrant.
	 * 
	 * @param event Evento di selezione di un Registrant.
	 */
	public void onRegistrantSelect(SelectEvent<?> event) {
		setRegistrant((ContactDTO) event.getObject());
		
	}
	
	/**
	 * Gestisce la validazione delle informazioni su un dominio e se viene superata rende persistenti le nuove informazioni
	 * creando un nuovo dominio.
	 */
	public void createDomain() {
		if(domain.getDomainName() == null || domain.getDomainName().length() == 0) {
			showInfoMessage("Non è possibile creare un dominio senza nome");
		}else {
			domain.setDomainName("www." + domain.getDomainName() + ".it");
			generateCreateRequest();
			domain.setAdmin(admin.getContactId());
			domain.setRegistrant(registrant.getContactId());
			domainSRV.addDomain(domain);
		}
	}
	
	/**
	 * Genera una request di crazione dominio con i parametri impostati dall'utente in sessione.
	 */
	public void generateCreateRequest() {
		if(getRegistrant() != null && getAdmin() != null && domain != null) {
			try {
				String result = null;
				result = xmlGenerator.getCreateDomainXmlRequest(domain, registrant.getContactId(), admin.getContactId());
				if(result!=null) {
					setCreateRequest(result);
					setExistingRequest(true);
					showInfoMessage("Request creata correttamente");
				} else {
					showInfoMessage("Si è verificato un problema nella creazione della request");
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
				showInfoMessage("La richiesta non è formalmente corretta");
			}
			 	
		} else {
			showInfoMessage("I parametri non sono stati scelti correttamente");
		}
	}

	/**
	 * Consente di ottenere informazioni su un determinato dominio se già registrato.
	 * Simula la funzionalità WHOIS di un servizio DNS.
	 * */
	public void verifica() {
		DomainDTO temp = domainSRV.findByDomainName("www." + getDomainToVerify() + ".it");
		if(temp.getDomainName().isEmpty() || temp == null) {
			setTempDomains(new ArrayList<>());
			setFree(true);
			setNotFree(false);
		} else {
			tempDomains = new ArrayList<>();
			tempDomains.add(temp);
			setTempDomains(tempDomains);
			setFree(false);
			setNotFree(true);
		}
	}
	
	/**
	 * Gestisce la selezione di una riga dal datatable degli amministratori di un dominio.
	 * 
	 * @param event Evento di selezione riga.
	 */
	public void onAdminSelect(SelectEvent<?> event) {
		setAdmin((ContactDTO) event.getObject());
	}
	
	/**
	 * Restituisce il dominio in focus.
	 * 
	 * @return Dominio in focus.
	 */
	public DomainDTO getDomain() {
		return domain;
	}

	/**
	 * Imposta il dominio in focus.
	 * 
	 * @param domain Dominio da impostare da considerare in focus.
	 */
	public void setDomain(DomainDTO domain) {
		this.domain = domain;
	}

	/**
	 * Restituisce la lista delle tipologie dei contatti per il popolamento della combobox.
	 * 
	 * @return Lista di tutti i possibili tipi di contatto.
	 */
	public List<ContactTypeEnum> getTypes() {
		return types;
	}

	/**
	 * Imposta la lista delle tipologie dei contatti.
	 * 
	 * @param types Tipologie dei contatti da impostare.
	 */
	public void setTypes(List<ContactTypeEnum> types) {
		this.types = types;
	}

	/**
	 * Restituisce la descrizione della tipologia del contatto in focus.
	 * 
	 * @return Descrizione tipologia contatto.
	 */
	public String getContactTypeString() {
		return contactTypeString;
	}

	/**
	 * Imposta la descrizione della tipologia del contatto in focus.
	 * 
	 * @param contactTypeString Descrizione tipologia contatto da impostare.
	 */
	public void setContactTypeString(String contactTypeString) {
		this.contactTypeString = contactTypeString;
	}

	/**
	 * Restituisce i domini recuperati dalla ricerca.
	 * Restituisce una lista vuota se la ricerca non ha prodotto risultato, restituisce <code> null </code> 
	 * se la ricerca non è stata mai eseguita.
	 * 
	 * @return Risultato della ricerca sui domini.
	 */
	public List<DomainDTO> getSearchedDomains() {
		return searchedDomains;
	}

	/**
	 * Imposta la lista dei domini recuperati dalla ricerca.
	 * 
	 * @param searchedDomains Risultati della ricerca da impostare.
	 */
	public void setSearchedDomains(List<DomainDTO> searchedDomains) {
		this.searchedDomains = searchedDomains;
	}

	/**
	 * Restituisce il contatto in focus.
	 * 
	 * @return Contatto in focus.
	 */
	public ContactDTO getContact() {
		return contact;
	}

	/**
	 * Imposta il contatto in focus.
	 * 
	 * @param contact Contatto da considerare in focus.
	 */
	public void setContact(ContactDTO contact) {
		this.contact = contact;
	}

	/**
	 * Restituisce i contatti Registrant del dominio in focus.
	 * 
	 * @return Lista dei Registrant del dominio in focus.
	 */
	public List<ContactDTO> getRegistrants() {
		return registrants;
	}

	/**
	 * Imposta la lista dei Registrant validi per il dominio considerato in focus.
	 *  
	 * @param registrants Contatti di tipo Registrant da impostare.
	 */
	public void setRegistrants(List<ContactDTO> registrants) {
		this.registrants = registrants;
	}

	/**
	 * Restituisce la lista degli amministratori del dominio considerato in focus.
	 * 
	 * @return Contatti amministratori del dominio.
	 */
	public List<ContactDTO> getAdmins() {
		return admins;
	}

	/**
	 * Imposta la lista degli amministratori del dominio in focus.
	 * 
	 * @param admins Amministratori del dominio da impostare che siano validi per il dominio considerato.
	 */
	public void setAdmins(List<ContactDTO> admins) {
		this.admins = admins;
	}

	/**
	 * Restituisce il Registrant principale che detiene i diritti sul dominio in focus.
	 * 
	 * @return Registrant principale del dominio.
	 */
	public ContactDTO getRegistrant() {
		return registrant;
	}

	/**
	 * Imposta il Registrant principale del dominio in focus.
	 * 
	 * @param registrant Registrant principale da impostare.
	 */
	public void setRegistrant(ContactDTO registrant) {
		this.registrant = registrant;
	}

	/**
	 * Restituisce l'Admin principale del dominio.
	 * 
	 * @return Admin principale del dominio.
	 */
	public ContactDTO getAdmin() {
		return admin;
	}

	/**
	 * Imposta l'Admin principale del dominio.
	 * 
	 * @param admin Contatto Admin del dominio in focus.
	 */
	public void setAdmin(ContactDTO admin) {
		this.admin = admin;
	}

	/**
	 * Restituisce la request di creazione dominio generata o una stringa vuota se la request non è stata creata.
	 * 
	 * @return Request <em> xml </em> creata.
	 */
	public String getCreateRequest() {
		return createRequest;
	}

	/**
	 * Imposta la request di creazione dominio generata.
	 * 
	 * @param createRequest Request di creazione da impostare.
	 */
	public void setCreateRequest(String createRequest) {
		this.createRequest = createRequest;
		System.out.println(this.createRequest);
	}

	/**
	 * Restituisce <code> true </code> se la request è esistente e formalmente corretta, restituisce <code> false </code> altrimenti.
	 * 
	 * @return Flag associato all'esistenza della Request.
	 */
	public boolean isExistingRequest() {
		return existingRequest;
	}

	/**
	 * Imposta il flag associato all'esistenza della Request.
	 * 
	 * @param existingRequest Flag da impostare.
	*/
	public void setExistingRequest(boolean existingRequest) {
		this.existingRequest = existingRequest;
	}

	/**
	 * Restituisce <code> true </code> se il dominio in focus è libero da ogni vincolo di utilizzo e può essere registrato come nuovo dominio.
	 * 
	 * @param free Flag associato ai vincoli del dominio.
	 */
	public boolean isFree() {
		return free;
	}

	/**
	 * Imposta il flag associato ad eventuali vincoli del dominio.
	 * 
	 * @param free Flag da impostare.
	 */
	public void setFree(boolean free) {
		this.free = free;
	}

	/**
	 * Restituisce la lista dei domini temporanei.
	 * 
	 * @return Domini temporanei.
	 */
	public List<DomainDTO> getTempDomains() {
		return tempDomains;
	}

	/**
	 * Imposta la lista dei domini temporanei.
	 * 
	 * @param tempDomains Lista dei domini temporanei da impostare.
	 */
	public void setTempDomains(List<DomainDTO> tempDomains) {
		this.tempDomains = tempDomains;
	}

	/**
	 * Restituisce il dominio identificato come dominio da verificare tramite funzionalità WHOIS.
	 * 
	 * @return Dominio da verificare.
	 */
	public String getDomainToVerify() {
		return domainToVerify;
	}

	/**
	 * Imposta il dominio da verificare tramite funzionalità WHOIS.
	 * 
	 * @param domainToVerify Dominio da impostare come <em> da verificare </em>.
	 */
	public void setDomainToVerify(String domainToVerify) {
		this.domainToVerify = domainToVerify;
	}

	/**
	 * Restituisce <code> true </code> se il dominio in focus non è libero da ogni vincolo di utilizzo e di conseguenza
	 * non può essere registrato come nuovo dominio.
	 * 
	 * @param free Flag associato ai vincoli del dominio.
	 */
	public boolean isNotFree() {
		return NotFree;
	}

	/**
	 * Imposta il flag associato ad eventuali vincoli del dominio.
	 * 
	 * @param free Flag da impostare.
	 */
	public void setNotFree(boolean notFree) {
		NotFree = notFree;
	}

	
}
