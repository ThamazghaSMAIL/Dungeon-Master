package servives;

import tools.Cell;
import tools.OptionEnum;
import tools.OptionFood;

public interface CelluleService {
	/*Observators*/
	
	public int getI();
	public int getJ() ;
	public Cell getNature();
	public OptionEnum getContent() ;
	public OptionFood ContainsFood();
	
	
	/*Constructors*/
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
	public void init(int i, int j, Cell nature);
	
	/*Operators*/
	
	public void setContent(OptionEnum opt) ;
	public void setFood(OptionFood food) ;
	public void setI(int i);
	public void setJ(int j);
	public void setNature(Cell nature) ;
	public EnvironnementService getEnv();
}
