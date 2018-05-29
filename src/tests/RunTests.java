package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({  
	MapTest.class,
	EditMapTest.class,
	CelluleTest.class
})
public class RunTests  {
	public RunTests() {
	}
}

