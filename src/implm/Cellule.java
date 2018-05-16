package implm;

import tools.Cell;
import tools.OptionEnum;
import tools.OptionFood;

public class Cellule {

	public Cellule(int i, int j, Cell nature) {
		this.i = i;
		this.j = j;
		this.nature = nature;
		this.naturePrec=nature;
	}

	public int i ;
	public int j ;
	public Cell nature ;
	public Cell naturePrec;
	public OptionEnum opt = OptionEnum.No;
	public OptionFood food = OptionFood.No;
	//public int NbClefs;

	boolean clef = false;

	public boolean getClef() {
		return this.clef;
	}

	public void setClef(boolean b) {
		this.clef = b ;
	}

	public OptionEnum getContent() {
		return this.opt;
	}

	public void setContent(OptionEnum opt) {
		this.opt = opt ;
	}

	public OptionFood ContainsFood() {
		return this.food;
	}

	public void setFood(OptionFood food) {
		this.food = food ;
	}

	public int getI() {
		return i;
	}
	
	public void setI(int i) {
		this.i = i;
	}
	
	public int getJ() {
		return j;
	}
	
	public void setJ(int j) {
		this.j = j;
	}
	
	public Cell getNature() {
		return nature;
	}
	
	public void setNature(Cell nature) {
		this.nature = nature;
	}

	public Cell getNaturePrec() {
		return nature;
	}
	public void setNaturePrec(Cell nature) {
		this.nature = nature;
	}
}
