package it.business.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Service che viene esteso da tutti i service che verranno utilizzati,
 * implementa tutto ciò di cui hanno bisogno i services.
 * 
 * @author Simone Lungarella
 */
@Service
@Component
public abstract class AbstractService implements Serializable {

	/**
	 * La costante serial version UID.
	 */
	private static final long serialVersionUID = -2017286881829397007L;

	/**
	 * Data source per il raggiungimento del Database.
	 */
	@Autowired
	@Qualifier("DataSource")
	private DataSource dataSource;

	/**
	 * Restituisce il datasource.
	 * 
	 * @return DataSource.
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * Consente di creare una connessione scegliendo da una tipologia che prevede
	 * l'auto commit ad ogni operazione e una strategia che consente di effettuare
	 * il commit manuale o il rollback delle azioni a seconda della necessità.
	 */
	protected final Connection setupConnection(final Connection connection, final Boolean inTransactionActive)
			throws SQLException {
		if (inTransactionActive) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
		}
		return connection;
	}

	/**
	 * Esegue la chiusura della connessione.
	 * 
	 * @param connection Connessione da chiudere.
	 */
	public final void closeConnection(final Connection connection) {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println("Impossibile chiudere la connessione");
		}
	}

	/**
	 * Esegue il commit della connessione chiudendo la transazione.
	 * 
	 * @param connection Connessione da committare.
	 */
	public final void closeTransaction(final Connection connection) {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.commit();
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			System.out.println("Impossibile terminare la transazione");
		}
	}

	/**
	 * Esegue il commit della connessione chiudendola infine.
	 * 
	 * @param connection Connessione da committare.
	 */
	public final void commitConnection(final Connection connection) throws SQLException {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.commit();
			}
		} finally {
			closeConnection(connection);
		}
	}

	/**
	 * Se le modifiche apportate al db non superano una determinata condizione ed
	 * occorre annullare tutte le modifiche effettuate dall'ultimo commit, occorre
	 * chiamare questa funzione per effettuare il rollback delle operazioni.
	 * 
	 * @param connection Connessione su cui effettuare il rollback.
	 */
	public final void rollbackConnection(final Connection connection) {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.rollback();
			}
		} catch (SQLException e) {
			System.out.println("Impossibile effettuare il rollback della transazione");
		}
	}

}
