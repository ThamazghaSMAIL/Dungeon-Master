package editmap;

import java.io.FileNotFoundException;

import implm.CelluleImplem;
import implm.EditMapImplem;
import implm.EnvironmentImplem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import servives.CelluleService;
import servives.EnvironnementService;
import tools.Cell;

public class ControllerEdit {
	CelluleService[][] cells ;
	EnvironnementService env = new EnvironmentImplem();

	

	@FXML
	private Button ok;

	@FXML
	private TextField dimention; 

	@FXML
	private HBox Map;

	@FXML
	public void ok() throws FileNotFoundException {
		System.out.println("ok");
		Map.setMaxHeight(80*Double.parseDouble(dimention.getText()));
		Map.setMaxWidth(80*Double.parseDouble(dimention.getText()));
	}
	int nb = 0;
	@FXML
	ImageView mur;

	@FXML
	public void mur() {
		if( nb< Integer.parseInt(dimention.getText())*Integer.parseInt(dimention.getText())) {
			javafx.scene.image.Image mur = new javafx.scene.image.Image(getClass().getResource("mur.png").toExternalForm());
			ImageView imur = new ImageView(mur);
			imur.setFitHeight(80);
			imur.setFitWidth(80);
			Map.getChildren().add(imur);
			ecrire(Cell.WLL);
			nb++;
			System.out.println("mur");
		}
	}

	@FXML
	ImageView gazon;

	@FXML
	public void gazon() {
		if( nb < Integer.parseInt(dimention.getText())*Integer.parseInt(dimention.getText())) {
			javafx.scene.image.Image gazon = new javafx.scene.image.Image(getClass().getResource("gazon.jpg").toExternalForm());
			ImageView igazon = new ImageView(gazon);
			igazon.setFitHeight(80);
			igazon.setFitWidth(80);
			Map.getChildren().add(igazon);
			System.out.println("gazon");
			ecrire(Cell.EMP);
			nb++;
		}
	}

	@FXML
	ImageView porteOuverteN;
	
	@FXML
	public void porteOuverteN() {
		if( nb < Integer.parseInt(dimention.getText())*Integer.parseInt(dimention.getText())) {
			javafx.scene.image.Image porte = new javafx.scene.image.Image(getClass().getResource("porteOuverte.png").toExternalForm());
			ImageView iporte = new ImageView(porte);
			iporte.setFitHeight(80);
			iporte.setFitWidth(80);
			Map.getChildren().add(iporte);
			System.out.println("porteOuverteN");
			ecrire(Cell.DNO);
			nb++;
		}
	}
	int i = 0;
	int j = 0;
	private void ecrire(Cell c) {
		cells[i][j] = new CelluleImplem();
		cells[i][j].init(i, j, c, env);
		i++;
		if( i == Integer.parseInt(dimention.getText())) {
			i=0;
			j++;
		}
	}
	@FXML
	ImageView porteFermeeN;
	@FXML
	public void porteFermeeN() {
		if( nb < Integer.parseInt(dimention.getText())*Integer.parseInt(dimention.getText())) {
			javafx.scene.image.Image porte = new javafx.scene.image.Image(getClass().getResource("porteFermee.png").toExternalForm());
			ImageView iporte = new ImageView(porte);
			iporte.setFitHeight(80);
			iporte.setFitWidth(80);
			Map.getChildren().add(iporte);
			System.out.println("porteFermeeN");
			ecrire(Cell.DNC);
			nb++;
		}
	}

	@FXML
	ImageView porteOuverteE;
	@FXML
	public void porteOuverteE() {
		if( nb < Integer.parseInt(dimention.getText())*Integer.parseInt(dimention.getText())) {
			javafx.scene.image.Image porte = new javafx.scene.image.Image(getClass().getResource("porteOuverte.png").toExternalForm());
			ImageView iporte = new ImageView(porte);
			iporte.setFitHeight(80);
			iporte.setFitWidth(80);
			iporte.setRotate(90);
			Map.getChildren().add(iporte);
			System.out.println("porteOuverteE");
			ecrire(Cell.DWO);
			nb++;
		}
	}

	@FXML
	ImageView porteFermeeE;
	@FXML
	public void porteFermeeE() {
		if( nb < Integer.parseInt(dimention.getText())*Integer.parseInt(dimention.getText())) {
			javafx.scene.image.Image porte = new javafx.scene.image.Image(getClass().getResource("porteFermee.png").toExternalForm());
			ImageView iporte = new ImageView(porte);
			iporte.setFitHeight(80);
			iporte.setFitWidth(80);
			iporte.setRotate(90);
			Map.getChildren().add(iporte);
			System.out.println("porteFermeeE");
			ecrire(Cell.DWC);
			nb++;
		}
	}


	public void affiche() {
		int dim = Integer.parseInt(dimention.getText());
		for (int i = 0 ; i < dim ; i++) {
			for (int j = 0 ; j < dim ; j++ ) {
				System.out.println(cells[i][j]+"\t");
			}
			System.out.println("\n");
		}
	}
	
	
	public void init() {
		cells = new CelluleImplem[100][100];
		EditMapImplem editmap = new EditMapImplem();
		
		porteOuverteN.setFitHeight(40);
		porteFermeeN.setFitHeight(40);
		
		porteOuverteE.setFitHeight(40);
		porteOuverteE.setRotate(90);
		
		porteFermeeE.setFitHeight(40);
		porteFermeeE.setRotate(90);
		
		gazon.setFitHeight(40);
		mur.setFitHeight(40);
	}
}

