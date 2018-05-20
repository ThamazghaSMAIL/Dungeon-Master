package decorators;


import servives.MapService;
import tools.Cell;
import implm.CelluleImplem;

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
	public CelluleImplem[][] getCells() {
		return serv.getCells();
	}

	@Override
	public void setCells(CelluleImplem[][] cells) {
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
