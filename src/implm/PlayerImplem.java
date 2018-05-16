package implm;

import servives.EntityService;
import servives.EnvironnementService;
import servives.PlayerService;
import tools.Cell;
import tools.Cellule;
import tools.Face;
import tools.OptionEnum;
import ui.Controller;

public class PlayerImplem implements PlayerService, EntityService {

	Controller controller = new Controller();

	protected int hp;
	protected int row;
	protected int col;
	protected Face face=Face.N;
	protected EnvironnementService env;
	protected boolean tresorFound = false;
	protected int NbClefs = 0;
	protected boolean enVie;

	public  PlayerImplem() {
	}

	@Override
	public void init(int row, int col,int hp , Face face , EnvironnementService env) {
		this.row = row;
		this.col = col;
		this.env = env;
		this.face = face;
		this.enVie = true;
		this.hp=hp;
	}


	@Override
	public OptionEnum getContent(int row, int col) {
		return this.getEnv().getCellContent(row, col);
	}

	@Override
	public Cell getNature(int row, int col) {
		return getEnv().getCellNature(row,col);
	}

	@Override
	public boolean Viewable(int row, int col) {
		if( row < -1 || row > 1 || col >3 || col < -1) {
			return false;
		} else {
			//WALL, DWC, DNC
			if( this.getContent(row, col).equals(Cell.WLL) || this.getContent(row, col).equals(Cell.DNC) || this.getContent(row, col).equals(Cell.DWC)) {
				return false;
			}else {
				return true;
			}
		}
	}


	@Override
	public EnvironnementService getEnv() {
		return this.env;
	}

