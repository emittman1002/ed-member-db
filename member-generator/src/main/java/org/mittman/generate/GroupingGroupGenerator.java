package org.mittman.generate;

import org.mittman.generate.domain.Group;
import org.mittman.generate.strategy.GroupingStrategy;
import org.mittman.generate.strategy.SingleGroupingStrategy;

import lombok.Getter;
import lombok.Setter;

/**
 * A GroupGenerator that sometimes returns a group that was
 * previously created.  Objects associated with the same
 * group are grouped together.
 * 
 * Grouping of objects is determined by a GroupingStrategy.
 * The default strategy puts everything into a single group. 
 * 
 * @author Edward Mittman
 *
 * @param <M> the type of members to be grouped
 * @param <I> the type of a group's ID
 */
public class GroupingGroupGenerator<I,M> extends GroupGenerator<I> {
	@Getter@Setter
	private GroupingStrategy<Group<I>,M> groupingStrategy;

	public GroupingGroupGenerator(Class<I> idClass) {
		super(idClass);	
		groupingStrategy = new SingleGroupingStrategy<Group<I>,M>();
	}

	@Override
	protected Group<I> create() {
		Group<I> g = groupingStrategy.group(null);
		if (g==null) {
			g = super.create();
			groupingStrategy.added(g, null);
		}

		return g;
	}

	
}
