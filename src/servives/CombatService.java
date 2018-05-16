package servives;

public interface CombatService {

	
	/**
	 * pre player != null && env.getentities().size() > 1
	 * @param player
	 * @param vache
	 */
	public void init ( EntityService player );
	/**
	 * post : 
	 */
	
	
	
	
	/**
	 * PlayerfrappeMonstre ( e1,  e2 ) require proche(e1,e2)
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
	 * player != null
	 * @param player
	 */
	public void setPlayer(EntityService player);

	public EntityService getVache();
	
	public void setVache(EntityService vache);
	
	
	
}
