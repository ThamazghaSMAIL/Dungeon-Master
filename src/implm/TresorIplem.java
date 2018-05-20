package implm;

import servives.EnvironnementService;
import servives.TresorService;

public class TresorIplem implements TresorService {
	public TresorIplem() {
	}

	int i;
	int j;
	EnvironnementService env ;
	
	@Override
	public int getI() {
		return this.i;
	}

	@Override
	public int getJ() {
		return this.j;
	}


	@Override
	public EnvironnementService getEnv() {
		return this.env;
	}
	
	@Override
	public void init(EnvironnementService env, int i, int j) {
		this.i = i;
		this.j = j;
		this.env = env;
	}

	
}
