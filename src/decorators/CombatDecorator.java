package decorators;

import servives.CombatService;
import servives.EntityService;
import servives.EnvironnementService;

public class CombatDecorator implements CombatService{

	CombatService serv ;
	@Override
	public void init(EntityService player, EnvironnementService env) {
		serv.init(player,env);
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
		return serv.touché(player, monstre);
	}

	@Override
	public EntityService getPlayer() {
		return serv.getPlayer();
	}

	@Override
	public void setPlayer(EntityService player) {
		serv.setPlayer(player);
	}

	@Override
	public EntityService getVache() {
		return serv.getVache();
	}

	@Override
	public void setVache(EntityService vache) {
		serv.setVache(vache);
	}

	@Override
	public EnvironnementService getEnv() {
		return serv.getEnv();
	}

	@Override
	public void setEnv(EnvironnementService env) {
		serv.setEnv(env);		
	}

	@Override
	public boolean isFini() {
		return serv.isFini();
	}

	@Override
	public boolean debutCombat() {
		return serv.debutCombat();
	}


}
