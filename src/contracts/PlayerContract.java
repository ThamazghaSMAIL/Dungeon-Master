package contracts;

import decorators.PlayerDecorator;
import servives.EnvironnementService;
import servives.PlayerService;
import tools.Cell;
import tools.Commande;
import tools.Face;
import tools.InvariantError;
import tools.OptionEnum;
import tools.PostConditionError;
import tools.PreconditionError;

public class PlayerContract extends PlayerDecorator implements PlayerService{
	PlayerService serv;
	public PlayerContract(PlayerService serv) {
		super(serv);
	}

	public void checkInvariant() {
		//inv de entity 
		/**
		 * 0 ≤ Col(M) < Environment::Width(Envi(M))
		 * 0 ≤ Row(M) < Environment::Height(Envi(M))
		 */

		if( super.getCol() < 0 || super.getRow() < 0 || super.getCol() > super.getEnv().getWidth() || super.getRow() > super.getEnv().getHeight() ) {
			throw new InvariantError("le Player ne peut être initialisé ! ses coordonnées dépassent les dimentions de l'env");
		}

		/**
		 * Environment::CellNature(Envi(M),Col(M),Row(M)) ∈ {WLL, DNC, DWC}
		 */
		if( serv.getEnv().getCells()[super.getRow()][super.getCol()].getNature().equals(Cell.WLL) || 
				serv.getEnv().getCells()[super.getRow()][super.getCol()].getNature().equals(Cell.DNC) ||
				serv.getEnv().getCells()[super.getRow()][super.getCol()].getNature().equals(Cell.DWC)) {
			throw new InvariantError("le Player ne peut être sur un WLL/DNC/DWC");
		}

		/**
		 * Viewable(P,-1,2) = Nature(P,-1,1) ∈/ {WALL, DWC, DNC }
		 */
		if( Viewable(-1, 2) && (serv.getEnv().getCells()[-1][2].getNature().equals(Cell.DNC) ||
				serv.getEnv().getCells()[-1][2].getNature().equals(Cell.DWC) ||
				serv.getEnv().getCells()[-1][2].getNature().equals(Cell.WLL) )) {
			throw new InvariantError("cette case ne peut être vue car WLL/DNC/DWC");
		}

		/**
		 * Viewable(P,0,2) = Nature(P,0,1) ∈/ {WALL, DWC, DNC }
		 */

		if( Viewable(0, 2) && (serv.getEnv().getCells()[0][2].getNature().equals(Cell.DNC) || 
				serv.getEnv().getCells()[0][2].getNature().equals(Cell.DWC) ||
				serv.getEnv().getCells()[0][2].getNature().equals(Cell.WLL) )) {
			throw new InvariantError("cette case ne peut être vue car WLL/DNC/DWC");
		}

		/**
		 * Viewable(P,1,2) = Nature(P,1,1) ∈/ {WALL, DWC, DNC }
		 */
		if( Viewable(1, 2) && (serv.getEnv().getCells()[1][2].getNature().equals(Cell.DNC) || 
				serv.getEnv().getCells()[1][2].getNature().equals(Cell.DWC) ||
				serv.getEnv().getCells()[1][2].getNature().equals(Cell.WLL) )) {
			throw new InvariantError("cette case ne peut être vue car WLL/DNC/DWC");
		}

		/**
		 * Viewable(P,-1,3) = Nature(P,-1,2) ∈/ {WALL, DWC, DNC } and Viewable(P,-1,2)
		 */

		if( Viewable(-1, 3) && (serv.getEnv().getCells()[-1][3].getNature().equals(Cell.DNC) || 
				serv.getEnv().getCells()[-1][3].getNature().equals(Cell.DWC) ||
				serv.getEnv().getCells()[-1][3].getNature().equals(Cell.WLL) ) || ! super.Viewable(-1, 2)) {
			throw new InvariantError("cette case ne peut être vue car WLL/DNC/DWC");
		}

		/**
		 * Viewable(P,0,3) = Nature(P,0,2) ∈/ {WALL, DWC, DNC } and Viewable(P,0,2)
		 */
		if( Viewable(0, 3) && (serv.getEnv().getCells()[0][3].getNature().equals(Cell.DNC) ||
				serv.getEnv().getCells()[0][3].getNature().equals(Cell.DWC) ||
				serv.getEnv().getCells()[0][3].getNature().equals(Cell.WLL) ) || ! super.Viewable(0, 2)) {
			throw new InvariantError("cette case ne peut être vue car WLL/DNC/DWC");
		}

		/**
		 * Viewable(P,1,3) = Nature(P,1,2) ∈/ {WALL, DWC, DNC } and Viewable(P,1,2)
		 */
		if( Viewable(1, 3) && (serv.getEnv().getCells()[1][3].getNature().equals(Cell.DNC) ||
				serv.getEnv().getCells()[1][3].getNature().equals(Cell.DWC) ||
				serv.getEnv().getCells()[1][3].getNature().equals(Cell.WLL) ) || ! super.Viewable(1, 2)) {
			throw new InvariantError("cette case ne peut être vue car WLL/DNC/DWC");
		}
	}

