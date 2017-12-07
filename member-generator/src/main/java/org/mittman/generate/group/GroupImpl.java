package org.mittman.generate.group;

import org.mittman.generate.domain.Group;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class GroupImpl implements Group<String> {
	private String groupId;
	private String name;
	private String groupNumber;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
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
		GroupImpl other = (GroupImpl) obj;
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
			return false;
		return true;
	}

}
