package it.business.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.business.dao.AbstractDAO;
import it.business.dao.IContractDAO;
import it.business.dto.ContractDTO;

public class ContractDAO extends AbstractDAO implements IContractDAO{

	private static final long serialVersionUID = -7998466574893315972L;

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
