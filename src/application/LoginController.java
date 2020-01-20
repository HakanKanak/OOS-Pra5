package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	
	@FXML
	private TextField userIdTextFeld;
	@FXML
	private TextField passWortTextFeld;
	@FXML
	private CheckBox checkbox;
	@FXML
	private Button button;
	
	private boolean neuAnmeldung = false;
	
	private MainApplication application;
	
	public LoginController() {
		this.setRef(MainApplication.getInstance());
	}
	
	public void setNeuAnmeldung(ActionEvent event) {

			this.neuAnmeldung = checkbox.isSelected();
			System.out.println(neuAnmeldung);			

	}

	
	public void ausfuehren_login(ActionEvent event) {
		
		Benutzer ben = new Benutzer(userIdTextFeld.getText(), passWortTextFeld.getText().toCharArray());
		
		if(neuAnmeldung) this.application.neuAnmeldung(); 
		else this.application.benutzerLogin(ben);
		
	}
	
	public void setRef(MainApplication app) {
		application = app;
	}
	
	public void showLoginScene(Stage stage) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/application/login.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Benutzerverwaltung");
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