	@Override
	public boolean Viewable(int row, int col) {
		//pre Viewable(P,x,y) requires x ∈ {-1,0,1}and y ∈ {-1,+3}

		if( row != -1 && row != 0 && row != 1 )
			throw new InvariantError(" Viewable error ! ");

		if( col != -1 && col != 3 )
			throw new InvariantError(" Viewable error ! ");

		return super.Viewable(row, col);
	}

	String fonction="";
	
	public void step() {
		checkInvariant();
		Commande c = super.getLastCommande();
		super.step();
		checkInvariant();
		
		/**
		 * LastCom(P)=FF implies step(P) = Forward(P)
		 */
		if( c.equals(Commande.FF) && fonction.contains("forward")) {
			throw new PostConditionError("erreur step player");
		}
		/**
		 * LastCom(P)=BB implies step(P) = Backward(P)
		 */
		if( c.equals(Commande.BB) && fonction.contains("backward")) {
			throw new PostConditionError("erreur step player");
		}
		/**
		 * LastCom(P)=LL implies step(P) = StrafeLeft(P)
		 */
		if( c.equals(Commande.LL) && fonction.contains("strafL")) {
			throw new PostConditionError("erreur step player");
		}
		/**
		 * LastCom(P)=RR implies step(P) = StrafeRight(P)
		 */
		if( c.equals(Commande.RR) && fonction.contains("strafR")) {
			throw new PostConditionError("erreur step player");
		}
		/**
		 * LastCom(P)=TL implies step(P) = TurnLeft(P)
		 */
		if( c.equals(Commande.TL) && fonction.contains("turnL")) {
			throw new PostConditionError("erreur step player");
		}
		/**
		 * LastCom(P)=TR implies step(P) = TurnRight(P)
		 */
		if( c.equals(Commande.TR) && fonction.contains("turnR")) {
			throw new PostConditionError("erreur step player");
		}
	}





	@Override
	public void init(int row, int col,int hp , Face face , EnvironnementService env) {

		checkInvariant();
		if( super.getHp() <= 0 ) {
			throw new PreconditionError("hp est négatif");
		}


		if( super.getCol() < 0 || super.getCol() > super.getEnv().getWidth() || super.getRow() < 0 || super.getRow() > super.getEnv().getHeight() ) {
			throw new PreconditionError("le Mob ne peut être initialisé ! ses coordonnées dépassent les dimentions de l'env");
		}
		super.init(row, col, hp,face, env);

		if( super.getCol() != col || super.getRow() != row || super.getFace() != face || super.getEnv() != env ) {
			throw new PostConditionError("le Mob ne peut être initialisé ! ses coordonnées dépassent les dimentions de l'env");
		}

		if( super.getHp() != hp )
			throw new PostConditionError("Hp n'est pas coherant !");
		
		if( ! super.getFace().equals(Face.N))
			throw new PostConditionError("Player error post");
		
		
		
		if(! super.isEnVie() ) {
			throw new PostConditionError("post player envie");
		}
		
		if( super.getHp() != 3 )
			throw new PostConditionError("post player hp");
	}






