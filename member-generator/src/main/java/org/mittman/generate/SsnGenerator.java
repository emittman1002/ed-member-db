package org.mittman.generate;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Generates SSN strings.  An optional
 * flag limits the strings to only valid
 * SSNs (defaults to true)
 * 
 * @author Edward Mittman
 *
 */
@Getter(AccessLevel.PACKAGE)
public class SsnGenerator implements Generator<String> {
	@Getter@Setter
	private boolean validOnly;
	@Setter
	private Random random;

	
	public SsnGenerator() {
		validOnly = true;
		random = new Random();
	}
	
	@Override
	public String generate() {
		int limit = validOnly ? 900 : 999;

		int val1 = random.nextInt(limit);
		while (validOnly && !isValidFirstThree(val1)) {
			val1 = random.nextInt(limit);
		}

		int val2 = random.nextInt(99);
		int val3 = random.nextInt(9999);
		
		return StringUtils.leftPad(Integer.toString(val1), 3, '0') +
				"-" +
				StringUtils.leftPad(Integer.toString(val2), 2, '0') +
				"-" +
				StringUtils.leftPad(Integer.toString(val3), 4, '0');	
	}

	protected boolean isValidFirstThree(int firstThree) {
		return (firstThree>0) &&
				(firstThree<237 || firstThree>246) &&
				(firstThree<587 || firstThree>679) &&
				(firstThree<681 || firstThree>699) &&
				(firstThree<734 || firstThree>772) &&
				(firstThree<900);
	}
}
