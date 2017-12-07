package org.mittman.generate;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	org.mittman.generate.impl.DateOfBirthGeneratorTest.class,
	org.mittman.generate.impl.LongIdGeneratorTest.class,
	org.mittman.generate.impl.SsnGeneratorTest.class
})
public class AllTests {

}
