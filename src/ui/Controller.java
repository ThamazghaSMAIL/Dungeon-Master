package ui;
import implm.EngineImplem;
import implm.PlayerImplem;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import servives.CombatService;
import servives.CowService;
import servives.EntityService;
import servives.EnvironnementService;
import tools.Cell;
import implm.CelluleImplem;
import tools.Commande;
import tools.Face;
import tools.OptionEnum;
import tools.OptionFood;

public class Controller extends Application {
	Rectangle[][] cases;
	StackPane[][] stacks;
	CelluleImplem[][] cells;
	EnvironnementService env;
	PlayerImplem player;
	EngineImplem moteurJeuImplem;

	Label labelGagne , force , cleflabel;
	final int tailleCase = 80;
	HBox entete;
	javafx.scene.image.Image gazon = new javafx.scene.image.Image(getClass().getResource("./images/gazon.jpg").toExternalForm());
	javafx.scene.image.Image donut = new javafx.scene.image.Image(getClass().getResource("./images/donut.png").toExternalForm());
	javafx.scene.image.Image clef = new javafx.scene.image.Image(getClass().getResource("./images/clef.png").toExternalForm());
	javafx.scene.image.Image tresor = new javafx.scene.image.Image(getClass().getResource("./images/tresor.png").toExternalForm());
	javafx.scene.image.Image mur= new javafx.scene.image.Image(getClass().getResource("./images/mur.png").toExternalForm());
	javafx.scene.image.Image porteOuverte = new javafx.scene.image.Image(getClass().getResource("./images/porteOuverte.png").toExternalForm());
	javafx.scene.image.Image porteFermee = new javafx.scene.image.Image(getClass().getResource("./images/porteFermee.png").toExternalForm());
	javafx.scene.image.Image playerN = new javafx.scene.image.Image(getClass().getResource("./images/joueurNord.png").toExternalForm());
	javafx.scene.image.Image playerW = new javafx.scene.image.Image(getClass().getResource("./images/joueurWest.png").toExternalForm());
	javafx.scene.image.Image playerE = new javafx.scene.image.Image(getClass().getResource("./images/joueurEst.png").toExternalForm());
	javafx.scene.image.Image playerS = new javafx.scene.image.Image(getClass().getResource("./images/joueurSud.png").toExternalForm());
	javafx.scene.image.Image monstreN = new javafx.scene.image.Image(getClass().getResource("./images/monstreNord.png").toExternalForm());
	javafx.scene.image.Image monstreS = new javafx.scene.image.Image(getClass().getResource("./images/monstreSud.png").toExternalForm());
	javafx.scene.image.Image monstreE = new javafx.scene.image.Image(getClass().getResource("./images/monstreEst.png").toExternalForm());
	javafx.scene.image.Image monstreW = new javafx.scene.image.Image(getClass().getResource("./images/monstreWest.png").toExternalForm());


