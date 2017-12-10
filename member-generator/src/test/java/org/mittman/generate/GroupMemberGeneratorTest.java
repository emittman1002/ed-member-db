package org.mittman.generate;

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

}
