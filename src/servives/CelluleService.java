package servives;

import tools.Cell;
import tools.OptionEnum;
import tools.OptionFood;

public interface CelluleService {

	public boolean getClef();
	public void setClef(boolean b) ;

	public OptionEnum getContent() ;
	public void setContent(OptionEnum opt) ;

	public OptionFood ContainsFood();

	public void setFood(OptionFood food) ;

	public int getI();

	public void setI(int i) ;

	public int getJ() ;

	public void setJ(int j);

	public Cell getNature() ;

	public void setNature(Cell nature) ;

	public Cell getNaturePrec() ;
	public void setNaturePrec(Cell nature) ;
	
	public void init(int i, int j, Cell nature);
}
