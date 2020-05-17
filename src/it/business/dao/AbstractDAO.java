package it.business.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Simone Lungarella
 * Questo DAO gestisce tutte le operazioni comuni a tutti i DAO
 * */


public abstract class AbstractDAO {

	public final void closeStatement(final Statement ps) {
		closeStatement(ps, null);
	}

	public final void closeStatement(final Statement ps, final ResultSet rs) {
		if (rs != null) {
			try {
				if (!rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
			}
		}
		if (ps != null) {
			try {
				if (!ps.isClosed()) {
					ps.close();
				}
			} catch (SQLException e) {
			}
		}
	}

	public void closeResultset(final ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
	}
}
