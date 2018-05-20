package implm;

import servives.ClefService;
import servives.EnvironnementService;

public class ClefImplem implements ClefService{

	protected int i;
	protected int j;
	protected EnvironnementService env;
	
	public ClefImplem() {
	}

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
