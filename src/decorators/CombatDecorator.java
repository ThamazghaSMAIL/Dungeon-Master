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
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean proche(EntityService player, EntityService vache) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touché(EntityService player, EntityService monstre) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EntityService getPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPlayer(EntityService player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EntityService getVache() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVache(EntityService vache) {
		// TODO Auto-generated method stub
		
	}

}
