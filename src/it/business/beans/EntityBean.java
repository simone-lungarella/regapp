package it.business.beans;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Abstract bean per la gestione della logica comune a tutti i bean.
 * 
 * @author Simone Lungarella
 */
public abstract class EntityBean {
	/**
	 * Metodo di utility per la renderizzazione di messaggi informativi per l'utente.
	 * @param msg Messaggio da visualizzare nel <em> Tooltip </em>.
	 * */
	protected final void showInfoMessage(final String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", msg));
	}
	
	/**
	 * Post construct del Bean.
	 */
	@PostConstruct
	protected abstract void postConstruct();
}
