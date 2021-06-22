package it.business.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import it.business.dao.AbstractDAO;
import it.business.dao.IDomainDAO;
import it.business.dto.DomainDTO;

/**
 * DAO che gestisce i domini.
 * 
 * @author Simone Lungarella
 */
@Repository
public class DomainDAO extends AbstractDAO implements IDomainDAO {

	/**
	 * La costante serial version UID.
	 */
	private static final long serialVersionUID = 1221220226271838975L;

	/**
	 * Restituisce il dominio identificato dal <code> domainName </code>.
	 * 
	 * @param connection Connessione al database.
	 * @param domainName Identificativo del dominio.
	 * @return Dominio recuperato dal database.
	 */
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

	/**
	 * Restituisce la lista dei domini che si riferiscono al Registrant identificato
	 * dal <code> idRegistrant </code>.
	 * 
	 * @param connection   Connessione al database.
	 * @param idRegistrant Identificativo del Registrant.
	 * @return Lista dei domini recuperati.
	 */
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

	/**
	 * Restituisce tutti i domini in base all'estensione di sicurezza stabilita dal
	 * flag: <code> isSafe </code>.
	 * 
	 * @param connection Connessione al database.
	 * @param isSafe     Se <code> true </code> indica che i domini ricercati devono
	 *                   avere l'estensione di sicurezza.
	 * @return Lista dei domini recuperati.
	 */
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

	/**
	 * Restituisce tutti i domini esistenti sul dominio.
	 * 
	 * @param connection Connessione al database.
	 * @return Lista dei domini recuperati.
	 */
	@Override
	public List<DomainDTO> findAll(Connection connection) {
		List<DomainDTO> domains = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DomainDTO domain = null;
		try {
			domains = new ArrayList<>();
			String query = "SELECT * FROM domains";
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				domain = new DomainDTO();
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

	/**
	 * Consente di rendere persistenti le informazioni sul dominio.
	 * 
	 * @param connection Connessione al database.
	 * @param domain     Dominio da memorizzare sulla base dati.
	 */
	@Override
	public void addDomain(Connection connection, DomainDTO domain) {
		PreparedStatement ps = null;

		int index = 1;
		try {
			String query = "INSERT INTO domains (domainName, admin, registrant, dnssec) VALUES (?,?,?,?)";
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

	/**
	 * Elimina dalla base dati il dominio identificato dal
	 * <code> domainName </code>.
	 * 
	 * @param connection Connessione al database.
	 * @param domainName Identificativo del dominio da eliminare.
	 */
	@Override
	public void removeByDomainName(Connection connection, String domainName) {
		PreparedStatement ps = null;

		try {
			String query = "DELETE FROM domains WHERE domainName = ?";
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

	/**
	 * Effettua un aggiornamento di un dominio modificandone le proprietà non
	 * identificative.
	 * 
	 * @param connection Connessione al database.
	 * @param domain     Dominio aggiornato.
	 */
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
