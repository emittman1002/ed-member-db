package org.mittman.generate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Collection;

import org.mittman.generate.domain.Group;
import org.mittman.generate.domain.GroupMember;
import org.mittman.generate.domain.Member;

public class GeneratorTestHelper {
	public static final String TEST_NAME = "TEST NAME";
	public static  final Long TEST_GROUP_NUMBER = 123456789L;
	
	/**
	 * Verify whether a member is valid
	 * @param mem
	 */
	public <M> void verifyMember(Member<M> mem, MemberGenerator<M> memberGenerator) {
		M id = mem.getId();
		assertNotNull(id);

		if (memberGenerator.isUseIdToGenerateNames()) {
			assertEquals("FirstName"+id, mem.getFirstName());
			assertEquals("MiddleName"+id, mem.getMiddleName());
			assertEquals("LastName"+id, mem.getLastName());
		}
		else {
			PersonNameGenerator pn = memberGenerator.getPersonNameGenerator();
			Collection<String> c = pn.getFirstNames();
			assertTrue( c.contains(mem.getFirstName()) );
			
			c = pn.getMiddleNames();
			assertTrue( c.contains(mem.getMiddleName()) );
			
			c = pn.getLastNames();
			assertTrue( c.contains(mem.getLastName()) );
		}

		verifySsn(mem.getSsn());
		
		DateOfBirthGenerator dobGen = memberGenerator.getDobGenerator();
		verifyDob(mem.getDateOfBirth(), dobGen.getMinAge(), dobGen.getMaxAge());
	}
	
	/**
	 * Verify whether a group is valid
	 * 
	 * @param group
	 * @param groupGenerator
	 */
	public <I> void verifyGroup(Group<I> group, GroupGenerator<I> groupGenerator) {
		I id = group.getId();
		assertNotNull(id);
		
		assertNotNull(group.getName());
		assertNotNull(group.getGroupNumber());
		
		if (groupGenerator.isUseIdForNamesAndNumbers()) {
			assertEquals(group.getName(), "GroupName" + id.toString());
			assertEquals(group.getGroupNumber(), "GroupNumber" + id.toString());
		}
		else {
			Generator<String> nameGen = groupGenerator.getNameGenerator();
			if (nameGen instanceof StringGenerator) {
				assertTrue( ((StringGenerator) nameGen).getValues().contains(group.getName()) );
			}
			
			Generator<Long> numberGen = groupGenerator.getGroupNumberGenerator();
			if (numberGen instanceof LongIdGenerator) {
				Long num = ((LongIdGenerator)numberGen).getNextId().get();
				assertEquals(num.toString(), group.getGroupNumber());
			}
		}
	}
	
	/**
	 * Verify whether a group member is valid
	 * @param groupMember
	 * @param groupMemberGenerator
	 */
	public <M,G> void verifyGroupMember(GroupMember<M,G> groupMember, GroupMemberGenerator<M,G> groupMemberGenerator) {
		verifyMember(groupMember, groupMemberGenerator.getMemberGenerator());
		verifyGroup(groupMember.getGroup(), groupMemberGenerator.getGroupGenerator());
	}
	
	/**
	 * Verify whether a date of birth is valid
	 * @param dob
	 */
	public void verifyDob(LocalDate dob, int minAge, int maxAge) {
		int age = LocalDate.now().getYear() - dob.getYear();
		assertTrue( age >= minAge );
		assertTrue( age <= maxAge );
	}
	
	/**
	 * Verify whether an SSN is valid
	 * @param ssn
	 */
	public void verifySsn(String ssn) {
		assertTrue("Incorrect formatting of SSN " + ssn,
				isSsnFormattedCorrectly(ssn) );
		
		String firstThree = ssn.substring(0,3);
		assertTrue("SSN begins with invalid range " + firstThree,
				isValidFirstThreeOfSsn(firstThree) );
	}
	
	/**
	 * Determines whether an SSN is valid
	 * 
	 * @param ssn
	 * @return
	 */
	public boolean isValidSsn(String ssn) {
		boolean ret = isSsnFormattedCorrectly(ssn);

		if (ret) {
			ret = isValidFirstThreeOfSsn( ssn.substring(0,3) ); 
		}

		return ret;
	}
	
	/**
	 * Validates whether the first three digits of an SSN are valid
	 * 
	 * @param firstThree
	 * @return
	 */
	public boolean isValidFirstThreeOfSsn(String firstThree) {
		int []range = findRangeOfFirstThree( Integer.valueOf(firstThree) );
		return (range == null) ;
	}
	
	private boolean isSsnFormattedCorrectly(String ssn) {
		boolean ret = ssn.length()==11;

		if (ret) {
			ret = ( Character.isDigit( ssn.charAt(0) ) &&
					Character.isDigit( ssn.charAt(1) ) &&
					Character.isDigit( ssn.charAt(2) ) &&
					'-' == ssn.charAt(3) &&
					Character.isDigit( ssn.charAt(4) ) &&
					Character.isDigit( ssn.charAt(5) ) &&
					'-' == ssn.charAt(6) &&
					Character.isDigit( ssn.charAt(7) ) &&
					Character.isDigit( ssn.charAt(8) ) &&
					Character.isDigit( ssn.charAt(9) ) &&
					Character.isDigit( ssn.charAt(10) )
					);
		}

		return ret;
	}
	
	public final int[][]invalidSsnRanges = {
			{0, 0},
			{237, 246},
			{587, 679},
			{681, 699},
			{734, 772},
			{900, 999}
	};
	
	private int [] findRangeOfFirstThree(int firstThree) {
		for(int []range: invalidSsnRanges) {
			if (firstThree>=range[0] && firstThree<=range[1]) {
				return range;
			}
		}
		
		return null;
	}

	/**
	 * Verify the constructor of a group generator
	 * @param gen
	 * @param clazz
	 */
	public void verifyGroupGeneratorConstructor(GroupGenerator<?> gen, Class<?> clazz) {
		assertNotNull(gen.getIdGenerator());
		assertEquals(clazz, gen.getIdClass());
		assertNotNull(gen.getNameGenerator());
		assertNotNull(gen.getGroupNumberGenerator());
		assertFalse(gen.isUseIdForNamesAndNumbers());
	}
	
	public static class SingleNameGenerator implements Generator<String> {
		@Override
		public String generate() {
			return TEST_NAME;
		}
	}
	
	public static class SingleGroupNumberGenerator implements Generator<Long> {
		@Override
		public Long generate() {
			return TEST_GROUP_NUMBER;
		}
	}
	
	/**
	 * Verify that a group generator populates the groups it generates
	 * @param gen
	 * @param clazz
	 */
	public <I> void verifyPopulatesAGroup(GroupGenerator<I> gen, Class<I> clazz) {
		gen.setNameGenerator(new SingleNameGenerator());
		gen.setGroupNumberGenerator(new SingleGroupNumberGenerator());
		
		Group<I> g = gen.generate();
		assertEquals(new Long(1).toString(), g.getId().toString());
		assertEquals(clazz, g.getId().getClass());
		assertEquals(TEST_NAME, g.getName());
		assertEquals(TEST_GROUP_NUMBER.toString(), g.getGroupNumber());
		
		g = gen.generate();
		assertEquals(new Long(2).toString(), g.getId().toString());
		assertEquals(clazz, g.getId().getClass());
		assertEquals(TEST_NAME, g.getName());
		assertEquals(TEST_GROUP_NUMBER.toString(), g.getGroupNumber());
		
		verifyGroup(g, gen);
	}
}
