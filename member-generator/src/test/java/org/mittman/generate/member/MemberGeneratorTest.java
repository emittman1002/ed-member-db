package org.mittman.generate.member;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mittman.generate.domain.Member;
import org.mittman.generate.impl.SsnGenerator;

public class MemberGeneratorTest {
	private final int NUM_TESTS = 1000;
	private MemberGenerator generator;
	
	
	@Before
	public void setUp() throws Exception {
		generator = new MemberGenerator();
	}

	@Test
	public void constructorInitializesFields() {
		assertNotNull(generator.getIdGenerator());
		
		assertNotNull(generator.getSsnGenerator());
		assertTrue(generator.isValidSsnOnly());
		
		assertNotNull(generator.getDobGenerator());
		assertTrue(generator.getSsnGenerator().isValidOnly());


		assertEquals(15, generator.getMinAge());
		assertEquals(150, generator.getMaxAge());
	}

	@Test
	public void testGenerate() {
		for(int i=0; i<NUM_TESTS; ++i) {
			Member<?> m = generator.generate();
			verifyMember(m);
		}
	}
	
	final SsnGenerator ssnGen = new SsnGenerator();
	
	private void verifyMember(Member<?> mem) {
		MemberImpl m = (MemberImpl) mem;

		Object id = m.getMemberId();
		assertNotNull(id);

		String str = "Member" + id.toString();
		assertEquals(str + "FirstName", m.getFirstName());
		assertEquals(str + "MiddleName", m.getMiddleName());
		assertEquals(str + "LastName", m.getLastName());

		assertTrue( ssnGen.isValid(m.getSsn()) );

		verifyDob( m.getDateOfBirth() );
	}
	
	private void verifyDob(LocalDate dob) {
		int age = LocalDate.now().getYear() - dob.getYear();
		assertTrue( age >= generator.getMinAge() );
		assertTrue( age <= generator.getMaxAge() );
	}

}
