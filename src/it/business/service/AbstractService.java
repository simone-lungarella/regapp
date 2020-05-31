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
 * @author Simone Lungarella
 * 
 *         Service che viene esteso da tutti i service che verranno utilizzati,
 *         implementa tutto ciò di cui hanno bisogno i services
 */

@Service
@Component
public abstract class AbstractService implements Serializable {

	private static final long serialVersionUID = -2017286881829397007L;

	@Autowired
	@Qualifier("DataSource")
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * Consente di creare una connessione scegliendo da una tipologia che prevede
	 * l'auto commit ad ogni operazione e una strategia che consente di effettuare
	 * il commit manuale o il rollback delle azioni a seconda della necessità
	 */
	protected final Connection setupConnection(final Connection connection, final Boolean inTransactionActive)
			throws SQLException {
		if (inTransactionActive) {
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
		}
		return connection;
	}

	public final void closeConnection(final Connection connection) {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println("Impossibile chiudere la connessione");
		}
	}

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
	 * Se la connessione non è stata gestita con auto commit, occorre utilizzare questo metodo per farlo manualmente
	 * */
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
	 * Se le modifiche apportate al db non superano una determinata condizione ed occorre annullare tutte le modifiche effettuate
	 * dall'ultimo commit, occorre chiamare questa funzione per effettuare il rollback delle operazioni
	 * */
	public final void rollbackConnection(final Connection connection) {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.rollback();
			}
		} catch (SQLException e) {
		}
	}

}
