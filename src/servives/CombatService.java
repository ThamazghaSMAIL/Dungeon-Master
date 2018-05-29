package servives;

public interface CombatService {

	
	/**
	 * pre player != null && env.getentities().size() > 1
	 * il y a le player dans entities et au moins un monstre
	 * @param player
	 * @param vache
	 */
	public void init ( EntityService player , EnvironnementService env );
	/**
	 * post : getEnv(init(p,e))=e
	 * 		  getPlayer(init(p,e))=p
	 */
	
	
	
	
	/**
	 * PlayerfrappeMonstre ( e1,  e2 ) require proche(e1,e2)
	 * and Entity :: getHp(e1) > 0 && Entity :: getHp(e2) > 0
	 * @param e1
	 * @param e2
	 */
	
	/**
	 * e2.getHp() > 0 && e1.getHp() > 0
	 * @param e1
	 * @param e2
	 */
	public void PlayerfrappeMonstre ();
	
	/**
	 * e2.getHp() > 0 && e1.getHp() > 0
	 * @param player
	 * @param vache
	 */
	public void VachefrappePlayer ();
	
	/**
	 * 
	 * @param player
	 * @param vache
	 * proche( player,  vache) -->
	 * player.getRow() == vache.getRow() &&) && Math.abs(player.getCol() - vache.getCol()) == 1 )
	 * player.getCol() == vache.getCol()) &&  Math.abs(player.getRow() - vache.getRow()) == 1
	 * @return
	 * 
	 */
	public boolean proche(EntityService player, EntityService vache);
	
	
	/**
	 * touché(p,m) requires debutCombat = true
	 * 
	 * @param player
	 * @param monstre
	 * @return
	 */
	public boolean touché(EntityService player, EntityService monstre);
	
	/**
	 */
	public EntityService getPlayer();
	
	

	/**
	 * vache != null
	 * @param player
	 */
	public EntityService getVache();
	/**
	 * vache != null
	 * @param player
	 */
	public void setVache(EntityService vache);
	/**
	 * player != null
	 * @param player
	 */
	public void setPlayer(EntityService player);
	
	
	public EnvironnementService getEnv() ;
	public void setEnv(EnvironnementService env);
	public boolean isFini();
	public boolean debutCombat();
}
