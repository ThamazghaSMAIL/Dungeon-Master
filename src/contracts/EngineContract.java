package contracts;

import decorators.EngineDecorator;
import servives.EngineService;

public class EngineContract extends EngineDecorator implements EngineService{

	public EngineContract(EngineService delegate) {
		super(delegate);
	}
	
	
	public void chechInvariant (){
		//forall i in [0;size(Entities(E))-1], Entity::Envi(getEntity(E,i))=Envi(E)
		for ( int i = 0 ; i < getEntities().size() ; i++ ) {
			
		}
	}


	@Override
	public void step() {
		
	}


}
