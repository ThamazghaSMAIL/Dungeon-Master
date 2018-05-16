package tests;




import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import servives.MapService;
import tools.InvariantError;
import tools.PreconditionError;


public abstract class AbstractMapTest {

	
	private MapService map;
	

	protected AbstractMapTest() {
		map = null;
	}
	
	protected final MapService getMap() {
		return map;
	}

	protected final void setMap(MapService map) {
		this.map = map;
	}

	@Before
	public abstract void beforeTests();

	@After
	public final void afterTests() {
		map = null;
	}
	
	public boolean checkInvariant() {
		return false;
		
	}
	
	
	@Test
	public void test1_1(){

		//beforeTests();

		try {
			int h = 2;
			int w = 5;
			//Assert.assertTrue(h);
			if( h <= 0 ) {
				throw new PreconditionError("----------test 1_1 fail-----------");
			}
			
			if(w <=0 ) {
				throw new PreconditionError("----------test 1_1 fail-----------");
			}

			map.init(h,w);

			if( map.getHeight() != h || map.getWidth() != w )
				throw new InvariantError("----------test 1_1 fail-----------");

		} catch (Exception e) {
			e.printStackTrace();
		}


		//afterTests();	
	}
	
//	@Test
//	public void test1_2(){
//
//		//beforeTests();
//
//		try {
//			int h = - 2;
//			int w = 5;
//
//			if( h <= 0 ) {
//				throw new PreconditionError("----------test 1_2 fail-----------");
//			}
//			if(w <=0 ) {
//				throw new PreconditionError("----------test 1_2 fail-----------");
//			}
//
//			map.init(h,w);
//
//			if( map.getHeight() != h || map.getWidth() != w )
//				throw new InvariantError("----------test 1_2 fail-----------");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//
//		//afterTests();	
//	}
}
