package servives;

import java.util.List;

import implm.EnvironmentImplem;
import tools.Cell;
import tools.Cellule;
import tools.OptionEnum;

public interface EnvironnementService extends  MapService {

	
	OptionEnum getCellContent(int row, int col);

	
	
	//duplication des meth de Map car include 
	

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
	
	/**
	 * pre init(w,h) requires 0 < w and 0 < h
	 * pre CloseDoor(M,x,y) requires CellContent(M,x,y) = No
	 * @param l
	 * @param c
	 */
	public void init( int height, int width);
	
	public Cellule getCell(int i, int j);
	public Cellule[][] getCells() ;
	public void setCells(Cellule[][] cells) ;

	public List<EntityService> getEntities();
	public void setEntities( List<EntityService> en);
	public Cellule getTresor();
	
	
	public boolean YaUnMob(int i , int j );

	boolean isReachable(Cellule depart, Cellule arrivé, List<Cellule> dejaVisites);
}
