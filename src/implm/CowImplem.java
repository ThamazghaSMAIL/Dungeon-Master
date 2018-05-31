package implm;

import servives.CowService;
import servives.EntityService;
import servives.EnvironnementService;
import tools.Cell;
import tools.Face;
import tools.OptionEnum;

public class CowImplem implements CowService, EntityService{

	public CowImplem() {
	}

	protected int row;
	protected int col;
	protected int hp ;
	protected Face face;
	protected EnvironnementService env;
	protected boolean frappe;
	protected boolean enVie;
	@Override
	public void init(int row, int col, int hp , Face face , EnvironnementService env) {
		this.row = row;
		this.col = col;
		this.hp=hp;
		this.face = face;
		this.env = env;
		this.frappe = false ;
		this.enVie = true;
		if( col < 0 || col > this.env.getWidth() || row < 0 || row > env.getHeight() ) {
			System.out.println(" la vache ne peut être initialisé ! ses coordonnées dépassent les dimentions de l'env ");
		}
	}



	@Override
	public boolean getFrappe() {
		return this.frappe;
	}
	@Override
	public void setFrappe(boolean frappe) {
		this.frappe = frappe;
	}
	@Override
	public void step () {
		int random = 1 + (int)(Math.random() * ((7 - 1) + 1));
		if( random == 1 ) {
			this.forward();
		}else {
			if( random == 2 ) {
				this.backward();
			}else{
				if( random == 3 ) {
					this.turnL();
				}else {
					if( random == 4 ) {
						this.turnR();
					}else {
						if( random == 5 ) {
							this.strafeL();
						}else {
							if( random == 6 ) {
								this.strafeR();

							}else {
								if( random == 7 ) {
									this.frappe = true ;
								}
							}
						}
					}
				}
			}
		}
	}

	public boolean casePossible(int i , int j) {
		if( !(i == this.getEnv().getEntities().get(0).getRow() && j == this.getEnv().getEntities().get(0).getCol()) &&
				i >= 0 && j >=0 && i < this.getEnv().getHeight() && j < this.getEnv().getWidth() &&
				!(j == 0 && i == 0))

			if( ! this.getEnv().getCells()[i][j].getNature().equals(Cell.WLL) &&
					! this.getEnv().getCells()[i][j].getNature().equals(Cell.DWC) &&
					! this.getEnv().getCells()[i][j].getNature().equals(Cell.DNC)) { 
				return true;
			}
		
		return false;
	}

	@Override
	public void forward() {
		if( this.face.equals(Face.N)) {
			if( casePossible(this.row-1, this.col)) {
				this.getEnv().getCells()[this.row][this.col].setContent(OptionEnum.No);
				this.getEnv().getCells()[this.row-1][this.col].setContent(OptionEnum.So);
				this.row--;
			}
		}else {
			if( this.face.equals(Face.S)) {
				if( casePossible(this.row+1, this.col)) {
					this.getEnv().getCells()[this.row][this.col].setContent(OptionEnum.No);
					this.getEnv().getCells()[this.row-1][this.col].setContent(OptionEnum.So);
					this.row++;
				}
			}else {
				if( this.face.equals(Face.E)) {
					if( casePossible(this.row, this.col +1 )) {
						this.getEnv().getCells()[this.row][this.col].setContent(OptionEnum.No);
						this.getEnv().getCells()[this.row][this.col+1].setContent(OptionEnum.So);
						this.col++;
					}
				}else {
					if( this.face.equals(Face.W)) {
						if( casePossible(this.row, this.col-1) ) {
							this.getEnv().getCells()[this.row][this.col].setContent(OptionEnum.No);
							this.getEnv().getCells()[this.row][this.col-1].setContent(OptionEnum.So);
							this.col--;
						}
					}
				}
			}
		}
	}

	@Override
	public void backward() {
		if( this.face.equals(Face.N)) {
			if( casePossible(this.row+1, this.col) ) {
				this.getEnv().getCells()[this.row][this.col].setContent(OptionEnum.No);
				this.getEnv().getCells()[this.row+1][this.col].setContent(OptionEnum.So);
				this.row++;
			}
		}else {
			if( this.face.equals(Face.S)) {
				if( casePossible(this.row-1, this.col) ) {
					this.getEnv().getCells()[this.row][this.col].setContent(OptionEnum.No);
					this.getEnv().getCells()[this.row-1][this.col].setContent(OptionEnum.So);
					this.row--;
				}
			}else {
				if( this.face.equals(Face.E)) {
					if( casePossible(this.row, this.col-1) ) {
						this.getEnv().getCells()[this.row][this.col].setContent(OptionEnum.No);
						this.getEnv().getCells()[this.row][this.col-1].setContent(OptionEnum.So);
						this.col--;
					}
				}else {
					if( this.face.equals(Face.W)) {
						if( casePossible(this.row, this.col+1) ) {
							this.getEnv().getCells()[this.row][this.col].setContent(OptionEnum.No);
							this.getEnv().getCells()[this.row][this.col+1].setContent(OptionEnum.So);
							this.col++;
						}
					}
				}
			}
		}
	}


