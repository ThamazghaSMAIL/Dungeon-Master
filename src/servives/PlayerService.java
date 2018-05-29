package servives;

import tools.Cell;
import tools.Commande;
import tools.Face;

public interface PlayerService extends EntityService {
	
	/**
	 * post : isEnVie(init (i,j,hp,face,env)) = true
	 * getHp(init (i,j,hp,face,env)) = 3
	 * @param row
	 * @param col
	 * @param hp
	 * @param face
	 * @param env
	 */
	public void init(int row, int col, int hp , Face face , EnvironnementService env);
	
	/**
	 * post: getLastCom() = lastCommande
	 * @param lastCommande
	 */
	public void setLastCommande(Commande lastCommande);

	public Commande getLastCommande();
	
	/**
	 * pre : Nature(P,x,y) requires x ∈ {-1,0,1} and y ∈ {-1,+3}
	 * post :
	 * forall u,v in [-1,1] × [-1,1], not Viewable(P,u,v)
	 * Viewable(P,-1,2) = Nature(P,-1,1) ∈{WALL, DWC, DNC }
	 * Viewable(P,0,2) = Nature(P,0,1) ∈ {WALL, DWC, DNC }
	 * Viewable(P,1,2) = Nature(P,1,1) ∈ {WALL, DWC, DNC }
	 * Viewable(P,-1,3) = Nature(P,-1,2) ∈{WALL, DWC, DNC } and Viewable(P,-1,2)
	 * Viewable(P,0,3) = Nature(P,0,2) ∈ {WALL, DWC, DNC } and Viewable(P,0,2)
	 * Viewable(P,1,3) = Nature(P,1,2) ∈ {WALL, DWC, DNC } and Viewable(P,1,2)
	 * @param row
	 * @param col
	 * @return
	 */
	boolean Viewable(int row, int col);
	
	/**
	 * post :
	 * LastCom(P)=FF implies step(P) = Forward(P)
	 * LastCom(P)=BB implies step(P) = Backward(P)
	 * LastCom(P)=LL implies step(P) = StrafeLeft(P)
	 * LastCom(P)=RR implies step(P) = StrafeRight(P)
	 * LastCom(P)=TL implies step(P) = TurnLeft(P)
	 * LastCom(P)=TR implies step(P) = TurnRight(P)
	 * @return
	 */
	public void step();
	
	public boolean getTresorFound();
	
	public boolean getClefFound();
	
	/**
	 * pre NatureP(x,y) requires x ∈ {-1,0,1}and y ∈ {-1,+3}
	 * @param i
	 * @param j
	 * @return
	 */
	public Cell getNatureP(int i , int j);
	/**
	 * pre ContentP(P,x,y) requires x ∈ {-1,0,1}and y ∈ {-1,+3}
	 * @param i
	 * @param j
	 * @return
	 */
	public Cell getContentP(int i , int j);
	
	
}
