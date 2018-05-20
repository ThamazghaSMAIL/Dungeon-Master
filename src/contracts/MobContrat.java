package contracts;

import decorators.MobDecorator;
import servives.EnvironnementService;
import servives.MobService;
import tools.Cell;
import tools.Face;
import tools.InvariantError;
import tools.OptionEnum;
import tools.PreconditionError;

public class MobContrat extends MobDecorator implements MobService{
	MobService serv;
	public MobContrat(MobService serv) {
		super(serv);
		this.serv = serv;
	}


	public void checkInvariant(){

		//0 ≤ Col(M) < Environment::Width(Envi(M))
		//0 ≤ Row(M) < Environment::Height(Envi(M))
		if( serv.getCol() < 0 || serv.getRow() < 0 || serv.getCol() > serv.getEnv().getWidth() || serv.getRow() > serv.getEnv().getHeight() ) {
			throw new InvariantError("le Mob ne peut être initialisé ! ses coordonnées dépassent les dimentions de l'env");
		}

		//Environment::CellNature(Envi(M),Col(M),Row(M)) ∈ {WLL, DNC, DWC}

		if( serv.getEnv().getCells()[serv.getRow()][serv.getCol()].getNature().equals(Cell.WLL) || 
				serv.getEnv().getCells()[serv.getRow()][serv.getCol()].getNature().equals(Cell.DNC) ||
				serv.getEnv().getCells()[serv.getRow()][serv.getCol()].getNature().equals(Cell.DWC)) {
			throw new InvariantError("le Mob ne peut être sur un WLL/DNC/DWC");
		}

	}

	@Override
	public void init(int row, int col, Face face , EnvironnementService env ) {

		checkInvariant();
		if( serv.getCol() < 0 || serv.getCol() > serv.getEnv().getWidth() || serv.getRow() < 0 || serv.getRow() > serv.getEnv().getHeight() ) {
			throw new PreconditionError("le Mob ne peut être initialisé ! ses coordonnées dépassent les dimentions de l'env");
		}
		super.init(row, col, face, env);

		if( serv.getCol() != col || serv.getRow() != row || serv.getFace() != face || serv.getEnv() != env ) {
			throw new InvariantError("le Mob ne peut être initialisé ! ses coordonnées dépassent les dimentions de l'env");
		}
	}





	@Override
	public void turnL() {
		checkInvariant();
		//Face(M)=N implies Face(TurnLeft(M))=W
		Face dir = serv.getFace();
		super.turnL();

		if( dir.equals(Face.N) && ! serv.getFace().equals(Face.W))
			throw new InvariantError("le Mob n'a pas tourné à gauche");

		//Face(M)=W implies Face(TurnLeft(M))=S
		if( dir.equals(Face.W) && ! serv.getFace().equals(Face.S))
			throw new InvariantError("le Mob n'a pas tourné à gauche");

		//Face(M)=S implies Face(TurnLeft(M))=E
		if( dir.equals(Face.S) && ! serv.getFace().equals(Face.E))
			throw new InvariantError("le Mob n'a pas tourné à gauche");

		//Face(M)=E implies Face(TurnLeft(M))=N
		if( dir.equals(Face.E) && ! serv.getFace().equals(Face.N))
			throw new InvariantError("le Mob n'a pas tourné à gauche");
		checkInvariant();
	}


