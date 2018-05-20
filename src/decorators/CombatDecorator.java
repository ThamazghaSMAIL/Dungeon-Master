package decorators;

import servives.CombatService;
import servives.EntityService;

public class CombatDecorator implements CombatService{

	CombatService serv ;
	@Override
	public void init(EntityService player) {
		serv.init(player);
	}

	@Override
	public void PlayerfrappeMonstre() {
		serv.PlayerfrappeMonstre();
	}

	@Override
	public void VachefrappePlayer() {
		serv.VachefrappePlayer();
	}

	@Override
	public boolean proche(EntityService player, EntityService vache) {
		return serv.proche(player, vache);
	}

	@Override
	public boolean touché(EntityService player, EntityService monstre) {
		return false;
	}

	@Override
	public EntityService getPlayer() {
		return null;
	}

	@Override
	public void setPlayer(EntityService player) {
	}

	@Override
	public EntityService getVache() {
		return null;
	}

	@Override
	public void setVache(EntityService vache) {
	}

}
