package contracts;

import decorators.ClefDecorator;
import servives.ClefService;
import servives.EnvironnementService;
import tools.PostConditionError;
import tools.PreconditionError;

public class ClefContract extends ClefDecorator {

	public ClefContract(ClefService serv) {
		super(serv);
	}

	public void checkInvariant() {
	}

	@Override
	public void init(EnvironnementService env, int i, int j) {
		checkInvariant();
		
		if( i < 0 || j <0 || i >= env.getHeight() || j >= env.getWidth() ) {
			throw new PreconditionError("la clef peut pas être initialisé");
		}
		
		super.init(env, i, j);
		
		if( this.getI() != i || this.getJ() != j || this.getEnv() != env ) {
			throw new PostConditionError("Erreur lors de l'initialisation de la clef");
		}
	}
}
