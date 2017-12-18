package org.mittman.config;

import lombok.Getter;

/**
 * Indicates that a problem occurred with a specified configuration
 * or that configuration isn't supported
 * 
 * @author Edward Mittman
 *
 */
@Getter
public class ConfigurationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	/**
	 * The object being configured when this exception was thrown
	 */
	private Object object;
	

	public ConfigurationException(Object object) {
		super();
		this.object = object;
	}

	public ConfigurationException(String message, Object object) {
		super(message);
		this.object = object;
	}

	public ConfigurationException(Throwable cause, Object object) {
		super(cause);
		this.object = object;
	}

	public ConfigurationException(String message, Throwable cause, Object object) {
		super(message, cause);
		this.object = object;
	}

	public ConfigurationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object object) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.object = object;
	}
}
