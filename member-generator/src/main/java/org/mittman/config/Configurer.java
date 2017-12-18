package org.mittman.config;

/**
 * Something that configures objects
 * 
 * @author Edward Mittman
 *
 */
public interface Configurer<T> {
	/**
	 * Configure an object
	 * 
	 * @param object
	 * @throws Exception
	 */
	void configure(T object) throws ConfigurationException;

}
