package org.mittman.generate;

import org.mittman.generate.domain.Member;
import org.mittman.generate.domain.MemberImpl;
import org.mittman.generate.domain.PersonName;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Generates members
 * 
 * @author Edward Mittman
 *
 * @param <I>
 */
@Getter(AccessLevel.PACKAGE)@Setter(AccessLevel.PACKAGE)
public class MemberGenerator<I> extends AbstractGenerator<Member<I>> {
	private LongIdGenerator idGenerator;
	private Class<?> idClass;
	private PersonNameGenerator personNameGenerator;
	@Getter@Setter
	private boolean useIdToGenerateNames;
	private SsnGenerator ssnGenerator;
	private DateOfBirthGenerator dobGenerator;

	
	public MemberGenerator(Class<I> idClass) {
		if (String.class.equals(idClass) || Long.class.equals(idClass)) {
			this.idClass = idClass;
		}
		else {
			throw new RuntimeException("Unsupported member ID class " + idClass.getName());
		}
		
		// ID generator
		idGenerator = new LongIdGenerator();
		
		// Other fields
		personNameGenerator = new PersonNameGenerator();
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
	protected Member<I> create() {
		return new MemberImpl<I>();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void populate(Member<I> member) {
		Long id = idGenerator.generate();

		if (Long.class.equals(idClass)) {
			member.setId((I) id);
		}
		else if (String.class.equals(idClass)) {
			member.setId((I) id.toString());
		}
		
		PersonName pn;
		if (useIdToGenerateNames) {
			populateNameFromId(member);
		}
		else {
			pn = personNameGenerator.generate();
			member.setFirstName(pn.getFirstName());
			member.setMiddleName(pn.getMiddleName());
			member.setLastName(pn.getLastName());
		}

		member.setSsn( ssnGenerator.generate() );		
		member.setDateOfBirth( dobGenerator.generate() );
	}
	
	private void populateNameFromId(Member<I> member) {
		String id = member.getId().toString();
		member.setFirstName("FirstName"+id);
		member.setMiddleName("MiddleName"+id);
		member.setLastName("LastName"+id);
	}
}
