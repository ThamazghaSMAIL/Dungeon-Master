package servives;

import implm.CelluleImplem;

public interface MapService {

	/*Observators*/
	public CelluleImplem[][] getCells() ;
	public void setCells(CelluleImplem[][] cells) ;
	
	
	public int getHeight();
	public int getWidth();
	
	public void setHeight(int h);
	public void setWidth(int w);
	
	
	
	/*Constructors*/
	/**
	 * pre init(w,h) requires 0 < w and 0 < h
	 * @param l
	 * @param c
	 */
	public void init(int height, int width);
	
	/*Operators*/
	/**
	 * pre OpenDoor(M,x,y) requires CellNature(M,x,y) { DNC, DWC }
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
	
	
	
	
}
