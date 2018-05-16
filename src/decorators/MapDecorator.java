package decorators;


import servives.MapService;
import tools.Cell;
import tools.Cellule;

public class MapDecorator implements MapService {
	private MapService serv;
	
	public MapDecorator(MapService mapService) {
		this.serv = mapService;
	}

	@Override
	public void init(int height, int width) {
		serv.init(height, width);
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

	public MapService getMapService() {
		return serv;
	}

	public void setMapService(MapService mapService) {
		this.serv = mapService;
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
	public void setHeight(int h) {

		serv.setHeight(h);
	}

	@Override
	public void setWidth(int w) {
		serv.setWidth(w);
	}

	
}
