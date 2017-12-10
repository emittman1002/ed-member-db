package org.mittman.generate;

/**
 * Base class for Generator implementations
 * 
 * @author Edward Mittman
 *
 * @param <T>
 */
public abstract class AbstractGenerator<T> implements Generator<T> {

	/**
	 * Create a new instance of the object being generated
	 * @return
	 */
	protected abstract T create();
	
	/**
	 * Populate a instance of an object being generated
	 * @param object
	 */
	protected abstract void populate(T object) ;

	@Override
	public T generate() {
		T obj = create();
		populate(obj);
		return obj;
	}
	
	
}
