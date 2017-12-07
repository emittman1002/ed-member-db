package org.mittman.generate.member;

import java.time.LocalDate;

import org.mittman.generate.domain.Member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberImpl implements Member<String> {
	private String memberId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String ssn;
	private LocalDate dateOfBirth;

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memberId == null) ? 0 : memberId.hashCode());

		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberImpl other = (MemberImpl) obj;
		if (memberId == null) {
			if (other.memberId != null)
				return false;
		} else if (!memberId.equals(other.memberId))
			return false;

		return true;
	}

}
