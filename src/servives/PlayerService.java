package servives;

import tools.Cell;
import tools.Commande;
import tools.Face;
import tools.OptionEnum;

public interface PlayerService {


	public OptionEnum getContent(int row, int col);

	public Cell getNature(int row, int col);

	boolean Viewable(int row, int col);

	
	public EnvironnementService getEnv();
	public void setEnv(EnvironnementService env);
	
	public int getCol();
	
	public int getRow();
	public void setRow(int row);
	
	public Face getFace();
	public void setFace(Face face);
	
	public void init(int row, int col,int hp , Face face , EnvironnementService env);
	
	public void forward();
	public void backward();
	
	public void turnL();
	public void turnR();
	
	public void strafeL();
	public void strafeR();
	
	
	
	
	
	public int getHp ();
	public void setHp(int hp);
	
	public void step();
	
	public boolean getTresorFound();
	
	public int getNbClefs();
	void setNbClefs(int nb);
}
