package org.mittman.generate.domain;

/**
 * A group that has a name and a number
 * @author Edward Mittman
 *
 */
public interface Group<I> extends Identifiable<I> {
	String getName();
	void setName(String name);
	
	String getGroupNumber();
	void setGroupNumber(String groupNumber);
}
