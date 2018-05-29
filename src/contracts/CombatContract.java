package contracts;

import decorators.CombatDecorator;
import servives.CowService;
import servives.EntityService;
import servives.EnvironnementService;
import tools.Face;
import tools.InvariantError;
import tools.PostConditionError;
import tools.PreconditionError;

public class CombatContract extends CombatDecorator{


	public void checkInvarient(){
		if( !(super.getPlayer().getRow() == getVache().getRow() && Math.abs(getPlayer().getCol() - getVache().getCol()) == 1) &&
				(getPlayer().getCol() == getVache().getCol() &&  Math.abs(getPlayer().getRow() - getVache().getRow()) == 1) &&
				super.proche(getPlayer(), getVache())) {
			throw new InvariantError("proche combat");
		}

		if( super.getPlayer().isEnVie() && super.getPlayer().getHp() <=0 )
			throw new InvariantError("");

		for( EntityService c : super.getEnv().getEntities() ){
			if( c instanceof CowService ) {
				if( c.isEnVie() && c.getHp() <= 0 ) {
					throw new InvariantError("");
				}
				if( ! c.isEnVie() && c.getHp() > 0) {
					throw new InvariantError("");
				}
			}
		}
		if( touché (super.getPlayer(),super.getVache()) && ! super.proche(super.getPlayer(),super.getVache()))
			throw new InvariantError("");
		
		if( super.isFini() && super.getPlayer().isEnVie() && super.getVache().isEnVie() )
			throw new InvariantError("");
		
		if( super.debutCombat() && ! proche(super.getPlayer(),super.getVache() ))
				throw new InvariantError("");
	}
	
	@Override
	public void init(EntityService player,EnvironnementService env) {
		if( player == null || super.getEnv().getEntities().size() < 2) {
			//il y a aucun monstre
			throw new PreconditionError("Combat init error");
		}

		checkInvarient();
		super.init(player, env);

		if( ! super.getEnv().equals(env) || ! super.getPlayer().equals(player)) {
			throw new PostConditionError("init post combat");
		}
		checkInvarient();

	}
	@Override
	public void PlayerfrappeMonstre() {
		if( ! super.proche(super.getPlayer(), super.getVache())) {
			throw new PreconditionError("PlayerfrappeMonstre combat");
		}
		if( ! super.getPlayer().isEnVie() || super.getVache() == null || ! super.getVache().isEnVie() ) {
			throw new PostConditionError("combat PlayerfrappeMonstre");
		}
		int f = super.getVache().getHp();
		checkInvarient();
		super.PlayerfrappeMonstre();
		if( super.getVache().getHp()==f)
			throw new PostConditionError("");
		checkInvarient();
	}

	@Override
	public void VachefrappePlayer() {
		//	e2.getHp() > 0 && e1.getHp() > 0
		checkInvarient();
		if( super.getPlayer().getHp() <= 0 || super.getVache().getHp() <= 0 ) {
			throw new PreconditionError("combaat pre VachefrappePlayer");
		}
		int f  = super.getPlayer().getHp();
		super.VachefrappePlayer();
		if( super.getPlayer().getHp()==f)
			throw new PostConditionError("");
		checkInvarient();
	}

	@Override
	public boolean proche(EntityService player, EntityService vache) {
		checkInvarient();
		boolean result = super.proche(player, vache);
		checkInvarient();
		return result;

	}

	@Override
	public boolean touché(EntityService player, EntityService monstre) {
		return false;
	}

}
