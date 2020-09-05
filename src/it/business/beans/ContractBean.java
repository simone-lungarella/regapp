package it.business.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import it.business.dto.ContractDTO;

/**
 * @author Simone Lungarella
 * */

@ManagedBean(name = "contractBean")
@ViewScoped
public class ContractBean extends EntityBean{

	private ContractDTO contract;
	
	@Override
	@PostConstruct
	protected void postConstruct() {
		contract=new ContractDTO();
	}

	public ContractDTO getContract() {
		return contract;
	}

	public void setContract(ContractDTO contract) {
		this.contract = contract;
	}

	
}
