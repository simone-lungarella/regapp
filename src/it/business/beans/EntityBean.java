package it.business.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class EntityBean {
	/**
	 * Metodo di utility per la renderizzazione di messaggi informativi per l'utente
	 * */
	protected final void showInfoMessage(final String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", msg));
	}
	
}
