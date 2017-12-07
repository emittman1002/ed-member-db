package org.mittman.generate.domain;

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
