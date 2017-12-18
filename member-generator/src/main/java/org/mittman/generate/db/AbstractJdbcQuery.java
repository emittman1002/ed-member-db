package org.mittman.generate.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Base class for jdbc helper classes
 * 
 * @author Edward Mittman
 *
 * @param <S>
 */
public abstract class AbstractJdbcQuery<S extends Statement> implements JdbcQuery<S> {
	@Getter(AccessLevel.PROTECTED)
	private Connection connection;
	@Getter
	private String sql;
	@Getter(AccessLevel.PROTECTED)
	private QueryType queryType;
	@Getter(AccessLevel.PROTECTED)@Setter(AccessLevel.PROTECTED)
	private S statement;
	@Getter(AccessLevel.PROTECTED)@Setter(AccessLevel.PROTECTED)
	private ResultSet resultSet;
	
	
	public AbstractJdbcQuery(Connection connection) {
		this.connection = connection;
	}
	
	/**
	 * Prepare the state for use with a different
	 * SQL query
	 * 
	 * @param sql
	 */
	public void setSql(String sql) {
		endResultSet();
		endStatement();
		this.sql = sql;
		classifyQueryType(sql);
	}
	
	protected void classifyQueryType(String sql) {
		queryType = null;

		if (sql != null) {
			sql = sql.trim().toUpperCase();

			if (sql.startsWith("INSERT ")) {
				queryType = QueryType.INSERT;
			}
			else
				if (sql.startsWith("UPDATE ")) {
					queryType = QueryType.UPDATE;
				}
			if (sql.startsWith("DELETE ")) {
				queryType = QueryType.DELETE;
			}
		}

		if (queryType==null) {
			queryType = QueryType.SELECT;
		}
	}
	
	/**
	 * Create a statement to be executed.  For parameterized
	 * queries, parameters can be set.
	 * 
	 * @return
	 * @throws Exception
	 */
	abstract public S prepare() throws Exception;
	
	/**
	 * Execute a query and return results
	 * @return
	 * @throws Exception
	 */
	abstract public ResultSet execute() throws Exception;
	
	/**
	 * Indicate that a query operation
	 * has finished processing results
	 */
	protected void endResultSet() {
		if (resultSet!=null) {
			try {
				resultSet.close();
			}
			catch(Exception e) {
				// ignore
			}
			resultSet = null;
		}
	}
	
	/**
	 * Indicate that a query operation
	 * has finished with the statement
	 */
	protected void endStatement() {
		if (statement!=null) {
			try {
				statement.close();
			}
			catch(Exception e) {
				// ignore
			}
			statement = null;
		}
	}
	
	/**
	 * Indicate that the latest query operation is
	 * complete.
	 */
	public void endQuery() {
		endResultSet();
		endStatement();
	}

	/**
	 * Free up resources associated with this
	 * state, but not the connection itself
	 * 
	 * @throws Exception
	 */
	public void close() throws Exception {
		endResultSet();
		endStatement();
		sql = null;
	}
}
