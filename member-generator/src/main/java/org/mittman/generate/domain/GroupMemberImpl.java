package org.mittman.generate.domain;

import lombok.Getter;
import lombok.Setter;

public class GroupMemberImpl<M, G> extends MemberImpl<M> implements GroupMember<M, G> {
	@Getter@Setter
	private Group<G> group;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( !super.equals(obj)) {
			return false;
		}

		@SuppressWarnings("unchecked")
		GroupMemberImpl<G,M> other = (GroupMemberImpl<G,M>) obj;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;

		return true;
	}

	@Override
	public String toString() {
		String s = super.toString();
		s = getClass().getSimpleName() + s.substring( s.indexOf('[') );
		
		String g;
		if (group==null) {
			g = "[null]";
		}
		else {
			g = group.toString();
		}
		
		return s.substring(0, s.length()-1) + ", group=" + g + "]";
	}
}
