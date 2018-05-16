package decorators;

import java.util.ArrayList;
import java.util.List;

import servives.EntityService;
import servives.EnvironnementService;
import servives.MobService;
import tools.Cell;
import tools.Cellule;
import tools.Option;
import tools.OptionEnum;

public class EnvironnementDecorator implements EnvironnementService {
	public EnvironnementDecorator(EnvironnementService serv) {
		super();
		this.serv = serv;
	}

	EnvironnementService serv ;

	@Override
	public OptionEnum getCellContent(int col, int row) {
		return serv.getCellContent(col, row);
	}

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
	public Cell getCellNature(int i, int j) {
		return serv.getCellNature(i, j);
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
	public Cellule getCell(int i, int j) {
		return serv.getCell(i, j);
	}

	@Override
	public Cellule[][] getCells() {
		return serv.getCells();
	}

	@Override
	public void setCells(Cellule[][] cells) {
		serv.setCells(cells);
	}



	@Override
	public Cellule getTresor() {
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
	public boolean isReachable(Cellule depart, Cellule arrivé, List<Cellule> dejaVisites) {
		return serv.isReachable(depart, arrivé, dejaVisites);
	}
}
