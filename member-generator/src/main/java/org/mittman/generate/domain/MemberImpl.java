package org.mittman.generate.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

/**
 * Implementation of a Member
 * 
 * @author Edward Mittman
 *
 * @param <I>
 */
public class MemberImpl<I> implements Member<I> {
	@Getter@Setter
	private I id;
	@Getter@Setter
	private PersonName personName;
	@Getter@Setter
	private String ssn;
	@Getter@Setter
	private LocalDate dateOfBirth;


	public MemberImpl() {
		personName = new PersonName();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		
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
		@SuppressWarnings("unchecked")
		MemberImpl<I> other = (MemberImpl<I>) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "MemberImpl [id=" + id + ", firstName=" + getFirstName() + ", middleName=" + getMiddleName()
				+ ", lastName=" + getLastName() + "]";
	}

	@Override
	public String getFirstName() {
		return personName.getFirstName();
	}
	@Override
	public void setFirstName(String firstName) {
		personName.setFirstName(firstName);
	}

	@Override
	public String getMiddleName() {
		return personName.getMiddleName();
	}
	@Override
	public void setMiddleName(String middleName) {
		personName.setMiddleName(middleName);
	}

	@Override
	public String getLastName() {
		return personName.getLastName();
	}
	@Override
	public void setLastName(String lastName) {
		personName.setLastName(lastName);
	}
}
