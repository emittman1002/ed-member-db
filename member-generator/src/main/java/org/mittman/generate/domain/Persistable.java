package org.mittman.generate.domain;

import java.io.Serializable;

/**
 * Designates that something can be persisted
 * 
 * @author Edward Mittman
 *
 */
public interface Persistable extends Serializable {
	boolean isPersisted();
	void setPersisted(boolean persisted);
}
