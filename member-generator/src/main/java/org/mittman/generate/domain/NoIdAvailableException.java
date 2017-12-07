package org.mittman.generate.domain;

/**
 * Indicates that an IdGenerator is unable to produce an id
 * @author Edward Mittman
 *
 */
public class NoIdAvailableException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NoIdAvailableException() {
		super();
	}
}
