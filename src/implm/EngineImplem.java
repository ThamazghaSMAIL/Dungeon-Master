package implm;


import java.util.ArrayList;
import java.util.List;

import servives.CombatService;
import servives.EngineService;
import servives.EntityService;
import servives.EnvironnementService;

public class EngineImplem implements EngineService {
	public EngineImplem() {}
	public EnvironnementService envi;
	public CombatService combat;
	public int h,w;


	@Override
	public void init(EnvironnementService env,int h , int w ) {
		this.h= h; this.w = w;
		this.envi = new EnvironmentImplem();
		do { 
			this.envi.init(this.h,this.w); 
		}
		while( ! envi.isReachable(envi.getHeight()-1, envi.getWidth()-1,0,0));
		this.combat = new CombatImplem();
		combat.init(this.envi.getEntities().get(0));
	}

	@Override
	public void removeEntity( EntityService entity) {
		this.envi.getEntities().remove(entity);
	}

	@Override
	public void addEntity(EntityService es) {
		this.envi.getEntities().add(es);
	}

	@Override
	public void step() {

	}

	@Override
	public EnvironnementService getEnvi() {
		return this.envi;
	}

	public EntityService getEntity ( int i ) {
		return this.envi.getEntities().get(i);
	}

	public List<EntityService> getEntities() {
		return this.envi.getEntities();
	}

	public void setEntities(ArrayList<EntityService> entities) {
		this.envi.setEntities(entities);
	}



	public int getH() {
		return this.envi.getHeight();
	}

	public void setH(int h) {
		this.h= h;
		this.envi.setHeight(h);
	}

	public int getW() {
		return this.envi.getWidth();
	}

	public void setW(int w) {
		this.w = w;
		this.envi.setWidth(w);
	}

	public CombatService getCombat() {
		return this.combat;
	}

	public void setCombat(CombatService combat) {
		this.combat = combat;
	}

	@Override
	public boolean isFinie() {
		if( this.combat.getPlayer().getHp() > 0 )
			return false;
		return true;
	}
}
