package org.mittman.generate.member;

import org.mittman.generate.domain.Generator;
import org.mittman.generate.domain.Member;
import org.mittman.generate.impl.DateOfBirthGenerator;
import org.mittman.generate.impl.LongIdGenerator;
import org.mittman.generate.impl.SsnGenerator;

import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PACKAGE)
public class MemberGenerator implements Generator<Member<String>> {
	private LongIdGenerator idGenerator;
	private SsnGenerator ssnGenerator;
	private DateOfBirthGenerator dobGenerator;

	
	public MemberGenerator() {
		idGenerator = new LongIdGenerator();
		ssnGenerator = new SsnGenerator();
		dobGenerator = new DateOfBirthGenerator();
	}
	
	public boolean isValidSsnOnly() {
		return ssnGenerator.isValidOnly();
	}
	public void setValidSsnOnly(boolean validOnly) {
		ssnGenerator.setValidOnly(validOnly);
	}
	
	public int getMinAge() {
		return dobGenerator.getMinAge();
	}
	public void setMinAge(int minAge) {
		dobGenerator.setMinAge(minAge);
	}
	
	public int getMaxAge() {
		return dobGenerator.getMaxAge();
	}
	public void setMaxAge(int maxAge) {
		dobGenerator.setMaxAge(maxAge);
	}
	
	@Override
	public Member<String> generate() {
		Long id = idGenerator.generate();
		
		MemberImpl m = new MemberImpl();
		m.setMemberId(id.toString());
		
		String base = "Member" + m.getMemberId();
		
		m.setFirstName(base + "FirstName");
		m.setMiddleName(base + "MiddleName");
		m.setLastName(base + "LastName");
		
		m.setSsn( ssnGenerator.generate() );
		
		m.setDateOfBirth( dobGenerator.generate() );
		
		return m;
	}

}
