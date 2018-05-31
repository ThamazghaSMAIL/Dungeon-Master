package implm;

import java.util.ArrayList;
import java.util.List;

import servives.ClefService;
import servives.EntityService;
import servives.EnvironnementService;
import servives.PlayerService;
import servives.TresorService;
import tools.Cell;
import implm.CelluleImplem;
import tools.Face;
import tools.OptionEnum;
import tools.OptionFood;

public class EnvironmentImplem extends MapImplem implements EnvironnementService{

	public EnvironmentImplem() {
	}

	protected int height ;
	protected int width ;
	protected CelluleImplem[][] cells ;
	
	protected List<EntityService> entities;	
	protected TresorService tresor;
	protected ClefService clef;

	public CelluleImplem getCell(int i, int j) {
		return this.cells[i][j];
	}
	
	public Cell getCellNature(int i, int j) {
		return cells[i][j].getNature();
	}

//	@Override
//	public void OpenDoor(int i, int j) {
//		if( this.cells[i][j].getNature().equals(Cell.DNC )){
//			if( ! ((PlayerService) entities.get(0)).getClefFound() )
//				System.out.println("you have not the key ");
//			
//			this.cells[i][j].setNature(Cell.DNO);
//		} else {
//			if( cells[i][j].getNature().equals(Cell.DWC) ){
//				cells[i][j].setNature(Cell.DWO);
//			}else{
//				System.out.println(" ce n'est pas une porte -_- ");
//			}
//		}
//	}

//	//modification
//	@Override
//	public void CloseDoor(int i, int j) {
//		if(  this.cells[i][j].getContent().equals(OptionEnum.No) ) {
//
//			if( getCell(i, j).getNature() == Cell.DNO ){
//				getCell(i, j).setNature(Cell.DNC);
//			}else{
//				if( getCell(i, j).getNature() == Cell.DWO ){
//					getCell(i, j).setNature(Cell.DWC);
//				}else{
//					System.out.println(" ce n'est pas une porte -_-");
//				}
//			}
//		}else {
//			System.out.println("cette porte ne peut pas être fermée ! il y a un Mob sur place");
//		}
//	}
	
	@Override
	public boolean YaUnMob(int i , int j ) {
		for ( int l = 1 ; l < this.getEntities().size() ; l++ ) {
			if( this.getEntities().get(l).getRow() == i && this.getEntities().get(l).getCol() == j ) 
				if( this.cells[i][j].ContainsFood().equals(OptionFood.No))
					return true ;
		}
		return false;
	}

