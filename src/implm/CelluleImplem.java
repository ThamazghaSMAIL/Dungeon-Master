package implm;

import servives.CelluleService;
import servives.EnvironnementService;
import tools.Cell;
import tools.OptionEnum;
import tools.OptionFood;

public class CelluleImplem implements CelluleService {

	public CelluleImplem() {
	}

	public int i ;
	public int j ;
	public Cell nature ;
	public OptionEnum opt;
	public OptionFood food ;
	boolean clef;
	protected EnvironnementService env;

	@Override
	public void init(int i, int j, Cell nature) {
		this.i = i;
		this.j = j;
		this.nature = nature;
		this.clef = false;
		this.opt = OptionEnum.No;
		this.food = OptionFood.No;
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

	@Override
	public EnvironnementService getEnv() {
		return this.env;
	}
	
}
