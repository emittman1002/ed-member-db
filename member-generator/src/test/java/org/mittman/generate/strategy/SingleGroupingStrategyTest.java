package org.mittman.generate.strategy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mittman.generate.domain.Group;
import org.mittman.generate.domain.GroupImpl;
import org.mittman.generate.domain.Member;
import org.mittman.generate.domain.MemberImpl;

public class SingleGroupingStrategyTest {
	private SingleGroupingStrategy<Group<Long>,Object> strategy;
	private Group<Long> group;
	private Member<Long> member;

	@Before
	public void setUp() throws Exception {
		strategy = new SingleGroupingStrategy<Group<Long>,Object>();
		group = new GroupImpl<Long>();
		member = new MemberImpl<Long>();
	}

	@Test
	public void constructorInitializesObject() {
		assertNull(strategy.getTheGroup());
	}
	
	@Test
	public void testGroup() {
		Group<Long> g = strategy.group(member);
		assertNull(g);
		
		g = strategy.group(null);
		assertNull(g);
		
		strategy.setTheGroup(group);
		g = strategy.group(member);
		assertTrue(g == group);
		
		g = strategy.group(null);
		assertTrue(g == group);
	}

	@Test
	public void testAdded() {
		strategy.added(group, member);
		
		assertEquals(group, strategy.getTheGroup());
	}

	@Test
	public void testRemoved() {
		strategy.setTheGroup(group);
		
		strategy.removed(group, member);
		assertEquals(group, strategy.getTheGroup());
	}

}
