package org.mittman.generate;

/**
 * Something that generates something else
 * 
 * @author Edward Mittman
 *
 * @param <T>
 */
public interface Generator<T> {
	T generate();
}
