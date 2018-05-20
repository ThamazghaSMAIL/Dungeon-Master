package implm;

import servives.EditMapService;
import tools.Cell;

import java.util.ArrayList;
import java.util.List;

import implm.CelluleImplem;

public class EditMapImplem extends MapImplem implements EditMapService{
	public EditMapImplem() {
	}

	//isReachable(M,x1,y1,x2,y2) = exists P in Array[int,int], P[0] = (x1,y1) and P[size(P)-1] = (x2,y2)
	//and forall i in [1;size(P)-1], (P[i-1]=(u,v) and P[i]=(s,t)) implies (u−s) 2 + (v−t) 2 = 1
	//and forall i in [1;size(P)-2], P[i-1]=(u,v) implies CellNature(M,u,v) != WLL

	
	@Override
	public boolean isReady() {
		boolean bool = false ;
		CelluleImplem cin = null , cout =null ;
		boolean in = false , out = false ;
		for( int i = 0 ; i < getHeight() ; i++ ) {
			for( int j = 0 ; j < getHeight() ; j++ ) {
				if( this.getCells()[i][j].getNature().equals(Cell.IN) ) {
					in = true ;
					cin = getCell(i, j);
				}

				if( this.getCells()[i][j].getNature().equals(Cell.OUT) ) {
					cout = getCell(i, j);
					out = true ;
				}

			}
		}



		for( int i = 0 ; i < getHeight() ; i++ ) {
			for( int j = 0 ; j < getWidth() ; j++ ) {
				if( ( i != cin.getI() || j != cin.getJ()) )
					if(  this.getCells()[i][j].getNature().equals(Cell.IN) ) {
						bool = false ;
					}

				if( ( i != cout.getI() || j != cout.getJ() ))
					if(  this.getCells()[i][j].getNature().equals(Cell.OUT) ) {
						bool = false ;
					}
			}
		}

		for( int i = 0 ; i < getHeight() ; i++ ) {
			for( int j = 0 ; j < getWidth() ; j++ ) {
				if(  this.getCells()[i][j].getNature().equals(Cell.DNO) || this.getCells()[i][j].getNature().equals(Cell.DNC ) ) {
					if( !this.cells[i+1][j].getNature().equals(Cell.EMP) || !this.cells[i-1][j].getNature().equals(Cell.EMP) || 
							!this.cells[i][j-1].getNature().equals(Cell.WLL) || ! this.cells[i][j+1].getNature().equals(Cell.WLL) ) {
						bool = false ;
					}
				}
				if(  this.getCells()[i][j].getNature().equals(Cell.DWO) || this.getCells()[i][j].getNature().equals(Cell.DWC ) ) {
					if( !this.cells[i+1][j].getNature().equals(Cell.WLL) || !this.cells[i-1][j].getNature().equals(Cell.WLL) ||
							!this.cells[i][j-1].getNature().equals(Cell.EMP) || ! this.cells[i][j+1].getNature().equals(Cell.EMP) ) {
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
	public void init (int height, int width) {
		super.init(height, width);
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
