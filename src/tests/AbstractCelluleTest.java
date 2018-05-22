package tests;

import static org.junit.Assert.fail;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import servives.CelluleService;
import tools.Cell;
import tools.PostConditionError;
import tools.PreconditionError;

public abstract class AbstractCelluleTest extends AbstractTest {
	protected CelluleService cellule;
	protected AbstractCelluleTest() {
		cellule = null;
	}

	protected final CelluleService getCellule() {
		return cellule;
	}

	protected final void setCellule(CelluleService cellule) {
		this.cellule = cellule;
	}

	@Before
	public abstract void beforeTests();

	@After
	public void afterTests() {
		cellule = null;
	}

	/*init*/
	/**
	 * pre : i >= 0 and i < Environnement :: getHeight() and
	 * pre : j >= 0 and j < Environnement :: getWidth()
	 * @param i
	 * @param j
	 * @param nature
	 * post : getI( init(i,j,nat) )==i
	 * post : getJ( init(i,j,nat) )==j
	 * post : getNature( init(i,j,nat) )==nat
	 */
	//public void init(int i, int j, Cell nature);
	@Test
	public void init_testPrePositif_1(){
		try{
			int i = ThreadLocalRandom.current().nextInt(0,cellule.getEnv().getHeight()-1);
			int j = ThreadLocalRandom.current().nextInt(0,cellule.getEnv().getWidth()-1);
			Cell nature = getNatureRandom();
			cellule.init(i, j, nature);
		}catch(PreconditionError pe){
			fail("init_testPrePositif_1_cellule");	
		}
	}
	
	@Test
	public void init_testPreNegatif_1(){
		try{
			int i = ThreadLocalRandom.current().nextInt(0,cellule.getEnv().getHeight()-1);
			int j = - ThreadLocalRandom.current().nextInt(0,cellule.getEnv().getWidth()-1);
			Cell nature = getNatureRandom();
			cellule.init(i, j, nature);
			fail("init_testPreNegatif_1_cellule");
		}catch(PreconditionError pe){
		}
	}

	@Test
	public void init_testPreNegatif_2(){
		try{
			int i = - ThreadLocalRandom.current().nextInt(0,cellule.getEnv().getHeight()-1);
			int j = ThreadLocalRandom.current().nextInt(0,cellule.getEnv().getWidth()-1);
			Cell nature = getNatureRandom();
			cellule.init(i, j, nature);
			fail("init_testPreNegatif_2_cellule");
		}catch(PreconditionError pe){
		}
	}
	
	@Test
	public void init_testPreNegatif_3(){
		try{
			int i = - ThreadLocalRandom.current().nextInt(0,cellule.getEnv().getHeight()-1);
			int j = - ThreadLocalRandom.current().nextInt(0,cellule.getEnv().getWidth()-1);
			Cell nature = getNatureRandom();
			cellule.init(i, j, nature);
			fail("init_testPreNegatif_3_cellule");
		}catch(PreconditionError pe){
		}
	}
	
	@Test
	public void init_testPreNegatif_4(){
		try{
			int i = 0;
			int j = ThreadLocalRandom.current().nextInt(0,cellule.getEnv().getWidth()-1);
			Cell nature = getNatureRandom();
			cellule.init(i, j, nature);
			fail("init_testPreNegatif_4_cellule");
		}catch(PreconditionError pe){
		}
	}
	
	@Test
	public void init_testPreNegatif_5(){
		try{
			int i = ThreadLocalRandom.current().nextInt(0,cellule.getEnv().getWidth()-1);
			int j = 0;
			Cell nature = getNatureRandom();
			cellule.init(i, j, nature);
			fail("init_testPreNegatif_5_cellule");
		}catch(PreconditionError pe){
		}
	}
	
	@Test
	public void init_testPreNegatif_6(){
		try{
			int i = 0;
			int j = 0;
			Cell nature = getNatureRandom();
			cellule.init(i, j, nature);
			fail("init_testPreNegatif_6_cellule");
		}catch(PreconditionError pe){
		}
	}
	
	@Test
	public void init_testPostPositif_1(){
		try{
			int i = ThreadLocalRandom.current().nextInt(0,cellule.getEnv().getHeight()-1);
			int j = ThreadLocalRandom.current().nextInt(0,cellule.getEnv().getWidth()-1);
			Cell nature = getNatureRandom();
			cellule.init(i, j, nature);
		}catch(PostConditionError pe){
			fail("init_testPreNegatif_6_cellule");
		}
	}
	
	@Test
	public void init_testPostNegatif_1(){
		try{
			int i = ThreadLocalRandom.current().nextInt(0,cellule.getEnv().getHeight()-1);
			int j = ThreadLocalRandom.current().nextInt(0,cellule.getEnv().getWidth()-1);
			Cell nature = getNatureRandom();
			cellule.init(i, j, nature);
			fail("init_testPreNegatif_6_cellule");
		}catch(PostConditionError pe){
		}
	}
	
	
}
