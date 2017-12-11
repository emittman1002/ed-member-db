package org.mittman.generate;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mittman.generate.domain.Group;
import org.mittman.generate.domain.GroupImpl;
import org.mittman.generate.strategy.GroupingStrategy;
import org.mittman.generate.strategy.SingleGroupingStrategy;
import org.mittman.generate.strategy.UnsupportedGroupingException;

public class GroupingGroupGeneratorTest {
	private GeneratorTestHelper testHelper;
	private GroupingGroupGenerator<Long,Object> generator;


	@Before
	public void setUp() throws Exception {
		testHelper = new GeneratorTestHelper();
		generator = new GroupingGroupGenerator<Long,Object>(Long.class);
	}

	@Test
	public void constructorInitializesFields() {
		testHelper.verifyGroupGeneratorConstructor(generator, Long.class);
		assertTrue(generator.getGroupingStrategy() instanceof SingleGroupingStrategy);
	}
	
	@Test
	public void createUsesDefaultStrategy() {
		Group<Long> g1 = generator.create();
		assertNull(g1.getId());
		
		Group<Long> g2 = generator.create();
		assertTrue(g1 == g2);
		
		g2 = generator.create();
		assertTrue(g1 == g2);
	}

	private class AbStrategy implements GroupingStrategy<Group<Long>, Object> {
		private Group<Long> a;
		private Group<Long> b;
		private boolean useA;
		
		private AbStrategy() {
			a = new GroupImpl<Long>();
			a.setId(1L);
			
			b = new GroupImpl<Long>();
			b.setId(2L);
			
			useA = true;
		}

		@Override
		public Group<Long> group(Object member) throws UnsupportedGroupingException {
			if(useA) {
				return a;
			}
			return b;
		}

		@Override
		public void added(Group<Long> group, Object member) throws UnsupportedGroupingException {
			// ignored
		}

		@Override
		public void removed(Group<Long> group, Object member) throws UnsupportedGroupingException {
			// ignored
		}
		
	}
	
	@Test
	public void createUsesAlternateStrategy() {
		AbStrategy strategy = new AbStrategy();
		generator.setGroupingStrategy(strategy);

		Group<Long> g = generator.create();
		assertTrue(g == strategy.a);

		g = generator.create();
		assertTrue(g == strategy.a);
		
		g = generator.create();
		assertTrue(g == strategy.a);
		
		strategy.useA = false;
		g = generator.create();
		assertTrue(g == strategy.b);

		g = generator.create();
		assertTrue(g == strategy.b);
		
		g = generator.create();
		assertTrue(g == strategy.b);
		
		strategy.useA = true;
		g = generator.create();
		assertTrue(g == strategy.a);
	}

}
