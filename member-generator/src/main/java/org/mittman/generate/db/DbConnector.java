package org.mittman.generate.db;

import java.util.Properties;

/**
 * A connection to a database.
 * 
 * Implementations of this class must
 * be thread safe.
 * 
 * @author Edward Mittman
 *
 */
public interface DbConnector {
	/**
	 * Initialize the object from a set of properties
	 * @param properties
	 */
	void initialize(Properties properties);
	
	/**
	 * Returns a list of properties that
	 * can be specified for this connector
	 * 
	 * @return
	 */
	String []propertyNames();
	
	/**
	 * Connect to a database
	 * 
	 * The result of calling connect() on
	 * a connector that already has a connection
	 * is implementation-defined.  Implementations
	 * may return the same connection, create and manage
	 * a new one, or throw an exception.
	 * @return 
	 * 
	 * @throws DbException
	 */
	void connect() throws DbException;
	
	/**
	 * Disconnect from a database and free up
	 * any resources in use.  Applications should
	 * always call this when shutting down.
	 * 
	 * Calling disconnect() on a connector that
	 * does not have a connection is implementation-defined
	 * but should reflect whatever semantics connect() implements.
	 * 
	 */
	void disconnect();
	
	/**
	 * Determine wither a connector is presently connected
	 * to a database
	 * @return
	 */
	boolean isConnected();
	/**
	 * Update state when a database exception has been thrown.
	 * Typically this invalidates the connection.
	 * @param exception
	 */
	void onDatabaseException(Exception exception);
}
