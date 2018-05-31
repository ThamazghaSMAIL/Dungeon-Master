package implm;

import servives.CelluleService;
import servives.EnvironnementService;
import servives.PlayerService;
import tools.Cell;
import implm.CelluleImplem;
import tools.Commande;
import tools.Face;
import tools.OptionEnum;
import ui.Controller;

public class PlayerImplem extends EntityImplem implements PlayerService {

	Controller controller = new Controller();

	protected int hp;
	protected int row;
	protected int col;
	protected Face face;
	protected EnvironnementService env;
	protected boolean enVie;
	protected Commande lastCommande = null;


	protected boolean tresorFound = false;//rajouter les spec sur le trésor
	protected boolean clefFound = false;//rajouter la cond dans player : que clefound = false initialement


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
	public boolean Viewable(int u, int v) {
		if( row < -1 || row > 1 || col >3 || col < -1) 
			return false;

		switch(this.getFace()){
		case N:
			return getCol()+u<getEnv().getWidth() &&  getCol()+u>=0 && getRow()+v >=0 && getRow()+v<getEnv().getHeight();
		case S:
			return getCol()-u<getEnv().getWidth() &&  getCol()-u>=0 && getRow()-v >=0 && getRow()-v<getEnv().getHeight();

		case E:
			return getCol()+v<getEnv().getWidth() &&  getCol()+v>=0 && getRow()-u >=0 && getRow()-u<getEnv().getHeight();

		case W:
			return getCol()-v<getEnv().getWidth() &&  getCol()-v>=0 && getRow()+u >=0 && getRow()+u<getEnv().getHeight();

		default:
			System.out.println("erreur");
		}
		return false;
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
		CelluleImplem cellSvte = null;
		if( this.row == this.getEnv().getTresor().getI() && this.col == this.getEnv().getTresor().getJ() ) {
			tresorFound = true ;
			this.env.getTresor().setTrouve(true);
			System.out.println(" Géniale ! vous avez trouvé le trésor, maintenant il faut rejoindre la sortie ");
		}

		if(this.getFace().equals(Face.N)) {
			cellSvte = this.getEnv().getCells()[this.getRow()-1][this.getCol()];
			if( this.row -1 >= 0)
				if( ! cellSvte.getNature().equals(Cell.WLL) && !( (cellSvte.getNature().equals(Cell.DNC)||cellSvte.getNature().equals(Cell.DWC)) 
						&& !this.clefFound  && !YaUnMonstre(cellSvte))) {
					if( this.env.getCells()[cellSvte.getI()][cellSvte.getJ()].getContent().equals(OptionEnum.No)){
						if( cellSvte.getI() == env.getClef().getI() &&  cellSvte.getJ() == env.getClef().getJ()) {

						}
						this.env.getCells()[this.row][this.col].setContent(OptionEnum.No);
						cellSvte.setContent(OptionEnum.So);
						this.row--;
					}else {
						if(this.env.getCells()[cellSvte.getI()][cellSvte.getJ()].getContent().equals(OptionEnum.Fo)){
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
					if( ! cellSvte.getNature().equals(Cell.WLL) && !( (cellSvte.getNature().equals(Cell.DNC)||cellSvte.getNature().equals(Cell.DWC)) 
							&& !this.clefFound ) && !YaUnMonstre(cellSvte)) 
						if( this.env.getCells()[cellSvte.getI()][cellSvte.getJ()].getContent().equals(OptionEnum.No) ) {
							this.env.getCells()[this.row][this.col].setContent(OptionEnum.No);
							cellSvte.setContent(OptionEnum.So);
							this.row++;

						}
			}else {

				if(this.getFace().equals(Face.E)) {
					cellSvte = this.getEnv().getCells()[this.getRow()][this.getCol()+1];
					if( this.col+1 < this.env.getWidth() )
						if( ! cellSvte.getNature().equals(Cell.WLL) && !( (cellSvte.getNature().equals(Cell.DNC)||cellSvte.getNature().equals(Cell.DWC)) 
								&& !this.clefFound ) && !YaUnMonstre(cellSvte) ) 
							if( this.env.getCells()[cellSvte.getI()][cellSvte.getJ()].getContent().equals(OptionEnum.No) ) {
								this.env.getCells()[this.row][this.col].setContent(OptionEnum.No);
								cellSvte.setContent(OptionEnum.So);
								this.col++;
							}
				}else {

					if(this.getFace().equals(Face.W)) {
						cellSvte = this.getEnv().getCells()[this.getRow()][this.getCol()-1];
						if( this.col -1 >= 0 ) {
							if( ! cellSvte.getNature().equals(Cell.WLL) && !( (cellSvte.getNature().equals(Cell.DNC)||cellSvte.getNature().equals(Cell.DWC)) 
									&& !this.clefFound && !YaUnMonstre(cellSvte) )) 
								if( this.env.getCells()[cellSvte.getI()][cellSvte.getJ()].getContent().equals(OptionEnum.No) ) {
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
		CelluleImplem cellSvte;
		if( this.row == this.getEnv().getTresor().getI() && this.col == this.getEnv().getTresor().getJ() ) {
			tresorFound = true ;
			System.out.println(" Géniale ! vous avez trouvé le trésor, maintenant il faut rejoindre la sortie ");
		}


		if( this.face.equals(Face.N)) {
			cellSvte=this.getEnv().getCells()[this.getRow()+1][this.getCol()];



			if( this.row+1 < this.getEnv().getHeight())
				if( ! cellSvte.getNature().equals(Cell.WLL) && !( (cellSvte.getNature().equals(Cell.DNC)||cellSvte.getNature().equals(Cell.DWC)) 
						&& !this.clefFound && !YaUnMonstre(cellSvte) )) 
					if( this.env.getCells()[cellSvte.getI()][cellSvte.getJ()].getContent().equals(OptionEnum.No) ){
						this.env.getCells()[this.row][this.col].setContent(OptionEnum.No);
						cellSvte.setContent(OptionEnum.So);
						this.row++;
					}
		}else {
			if( this.face.equals(Face.S)) {
				cellSvte = this.getEnv().getCells()[this.getRow()-1][this.getCol()];


				if( this.row-1 >= 0)
					if( ! cellSvte.getNature().equals(Cell.WLL) && !( (cellSvte.getNature().equals(Cell.DNC)||cellSvte.getNature().equals(Cell.DWC)) 
							&& !this.clefFound && !YaUnMonstre(cellSvte))) 
						if( this.env.getCells()[cellSvte.getI()][cellSvte.getJ()].getContent().equals(OptionEnum.No) ){
							this.env.getCells()[this.row][this.col].setContent(OptionEnum.No);
							cellSvte.setContent(OptionEnum.So);
							this.row--;
						}
			}else {
				if( this.face.equals(Face.E)) {
					cellSvte = this.getEnv().getCells()[this.getRow()][this.getCol()-1];

					if( this.col-1 >= 0)
						if( ! cellSvte.getNature().equals(Cell.WLL) && !( (cellSvte.getNature().equals(Cell.DNC)||cellSvte.getNature().equals(Cell.DWC)) 
								&& !this.clefFound && !YaUnMonstre(cellSvte))) 
							if( this.env.getCells()[cellSvte.getI()][cellSvte.getJ()].getContent().equals(OptionEnum.No) ){
								this.env.getCells()[this.row][this.col].setContent(OptionEnum.No);
								cellSvte.setContent(OptionEnum.So);
								this.col--;
							}
				}else {
					if( this.face.equals(Face.W)) {
						cellSvte = this.getEnv().getCells()[this.getRow()][this.getCol()+1];

						if( this.col+1 < this.getEnv().getWidth() )
							if( ! cellSvte.getNature().equals(Cell.WLL)&& !( (cellSvte.getNature().equals(Cell.DNC)||cellSvte.getNature().equals(Cell.DWC)) 
									&& !this.clefFound && !YaUnMonstre(cellSvte) ) ) 
								if( this.env.getCells()[cellSvte.getI()][cellSvte.getJ()].getContent().equals(OptionEnum.No) ){
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
		CelluleImplem cellSvte; 

		if( this.row == this.getEnv().getTresor().getI() && this.col == this.getEnv().getTresor().getJ() ) {
			tresorFound = true ;
			System.out.println(" Géniale ! vous avez trouvé le trésor, maintenant il faut rejoindre la sortie ");
		}

		if( this.face.equals(Face.N)) {
			cellSvte = this.getEnv().getCells()[this.getRow()][this.getCol()+1];
			if( this.col+1 < this.getEnv().getWidth() && ! cellSvte.getNature().equals(Cell.WLL) && !YaUnMonstre(cellSvte) &&
					!( (cellSvte.getNature().equals(Cell.DNC)||cellSvte.getNature().equals(Cell.DWC)) && !this.clefFound )) {
				this.env.getCells()[this.row][this.col].setContent(OptionEnum.No);
				cellSvte.setContent(OptionEnum.So);
				this.col++;
			}
		}else {
			if( this.face.equals(Face.S)) {
				cellSvte = this.getEnv().getCells()[this.getRow()][this.getCol()-1];
				if( this.col-1 >=0 && cellSvte.getNature().equals(Cell.WLL)&& !YaUnMonstre(cellSvte) &&
						!( (cellSvte.getNature().equals(Cell.DNC)||cellSvte.getNature().equals(Cell.DWC)) && !this.clefFound )) {
					cellSvte.setContent(OptionEnum.So);
					this.col--;
				}

			}else {
				if( this.face.equals(Face.E)) {
					cellSvte = this.getEnv().getCells()[this.getRow()+1][this.getCol()];
					if( this.row+1< this.env.getHeight() && ! cellSvte.getNature().equals(Cell.WLL)&& !YaUnMonstre(cellSvte) &&
							!( (cellSvte.getNature().equals(Cell.DNC)||cellSvte.getNature().equals(Cell.DWC)) && !this.clefFound )) {
						this.env.getCells()[this.row][this.col].setContent(OptionEnum.No);
						cellSvte.setContent(OptionEnum.So);
						this.row++;
					}
				}else {
					if( this.face.equals(Face.W)) {
						cellSvte = this.getEnv().getCells()[this.getRow()-1][this.getCol()];
						if( this.row-1 >=0 && ! cellSvte.getNature().equals(Cell.WLL)&& !YaUnMonstre(cellSvte) &&
								!( (cellSvte.getNature().equals(Cell.DNC)||cellSvte.getNature().equals(Cell.DWC)) && !this.clefFound )) {
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
		CelluleImplem cellSvte; 
		if( this.row == this.getEnv().getTresor().getI() && this.col == this.getEnv().getTresor().getJ() ) {
			tresorFound = true ;
			System.out.println(" Géniale ! vous avez trouvé le trésor, maintenant il faut rejoindre la sortie ");
		}

		if( this.face.equals(Face.N)) {
			cellSvte = this.getEnv().getCells()[this.getRow()][this.getCol()-1];
			if( this.col-1 >=0 && ! cellSvte.getNature().equals(Cell.WLL)&& !YaUnMonstre(cellSvte) &&
					!( (cellSvte.getNature().equals(Cell.DNC)||cellSvte.getNature().equals(Cell.DWC)) && !this.clefFound ) ) {
				this.env.getCells()[this.row][this.col].setContent(OptionEnum.No);
				cellSvte.setContent(OptionEnum.So);
				this.col--;
			}

		}else {
			if( this.face.equals(Face.S)) {
				cellSvte = this.getEnv().getCells()[this.getRow()][this.getCol()+1];
				if( this.col+1 < this.getEnv().getWidth() && !cellSvte.getNature().equals(Cell.WLL)&& !YaUnMonstre(cellSvte) &&
						!( (cellSvte.getNature().equals(Cell.DNC)||cellSvte.getNature().equals(Cell.DWC)) && !this.clefFound )) {
					this.env.getCells()[this.row][this.col].setContent(OptionEnum.No);
					cellSvte.setContent(OptionEnum.So);
					this.col++;
				}

			}else {
				if( this.face.equals(Face.E)) {
					cellSvte = this.getEnv().getCells()[this.getRow()-1][this.getCol()];
					if( this.row-1 >=0 && !cellSvte.getNature().equals(Cell.WLL)&& !YaUnMonstre(cellSvte) &&
							!( (cellSvte.getNature().equals(Cell.DNC)||cellSvte.getNature().equals(Cell.DWC)) && !this.clefFound )) {

						this.env.getCells()[this.row][this.col].setContent(OptionEnum.No);
						cellSvte.setContent(OptionEnum.So);
						this.row--;
					}

				}else {
					if( this.face.equals(Face.W)) {
						cellSvte = this.getEnv().getCells()[this.getRow()+1][this.getCol()];
						if( this.row+1 < this.getEnv().getHeight() && !cellSvte.getNature().equals(Cell.WLL)&& !YaUnMonstre(cellSvte) &&
								!( (cellSvte.getNature().equals(Cell.DNC)||cellSvte.getNature().equals(Cell.DWC)) && !this.clefFound )) {

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

		if( this.getRow() == this.env.getTresor().getI() && this.getCol() == this.env.getTresor().getJ() ) {
			this.tresorFound = true;
		}
		if( this.getRow() == this.env.getClef().getI() && this.getCol() == this.env.getClef().getJ() ) {
			this.clefFound = true;
		}


		switch(this.getLastCommande()){
		case FF:
			this.forward();
			break;

		case BB:
			this.backward();
			break;

		case LL:
			this.strafeL();
			break;

		case RR:
			this.strafeR();
			break;

		case TL:
			this.turnL();
			break;

		case TR:
			this.turnR();
			break;

		default:
			throw new Error("Erreur");			
		}
	}

	@Override
	public boolean getTresorFound() {
		return this.tresorFound;
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

	@Override
	public Commande getLastCommande() {
		return this.lastCommande;
	}

	@Override
	public void setLastCommande(Commande lastCommande) {
		this.lastCommande = lastCommande;
	}

	@Override
	public boolean getClefFound() {
		return this.clefFound;
	}

	@Override
	public void setClefFound(boolean b) {
		this.clefFound= b;
	}

	@Override
	public Cell getNatureP(int i , int j ) {
		if( i==0 || i==1 || i==-1 )
			if( j>= -1 && i<=3 ) 
				if( this.getEnv().getCells()[this.getRow()+i][this.getCol()+j] != null ) {
					return this.getEnv().getCells()[this.getRow()+i][this.getCol()+j].getNature();
				}
		return null;
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

	public boolean YaUnMonstre(CelluleService c){
		for(int i = 1 ; i < this.env.getEntities().size() ; i++ ){
			if( this.getEnv().getEntities().get(i).getRow() == c.getI() && this.getEnv().getEntities().get(i).getCol() == c.getJ() )
				if( this.getEnv().getEntities().get(i).isEnVie())
					return true;
		}
		return false;

	}

}
