package servives;

import java.util.List;

import implm.CelluleImplem;

public interface EnvironnementService extends  MapService {

	/**
	 * const Height: [Map]!int
	 * @return
	 */
	public int getHeight();
	public int getWidth();
	
	public void setHeight(int h);
	public void setWidth(int w);
	
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
	
	/**
	 * pre init(w,h) requires 0 < w and 0 < h
	 * pre CloseDoor(M,x,y) requires CellContent(M,x,y) = No
	 * @param l
	 * @param c
	 */
	public void init( int height, int width);
	
	public CelluleImplem[][] getCells() ;
	public void setCells(CelluleImplem[][] cells) ;

	public List<EntityService> getEntities();
	public void setEntities( List<EntityService> en);
	
	public boolean YaUnMob(int i , int j );

	
	public TresorService getTresor();
	public ClefService getClef();
	boolean isReachable(int x1, int y1, int x2, int y2);
	
}
