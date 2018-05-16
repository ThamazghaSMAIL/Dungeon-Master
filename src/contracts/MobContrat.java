package contracts;

import decorators.MobDecorator;
import servives.EnvironnementService;
import servives.MobService;
import tools.Cell;
import tools.Face;
import tools.InvariantError;
import tools.OptionEnum;
import tools.PreconditionError;

public class MobContrat extends MobDecorator {

	public MobContrat(MobService serv) {
		super(serv);
	}


	public void checkInvariant(){

		//0 ≤ Col(M) < Environment::Width(Envi(M))
		//0 ≤ Row(M) < Environment::Height(Envi(M))
		if( this.getCol() < 0 || this.getRow() < 0 || this.getCol() > this.getEnv().getWidth() || this.getRow() > this.getEnv().getHeight() ) {
			throw new InvariantError("le Mob ne peut être initialisé ! ses coordonnées dépassent les dimentions de l'env");
		}

		//Environment::CellNature(Envi(M),Col(M),Row(M)) ∈ {WLL, DNC, DWC}

		if( (this.getEnv().getCellNature(this.getRow(), this.getCol())).equals(Cell.WLL) || 
				(this.getEnv().getCellNature(this.getRow(), this.getCol())).equals(Cell.DNC) ||
				(this.getEnv().getCellNature(this.getRow(), this.getCol())).equals(Cell.DWC)) {
			throw new InvariantError("le Mob ne peut être sur un WLL/DNC/DWC");
		}

	}

