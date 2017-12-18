package org.mittman.generate.db;
/**
 * Info related to a JDBC Connection and its usage
 * 
 * @author Edward Mittman
 *
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * State needed to work with a JDBC prepared statement,
 * used for SQL queries with parameters
 * 
 * @author Edward Mittman
 *
 */
public class ParameterizedJdbcQuery extends AbstractJdbcQuery<PreparedStatement>{

	/**
	 * Create a statement from the connection
	 * @param connection
	 */
	public ParameterizedJdbcQuery(Connection connection) {
		super(connection);
	}

	/**
	 * Prepare a statement used with a parameterized query.
	 * Return the prepared statement, ready to have parameters
	 * set for an execution
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public PreparedStatement prepare() throws Exception {
		setStatement( getConnection().prepareStatement( getSql() ) );
		return getStatement();
	}
	
	/**
	 * Execute a query
	 * @return
	 * @throws Exception
	 */
	@Override
	public ResultSet execute() throws Exception {
		switch( getQueryType() ) {
		case SELECT:
			setResultSet( getStatement().executeQuery() );
			break;
		default:
			getStatement().executeUpdate();
			setResultSet( getStatement().getGeneratedKeys() );
			break;
		};

		return getResultSet();
	}
	
	@Override
	/**
	 * Close result set but leave statement intact
	 * in case we want to reexecute it with different parameters
	 */
	public void endQuery() {
		endResultSet();
	}

}
