package decorators;

import servives.EnvironnementService;
import servives.PlayerService;
import tools.Cell;
import tools.Commande;
import tools.Face;
import tools.OptionEnum;

public class PlayerDecorator implements PlayerService{

	public PlayerDecorator(PlayerService serv) {
		super();
		this.serv = serv;
	}

	PlayerService serv;
	
	
	@Override
	public void init(int row, int col,int hp , Face face , EnvironnementService env) {
		serv.init(row, col, hp,face, env);
		
	}
	
	

	@Override
	public OptionEnum getContent(int row, int col) {
		return serv.getContent(row, col);
	}

	@Override
	public Cell getNature(int row, int col) {
		return serv.getNature(row, col);
	}

	@Override
	public boolean Viewable(int row, int col) {
		return serv.Viewable(row, col);
	}
	
	
	
	/*suite*/
	@Override
	public int getHp() {
		return serv.getHp();
	}

	

	@Override
	public void step() {
		serv.step();
	}


	

	@Override
	public int getCol() {
		return serv.getCol();
	}


	@Override
	public int getRow() {
		return serv.getRow();
	}


	@Override
	public Face getFace() {
		return serv.getFace();
	}


	@Override
	public void forward() {
		serv.forward();
	}


	@Override
	public void backward() {
		serv.backward();
	}


	@Override
	public void turnL() {
		serv.turnL();
	}


	@Override
	public void turnR() {
		serv.turnR();
	}


	@Override
	public void strafeL() {
		serv.strafeL();
	}


	@Override
	public void strafeR() {
		serv.strafeR();
	}


	@Override
	public EnvironnementService getEnv() {
		return serv.getEnv();
	}



	@Override
	public void setEnv(EnvironnementService env) {
		serv.setEnv(env);
	}


	@Override
	public void setFace(Face face) {
		serv.setFace(face);
		
	}


	@Override
	public void setRow(int row) {
		serv.setRow(row);
		
	}

	

	@Override
	public boolean getTresorFound() {
		return serv.getTresorFound();
	}

	@Override
	public int getNbClefs() {
		return serv.getNbClefs();
	}

	@Override
	public void setNbClefs(int nb) {
		serv.setNbClefs(nb);
	}

	@Override
	public void setHp(int hp) {
		serv.setHp(hp);
	}


}
