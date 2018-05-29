package implm;



import servives.MapService;
import tools.Cell;
import implm.CelluleImplem;

public class MapImplem implements MapService{
	public MapImplem() {}

	public int height ;
	public int width ;
	public CelluleImplem[][] cells ;
	

	public CelluleImplem getCell(int i, int j) {
		return this.cells[i][j];
	}


	//CellNature(M,x,y) { fDNC, DWC }
	@Override
	public void OpenDoor(int i, int j) {
		if( getCell(i, j).getNature() == Cell.DNC ){
			getCell(i, j).setNature(Cell.DNO);
		}else{
			if( getCell(i, j).getNature() == Cell.DWC ){
				getCell(i, j).setNature(Cell.DWO);
			}else{
				System.out.println(" ce n'est pas une porte -_-");
			}
		}
	}

	@Override
	public void CloseDoor(int i, int j) {
		if( getCell(i, j).getNature() == Cell.DNO ){
			getCell(i, j).setNature(Cell.DNC);
		}else{
			if( getCell(i, j).getNature() == Cell.DWO ){
				getCell(i, j).setNature(Cell.DWC);
			}else{
				System.out.println(" ce n'est pas une porte -_-");
			}
		}

	}

	private boolean contient(int i,int j,Cell c) {
		if( this.cells[i][j] != null )
			if( this.cells[i][j].getNature().equals(c))
				return true;
		return false;
	}


//	public void init(int h, int w) {
//		
//		this.height = h;
//		this.width = w;
//		this.cells = new CelluleImplem[100][100];
//		
//		int random ;
//
//		for (int i = 0; i <= h-1 ; i++ ) 
//			for (int j = 0; j <= w-1 ; j++ ) {
//				if( this.cells[i][j] != null ) {
//					continue;
//				}else {
//					if( i ==0 && j == 0 ) {
//						this.cells[i][j] = new CelluleImplem();
//						this.cells[i][j].init( 0, 0 , Cell.OUT);
//						continue;
//					}else {
//						if( i ==h-1 && j == w-1 ) {
//							this.cells[i][j] = new CelluleImplem( );
//							this.cells[i][j].init(0, 0 , Cell.IN);
//							continue;
//						}else {
//							random = 1 + (int)(Math.random() * ((4 - 1) + 1));
//							System.out.println("\n-"+random+" iteration i="+i+" j="+j); 
//							switch (random) {
//
//							case 1 :
//								if( i>0 && j>0 && i< h-1 && j< w-1 ) {
//									if( contient (i-1, j,Cell.WLL) && contient(i, j-1, Cell.EMP) )
//									{
//										this.cells[i][j] = new CelluleImplem();
//										this.cells[i][j].init( i, j , Cell.DWC);
//										
//										this.cells[i][j+1] = new CelluleImplem();
//										this.cells[i][j+1].init( i, j+1 , Cell.EMP);
//										
//										this.cells[i+1][j] = new CelluleImplem();
//										this.cells[i+1][j].init( i+1, j , Cell.WLL);
//										
//										continue;
//									}else {
//										this.cells[i][j] = new CelluleImplem();
//										this.cells[i][j].init( i, j , Cell.EMP);
//										
//										continue;
//									}
//								}else {
//									this.cells[i][j] = new CelluleImplem();
//									this.cells[i][j].init( i, j , Cell.EMP);
//									
//									continue;
//								}
//							case 2 :
//								if( i>0 && j>0 && i< h-1 && j< w-1 ) {
//									if( contient (i-1, j,Cell.WLL) && contient(i, j-1, Cell.EMP) )
//									{
//										this.cells[i][j] = new CelluleImplem( );
//										this.cells[i][j].init(i, j , Cell.DWO);
//										
//										this.cells[i][j+1] = new CelluleImplem();
//										this.cells[i][j+1].init( i, j+1 , Cell.EMP);
//										
//										this.cells[i+1][j] = new CelluleImplem();
//										this.cells[i+1][j].init( i+1, j , Cell.WLL);
//										
//										continue;
//									}else {
//										this.cells[i][j] = new CelluleImplem();
//										this.cells[i][j].init( i, j , Cell.EMP);
//										
//										continue;
//									}
//								}else {
//									this.cells[i][j] = new CelluleImplem();
//									this.cells[i][j].init( i, j , Cell.EMP);
//									continue;
//								}
//							case 3 :
//								if( i>0 && j>0 && i< h-1 && j< w-1 ) {
//									if( contient (i-1, j,Cell.EMP) && contient(i, j-1, Cell.WLL) )
//									{
//										this.cells[i][j] = new CelluleImplem();
//										this.cells[i][j].init( i, j , Cell.DNC);
//										
//										this.cells[i][j+1] = new CelluleImplem( );
//										this.cells[i][j+1].init(i, j , Cell.WLL);
//										
//										this.cells[i+1][j] = new CelluleImplem( );
//										this.cells[i+1][j].init(i, j , Cell.EMP);
//										
//										continue;
//									}else {
//										this.cells[i][j] = new CelluleImplem();
//										this.cells[i][j].init(i, j , Cell.WLL);
//										continue;
//									}
//								}else {
//									this.cells[i][j] = new CelluleImplem();
//									this.cells[i][j].init( i, j , Cell.WLL);
//									continue;
//								}
//							case 4 :
//								if( i>0 && j>0 && i< h-1 && j< w-1 ) {
//									if( contient (i-1, j,Cell.EMP) && contient(i, j-1, Cell.WLL) )
//									{
//										this.cells[i][j] = new CelluleImplem( );
//										this.cells[i][j].init(i, j , Cell.DNO);
//										
//										this.cells[i][j+1] = new CelluleImplem();
//										this.cells[i][j+1].init(i, j , Cell.WLL);
//										
//										this.cells[i+1][j] = new CelluleImplem();
//										this.cells[i+1][j].init( i, j , Cell.EMP);
//										continue;
//									}else {
//										this.cells[i][j] = new CelluleImplem();
//										this.cells[i][j].init(i, j , Cell.WLL);
//										continue;
//									}
//								}else {
//									this.cells[i][j] = new CelluleImplem();
//									this.cells[i][j].init( i, j , Cell.WLL);
//									
//									continue;
//								}
//							}
//
//						}
//					}
//
//				}
//			}
//	}

	@Override
	public CelluleImplem[][] getCells() {
		return this.cells;
	}

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


	@Override
	public void init(int height, int width) {
		// TODO Auto-generated method stub
		
	}

}
