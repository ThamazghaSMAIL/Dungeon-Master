package contracts;

import decorators.EnvironnementDecorator;
import servives.EnvironnementService;
import tools.Cell;
import tools.InvariantError;
import tools.OptionEnum;
import tools.PreconditionError;

public class EnvironnementContract extends EnvironnementDecorator implements EnvironnementService{//include Map
	EnvironnementService serv;

	public EnvironnementContract(EnvironnementService serv) {
		super(serv);
		this.serv = serv;
	}


	public void checkInvariant(){
	}

	@Override
	public void init( int height, int width){//ok
		/**
		 * Height(init(h,w)) = h
		 * Width(init(h,w)) = w
		 */

		//pre init(w,h) requires 0 < w and 0 < h
		if( ! ( width > 0 )){
			throw new PreconditionError("la largeur du terrain est <=0");
		}
		if( ! (height > 0 )){
			throw new PreconditionError("la longueur du terrain est <=0");
		}

		// inv pre
		checkInvariant();
		super.init(height, width);

		// post
		if( this.getHeight() != height ){
			throw new InvariantError("la hauteur du terrain n'est pas coherante");
		}
		if( this.getWidth() != height ){
			throw new InvariantError("la largeur du terrain n'est pas coherante");
		}

		checkInvariant();
	}

	@Override
	public void OpenDoor(int i, int j) {
		int k=0,l = 0;
		checkInvariant();

		//pre OpenDoor(M,x,y) requires CellNature(M,x,y) = DNC || DWC 
		if( ! serv.getCells()[i][j].getNature().equals(Cell.DNC) || serv.getCells()[i][j].getNature().equals(Cell.DWC) ) 
			throw new PreconditionError("ce n'est pas une porte ouverte ! ");

		Cell cellNaturePre = serv.getCells()[i][j].getNature();

		Cell [][] cellNaturePres = new Cell [getHeight()][getHeight()];
		for( k = 0; k < getHeight() ; k++)
			for( l = 0 ; l < getWidth() ; l++ ){
				cellNaturePres[k][l] = serv.getCells()[k][l].getNature();
			}


		super.OpenDoor(i, j);
		/*	CellNature(M,x,y) = DWC implies CellNature(OpenDoor(M,x,y),x,y) = DWO
		CellNature(M,x,y) = DNC implies CellNature(OpenDoor(M,x,y),x,y) = DNO
		forall u dans [0; Width(M)-1] forall v dans [0; Height(M)-1] (u != x or v != y)
		implies CellNature(OpenDoor(M,x,y),u,v) = CellNature(M,u,v)*/

		if( cellNaturePre == Cell.DWC && !serv.getCells()[i][j].getNature().equals(Cell.DWO )){
			throw new InvariantError("la porte n'a pas pu être ouverte ");
		}

		if( cellNaturePre == Cell.DNC && !serv.getCells()[i][j].getNature().equals(Cell.DNO ) ){
			throw new InvariantError("la porte n'a pas pu être ouverte ");
		}

		boolean bool = true ;
		for( k = 0; k < getHeight() ; k++)
			for( l = 0 ; l < getWidth() ; l++ )
				if( k != i && l != j){
					if( serv.getCells()[k][l].getNature().equals(cellNaturePres[k][l]) ){
						bool = false ;
						break;
					}
				}

		if( ! bool )
			throw new InvariantError("incoherance dans l'état la case "+k+" "+l); 

		checkInvariant();

	}//ok

	@Override
	public void CloseDoor(int i, int j) {
		int k=0,l = 0;
		checkInvariant();
		//pre OpenDoor(M,x,y) requires CellNature(M,x,y) = DNC || DWC 
		if( !serv.getCells()[i][j].getNature().equals(Cell.DNO) || !serv.getCells()[i][j].getNature().equals(Cell.DWO) )
			throw new PreconditionError("ce n'est pas une porte ouverte ! ");

		//pre CloseDoor(M,x,y) requires CellContent(M,x,y) = N
		if( ! serv.getCells()[k][l].getContent().equals(OptionEnum.No)) 
			throw new PreconditionError("cette porte ne peut pas être fermée ! il y a un Mob sur place ");

		Cell cellNaturePre = serv.getCells()[i][j].getNature();

		Cell [][] cellNaturePres = new Cell [getHeight()][getHeight()];
		for( k = 0; k < getHeight() ; k++)
			for( l = 0 ; l < getWidth() ; l++ ){
				cellNaturePres[k][l] = serv.getCells()[k][l].getNature();
			}


		this.CloseDoor(i, j);
		/*	CellNature(M,x,y) = DWC implies CellNature(CloseDoor(M,x,y),x,y) = DWC
		CellNature(M,x,y) = DNO implies CellNature(CloseDoor(M,x,y),x,y) = DNC
		forall u dans [0; Width(M)-1] forall v dans [0; Height(M)-1] (u != x or v != y)
		implies CellNature(CloseDoor(M,x,y),u,v) = CellNature(M,u,v)*/

		if(cellNaturePre == Cell.DWO && !serv.getCells()[i][j].getNature().equals(Cell.DWC) ){
			throw new InvariantError("la porte n'a pas pu être fermée ");
		}

		if(cellNaturePre == Cell.DNO && !serv.getCells()[i][j].getNature().equals(Cell.DNC) ){
			throw new InvariantError("la porte n'a pas pu être fermée ");
		}

		boolean bool = true ;
		for( k = 0; k < getHeight() ; k++)
			for( l = 0 ; l < getWidth() ; l++ )
				if( k != i && l != j){
					if( !serv.getCells()[k][l].getNature().equals(cellNaturePres[k][l])){
						bool = false ;
						break;
					}
				}

		if( ! bool )
			throw new InvariantError("incoherance dans l'état la case "+k+" "+l); 


		checkInvariant();
	}//ok

	
}
