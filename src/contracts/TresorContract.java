package contracts;

import decorators.TresorDecorator;
import servives.EnvironnementService;
import servives.TresorService;
import tools.PostConditionError;
import tools.PreconditionError;

public class TresorContract extends TresorDecorator {

	public TresorContract(TresorService serv) {
		super(serv);
	}

	public void checkInvariant() {
	}

	@Override
	public void init(EnvironnementService env, int i, int j) {
		checkInvariant();
		
		if( i < 0 || j <0 || i >= env.getHeight() || j >= env.getWidth() ) {
			throw new PreconditionError("le tresor peut pas être initialisé");
		}
		if( i== super.getEnv().getEntities().get(0).getRow() || j == super.getEnv().getEntities().get(0).getCol())
			throw new PreconditionError("erreur tresorcontrat");
		
		super.init(env, i, j);
		
		if( super.getI() != i || super.getJ() != j || ! super.getEnv().equals(env) || super.getTrouve() ) {
			throw new PostConditionError("Erreur lors de l'initialisation du trésor");
		}
		checkInvariant();
	}

}
