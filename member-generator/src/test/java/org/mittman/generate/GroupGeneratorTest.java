package org.mittman.generate;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mittman.generate.domain.Group;

import lombok.Setter;

public class GroupGeneratorTest {
	@Setter
	private GeneratorTestHelper testHelper;
	@Setter
	private GroupGenerator<Long> longGenerator;
	@Setter
	private GroupGenerator<String> stringGenerator;
	

	@Before
	public void setUp() throws Exception {
		testHelper = new GeneratorTestHelper();
		longGenerator = new GroupGenerator<Long>(Long.class);
		stringGenerator = new GroupGenerator<String>(String.class);
	}

	@Test
	public void constructorInitializesFields() {
		testHelper.verifyGroupGeneratorConstructor(longGenerator, Long.class);
		testHelper.verifyGroupGeneratorConstructor(stringGenerator, String.class);
	}
	
	@Test
	public void generateFullyPopulatesAGroup() {
		testHelper.verifyPopulatesAGroup(longGenerator, Long.class);
		testHelper.verifyPopulatesAGroup(stringGenerator, String.class);
	}
	
	@Test
	public void generatePopulatesUsingRandomValues() {
		Group<Long> g = longGenerator.generate();
		assertEquals(new Long(1), g.getId());
		testHelper.verifyGroup(g, longGenerator);
		
		g = longGenerator.generate();
		assertEquals(new Long(2), g.getId());
		testHelper.verifyGroup(g, longGenerator);
	}
	
	@Test
	public void generateUsesIdValues() {
		// Set flag to use ID values
		longGenerator.setUseIdForNamesAndNumbers(true);
		
		// Generator should ignore these
		longGenerator.setNameGenerator(new GeneratorTestHelper.SingleNameGenerator());
		longGenerator.setGroupNumberGenerator(new GeneratorTestHelper.SingleGroupNumberGenerator());
		
		Group<Long> g = longGenerator.generate();
		assertEquals(new Long(1), g.getId());
		testHelper.verifyGroup(g, longGenerator);
		
		g = longGenerator.generate();
		assertEquals(new Long(2), g.getId());
		testHelper.verifyGroup(g, longGenerator);
	}

}
