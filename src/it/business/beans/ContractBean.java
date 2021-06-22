package it.business.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import it.business.dto.ContractDTO;

/**
 * Bean che gestisce tutte le relazioni del Registrar identificando le associazioni tra i contatti e i domini.
 * 
 * @author Simone Lungarella
 */
@ManagedBean(name = "contractBean")
@ViewScoped
public class ContractBean extends EntityBean{

	/**
	 * Contratto in focus.
	 */
	private ContractDTO contract;
	
	/**
	 * Inizializza la pagina dei contratti in fase di visualizzazione.
	 */
	@Override
	@PostConstruct
	protected void postConstruct() {
		contract=new ContractDTO();
	}

	/**
	 * Restituisce il contratto in focus.
	 * 
	 * @param contract Contratto in focus.
	 */
	public ContractDTO getContract() {
		return contract;
	}

	/**
	 * Imposta il contratto in focus.
	 * 
	 * @param contract Contratto in focus da impostare.
	 */
	public void setContract(ContractDTO contract) {
		this.contract = contract;
	}

	
}
