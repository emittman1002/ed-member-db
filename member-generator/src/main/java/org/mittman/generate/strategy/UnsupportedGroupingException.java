package org.mittman.generate.strategy;

/**
 * Indicates that a strategy does not support grouping of an object
 * 
 * @author Edward Mittman
 *
 */
public class UnsupportedGroupingException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UnsupportedGroupingException() {
		super();
	}

	public UnsupportedGroupingException(String message, Throwable cause, boolean enableSuppression, boolean writeableStackTrace) {
		super(message, cause, enableSuppression, writeableStackTrace);
	}

	public UnsupportedGroupingException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnsupportedGroupingException(String message) {
		super(message);
	}

	public UnsupportedGroupingException(Throwable cause) {
		super(cause);
	}

}
