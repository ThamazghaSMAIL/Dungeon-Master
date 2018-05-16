package servives;

import tools.Face;

public interface CowService {
	//toutes les meth de Entity
	
	public EnvironnementService getEnv();
	public void setEnv(EnvironnementService env);
	
	public void forward();
	public void backward();
	
	public void turnL();
	public void turnR();
	
	public void strafeL();
	public void strafeR();
	
	public Face getFace();
	public void setFace(Face face);
	
	public int getRow();
	public void setRow(int row);
	
	public int getHp ();
	public void setHp(int hp);
	
	public void init(int row, int col, int hp , Face face , EnvironnementService env);
	
	public void step ();
	
	public int getCol();
	public void setCol(int col );
	
	public boolean casePossible(int i ,int j);
	//pre : init(E,x,y,D,h) requires 4 >= h >= 3
	public void setFrappe(boolean frappe);
	public boolean getFrappe();
	public boolean isEnVie();
	public void setEnVie(boolean etat);
	
}
