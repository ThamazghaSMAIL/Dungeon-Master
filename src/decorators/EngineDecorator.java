package decorators;

import java.util.List;

import servives.CombatService;
import servives.EngineService;
import servives.EntityService;
import servives.EnvironnementService;

public class EngineDecorator implements EngineService {

	public EngineDecorator(EngineService delegate) {
		super();
		this.delegate = delegate;
	}

	EngineService delegate ;

	@Override
	public EnvironnementService getEnvi() {
		return delegate.getEnvi();
	}

	@Override
	public List<EntityService> getEntities() {
		return delegate.getEntities();
	}

	@Override
	public EntityService getEntity(int nb) {
		return delegate.getEntity(nb);
	}

	@Override
	public void init(EnvironnementService env,int h ,int w) {
		
	}

	@Override
	public void removeEntity(EntityService e) {
		
	}

	@Override
	public void addEntity(EntityService es) {
		delegate.addEntity(es);
	}

	@Override
	public void step() {
		delegate.step();
	}

	@Override
	public boolean isFinie() {
		return delegate.isFinie();
	}

	@Override
	public CombatService getCombat() {
		return delegate.getCombat();
	}

}
