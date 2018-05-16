package servives;

import tools.Face;

public interface MobService {
	/*
	 * 
	 * 
	 */
	public EnvironnementService getEnv();
	/*
	 * 
	 * 
	 */
	public void setEnv(EnvironnementService env);
	/*
	 * 
	 * 
	 */
	public int getCol();
	/*
	 * 
	 * 
	 */
	public int getRow();
	/*
	 * 
	 * 
	 */
	public Face getDir();
	/*
	 * 
	 * 
	 */
	public void init(int row, int col, Face face , EnvironnementService env);
	/*
	 * 
	 * 
	 */
	public void forward();
	/*
	 * 
	 * 
	 */
	public void backward();
	/*
	 * 
	 * 
	 */
	public void turnL();
	/*
	 * 
	 * 
	 */
	public void turnR();
	/*
	 * 
	 * 
	 */
	public void strafeL();
	/*
	 * 
	 * 
	 */
	public void strafeR();
	
	public boolean getTresorFound();
	public int getNbClefs();
	public void setFace(Face face);
	public void setRow(int row);
	void setNbClefs(int nb);

}
