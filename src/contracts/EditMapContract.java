package contracts;

import decorators.MapDecorator;
import servives.EditMapService;
import tools.Cell;
import tools.Cellule;
import tools.InvariantError;

public class EditMapContract extends MapDecorator implements EditMapService{

	public EditMapContract(EditMapService delegate) {
		super(delegate);
	}

	public void checkInvariant(){
		/** pas fait
		 * isReachable(M,x1,y1,x2,y2) = exists P in Array[int,int], P[0] = (x1,y1) and P[size(P)-1] = (x2,y2)
		and forall i in [1;size(P)-1], (P[i-1]=(u,v) and P[i]=(s,t)) implies (u−s) 2 + (v−t) 2 = 1
		and forall i in [1;size(P)-2], P[i-1]=(u,v) implies CellNature(M,u,v) 6 = WLL
		 */
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

		if( isReady() && ( !in || !out ) ) {
			throw new InvariantError("y a pas d'entrée/sortie ");
		}
		if( cin != null && cout != null ) {
			if( ! this.isReachable(cin.getI(), cin.getJ() , cout.getI() , cout.getJ() ))
				throw new InvariantError("la sortie n'est pas accessible a partir de l'entrée  ");
		}


		for( int i = 0 ; i < getHeight() ; i++ ) {
			for( int j = 0 ; j < getWidth() ; j++ ) {
				if( ( i != cin.getI() || j != cin.getJ() ) && getCellNature(i, j) == Cell.IN ) {
					throw new InvariantError("y a plus d'une entrée  ");
				}

				if( ( i != cout.getI() || j != cout.getJ() ) && getCellNature(i, j )== Cell.OUT ) {
					throw new InvariantError("y a plus d'une sortie  ");
				}
			}
		}



		for( int i = 0 ; i < getHeight() ; i++ ) {
			for( int j = 0 ; j < getWidth() ; j++ ) {
				if( this.getCellNature(i, j) == Cell.DNO || this.getCellNature(i, j) == Cell.DNC  ) {
					//CellNature(M,x+1,y) = CellNature(M,x-1,y) = EMP and
					//CellNature(M,x,y-1) = CellNature(M,x,y+1) = WLL
					if( getCellNature( i+1 , j) != Cell.EMP || getCellNature( i-1 , j) != Cell.EMP || getCellNature( i , j-1) != Cell.WLL 
							|| getCellNature( i , j+1) != Cell.WLL ) {
						throw new InvariantError("erreurs case voisines des portes Nord");
					}
				}
				if( this.getCellNature(i, j) == Cell.DWO || this.getCellNature(i, j) == Cell.DWC  ) {
					//CellNature(M,x+1,y) = CellNature(M,x-1,y) = WLL and
					//CellNature(M,x,y-1) = CellNature(M,x,y+1) = EMP
					if( getCellNature( i+1 , j) != Cell.WLL || getCellNature( i-1 , j) != Cell.WLL || getCellNature( i , j-1) != Cell.EMP 
							|| getCellNature( i , j+1) != Cell.EMP ) {
						throw new InvariantError("erreurs case voisines des portes W");
					}
				}
			}
		}
	}

	@Override
	public boolean isReachable(int x1, int y1, int x2, int y2) {
		return true;
	}

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public void setNature(int i, int j , Cell c) {
		/*	[SetNature]
				CellNature(SetNature(M,x,y,Na),x,y) = Na
				forall u,v in int 2 , u 6 = x or v 6 = y implies CellNature(SetNature(M,x,y),u,v) = CellNature(M,u,v)*/
		Cell cPre = getCellNature(i, j); 
		Cellule[][] cellPre = null ;

		for (int i1 = 0; i1 <getHeight(); i1++ ) 
			for (int j1 = 0; j1 < getWidth() ; j1++ ) {
				cellPre[i1][j1] = getCells()[i1][j1];
			}

		this.setNature(i, j, c);


		if ( getCellNature(i, j) != c) {
			throw new InvariantError(" erreur de changement de nature ");
		}

		for (int i1 = 0; i1 <getHeight(); i1++ ) 
			for (int j1 = 0; j1 < getWidth() ; j1++ ) {
				if( getCells()[i1][j1].getNature() != cellPre[i1][j1].getNature() ) {
					throw new InvariantError(" erreurs état incoherent ");
				}
			}

	}



}
