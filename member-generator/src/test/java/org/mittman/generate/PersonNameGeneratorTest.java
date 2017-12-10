package org.mittman.generate;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mittman.generate.domain.PersonName;

public class PersonNameGeneratorTest {
	private PersonNameGenerator generator;

	@Before
	public void setUp() throws Exception {
		generator = new PersonNameGenerator();
	}

	@Test
	public void constructorInitializesThings() {
		assertNotNull(generator.getFirstNameFile());
		assertNull(generator.getFirstNames());
		
		assertNotNull(generator.getMiddleNameFile());
		assertNull(generator.getMiddleNames());
		
		assertNotNull(generator.getLastNameFile());
		assertNull(generator.getLastNames());
		
		assertFalse(generator.isInitialized());
		
		assertNotNull(generator.getRandom());
	}

	@Test
	public void generateReturnsAFullyPopulatedObject() {
		PersonName pn = generator.generate();
		
		assertNotNull(pn.getFirstName());
		assertTrue(generator.getFirstNames().contains(pn.getFirstName()));
		
		assertNotNull(pn.getMiddleName());
		assertTrue(generator.getMiddleNames().contains(pn.getMiddleName()));
		
		assertNotNull(pn.getLastName());
		assertTrue(generator.getLastNames().contains(pn.getLastName()));
	}

	@Test
	public void setFirstNameFileUninitializes() {
		generator.setInitialized(true);
		generator.setFirstNameFile("whatever");
		assertFalse(generator.isInitialized());
	}

	@Test
	public void testSetMiddleNameFile() {
		generator.setInitialized(true);
		generator.setMiddleNameFile("whatever");
		assertFalse(generator.isInitialized());
	}

	@Test
	public void testSetLastNameFile() {
		generator.setInitialized(true);
		generator.setLastNameFile("whatever");
		assertFalse(generator.isInitialized());
	}

}
