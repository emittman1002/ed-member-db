package org.mittman.generate.domain;

import java.time.LocalDate;

/**
 * A Member of some group
 * @author Edward Mittman
 *
 */
public interface Member<I> extends Identifiable<I> {
	String getFirstName();
	void setFirstName(String firstName);
	
	String getMiddleName();
	void setMiddleName(String middleName);
	
	String getLastName();
	void setLastName(String lastName);
	
	String getSsn();
	void setSsn(String ssn);
	
	LocalDate getDateOfBirth();
	void setDateOfBirth(LocalDate dob);
}
