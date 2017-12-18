package org.mittman.generate.db;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * State associated with JDBC queries
 * 
 * @author Edward Mittman
 *
 * @param <S>
 */
public interface JdbcQuery<S extends Statement> {
	enum QueryType {
		SELECT,
		INSERT,
		UPDATE,
		DELETE
	};
	
	/**
	 * Prepare the state for use with a different
	 * SQL query
	 * 
	 * @param sql
	 */
	void setSql(String sql);
	
	/**
	 * Create a statement to be executed.  For parameterized
	 * queries, parameters can be set.
	 * 
	 * @return
	 * @throws Exception
	 */
	S prepare() throws Exception;
	
	/**
	 * Execute a query and return results
	 * @return
	 * @throws Exception
	 */
	ResultSet execute() throws Exception;
	
	/**
	 * Indicate that the latest query operation is
	 * complete.
	 */
	void endQuery();

	/**
	 * Free up resources associated with this
	 * state, but not the connection
	 * 
	 * @throws Exception
	 */
	public void close() throws Exception;
}
