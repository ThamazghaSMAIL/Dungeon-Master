package servives;

import tools.Face;

public interface EntityService {

	public EnvironnementService getEnv();
	public void setEnv(EnvironnementService env);



	public void init(int row, int col, int hp , Face face , EnvironnementService env);
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

	public int getCol();

	public int getHp ();
	public void setHp(int hp);

	public void step();

	public boolean isEnVie();
	public void setEnVie(boolean etat);


}
