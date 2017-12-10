package org.mittman.generate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mittman.generate.domain.Member;
import org.mittman.generate.domain.MemberImpl;

import lombok.Setter;

@Setter
public class MemberGeneratorTest {
	private GeneratorTestHelper testHelper;
	private MemberGenerator<Long> longGenerator;
	private MemberGenerator<String> stringGenerator;
	

	@Before
	public void setUp() throws Exception {
		testHelper = new GeneratorTestHelper();
		longGenerator = new MemberGenerator<Long>(Long.class);
		stringGenerator = new MemberGenerator<String>(String.class);
	}

	@Test
	public void constructorInitializesFields() {
		verifyConstructor(longGenerator);
		verifyConstructor(stringGenerator);
	}
	
	private void verifyConstructor(MemberGenerator<?> gen) {
		assertNotNull(gen.getIdGenerator());
		assertTrue( Long.class.equals(gen.getIdClass()) ||
				String.class.equals(gen.getIdClass()) );
		assertNotNull( gen.getPersonNameGenerator() );
		assertFalse( gen.isUseIdToGenerateNames() );
		assertNotNull( gen.getSsnGenerator() );
		assertNotNull( gen.getDobGenerator() );
		assertTrue( gen.isValidSsnOnly() );
	}

	@Test
	public void testGetMinAge() {
		final int AGE = 100;
		
		longGenerator.getDobGenerator().setMinAge(AGE);
		assertEquals(AGE, longGenerator.getMinAge());
		
		stringGenerator.getDobGenerator().setMinAge(AGE);
		assertEquals(AGE, stringGenerator.getMinAge());
	}

	@Test
	public void testSetMinAge() {
		final int AGE = 1000;
		
		longGenerator.setMinAge(AGE);
		assertEquals(AGE, longGenerator.getDobGenerator().getMinAge());
		
		stringGenerator.setMinAge(AGE);
		assertEquals(AGE, stringGenerator.getDobGenerator().getMinAge());
	}

	@Test
	public void testGetMaxAge() {
		final int AGE = 1000;
		
		longGenerator.getDobGenerator().setMaxAge(AGE);
		assertEquals(AGE, longGenerator.getMaxAge());
		
		stringGenerator.getDobGenerator().setMaxAge(AGE);
		assertEquals(AGE, stringGenerator.getMaxAge());
	}

	@Test
	public void testSetMaxAge() {
		final int AGE = 1000;
		
		longGenerator.setMaxAge(AGE);
		assertEquals(AGE, longGenerator.getDobGenerator().getMaxAge());
		
		stringGenerator.setMaxAge(AGE);
		assertEquals(AGE, stringGenerator.getDobGenerator().getMaxAge());
	}
	
	@Test
	public void testIsValidSsnOnly() {
		SsnGenerator gen = longGenerator.getSsnGenerator();
		boolean b = gen.isValidOnly();
		assertEquals(b, longGenerator.isValidSsnOnly());
		
		b = !b;
		gen.setValidOnly(b);
		assertEquals(b, longGenerator.isValidSsnOnly());
		
		gen = stringGenerator.getSsnGenerator();
		b = gen.isValidOnly();
		assertEquals(b, stringGenerator.isValidSsnOnly());
		
		b = !b;
		gen.setValidOnly(b);
		assertEquals(b, stringGenerator.isValidSsnOnly());
	}
	
	@Test
	public void testSetValidSsnOnly() {
		SsnGenerator gen = longGenerator.getSsnGenerator();
		boolean b = !gen.isValidOnly();
		longGenerator.setValidSsnOnly(b);
		assertEquals(b, gen.isValidOnly());
		
		gen = stringGenerator.getSsnGenerator();
		b = !gen.isValidOnly();
		stringGenerator.setValidSsnOnly(b);
		assertEquals(b, gen.isValidOnly());
	}

	@Test
	public void testCreate() {
		Member<Long> m1 = longGenerator.create();
		assertNotNull(m1);
		
		Member<String> m2 = stringGenerator.create();
		assertNotNull(m2);
	}

	@Test
	public void testPopulate() {
		Member<Long> m1 = new MemberImpl<Long>();
		longGenerator.populate(m1);
		testHelper.verifyMember(m1, longGenerator);

		Member<String> m2 = new MemberImpl<String>();
		stringGenerator.populate(m2);
		testHelper.verifyMember(m2, stringGenerator);
	}
	
	@Test
	public void testGenerate() {
		Member<Long> m1 = longGenerator.generate();
		testHelper.verifyMember(m1, longGenerator);

		Member<String> m2 = stringGenerator.generate();
		testHelper.verifyMember(m2, stringGenerator);
	}

}
