package decorators;

import servives.ClefService;
import servives.EnvironnementService;

public class ClefDecorator implements ClefService{

	ClefService serv ;
	public ClefDecorator(ClefService serv) {
		super();
		this.serv = serv;
	}

	@Override
	public int getI() {
		return serv.getI();
	}

	@Override
	public int getJ() {
		return serv.getJ();
	}

	@Override
	public EnvironnementService getEnv() {
		return serv.getEnv();
	}

	@Override
	public void init(EnvironnementService env, int i, int j) {
		serv.init(env, i, j);
	}

}
