package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MainApplication extends Application {

	private Stage stage = new Stage();
	private static MainApplication instance;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		this.stage = primaryStage;
		this.instance = this;
		BenutzerVerwaltungAdmin admin = new BenutzerVerwaltungAdmin();
		int dbInitialisieren; //Für die Auswahl in der Schleife
		
		do {
			System.out.println("Datenhaltung initialisieren?");
			System.out.println("[1] fuer JA, [2] fuer NEIN : ");
			BufferedReader din = new BufferedReader(new InputStreamReader(System.in));
			dbInitialisieren = Integer.parseInt(din.readLine());
		} while (dbInitialisieren != 1 && dbInitialisieren != 2);
		
		if(dbInitialisieren == 1) {
			admin.dbInitialisieren();
			System.out.println("Datenbank wurde erfolgreich initialisert!");
		} else System.out.println("Es wurde keine Initialisierung vorgenommen");
		
		LoginController lc = new LoginController();
		lc.setRef(this);
		lc.showLoginScene(this.stage);
		
	}
	
	public void neuAnmeldung() {
		AnmeldungsController ac = new AnmeldungsController();
		ac.setRef(this);
		ac.showAnmeldungScene(stage);
	}
	
	public void neuerBenutzer(Benutzer benutzer) {
		BenutzerVerwaltungAdmin admin = new BenutzerVerwaltungAdmin();
		boolean exception = false;
		
		try {
			admin.benutzerEintragen(benutzer);
			System.out.println("Benutzer wurde angelegt");
			System.out.println(benutzer.toString());
		} catch (BenutzerExistException e) {
			e.printStackTrace();
			exception = true;
		}
		
		if(exception == false) {
			LoginController lc = new LoginController();
			lc.setRef(this);
			lc.showLoginScene(stage);
		} else {
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("FEHLER");
			a.setHeaderText("Benutzer ist bereits vorhanden");
			a.setContentText("Bitte erneut mit anderer ID oder Passwort anmelden !");
			a.showAndWait();
		}
	}
	
	public void benutzerLogin(Benutzer benutzer) {
		BenutzerVerwaltungAdmin admin = new BenutzerVerwaltungAdmin();
		
		if(admin.benutzerOk(benutzer)) {
			AnwendungsController ac = new AnwendungsController();
			ac.showAnwendungsScene(stage);
		} else {
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle("FEHLER");
			a.setHeaderText("Benutzername oder Passwort falsch");
			a.setContentText("Bitte erneut anmelden!");
			a.showAndWait();
		}
	}
	
	public static MainApplication getInstance() {
		return MainApplication.instance;
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
