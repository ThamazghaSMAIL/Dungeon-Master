package servives;

public interface EditMapService extends /* refine */ MapService {
	/*Constructors*/
	/**
	 * pre : init(w,h) requires 0 < w and 0 < h
	 * @param l
	 * @param c
	 */
	void init(int height, int width);
	/*Operators*/
	/**
	 * pre : CellNature(M,x1,y1) 6 = WLL and CellNature(M,x2,y2) 6 = WLL
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public boolean isReachable ( int x1 , int y1 , int x2 , int y2);

	public boolean isReady();

	
}
