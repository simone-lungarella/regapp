package it.business.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.business.dao.AbstractDAO;
import it.business.dao.IDomainDAO;
import it.business.dto.DomainDTO;

/**
 * @author Simone Lungarella
 * 
 */

public class DomainDAO extends AbstractDAO implements IDomainDAO {

	private static final long serialVersionUID = 1221220226271838975L;

	@Override
	public DomainDTO findByDomainName(Connection connection, String domainName) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		DomainDTO domain = new DomainDTO();
		try {
			String query = "SELECT * FROM domains WHERE domainName = ?";
			ps = connection.prepareStatement(query);
			ps.setString(1, domainName);
			rs = ps.executeQuery();
			if (rs.next()) {
				domain.setDomainName(rs.getString("domainName"));
				domain.setDnssec(rs.getBoolean("dnssec"));
				domain.setAdmin(rs.getString("admin"));
				domain.setRegistrant(rs.getString("registrant"));
			}
		} catch (SQLException e) {
			System.out.println("Errore riscontrato nell'esecuzione della query nel metodo findByDomainName");
			e.printStackTrace();
		} finally {
			closeStatement(ps, rs);
		}

		return domain;
	}

	@Override
	public List<DomainDTO> findByRegistrant(Connection connection, String idRegistrant) {
		List<DomainDTO> domains = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		DomainDTO domain = new DomainDTO();
		try {
			String query = "SELECT * FROM domains WHERE registrant = ?";
			ps = connection.prepareStatement(query);
			ps.setString(1, idRegistrant);
			rs = ps.executeQuery();
			while (rs.next()) {
				domain.setDomainName(rs.getString("domainName"));
				domain.setDnssec(rs.getBoolean("dnssec"));
				domain.setAdmin(rs.getString("admin"));
				domain.setRegistrant(rs.getString("registrant"));
				domains.add(domain);
			}
		} catch (SQLException e) {
			System.out.println("Errore riscontrato nell'esecuzione della query nel metodo findByRegistrant");
			e.printStackTrace();
		} finally {
			closeStatement(ps, rs);
		}
		return domains;
	}

	@Override
	public List<DomainDTO> findBySecurity(Connection connection, boolean isSafe) {
		List<DomainDTO> domains = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		DomainDTO domain = new DomainDTO();
		try {
			String query = "SELECT * FROM domains WHERE dnssec = ?";
			ps = connection.prepareStatement(query);
			ps.setBoolean(1, isSafe);
			rs = ps.executeQuery();
			while (rs.next()) {
				domain.setDomainName(rs.getString("domainName"));
				domain.setDnssec(rs.getBoolean("dnssec"));
				domain.setAdmin(rs.getString("admin"));
				domain.setRegistrant(rs.getString("registrant"));
				domains.add(domain);
			}
		} catch (SQLException e) {
			System.out.println("Errore riscontrato nell'esecuzione della query nel metodo findBySecurity");
			e.printStackTrace();
		} finally {
			closeStatement(ps, rs);
		}
		return domains;
	}

	@Override
	public List<DomainDTO> findAll(Connection connection) {
		List<DomainDTO> domains = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		DomainDTO domain = new DomainDTO();
		try {
			String query = "SELECT * FROM domains";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				domain.setDomainName(rs.getString("domainName"));
				domain.setDnssec(rs.getBoolean("dnssec"));
				domain.setAdmin(rs.getString("admin"));
				domain.setRegistrant(rs.getString("registrant"));
				domains.add(domain);
			}
		} catch (SQLException e) {
			System.out.println("Errore riscontrato nell'esecuzione della query nel metodo findAll");
			e.printStackTrace();
		} finally {
			closeStatement(ps, rs);
		}
		return domains;
	}

	@Override
	public void addDomain(Connection connection, DomainDTO domain) {
		PreparedStatement ps = null;

		int index = 1;
		try {
			String query = "INSERT INTO contacts (contactId, firstName, lastName, contactType) VALUES (?,?,?,?)";
			ps = connection.prepareStatement(query);
			ps.setString(index++, domain.getDomainName());
			ps.setString(index++, domain.getAdmin());
			ps.setString(index++, domain.getRegistrant());
			ps.setBoolean(index++, domain.isDnssec());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Errore riscontrato durante l'inserimento del nuovo dominio");
			e.printStackTrace();
		} finally {
			closeStatement(ps);
		}
		
	}

	@Override
	public void removeByDomainName(Connection connection, String domainName) {
		PreparedStatement ps = null;

		try {
			String query = "DELETE FROM contacts WHERE domainName = ?";
			ps = connection.prepareStatement(query);
			ps.setString(1, domainName);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Errore riscontrato durante l'eliminazione del dominio");
			e.printStackTrace();
		} finally {
			closeStatement(ps);
		}
	}

	@Override
	public void update(Connection connection, DomainDTO domain) {
		PreparedStatement ps = null;
		int index = 1;
		try {
			String query = "UPDATE domains SET admin = ?, registrant = ?, dnssec = ? WHERE domainName = ?";
			ps = connection.prepareStatement(query);
			ps.setString(index++, domain.getAdmin());
			ps.setString(index++, domain.getRegistrant());
			ps.setBoolean(index++, domain.isDnssec());
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Errore durante l'update del dominio");
		} finally {
			closeStatement(ps);
		}
	}

}
