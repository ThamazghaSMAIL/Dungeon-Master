package servives;

import tools.Commande;

public interface PlayerService extends EntityService {
	public Commande getLastCommande();
	
	/**
	 * post: getLastCom() = lastCommande
	 * @param lastCommande
	 */
	public void setLastCommande(Commande lastCommande);

	boolean Viewable(int row, int col);
	
	public void step();
	
	public boolean getTresorFound();
	
	public boolean getClefFound();
}
