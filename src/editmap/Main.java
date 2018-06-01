package editmap;
	
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxml = new FXMLLoader(getClass().getResource("interface1.fxml"));
			VBox root = fxml.load();
			ControllerEdit controller = fxml.getController();
			Platform.runLater(()->controller.init());
			PrintWriter out = new PrintWriter( new BufferedOutputStream(new FileOutputStream("Map.txt")));
			//controller.ecrire(out);
			Scene scene = new Scene(root,1024,768);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
