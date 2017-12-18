package org.mittman.generate.db;

/**
 * Indicates some problem with a datbase connection
 * or operation 
 * 
 * @author Edward Mittman
 *
 */
public class DbException extends Exception {
	private static final long serialVersionUID = 1L;

	public DbException() {
		super();
	}

	public DbException(String message) {
		super(message);
	}

	public DbException(Throwable cause) {
		super(cause);
	}

	public DbException(String message, Throwable cause) {
		super(message, cause);
	}

	public DbException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
