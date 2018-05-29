package implm;

import servives.EnvironnementService;
import servives.TresorService;

public class TresorIplem implements TresorService {
	public TresorIplem() {
	}

	protected int i;
	protected int j;
	protected EnvironnementService env ;
	protected boolean trouve ;
	
	@Override
	public int getI() {
		return this.i;
	}

	@Override
	public int getJ() {
		return this.j;
	}

	@Override
	public boolean getTrouve() {
		return this.trouve;
	}

	@Override
	public void setTrouve(boolean trouve) {
		 this.trouve =trouve;
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
		this.trouve=false;
	}

	
}
