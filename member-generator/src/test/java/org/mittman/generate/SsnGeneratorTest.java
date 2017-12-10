package org.mittman.generate;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.mittman.generate.SsnGenerator;

public class SsnGeneratorTest {
	private final int NUM_TESTS = 100000;
	private GeneratorTestHelper testHelper;
	private SsnGenerator generator;
	

	@Before
	public void setUp() throws Exception {
		testHelper = new GeneratorTestHelper();
		generator = new SsnGenerator();
	}

	@Test
	public void constructorInitializesFields() {
		assertTrue(generator.isValidOnly());
		assertNotNull(generator.getRandom());
	}

	
	@Test
	public void testIsValidFirstThree() {
		final String MSG = "Invalid first three ";
		
		for(int []range: testHelper.invalidSsnRanges) {
			int val = range[0];
			assertFalse(generator.isValidFirstThree(val));
			
			--val;
			if (val>0) {
				assertTrue(MSG + val, generator.isValidFirstThree(val));
			}
			else {
				assertFalse("Invalid first three " + val, generator.isValidFirstThree(val));
			}
			
			val = (range[1] - range[0])/2 + range[0];
			assertFalse(MSG + val, generator.isValidFirstThree(val));
			
			val = range[1];
			assertFalse(MSG + val, generator.isValidFirstThree(val));
			
			++val;
			if (val<1000) {
				assertTrue(MSG + val, generator.isValidFirstThree(val));
			}
			else {
				assertFalse("Invalid first three " + val, generator.isValidFirstThree(val));
			}
		}
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
			
			if (!testHelper.isValidSsn(ssn)) {
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
			
			if (!testHelper.isValidSsn(ssn)) {
				++invalidCt;
			}
		}
		
		assertEquals(NUM_TESTS/2, invalidCt);
	}
}
