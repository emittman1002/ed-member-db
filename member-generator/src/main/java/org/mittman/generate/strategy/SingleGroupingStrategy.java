package org.mittman.generate.strategy;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * A strategy that places all members in the same group
 * 
 * @author Edward Mittman
 *
 * @param <M> the type of the member objects
 * @param <G> the type of the group objects
 */
public class SingleGroupingStrategy<G,M> implements GroupingStrategy<G,M> {
	@Getter(AccessLevel.PACKAGE)@Setter(AccessLevel.PACKAGE)
	private G theGroup;
	
	@Override
	public G group(M member) {
		return theGroup;
	}

	@Override
	public void added(G group, M member) throws UnsupportedGroupingException {
		if (group==null) {
			throw new UnsupportedGroupingException("Null groups are not allowed");
		}
		if (theGroup!=null && !theGroup.equals(group)) {
			throw new UnsupportedGroupingException("Strategy only supports a single group");
		}
		
		if (theGroup==null) {
			theGroup = group;
		}
	}

	@Override
	public void removed(G group, M member) throws UnsupportedGroupingException {
		// Ignore it
	}
}
