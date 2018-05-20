package contracts;

import implm.CelluleImplem;
import servives.EditMapService;
import tools.Cell;
import tools.InvariantError;
import tools.PreconditionError;

public class EditMapContract extends MapContract implements EditMapService{
	EditMapService serv;
	public EditMapContract(EditMapService delegate) {
		super(delegate);
	}

	public void checkInvariant(){
		super.checkInvariant();
		/** pas fait
		 * isReachable(M,x1,y1,x2,y2) = exists P in Array[int,int], P[0] = (x1,y1) and P[size(P)-1] = (x2,y2)
		and forall i in [1;size(P)-1], (P[i-1]=(u,v) and P[i]=(s,t)) implies (u−s) 2 + (v−t) 2 = 1
		and forall i in [1;size(P)-2], P[i-1]=(u,v) implies CellNature(M,u,v) 6 = WLL
		 */

		CelluleImplem cin = null , cout =null ;
		boolean in = false , out = false ;
		for( int i = 0 ; i < serv.getHeight() ; i++ ) {
			for( int j = 0 ; j < serv.getHeight() ; j++ ) {
				if( serv.getCells()[i][j].getNature().equals( Cell.IN) ) {
					in = true ;
					cin = serv.getCells()[i][j];
				}

				if( serv.getCells()[i][j].getNature().equals( Cell.OUT)  ) {
					out = true ;
					cout = this.getCells()[i][j];
				}

			}
		}

		if( serv.isReady() && ( !in || !out || ! serv.isReachable(cin.getI(), cin.getJ() , cout.getI() , cout.getJ())) ) {
			throw new InvariantError("erreur invariant EditMap");
		}

		for( int i = 0 ; i < serv.getHeight() ; i++ ) {
			for( int j = 0 ; j < serv.getWidth() ; j++ ) {
				if( ( i != cin.getI() || j != cin.getJ() ) && serv.getCells()[i][j].getNature().equals(Cell.IN) ) {
					throw new InvariantError("y a plus d'une entrée  ");
				}
				if( ( i != cout.getI() || j != cout.getJ() ) && serv.getCells()[i][j].getNature().equals(Cell.OUT) ) {
					throw new InvariantError("y a plus d'une sortie  ");
				}
			}
		}



		for( int i = 0 ; i < getHeight() ; i++ ) {
			for( int j = 0 ; j < getWidth() ; j++ ) {
				if( serv.getCells()[i][j].getNature().equals(Cell.DNO) || serv.getCells()[i][j].getNature().equals(Cell.DNC)) {
					//CellNature(M,x+1,y) = CellNature(M,x-1,y) = EMP and
					//CellNature(M,x,y-1) = CellNature(M,x,y+1) = WLL
					if( serv.getCells()[i+1][j].getNature().equals(Cell.EMP) || serv.getCells()[i-1][j].getNature().equals(Cell.EMP) ||
							serv.getCells()[i][j-1].getNature().equals(Cell.WLL) || serv.getCells()[i][j+1].getNature().equals(Cell.WLL)) {
						throw new InvariantError("erreurs case voisines des portes Nord");
					}
				}
				if( serv.getCells()[i][j].getNature().equals(Cell.DWO) || serv.getCells()[i][j].getNature().equals(Cell.DWC) ) {
					//CellNature(M,x+1,y) = CellNature(M,x-1,y) = WLL and
					//CellNature(M,x,y-1) = CellNature(M,x,y+1) = EMP
					if(serv.getCells()[i+1][j].getNature().equals(Cell.WLL) || serv.getCells()[i-1][j].getNature().equals(Cell.WLL) ||
							serv.getCells()[i][j-1].getNature().equals(Cell.EMP) || serv.getCells()[i][j+1].getNature().equals(Cell.EMP)) {
						throw new InvariantError("erreurs case voisines des portes W");
					}
				}
			}
		}
	}


	

	@Override
	public boolean isReachable(int x1, int y1, int x2, int y2) {
		/* pre isReachable(M,x1,y1,x2,y2) requires CellNature(M,x1,y1) != WLL
		and CellNature(M,x2,y2) != WLL*/
		
		if( serv.getCells()[x1][y1].getNature().equals(Cell.WLL) || serv.getCells()[x2][y2].getNature().equals(Cell.WLL) )
			throw new PreconditionError("error isReachable");
		
		checkInvariant();
		Boolean result = serv.isReachable(x1, y1, x2, y2);
		checkInvariant();

		return result;
	}

	@Override
	public boolean isReady() {
		checkInvariant();
		Boolean result = serv.isReady();
		checkInvariant();
		return result;
	}

}
