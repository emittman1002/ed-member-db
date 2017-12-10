package org.mittman.generate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Returns a random value from a collection of strings
 * 
 * @author Edward Mittman
 *
 */
@Getter(AccessLevel.PACKAGE)@Setter(AccessLevel.PACKAGE)
public class StringGenerator implements Generator<String> {
	@Getter@Setter
	private String filename;
	@Getter@Setter
	private String label;
	private boolean initialized;
	private List<String> values;
	private Random random;
	

	public StringGenerator() {
		random = new Random();
	}
	
	@Override
	public String generate() {
		if (!initialized) {
			reinitialize();
		}
		
		int idx = random.nextInt(values.size());
		
		return values.get(idx);
	}
	
	void reinitialize() throws RuntimeException {
		CollectionPopulator populator = new CollectionPopulator();
		values = new ArrayList<String>();
		populator.populate(values, filename, label);
		initialized = true;
	}


}
