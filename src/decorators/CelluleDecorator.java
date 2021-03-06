package decorators;

import servives.CelluleService;
import servives.EnvironnementService;
import tools.Cell;
import tools.OptionEnum;
import tools.OptionFood;

public class CelluleDecorator implements CelluleService{

	protected CelluleService serv;

	@Override
	public OptionEnum getContent() {
		return serv.getContent();
	}

	@Override
	public void setContent(OptionEnum opt) {
		serv.setContent(opt);
	}

	@Override
	public OptionFood ContainsFood() {
		return serv.ContainsFood();
	}

	@Override
	public void setFood(OptionFood food) {
		serv.setFood(food);		
	}

	@Override
	public int getI() {
		return serv.getI();
	}

	

	@Override
	public int getJ() {
		return serv.getJ();
	}

	

	@Override
	public Cell getNature() {
		return serv.getNature();
	}

	@Override
	public void setNature(Cell nature) {
		serv.setNature(nature);		
	}


	@Override
	public void init(int i, int j, Cell nature,EnvironnementService env) {
		serv.init(i, j, nature,env);		
	}

	@Override
	public EnvironnementService getEnv() {
		return serv.getEnv();
	}

}
