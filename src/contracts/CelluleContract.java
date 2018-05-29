package contracts;

import decorators.CelluleDecorator;
import implm.CelluleImplem;
import servives.CelluleService;
import tools.Cell;
import tools.Face;
import tools.InvariantError;
import tools.OptionEnum;
import tools.PostConditionError;

public class CelluleContract extends CelluleDecorator{
	CelluleService serv;
	
	public CelluleContract(CelluleService serv) {
		super();
		this.serv = serv;
	}
	public void checkInvariant(){
		
	}
	@Override
	public void setNature(Cell c) {
		/*	[SetNature]
				CellNature(SetNature(M,x,y,Na),x,y) = Na
				forall u,v in int*int , u ! = x or v ! = y implies CellNature(SetNature(M,x,y),u,v) = CellNature(M,u,v)*/
		Cell cPre = super.getNature(); 
		Cell[][] cellPre = new Cell[super.getEnv().getHeight()][super.getEnv().getWidth()] ;

		for (int i1 = 0; i1 <super.getEnv().getHeight(); i1++ ) 
			for (int j1 = 0; j1 < super.getEnv().getWidth() ; j1++ ) {
				cellPre[i1][j1] = super.getEnv().getCells()[i1][j1].getNature();
			}
		
		checkInvariant();
		super.setNature(c);
		checkInvariant();
		
		if ( super.getNature().equals(c) ) {
			throw new PostConditionError(" erreur de changement de nature -CelluleContrat");
		}

		for (int i1 = 0; i1 <super.getEnv().getHeight(); i1++ ) 
			for (int j1 = 0; j1 < super.getEnv().getWidth() ; j1++ ) {
				if( ! super.getEnv().getCells()[i1][j1].getNature().equals(cellPre[i1][j1]) ) {
					throw new PostConditionError(" erreurs état incoherent -CelluleContrat");
				}
			}
	}

}
	
	
//	@Override
//	public OptionEnum getContent() {
//		//pre Content(P,x,y) requires x ∈ {-1,0,1}and y ∈ {-1,+3}
//		checkInvariant();
//		if( u != -1 && u != 0 && u != 1 )
//			throw new InvariantError(" content error ! ");
//
//		if( v < -1 && v > 3 )
//			throw new InvariantError(" content error ! ");
//
//		/*****N******/
//		/**
//		 * Face(P) = N implies Content(P,u,v) = Environment:CellContent(Envi(P),Col(P)+u,Row(P)+v)
//		 */
//
//		if( this.getFace().equals(Face.N) && 
//				! this.getContent(u, v).equals(this.getEnv().getCellContent(this.getRow()+u, this.getCol()+v)) ) {
//			throw new InvariantError(" le contenu de la case est incoherant par rapport à l'env");
//		}
//
//		/**
//		 * Face(P) = N implies Nature(P,u,v) = Environment:CellNature(Envi(P),Col(P)+u,Row(P)+v)
//		 */
//		if( this.getFace().equals(Face.N) && 
//				! this.getNature(u, v).equals(this.getEnv().getCellNature(this.getRow()+u, this.getCol()+v)) ) {
//			throw new InvariantError(" le contenu de la case est incoherant par rapport à l'env");
//		}
//		
//		/*****S******/
//		/**
//		 * Face(P) = S implies Content(P,u,v) = Environment:CellContent(Envi(P),Col(P)-u,Row(P)-v)
//		 */
//
//		if( this.getFace().equals(Face.S) && 
//				! this.getContent(u, v).equals(this.getEnv().getCellContent(this.getRow()-u, this.getCol()-v)) ) {
//			throw new InvariantError(" le contenu de la case est incoherant par rapport à l'env");
//		}
//
//		/**
//		 * Face(P) = S implies Nature(P,u,v) = Environment:CellNature(Envi(P),Col(P)-u,Row(P)-v)
//		 */
//		if( this.getFace().equals(Face.S) && 
//				! this.getNature(u, v).equals(this.getEnv().getCellNature(this.getRow()-u, this.getCol()-v)) ) {
//			throw new InvariantError(" le contenu de la case est incoherant par rapport à l'env");
//		}
//		
//		/*****E******/
//		/**
//		 * Face(P) = E implies Content(P,u,v) = Environment:CellContent(Envi(P),Col(P)+v,Row(P)-u)
//		 */
//
//		if( this.getFace().equals(Face.E) && 
//				! this.getContent(u, v).equals(this.getEnv().getCellContent(this.getRow()-u, this.getCol()+v)) ) {
//			throw new InvariantError(" le contenu de la case est incoherant par rapport à l'env");
//		}
//
//		/**
//		 * Face(P) = E implies Nature(P,u,v) = Environment:CellNature(Envi(P),Col(P)+v,Row(P)-u)
//		 */
//		if( this.getFace().equals(Face.E) && 
//				! this.getNature(u, v).equals(this.getEnv().getCellNature(this.getRow()-u, this.getCol()+v)) ) {
//			throw new InvariantError(" le contenu de la case est incoherant par rapport à l'env");
//		}
//		
//		/*****W******/
//		/**
//		 * Face(P) = W implies Content(P,u,v) = Environment:CellContent(Envi(P),Col(P)-v,Row(P)+u)
//		 */
//
//		if( this.getFace().equals(Face.W) && 
//				! this.getContent(u, v).equals(this.getEnv().getCellContent(this.getRow()+u, this.getCol()-v)) ) {
//			throw new InvariantError(" le contenu de la case est incoherant par rapport à l'env");
//		}
//
//		/**
//		 * Face(P) = W implies Nature(P,u,v) = Environment:CellNature(Envi(P),Col(P)-v,Row(P)+u)
//		 */
//		if( this.getFace().equals(Face.W) && 
//				! this.getNature(u, v).equals(this.getEnv().getCellNature(this.getRow()+u, this.getCol()-v)) ) {
//			throw new InvariantError(" le contenu de la case est incoherant par rapport à l'env");
//		}
//		return super.getContent(u,v);
//	}

