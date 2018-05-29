package servives;

public interface TresorService {
	public int getI();
	public int getJ();
	public EnvironnementService getEnv();
	
	/**
	 * 
	 * pre
	 * init(env,i,j)requires i>= 0 and j>= 0 and i<= Environnement ::  Height(getEnv()) and j<= Environnement::Width(getEnv())
	 * and Environnement :: getEntities.get(0).getI() != i ||  Environnement :: getEntities.get(0).getJ() != j
	 * post :
	 * getI(init( env,i,j))==i and getJ(init( env,i,j))==j and getEnv(init( env,i,j))==env
	 * @param env
	 * @param i
	 * @param j
	 */
	public void init(EnvironnementService env , int i, int j);
	public boolean getTrouve();
	public void setTrouve(boolean trouve);
}
