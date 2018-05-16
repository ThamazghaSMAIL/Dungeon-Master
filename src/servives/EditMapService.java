package servives;

import tools.Cell;
import tools.Cellule;

public interface EditMapService extends /* refine */ MapService {
	//CellNature(M,x1,y1) 6 = WLL and CellNature(M,x2,y2) 6 = WLL
	public boolean isReachable ( int x1 , int y1 , int x2 , int y2);

	public boolean isReady();

	//0 ≤ i < Width(M) and 0 ≤ j < Height(M)
	public void setNature ( int i , int j , Cell c) ;

	void init(int height, int width);
}
