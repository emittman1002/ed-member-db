package org.mittman.generate.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * A grouping strategy that uses a round robin algorithm to
 * distribute members into distinct groups.  The limit on the number of groups
 * can be specified and defaults to 2.
 * 
 * @author Edward Mittman
 *
 * @param <G> the class of each group
 * @param <M> the class of group members
 */
@Getter(AccessLevel.PACKAGE)@Setter(AccessLevel.PACKAGE)
public class RoundRobinGroupingStrategy<G, M> implements GroupingStrategy<G, M> {
	private int numberOfGroups;
	private int nextGroupToAddTo;
	private List<G> groups;


	public RoundRobinGroupingStrategy() {
		this(2);
	}

	/**
	 * Create a new round robin strategy and specify the number
	 * of groups to distribute members into
	 * 
	 * @param numberOfGroups
	 * @throws IllegalArgumentException if the numberOfGroups is <= 0
	 */
	public RoundRobinGroupingStrategy(int numberOfGroups) throws IllegalArgumentException {
		if (numberOfGroups<=0) {
			throw new IllegalArgumentException("Number of groups must be greater than zero");
		}

		this.numberOfGroups = numberOfGroups;
		groups = new ArrayList<G>(numberOfGroups);
	}

	@Override
	public G group(M member) throws UnsupportedGroupingException {
		if (groups.isEmpty() || groups.size() < numberOfGroups) {
			return null;
		}

		G g = groups.get(nextGroupToAddTo);
		advance();

		return g;
	}

	@Override
	public void added(G group, M member) throws UnsupportedGroupingException {
		if (groups.isEmpty() || groups.size() < numberOfGroups) {
			if ( addGroup(group) ) {
				advance();	
			}
		}
	}

	protected boolean addGroup(G group) throws UnsupportedGroupingException {
		if (group==null) {
			throw new UnsupportedGroupingException("Null groups are not allowed");
		}

		if ( !groups.contains(group)) {
			groups.add(group);
			return true;
		}

		return false;
	}

	protected void advance() throws UnsupportedGroupingException {
		++nextGroupToAddTo;
		nextGroupToAddTo %= numberOfGroups;
	}

	@Override
	public void removed(G group, M member) throws UnsupportedGroupingException {
		// Do nothing
	}
	
	@Override
	public void initialize(Properties properties) {
		// nothing to initialize
	}

}
