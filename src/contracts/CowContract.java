package contracts;

import servives.CowService;
import servives.EnvironnementService;
import tools.Cell;
import tools.Face;
import tools.InvariantError;
import tools.OptionEnum;
import tools.PreconditionError;
import decorators.CowDecorator;

public class CowContract extends CowDecorator{

	public CowContract(CowService delegate) {
		super(delegate);
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
	public void init(int row, int col, int hp , Face face , EnvironnementService env) {
		checkInvariant();
		if( this.getHp() <= 0 ) {
			throw new PreconditionError("hp est négatif");
		}


		if( this.getCol() < 0 || this.getCol() > this.getEnv().getWidth() || this.getRow() < 0 || this.getRow() > this.getEnv().getHeight() ) {
			throw new PreconditionError("le Mob ne peut être initialisé ! ses coordonnées dépassent les dimentions de l'env");
		}

		if( this.getHp() != 4 || this.getHp() != 3 ) {
			throw new PreconditionError("une vache ne peut pas avoir plus de 4 ou moins de 3 points de vie");
		}


		super.init(row, col, hp, face, env);


//		if( this.getCol() != col || this.getRow() != row || this.getFace() != face || this.getEnv() != env ) {
//			throw new InvariantError("le Mob ne peut être initialisé ! ses coordonnées dépassent les dimentions de l'env");
//		}
//
//		if( this.getHp() != h )
//			throw new InvariantError("Hp n'est pas coherant !");

		checkInvariant();
	}

	@Override
	public void step () {
		checkInvariant();
		int col = this.getCol();
		int row = this.getRow();
		super.step();
		//Col(M) - 1 ≤ Col(step(M)) ≤ Col(M) + 1
		if( this.getCol() < col -1 || this.getCol() > col + 1) {
			throw new InvariantError("y a un pblm dans step");
		}
		//Row(M) - 1 ≤ Row(step(M)) ≤ Row(M) + 1
		if( this.getRow() < row - 1 || this.getRow() > row + 1) {
			throw new InvariantError("y a un pblm dans step");
		}
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
				((this.getEnv().getCellNature(row+1 , col).equals(Cell.EMP)||this.getEnv().getCellNature(row+1 , col).equals(Cell.DNO)) &&
						row+1 <this.getEnv().getHeight() &&
						this.getEnv().getCellContent(row+1, col).equals(OptionEnum.No)) &&

				(this.getRow() != row+1 || this.getCol() != col || ! this.getFace().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas avancé au Nord");
		}

		if( dir.equals(Face.N) &&
				( ((! this.getEnv().getCellNature(row+1 , col).equals(Cell.EMP) && ! this.getEnv().getCellNature(row+1 , col).equals(Cell.DNO)) || 
						row+1 >= this.getEnv().getHeight() ||
						! this.getEnv().getCellContent(row+1, col).equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getFace().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****E*******/

		if( dir.equals(Face.E) &&
				((this.getEnv().getCellNature(row , col+1).equals(Cell.EMP)||this.getEnv().getCellNature(row , col+1).equals(Cell.DNO)) &&
						col+1 <this.getEnv().getWidth() &&
						this.getEnv().getCellContent(row, col+1).equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col+1 || ! this.getFace().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas avancé à l'est");
		}

		if( dir.equals(Face.E) &&
				( (! this.getEnv().getCellNature(row , col+1).equals(Cell.EMP) && ! this.getEnv().getCellNature(row , col+1).equals(Cell.DNO)) || 
						col+1 >= this.getEnv().getHeight() ||
						! this.getEnv().getCellContent(row, col+1).equals(OptionEnum.No) &&

						(this.getRow() != row || this.getCol() != col || ! this.getFace().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****S*******/

		if( dir.equals(Face.S) &&
				((this.getEnv().getCellNature(row-1 , col).equals(Cell.EMP)||this.getEnv().getCellNature(row-1 , col).equals(Cell.DNO)) &&
						row-1 >= 0 &&
						this.getEnv().getCellContent(row-1 , col).equals(OptionEnum.No)) &&

				(this.getRow() != row-1 || this.getCol() != col || ! this.getFace().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas avancé au Sud");
		}

		if( dir.equals(Face.S) &&
				( (! this.getEnv().getCellNature(row-1 , col).equals(Cell.EMP) && ! this.getEnv().getCellNature(row-1 , col).equals(Cell.DNO)) || 
						row-1 < 0 ||
						! this.getEnv().getCellContent(row-1, col).equals(OptionEnum.No) &&

						(this.getRow() != row || this.getCol() != col || ! this.getFace().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****W*******/

		if(dir.equals(Face.W) &&
				((this.getEnv().getCellNature(row , col-1).equals(Cell.EMP)||this.getEnv().getCellNature(row , col-1).equals(Cell.DNO)) &&
						col-1 >= 0 &&
						this.getEnv().getCellContent(row , col-1).equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col-1 || ! this.getFace().equals(Face.N)) ){
			throw new InvariantError("le Mob n'a pas avancé au W");
		}

		if( dir.equals(Face.W) &&
				( (! this.getEnv().getCellNature(row , col-1).equals(Cell.EMP) && ! this.getEnv().getCellNature(row , col-1).equals(Cell.DNO)) || 
						row-1 < 0 ||
						! this.getEnv().getCellContent(row-1, col).equals(OptionEnum.No)) &&

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
				((this.getEnv().getCellNature(row-1 , col).equals(Cell.EMP)||this.getEnv().getCellNature(row-1 , col).equals(Cell.DNO)) &&
						row-1 >=0 &&
						this.getEnv().getCellContent(row-1, col).equals(OptionEnum.No)) &&

				(this.getRow() != row-1 || this.getCol() != col || ! this.getFace().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas reculer ");
		}

		if( dir.equals(Face.N) &&
				( ((! this.getEnv().getCellNature(row-1 , col).equals(Cell.EMP) && ! this.getEnv().getCellNature(row-1 , col).equals(Cell.DNO)) || 
						row-1 < 0 ||
						! this.getEnv().getCellContent(row-1, col).equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getFace().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****S*******/
		if( dir.equals(Face.S) &&
				((this.getEnv().getCellNature(row+1 , col).equals(Cell.EMP)||this.getEnv().getCellNature(row+1 , col).equals(Cell.DNO)) &&
						row+1 <this.getEnv().getHeight() &&
						this.getEnv().getCellContent(row+1, col).equals(OptionEnum.No)) &&

				(this.getRow() != row+1 || this.getCol() != col || ! this.getFace().equals(Face.S))){
			throw new InvariantError("le Mob n'a pas reculé");
		}

		if( dir.equals(Face.S) &&
				((! this.getEnv().getCellNature(row+1 , col).equals(Cell.EMP) && ! this.getEnv().getCellNature(row+1 , col).equals(Cell.DNO)) || 
						row+1 >= this.getEnv().getHeight() ||
						! this.getEnv().getCellContent(row+1, col).equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col|| ! this.getFace().equals(Face.S)) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****E*******/
		if( dir.equals(Face.E) &&
				((this.getEnv().getCellNature(row , col+1).equals(Cell.EMP)||this.getEnv().getCellNature(row , col+1).equals(Cell.DNO)) &&
						col+1 < this.getEnv().getWidth() &&
						this.getEnv().getCellContent(row, col+1).equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col+1 || ! this.getFace().equals(Face.E))){
			throw new InvariantError("le Mob n'a pas reculé");
		}

		if( dir.equals(Face.E) &&
				((! this.getEnv().getCellNature(row , col+1).equals(Cell.EMP) && ! this.getEnv().getCellNature(row , col+1).equals(Cell.DNO)) || 
						col+1 >= this.getEnv().getWidth() ||
						! this.getEnv().getCellContent(row, col+1).equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col || ! this.getFace().equals(Face.E)) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****W*******/

		if( dir.equals(Face.W) &&
				((this.getEnv().getCellNature(row , col-1).equals(Cell.EMP)||this.getEnv().getCellNature(row , col-1).equals(Cell.DNO)) &&
						col-1 >=0 &&
						this.getEnv().getCellContent(row, col-1).equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col-1 || ! this.getFace().equals(Face.W))){
			throw new InvariantError("le Mob n'a pas reculé");
		}

		if( dir.equals(Face.W) &&
				((! this.getEnv().getCellNature(row , col-1).equals(Cell.EMP) && ! this.getEnv().getCellNature(row , col-1).equals(Cell.DNO)) || 
						col-1 < 0 ||
						! this.getEnv().getCellContent(row, col-1).equals(OptionEnum.No)) &&

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
				((this.getEnv().getCellNature(row , col-1).equals(Cell.EMP)||this.getEnv().getCellNature(row , col-1).equals(Cell.DNO)) &&
						col-1 >= 0 &&
						this.getEnv().getCellContent(row, col-1).equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col-1 || ! this.getFace().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas strafeL");
		}

		if( dir.equals(Face.N) &&
				( ((! this.getEnv().getCellNature(row , col-1).equals(Cell.EMP) && ! this.getEnv().getCellNature(row , col-1).equals(Cell.DNO)) || 
						col-1 < 0  ||
						! this.getEnv().getCellContent(row, col-1).equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getFace().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****S*******/
		if( dir.equals(Face.S) &&
				((this.getEnv().getCellNature(row , col+1).equals(Cell.EMP)||this.getEnv().getCellNature(row , col+1).equals(Cell.DNO)) &&
						col+1 < this.getEnv().getWidth() &&
						this.getEnv().getCellContent(row, col+1).equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col+1 || ! this.getFace().equals(Face.S))){
			throw new InvariantError("le Mob n'a pas strafeL");
		}

		if( dir.equals(Face.S) &&
				( ((! this.getEnv().getCellNature(row , col+1).equals(Cell.EMP) && ! this.getEnv().getCellNature(row , col+1).equals(Cell.DNO)) || 
						col+1 >= this.getEnv().getWidth() ||
						! this.getEnv().getCellContent(row, col+1).equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getFace().equals(Face.S))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****E*******/
		if( dir.equals(Face.E) &&
				((this.getEnv().getCellNature(row-1 , col).equals(Cell.EMP)||this.getEnv().getCellNature(row-1 , col).equals(Cell.DNO)) &&
						row-1 >= 0 &&
						this.getEnv().getCellContent(row-1, col).equals(OptionEnum.No)) &&

				(this.getRow() != row-1 || this.getCol() != col || ! this.getFace().equals(Face.E))){
			throw new InvariantError("le Mob n'a pas strafeL");
		}

		if( dir.equals(Face.E) &&
				( ((! this.getEnv().getCellNature(row-1 , col).equals(Cell.EMP) && ! this.getEnv().getCellNature(row-1 , col).equals(Cell.DNO)) || 
						row-1 < 0 ||
						! this.getEnv().getCellContent(row-1, col).equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getFace().equals(Face.E))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****W*******/
		if( dir.equals(Face.W) &&
				((this.getEnv().getCellNature(row+1 , col).equals(Cell.EMP)||this.getEnv().getCellNature(row+1 , col).equals(Cell.DNO)) &&
						row+1 < this.getEnv().getHeight() &&
						this.getEnv().getCellContent(row+1, col).equals(OptionEnum.No)) &&

				(this.getRow() != row+1 || this.getCol() != col || ! this.getFace().equals(Face.W))){
			throw new InvariantError("le Mob n'a pas strafeL");
		}

		if( dir.equals(Face.W) &&
				( ((! this.getEnv().getCellNature(row+1 , col).equals(Cell.EMP) && ! this.getEnv().getCellNature(row+1 , col).equals(Cell.DNO)) || 
						row+1 >= this.getEnv().getHeight() ||
						! this.getEnv().getCellContent(row+1, col).equals(OptionEnum.No)) &&

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
				((this.getEnv().getCellNature(row , col+1).equals(Cell.EMP)||this.getEnv().getCellNature(row , col+1).equals(Cell.DNO)) &&
						col+1 < this.getEnv().getWidth() &&
						this.getEnv().getCellContent(row, col+1).equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col+1 || ! this.getFace().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas strafeR");
		}

		if( dir.equals(Face.N) &&
				( ((! this.getEnv().getCellNature(row , col+1).equals(Cell.EMP) && ! this.getEnv().getCellNature(row , col+1).equals(Cell.DNO)) || 
						col+1 >= this.getEnv().getWidth() ||
						! this.getEnv().getCellContent(row, col+1).equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getFace().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****S*******/
		if( dir.equals(Face.S) &&
				((this.getEnv().getCellNature(row , col-1).equals(Cell.EMP)||this.getEnv().getCellNature(row , col-1).equals(Cell.DNO)) &&
						col-1 >= 0 &&
						this.getEnv().getCellContent(row, col-1).equals(OptionEnum.No)) &&

				(this.getRow() != row || this.getCol() != col-1 || ! this.getFace().equals(Face.S))){
			throw new InvariantError("le Mob n'a pas strafeR");
		}

		if( dir.equals(Face.S) &&
				( ((! this.getEnv().getCellNature(row , col-1).equals(Cell.EMP) && ! this.getEnv().getCellNature(row , col-1).equals(Cell.DNO)) || 
						col-1 < 0  ||
						! this.getEnv().getCellContent(row, col-1).equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getFace().equals(Face.S))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****E*******/
		if( dir.equals(Face.E) &&
				((this.getEnv().getCellNature(row+1 , col).equals(Cell.EMP)||this.getEnv().getCellNature(row+1 , col).equals(Cell.DNO)) &&
						row+1 < this.getEnv().getHeight() &&
						this.getEnv().getCellContent(row+1, col).equals(OptionEnum.No)) &&

				(this.getRow() != row+1 || this.getCol() != col || ! this.getFace().equals(Face.E))){
			throw new InvariantError("le Mob n'a pas strafeR");
		}

		if( dir.equals(Face.E) &&
				( ((! this.getEnv().getCellNature(row+1 , col).equals(Cell.EMP) && ! this.getEnv().getCellNature(row+1 , col).equals(Cell.DNO)) || 
						row+1 >= this.getEnv().getHeight() ||
						! this.getEnv().getCellContent(row+1, col).equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getFace().equals(Face.E))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}


		/*****W*******/
		if( dir.equals(Face.W) &&
				((this.getEnv().getCellNature(row-1 , col).equals(Cell.EMP)||this.getEnv().getCellNature(row-1 , col).equals(Cell.DNO)) &&
						row-1 >= 0 &&
						this.getEnv().getCellContent(row-1, col).equals(OptionEnum.No)) &&

				(this.getRow() != row-1 || this.getCol() != col || ! this.getFace().equals(Face.W))){
			throw new InvariantError("le Mob n'a pas strafeR");
		}

		if( dir.equals(Face.W) &&
				( ((! this.getEnv().getCellNature(row-1 , col).equals(Cell.EMP) && ! this.getEnv().getCellNature(row-1 , col).equals(Cell.DNO)) || 
						row-1 < 0 ||
						! this.getEnv().getCellContent(row-1, col).equals(OptionEnum.No)) &&

						(this.getRow() != row || this.getCol() != col || ! this.getFace().equals(Face.W))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}
		checkInvariant();
	}
}
