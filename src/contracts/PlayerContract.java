package contracts;

import decorators.PlayerDecorator;
import servives.EntityService;
import servives.EnvironnementService;
import servives.MobService;
import servives.PlayerService;
import tools.Cell;
import tools.Commande;
import tools.Face;
import tools.InvariantError;
import tools.Option;
import tools.OptionEnum;
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

		if( this.getCol() < 0 || this.getRow() < 0 || this.getCol() > this.getEnv().getWidth() || this.getRow() > this.getEnv().getHeight() ) {
			throw new InvariantError("le Player ne peut être initialisé ! ses coordonnées dépassent les dimentions de l'env");
		}

		/**
		 * Environment::CellNature(Envi(M),Col(M),Row(M)) ∈ {WLL, DNC, DWC}
		 */
		if( serv.getEnv().getCells()[this.getRow()][this.getCol()].getNature().equals(Cell.WLL) || 
				serv.getEnv().getCells()[this.getRow()][this.getCol()].getNature().equals(Cell.DNC) ||
				serv.getEnv().getCells()[this.getRow()][this.getCol()].getNature().equals(Cell.DWC)) {
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
				serv.getEnv().getCells()[-1][3].getNature().equals(Cell.WLL) ) || ! this.Viewable(-1, 2)) {
			throw new InvariantError("cette case ne peut être vue car WLL/DNC/DWC");
		}

		/**
		 * Viewable(P,0,3) = Nature(P,0,2) ∈/ {WALL, DWC, DNC } and Viewable(P,0,2)
		 */
		if( Viewable(0, 3) && (serv.getEnv().getCells()[0][3].getNature().equals(Cell.DNC) ||
				serv.getEnv().getCells()[0][3].getNature().equals(Cell.DWC) ||
				serv.getEnv().getCells()[0][3].getNature().equals(Cell.WLL) ) || ! this.Viewable(0, 2)) {
			throw new InvariantError("cette case ne peut être vue car WLL/DNC/DWC");
		}

		/**
		 * Viewable(P,1,3) = Nature(P,1,2) ∈/ {WALL, DWC, DNC } and Viewable(P,1,2)
		 */
		if( Viewable(1, 3) && (serv.getEnv().getCells()[1][3].getNature().equals(Cell.DNC) ||
				serv.getEnv().getCells()[1][3].getNature().equals(Cell.DWC) ||
				serv.getEnv().getCells()[1][3].getNature().equals(Cell.WLL) ) || ! this.Viewable(1, 2)) {
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

	public void step() {
		checkInvariant();
		super.step();

		//		/**
		//		 * LastCom(P)=FF implies step(P) = Forward(P)
		//		 */
		//		if( this.getLastCom().equals(Commande.FF)) {
		//			this.forward();
		//		}
		//		/**
		//		 * LastCom(P)=BB implies step(P) = Backward(P)
		//		 */
		//		if( this.getLastCom().equals(Commande.BB)) {
		//			this.backward();
		//		}
		//		/**
		//		 * LastCom(P)=LL implies step(P) = StrafeLeft(P)
		//		 */
		//		if( this.getLastCom().equals(Commande.LL)) {
		//			this.strafeL();
		//		}
		//		/**
		//		 * LastCom(P)=RR implies step(P) = StrafeRight(P)
		//		 */
		//		if( this.getLastCom().equals(Commande.RR)) {
		//			this.strafeR();
		//		}
		//		/**
		//		 * LastCom(P)=TL implies step(P) = TurnLeft(P)
		//		 */
		//		if( this.getLastCom().equals(Commande.TL)) {
		//			this.turnL();
		//		}
		//		/**
		//		 * LastCom(P)=TR implies step(P) = TurnRight(P)
		//		 */
		//		if( this.getLastCom().equals(Commande.TR)) {
		//			this.turnR();
		//		}
		checkInvariant();
	}





	@Override
	public void init(int row, int col,int hp , Face face , EnvironnementService env) {

		checkInvariant();
		if( this.getHp() <= 0 ) {
			throw new PreconditionError("hp est négatif");
		}


		if( this.getCol() < 0 || this.getCol() > this.getEnv().getWidth() || this.getRow() < 0 || this.getRow() > this.getEnv().getHeight() ) {
			throw new PreconditionError("le Mob ne peut être initialisé ! ses coordonnées dépassent les dimentions de l'env");
		}
		super.init(row, col, hp,face, env);

		//		if( this.getCol() != col || this.getRow() != lin || this.getFace() != face || this.getEnv() != env ) {
		//			throw new InvariantError("le Mob ne peut être initialisé ! ses coordonnées dépassent les dimentions de l'env");
		//		}
		//		
		//		if( this.getHp() != h )
		//			throw new InvariantError("Hp n'est pas coherant !");
	}






	@Override
	public void forward() {
		checkInvariant();

		int row = this.getRow();
		int col = this.getCol();
		Face dir = this.getFace();
		super.forward();

		/*****N*******/
		if( dir.equals(Face.N) &&
				((this.getEnv().getCells()[row+1][ col].getNature().equals(Cell.EMP)||
						this.getEnv().getCells()[row+1][ col].getNature().equals(Cell.DNO)) &&
						row+1 <this.getEnv().getHeight() &&
						this.getEnv().getCells()[row+1][ col].getContent().equals(OptionEnum.No)) &&

				(this.getRow() != row+1 || this.getCol() != col || ! this.getFace().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas avancé au Nord");
		}

		if( dir.equals(Face.N) &&
				( ((! this.getEnv().getCells()[row+1][ col].getNature().equals(Cell.EMP) &&
						! this.getEnv().getCells()[row+1][ col].getNature().equals(Cell.DNO)) || 
						row+1 >= this.getEnv().getHeight() ||
						! this.getEnv().getCells()[row+1][ col].getContent().equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getFace().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****E*******/

		if( dir.equals(Face.E) &&
				((this.getEnv().getCells()[row][col+1].getNature().equals(Cell.EMP)||
						this.getEnv().getCells()[row][col+1].getNature().equals(Cell.DNO)) &&
						col+1 <this.getEnv().getWidth() &&
						this.getEnv().getCells()[row][col+1].getContent().equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col+1 || ! this.getFace().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas avancé à l'est");
		}

		if( dir.equals(Face.E) &&
				( (! this.getEnv().getCells()[row][col+1].getNature().equals(Cell.EMP) && 
						! this.getEnv().getCells()[row][col+1].getNature().equals(Cell.DNO)) || 
						col+1 >= this.getEnv().getHeight() ||
						! this.getEnv().getCells()[row][col+1].getContent().equals(OptionEnum.No) &&

						(this.getRow() != row || this.getCol() != col || ! this.getFace().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****S*******/

		if( dir.equals(Face.S) &&
				((this.getEnv().getCells()[row-1][col].getNature().equals(Cell.EMP)||
						this.getEnv().getCells()[row-1][col].getNature().equals(Cell.DNO)) &&
						row-1 >= 0 &&
						this.getEnv().getCells()[row-1][col].getContent().equals(OptionEnum.No)) &&

				(this.getRow() != row-1 || this.getCol() != col || ! this.getFace().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas avancé au Sud");
		}

		if( dir.equals(Face.S) &&
				( (! this.getEnv().getCells()[row-1][col].getNature().equals(Cell.EMP) &&
						! this.getEnv().getCells()[row-1][col].getNature().equals(Cell.DNO)) || 
						row-1 < 0 ||
						! this.getEnv().getCells()[row-1][col].getContent().equals(OptionEnum.No) &&

						(this.getRow() != row || this.getCol() != col || ! this.getFace().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****W*******/

		if(dir.equals(Face.W) &&
				((this.getEnv().getCells()[row][col-1].getNature().equals(Cell.EMP)||
						this.getEnv().getCells()[row][col-1].getNature().equals(Cell.DNO)) &&
						col-1 >= 0 &&
						this.getEnv().getCells()[row][col-1].getContent().equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col-1 || ! this.getFace().equals(Face.N)) ){
			throw new InvariantError("le Mob n'a pas avancé au W");
		}

		if( dir.equals(Face.W) &&
				( (! this.getEnv().getCells()[row][col-1].getNature().equals(Cell.EMP) &&
						! this.getEnv().getCells()[row][col-1].getNature().equals(Cell.DNO)) || 
						row-1 < 0 ||
						! this.getEnv().getCells()[row][col-1].getContent().equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col || ! this.getFace().equals(Face.N)) ) {
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}
		checkInvariant();
	}
	@Override
	public void backward() {
		checkInvariant();

		int row = this.getRow();
		int col = this.getCol();
		Face dir = this.getFace();
		super.backward();

		/*****N*******/
		if( dir.equals(Face.N) &&
				((this.getEnv().getCells()[row-1][col].getNature().equals(Cell.EMP)||
						this.getEnv().getCells()[row-1][col].getNature().equals(Cell.DNO)) &&
						row-1 >=0 &&
						this.getEnv().getCells()[row-1][col].getContent().equals(OptionEnum.No)) &&

				(this.getRow() != row-1 || this.getCol() != col || ! this.getFace().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas reculer ");
		}

		if( dir.equals(Face.N) &&
				( ((! this.getEnv().getCells()[row-1][col].getNature().equals(Cell.EMP) && 
						! this.getEnv().getCells()[row-1][col].getNature().equals(Cell.DNO)) || 
						row-1 < 0 ||
						! this.getEnv().getCells()[row-1][col].getContent().equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getFace().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****S*******/
		if( dir.equals(Face.S) &&
				((this.getEnv().getCells()[row+1][col].getNature().equals(Cell.EMP)||
						this.getEnv().getCells()[row+1][col].getNature().equals(Cell.DNO)) &&
						row+1 <this.getEnv().getHeight() &&
						this.getEnv().getCells()[row+1][col].getContent().equals(OptionEnum.No)) &&

				(this.getRow() != row+1 || this.getCol() != col || ! this.getFace().equals(Face.S))){
			throw new InvariantError("le Mob n'a pas reculé");
		}

		if( dir.equals(Face.S) &&
				((!this.getEnv().getCells()[row+1][col].getNature().equals(Cell.EMP) &&
						! this.getEnv().getCells()[row+1][col].getNature().equals(Cell.DNO)) || 
						row+1 >= this.getEnv().getHeight() ||
						!  this.getEnv().getCells()[row+1][col].getContent().equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col|| ! this.getFace().equals(Face.S)) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****E*******/
		if( dir.equals(Face.E) &&
				((this.getEnv().getCells()[row][col+1].getNature().equals(Cell.EMP)||
						this.getEnv().getCells()[row][col+1].getNature().equals(Cell.DNO)) &&
						col+1 < this.getEnv().getWidth() &&
						this.getEnv().getCells()[row][col+1].getContent().equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col+1 || ! this.getFace().equals(Face.E))){
			throw new InvariantError("le Mob n'a pas reculé");
		}

		if( dir.equals(Face.E) &&
				((! this.getEnv().getCells()[row][col+1].getNature().equals(Cell.EMP) &&
						! this.getEnv().getCells()[row][col+1].getNature().equals(Cell.DNO)) || 
						col+1 >= this.getEnv().getWidth() ||
						! this.getEnv().getCells()[row][col+1].getContent().equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col || ! this.getFace().equals(Face.E)) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****W*******/

		if( dir.equals(Face.W) &&
				((this.getEnv().getCells()[row][col-1].getNature().equals(Cell.EMP)||
						this.getEnv().getCells()[row][col-1].getNature().equals(Cell.DNO)) &&
						col-1 >=0 &&
						this.getEnv().getCells()[row][col-1].getContent().equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col-1 || ! this.getFace().equals(Face.W))){
			throw new InvariantError("le Mob n'a pas reculé");
		}

		if( dir.equals(Face.W) &&
				((! this.getEnv().getCells()[row][col-1].getNature().equals(Cell.EMP) &&
						! this.getEnv().getCells()[row][col-1].getNature().equals(Cell.DNO)) || 
						col-1 < 0 ||
						! this.getEnv().getCells()[row][col-1].getContent().equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col || ! this.getFace().equals(Face.W)) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}
		checkInvariant();
	}

	@Override
	public void turnL() {
		checkInvariant();
		//Face(M)=N implies Face(TurnLeft(M))=W
		Face dir = this.getFace();
		super.turnL();

		if( dir.equals(Face.N) && ! this.getFace().equals(Face.W))
			throw new InvariantError("le Mob n'a pas tourné à gauche");

		//Face(M)=W implies Face(TurnLeft(M))=S
		if( dir.equals(Face.W) && ! this.getFace().equals(Face.S))
			throw new InvariantError("le Mob n'a pas tourné à gauche");

		//Face(M)=S implies Face(TurnLeft(M))=E
		if( dir.equals(Face.S) && ! this.getFace().equals(Face.E))
			throw new InvariantError("le Mob n'a pas tourné à gauche");

		//Face(M)=E implies Face(TurnLeft(M))=N
		if( dir.equals(Face.E) && ! this.getFace().equals(Face.N))
			throw new InvariantError("le Mob n'a pas tourné à gauche");
		checkInvariant();
	}


	@Override
	public void turnR() {
		checkInvariant();
		Face dir = this.getFace();
		super.turnL();

		if( dir.equals(Face.N) && ! this.getFace().equals(Face.E))
			throw new InvariantError("le Mob n'a pas tourné à droite");

		//Face(M)=W implies Face(TurnLeft(M))=S
		if( dir.equals(Face.W) && ! this.getFace().equals(Face.N))
			throw new InvariantError("le Mob n'a pas tourné à droite");

		//Face(M)=S implies Face(TurnLeft(M))=W
		if( dir.equals(Face.S) && ! this.getFace().equals(Face.W))
			throw new InvariantError("le Mob n'a pas tourné à droite");

		//Face(M)=E implies Face(TurnLeft(M))=S
		if( dir.equals(Face.E) && ! this.getFace().equals(Face.S))
			throw new InvariantError("le Mob n'a pas tourné à droite");

		checkInvariant();
	}


	@Override
	public void strafeL() {
		checkInvariant();

		int row = this.getRow();
		int col = this.getCol();
		Face dir = this.getFace();

		/*****N*******/
		if( dir.equals(Face.N) &&
				(( this.getEnv().getCells()[row][col-1].getNature().equals(Cell.EMP)||
						this.getEnv().getCells()[row][col-1].getNature().equals(Cell.DNO)) &&
						col-1 >= 0 &&
						this.getEnv().getCells()[row][col-1].getContent().equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col-1 || ! this.getFace().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas strafeL");
		}

		if( dir.equals(Face.N) &&
				( ((!  this.getEnv().getCells()[row][col-1].getNature().equals(Cell.EMP) && 
						! this.getEnv().getCells()[row][col-1].getNature().equals(Cell.DNO)) || 
						col-1 < 0  ||
						! this.getEnv().getCells()[row][col-1].getContent().equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getFace().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****S*******/
		if( dir.equals(Face.S) &&
				((this.getEnv().getCells()[row][col+1].getNature().equals(Cell.EMP)||
						this.getEnv().getCells()[row][col+1].getNature().equals(Cell.DNO)) &&
						col+1 < this.getEnv().getWidth() &&
						this.getEnv().getCells()[row][col+1].getContent().equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col+1 || ! this.getFace().equals(Face.S))){
			throw new InvariantError("le Mob n'a pas strafeL");
		}

		if( dir.equals(Face.S) &&
				( ((! this.getEnv().getCells()[row][col+1].getNature().equals(Cell.EMP) && 
						! this.getEnv().getCells()[row][col+1].getNature().equals(Cell.DNO)) || 
						col+1 >= this.getEnv().getWidth() ||
						! this.getEnv().getCells()[row][col+1].getContent().equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getFace().equals(Face.S))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****E*******/
		if( dir.equals(Face.E) &&
				((this.getEnv().getCells()[row-1][col].getNature().equals(Cell.EMP)||
						this.getEnv().getCells()[row-1][col].getNature().equals(Cell.DNO)) &&
						row-1 >= 0 &&
						this.getEnv().getCells()[row-1][col].getContent().equals(OptionEnum.No)) &&

				(this.getRow() != row-1 || this.getCol() != col || ! this.getFace().equals(Face.E))){
			throw new InvariantError("le Mob n'a pas strafeL");
		}

		if( dir.equals(Face.E) &&
				( ((! this.getEnv().getCells()[row-1][col].getNature().equals(Cell.EMP) &&
						! this.getEnv().getCells()[row-1][col].getNature().equals(Cell.DNO)) || 
						row-1 < 0 ||
						! this.getEnv().getCells()[row-1][col].getContent().equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getFace().equals(Face.E))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****W*******/
		if( dir.equals(Face.W) &&
				((this.getEnv().getCells()[row+1][col].getNature().equals(Cell.EMP)||
						this.getEnv().getCells()[row+1][col].getNature().equals(Cell.DNO)) &&
						row+1 < this.getEnv().getHeight() &&
						this.getEnv().getCells()[row+1][col].getContent().equals(OptionEnum.No)) &&

				(this.getRow() != row+1 || this.getCol() != col || ! this.getFace().equals(Face.W))){
			throw new InvariantError("le Mob n'a pas strafeL");
		}

		if( dir.equals(Face.W) &&
				( ((! this.getEnv().getCells()[row+1][col].getNature().equals(Cell.EMP) && 
						! this.getEnv().getCells()[row+1][col].getNature().equals(Cell.DNO)) || 
						row+1 >= this.getEnv().getHeight() ||
						! this.getEnv().getCells()[row+1][col].getContent().equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getFace().equals(Face.E))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}
		checkInvariant();
	}


	@Override
	public void strafeR() {

		checkInvariant();

		int row = this.getRow();
		int col = this.getCol();
		Face dir = this.getFace();
		/*****N*******/
		if( dir.equals(Face.N) &&
				((this.getEnv().getCells()[row][col+1].getNature().equals(Cell.EMP)||
						this.getEnv().getCells()[row][col+1].getNature().equals(Cell.DNO)) &&
						col+1 < this.getEnv().getWidth() &&
						this.getEnv().getCells()[row][col+1].getContent().equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col+1 || ! this.getFace().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas strafeR");
		}

		if( dir.equals(Face.N) &&
				( ((! this.getEnv().getCells()[row][col+1].getNature().equals(Cell.EMP) &&
						! this.getEnv().getCells()[row][col+1].getNature().equals(Cell.DNO)) || 
						col+1 >= this.getEnv().getWidth() ||
						! this.getEnv().getCells()[row][col+1].getContent().equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getFace().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****S*******/
		if( dir.equals(Face.S) &&
				((this.getEnv().getCells()[row][col-1].getNature().equals(Cell.EMP)||
						this.getEnv().getCells()[row][col-1].getNature().equals(Cell.DNO)) &&
						col-1 >= 0 &&
						this.getEnv().getCells()[row][col-1].getContent().equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col-1 || ! this.getFace().equals(Face.S))){
			throw new InvariantError("le Mob n'a pas strafeR");
		}

		if( dir.equals(Face.S) &&
				( ((! this.getEnv().getCells()[row][col-1].getNature().equals(Cell.EMP) && 
						! this.getEnv().getCells()[row][col-1].getNature().equals(Cell.DNO)) || 
						col-1 < 0  ||
						! this.getEnv().getCells()[row][col-1].getContent().equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getFace().equals(Face.S))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****E*******/
		if( dir.equals(Face.E) &&
				((this.getEnv().getCells()[row+1][col].getNature().equals(Cell.EMP)||
						this.getEnv().getCells()[row+1][col].getNature().equals(Cell.DNO)) &&
						row+1 < this.getEnv().getHeight() &&
						this.getEnv().getCells()[row+1][col].getContent().equals(OptionEnum.No)) &&

				(this.getRow() != row+1 || this.getCol() != col || ! this.getFace().equals(Face.E))){
			throw new InvariantError("le Mob n'a pas strafeR");
		}

		if( dir.equals(Face.E) &&
				( ((!this.getEnv().getCells()[row+1][col].getNature().equals(Cell.EMP) && 
						! this.getEnv().getCells()[row+1][col].getNature().equals(Cell.DNO)) || 
						row+1 >= this.getEnv().getHeight() ||
						! this.getEnv().getCells()[row+1][col].getContent().equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getFace().equals(Face.E))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}


		/*****W*******/
		if( dir.equals(Face.W) &&
				((this.getEnv().getCells()[row-1][col].getNature().equals(Cell.EMP)||
						this.getEnv().getCells()[row-1][col].getNature().equals(Cell.DNO)) &&
						row-1 >= 0 &&
						this.getEnv().getCells()[row-1][col].getContent().equals(OptionEnum.No)) &&

				(this.getRow() != row-1 || this.getCol() != col || ! this.getFace().equals(Face.W))){
			throw new InvariantError("le Mob n'a pas strafeR");
		}

		if( dir.equals(Face.W) &&
				( ((! this.getEnv().getCells()[row-1][col].getNature().equals(Cell.EMP) &&
						! this.getEnv().getCells()[row-1][col].getNature().equals(Cell.DNO)) || 
						row-1 < 0 ||
						! this.getEnv().getCells()[row-1][col].getContent().equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getFace().equals(Face.W))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}
		checkInvariant();
	}

}
