package org.mittman.generate.strategy;

/**
 * A strategy for grouping objects
 *
 * Typical usage:
 * 
 * // Create a member
 * Member member = ...;
 * 
 * try {
 * 	Group group = strategy.group(member);
 * 
 * 	if (group==null) {
 * 		group = ...;	// create a new group
 * 	}
 * 
 * 	// Associate the member with the group
 * 	member.setGroup(group);
 * 
 *	// Tell the strategy there's a new group member
 *	strategy.added(group, member);
 * }
 * catch(UnsupportedGroupingException uge) {
 * 	...
 * }
 * 
 * @author Edward Mittman
 */
public interface GroupingStrategy<G,M> {
	/**
	 * Determine the group for a member.
	 * 
	 * @param member
	 * @param <M> the type of the members of the group
	 * @param <G> the type of a group
	 * @return the group to add the member to, or null if a new group should be created
	 * @throws UnsupportedGroupingException if the member cannot be grouped
	 */
	G group(M member) throws UnsupportedGroupingException;
	
	/**
	 * Tell a strategy that a member has been added to a group.
	 * It's up to a strategy to determine how it handles null
	 * groups or members
	 * 
	 * @param group
	 * @param member
	 * @throws UnsupportedGroupingException if the strategy cannot support the specified grouping
	 */
	void added(G group, M member) throws UnsupportedGroupingException;
	
	/**
	 * Tell a strategy that a member has been removed from a group.
	 * It's up to a strategy to determine how it handles null
	 * groups or members
	 * 
	 * @param group
	 * @param member
	 * @throws UnsupportedGroupingException if the strategy cannot support removal of the specified grouping
	 */
	void removed(G group, M member) throws UnsupportedGroupingException;
}
