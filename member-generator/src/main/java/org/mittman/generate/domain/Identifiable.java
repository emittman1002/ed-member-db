package org.mittman.generate.domain;

/**
 * Something that has an ID
 * 
 * @author Edward Mittman
 *
 */
public interface Identifiable<I> {
	I getId();
	void setId(I id);
}