	public static void main(String[] args) {
		launch(args);//lance la meth start
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		moteurJeuImplem = new EngineImplem();
		moteurJeuImplem.init(env,10,10);
		env = moteurJeuImplem.getEnvi();
		CombatService combat = moteurJeuImplem.getCombat();
		cells = moteurJeuImplem.getEnvi().getCells();
		player = (PlayerImplem) env.getEntities().get(0);

		cases = new Rectangle[100][100];
		stacks = new StackPane[100][100];

		primaryStage.setTitle("Dungeon Master");
		BorderPane borderPaneRoot = new BorderPane();
		Scene scene = new Scene(borderPaneRoot,tailleCase*moteurJeuImplem.getH()+10,tailleCase*moteurJeuImplem.getW()+100);
		primaryStage.setScene(scene);

		entete = new HBox();
		entete.setPadding(new Insets(10));
		entete.setSpacing(20);
		entete.setAlignment(Pos.CENTER);
		labelGagne = new Label("");

		/*****/
		force =  new Label(Integer.toString(player.getHp()));
		cleflabel= new Label("0");

		/*****/
		ImageView f = new ImageView(donut);
		f.setFitWidth(20);
		f.setFitHeight(20);

		ImageView c = new ImageView(clef);
		c.setFitWidth(20);
		c.setFitHeight(20);



		entete.getChildren().addAll(labelGagne,f,force,c,cleflabel);
		borderPaneRoot.setTop(entete);


		HBox footer = new HBox();
		footer.setPadding(new Insets(10));
		footer.setSpacing(20);
		footer.setAlignment(Pos.CENTER);
		Button recommencer = new Button("Recommencer");
		Button quitter = new Button("Quitter");
		footer.getChildren().addAll(recommencer,quitter);



		final TilePane tilePane = new TilePane(); 
		tilePane.setVgap(1/2);
		tilePane.setHgap(1/2);

		/**________________________________________________________________________________________________________________________________________*/


		for( int i=0 ; i < env.getHeight() ; i++ )
			for ( int j = 0 ; j< env.getWidth() ; j++ ) {
				if( cells[i][j].getNature().equals(Cell.IN)) {
					ImageView g = new ImageView(gazon);
					g.setFitWidth(tailleCase);
					g.setFitHeight(tailleCase);

					ImageView po = new ImageView(porteOuverte);
					po.setFitWidth(tailleCase);
					po.setFitHeight(tailleCase);

					stacks[i][j] = new StackPane(g,po); 
					tilePane.getChildren().add(stacks[i][j]); 
					continue;
				}

				if(cells[i][j].getNature().equals(Cell.OUT)) {
					ImageView g = new ImageView(gazon);
					g.setFitWidth(tailleCase);
					g.setFitHeight(tailleCase);

					ImageView po = new ImageView(porteOuverte);
					po.setFitWidth(tailleCase);
					po.setFitHeight(tailleCase);
					stacks[i][j] = new StackPane(g,po); 

					tilePane.getChildren().add(stacks[i][j]); 
					continue;
				}

				if( cells[i][j].getNature().equals(Cell.EMP)) {

					ImageView g = new ImageView(gazon);
					g.setFitWidth(tailleCase);
					g.setFitHeight(tailleCase);

					cases[i][j]=new Rectangle(tailleCase, tailleCase);	 
					stacks[i][j] = new StackPane(g); 

					tilePane.getChildren().add(stacks[i][j]); 

					if( i == env.getTresor().getI() && j == env.getTresor().getJ() )
					{
						ImageView t = new ImageView(tresor);
						t.setFitWidth(tailleCase);
						t.setFitHeight(tailleCase);

						stacks[i][j].getChildren().add(t); 
					}
					if(i == env.getClef().getI() && j == env.getClef().getJ())
					{
						ImageView cl = new ImageView(clef);
						cl.setFitWidth(tailleCase-40);
						cl.setFitHeight(tailleCase-40);

						stacks[i][j].getChildren().add(cl); 
					}

					if( env.getCells()[i][j].getContent().equals(OptionEnum.So) && !(i == player.getRow() && j== player.getCol()) ) {//on est sur que c'est pas le player
						ImageView m = new ImageView(monstreN);
						stacks[i][j].getChildren().add(m); 
					}



					if( i == player.getRow() && j == player.getCol()) {
						ImageView p = new ImageView(playerN);
						stacks[i][j].getChildren().add(p);
					}
					continue;
				}

				if(cells[i][j].getNature().equals(Cell.DNC)){
					ImageView g = new ImageView(gazon);
					g.setFitWidth(tailleCase);
					g.setFitHeight(tailleCase);

					ImageView pc = new ImageView(porteFermee);
					pc.setFitWidth(tailleCase);
					pc.setFitHeight(tailleCase);

					stacks[i][j] = new StackPane(g,pc); 
					tilePane.getChildren().add(stacks[i][j]); 
					continue;
				}
				if(cells[i][j].getNature().equals(Cell.DNO)){
					ImageView g = new ImageView(gazon);
					g.setFitWidth(tailleCase);
					g.setFitHeight(tailleCase);

					ImageView po = new ImageView(porteOuverte);
					po.setFitWidth(tailleCase);
					po.setFitHeight(tailleCase);

					stacks[i][j] = new StackPane(g,po); 
					tilePane.getChildren().add(stacks[i][j]); 
					continue;
				}

				if( cells[i][j].getNature().equals(Cell.DWC)){
					ImageView g = new ImageView(gazon);
					g.setFitWidth(tailleCase);
					g.setFitHeight(tailleCase);

					ImageView pc = new ImageView(porteFermee);
					pc.setFitWidth(tailleCase);
					pc.setFitHeight(tailleCase);

					stacks[i][j] = new StackPane(g,pc); 
					stacks[i][j].getChildren().get(stacks[i][j].getChildren().size()-1).setRotate(90);
					tilePane.getChildren().add(stacks[i][j]); 
					continue;
				}
				if( cells[i][j].getNature().equals(Cell.DWO)){
					ImageView g = new ImageView(gazon);
					g.setFitWidth(tailleCase);
					g.setFitHeight(tailleCase);

					ImageView po = new ImageView(porteOuverte);
					po.setFitWidth(tailleCase);
					po.setFitHeight(tailleCase);

					stacks[i][j] = new StackPane(g,po); 
					stacks[i][j].getChildren().get(stacks[i][j].getChildren().size()-1).setRotate(90);
					tilePane.getChildren().add(stacks[i][j]); 
					continue;
				}

				if( cells[i][j].getNature().equals(Cell.WLL) ) {
					ImageView mr = new ImageView(mur);
					mr.setFitWidth(tailleCase);
					mr.setFitHeight(tailleCase);

					cases[i][j] = new Rectangle(tailleCase, tailleCase);
					stacks[i][j] = new StackPane(mr); 
					tilePane.getChildren().add(stacks[i][j]); 
				}

			}


		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			Rectangle caseCourante;
			Rectangle caseSuivante;
			StackPane stacksSvte;
			CelluleImplem cellCourante;
			CelluleImplem cellSvte;
			StackPane stacksCourante = stacks[player.getRow()][player.getCol()];
			

			@Override
			public void handle(KeyEvent event) {

				caseCourante = cases[player.getRow()][player.getCol()];
				cellCourante = cells[player.getRow()][player.getCol()];
				stacksCourante = stacks[player.getRow()][player.getCol()];

				if( event.getCode().equals(KeyCode.UP)) {
					player.setLastCommande(Commande.FF);
				}else {
					if( event.getCode().equals(KeyCode.DOWN)) {
						player.setLastCommande(Commande.BB);
					}else {
						if( event.getCode().equals(KeyCode.RIGHT)) {
							player.setLastCommande(Commande.RR);
						}else {

							if( event.getCode().equals(KeyCode.LEFT)) {
								player.setLastCommande(Commande.LL);
							}else {

								if( event.getCode().equals(KeyCode.CONTROL)) {
									player.setLastCommande(Commande.TL);
								}else {

									if( event.getCode().equals(KeyCode.ALT)) {
										player.setLastCommande(Commande.TR);
									}else {
										player.setLastCommande(Commande.NO);
									}
								}
							}
						}
					}
				}

				if( player.getLastCommande().equals(Commande.FF) || player.getLastCommande().equals(Commande.BB)||
						player.getLastCommande().equals(Commande.LL) || player.getLastCommande().equals(Commande.RR)) {
					player.step();

					stacksSvte = stacks[player.getRow()][player.getCol()];
					cellSvte = cells[player.getRow()][player.getCol()];
					caseSuivante = cases[player.getRow()][player.getCol()];

					if( cellSvte.getNature().equals(Cell.DNC) || cellSvte.getNature().equals(Cell.DWC)) {
						if( player.getClefFound()) {
							changements(cellCourante.getI(),cellCourante.getJ(),cellSvte.getI(),cellSvte.getJ());
							player.setLastCommande(null);
						}else {
							
						}
					}else {
						changements(cellCourante.getI(),cellCourante.getJ(),cellSvte.getI(),cellSvte.getJ());
						player.setLastCommande(null);
					}
				}else {
					if(player.getLastCommande().equals(Commande.TL) ) {
						turnLchangements(player.getFace());
						player.setLastCommande(null);
					}else {
						if(player.getLastCommande().equals(Commande.TR)) {
							turnRchangements(player.getFace());
							player.setLastCommande(null);
						}else {
							//le player a frapper dans le vide--> ça n'apporte aucun changement 
						}
					}
				}

				for( EntityService c : env.getEntities() ) {
					if( c instanceof CowService ) 
						if( c.isEnVie() ){
							Node p = stacks[c.getRow()][c.getCol()].getChildren().remove(stacks[c.getRow()][c.getCol()].getChildren().size()-1);
							int ipre = c.getRow() , jpre = c.getCol();
							c.step();
							if( ipre != c.getRow() || jpre != c.getCol()) {
								stacks[c.getRow()][c.getCol()].getChildren().add(p);
							}else {
								Face f = c.getFace();
								if( f.equals(Face.N) ){
									stacks[c.getRow()][c.getCol()].getChildren().add(new ImageView(monstreN));
								}else {
									if( f.equals(Face.S) ){
										stacks[c.getRow()][c.getCol()].getChildren().add(new ImageView(monstreS));
									}else {
										if( f.equals(Face.E) ){
											stacks[c.getRow()][c.getCol()].getChildren().add(new ImageView(monstreE));
										}else {
											if(f.equals(Face.W) ){
												stacks[c.getRow()][c.getCol()].getChildren().add(new ImageView(monstreW));
											}
										}
									}
								}
								if( c != null )
									if( ((CowService) c).getFrappe() ) {
										moteurJeuImplem.getCombat().VachefrappePlayer();
										((CowService) c).setFrappe(false);

										System.out.println("le monstre a frappé");
									}
							}
							System.out.println("proche ? : "+combat.proche(player, c));
							if(combat.proche(player,c) ) {
								moteurJeuImplem.getCombat().setVache(c);
								if( event.getCode().equals(KeyCode.ENTER) ) {
									//attaquer dans sa direction
									System.out.println("le player a frappé");
									changementsEnter(c);
								}
							}
							System.out.println("le monstre est en vie ?"+c.isEnVie());
						}
				}
				force.setText( Integer.toString(player.getHp()) );
				if( player.getClefFound())
					cleflabel.setText( "1" );
			}

			public void changementsEnter(EntityService c) {
				moteurJeuImplem.getCombat().PlayerfrappeMonstre();
				if(  c.isEnVie() == false ) {
					stacks[c.getRow()][c.getCol()].getChildren().remove(stacks[c.getRow()][c.getCol()].getChildren().size()-1);
					ImageView d = new ImageView(donut);
					d.setFitHeight(tailleCase);
					d.setFitWidth(tailleCase);
					stacks[c.getRow()][c.getCol()].getChildren().add(d);
					System.out.println("la pos du donut : x="+c.getRow()+" y = "+c.getCol());
				}
			}

			private void turnRchangements(Face face) {
				stacksCourante.getChildren().remove(stacksCourante.getChildren().size()-1);
				if( face.equals(Face.N) ){
					stacksCourante.getChildren().add(new ImageView(playerE));
				}else {
					if( face.equals(Face.S) ){
						stacksCourante.getChildren().add(new ImageView(playerW));
					}else {
						if( face.equals(Face.E) ){
							stacksCourante.getChildren().add(new ImageView(playerS));
						}else {
							if( face.equals(Face.W) ){
								stacksCourante.getChildren().add(new ImageView(playerN));
							}
						}
					}
					player.turnR();
				}
			}

			private void turnLchangements(Face face) {
				stacksCourante.getChildren().remove(stacksCourante.getChildren().size()-1);
				if( face.equals(Face.N) ){
					stacksCourante.getChildren().add(new ImageView(playerW));
				}else {
					if( face.equals(Face.S) ){
						stacksCourante.getChildren().add(new ImageView(playerE));
					}else {
						if( face.equals(Face.E) ){
							stacksCourante.getChildren().add(new ImageView(playerN));
						}else {
							if( face.equals(Face.W) ){
								stacksCourante.getChildren().add(new ImageView(playerS));
							}
						}
					}
				}
				player.turnL();
			}


		});

		quitter.setOnAction(new EventHandler<ActionEvent>() { 

			@Override 
			public void handle(ActionEvent actionEvent) { 
				primaryStage.close();
			}
		});

		recommencer.setOnAction(new EventHandler<ActionEvent>() { 
			@Override 
			public void handle(ActionEvent actionEvent) { 
				try {
					start(primaryStage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		borderPaneRoot.setBottom(footer);
		borderPaneRoot.setCenter(tilePane);
		primaryStage.setScene(scene); 
		primaryStage.setResizable(false);
		primaryStage.show();

	}



	public void changements(int iAvant, int jAvant , int iApres , int jApres ) {
		ImageView po = new ImageView(porteOuverte);
		po.setFitWidth(tailleCase);
		po.setFitHeight(tailleCase);

		StackPane stacksCourante = stacks[iAvant][jAvant], stacksSvte= stacks[iApres][jApres];
		CelluleImplem cellCourante = cells[iAvant][jAvant],cellSvte= cells[iApres][jApres];
		System.out.println("yaunmob cellsvte"+cellSvte.getContent());
		if( ! cellSvte.getNature().equals(Cell.WLL) && ! env.YaUnMob(cellSvte.getI(), cellSvte.getJ())) {

			if( cellCourante.getNature().equals(Cell.EMP) && cellSvte.getNature().equals(Cell.EMP)) {

				if(env.getClef().getI()==iApres && env.getClef().getJ()==jApres){//si y a une clé la manger 
					stacksSvte.getChildren().remove(stacksSvte.getChildren().size()-1);
				}
				if( iApres == env.getTresor().getI() && jApres == env.getTresor().getJ() ) {
					stacksSvte.getChildren().remove(stacksSvte.getChildren().size()-1);
				}

				if( cellSvte.ContainsFood().equals(OptionFood.Fo) ) {
					stacksSvte.getChildren().remove(stacksCourante.getChildren().size()-1);
					player.setHp(player.getHp() + 1);
				}


				Node p = stacksCourante.getChildren().remove(stacksCourante.getChildren().size()-1);// le player
				stacksSvte.getChildren().add(p);
			}else {

				if( cellCourante.getNature().equals(Cell.EMP) && cellSvte.getNature().equals(Cell.DNC)) {
					stacksSvte.getChildren().remove(stacksSvte.getChildren().size()-1);//supp la porte fermee
					stacksSvte.getChildren().add(po);//la porte ouverte
					Node p = stacksCourante.getChildren().remove(stacksCourante.getChildren().size()-1);//supp le joueur
					stacksSvte.getChildren().add(p);//add le joueur

					env.OpenDoor(cellSvte.getI(), cellSvte.getJ());//ca marche

				}else {
					if( cellCourante.getNature().equals(Cell.EMP) && cellSvte.getNature().equals(Cell.DWC)) {

						stacksSvte.getChildren().remove(stacksSvte.getChildren().size()-1);//supp la porte fermee
						stacksSvte.getChildren().add(po);//la porte ouverte
						stacksSvte.getChildren().get(stacksSvte.getChildren().size()-1).setRotate(90);
						Node p = stacksCourante.getChildren().remove(stacksCourante.getChildren().size()-1);//supp le joueur
						stacksSvte.getChildren().add(p);//add le joueur

						env.OpenDoor(cellSvte.getI(), cellSvte.getJ());
					}else {
						if( (cellCourante.getNature().equals(Cell.DNO)||cellCourante.getNature().equals(Cell.DWO)||
								cellCourante.getNature().equals(Cell.IN)) && cellSvte.getNature().equals(Cell.EMP)) {
							if( iApres == env.getTresor().getI() && jApres == env.getTresor().getJ() ) {
								stacksSvte.getChildren().remove(stacksSvte.getChildren().size()-1);
							}
							Node p = stacksCourante.getChildren().remove(stacksCourante.getChildren().size()-1);//supp le joueur
							stacksSvte.getChildren().add(p);//add le joueur
						}else {

							if( cellCourante.getNature().equals(Cell.EMP) && ( cellSvte.getNature().equals(Cell.DNO) || 
									cellSvte.getNature().equals(Cell.IN ) ||( cellSvte.getNature().equals(Cell.DWO) ))) {
								Node p = stacksCourante.getChildren().remove(stacksCourante.getChildren().size()-1);//supp le joueur
								stacksSvte.getChildren().add(p);//add le joueur
							}else {
								if( cellSvte.getNature().equals(Cell.OUT)) {
									Sortie(stacksCourante,stacksSvte);
								}
							}
						}
					}
				}
			}

		}
	}

	public void Sortie(StackPane stacksCourante, StackPane stacksSvte) {
		Node p = stacksCourante.getChildren().remove(stacksCourante.getChildren().size()-1);//supp le joueur
		stacksSvte.getChildren().add(p);//add le joueur
		if(player.getTresorFound()) {
			labelGagne.setText("Gagné :) ");
		}else {
			labelGagne.setText("Perdu :( ");
		}
	}

}