	@Override
	public void init( int height, int width) {
		this.height = height;
		this.width = width;
		this.cells = new CelluleImplem[100][100];
		this.entities = new ArrayList<EntityService>();

		int random ;

		this.cells[height-1][width-2] = new CelluleImplem();
		this.cells[height-1][width-2].init(height-1, width-2, Cell.EMP,this);
		this.cells[height-1][width-2].setContent(OptionEnum.So);
		PlayerImplem player =new PlayerImplem();
		player.init(height-1, width-2 , 3 , Face.N  , this );
		this.entities.add(player);
		for (int i = 0; i <= height-1 ; i++ ) 
			for (int j = 0; j <= width-1 ; j++ ) {

				if( this.cells[i][j] != null ) {
					continue;
				}
				if( i ==0 && j == 0 ) {
					this.cells[i][j] = new CelluleImplem();
					this.cells[i][j].init( i, j , Cell.OUT,this);
					this.cells[i][j].setContent(OptionEnum.No);
					continue;
				}

				if( i ==height-1 && j == width-1 ) {
					this.cells[i][j] = new CelluleImplem();
					this.cells[i][j].init(i, j, Cell.IN,this);
					this.cells[i][j].setContent(OptionEnum.No);
					continue;
				}
				if(i<2 && j<2) {
					this.cells[i][j] = new CelluleImplem();
					this.cells[i][j].init(i, j,Cell.EMP,this);
					this.cells[i][j].setContent(OptionEnum.No);
					continue;
				}
				if(i>height-3 && j>width-3) {
					this.cells[i][j] = new CelluleImplem();
					this.cells[i][j].init( i, j , Cell.EMP,this);
					this.cells[i][j].setContent(OptionEnum.No);
					continue;
				}
				if(i==height-1 || j==width-1 || i==0 || j==0) {
					this.cells[i][j] = new CelluleImplem( );
					this.cells[i][j].init(i, j , Cell.WLL,this);
					this.cells[i][j].setContent(OptionEnum.No);
					continue;
				}
				random = 1 + (int)(Math.random() * (4 - 1 ));
				switch (random) {

				case 1 :
					if(contient (i+1,j,Cell.WLL) && contient(i,j-1,Cell.EMP))
					{
						this.cells[i][j] = new CelluleImplem();
						this.cells[i][j].init(i,j,Cell.DWO,this);
						this.cells[i][j].setContent(OptionEnum.No);

						this.cells[i][j+1] = new CelluleImplem();
						this.cells[i][j+1].init(i,j+1,Cell.EMP,this);
						this.cells[i][j+1].setContent(OptionEnum.No);

						this.cells[i+1][j] = new CelluleImplem();
						this.cells[i+1][j].init(i+1,j,Cell.WLL,this);
						this.cells[i+1][j].setContent(OptionEnum.No);
						continue;
					}else {
						this.cells[i][j] = new CelluleImplem();
						this.cells[i][j].init( i, j , Cell.EMP,this);
						this.cells[i][j].setContent(OptionEnum.No);
						continue;
					}

				case 2 :
					if( contient (i-1, j,Cell.WLL) && contient(i, j-1, Cell.EMP) )
					{
						this.cells[i][j] = new CelluleImplem();
						this.cells[i][j].init( i, j , Cell.DWC,this);
						this.cells[i][j].setContent(OptionEnum.No);

						this.cells[i][j+1] = new CelluleImplem();
						this.cells[i][j+1].init( i, j+1 , Cell.EMP,this);
						this.cells[i][j+1].setContent(OptionEnum.No);

						this.cells[i+1][j] = new CelluleImplem();	
						this.cells[i+1][j].init( i+1, j , Cell.WLL,this);
						this.cells[i+1][j].setContent(OptionEnum.No);

						continue;
					}else {
						this.cells[i][j] = new CelluleImplem();
						this.cells[i][j].init( i, j , Cell.EMP,this);
						this.cells[i][j].setContent(OptionEnum.No);
						continue;
					}

				case 3 :
					if( contient (i-1, j,Cell.EMP) && contient(i, j-1, Cell.WLL) )
					{
						this.cells[i][j] = new CelluleImplem();
						this.cells[i][j] .init( i, j , Cell.DNC,this);
						this.cells[i][j].setContent(OptionEnum.No);

						this.cells[i][j+1] = new CelluleImplem();
						this.cells[i][j+1].init( i, j+1 , Cell.WLL,this);
						this.cells[i][j+1].setContent(OptionEnum.No);

						this.cells[i+1][j] = new CelluleImplem( );
						this.cells[i+1][j].init(i+1, j , Cell.EMP,this);
						this.cells[i+1][j].setContent(OptionEnum.No);

						/*this.cells[i+1][j].setNbClefCell(this.cells[i+1][j].getNbClefCell()+1);
						this.cells[i-1][j].setNbClefCell(this.cells[i-1][j].getNbClefCell()+1);*/
						continue;
					}else {
						this.cells[i][j] = new CelluleImplem();
						this.cells[i][j].init( i, j , Cell.WLL,this);
						this.cells[i][j].setContent(OptionEnum.No);
						continue;
					}

				case 4 :
					if( contient (i-1, j,Cell.EMP) && contient(i, j-1, Cell.WLL) )
					{
						this.cells[i][j] = new CelluleImplem();
						this.cells[i][j].init( i, j , Cell.DNO,this);
						this.cells[i][j].setContent(OptionEnum.No);

						this.cells[i][j+1] = new CelluleImplem();
						this.cells[i][j+1].init( i, j+1 , Cell.WLL,this);
						this.cells[i][j+1].setContent(OptionEnum.No);

						this.cells[i+1][j] = new CelluleImplem();
						this.cells[i+1][j].init( i+1, j , Cell.EMP,this);
						this.cells[i+1][j].setContent(OptionEnum.No);

						continue;
					}else {
						this.cells[i][j] = new CelluleImplem();
						this.cells[i][j].init( i, j , Cell.WLL,this);
						this.cells[i][j].setContent(OptionEnum.No);
						continue;
					}				
				}
			}

		int x,y;

		while( this.tresor == null ) {
			x = 1 + (int)(Math.random() * this.height-1);
			y = 2 + (int)(Math.random() * this.width-2 );
			if(this.cells[x][y].getNature().equals(Cell.EMP) && x!= 9 && y!= 8 &&
					this.isReachable(height-1, width-1, x, y )) {
				tresor = new TresorIplem();
				tresor.init(this,x, y);
				this.cells[x][y].setContent(OptionEnum.No);
			}
		}
		
		while( this.clef == null ) {
			x = 1 + (int)(Math.random() * this.height-1);
			y = 2 + (int)(Math.random() * this.width-2 );
			List<CelluleImplem> dejaVisites = new ArrayList<CelluleImplem>();
			if(this.cells[x][y].getNature().equals(Cell.EMP) && x!= 9 && y!= 8 &&
					x!= tresor.getI() && y!= tresor.getJ() && 
					this.isReachable_EMP_DNO(cells[height-1][width-1], this.cells[x][y], dejaVisites )) {
				this.clef = new ClefImplem();
				this.clef.init(this, x, y);
				this.cells[x][y].setContent(OptionEnum.No);
			}
		}
		
		System.out.println("tresor est a : "+tresor.getI()+" "+tresor.getJ());
		while( this.getEntities().size() < 2 ) {
			x = 1 + (int)(Math.random() * this.height-1);
			y = 2 + (int)(Math.random() * this.width-2);
			if( this.cells[x][y].getNature().equals(Cell.EMP) && this.getCells()[x][y].getContent().equals(OptionEnum.No) &&
					x!= tresor.getI() && y!= tresor.getJ() && x!= clef.getI() && y!= clef.getJ()) {
				int NbPoint = 3;
				CowImplem c = new CowImplem();
				c.init(x, y, NbPoint, Face.N, this);
				this.getEntities().add(c);
				this.cells[x][y].setContent(OptionEnum.So);
				System.out.println("le monstre est dans la position : x="+x+"y = "+y);
			}
		}
		List<CelluleImplem> deja = new ArrayList<CelluleImplem>();
		System.out.println("accessible : "+isReachable_EMP_DNO(cells[9][9], cells[0][0], deja));
	}

	