	@Override
	public void turnR() {
		checkInvariant();
		Face dir = serv.getFace();
		super.turnL();

		if( dir.equals(Face.N) && ! serv.getFace().equals(Face.E))
			throw new InvariantError("le Mob n'a pas tourné à droite");

		//Face(M)=W implies Face(TurnLeft(M))=S
		if( dir.equals(Face.W) && ! serv.getFace().equals(Face.N))
			throw new InvariantError("le Mob n'a pas tourné à droite");

		//Face(M)=S implies Face(TurnLeft(M))=W
		if( dir.equals(Face.S) && ! serv.getFace().equals(Face.W))
			throw new InvariantError("le Mob n'a pas tourné à droite");

		//Face(M)=E implies Face(TurnLeft(M))=S
		if( dir.equals(Face.E) && ! serv.getFace().equals(Face.S))
			throw new InvariantError("le Mob n'a pas tourné à droite");

		checkInvariant();
	}
	@Override
	public void forward() {
		checkInvariant();

		int row = serv.getRow();
		int col = serv.getCol();
		Face dir = serv.getFace();
		serv.forward();

		/*****N*******/
		if( dir.equals(Face.N) &&
				(serv.getEnv().getCells()[row+1][col].getNature().equals(Cell.EMP)||
						serv.getEnv().getCells()[row+1][col].getNature().equals(Cell.DNO)) &&
				row+1 <serv.getEnv().getHeight() &&
				serv.getEnv().getCells()[row+1][col].getNature().equals(OptionEnum.No) &&
				(serv.getRow() != row+1 || serv.getCol() != col || ! serv.getFace().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas avancé au Nord");
		}

		if( dir.equals(Face.N) &&
				(! serv.getEnv().getCells()[row+1][col].getNature().equals(Cell.EMP) &&
						! serv.getEnv().getCells()[row+1][col].getNature().equals(Cell.DNO) || 
						row+1 >= serv.getEnv().getHeight() ||
						! serv.getEnv().getCells()[row+1][col].getContent().equals(OptionEnum.No)) &&
				(serv.getRow() != row || serv.getCol() != col || ! serv.getFace().equals(Face.N))){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****E*******/

		if( dir.equals(Face.E) &&
				((serv.getEnv().getCells()[row][col+1].getNature().equals(Cell.EMP)||
						serv.getEnv().getCells()[row][col+1].getNature().equals(Cell.DNO)) &&
						col+1 <serv.getEnv().getWidth() &&
						serv.getEnv().getCells()[row][col+1].getContent().equals(OptionEnum.No)) &&

				(serv.getRow() != row || serv.getCol() != col+1 || ! serv.getFace().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas avancé à l'est");
		}

		if( dir.equals(Face.E) &&
				( (! serv.getEnv().getCells()[row][col+1].getNature().equals(Cell.EMP) && 
						! serv.getEnv().getCells()[row][col+1].getNature().equals(Cell.DNO)) || 
						col+1 >= serv.getEnv().getHeight() ||
						! serv.getEnv().getCells()[row][col+1].getContent().equals(OptionEnum.No) &&

						(serv.getRow() != row || serv.getCol() != col || ! serv.getFace().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****S*******/

		if( dir.equals(Face.S) &&
				((serv.getEnv().getCells()[row-1][col].getNature().equals(Cell.EMP) ||
						serv.getEnv().getCells()[row-1][col].getNature().equals(Cell.DNO)) &&
						row-1 >= 0 &&
						serv.getEnv().getCells()[row-1][col].equals(OptionEnum.No)) &&

				(serv.getRow() != row-1 || serv.getCol() != col || ! serv.getFace().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas avancé au Sud");
		}

		if( dir.equals(Face.S) &&
				( (! serv.getEnv().getCells()[row-1][col].getNature().equals(Cell.EMP) && 
						! serv.getEnv().getCells()[row-1][col].getNature().equals(Cell.DNO)) || 
						row-1 < 0 ||
						! serv.getEnv().getCells()[row-1][col].getContent().equals(OptionEnum.No) &&

						(serv.getRow() != row || serv.getCol() != col || ! serv.getFace().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****W*******/

		if(dir.equals(Face.W) &&
				((serv.getEnv().getCells()[row ][col-1].getNature().equals(Cell.EMP)||
						serv.getEnv().getCells()[row ][col-1].getNature().equals(Cell.DNO)) &&
						col-1 >= 0 &&
						serv.getEnv().getCells()[row ][col-1].getContent().equals(OptionEnum.No)) &&

				(serv.getRow() != row || serv.getCol() != col-1 || ! serv.getFace().equals(Face.N)) ){
			throw new InvariantError("le Mob n'a pas avancé au W");
		}

		if( dir.equals(Face.W) &&
				( (! serv.getEnv().getCells()[row][col-1].getNature().equals(Cell.EMP) && 
						! serv.getEnv().getCells()[row][col-1].getNature().equals(Cell.DNO)) || 
						row-1 < 0 ||
						! serv.getEnv().getCells()[row][col-1].getContent().equals(OptionEnum.No)) &&

				(serv.getRow() != row || serv.getCol() != col || ! serv.getFace().equals(Face.N)) ) {
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}
		checkInvariant();
	}
	@Override
	public void backward() {
		checkInvariant();

		int row = serv.getRow();
		int col = serv.getCol();
		Face dir = serv.getFace();
		super.backward();

		/*****N*******/
		if( dir.equals(Face.N) &&
				((serv.getEnv().getCells()[row-1][col].getNature().equals(Cell.EMP) ||
						serv.getEnv().getCells()[row-1][col].getNature().equals(Cell.DNO)) &&
						row-1 >=0 &&
						serv.getEnv().getCells()[row-1][col].getContent().equals(OptionEnum.No)) &&

				(serv.getRow() != row-1 || serv.getCol() != col || ! serv.getFace().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas reculer ");
		}

		if( dir.equals(Face.N) &&
				( ((! serv.getEnv().getCells()[row-1][col].getNature().equals(Cell.EMP) && 
						! serv.getEnv().getCells()[row-1][col].getNature().equals(Cell.DNO)) || 
						row-1 < 0 ||
						! serv.getEnv().getCells()[row-1][col].getContent().equals(OptionEnum.No)) &&

						(serv.getRow() != row || serv.getCol() != col || ! serv.getFace().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****S*******/
		if( dir.equals(Face.S) &&
				((serv.getEnv().getCells()[row+1][col].getNature().equals(Cell.EMP) ||
						serv.getEnv().getCells()[row+1][col].getNature().equals(Cell.DNO)) &&
						row+1 <serv.getEnv().getHeight() &&
						serv.getEnv().getCells()[row+1][col].getContent().equals(OptionEnum.No)) &&

				(serv.getRow() != row+1 || serv.getCol() != col || ! serv.getFace().equals(Face.S))){
			throw new InvariantError("le Mob n'a pas reculé");
		}

		if( dir.equals(Face.S) &&
				((! serv.getEnv().getCells()[row+1][col].getNature().equals(Cell.EMP) &&
						! serv.getEnv().getCells()[row+1][col].getNature().equals(Cell.DNO)) || 
						row+1 >= serv.getEnv().getHeight() ||
						! serv.getEnv().getCells()[row+1][col].getContent().equals(OptionEnum.No)) &&

				(serv.getRow() != row || serv.getCol() != col|| ! serv.getFace().equals(Face.S)) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****E*******/
		if( dir.equals(Face.E) &&
				((serv.getEnv().getCells()[row][col+1].getNature().equals(Cell.EMP)||
						serv.getEnv().getCells()[row][col+1].getNature().equals(Cell.DNO)) &&
						col+1 < serv.getEnv().getWidth() &&
						serv.getEnv().getCells()[row][col+1].getContent().equals(OptionEnum.No)) &&

				(serv.getRow() != row || serv.getCol() != col+1 || ! serv.getFace().equals(Face.E))){
			throw new InvariantError("le Mob n'a pas reculé");
		}

		if( dir.equals(Face.E) &&
				((! serv.getEnv().getCells()[row][col+1].getNature().equals(Cell.EMP) &&
						! serv.getEnv().getCells()[row][col+1].getNature().equals(Cell.DNO)) || 
						col+1 >= serv.getEnv().getWidth() ||
						! serv.getEnv().getCells()[row][col+1].getContent().equals(OptionEnum.No)) &&

				(serv.getRow() != row || serv.getCol() != col || ! serv.getFace().equals(Face.E)) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****W*******/

		if( dir.equals(Face.W) &&
				((serv.getEnv().getCells()[row][col-1].getNature().equals(Cell.EMP)||
						serv.getEnv().getCells()[row][col-1].getNature().equals(Cell.DNO)) &&
						col-1 >=0 &&
						serv.getEnv().getCells()[row][col-1].getContent().equals(OptionEnum.No)) &&

				(serv.getRow() != row || serv.getCol() != col-1 || ! serv.getFace().equals(Face.W))){
			throw new InvariantError("le Mob n'a pas reculé");
		}

		if( dir.equals(Face.W) &&
				((! serv.getEnv().getCells()[row][col-1].getNature().equals(Cell.EMP) &&
						! serv.getEnv().getCells()[row][col-1].getNature().equals(Cell.DNO)) || 
						col-1 < 0 ||
						! serv.getEnv().getCells()[row][col-1].getContent().equals(OptionEnum.No)) &&

				(serv.getRow() != row || serv.getCol() != col || ! serv.getFace().equals(Face.W)) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}
		checkInvariant();
	}
	@Override
	public void strafeL() {
		checkInvariant();

		int row = serv.getRow();
		int col = serv.getCol();
		Face dir = serv.getFace();

		/*****N*******/
		if( dir.equals(Face.N) &&
				((serv.getEnv().getCells()[row][col-1].getNature().equals(Cell.EMP) ||
						serv.getEnv().getCells()[row][col-1].getNature().equals(Cell.DNO)) &&
						col-1 >= 0 &&
						serv.getEnv().getCells()[row][col-1].getContent().equals(OptionEnum.No)) &&

				(serv.getRow() != row || serv.getCol() != col-1 || ! serv.getFace().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas strafeL");
		}

		if( dir.equals(Face.N) &&
				( (! serv.getEnv().getCells()[row][col-1].getNature().equals(Cell.EMP) &&
						! serv.getEnv().getCells()[row][col-1].getNature().equals(Cell.DNO) ||
						col-1 < 0  ||
						! serv.getEnv().getCells()[row][col-1].getContent().equals(OptionEnum.No)) &&

						(serv.getRow() != row || serv.getCol() != col || ! serv.getFace().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****S****ok***/
		if( dir.equals(Face.S) &&
				(( serv.getEnv().getCells()[row ][col+1].getNature().equals(Cell.EMP) ||
						serv.getEnv().getCells()[row ][col+1].getNature().equals(Cell.DNO)) &&
						col+1 < serv.getEnv().getWidth() &&
						serv.getEnv().getCells()[row ][col+1].getContent().equals(OptionEnum.No)) &&

				(serv.getRow() != row || serv.getCol() != col+1 || ! serv.getFace().equals(Face.S))){
			throw new InvariantError("le Mob n'a pas strafeL");
		}

		if( dir.equals(Face.S) &&
				( (( !serv.getEnv().getCells()[row][col+1].getNature().equals(Cell.EMP) &&
						! serv.getEnv().getCells()[row][col+1].getNature().equals(Cell.DNO)) || 
						col+1 >= serv.getEnv().getWidth() ||
						! serv.getEnv().getCells()[row][col+1].getContent().equals(OptionEnum.No)) &&

						(serv.getRow() != row || serv.getCol() != col || ! serv.getFace().equals(Face.S))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****E****ok***/
		if( dir.equals(Face.E) &&
				(( serv.getEnv().getCells()[row-1][col].getNature().equals(Cell.EMP) ||
						serv.getEnv().getCells()[row-1][col].getNature().equals(Cell.DNO)) &&
						row-1 >= 0 &&
						serv.getEnv().getCells()[row-1][col].getContent().equals(OptionEnum.No)) &&

				(serv.getRow() != row-1 || serv.getCol() != col || ! serv.getFace().equals(Face.E))){
			throw new InvariantError("le Mob n'a pas strafeL");
		}

		if( dir.equals(Face.E) &&
				( (! serv.getEnv().getCells()[row-1][col].getNature().equals(Cell.EMP) &&
						! serv.getEnv().getCells()[row-1][col].getNature().equals(Cell.DNO)) ||
						row-1 < 0 ||
						! serv.getEnv().getCells()[row-1][col].getContent().equals(OptionEnum.No) &&

						serv.getRow() != row || serv.getCol() != col || ! serv.getFace().equals(Face.E)) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****W*******/
		if( dir.equals(Face.W) &&
				((serv.getEnv().getCells()[row+1][col].getNature().equals(Cell.EMP)||
						serv.getEnv().getCells()[row+1][col].getNature().equals(Cell.DNO)) &&
						row+1 < serv.getEnv().getHeight() &&
						serv.getEnv().getCells()[row+1][col].getContent().equals(OptionEnum.No)) &&

				(serv.getRow() != row+1 || serv.getCol() != col || ! serv.getFace().equals(Face.W))){
			throw new InvariantError("le Mob n'a pas strafeL");
		}

		if( dir.equals(Face.W) &&
				( ((! serv.getEnv().getCells()[row+1][col].getNature().equals(Cell.EMP) &&
						! serv.getEnv().getCells()[row+1][col].getNature().equals(Cell.DNO)) || 
						row+1 >= serv.getEnv().getHeight() ||
						! serv.getEnv().getCells()[row+1][col].getContent().equals(OptionEnum.No)) &&

						(serv.getRow() != row || serv.getCol() != col || ! serv.getFace().equals(Face.E))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}
		checkInvariant();
	}


	@Override
	public void strafeR() {

		checkInvariant();

		int row = serv.getRow();
		int col = serv.getCol();
		Face dir = serv.getFace();
		/*****N*******/
		if( dir.equals(Face.N) &&
				((serv.getEnv().getCells()[row][col+1].getNature().equals(Cell.EMP) ||
						serv.getEnv().getCells()[row][col+1].getNature().equals(Cell.DNO)) &&
						col+1 < serv.getEnv().getWidth() &&
						serv.getEnv().getCells()[row][col+1].getContent().equals(OptionEnum.No)) &&

				(serv.getRow() != row || serv.getCol() != col+1 || ! serv.getFace().equals(Face.N))){
			throw new InvariantError("le Mob n'a pas strafeR");
		}

		if( dir.equals(Face.N) &&
				( ((!serv.getEnv().getCells()[row][col+1].getNature().equals(Cell.EMP) &&
						! serv.getEnv().getCells()[row][col+1].getNature().equals(Cell.DNO)) || 
						col+1 >= serv.getEnv().getWidth() ||
						! serv.getEnv().getCells()[row][col+1].getContent().equals(OptionEnum.No)) &&

						(serv.getRow() != row || serv.getCol() != col || ! serv.getFace().equals(Face.N))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****S*******/
		if( dir.equals(Face.S) &&
				((serv.getEnv().getCells()[row][col-1].getNature().equals(Cell.EMP) ||
						serv.getEnv().getCells()[row][col-1].getNature().equals(Cell.DNO)) &&
						col-1 >= 0 &&
						serv.getEnv().getCells()[row][col-1].getContent().equals(OptionEnum.No)) &&

				(serv.getRow() != row || serv.getCol() != col-1 || ! serv.getFace().equals(Face.S))){
			throw new InvariantError("le Mob n'a pas strafeR");
		}

		if( dir.equals(Face.S) &&
				( ((! serv.getEnv().getCells()[row][col-1].getNature().equals(Cell.EMP) &&
						! serv.getEnv().getCells()[row][col-1].getNature().equals(Cell.DNO)) || 
						col-1 < 0  ||
						! serv.getEnv().getCells()[row][col-1].getContent().equals(OptionEnum.No)) &&

						(serv.getRow() != row || serv.getCol() != col || ! serv.getFace().equals(Face.S))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}

		/*****E*******/
		if( dir.equals(Face.E) &&
				((serv.getEnv().getCells()[row+1][col].getNature().equals(Cell.EMP)||
						serv.getEnv().getCells()[row+1][col].getNature().equals(Cell.DNO)) &&
						row+1 < serv.getEnv().getHeight() &&
						serv.getEnv().getCells()[row+1][col].getContent().equals(OptionEnum.No)) &&

				(serv.getRow() != row+1 || serv.getCol() != col || ! serv.getFace().equals(Face.E))){
			throw new InvariantError("le Mob n'a pas strafeR");
		}

		if( dir.equals(Face.E) &&
				( ! serv.getEnv().getCells()[row+1][col].getNature().equals(Cell.EMP) &&
						! serv.getEnv().getCells()[row+1][col].getNature().equals(Cell.DNO)) || 
				row+1 >= serv.getEnv().getHeight() ||
				! serv.getEnv().getCells()[row+1][col].getContent().equals(OptionEnum.No) &&

				(serv.getRow() != row || serv.getCol() != col || ! serv.getFace().equals(Face.E))){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}


		/*****W*******/
		if( dir.equals(Face.W) &&
				((serv.getEnv().getCells()[row-1][col].getNature().equals(Cell.EMP)||
						serv.getEnv().getCells()[row-1][col].getNature().equals(Cell.DNO)) &&
						row-1 >= 0 &&
						serv.getEnv().getCells()[row-1][col].getContent().equals(OptionEnum.No)) &&

				(serv.getRow() != row-1 || serv.getCol() != col || ! serv.getFace().equals(Face.W))){
			throw new InvariantError("le Mob n'a pas strafeR");
		}

		if( dir.equals(Face.W) &&
				( ((! serv.getEnv().getCells()[row-1][col].getNature().equals(Cell.EMP) &&
						! serv.getEnv().getCells()[row-1][col].getNature().equals(Cell.DNO)) || 
						row-1 < 0 ||
						! serv.getEnv().getCells()[row-1][col].getContent().equals(OptionEnum.No)) &&

						(serv.getRow() != row || serv.getCol() != col || ! serv.getFace().equals(Face.W))) ){
			throw new InvariantError("Le Mob n'est pas en bonne position");
		}
		checkInvariant();
	}


}
