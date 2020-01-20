package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class AnwendungsController {
	
	
	@FXML
	private Button button;
	
	@FXML
	public void abbrechen(ActionEvent event) {
		Stage stage = (Stage) button.getScene().getWindow();
		stage.close();
		System.out.println("Das Fenster wurde geschlossen");
	}
	
	public void showAnwendungsScene(Stage stage) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/application/anwendung.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Benutzerverwaltung");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
