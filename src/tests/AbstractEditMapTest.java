package tests;

import org.junit.After;
import org.junit.Before;

import servives.EditMapService;

public abstract class AbstractEditMapTest extends AbstractMapTest {
	protected EditMapService editMap;

	protected AbstractEditMapTest() {
		editMap = null;
	}

	protected final void setMap(EditMapService editMap) {
		this.editMap = editMap;
		setMap(editMap);
	}

	@Before
	public abstract void beforeTests();

	@After
	public final void afterTests() {
		this.editMap = null;
		map = null;
	}
	/*isReachable*/
	//pre : CellNature(M,x1,y1) 6 = WLL and CellNature(M,x2,y2) 6 = WLL
	
	
	
	/*isReady*/

}
