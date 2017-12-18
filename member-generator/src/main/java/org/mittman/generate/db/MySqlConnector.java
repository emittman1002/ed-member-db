package org.mittman.generate.db;

/**
 * A connection to a MySQL database
 * 
 * @author Edward Mittman
 *
 */
public class MySqlConnector extends AbstractSingleConnectionConnector {
	public MySqlConnector() {
		super();
		setPort("3306");
	}

	protected String baseConnectionUrl() {
		return "jdbc:mysql://" + getHostname() + ":" + getPort() + "/";
	}

}
