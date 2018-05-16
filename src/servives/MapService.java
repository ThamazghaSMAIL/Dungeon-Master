package servives;



import tools.Cell;
import tools.Cellule;

public interface MapService {

	
	/**
	 * const Height: [Map]!int
	 * @return
	 */
	public int getHeight();
	public int getWidth();
	
	public void setHeight(int h);
	public void setWidth(int w);
	
	
	
	/**
	 * pre CellNature(M,x,y) requires 0 <= x < Width(M) and 0 <= y < Height(M)
	 * @param i
	 * @param j
	 * @return
	 */
	public Cell getCellNature( int i,int j );
	
	/**
	 * pre OpenDoor(M,x,y) requires CellNature(M,x,y) { fDNC, DWC }
	 * @param i
	 * @param j
	 */
	public void OpenDoor (int i,int j);
	
	/**
	 * pre CloseDoor(M,x,y) requires CellNature(M,x,y) { fDNO, DWO }
	 * @param i
	 * @param j
	 */
	public void CloseDoor (int i,int j);
	//public MapService  getMapService();
	//public void  setMapService(MapService mapService);
	
	/**
	 * pre init(w,h) requires 0 < w and 0 < h
	 * @param l
	 * @param c
	 */
	
	
	public Cellule getCell(int i, int j);
	public Cellule[][] getCells() ;
	public void setCells(Cellule[][] cells) ;
	public void init(int height, int width);
	
}
