package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AnmeldungsController {
	
	@FXML
	private TextField userIdTextFeld;
	@FXML
	private TextField passWortTextFeld;
	@FXML
	private TextField passWortTextFeld2;
	@FXML
	private Label error;
	@FXML
	private Button button;
	
	private MainApplication application;
	
	
	public AnmeldungsController() {
		this.setRef(MainApplication.getInstance());
	}
	
	public void ausfuehren(ActionEvent event) {
		if(passWortTextFeld.getText().equals(passWortTextFeld2.getText())) {
			Benutzer ben = new Benutzer(userIdTextFeld.getText(), passWortTextFeld.getText());
			application.neuerBenutzer(ben);
		} else {
			System.out.println("Passwort != Wiederholung");
			error.setVisible(true);
		}
		
	}
	
	public void setRef(MainApplication app) {
		application = app;
	}
	
	public void showAnmeldungScene(Stage stage) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/application/anmeldung.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Benutzerverwaltung");
			stage.show();
		} catch (IOException e) {
			e.getCause().printStackTrace();
			e.printStackTrace();
		}
	}
	
	

}
