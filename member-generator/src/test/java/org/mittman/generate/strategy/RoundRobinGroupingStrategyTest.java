package org.mittman.generate.strategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mittman.generate.domain.Group;
import org.mittman.generate.domain.GroupImpl;
import org.mittman.generate.domain.Member;

public class RoundRobinGroupingStrategyTest {
	private RoundRobinGroupingStrategy<Group<Long>, Member<Long>> strategy;
	private Group<Long> group1;
	private Group<Long> group2;
	

	@Before
	public void setUp() throws Exception {
		strategy = new RoundRobinGroupingStrategy<Group<Long>, Member<Long>>();
		group1 = new GroupImpl<Long>();
		group1.setId(1L);
		group2 = new GroupImpl<Long>();
		group2.setId(2L);
	}

	@Test
	public void defaultConstructorInitializesFields() {
		assertEquals(2, strategy.getNumberOfGroups());
		assertEquals(0, strategy.getNextGroupToAddTo());
		assertTrue(strategy.getGroups().isEmpty());
	}

	@Test
	public void singleArgumentConstructorInitializesFields() {
		strategy = new RoundRobinGroupingStrategy<Group<Long>, Member<Long>>(10);
		assertEquals(10, strategy.getNumberOfGroups());
		assertEquals(0, strategy.getNextGroupToAddTo());
		assertTrue(strategy.getGroups().isEmpty());
	}

	@Test
	public void testGroup() {
		Group<Long> g = strategy.group(null);
		assertNull(g);
		
		g = strategy.group(null);
		assertNull(g);
		
		List<Group<Long>> groups = strategy.getGroups();
		groups.add(group1);
		
		g = strategy.group(null);
		assertNull(g);
		
		groups.add(group2);
		g = strategy.group(null);
		assertEquals(group1, g);
		
		g = strategy.group(null);
		assertEquals(group2, g);
		
		g = strategy.group(null);
		assertEquals(group1, g);
		
		g = strategy.group(null);
		assertEquals(group2, g);
		
		// Ignores any groups beyond numberOfGroups setting
		Group<Long> group3 = new GroupImpl<Long>();
		group3.setId(3L);
		groups.add(group3);
		
		g = strategy.group(null);
		assertEquals(group1, g);
		
		g = strategy.group(null);
		assertEquals(group2, g);
		
		g = strategy.group(null);
		assertEquals(group1, g);
	}

	@Test
	public void testAdded() {
		List<Group<Long>> groups = strategy.getGroups();

		// Doesn't add nulls
		try {
			strategy.added(null, null);
			fail("Should have thrown UnsupportedGroupingException for null group");
		}
		catch(UnsupportedGroupingException e) {
			// ok
		}
		
		// Adds new group
		strategy.added(group1, null);
		assertEquals(1, groups.size());

		// Doesn't add dups
		strategy.added(group1, null);
		assertEquals(1, groups.size());

		// Adds distinct
		strategy.added(group2, null);
		assertEquals(2, groups.size());

		// Doesn't add more than specified number
		Group<Long> group3 = new GroupImpl<Long>();
		strategy.added(group3, null);
		assertEquals(2, groups.size());
		
		strategy.setNumberOfGroups(3);
		strategy.added(group3, null);
		assertEquals(3, groups.size());
	}

	@Test
	public void testRemoved() {
		List<Group<Long>> groups = strategy.getGroups();
		groups.add(group1);
		groups.add(group2);
		
		strategy.removed(group1, null);
		assertEquals(2, groups.size());
		
		strategy.removed(group2, null);
		assertEquals(2, groups.size());
	}

}
