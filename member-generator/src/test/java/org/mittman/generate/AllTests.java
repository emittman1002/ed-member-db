package org.mittman.generate;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	org.mittman.generate.DateOfBirthGeneratorTest.class,
	org.mittman.generate.LongIdGeneratorTest.class,
	org.mittman.generate.SsnGeneratorTest.class,
	org.mittman.generate.PersonNameGeneratorTest.class,
	org.mittman.generate.GroupMemberGeneratorTest.class,
	org.mittman.generate.MemberGeneratorTest.class,
	org.mittman.generate.GroupMemberGeneratorTest.class,
	org.mittman.generate.strategy.SingleGroupingStrategyTest.class,
	org.mittman.generate.GroupingGroupGeneratorTest.class
})
public class AllTests {

}
