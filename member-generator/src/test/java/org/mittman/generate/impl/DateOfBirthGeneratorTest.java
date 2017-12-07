package org.mittman.generate.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class DateOfBirthGeneratorTest {
	private DateOfBirthGenerator generator;
	

	@Before
	public void setUp() throws Exception {
		generator = new DateOfBirthGenerator();
	}

	@Test
	public void constructorInitializesFields() {
		assertEquals(15, generator.getMinAge());
		assertEquals(150, generator.getMaxAge());
		assertEquals(0, generator.getDifference());
		assertFalse(generator.isInitialized());
		assertNotNull(generator.getRandom());
	}

	@Test
	public void testReinitialize() {
		generator.reinitialize();
		
		assertTrue(generator.isInitialized());
		assertEquals(generator.getMaxAge()-generator.getMinAge(), generator.getDifference());
	}
	
	@Test(expected = IllegalStateException.class)
	public void reinitializeThrowsExceptionForInvalidAges() {
		generator.setMaxAge(generator.getMinAge() - 1);
		
		generator.reinitialize();
	}
	
	public void reinitializeAcceptsSameAges() {
		generator.setMaxAge(generator.getMinAge());
		
		generator.reinitialize();
		
		assertTrue(generator.isInitialized());
		assertEquals(0, generator.getDifference());
	}

	@Test
	public void setMinAgeRequiresReinitialization() {
		generator.setInitialized(true);
		
		generator.setMinAge(20);
		
		assertFalse(generator.isInitialized());
	}

	@Test
	public void setMaxAgeRequiresReinitialization() {
		generator.setInitialized(true);

		generator.setMaxAge(20);

		assertFalse(generator.isInitialized());
	}

	@Test
	public void generateWorksForDifferentAges() {
		LocalDate dob = generator.generate();
		LocalDate now = LocalDate.now();
		
		assertTrue(generator.getMinAge() <= now.getYear() - dob.getYear());
		assertTrue(generator.getMaxAge() >= now.getYear() - dob.getYear());
	}
	
	@Test
	public void generateWorksForSameAges() {
		generator.setMaxAge( generator.getMinAge() );
		
		LocalDate dob = generator.generate();
		LocalDate now = LocalDate.now();
		
		assertEquals(generator.getMaxAge(), now.getYear() - dob.getYear());
	}

}
