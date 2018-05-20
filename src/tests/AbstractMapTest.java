package tests;




import static org.junit.Assert.fail;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import servives.MapService;
import tools.Cell;
import tools.InvariantError;
import tools.PostConditionError;
import tools.PreconditionError;


public abstract class AbstractMapTest extends AbstractTest {


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

	/*init*/

	@Test
	public void init_testPrePositif_1(){
		try{
			int h = ThreadLocalRandom.current().nextInt(1, 10);
			int w = ThreadLocalRandom.current().nextInt(1, 10);
			map.init(h, w);
		}catch(PreconditionError pe){
			fail("tinit_testPrePositif_1_map");	
		}
	}

	@Test
	public void init_testPreNegatif_1(){
		try{
			int h = ThreadLocalRandom.current().nextInt(1, 10);
			int w = - ThreadLocalRandom.current().nextInt(1, 10);
			map.init(h, w);
			fail("init_testPreNegatif_1_map");	
		}catch(PreconditionError pe){
		}
	}

	@Test
	public void init_testPreNegatif_2(){
		try{
			int h = - ThreadLocalRandom.current().nextInt(1, 10);
			int w = ThreadLocalRandom.current().nextInt(1, 10);
			map.init(h, w);
			fail("init_testPreNegatif_2_map");	
		}catch(PreconditionError pe){
		}
	}

	@Test
	public void init_testPreNegatif_3(){
		try{
			int h = - ThreadLocalRandom.current().nextInt(1, 10);
			int w = - ThreadLocalRandom.current().nextInt(1, 10);
			map.init(h, w);
			fail("init_testPreNegatif_3_map");	
		}catch(PreconditionError pe){
		}
	}


	@Test
	public void init_testPreNegatif_4(){
		try{
			int h = 0;
			int w = ThreadLocalRandom.current().nextInt(1, 10);
			map.init(h, w);
			fail("init_testPreNegatif_4_map");	
		}catch(PreconditionError pe){
		}
	}

	@Test
	public void init_testPreNegatif_5(){
		try{
			int h = - ThreadLocalRandom.current().nextInt(1, 10);
			int w = 0;
			map.init(h, w);
			fail("init_testPreNegatif_5_map");	
		}catch(PreconditionError pe){
		}
	}

	@Test
	public void init_testPreNegatif_6(){
		try{
			int h = 0;
			int w = 0;
			map.init(h, w);
			fail("init_testPreNegatif_6_map");	
		}catch(PreconditionError pe){
		}
	}
	//post
	@Test
	public void init_testPostPositif_1(){
		try{
			int h = ThreadLocalRandom.current().nextInt(1, 10);
			int w = ThreadLocalRandom.current().nextInt(1, 10);
			map.init(h, w);
			fail("init_testPostPositif_1_map");	
		}catch(PostConditionError pe){
		}
	}

	/*Opendoor*/
	//pre OpenDoor(M,x,y) requires CellNature(M,x,y) { DNC, DWC }

	@Test
	public void OpenDoor_testPrePositif_1(){

		int h = ThreadLocalRandom.current().nextInt(1, 10);
		int w = ThreadLocalRandom.current().nextInt(1, 10);
		map.init(h, w);
		for( int i = 0 ; i < map.getHeight() ; i++ )
			for (int j = 0 ; j < map.getWidth() ; j++ ) {
				if( map.getCells()[i][j].getNature().equals(Cell.DNC) || map.getCells()[i][j].getNature().equals(Cell.DWC))
					try{
						map.OpenDoor(i, j);
					}catch(PreconditionError pe){
						fail("OpenDoor_testPrePositif_1_map");	
					}
			}
	}
	
	@Test
	public void OpenDoor_testPreNegatif_1(){

		int h = ThreadLocalRandom.current().nextInt(1, 10);
		int w = ThreadLocalRandom.current().nextInt(1, 10);
		map.init(h, w);
		for( int i = 0 ; i < map.getHeight() ; i++ )
			for (int j = 0 ; j < map.getWidth() ; j++ ) {
				if( ! map.getCells()[i][j].getNature().equals(Cell.DNC) && ! map.getCells()[i][j].getNature().equals(Cell.DWC))
					try{
						map.OpenDoor(i, j);
						fail("OpenDoor_testPreNegatif_1_map");	
					}catch(PreconditionError pe){
					}
			}
	}
	
	/*Closedoor*/
	//pre Closedoor(M,x,y) requires CellNature(M,x,y) { DNO, DWO }

	@Test
	public void CloseDoor_testPrePositif_1(){

		int h = ThreadLocalRandom.current().nextInt(1, 10);
		int w = ThreadLocalRandom.current().nextInt(1, 10);
		map.init(h, w);
		for( int i = 0 ; i < map.getHeight() ; i++ )
			for (int j = 0 ; j < map.getWidth() ; j++ ) {
				if( map.getCells()[i][j].getNature().equals(Cell.DNO) || map.getCells()[i][j].getNature().equals(Cell.DWO))
					try{
						map.OpenDoor(i, j);
					}catch(PreconditionError pe){
						fail("CloseDoor_testPrePositif_1_map");	
					}
			}
	}
	
	@Test
	public void CloseDoor_testPreNegatif_1(){

		int h = ThreadLocalRandom.current().nextInt(1, 10);
		int w = ThreadLocalRandom.current().nextInt(1, 10);
		map.init(h, w);
		for( int i = 0 ; i < map.getHeight() ; i++ )
			for (int j = 0 ; j < map.getWidth() ; j++ ) {
				if( ! map.getCells()[i][j].getNature().equals(Cell.DNO) && ! map.getCells()[i][j].getNature().equals(Cell.DWO))
					try{
						map.CloseDoor(i, j);
						fail("CloseDoor_testPreNegatif_1_map");	
					}catch(PreconditionError pe){
					}
			}
	}

}
