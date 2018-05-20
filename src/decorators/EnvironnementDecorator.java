package decorators;

import java.util.List;

import implm.CelluleImplem;
import servives.ClefService;
import servives.EntityService;
import servives.EnvironnementService;
import servives.TresorService;
import tools.Cell;
import tools.OptionEnum;

public class EnvironnementDecorator implements EnvironnementService {
	public EnvironnementDecorator(EnvironnementService serv) {
		super();
		this.serv = serv;
	}

	EnvironnementService serv ;

	

	@Override
	public int getHeight() {
		return serv.getHeight();
	}

	@Override
	public int getWidth() {
		return serv.getWidth();
	}

	@Override
	public void setHeight(int h) {
		serv.setHeight(h);
	}

	@Override
	public void setWidth(int w) {
		serv.setWidth(w);
	}


	@Override
	public void OpenDoor(int i, int j) {
		serv.OpenDoor(i, j);
	}

	@Override
	public void CloseDoor(int i, int j) {
		serv.CloseDoor(i, j);
	}

	@Override
	public void init( int height, int width) {
		serv.init(height, width);
	}


	@Override
	public CelluleImplem[][] getCells() {
		return serv.getCells();
	}

	@Override
	public void setCells(CelluleImplem[][] cells) {
		serv.setCells(cells);
	}

	@Override
	public TresorService getTresor() {
		return serv.getTresor();
	}

	@Override
	public List<EntityService> getEntities() {
		return serv.getEntities();
	}

	@Override
	public void setEntities(List<EntityService> en) {
		serv.setEntities(en);		
	}

	@Override
	public boolean YaUnMob(int i, int j) {
		return serv.YaUnMob(i, j);
	}


	@Override
	public ClefService getClef() {
		return serv.getClef();
	}

	@Override
	public boolean isReachable(int x1, int y1, int x2, int y2) {
		return serv.isReachable(x1, y1, x2, y2);
	}
}