	@Override
	public void init(int row, int col, Face face , EnvironnementService env ) {

		checkInvariant();
		if( this.getCol() < 0 || this.getCol() > this.getEnv().getWidth() || this.getRow() < 0 || this.getRow() > this.getEnv().getHeight() ) {
			throw new PreconditionError("le Mob ne peut être initialisé ! ses coordonnées dépassent les dimentions de l'env");
		}
		super.init(row, col, face, env);

		if( this.getCol() != col || this.getRow() != row || this.getDir() != face || this.getEnv() != env ) {
			throw new InvariantError("le Mob ne peut être initialisé ! ses coordonnées dépassent les dimentions de l'env");
		}
	}
	@Override
	public void forward() {
		checkInvariant();

		int row = this.getRow();
		int col = this.getCol();
		Face dir = this.getDir();
		super.forward();

		/*****N*******/
		if( dir.equals(Face.N) &&
				((this.getEnv().getCellNature(row+1 , col).equals(Cell.EMP)||this.getEnv().getCellNature(row+1 , col).equals(Cell.DNO)) &&
						row+1 <this.getEnv().getHeight() &&
						this.getEnv().getCellContent(row+1, col).equals(OptionEnum.No)) &&

				(this.getRow() != row+1 || this.getCol() != col || ! this.getDir().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas avancé au Nord");
		}

		if( dir.equals(Face.N) &&
				( ((! this.getEnv().getCellNature(row+1 , col).equals(Cell.EMP) && ! this.getEnv().getCellNature(row+1 , col).equals(Cell.DNO)) || 
						row+1 >= this.getEnv().getHeight() ||
						! this.getEnv().getCellContent(row+1, col).equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getDir().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****E*******/

		if( dir.equals(Face.E) &&
				((this.getEnv().getCellNature(row , col+1).equals(Cell.EMP)||this.getEnv().getCellNature(row , col+1).equals(Cell.DNO)) &&
						col+1 <this.getEnv().getWidth() &&
						this.getEnv().getCellContent(row, col+1).equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col+1 || ! this.getDir().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas avancé à l'est");
		}

		if( dir.equals(Face.E) &&
				( (! this.getEnv().getCellNature(row , col+1).equals(Cell.EMP) && ! this.getEnv().getCellNature(row , col+1).equals(Cell.DNO)) || 
						col+1 >= this.getEnv().getHeight() ||
						! this.getEnv().getCellContent(row, col+1).equals(OptionEnum.No) &&

						(this.getRow() != row || this.getCol() != col || ! this.getDir().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****S*******/

		if( dir.equals(Face.S) &&
				((this.getEnv().getCellNature(row-1 , col).equals(Cell.EMP)||this.getEnv().getCellNature(row-1 , col).equals(Cell.DNO)) &&
						row-1 >= 0 &&
						this.getEnv().getCellContent(row-1 , col).equals(OptionEnum.No)) &&

				(this.getRow() != row-1 || this.getCol() != col || ! this.getDir().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas avancé au Sud");
		}

		if( dir.equals(Face.S) &&
				( (! this.getEnv().getCellNature(row-1 , col).equals(Cell.EMP) && ! this.getEnv().getCellNature(row-1 , col).equals(Cell.DNO)) || 
						row-1 < 0 ||
						! this.getEnv().getCellContent(row-1, col).equals(OptionEnum.No) &&

						(this.getRow() != row || this.getCol() != col || ! this.getDir().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****W*******/

		if(dir.equals(Face.W) &&
				((this.getEnv().getCellNature(row , col-1).equals(Cell.EMP)||this.getEnv().getCellNature(row , col-1).equals(Cell.DNO)) &&
						col-1 >= 0 &&
						this.getEnv().getCellContent(row , col-1).equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col-1 || ! this.getDir().equals(Face.N)) ){
			throw new InvariantError("le Mob n'a pas avancé au W");
		}

		if( dir.equals(Face.W) &&
				( (! this.getEnv().getCellNature(row , col-1).equals(Cell.EMP) && ! this.getEnv().getCellNature(row , col-1).equals(Cell.DNO)) || 
						row-1 < 0 ||
						! this.getEnv().getCellContent(row-1, col).equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col || ! this.getDir().equals(Face.N)) ) {
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}
		checkInvariant();
	}
	@Override
	public void backward() {
		checkInvariant();

		int row = this.getRow();
		int col = this.getCol();
		Face dir = this.getDir();
		super.backward();

		/*****N*******/
		if( dir.equals(Face.N) &&
				((this.getEnv().getCellNature(row-1 , col).equals(Cell.EMP)||this.getEnv().getCellNature(row-1 , col).equals(Cell.DNO)) &&
						row-1 >=0 &&
						this.getEnv().getCellContent(row-1, col).equals(OptionEnum.No)) &&

				(this.getRow() != row-1 || this.getCol() != col || ! this.getDir().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas reculer ");
		}

		if( dir.equals(Face.N) &&
				( ((! this.getEnv().getCellNature(row-1 , col).equals(Cell.EMP) && ! this.getEnv().getCellNature(row-1 , col).equals(Cell.DNO)) || 
						row-1 < 0 ||
						! this.getEnv().getCellContent(row-1, col).equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getDir().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****S*******/
		if( dir.equals(Face.S) &&
				((this.getEnv().getCellNature(row+1 , col).equals(Cell.EMP)||this.getEnv().getCellNature(row+1 , col).equals(Cell.DNO)) &&
						row+1 <this.getEnv().getHeight() &&
						this.getEnv().getCellContent(row+1, col).equals(OptionEnum.No)) &&

				(this.getRow() != row+1 || this.getCol() != col || ! this.getDir().equals(Face.S))){
			throw new InvariantError("le Mob n'a pas reculé");
		}

		if( dir.equals(Face.S) &&
				((! this.getEnv().getCellNature(row+1 , col).equals(Cell.EMP) && ! this.getEnv().getCellNature(row+1 , col).equals(Cell.DNO)) || 
						row+1 >= this.getEnv().getHeight() ||
						! this.getEnv().getCellContent(row+1, col).equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col|| ! this.getDir().equals(Face.S)) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****E*******/
		if( dir.equals(Face.E) &&
				((this.getEnv().getCellNature(row , col+1).equals(Cell.EMP)||this.getEnv().getCellNature(row , col+1).equals(Cell.DNO)) &&
						col+1 < this.getEnv().getWidth() &&
						this.getEnv().getCellContent(row, col+1).equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col+1 || ! this.getDir().equals(Face.E))){
			throw new InvariantError("le Mob n'a pas reculé");
		}

		if( dir.equals(Face.E) &&
				((! this.getEnv().getCellNature(row , col+1).equals(Cell.EMP) && ! this.getEnv().getCellNature(row , col+1).equals(Cell.DNO)) || 
						col+1 >= this.getEnv().getWidth() ||
						! this.getEnv().getCellContent(row, col+1).equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col || ! this.getDir().equals(Face.E)) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****W*******/

		if( dir.equals(Face.W) &&
				((this.getEnv().getCellNature(row , col-1).equals(Cell.EMP)||this.getEnv().getCellNature(row , col-1).equals(Cell.DNO)) &&
						col-1 >=0 &&
						this.getEnv().getCellContent(row, col-1).equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col-1 || ! this.getDir().equals(Face.W))){
			throw new InvariantError("le Mob n'a pas reculé");
		}

		if( dir.equals(Face.W) &&
				((! this.getEnv().getCellNature(row , col-1).equals(Cell.EMP) && ! this.getEnv().getCellNature(row , col-1).equals(Cell.DNO)) || 
						col-1 < 0 ||
						! this.getEnv().getCellContent(row, col-1).equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col || ! this.getDir().equals(Face.W)) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}
		checkInvariant();
	}


	@Override
	public void turnL() {
		checkInvariant();
		//Face(M)=N implies Face(TurnLeft(M))=W
		Face dir = this.getDir();
		super.turnL();

		if( dir.equals(Face.N) && ! this.getDir().equals(Face.W))
			throw new InvariantError("le Mob n'a pas tourné à gauche");
		
		//Face(M)=W implies Face(TurnLeft(M))=S
		if( dir.equals(Face.W) && ! this.getDir().equals(Face.S))
			throw new InvariantError("le Mob n'a pas tourné à gauche");
		
		//Face(M)=S implies Face(TurnLeft(M))=E
		if( dir.equals(Face.S) && ! this.getDir().equals(Face.E))
			throw new InvariantError("le Mob n'a pas tourné à gauche");
		
		//Face(M)=E implies Face(TurnLeft(M))=N
		if( dir.equals(Face.E) && ! this.getDir().equals(Face.N))
			throw new InvariantError("le Mob n'a pas tourné à gauche");
		checkInvariant();
	}


	@Override
	public void turnR() {
		checkInvariant();
		Face dir = this.getDir();
		super.turnL();

		if( dir.equals(Face.N) && ! this.getDir().equals(Face.E))
			throw new InvariantError("le Mob n'a pas tourné à droite");
		
		//Face(M)=W implies Face(TurnLeft(M))=S
		if( dir.equals(Face.W) && ! this.getDir().equals(Face.N))
			throw new InvariantError("le Mob n'a pas tourné à droite");
		
		//Face(M)=S implies Face(TurnLeft(M))=W
		if( dir.equals(Face.S) && ! this.getDir().equals(Face.W))
			throw new InvariantError("le Mob n'a pas tourné à droite");
		
		//Face(M)=E implies Face(TurnLeft(M))=S
		if( dir.equals(Face.E) && ! this.getDir().equals(Face.S))
			throw new InvariantError("le Mob n'a pas tourné à droite");
		
		checkInvariant();
	}


	@Override
	public void strafeL() {
		checkInvariant();

		int row = this.getRow();
		int col = this.getCol();
		Face dir = this.getDir();
		
		/*****N*******/
		if( dir.equals(Face.N) &&
				((this.getEnv().getCellNature(row , col-1).equals(Cell.EMP)||this.getEnv().getCellNature(row , col-1).equals(Cell.DNO)) &&
						col-1 >= 0 &&
						this.getEnv().getCellContent(row, col-1).equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col-1 || ! this.getDir().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas strafeL");
		}

		if( dir.equals(Face.N) &&
				( ((! this.getEnv().getCellNature(row , col-1).equals(Cell.EMP) && ! this.getEnv().getCellNature(row , col-1).equals(Cell.DNO)) || 
						col-1 < 0  ||
						! this.getEnv().getCellContent(row, col-1).equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getDir().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}
		
		/*****S*******/
		if( dir.equals(Face.S) &&
				((this.getEnv().getCellNature(row , col+1).equals(Cell.EMP)||this.getEnv().getCellNature(row , col+1).equals(Cell.DNO)) &&
						col+1 < this.getEnv().getWidth() &&
						this.getEnv().getCellContent(row, col+1).equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col+1 || ! this.getDir().equals(Face.S))){
			throw new InvariantError("le Mob n'a pas strafeL");
		}

		if( dir.equals(Face.S) &&
				( ((! this.getEnv().getCellNature(row , col+1).equals(Cell.EMP) && ! this.getEnv().getCellNature(row , col+1).equals(Cell.DNO)) || 
						col+1 >= this.getEnv().getWidth() ||
						! this.getEnv().getCellContent(row, col+1).equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getDir().equals(Face.S))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}
		
		/*****E*******/
		if( dir.equals(Face.E) &&
				((this.getEnv().getCellNature(row-1 , col).equals(Cell.EMP)||this.getEnv().getCellNature(row-1 , col).equals(Cell.DNO)) &&
						row-1 >= 0 &&
						this.getEnv().getCellContent(row-1, col).equals(OptionEnum.No)) &&

				(this.getRow() != row-1 || this.getCol() != col || ! this.getDir().equals(Face.E))){
			throw new InvariantError("le Mob n'a pas strafeL");
		}

		if( dir.equals(Face.E) &&
				( ((! this.getEnv().getCellNature(row-1 , col).equals(Cell.EMP) && ! this.getEnv().getCellNature(row-1 , col).equals(Cell.DNO)) || 
						row-1 < 0 ||
						! this.getEnv().getCellContent(row-1, col).equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getDir().equals(Face.E))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}
		
		/*****W*******/
		if( dir.equals(Face.W) &&
				((this.getEnv().getCellNature(row+1 , col).equals(Cell.EMP)||this.getEnv().getCellNature(row+1 , col).equals(Cell.DNO)) &&
						row+1 < this.getEnv().getHeight() &&
						this.getEnv().getCellContent(row+1, col).equals(OptionEnum.No)) &&

				(this.getRow() != row+1 || this.getCol() != col || ! this.getDir().equals(Face.W))){
			throw new InvariantError("le Mob n'a pas strafeL");
		}

		if( dir.equals(Face.W) &&
				( ((! this.getEnv().getCellNature(row+1 , col).equals(Cell.EMP) && ! this.getEnv().getCellNature(row+1 , col).equals(Cell.DNO)) || 
						row+1 >= this.getEnv().getHeight() ||
						! this.getEnv().getCellContent(row+1, col).equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getDir().equals(Face.E))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}
		checkInvariant();
	}


	@Override
	public void strafeR() {
		
		checkInvariant();

		int row = this.getRow();
		int col = this.getCol();
		Face dir = this.getDir();
		/*****N*******/
		if( dir.equals(Face.N) &&
				((this.getEnv().getCellNature(row , col+1).equals(Cell.EMP)||this.getEnv().getCellNature(row , col+1).equals(Cell.DNO)) &&
						col+1 < this.getEnv().getWidth() &&
						this.getEnv().getCellContent(row, col+1).equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col+1 || ! this.getDir().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas strafeR");
		}

		if( dir.equals(Face.N) &&
				( ((! this.getEnv().getCellNature(row , col+1).equals(Cell.EMP) && ! this.getEnv().getCellNature(row , col+1).equals(Cell.DNO)) || 
						col+1 >= this.getEnv().getWidth() ||
						! this.getEnv().getCellContent(row, col+1).equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getDir().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}
		
		/*****S*******/
		if( dir.equals(Face.S) &&
				((this.getEnv().getCellNature(row , col-1).equals(Cell.EMP)||this.getEnv().getCellNature(row , col-1).equals(Cell.DNO)) &&
						col-1 >= 0 &&
						this.getEnv().getCellContent(row, col-1).equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col-1 || ! this.getDir().equals(Face.S))){
			throw new InvariantError("le Mob n'a pas strafeR");
		}

		if( dir.equals(Face.S) &&
				( ((! this.getEnv().getCellNature(row , col-1).equals(Cell.EMP) && ! this.getEnv().getCellNature(row , col-1).equals(Cell.DNO)) || 
						col-1 < 0  ||
						! this.getEnv().getCellContent(row, col-1).equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getDir().equals(Face.S))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}
		
		/*****E*******/
		if( dir.equals(Face.E) &&
				((this.getEnv().getCellNature(row+1 , col).equals(Cell.EMP)||this.getEnv().getCellNature(row+1 , col).equals(Cell.DNO)) &&
						row+1 < this.getEnv().getHeight() &&
						this.getEnv().getCellContent(row+1, col).equals(OptionEnum.No)) &&

				(this.getRow() != row+1 || this.getCol() != col || ! this.getDir().equals(Face.E))){
			throw new InvariantError("le Mob n'a pas strafeR");
		}

		if( dir.equals(Face.E) &&
				( ((! this.getEnv().getCellNature(row+1 , col).equals(Cell.EMP) && ! this.getEnv().getCellNature(row+1 , col).equals(Cell.DNO)) || 
						row+1 >= this.getEnv().getHeight() ||
						! this.getEnv().getCellContent(row+1, col).equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getDir().equals(Face.E))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}
		
		
		/*****W*******/
		if( dir.equals(Face.W) &&
				((this.getEnv().getCellNature(row-1 , col).equals(Cell.EMP)||this.getEnv().getCellNature(row-1 , col).equals(Cell.DNO)) &&
						row-1 >= 0 &&
						this.getEnv().getCellContent(row-1, col).equals(OptionEnum.No)) &&

				(this.getRow() != row-1 || this.getCol() != col || ! this.getDir().equals(Face.W))){
			throw new InvariantError("le Mob n'a pas strafeR");
		}

		if( dir.equals(Face.W) &&
				( ((! this.getEnv().getCellNature(row-1 , col).equals(Cell.EMP) && ! this.getEnv().getCellNature(row-1 , col).equals(Cell.DNO)) || 
						row-1 < 0 ||
						! this.getEnv().getCellContent(row-1, col).equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getDir().equals(Face.W))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}
		checkInvariant();
	}


}
