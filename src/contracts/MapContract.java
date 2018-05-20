package contracts;

import decorators.MapDecorator;
import servives.MapService;
import tools.*;

public class MapContract extends MapDecorator {//ok 
	MapService serv;
	
	public MapContract(MapService mapService) {
		super(mapService);
		serv = mapService;
	}


	public void checkInvariant(){
	}


	@Override
	public void init(int w,int h){//ok
		/**
		 * Height(init(h,w)) = h
		 * Width(init(h,w)) = w
		 */

		//pre init(w,h) requires 0 < w and 0 < h
		if( ! ( w > 0 )){
			throw new PreconditionError("la largeur du terrain est <=0");
		}
		if( ! ( h > 0 )){
			throw new PreconditionError("la longueur du terrain est <=0");
		}

		// inv pre
		checkInvariant();
		serv.init(w, h);

		// post
		if( getHeight() != h ){
			throw new InvariantError("la hauteur du terrain n'est pas coherante");
		}
		if( getWidth() != w ){
			throw new InvariantError("la largeur du terrain n'est pas coherante");
		}

		checkInvariant();
	}

	@Override
	public void OpenDoor(int i, int j) {
		int k=0,l = 0;
		checkInvariant();

		//pre OpenDoor(M,x,y) requires CellNature(M,x,y) = DNC || DWC 
		if( ! serv.getCells()[i][j].equals(Cell.DNC) || ! serv.getCells()[i][j].equals(Cell.DWC) ) 
			throw new PreconditionError("ce n'est pas une porte fermée ! ");

		Cell cellNaturePre = serv.getCells()[i][j].getNature();

		Cell [][] cellNaturePres = new Cell [getHeight()][getHeight()];
		for( k = 0; k < getHeight() ; k++)
			for( l = 0 ; l < getWidth() ; l++ ){
				cellNaturePres[k][l] = serv.getCells()[k][l].getNature();
			}


		serv.OpenDoor(i, j);
		/*	CellNature(M,x,y) = DWC implies CellNature(OpenDoor(M,x,y),x,y) = DWO
		CellNature(M,x,y) = DNC implies CellNature(OpenDoor(M,x,y),x,y) = DNO
		forall u dans [0; Width(M)-1] forall v dans [0; Height(M)-1] (u != x or v != y)
		implies CellNature(OpenDoor(M,x,y),u,v) = CellNature(M,u,v)*/

		if( cellNaturePre == Cell.DWC && !serv.getCells()[i][j].getNature().equals(Cell.DWO) ){
			throw new PostConditionError("la porte n'a pas pu être ouverte ");
		}

		if( cellNaturePre == Cell.DNC && !serv.getCells()[i][j].getNature().equals(Cell.DNO)){
			throw new PostConditionError("la porte n'a pas pu être ouverte ");
		}

		boolean bool = true ;
		for( k = 0; k < getHeight() ; k++)
			for( l = 0 ; l < getWidth() ; l++ )
				if( k != i && l != j){
					if( !serv.getCells()[k][l].getNature().equals(cellNaturePres[k][l]) ){
						bool = false ;
						break;
					}
				}

		if( ! bool )
			throw new PostConditionError("incoherance dans l'état la case "+k+" "+l); 

		checkInvariant();

	}//ok

	@Override
	public void CloseDoor(int i, int j) {
		int k=0,l = 0;
		checkInvariant();
		//pre OpenDoor(M,x,y) requires CellNature(M,x,y) = DNC || DWC 
		if( ! (serv.getCells()[i][j].getNature().equals(Cell.DNO) || (serv.getCells()[i][j].getNature().equals(Cell.DWO) )))
			throw new PreconditionError("ce n'est pas une porte ouverte ! ");

		Cell cellNaturePre = serv.getCells()[i][j].getNature();

		Cell [][] cellNaturePres = new Cell [getHeight()][getHeight()];
		for( k = 0; k < getHeight() ; k++)
			for( l = 0 ; l < getWidth() ; l++ ){
				cellNaturePres[k][l] = serv.getCells()[k][l].getNature();
			}


		serv.CloseDoor(i, j);
		/*	CellNature(M,x,y) = DWC implies CellNature(CloseDoor(M,x,y),x,y) = DWC
		CellNature(M,x,y) = DNO implies CellNature(CloseDoor(M,x,y),x,y) = DNC
		forall u dans [0; Width(M)-1] forall v dans [0; Height(M)-1] (u != x or v != y)
		implies CellNature(CloseDoor(M,x,y),u,v) = CellNature(M,u,v)*/

		if(cellNaturePre == Cell.DWO && !serv.getCells()[i][j].getNature().equals(Cell.DWC) ){
			throw new InvariantError("la porte n'a pas pu être fermée ");
		}

		if(cellNaturePre == Cell.DNO && !serv.getCells()[i][j].getNature().equals(Cell.DNC)){
			throw new InvariantError("la porte n'a pas pu être fermée ");
		}

		boolean bool = true ;
		for( k = 0; k < getHeight() ; k++)
			for( l = 0 ; l < getWidth() ; l++ )
				if( k != i && l != j){
					if( !serv.getCells()[k][l].getNature().equals(cellNaturePres[k][l]) ){
						bool = false ;
						break;
					}
				}

		if( ! bool )
			throw new InvariantError("incoherance dans l'état la case "+k+" "+l); 


		checkInvariant();
	}//ok
}
