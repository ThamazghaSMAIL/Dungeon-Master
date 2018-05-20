package decorators;

import servives.EnvironnementService;
import servives.TresorService;

public class TresorDecorator implements TresorService {
	TresorService serv; 
	
	public TresorDecorator(TresorService serv) {
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
