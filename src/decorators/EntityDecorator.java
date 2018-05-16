package decorators;


import servives.EntityService;
import servives.EnvironnementService;
import tools.Face;

public class EntityDecorator implements EntityService {

	EntityService serv ;
	public EntityDecorator(EntityService delegate) {
		this.serv = delegate;
	}
	
	@Override
	public int getHp() {
		return serv.getHp();
	}

	
	@Override
	public void step() {
		serv.step();
	}


	@Override
	public void init(int row, int col, int hp , Face face , EnvironnementService env) {
		serv.init(row, col, hp, face, env);
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
	public void setHp(int hp) {
		serv.setHp(hp);
		
	}

	@Override
	public Face getFace() {
		return serv.getFace();
	}

	@Override
	public boolean isEnVie() {
		return serv.isEnVie();
	}

	@Override
	public void setEnVie(boolean etat) {
		serv.setEnVie(etat);
	}


	
}
