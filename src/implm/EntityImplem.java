package implm;

import servives.EntityService;
import servives.EnvironnementService;
import tools.Cell;
import tools.Face;

public class EntityImplem extends MobImplem implements EntityService {

	protected int hp;
	protected int row;
	protected int col;
	protected Face face;
	protected EnvironnementService env;
	protected boolean tresorFound = false;
	protected int NbClefs = 0;
	boolean enVie;
	
	public EntityImplem(){
	}

	@Override
	public void init(int row, int col, int hp , Face face , EnvironnementService env) {
		this.row = row;
		this.col = col;
		this.face = face;
		this.env = env;
		this.hp=hp;
		this.enVie = true;
		
	}
	@Override
	public boolean isEnVie() {
		return this.enVie;
	}
	
	@Override
	public void setEnVie(boolean etat) {
		 this.enVie = etat;
	}
	
	@Override
	public int getHp() {
		return this.hp;
	}
	
	@Override
	public void setHp(int hp) {
		 this.hp = hp;
	}
	
	@Override
	public void step() {

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
	
	public Face getFace() {
		return this.face;
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
		return this.NbClefs;
	}

	@Override
	public void setNbClefs(int nb) {
		this.NbClefs = nb;
	}

}
