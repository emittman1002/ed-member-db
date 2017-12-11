package org.mittman.generate.domain;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class GroupImpl<I> implements Group<I> {
	private I id;
	private String name;
	private String groupNumber;
	

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
		GroupImpl<I> other = (GroupImpl<I>) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getName() + "[id=" + id + ", name=" + name + ", groupNumber=" + groupNumber + "]";
	}

}