	@Override
	public void setEnv(EnvironnementService env) {
		this.env=env;
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
	public void forward() {
		Cellule cellSvte = null;
		if( this.row == this.getEnv().getTresor().getI() && this.col == this.getEnv().getTresor().getJ() ) {
			tresorFound = true ;
			System.out.println(" Géniale ! vous avez trouvé le trésor, maintenant il faut rejoindre la sortie ");
		}

		if(this.getFace().equals(Face.N)) {
			cellSvte = this.getEnv().getCells()[this.getRow()-1][this.getCol()];
			if( this.row -1 >= 0)
				if( ! cellSvte.getNature().equals(Cell.WLL) ) {
					if( this.env.getCellContent( cellSvte.getI(), cellSvte.getJ()).equals(OptionEnum.No)){
						if( cellSvte.getClef() ) {
							this.setNbClefs(this.getNbClefs()+2);
						}
						this.env.getCells()[this.row][this.col].setContent(OptionEnum.No);
						cellSvte.setContent(OptionEnum.So);
						this.row--;
					}else {
						if(this.env.getCellContent( cellSvte.getI(), cellSvte.getJ()).equals(OptionEnum.Fo)){
							System.out.println("there is food");
							if( cellSvte.getClef() ) {
								this.setNbClefs(this.getNbClefs()+2);
							}
							this.env.getCells()[this.row][this.col].setContent(OptionEnum.No);
							cellSvte.setContent(OptionEnum.So);
							this.row--;
						}
					}
				}
		}else {

			if(this.getFace().equals(Face.S)) {
				cellSvte = this.getEnv().getCells()[this.getRow()+1][this.getCol()];
				if( this.row+1 < this.getEnv().getHeight() )
					if( ! cellSvte.getNature().equals(Cell.WLL) ) 
						if( this.env.getCellContent( cellSvte.getI(), cellSvte.getJ()).equals(OptionEnum.No) ) {
							if( cellSvte.getClef() ) {
								this.setNbClefs(this.getNbClefs()+2);
							}
							this.env.getCells()[this.row][this.col].setContent(OptionEnum.No);
							cellSvte.setContent(OptionEnum.So);
							this.row++;

						}
			}else {

				if(this.getFace().equals(Face.E)) {
					cellSvte = this.getEnv().getCells()[this.getRow()][this.getCol()+1];
					if( this.col+1 < this.env.getWidth() )
						if( ! cellSvte.getNature().equals(Cell.WLL) ) 
							if( this.env.getCellContent( cellSvte.getI(), cellSvte.getJ()).equals(OptionEnum.No) ) {
								if( cellSvte.getClef() ) {
									this.setNbClefs(this.getNbClefs()+2);
								}
								this.env.getCells()[this.row][this.col].setContent(OptionEnum.No);
								cellSvte.setContent(OptionEnum.So);
								this.col++;
							}
				}else {

					if(this.getFace().equals(Face.W)) {
						cellSvte = this.getEnv().getCells()[this.getRow()][this.getCol()-1];
						if( this.col -1 >= 0 ) {
							if( ! cellSvte.getNature().equals(Cell.WLL) ) 
								if( this.env.getCellContent( cellSvte.getI(), cellSvte.getJ()).equals(OptionEnum.No) ) {
									if( cellSvte.getClef() ) {
										this.setNbClefs(this.getNbClefs()+2);
									}
									this.env.getCells()[this.row][this.col].setContent(OptionEnum.No);
									cellSvte.setContent(OptionEnum.So);
									this.col--;
								}
						}
					}
				}

			}
		}
	}

	@Override
	public void backward() {
		Cellule cellSvte;
		if( this.row == this.getEnv().getTresor().getI() && this.col == this.getEnv().getTresor().getJ() ) {
			tresorFound = true ;
			System.out.println(" Géniale ! vous avez trouvé le trésor, maintenant il faut rejoindre la sortie ");
		}


		if( this.face.equals(Face.N)) {
			cellSvte=this.getEnv().getCells()[this.getRow()+1][this.getCol()];



			if( this.row+1 < this.getEnv().getHeight())
				if( ! cellSvte.getNature().equals(Cell.WLL) ) 
					if( this.env.getCellContent( cellSvte.getI(), cellSvte.getJ()).equals(OptionEnum.No) ){
						if( cellSvte.getClef() ) {
							this.setNbClefs(this.getNbClefs()+2);
						}
						this.env.getCells()[this.row][this.col].setContent(OptionEnum.No);
						cellSvte.setContent(OptionEnum.So);
						this.row++;
					}
		}else {
			if( this.face.equals(Face.S)) {
				cellSvte = this.getEnv().getCells()[this.getRow()-1][this.getCol()];


				if( this.row-1 >= 0)
					if( ! cellSvte.getNature().equals(Cell.WLL) ) 
						if( this.env.getCellContent( cellSvte.getI(), cellSvte.getJ()).equals(OptionEnum.No) ){
							if( cellSvte.getClef() ) {
								this.setNbClefs(this.getNbClefs()+2);
							}
							this.env.getCells()[this.row][this.col].setContent(OptionEnum.No);
							cellSvte.setContent(OptionEnum.So);
							this.row--;
						}
			}else {
				if( this.face.equals(Face.E)) {
					cellSvte = this.getEnv().getCells()[this.getRow()][this.getCol()-1];

					if( this.col-1 >= 0)
						if( ! cellSvte.getNature().equals(Cell.WLL) ) 
							if( this.env.getCellContent( cellSvte.getI(), cellSvte.getJ()).equals(OptionEnum.No) ){
								if( cellSvte.getClef() ) {
									this.setNbClefs(this.getNbClefs()+2);
								}
								this.env.getCells()[this.row][this.col].setContent(OptionEnum.No);
								cellSvte.setContent(OptionEnum.So);
								this.col--;
							}
				}else {
					if( this.face.equals(Face.W)) {
						cellSvte = this.getEnv().getCells()[this.getRow()][this.getCol()+1];

						if( this.col+1 < this.getEnv().getWidth() )
							if( ! cellSvte.getNature().equals(Cell.WLL) ) 
								if( this.env.getCellContent( cellSvte.getI(), cellSvte.getJ()).equals(OptionEnum.No) ){
									if( cellSvte.getClef() ) {
										this.setNbClefs(this.getNbClefs()+2);
									}
									this.env.getCells()[this.row][this.col].setContent(OptionEnum.No);
									cellSvte.setContent(OptionEnum.So);
									this.col++;
								}
					}
				}
			}
		}
	}

	@Override
	public void turnL() {
		Cellule cellCourante = this.getEnv().getCells()[this.getRow()][this.getCol()];
		if( cellCourante.getClef() ) {
			this.setNbClefs(this.getNbClefs()+2);
			cellCourante.setClef(false);
		}


		if( this.face.equals(Face.N) ){
			this.setFace(Face.W);
		}else {
			if( this.face.equals(Face.S)){
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
		Cellule cellCourante = this.getEnv().getCells()[this.getRow()][this.getCol()];
		if( cellCourante.getClef() ) {
			this.setNbClefs(this.getNbClefs()+2);
			cellCourante.setClef(false);
		}
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
	public void strafeR() {
		Cellule cellSvte; 

		if( this.row == this.getEnv().getTresor().getI() && this.col == this.getEnv().getTresor().getJ() ) {
			tresorFound = true ;
			System.out.println(" Géniale ! vous avez trouvé le trésor, maintenant il faut rejoindre la sortie ");
		}

		if( this.face.equals(Face.N)) {
			cellSvte = this.getEnv().getCells()[this.getRow()][this.getCol()+1];
			if( this.col+1 < this.getEnv().getWidth() && ! cellSvte.getNature().equals(Cell.WLL)) {
				this.env.getCells()[this.row][this.col].setContent(OptionEnum.No);
				cellSvte.setContent(OptionEnum.So);
				this.col++;
			}
		}else {
			if( this.face.equals(Face.S)) {
				cellSvte = this.getEnv().getCells()[this.getRow()][this.getCol()-1];
				if( this.col-1 >=0 && cellSvte.getNature().equals(Cell.WLL)) {
					cellSvte.setContent(OptionEnum.So);
					this.col--;
				}

			}else {
				if( this.face.equals(Face.E)) {
					cellSvte = this.getEnv().getCells()[this.getRow()+1][this.getCol()];
					if( this.row+1< this.env.getHeight() && ! cellSvte.getNature().equals(Cell.WLL)) {
						this.env.getCells()[this.row][this.col].setContent(OptionEnum.No);
						cellSvte.setContent(OptionEnum.So);
						this.row++;
					}

				}else {
					if( this.face.equals(Face.W)) {
						cellSvte = this.getEnv().getCells()[this.getRow()-1][this.getCol()];
						if( this.row-1 >=0 && ! cellSvte.getNature().equals(Cell.WLL)) {
							this.env.getCells()[this.row][this.col].setContent(OptionEnum.No);
							cellSvte.setContent(OptionEnum.So);
							this.row--;
						}

					}
				}
			}
		}
	}

	@Override
	public void strafeL() {
		Cellule cellSvte; 
		if( this.row == this.getEnv().getTresor().getI() && this.col == this.getEnv().getTresor().getJ() ) {
			tresorFound = true ;
			System.out.println(" Géniale ! vous avez trouvé le trésor, maintenant il faut rejoindre la sortie ");
		}

		if( this.face.equals(Face.N)) {
			cellSvte = this.getEnv().getCells()[this.getRow()][this.getCol()-1];
			if( this.col-1 >=0 && ! cellSvte.getNature().equals(Cell.WLL)) {
				this.env.getCells()[this.row][this.col].setContent(OptionEnum.No);
				cellSvte.setContent(OptionEnum.So);
				this.col--;
			}

		}else {
			if( this.face.equals(Face.S)) {
				cellSvte = this.getEnv().getCells()[this.getRow()][this.getCol()+1];
				if( this.col+1 < this.getEnv().getWidth() && !cellSvte.getNature().equals(Cell.WLL)) {
					this.env.getCells()[this.row][this.col].setContent(OptionEnum.No);
					cellSvte.setContent(OptionEnum.So);
					this.col++;
				}

			}else {
				if( this.face.equals(Face.E)) {
					cellSvte = this.getEnv().getCells()[this.getRow()-1][this.getCol()];
					if( this.row-1 >=0 && !cellSvte.getNature().equals(Cell.WLL)) {
						this.env.getCells()[this.row][this.col].setContent(OptionEnum.No);
						cellSvte.setContent(OptionEnum.So);
						this.row--;
					}

				}else {
					if( this.face.equals(Face.W)) {
						cellSvte = this.getEnv().getCells()[this.getRow()+1][this.getCol()];
						if( this.row+1 < this.getEnv().getHeight() && !cellSvte.getNature().equals(Cell.WLL)) {
							this.env.getCells()[this.row][this.col].setContent(OptionEnum.No);
							cellSvte.setContent(OptionEnum.So);
							this.row++;
						}
					}
				}
			}
		}
	}





	@Override
	public void step () {
		int random  = random = 1 + (int)(Math.random() * ((6 - 1) + 1));;
		switch( random ) {
		case 1: 
			this.forward();
			break;
		case 2 :
			this.backward();
			break;
		case 3 :
			this.turnL();
			break;
		case 4 :
			this.turnR();
			break;
		case 5 : 
			this.strafeL();
			break ;
		case 6 : 
			this.strafeR();
			break;
		}

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


	@Override
	public void setRow(int row) {
		this.row=row;
	}

	@Override
	public int getHp() {
		return this.hp;
	}

	@Override
	public Face getFace() {
		return this.face;
	}
	@Override
	public void setFace(Face face) {
		this.face=face;
	}

	@Override
	public void setHp(int hp) {
		this.hp = hp ;
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