	private boolean contient(int i,int j,Cell c) {
		if( cells[i][j] != null )
			if( cells[i][j].getNature().equals(c))
				return true;
		return false;
	}


	@Override
	public CelluleImplem[][] getCells() {
		return this.cells;
	}
	@Override
	public void setCells(CelluleImplem[][] cells) {
		this.cells = cells;
	}
	@Override
	public void setHeight(int height) {
		this.height = height;
	}
	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public int getHeight() {
		return  this.height;
	}

	@Override
	public int getWidth() {
		return this.width;
	}



	
	
	

	public boolean isReachable_EMP_DNO(CelluleImplem depart, CelluleImplem arrivé, List<CelluleImplem> dejaVisites) {

		if( depart.getNature().equals(Cell.WLL) || depart.getNature().equals(Cell.DNC)
				|| depart.getNature().equals(Cell.DWC) )
			return false;

		if(depart.equals(arrivé))
			return true;

		boolean res = false;
		dejaVisites.add(depart);
		for(CelluleImplem next : casesPossibles(depart)) { 
			List<CelluleImplem> l = new ArrayList<>();
			l.addAll(dejaVisites);
			if(!dejaVisites.contains(next))
				res = res || isReachable_EMP_DNO(next, arrivé, l);
		}
		return res; 
	}

	@Override
	public List<EntityService> getEntities() {
		return this.entities;
	}

	@Override
	public void setEntities(List<EntityService> en) {
		this.entities = en;
	}
	@Override
	public TresorService getTresor() {
		return this.tresor;
	}
	@Override
	public ClefService getClef() {
		return this.clef;
	}

	@Override
	public boolean isReachable(int x1, int y1, int x2, int y2) {
		List<CelluleImplem> dejaVisites = new ArrayList<CelluleImplem>();
		return isReachableBis(cells[x1][y1], cells[x2][y2], dejaVisites);
	}
	
	public boolean isReachableBis(CelluleImplem depart, CelluleImplem arrivé, List<CelluleImplem> dejaVisites) {

		if(depart.getNature().equals(Cell.WLL))
			return false;

		if(depart.equals(arrivé))
			return true;

		boolean res = false;
		dejaVisites.add(depart);
		for(CelluleImplem next : casesPossibles(depart)) { 
			List<CelluleImplem> l = new ArrayList<>();
			l.addAll(dejaVisites);
			if(!dejaVisites.contains(next))
				res = res || isReachableBis(next, arrivé, l);
		}
		return res; 
	}
	
	public List<CelluleImplem> casesPossibles(CelluleImplem c){
		List<CelluleImplem> res = new ArrayList<CelluleImplem>();
		if(c.getI()-1>=0 )
			res.add(cells[c.getI()-1][c.getJ()]);
		if(c.getJ()-1>=0)
			res.add(cells[c.getI()][c.getJ()-1]);
		if(c.getI()+1<height)
			res.add(cells[c.getI()+1][c.getJ()]);
		if(c.getJ()+1<width)
			res.add(cells[c.getI()][c.getJ()+1]);
		return res;
	}

}
