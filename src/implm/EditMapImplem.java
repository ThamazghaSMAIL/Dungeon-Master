package implm;

import servives.EditMapService;
import tools.Cell;
import tools.Cellule;

public class EditMapImplem extends MapImplem implements EditMapService{
	public EditMapImplem() {
	}

	@Override
	public boolean isReachable(int x1, int y1, int x2, int y2) {

		boolean bool = true ;
		int i = x1 , j = y1 ;

		while ( i != x2 && j != y2) {

			//isReachable(M,x1,y1,x2,y2) = exists P in Array[int,int], P[0] = (x1,y1) and P[size(P)-1] = (x2,y2)
			//and forall i in [1;size(P)-1], (P[i-1]=(u,v) and P[i]=(s,t)) implies (u−s) 2 + (v−t) 2 = 1
			//and forall i in [1;size(P)-2], P[i-1]=(u,v) implies CellNature(M,u,v) != WLL
			if( (this.getCells())[x1+1][x2] != null )
				if(!((this.getCells())[x1+1][x2].getNature().equals(Cell.WLL))){
					return isReachable(x1+1, y1, x2, y2);
				}else {
					if( (this.getCells())[x1-1][x2] != null )
						if(!((this.getCells())[x1-1][x2].getNature().equals(Cell.WLL))){
							return isReachable(x1-1, y1, x2, y2);
						}else {
							if( (this.getCells())[x1][x2+1] != null )
								if(!((this.getCells())[x1][x2+1].getNature().equals(Cell.WLL))){
									return isReachable(x1, y1, x2+1, y2);
								}else {
									if( (this.getCells())[x1][x2-1] != null )
										if(!((this.getCells())[x1][x2-1].getNature().equals(Cell.WLL))){
											return isReachable(x1, y1, x2-1, y2);
										}else {
											return false ;
										}
								}
						}
				}

		}
		return true;
	}

	@Override
	public boolean isReady() {
		boolean bool = false ;
		Cellule cin = null , cout =null ;
		boolean in = false , out = false ;
		for( int i = 0 ; i < getHeight() ; i++ ) {
			for( int j = 0 ; j < getHeight() ; j++ ) {
				if( this.getCellNature(i, j) == Cell.IN ) {
					in = true ;
					cin = getCell(i, j);
				}

				if( this.getCellNature(i, j) == Cell.OUT ) {
					cout = getCell(i, j);
					out = true ;
				}

			}
		}



		for( int i = 0 ; i < getHeight() ; i++ ) {
			for( int j = 0 ; j < getWidth() ; j++ ) {
				if( ( i != cin.getI() || j != cin.getJ()) )
					if( getCellNature(i, j) == Cell.IN ) {
						bool = false ;
					}

				if( ( i != cout.getI() || j != cout.getJ() ))
					if( getCellNature(i, j )== Cell.OUT ) {
						bool = false ;
					}
			}
		}

		for( int i = 0 ; i < getHeight() ; i++ ) {
			for( int j = 0 ; j < getWidth() ; j++ ) {
				if( this.getCellNature(i, j) == Cell.DNO || this.getCellNature(i, j) == Cell.DNC  ) {
					if( getCellNature( i+1 , j) != Cell.EMP || getCellNature( i-1 , j) != Cell.EMP || getCellNature( i , j-1) != Cell.WLL 
							|| getCellNature( i , j+1) != Cell.WLL ) {
						bool = false ;
					}
				}
				if( this.getCellNature(i, j) == Cell.DWO || this.getCellNature(i, j) == Cell.DWC  ) {
					if( getCellNature( i+1 , j) != Cell.WLL || getCellNature( i-1 , j) != Cell.WLL || getCellNature( i , j-1) != Cell.EMP 
							|| getCellNature( i , j+1) != Cell.EMP ) {
						bool = false;
					}
				}

			}
		}


		if( ( in && out && bool && this.isReachable(cin.getI(), cin.getJ(), cout.getI() , cout.getJ() ))) {
			return true ;
		}else
			return false;
	}

	@Override
	public void setNature(int i, int j, Cell c) {
		this.cells[i][j].setNature(c);
	}

	@Override
	public void init (int height, int width) {
		super.init(height, width);
	}
}
