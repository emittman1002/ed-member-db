package org.mittman.generate.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/**
 * Base class for DB connector implementations that
 * maintain a single connection to the datbase
 * 
 * @author Edward Mittman
 *
 */
public abstract class AbstractSingleConnectionConnector implements DbConnector {
	private String connectionUrl;
	private String hostname;
	private String port;
	private String username;
	private String password;
	private String database;
	private Connection connection;
	private boolean valid;

	protected abstract String baseConnectionUrl();
	
	@Override
	public String []propertyNames() {
		return new String[]{
			"connectionUrl",
			"hostname",
			"port",
			"username",
			"password",
			"schema",
			"database"
		};
	}

	@Override
	public synchronized void initialize(Properties properties) {
		String tgt = "connectionUrl";
		String str;
		
		if (properties.contains(tgt)) {
			str = properties.getProperty(tgt);
			if (str!=null) {
				connectionUrl = str;
			}
		}
		
		tgt = "hostname";
		if (properties.contains(tgt)) {
			str = properties.getProperty(tgt);
			if (str!=null) {
				hostname = str;
			}
		}
		
		tgt = "port";
		if (properties.contains(tgt)) {
			str = properties.getProperty(tgt);
			if (str!=null) {
				port = str;
			}
		}
		
		tgt = "username";
		if (properties.contains(tgt)) {
			str = properties.getProperty(tgt);
			if (str!=null) {
				username = str;
			}
		}
		
		tgt = "password";
		if (properties.contains(tgt)) {
			str = properties.getProperty(tgt);
			if (str!=null) {
				password = str;
			}
		}
		
		// Schema is an alias for database
		// If both are specified, database wins
		tgt = "schema";
		if (properties.contains(tgt)) {
			str = properties.getProperty(tgt);
			if (str!=null) {
				database = str;
			}
		}
		
		tgt = "database";
		if (properties.contains(tgt)) {
			str = properties.getProperty(tgt);
			if (str!=null) {
				database = str;
			}
		}
	}
	
	@Override
	public void connect() throws DbException {
		try {
			connection = doConnection();
			valid = true;
		} catch (Exception e) {
			throw new DbException("Unable to connect to database", e);
		}
	}

	protected synchronized Connection doConnection() throws SQLException {
		String url = (connectionUrl!=null) ? connectionUrl : baseConnectionUrl();
		
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.username);
		connectionProps.put("password", this.password);
		
		Connection conn = DriverManager.getConnection(url, connectionProps);

		return conn;
	}

	@Override
	public synchronized void disconnect() {
		if (connection!=null) {
			try {
				connection.close();
			}
			catch(Exception e) {
				// ignore it
			}
			finally {
				connection = null;
				valid = false;
			}
		}
	}

	@Override
	public synchronized boolean isConnected() {
		return connection!=null && valid;
	}

	@Override
	public synchronized void onDatabaseException(Exception exception) {
		valid = false;
	}

	public synchronized Connection getConnection() {
		return connection;
	}

	public synchronized void setConnection(Connection connection) {
		this.connection = connection;
	}

	public synchronized String getConnectionUrl() {
		return connectionUrl;
	}

	public synchronized void setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl;
	}

	public synchronized String getHostname() {
		return hostname;
	}

	public synchronized void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public synchronized String getPort() {
		return port;
	}

	public synchronized void setPort(String port) {
		this.port = port;
	}

	public synchronized String getUsername() {
		return username;
	}

	public synchronized void setUsername(String username) {
		this.username = username;
	}

	public synchronized String getPassword() {
		return password;
	}

	public synchronized void setPassword(String password) {
		this.password = password;
	}

	public synchronized String getDatabase() {
		return database;
	}

	public synchronized void setDatabase(String database) {
		this.database = database;
	}

}
