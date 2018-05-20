package implm;

import servives.CelluleService;
import tools.Cell;
import tools.OptionEnum;
import tools.OptionFood;

public class CelluleImplem implements CelluleService {

	public CelluleImplem() {
	}

	public int i ;
	public int j ;
	public Cell nature ;
	public Cell naturePrec;
	public OptionEnum opt = OptionEnum.No;
	public OptionFood food = OptionFood.No;
	boolean clef;

	@Override
	public void init(int i, int j, Cell nature) {
		this.i = i;
		this.j = j;
		this.nature = nature;
		this.naturePrec=nature;
		this.clef = false;
	}
	
	@Override
	public OptionEnum getContent() {
		return this.opt;
	}
	@Override
	public void setContent(OptionEnum opt) {
		this.opt = opt ;
	}
	@Override
	public OptionFood ContainsFood() {
		return this.food;
	}
	@Override
	public void setFood(OptionFood food) {
		this.food = food ;
	}
	@Override
	public int getI() {
		return i;
	}
	
	@Override
	public void setI(int i) {
		this.i = i;
	}
	@Override
	public int getJ() {
		return j;
	}
	@Override
	public void setJ(int j) {
		this.j = j;
	}
	@Override
	public Cell getNature() {
		return nature;
	}
	@Override
	public void setNature(Cell nature) {
		this.nature = nature;
	}
	
}