	@Override
	public void forward() {
		fonction ="forward";
		checkInvariant();

		int row = super.getRow();
		int col = super.getCol();
		Face dir = super.getFace();
		super.forward();

		/*****N*******/
		if( dir.equals(Face.N) &&
				((super.getEnv().getCells()[row+1][ col].getNature().equals(Cell.EMP)||
						super.getEnv().getCells()[row+1][ col].getNature().equals(Cell.DNO)) &&
						row+1 <super.getEnv().getHeight() &&
						super.getEnv().getCells()[row+1][ col].getContent().equals(OptionEnum.No)) &&

				(super.getRow() != row+1 || super.getCol() != col || ! super.getFace().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas avancé au Nord");
		}

		if( dir.equals(Face.N) &&
				( ((! super.getEnv().getCells()[row+1][ col].getNature().equals(Cell.EMP) &&
						! super.getEnv().getCells()[row+1][ col].getNature().equals(Cell.DNO)) || 
						row+1 >= super.getEnv().getHeight() ||
						! super.getEnv().getCells()[row+1][ col].getContent().equals(OptionEnum.No)) &&

						(super.getRow() != row || super.getCol() != col || ! super.getFace().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****E*******/

		if( dir.equals(Face.E) &&
				((super.getEnv().getCells()[row][col+1].getNature().equals(Cell.EMP)||
						super.getEnv().getCells()[row][col+1].getNature().equals(Cell.DNO)) &&
						col+1 <super.getEnv().getWidth() &&
						super.getEnv().getCells()[row][col+1].getContent().equals(OptionEnum.No)) &&

				(super.getRow() != row || super.getCol() != col+1 || ! super.getFace().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas avancé à l'est");
		}

		if( dir.equals(Face.E) &&
				( (! super.getEnv().getCells()[row][col+1].getNature().equals(Cell.EMP) && 
						! super.getEnv().getCells()[row][col+1].getNature().equals(Cell.DNO)) || 
						col+1 >= super.getEnv().getHeight() ||
						! super.getEnv().getCells()[row][col+1].getContent().equals(OptionEnum.No) &&

						(super.getRow() != row || super.getCol() != col || ! super.getFace().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****S*******/

		if( dir.equals(Face.S) &&
				((super.getEnv().getCells()[row-1][col].getNature().equals(Cell.EMP)||
						super.getEnv().getCells()[row-1][col].getNature().equals(Cell.DNO)) &&
						row-1 >= 0 &&
						super.getEnv().getCells()[row-1][col].getContent().equals(OptionEnum.No)) &&

				(super.getRow() != row-1 || super.getCol() != col || ! super.getFace().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas avancé au Sud");
		}

		if( dir.equals(Face.S) &&
				( (! super.getEnv().getCells()[row-1][col].getNature().equals(Cell.EMP) &&
						! super.getEnv().getCells()[row-1][col].getNature().equals(Cell.DNO)) || 
						row-1 < 0 ||
						! super.getEnv().getCells()[row-1][col].getContent().equals(OptionEnum.No) &&

						(super.getRow() != row || super.getCol() != col || ! super.getFace().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****W*******/

		if(dir.equals(Face.W) &&
				((super.getEnv().getCells()[row][col-1].getNature().equals(Cell.EMP)||
						super.getEnv().getCells()[row][col-1].getNature().equals(Cell.DNO)) &&
						col-1 >= 0 &&
						super.getEnv().getCells()[row][col-1].getContent().equals(OptionEnum.No)) &&

				(super.getRow() != row || super.getCol() != col-1 || ! super.getFace().equals(Face.N)) ){
			throw new InvariantError("le Mob n'a pas avancé au W");
		}

		if( dir.equals(Face.W) &&
				( (! super.getEnv().getCells()[row][col-1].getNature().equals(Cell.EMP) &&
						! super.getEnv().getCells()[row][col-1].getNature().equals(Cell.DNO)) || 
						row-1 < 0 ||
						! super.getEnv().getCells()[row][col-1].getContent().equals(OptionEnum.No)) &&

				(super.getRow() != row || super.getCol() != col || ! super.getFace().equals(Face.N)) ) {
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}
		checkInvariant();
	}
	@Override
	public void backward() {
		fonction ="backward";
		checkInvariant();

		int row = super.getRow();
		int col = super.getCol();
		Face dir = super.getFace();
		super.backward();

		/*****N*******/
		if( dir.equals(Face.N) &&
				((super.getEnv().getCells()[row-1][col].getNature().equals(Cell.EMP)||
						super.getEnv().getCells()[row-1][col].getNature().equals(Cell.DNO)) &&
						row-1 >=0 &&
						super.getEnv().getCells()[row-1][col].getContent().equals(OptionEnum.No)) &&

				(super.getRow() != row-1 || super.getCol() != col || ! super.getFace().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas reculer ");
		}

		if( dir.equals(Face.N) &&
				( ((! super.getEnv().getCells()[row-1][col].getNature().equals(Cell.EMP) && 
						! super.getEnv().getCells()[row-1][col].getNature().equals(Cell.DNO)) || 
						row-1 < 0 ||
						! super.getEnv().getCells()[row-1][col].getContent().equals(OptionEnum.No)) &&

						(super.getRow() != row || super.getCol() != col || ! super.getFace().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****S*******/
		if( dir.equals(Face.S) &&
				((super.getEnv().getCells()[row+1][col].getNature().equals(Cell.EMP)||
						super.getEnv().getCells()[row+1][col].getNature().equals(Cell.DNO)) &&
						row+1 <super.getEnv().getHeight() &&
						super.getEnv().getCells()[row+1][col].getContent().equals(OptionEnum.No)) &&

				(super.getRow() != row+1 || super.getCol() != col || ! super.getFace().equals(Face.S))){
			throw new InvariantError("le Mob n'a pas reculé");
		}

		if( dir.equals(Face.S) &&
				((!super.getEnv().getCells()[row+1][col].getNature().equals(Cell.EMP) &&
						! super.getEnv().getCells()[row+1][col].getNature().equals(Cell.DNO)) || 
						row+1 >= super.getEnv().getHeight() ||
						!  super.getEnv().getCells()[row+1][col].getContent().equals(OptionEnum.No)) &&

				(super.getRow() != row || super.getCol() != col|| ! super.getFace().equals(Face.S)) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****E*******/
		if( dir.equals(Face.E) &&
				((super.getEnv().getCells()[row][col+1].getNature().equals(Cell.EMP)||
						super.getEnv().getCells()[row][col+1].getNature().equals(Cell.DNO)) &&
						col+1 < super.getEnv().getWidth() &&
						super.getEnv().getCells()[row][col+1].getContent().equals(OptionEnum.No)) &&

				(super.getRow() != row || super.getCol() != col+1 || ! super.getFace().equals(Face.E))){
			throw new InvariantError("le Mob n'a pas reculé");
		}

		if( dir.equals(Face.E) &&
				((! super.getEnv().getCells()[row][col+1].getNature().equals(Cell.EMP) &&
						! super.getEnv().getCells()[row][col+1].getNature().equals(Cell.DNO)) || 
						col+1 >= super.getEnv().getWidth() ||
						! super.getEnv().getCells()[row][col+1].getContent().equals(OptionEnum.No)) &&

				(super.getRow() != row || super.getCol() != col || ! super.getFace().equals(Face.E)) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****W*******/

		if( dir.equals(Face.W) &&
				((super.getEnv().getCells()[row][col-1].getNature().equals(Cell.EMP)||
						super.getEnv().getCells()[row][col-1].getNature().equals(Cell.DNO)) &&
						col-1 >=0 &&
						super.getEnv().getCells()[row][col-1].getContent().equals(OptionEnum.No)) &&

				(super.getRow() != row || super.getCol() != col-1 || ! super.getFace().equals(Face.W))){
			throw new InvariantError("le Mob n'a pas reculé");
		}

		if( dir.equals(Face.W) &&
				((! super.getEnv().getCells()[row][col-1].getNature().equals(Cell.EMP) &&
						! super.getEnv().getCells()[row][col-1].getNature().equals(Cell.DNO)) || 
						col-1 < 0 ||
						! super.getEnv().getCells()[row][col-1].getContent().equals(OptionEnum.No)) &&

				(super.getRow() != row || super.getCol() != col || ! super.getFace().equals(Face.W)) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}
		checkInvariant();
	}

	@Override
	public void turnL() {
		fonction ="turnL";
		checkInvariant();
		//Face(M)=N implies Face(TurnLeft(M))=W
		Face dir = super.getFace();
		super.turnL();

		if( dir.equals(Face.N) && ! super.getFace().equals(Face.W))
			throw new InvariantError("le Mob n'a pas tourné à gauche");

		//Face(M)=W implies Face(TurnLeft(M))=S
		if( dir.equals(Face.W) && ! super.getFace().equals(Face.S))
			throw new InvariantError("le Mob n'a pas tourné à gauche");

		//Face(M)=S implies Face(TurnLeft(M))=E
		if( dir.equals(Face.S) && ! super.getFace().equals(Face.E))
			throw new InvariantError("le Mob n'a pas tourné à gauche");

		//Face(M)=E implies Face(TurnLeft(M))=N
		if( dir.equals(Face.E) && ! super.getFace().equals(Face.N))
			throw new InvariantError("le Mob n'a pas tourné à gauche");
		checkInvariant();
	}


	@Override
	public void turnR() {
		fonction = "turnR";
		checkInvariant();
		Face dir = super.getFace();
		super.turnL();

		if( dir.equals(Face.N) && ! super.getFace().equals(Face.E))
			throw new InvariantError("le Mob n'a pas tourné à droite");

		//Face(M)=W implies Face(TurnLeft(M))=S
		if( dir.equals(Face.W) && ! super.getFace().equals(Face.N))
			throw new InvariantError("le Mob n'a pas tourné à droite");

		//Face(M)=S implies Face(TurnLeft(M))=W
		if( dir.equals(Face.S) && ! super.getFace().equals(Face.W))
			throw new InvariantError("le Mob n'a pas tourné à droite");

		//Face(M)=E implies Face(TurnLeft(M))=S
		if( dir.equals(Face.E) && ! super.getFace().equals(Face.S))
			throw new InvariantError("le Mob n'a pas tourné à droite");

		checkInvariant();
	}


	@Override
	public void strafeL() {
		fonction ="strafeL";
		checkInvariant();

		int row = super.getRow();
		int col = super.getCol();
		Face dir = super.getFace();

		/*****N*******/
		if( dir.equals(Face.N) &&
				(( super.getEnv().getCells()[row][col-1].getNature().equals(Cell.EMP)||
						super.getEnv().getCells()[row][col-1].getNature().equals(Cell.DNO)) &&
						col-1 >= 0 &&
						super.getEnv().getCells()[row][col-1].getContent().equals(OptionEnum.No)) &&

				(super.getRow() != row || super.getCol() != col-1 || ! super.getFace().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas strafeL");
		}

		if( dir.equals(Face.N) &&
				( ((!  super.getEnv().getCells()[row][col-1].getNature().equals(Cell.EMP) && 
						! super.getEnv().getCells()[row][col-1].getNature().equals(Cell.DNO)) || 
						col-1 < 0  ||
						! super.getEnv().getCells()[row][col-1].getContent().equals(OptionEnum.No)) &&

						(super.getRow() != row || super.getCol() != col || ! super.getFace().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****S*******/
		if( dir.equals(Face.S) &&
				((super.getEnv().getCells()[row][col+1].getNature().equals(Cell.EMP)||
						super.getEnv().getCells()[row][col+1].getNature().equals(Cell.DNO)) &&
						col+1 < super.getEnv().getWidth() &&
						super.getEnv().getCells()[row][col+1].getContent().equals(OptionEnum.No)) &&

				(super.getRow() != row || super.getCol() != col+1 || ! super.getFace().equals(Face.S))){
			throw new InvariantError("le Mob n'a pas strafeL");
		}

		if( dir.equals(Face.S) &&
				( ((! super.getEnv().getCells()[row][col+1].getNature().equals(Cell.EMP) && 
						! super.getEnv().getCells()[row][col+1].getNature().equals(Cell.DNO)) || 
						col+1 >= super.getEnv().getWidth() ||
						! super.getEnv().getCells()[row][col+1].getContent().equals(OptionEnum.No)) &&

						(super.getRow() != row || super.getCol() != col || ! super.getFace().equals(Face.S))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****E*******/
		if( dir.equals(Face.E) &&
				((super.getEnv().getCells()[row-1][col].getNature().equals(Cell.EMP)||
						super.getEnv().getCells()[row-1][col].getNature().equals(Cell.DNO)) &&
						row-1 >= 0 &&
						super.getEnv().getCells()[row-1][col].getContent().equals(OptionEnum.No)) &&

				(super.getRow() != row-1 || super.getCol() != col || ! super.getFace().equals(Face.E))){
			throw new InvariantError("le Mob n'a pas strafeL");
		}

		if( dir.equals(Face.E) &&
				( ((! super.getEnv().getCells()[row-1][col].getNature().equals(Cell.EMP) &&
						! super.getEnv().getCells()[row-1][col].getNature().equals(Cell.DNO)) || 
						row-1 < 0 ||
						! super.getEnv().getCells()[row-1][col].getContent().equals(OptionEnum.No)) &&

						(super.getRow() != row || super.getCol() != col || ! super.getFace().equals(Face.E))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****W*******/
		if( dir.equals(Face.W) &&
				((super.getEnv().getCells()[row+1][col].getNature().equals(Cell.EMP)||
						super.getEnv().getCells()[row+1][col].getNature().equals(Cell.DNO)) &&
						row+1 < super.getEnv().getHeight() &&
						super.getEnv().getCells()[row+1][col].getContent().equals(OptionEnum.No)) &&

				(super.getRow() != row+1 || super.getCol() != col || ! super.getFace().equals(Face.W))){
			throw new InvariantError("le Mob n'a pas strafeL");
		}

		if( dir.equals(Face.W) &&
				( ((! super.getEnv().getCells()[row+1][col].getNature().equals(Cell.EMP) && 
						! super.getEnv().getCells()[row+1][col].getNature().equals(Cell.DNO)) || 
						row+1 >= super.getEnv().getHeight() ||
						! super.getEnv().getCells()[row+1][col].getContent().equals(OptionEnum.No)) &&

						(super.getRow() != row || super.getCol() != col || ! super.getFace().equals(Face.E))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}
		checkInvariant();
	}


	@Override
	public void strafeR() {
		fonction = "strafeR";
		checkInvariant();

		int row = super.getRow();
		int col = super.getCol();
		Face dir = super.getFace();
		/*****N*******/
		if( dir.equals(Face.N) &&
				((super.getEnv().getCells()[row][col+1].getNature().equals(Cell.EMP)||
						super.getEnv().getCells()[row][col+1].getNature().equals(Cell.DNO)) &&
						col+1 < super.getEnv().getWidth() &&
						super.getEnv().getCells()[row][col+1].getContent().equals(OptionEnum.No)) &&

				(super.getRow() != row || super.getCol() != col+1 || ! super.getFace().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas strafeR");
		}

		if( dir.equals(Face.N) &&
				( ((! super.getEnv().getCells()[row][col+1].getNature().equals(Cell.EMP) &&
						! super.getEnv().getCells()[row][col+1].getNature().equals(Cell.DNO)) || 
						col+1 >= super.getEnv().getWidth() ||
						! super.getEnv().getCells()[row][col+1].getContent().equals(OptionEnum.No)) &&

						(super.getRow() != row || super.getCol() != col || ! super.getFace().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****S*******/
		if( dir.equals(Face.S) &&
				((super.getEnv().getCells()[row][col-1].getNature().equals(Cell.EMP)||
						super.getEnv().getCells()[row][col-1].getNature().equals(Cell.DNO)) &&
						col-1 >= 0 &&
						super.getEnv().getCells()[row][col-1].getContent().equals(OptionEnum.No)) &&

				(super.getRow() != row || super.getCol() != col-1 || ! super.getFace().equals(Face.S))){
			throw new InvariantError("le Mob n'a pas strafeR");
		}

		if( dir.equals(Face.S) &&
				( ((! super.getEnv().getCells()[row][col-1].getNature().equals(Cell.EMP) && 
						! super.getEnv().getCells()[row][col-1].getNature().equals(Cell.DNO)) || 
						col-1 < 0  ||
						! super.getEnv().getCells()[row][col-1].getContent().equals(OptionEnum.No)) &&

						(super.getRow() != row || super.getCol() != col || ! super.getFace().equals(Face.S))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****E*******/
		if( dir.equals(Face.E) &&
				((super.getEnv().getCells()[row+1][col].getNature().equals(Cell.EMP)||
						super.getEnv().getCells()[row+1][col].getNature().equals(Cell.DNO)) &&
						row+1 < super.getEnv().getHeight() &&
						super.getEnv().getCells()[row+1][col].getContent().equals(OptionEnum.No)) &&

				(super.getRow() != row+1 || super.getCol() != col || ! super.getFace().equals(Face.E))){
			throw new InvariantError("le Mob n'a pas strafeR");
		}

		if( dir.equals(Face.E) &&
				( ((!super.getEnv().getCells()[row+1][col].getNature().equals(Cell.EMP) && 
						! super.getEnv().getCells()[row+1][col].getNature().equals(Cell.DNO)) || 
						row+1 >= super.getEnv().getHeight() ||
						! super.getEnv().getCells()[row+1][col].getContent().equals(OptionEnum.No)) &&

						(super.getRow() != row || super.getCol() != col || ! super.getFace().equals(Face.E))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}


		/*****W*******/
		if( dir.equals(Face.W) &&
				((super.getEnv().getCells()[row-1][col].getNature().equals(Cell.EMP)||
						super.getEnv().getCells()[row-1][col].getNature().equals(Cell.DNO)) &&
						row-1 >= 0 &&
						super.getEnv().getCells()[row-1][col].getContent().equals(OptionEnum.No)) &&

				(super.getRow() != row-1 || super.getCol() != col || ! super.getFace().equals(Face.W))){
			throw new InvariantError("le Mob n'a pas strafeR");
		}

		if( dir.equals(Face.W) &&
				( ((! super.getEnv().getCells()[row-1][col].getNature().equals(Cell.EMP) &&
						! super.getEnv().getCells()[row-1][col].getNature().equals(Cell.DNO)) || 
						row-1 < 0 ||
						! super.getEnv().getCells()[row-1][col].getContent().equals(OptionEnum.No)) &&

						(super.getRow() != row || super.getCol() != col || ! super.getFace().equals(Face.W))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}
		checkInvariant();
	}
	@Override
	public Cell getNatureP(int i , int j ) {
		if( !(i==0 || i==1 || i==-1) )
			throw new PreconditionError("playercontrat getNature");
			if( !(j>= -1 && i<=3) ) 
				throw new PreconditionError("playercontrat getNature");
			Cell c = super.getNatureP(i, j)	;
			checkInvariant();
			return c;
	}

	@Override
	public Cell getContentP(int i , int j ) {
		if( i==0 || i==1 || i==-1 )
			if( j>= -1 && i<=3 ) 
				if( this.getEnv().getCells()[this.getRow()+i][this.getCol()+j] != null ) {
					return this.getEnv().getCells()[this.getRow()+i][this.getCol()+j].getNature();
				}
		return null;
	}


}
