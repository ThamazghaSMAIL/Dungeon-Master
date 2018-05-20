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
		
		super.init(env, i, j);
		
		if( this.getI() != i || this.getJ() != j || this.getEnv() != env ) {
			throw new PostConditionError("Erreur lors de l'initialisation du trésor");
		}
		
	}

}
