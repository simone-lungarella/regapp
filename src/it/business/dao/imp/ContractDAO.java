package it.business.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import it.business.dao.AbstractDAO;
import it.business.dao.IContractDAO;
import it.business.dto.ContractDTO;

/**
 * DAO per la gestione dei contratti.
 * 
 * @author Simone Lungarella
 */
@Repository
public class ContractDAO extends AbstractDAO implements IContractDAO {

	/**
	 * Costante serial version UID.
	 */
	private static final long serialVersionUID = -7998466574893315972L;

	/**
	 * Restituisce il contratto identificato dal <code> contractNumber </code>.
	 * 
	 * @param connection     Connessione al database.
	 * @param contractNumber Identificativo univoco del contratto.
	 * @return Contratto recuperato se esiste un contratto con il numero
	 *         specificato, <code> null </code> altrimenti.
	 */
	@Override
	public ContractDTO findByContractNumber(Connection connection, int contractNumber) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ContractDTO contract = null;
		try {
			String query = "SELECT * FROM contracts WHERE contractNumber = ?";
			ps = connection.prepareStatement(query);
			ps.setInt(1, contractNumber);
			rs = ps.executeQuery();
			if (rs.next()) {
				contract = new ContractDTO();
				contract.setAdmin(rs.getString("admin"));
				contract.setDomain(rs.getString("domainName"));
				contract.setRegistrant("registrant");
				contract.setRegistrar("registrar");
			}
		} catch (SQLException e) {
			System.out.println("Errore riscontrato nell'esecuzione della query nel metodo findByContractNumber");
			e.printStackTrace();
		} finally {
			closeStatement(ps, rs);
		}

		return contract;
	}

	/**
	 * Restituisce tutti i contratti sotto la supervisione del Registrar
	 * identificato dal <code> idRegistrar </code>.
	 * 
	 * @param connection  Connessione al database.
	 * @param idRegistrar Identificativo del Registrar.
	 * @return Lista dei contatti recuperati.
	 */
	@Override
	public List<ContractDTO> findByRegistrar(Connection connection, String idRegistrar) {
		List<ContractDTO> contracts = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ContractDTO contract = new ContractDTO();
		try {
			String query = "SELECT * From contracts WHERE registrar=?";
			ps = connection.prepareStatement(query);
			ps.setString(1, idRegistrar);
			rs = ps.executeQuery();
			while (rs.next()) {
				contract.setAdmin(rs.getString("admin"));
				contract.setDomain(rs.getString("domainName"));
				contract.setRegistrant("registrant");
				contract.setRegistrar("registrar");
				contracts.add(contract);
			}
		} catch (SQLException e) {
			System.out.println("Errore riscontrato nell'esecuzione della query nel metodo findByRegistrar");
			e.printStackTrace();
		} finally {
			closeStatement(ps, rs);
		}
		return contracts;
	}

	/**
	 * Restituisce la lista dei contratti che si riferiscono al Registrant
	 * identificato dal <code> idRegistrant </code>.
	 * 
	 * @param connection   Connessione al database.
	 * @param idRegistrant Identificativo del Registrant.
	 * @return ista dei contratti recuperati.
	 */
	@Override
	public List<ContractDTO> findByRegistrant(Connection connection, String idRegistrant) {
		List<ContractDTO> contracts = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ContractDTO contract = null;
		try {
			String query = "SELECT * From contracts WHERE registrant=?";
			ps = connection.prepareStatement(query);
			ps.setString(1, idRegistrant);
			rs = ps.executeQuery();
			while (rs.next()) {
				contract = new ContractDTO();
				contract.setAdmin(rs.getString("admin"));
				contract.setDomain(rs.getString("domainName"));
				contract.setRegistrant("registrant");
				contract.setRegistrar("registrar");
				contracts.add(contract);
			}
		} catch (SQLException e) {
			System.out.println("Errore riscontrato nell'esecuzione della query nel metodo findByRegistrant");
			e.printStackTrace();
		} finally {
			closeStatement(ps, rs);
		}
		return contracts;
	}

	/**
	 * Restituisce tutti i contatti configurati sul database.
	 * 
	 * @param connection Connessione al database.
	 * @return Lista dei contratti esistenti sul database.
	 */
	@Override
	public List<ContractDTO> findAll(Connection connection) {
		List<ContractDTO> contracts = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ContractDTO contract = null;
		try {
			String query = "SELECT * From contracts";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				contract = new ContractDTO();
				contract.setAdmin(rs.getString("admin"));
				contract.setDomain(rs.getString("domainName"));
				contract.setRegistrant("registrant");
				contract.setRegistrar("registrar");
				contracts.add(contract);
			}
		} catch (SQLException e) {
			System.out.println("Errore riscontrato nell'esecuzione della query nel metodo findByRegistrant");
			e.printStackTrace();
		} finally {
			closeStatement(ps, rs);
		}
		return contracts;
	}

	/**
	 * Consente di rendere persistenti le informazioni sul contratto sulla base
	 * dati.
	 * 
	 * @param connection Connessione al database.
	 * @param contract   Contratto da memorizzare.
	 */
	@Override
	public void addContract(Connection connection, ContractDTO contract) {
		PreparedStatement ps = null;

		int index = 1;
		try {
			String query = "INSERT INTO contracts (registrar, registrant, admin, domainName) VALUES (?,?,?,?, ?)";
			ps = connection.prepareStatement(query);
			ps.setString(index++, contract.getRegistrar());
			ps.setString(index++, contract.getRegistrant());
			ps.setString(index++, contract.getAdmin());
			ps.setString(index, contract.getDomain());

			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Errore riscontrato durante l'inserimento del nuovo contratto");
			e.printStackTrace();
		} finally {
			closeStatement(ps);
		}
	}

	/**
	 * Elimina il contratto identificato dal <code> contractNumber </code>.
	 * 
	 * @param connection     Connessione al database.
	 * @param contractNumber Identificativo del contratto da eliminare.
	 */
	@Override
	public void removeContractByNumber(Connection connection, int contractNumber) {
		PreparedStatement ps = null;

		try {
			String query = "DELETE FROM contracts WHERE contractNumber = ?";
			ps = connection.prepareStatement(query);
			ps.setInt(1, contractNumber);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Errore riscontrato durante l'eliminazione del contratto");
			e.printStackTrace();
		} finally {
			closeStatement(ps);
		}
	}

	/**
	 * Restituisce il contratto associato al dominio identificato dal
	 * <code> domainName </code>.
	 * 
	 * @param connection Connessione al database.
	 * @param domainName Identificativo del dominio.
	 * @return Contratto recuperato dal database se esistente, <code> null </code>
	 *         altrimenti.
	 */
	@Override
	public ContractDTO findByDomainName(Connection connection, String domainName) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		ContractDTO contract = null;
		try {
			String query = "SELECT * FROM contracts WHERE domainName = ?";
			ps = connection.prepareStatement(query);
			ps.setString(1, domainName);
			rs = ps.executeQuery();
			if (rs.next()) {
				contract = new ContractDTO();
				contract.setAdmin(rs.getString("admin"));
				contract.setDomain(rs.getString("domainName"));
				contract.setRegistrant("registrant");
				contract.setRegistrar("registrar");
			}
		} catch (SQLException e) {
			System.out.println("Errore riscontrato nell'esecuzione della query nel metodo findByContractNumber");
			e.printStackTrace();
		} finally {
			closeStatement(ps, rs);
		}

		return contract;
	}

}
