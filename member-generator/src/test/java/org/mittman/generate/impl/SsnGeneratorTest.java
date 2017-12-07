package org.mittman.generate.impl;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class SsnGeneratorTest {
	private final int NUM_TESTS = 100000;
	private SsnGenerator generator;
	

	@Before
	public void setUp() throws Exception {
		generator = new SsnGenerator();
	}

	@Test
	public void constructorInitializesFields() {
		assertTrue(generator.isValidOnly());
		assertNotNull(generator.getRandom());
	}

	private int[][]invalidRanges = {
			{0, 0},
			{666, 666},
			{237, 246},
			{587, 665},
			{667, 679},
			{681, 699},
			{750, 772},
			{734, 749},
			{900, 999}
	};
	
	@Test
	public void testIsValidFirstThree() {
		for(int candidate=-1000; candidate<=1000; ++candidate) {
			boolean b = expected(candidate);
			assertEquals("Candidate " + candidate,
					b, generator.isValidFirstThree(candidate));
		}
	}
	
	private boolean expected(int candidate) {
		boolean ret = candidate>0 && candidate!=666 && candidate<1000;

		if (ret) {
			for(int i=0; i<invalidRanges.length; ++i) {
				if (candidate>=invalidRanges[i][0] && candidate<=invalidRanges[i][1]) {
					ret = false;
					break;
				}
			}
		}

		return ret;
	}
	
	@Test
	public void testIsValid() {
		assertFalse( generator.isValid("000-22-3333") );
		assertTrue( generator.isValid("001-22-3333") );
		assertTrue( generator.isValid("111-22-3333") );
		assertTrue( generator.isValid("899-22-3333") );
		assertFalse( generator.isValid("900-22-3333") );
		
		assertFalse( generator.isValid("111223333") );
		assertFalse( generator.isValid("1111-22-3333") );
		assertFalse( generator.isValid("1112-2-3333") );
		assertFalse( generator.isValid(" 111-22-3333") );
		assertFalse( generator.isValid("111 -22-3333") );
		assertFalse( generator.isValid("111-22-333 ") );
		assertFalse( generator.isValid("111-2A-3333") );
	}
	
	private class HalfValid extends Random {
		private static final long serialVersionUID = 1L;
		private int prev;
		
		@Override
		public int nextInt(int limit) {
			if (prev==0) {
				prev = 1;
			}
			else {
				prev = 0;
			}
			
			return prev;
		}
	}
	
	@Test
	public void testGenerateValidOnly() {
		generator.setRandom(new HalfValid());
		
		int invalidCt = 0;
		for(int i=0; i<NUM_TESTS; ++i) {
			String ssn = generator.generate();
			
			if (!generator.isValid(ssn)) {
				++invalidCt;
			}
		}
		
		assertEquals(0, invalidCt);
	}

	@Test
	public void testGenerateInvalidAllowed() {
		generator.setValidOnly(false);
		generator.setRandom(new HalfValid());
		
		int invalidCt = 0;
		for(int i=0; i<NUM_TESTS; ++i) {
			String ssn = generator.generate();
			
			if (!generator.isValid(ssn)) {
				++invalidCt;
			}
		}
		
		assertEquals(NUM_TESTS/2, invalidCt);
	}
}
