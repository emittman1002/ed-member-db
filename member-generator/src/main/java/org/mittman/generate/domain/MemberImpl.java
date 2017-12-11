package org.mittman.generate.domain;

import java.io.Serializable;
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
@Getter@Setter
public class MemberImpl<I> implements Member<I>, Serializable {
	private static final long serialVersionUID = 1L;

	private I id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String ssn;
	private LocalDate dateOfBirth;


	public MemberImpl() {
		firstName = "";
		middleName = "";
		lastName = "";
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
	public String getFirstName() {
		return firstName;
	}
	@Override
	public void setFirstName(String firstName) {
		if (firstName!=null) {
			this.firstName = firstName;
		}
		else {
			this.firstName = "";
		}
	}

	@Override
	public String getMiddleName() {
		return middleName;
	}
	@Override
	public void setMiddleName(String middleName) {
		if (middleName!=null) {
			this.middleName = middleName;
		}
		else {
			this.middleName = "";
		}
	}

	@Override
	public String getLastName() {
		return lastName;
	}
	@Override
	public void setLastName(String lastName) {
		if (lastName!=null) {
			this.lastName = lastName;
		}
		else {
			this.lastName = "";
		}
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "[id=" + id + ", firstName=" + getFirstName() + ", middleName=" + getMiddleName()
				+ ", lastName=" + getLastName() + ", ssn=" + getSsn() + ", dateOfBirth=" + getDateOfBirth() + "]";
	}
}
