package contracts;

import servives.CombatService;
import servives.EntityService;

public class CombatContract implements CombatService{

	@Override
	public void init(EntityService player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PlayerfrappeMonstre() {
		// TODO Auto-generated method stub
		
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
