package org.mittman.generate.strategy;

/**
 * A grouping strategy that puts every member in a separate group
 * 
 * @author Edward Mittman
 *
 * @param <G> the class of the groups
 * @param <M> the class of the members
 */
public class IndividualGroupingStrategy<G, M> implements GroupingStrategy<G, M> {

	@Override
	public G group(M member) throws UnsupportedGroupingException {
		return null;
	}

	@Override
	public void added(G group, M member) throws UnsupportedGroupingException {
		// ignore
	}

	@Override
	public void removed(G group, M member) throws UnsupportedGroupingException {
		// ignore
	}

}
