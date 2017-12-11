package org.mittman.generate;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mittman.generate.domain.GroupMember;

public class GroupMemberGeneratorTest {
	private GeneratorTestHelper testHelper;
	private GroupMemberGenerator<Long,Long> longGenerator;
	private GroupMemberGenerator<String,String> stringGenerator;
	private MemberGeneratorTest memberGeneratorTest;
	private GroupGeneratorTest groupGeneratorTest;
	
	
	@Before
	public void setUp() throws Exception {
		testHelper = new GeneratorTestHelper();
		
		longGenerator = new GroupMemberGenerator<Long,Long>(Long.class, Long.class);
		stringGenerator = new GroupMemberGenerator<String,String>(String.class, String.class);
		
		memberGeneratorTest = new MemberGeneratorTest();
		memberGeneratorTest.setUp();
		memberGeneratorTest.setTestHelper(testHelper);
		memberGeneratorTest.setLongGenerator(longGenerator.getMemberGenerator());
		memberGeneratorTest.setStringGenerator(stringGenerator.getMemberGenerator());
		
		groupGeneratorTest = new GroupGeneratorTest();
		groupGeneratorTest.setUp();
		groupGeneratorTest.setTestHelper(testHelper);
		groupGeneratorTest.setLongGenerator(longGenerator.getGroupGenerator());
		groupGeneratorTest.setStringGenerator(stringGenerator.getGroupGenerator());
	}

	@Test
	public void constructorInitializesFields() {
		memberGeneratorTest.constructorInitializesFields();
		groupGeneratorTest.constructorInitializesFields();
	}

	@Test
	public void generateFullyPopulatesResult() {
		GroupMember<Long,Long> m1 = longGenerator.generate();
		testHelper.verifyGroupMember(m1, longGenerator);
		
		GroupMember<String,String> m2 = stringGenerator.generate();
		testHelper.verifyGroupMember(m2, stringGenerator);
	}

	@Test
	public void testIsValidSsnsOnly() {
		MemberGenerator<Long> longGen = longGenerator.getMemberGenerator();
		boolean b = longGen.isValidSsnOnly();
		assertEquals(b, longGenerator.isValidSsnsOnly());

		b = !b;
		longGen.setValidSsnOnly(b);
		assertEquals(b, longGenerator.isValidSsnsOnly());

		MemberGenerator<String> stringGen = stringGenerator.getMemberGenerator();
		b = stringGen.isValidSsnOnly();
		assertEquals(b, stringGenerator.isValidSsnsOnly());

		b = !b;
		stringGen.setValidSsnOnly(b);
		assertEquals(b, stringGenerator.isValidSsnsOnly());
	}
	
	@Test
	public void testSetValidSsnsOnly() {
		MemberGenerator<Long> longGen = longGenerator.getMemberGenerator();
		boolean b = !longGen.isValidSsnOnly();
		longGenerator.setValidSsnsOnly(b);
		assertEquals(b, longGen.isValidSsnOnly());

		MemberGenerator<String> stringGen = stringGenerator.getMemberGenerator();
		b = !stringGen.isValidSsnOnly();
		stringGenerator.setValidSsnsOnly(b);
		assertEquals(b, stringGen.isValidSsnOnly());
	}
	
	@Test
	public void testIsUseIdsForMemberNames() {
		MemberGenerator<Long> longGen = longGenerator.getMemberGenerator();
		boolean b = longGen.isUseIdToGenerateNames();
		assertEquals(b, longGenerator.isUseIdForMemberNames());
		
		b = !b;
		longGen.setUseIdToGenerateNames(b);
		assertEquals(b, longGenerator.isUseIdForMemberNames());
		
		MemberGenerator<String> stringGen = stringGenerator.getMemberGenerator();
		b = stringGen.isUseIdToGenerateNames();
		assertEquals(b, stringGenerator.isUseIdForMemberNames());
		
		b = !b;
		stringGen.setUseIdToGenerateNames(b);
		assertEquals(b, stringGenerator.isUseIdForMemberNames());
	}
	
	@Test
	public void testSetUseIdForMemberNames() {
		MemberGenerator<Long> longGen = longGenerator.getMemberGenerator();
		boolean b = !longGen.isUseIdToGenerateNames();
		longGenerator.setUseIdForMemberNames(b);
		assertEquals(b, longGen.isUseIdToGenerateNames());

		MemberGenerator<String> stringGen = stringGenerator.getMemberGenerator();
		b = !stringGen.isUseIdToGenerateNames();
		stringGenerator.setUseIdForMemberNames(b);
		assertEquals(b, stringGen.isUseIdToGenerateNames());
	}
	
	@Test
	public void testIsUseIdForGroupNamesAndNumbers() {
		GroupGenerator<Long> longGen = longGenerator.getGroupGenerator();
		boolean b = longGen.isUseIdForNamesAndNumbers();
		assertEquals(b, longGenerator.isUseIdForGroupNamesAndNumbers());

		b = !b;
		longGen.setUseIdForNamesAndNumbers(b);
		assertEquals(b, longGenerator.isUseIdForGroupNamesAndNumbers());

		GroupGenerator<String> stringGen = stringGenerator.getGroupGenerator();
		b = stringGen.isUseIdForNamesAndNumbers();
		assertEquals(b, stringGenerator.isUseIdForGroupNamesAndNumbers());

		b = !b;
		stringGen.setUseIdForNamesAndNumbers(b);
		assertEquals(b, stringGenerator.isUseIdForGroupNamesAndNumbers());
	}
	
	@Test
	public void testSetUseIdForGroupNamesAndNumbers() {
		GroupGenerator<Long> longGen = longGenerator.getGroupGenerator();
		boolean b = !longGen.isUseIdForNamesAndNumbers();
		longGenerator.setUseIdForGroupNamesAndNumbers(b);
		assertEquals(b, longGen.isUseIdForNamesAndNumbers());

		GroupGenerator<String> stringGen = stringGenerator.getGroupGenerator();
		b = !stringGen.isUseIdForNamesAndNumbers();
		stringGenerator.setUseIdForGroupNamesAndNumbers(b);
		assertEquals(b, stringGen.isUseIdForNamesAndNumbers());
	}
}
