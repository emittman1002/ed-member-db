package org.mittman.generate.write;

import java.util.Collection;

/**
 * Outputs objects
 * 
 * @author Edward Mittman
 *
 */
public interface OutputWriter<T> {
	/**
	 * Output the contents of the collection
	 * 
	 * @param objects
	 * @throws Exception if output failed
	 */
	void write(Collection<T> objects) throws Exception;
}
