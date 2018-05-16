package decorators;

import servives.EnvironnementService;
import servives.MobService;
import tools.Face;

public class MobDecorator implements MobService {
	
	public MobDecorator(MobService serv) {
		this.serv = serv;
	}
	private MobService serv;
	


	@Override
	public int getCol() {
		return serv.getCol();
	}


	@Override
	public int getRow() {
		return serv.getRow();
	}


	@Override
	public Face getDir() {
		return serv.getDir();
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
	public void init(int row, int col, Face face , EnvironnementService env) {
		serv.init(row, col, face, env);
		
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

	

	

}
