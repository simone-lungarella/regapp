package it.business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe abstract che gestisce tutte le operazioni comuni a tutti i DAO.
 * 
 * @author Simone Lungarella
 */
public abstract class AbstractDAO {

	/**
	 * Chiude lo statemet <code> ps </code>.
	 * 
	 * @param ps Statement da chiudere.
	 */
	public final void closeStatement(final Statement ps) {
		closeStatement(ps, null);
	}

	/**
	 * Consente di chiudere uno statement ed un result set.
	 * 
	 * @param ps Statement da chiudere.
	 * @param rs ResultSet da chiudere.
	 */
	public final void closeStatement(final Statement ps, final ResultSet rs) {
		if (rs != null) {
			try {
				if (!rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				System.out.println("Errore riscontrato durante la chiusura del resultSet");
			}
		}
		if (ps != null) {
			try {
				if (!ps.isClosed()) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("Errore riscontrato durante la chiusura dello statement");
			}
		}
	}

	/**
	 * Consente di chiudere il ResultSet: <code> rs </code>.
	 * 
	 * @param rs ResultSet da chiudere.
	 */
	public void closeResultset(final ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("Errore riscontrato durante la chiusura del resultSet");
			}
		}
	}
}
