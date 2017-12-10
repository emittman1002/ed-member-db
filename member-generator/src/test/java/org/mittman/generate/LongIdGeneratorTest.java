package org.mittman.generate;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mittman.generate.IdGenerator;
import org.mittman.generate.LongIdGenerator;
import org.mittman.generate.domain.NoIdAvailableException;

public class LongIdGeneratorTest {
	private LongIdGenerator generator;
	

	@Before
	public void setUp() throws Exception {
		generator = new LongIdGenerator();
	}

	@Test
	public void constructorInitializesGenerator() {
		assertEquals(0, generator.getNextId().get());
	}

	@Test
	public void testGenerate() {
		long l = generator.getNextId().get();
		
		assertEquals(++l, generator.generate().longValue());
		assertEquals(++l, generator.generate().longValue());
		assertEquals(++l, generator.generate().longValue());
	}

	@Test
	public void testReserve() {
		long l = generator.getNextId().get();
		assertEquals(++l, generator.generate().longValue());
		
		final int NUM_RESERVATIONS = 100;
		
		IdGenerator<Long> delegate = generator.reserve(NUM_RESERVATIONS);
		
		for(int i=0; i<NUM_RESERVATIONS; ++i) {
			assertEquals(++l, delegate.generate().longValue());
		}
		
		try {
			delegate.generate();
			fail("Should have thrown NotAvailableException after consuming " + NUM_RESERVATIONS + " reserved ids");
		}
		catch(NoIdAvailableException e) {
			// pass
		}
		
		assertEquals(++l, generator.generate().longValue());
	}

	@Test
	public void generatorReturnedByReserveDoesNotSupportReserve() {
		final int NUM_RESERVATIONS = 5;
		
		IdGenerator<Long> delegate = generator.reserve(NUM_RESERVATIONS);
		
		try {
			delegate.reserve(NUM_RESERVATIONS);
			fail("Should have thrown UnsupportedOperationException");
		}
		catch(UnsupportedOperationException e) {
			// pass
		}
	}
	
}
