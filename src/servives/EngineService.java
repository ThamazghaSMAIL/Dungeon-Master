package servives;

import java.util.List;

public interface EngineService {
	
	
	//Envi: [Engine] → Environment
	
	public EnvironnementService getEnvi();
	//Entities: [Engine] → Array[Entity]
	
	public List<EntityService> getEntities();
	
	//getEntity: [Engine] × int → Entity
	
	public EntityService getEntity(int nb);
	
	//init: Environment → [Engine]
	
	public void init ( EnvironnementService env ,int h , int w);
	
	//removeEntity: [Engine] × int → [Engine]
			//pre removeEntity(E,i) requires 0 ≤ i < size(Entities(E))
	
	public void removeEntity( EntityService entity );
	
	//addEntity: [Engine] × Entity → [Engine]
	
	public void addEntity( EntityService es );
	
	//step: [Engine] → [Engine]
			//pre step() requires forall i in [0;size(Entities(E))-1], Entity::Hp(getEntity(E,i))>0
	
	public void step();
	
	public CombatService getCombat();
	/**
	 * CombatService :: getPlayer().isEnvie() == true
	 * @return
	 */
	public boolean isFinie();
}