	@Override
	public void strafeL() {
		if( this.face.equals(Face.N)) {
			if( this.col-1 >=0 )
				if( casePossible(this.row, this.col-1) ) {

					this.getEnv().getCells()[this.row][this.col].setContent(OptionEnum.No);
					this.getEnv().getCells()[this.row][this.col-1].setContent(OptionEnum.So);
					this.col--;
				}
		}else {
			if( this.face.equals(Face.S)) {
				if( casePossible(this.row, this.col+1) ) {
					this.getEnv().getCells()[this.row][this.col].setContent(OptionEnum.No);
					this.getEnv().getCells()[this.row][this.col+1].setContent(OptionEnum.So);
					this.col++;
				}
			}else {
				if( this.face.equals(Face.E)) {
					if(this.row-1 >=0 )
						if( casePossible(this.row-1, this.col) ) {

							this.getEnv().getCells()[this.row][this.col].setContent(OptionEnum.No);
							this.getEnv().getCells()[this.row-1][this.col].setContent(OptionEnum.So);
							this.row--;
						}
				}else {
					if( this.face.equals(Face.W)) {
						if( casePossible(this.row+1, this.col) ) {
							this.getEnv().getCells()[this.row][this.col].setContent(OptionEnum.No);
							this.getEnv().getCells()[this.row+1][this.col].setContent(OptionEnum.So);
							this.row++;
						}
					}
				}
			}
		}
	}

	@Override
	public void strafeR() {
		if( this.face.equals(Face.N)) {
			if( casePossible(this.row, this.col+1) ) {
				this.getEnv().getCells()[this.row][this.col].setContent(OptionEnum.No);
				this.getEnv().getCells()[this.row][this.col+1].setContent(OptionEnum.So);
				this.col++;
			}
		}else {
			if( this.face.equals(Face.S)) {
				if( casePossible(this.row, this.col-1) ) {
					this.getEnv().getCells()[this.row][this.col].setContent(OptionEnum.No);
					this.getEnv().getCells()[this.row][this.col-1].setContent(OptionEnum.So);
					this.col--;

				}
			}else {
				if( this.face.equals(Face.E)) {
					if( casePossible(this.row+1, this.col) ) {

						this.getEnv().getCells()[this.row][this.col].setContent(OptionEnum.No);
						this.getEnv().getCells()[this.row+1][this.col].setContent(OptionEnum.So);
						this.row++;

					}
				}else {
					if( this.face.equals(Face.W)) {
						if( casePossible(this.row-1, this.col) ) {
							this.getEnv().getCells()[this.row][this.col].setContent(OptionEnum.No);
							this.getEnv().getCells()[this.row-1][this.col].setContent(OptionEnum.So);
							this.row--;
						}
					}
				}
			}
		}
	}


	@Override
	public void turnL() {
		if( this.face.equals(Face.N) ){
			this.setFace(Face.W);
		}else {
			if( this.face.equals(Face.S) ){
				this.face=Face.E;
			}else {
				if( this.face.equals(Face.E) ){
					this.face=Face.N;
				}else {
					if( this.face.equals(Face.W) ){
						this.face=Face.S;
					}
				}
			}
		}
	}



	@Override
	public void turnR() {
		if( this.face.equals(Face.N) ){
			this.face=Face.E;
		}else {
			if( this.face.equals(Face.S) ){
				this.face=Face.W;
			}else {
				if( this.face.equals(Face.E) ){
					this.face=Face.S;
				}else {
					if( this.face.equals(Face.W) ){
						this.face=Face.N;
					}
				}
			}
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
	public Face getFace() {
		return face;
	}
	@Override 
	public void setFace(Face face) {
		this.face = face;
	}
	@Override 
	public void setRow(int row) {
		this.row = row;
	}

	@Override
	public int getHp() {
		return this.hp;
	}

	@Override
	public void setHp(int hp) {
		this.hp = hp ;

	}

	@Override
	public void setCol(int col ) {
		this.col = col	;	
	}



	@Override
	public boolean isEnVie() {
		return this.enVie;

	}

	@Override
	public void setEnVie(boolean etat) {
		this.enVie=etat;		
	}
}
