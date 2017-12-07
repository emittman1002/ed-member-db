package org.mittman.generate.impl;

/**
 * A generator for IDs used to identify objects of a specified class.
 * IdGenerators must be thread safe. 
 * 
 * @author Edward Mittman
 *
 */
public interface IdGenerator<I> {
	/**
	 * Generate a single ID
	 * @return
	 */
	I generate();
	
	/**
	 * Reserve a number of IDs for future use and
	 * return an IdGenerator to dole them out 
	 * @param numberOfReservations
	 * @return the generator to dole out the reserved IDs
	 * @throws UnsupportedOperationException if reservations are not supported
	 */
	IdGenerator<I> reserve(int numberOfReservations) throws UnsupportedOperationException;
}
