package org.mittman.generate.domain;

/**
 * A Member that belongs to a group
 * 
 * @author Edward Mittman
 *
 * @param <M> type of the id for the member
 * @param <G> type of the id for the group
 */
public interface GroupMember<M,G> extends Member<M> {
	Group<G> getGroup();
	void setGroup(Group<G> group);
}
