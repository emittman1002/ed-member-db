package org.mittman.generate.domain;

/**
 * A person's name, consisting of some or all of
 * the following:
 *   a first name
 *   a middle name
 *   a last name
 * 
 * When any of the above do not apply the corresponding
 * member variable must be an empty string-- nulls are not allowed.
 * 
 * @author Edward Mittman
 *
 */
public class PersonName {
	private String firstName;
	private String middleName;
	private String lastName;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		if (firstName==null) {
			firstName = "";
		}
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		if (middleName==null) {
			middleName = "";
		}
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		if (lastName==null) {
			lastName = "";
		}
		this.lastName = lastName;
	}
	
	
	
}
