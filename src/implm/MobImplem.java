package implm;

import servives.EntityService;
import servives.EnvironnementService;
import servives.MobService;
import tools.Cell;
import tools.Face;

public class MobImplem implements MobService {
	public MobImplem() {
		
	}


	protected int row,col;
	protected Face face;
	protected EnvironnementService env ;
	protected boolean tresorFound = false;
	protected int NbClefs = 0;


	@Override
	public void init(int row, int col, Face face , EnvironnementService env) {
		this.row = row;
		this.col = col;
		this.face = face;
		this.env = env;
		
		if( col < 0 || col > this.env.getWidth() || row < 0 || row > this.env.getHeight() ) {
			System.out.println(" le Mob ne peut être initialisé ! ses coordonnées dépassent les dimentions de l'env ");
		}else {
			System.out.println("heeeeeeeeeeeeeeeeeeere : "+this.getEnv());
			this.env.getEntities().add((EntityService) this);
		}
	}

	@Override
	public void forward() {
		if( this.row == this.getEnv().getTresor().getI() && this.col == this.getEnv().getTresor().getJ() ) {
			tresorFound = true ;
			System.out.println(" Géniale ! vous avez trouvé le trésor, maintenant il faut rejoindre la sortie ");
		}


		switch (face) {
		case N:
			if( this.tresorFound && this.row-1 == 0 && this.col == 0 )
				System.out.println("gagné :) ");

			if(! this.getEnv().getCells()[this.getRow()-1][this.getCol()].getNature().equals(Cell.WLL))
				this.row--;
			break;
		case S:
			if( this.tresorFound && this.row+1 == 0 && this.col == 0 )
				System.out.println("gagné :) ");

			if(! this.getEnv().getCells()[this.getRow()-1][this.getCol()].getNature().equals(Cell.WLL))
				this.row++;
			break;
		case E:
			if( this.tresorFound && this.row == 0 && this.col+1 == 0 )
				System.out.println("gagné :) ");

			if(! this.getEnv().getCells()[this.getRow()][this.getCol()+1].getNature().equals(Cell.WLL))
				this.col++;
			break;
		case W:
			if( this.tresorFound && this.row == 0 && this.col-1 == 0 )
				System.out.println("gagné :) ");

			if(! this.getEnv().getCells()[this.getRow()][this.getCol()-1].getNature().equals(Cell.WLL))
				this.col--;
			break;
		default:
			break;
		}	
	}

	@Override
	public void backward() {


		if( this.row == this.getEnv().getTresor().getI() && this.col == this.getEnv().getTresor().getJ() ) {
			tresorFound = true ;
			System.out.println(" Géniale ! vous avez trouvé le trésor, maintenant il faut rejoindre la sortie ");
		}

		switch (face) {
		case N:
			if( this.tresorFound && this.row+1 == 0 && this.col == 0 )
				System.out.println("gagné :) ");
			if(! this.getEnv().getCells()[this.getRow()+1][this.getCol()].getNature().equals(Cell.WLL))
				this.row++;
			break;
		case S:
			if( this.tresorFound && this.row-1 == 0 && this.col == 0 )
				System.out.println("gagné :) ");
			if(! this.getEnv().getCells()[this.getRow()-1][this.getCol()].getNature().equals(Cell.WLL))
				this.row--;
			break;
		case E:
			if( this.tresorFound && this.row == 0 && this.col-1 == 0 )
				System.out.println("gagné :) ");
			if(! this.getEnv().getCells()[this.getRow()][this.getCol()-1].getNature().equals(Cell.WLL))
				this.col--;
			break;
		case W:
			if( this.tresorFound && this.row == 0 && this.col+1 == 0 )
				System.out.println("gagné :) ");
			if(! this.getEnv().getCells()[this.getRow()][this.getCol()+1].getNature().equals(Cell.WLL))
				this.col++;
			break;
		default:
			break;
		}
	}

	@Override
	public void turnL() {
		switch (face) {
		case S:
			this.face=Face.E;
			break;
		case N:
			this.face=Face.W;
			break;
		case E:
			this.face=Face.N;
			break;
		case W:
			this.face=Face.S;
			break;
		default:
			break;
		}
	}

	@Override
	public void turnR() {

		switch (face) {
		case S:
			this.face=Face.W;
			break;
		case N:
			this.face=Face.E;
			break;
		case E:
			this.face=Face.S;
			break;
		case W:
			this.face=Face.N;
			break;
		default:
			break;
		}	}

	@Override
	public void strafeL() {
		if( this.row == this.getEnv().getTresor().getI() && this.col == this.getEnv().getTresor().getJ() ) {
			tresorFound = true ;
			System.out.println(" Géniale ! vous avez trouvé le trésor, maintenant il faut rejoindre la sortie ");
		}

		switch (face) {
		case N:
			if( this.tresorFound && this.row == 0 && this.col-1 == 0 )
				System.out.println("gagné :) ");
			if( this.col-1 >=0)
				this.col--;
			break;
		case S:
			if( this.tresorFound && this.row == 0 && this.col+1 == 0 )
				System.out.println("gagné :) ");
			if( this.col+1 <= this.getEnv().getWidth())
				this.col++;
			break;
		case E:
			if( this.tresorFound && this.row-1 == 0 && this.col == 0 )
				System.out.println("gagné :) ");
			if( this.row-1 >=0 )
				this.row--;
			break;
		case W:
			if( this.tresorFound && this.row+1 == 0 && this.col == 0 )
				System.out.println("gagné :) ");
			if( this.row+1 <= this.getEnv().getHeight())
				this.row++;
			break;
		default:
			break;
		}
	}

	@Override
	public void strafeR() {
		if( this.row == this.getEnv().getTresor().getI() && this.col == this.getEnv().getTresor().getJ() ) {
			tresorFound = true ;
			System.out.println(" Géniale ! vous avez trouvé le trésor, maintenant il faut rejoindre la sortie ");
		}

		if( this.tresorFound && this.row == 0 && this.col == 0 )
			System.out.println("gagné :) ");
		switch (face) {
		case N:
			if( this.tresorFound && this.row == 0 && this.col+1 == 0 )
				System.out.println("gagné :) ");
			this.col++;
			break;
		case S:
			if( this.tresorFound && this.row == 0 && this.col-1 == 0 )
				System.out.println("gagné :) ");
			this.col--;
			break;
		case E:
			if( this.tresorFound && this.row+1 == 0 && this.col == 0 )
				System.out.println("gagné :) ");
			this.row++;
			break;
		case W:
			if( this.tresorFound && this.row-1 == 0 && this.col == 0 )
				System.out.println("gagné :) ");
			this.row--;
			break;
		default:
			break;
		}
	}



	@Override
	public EnvironnementService getEnv() {
		return this.env;
	}

	@Override
	public void setEnv(EnvironnementService env) {
		this.env.getEntities().remove(this);
		this.env = env;

	}

	@Override
	public int getCol() {
		return this.col;
	}

	@Override
	public int getRow() {
		return this.row;
	}

	@Override
	public Face getDir() {
		return this.face;
	}

	public Face getFace() {
		return face;
	}

	public void setFace(Face face) {
		this.face = face;
	}

	public void setRow(int row) {
		this.row = row;
	}





	@Override
	public boolean getTresorFound() {
		return this.tresorFound;
	}

	@Override
	public int getNbClefs() {
		return NbClefs;
	}

	@Override
	public void setNbClefs(int nb) {
		this.NbClefs = nb;
	}

}
