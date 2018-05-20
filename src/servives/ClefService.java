package servives;

public interface ClefService {
	public int getI();
	public int getJ();
	public EnvironnementService getEnv();
	
	/**
	 * 
	 * pre
	 * init(env,i,j)requiresi>= 0 and j>= 0 and i<= Environnement ::  Height(getEnv()) and j<= Environnement::Width(getEnv())
	 * post :
	 * getI(init( env,i,j))==i and getJ(init( env,i,j))==j and getEnv(init( env,i,j))==env
	 * @param env
	 * @param i
	 * @param j
	 */
	public void init(EnvironnementService env , int i, int j);
}
