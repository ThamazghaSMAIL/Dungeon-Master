package decorators;

import servives.CowService;
import servives.EnvironnementService;
import tools.Face;

public class CowDecorator implements CowService {

	public CowDecorator(CowService serv) {
		super();
		this.serv = serv;
	}

	public CowService serv ; 


	@Override
	public void init(int row, int col, int hp , Face face , EnvironnementService env) {
		serv.init(row, col, hp, face, env);		
	}

	@Override
	public void step() {
		serv.step();
	}

	@Override
	public int getHp() {
		return serv.getHp();
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
	public void setFace(Face face) {
		serv.setFace(face);

	}

	@Override
	public void setRow(int row) {
		serv.setRow(row);		
	}

	@Override
	public boolean casePossible(int i, int j) {
		return serv.casePossible(i, j);
	}

	@Override
	public Face getFace() {
		return serv.getFace();
	}

	@Override
	public void setHp(int hp) {
		serv.setHp(hp);
	}

	@Override
	public void setCol(int col) {
		serv.setCol(col);
	}

	@Override
	public void setFrappe(boolean frappe) {
		serv.setFrappe(frappe);
		
	}

	@Override
	public boolean getFrappe() {
		return serv.getFrappe();
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
