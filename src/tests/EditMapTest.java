package tests;

import org.junit.Test;

import servives.EditMapService;
import tools.InvariantError;
import tools.PreconditionError;

public class EditMapTest {
	
	public EditMapTest(EditMapService map) {
		super();
		this.map = map;
	}

	private EditMapService map ;

	public EditMapService getMap() {
		return map;
	}

	public void setMap(EditMapService map) {
		this.map = map;
	} 
	
	@Test
	public void test1_1(){

		//beforeTests();

		try {
			int h = 2;
			int w = 5;

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
	
	@Test
	public void test1_2(){

		//beforeTests();

		try {
			int h = - 2;
			int w = 5;

			if( h <= 0 ) {
				throw new PreconditionError("----------test 1_2 fail-----------");
			}
			if(w <=0 ) {
				throw new PreconditionError("----------test 1_2 fail-----------");
			}

			map.init(h,w);

			if( map.getHeight() != h || map.getWidth() != w )
				throw new InvariantError("----------test 1_2 fail-----------");

		} catch (Exception e) {
			e.printStackTrace();
		}


		//afterTests();	
	}
	
}

