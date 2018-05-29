package implm;

import servives.CombatService;
import servives.EntityService;
import servives.EnvironnementService;
import tools.Face;
import tools.OptionEnum;
import tools.OptionFood;

public class CombatImplem implements CombatService {
	protected EntityService player;
	protected EntityService vache;
	protected boolean debutCombat;
	protected boolean Finie;

	protected EnvironnementService env;
	public CombatImplem(){
		this.Finie = false;
	}

	@Override
	public void init (EntityService player ,EnvironnementService env ) {
		this.player = player ;
		this.Finie = false;
		this.debutCombat = false;
		this.env = env ;
	}

	@Override
	public void PlayerfrappeMonstre ( ) {
		System.out.println("player touché monstre : "+touché(player,this.vache));
		if( touché ( this.player,this.vache )) {
			this.vache.setHp(this.vache.getHp() - 1 );
		}

		if( this.vache.getHp() <= 0 ) {
			this.vache.setEnVie(false);
			this.vache.getEnv().getCells()[this.vache.getRow()][this.vache.getCol()].setFood(OptionFood.Fo);
			this.vache.getEnv().getCells()[this.vache.getRow()][this.vache.getCol()].setContent(OptionEnum.No);
		}
	}

	@Override
	public void VachefrappePlayer () {
		if( touché(  this.vache, this.player )) {
			player.setHp(this.player.getHp() - 1);
		}
		if( this.player.getHp() <= 0 ) {
			this.Finie = true;
		}
	}

	@Override
	public boolean touché(EntityService e1, EntityService e2) {
		if( this.debutCombat ) {
			if( e1.getFace().equals(Face.N) && e1.getCol() == e2.getCol() && e1.getRow()-e2.getRow()==1 ) {
				return true;
			}else {
				if( e1.getFace().equals(Face.S) && e1.getCol() == e2.getCol() && e1.getRow()-e2.getRow()==-1 ) {
					return true;
				}else {
					if( e1.getFace().equals(Face.E) && e1.getRow() == e2.getRow() && e1.getCol()-e2.getCol()==-1 ) {
						return true;
					}else {
						if( e1.getFace().equals(Face.W) && e1.getRow() == e2.getRow() && e1.getCol()-e2.getCol()==1 ) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean proche(EntityService player, EntityService vache) {
		if( this.debutCombat )
		if( player.getRow() == vache.getRow() )
			if( Math.abs(player.getCol() - vache.getCol()) == 1 )
				return true;

		if( player.getCol() == vache.getCol()) 
			if( Math.abs(player.getRow() - vache.getRow()) == 1 )
				return true;
		return false;
	}

	@Override
	public EntityService getPlayer() {
		return this.player;
	}
	@Override
	public void setPlayer(EntityService player) {
		this.player = player;
	}
	@Override
	public EntityService getVache() {
		return this.vache;
	}
	@Override
	public void setVache(EntityService vache) {
		this.vache = vache;
		this.debutCombat = true;
	}
	@Override
	public EnvironnementService getEnv() {
		return this.env;
	}
	@Override
	public void setEnv(EnvironnementService env) {
		this.env = env;
	}

	@Override
	public boolean isFini() {
		return this.Finie;
	}

	@Override
	public boolean debutCombat() {
		return this.debutCombat;
	}
}
