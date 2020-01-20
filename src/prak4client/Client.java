package prak4client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;

import prak4gemklassen.*;

/** Diese Klasse steuert das Zusammenspiel aller Klassen, die in den Praktika 2 &
 * 3 erzeugt wurden und sorgt somit auch fuer die Kommunikation zwischen den
 * Objekten.
 * 
 */


public class Client {
	
	private BenutzerVerwaltungAdmin admin;
	private InetAddress address;
	private ClientOrb co;
	private LoginFrame loginFrame;
	private AnmeldungsFrame anmeldungsFrame;
	private AnwendungsFrame anwendungsFrame;
	private boolean lokal;
	
	public Client() throws NumberFormatException, IOException {
    admin = new BenutzerVerwaltungAdmin();
    DbInitialisierenWahl();
    loginFrame = new LoginFrame(this);
    loginFrame.setVisible(true);
  }
	
	private void DbInitialisierenWahl() throws IOException {
		int dbInitialisieren;
		do {
			System.out.println("Datenhaltung initialisieren?");
			System.out.println("[1] fuer JA, [2] fuer NEIN : ");
			BufferedReader din = new BufferedReader(new InputStreamReader(System.in));
			dbInitialisieren = Integer.parseInt(din.readLine()); // in int casten
		} while (dbInitialisieren != 1 && dbInitialisieren != 2);
		
		if(dbInitialisieren == 1) {
			admin.dbInitialisieren();
			System.out.println("Datenbank wurde erfolgreich initialisiert!");
		} else System.out.println("Es wurde keine Initialisierung vorgenommen");
	}
	
	
	public void neuAnmeldungLokal() {
		this.lokal = true;
		anmeldungsFrame = new AnmeldungsFrame(this);
		anmeldungsFrame.setVisible(true);
	}
	
	public void neuAnmeldungRemote(InetAddress address) {
		this.lokal = false;
		this.address = address;
		co = new ClientOrb(address);
		anmeldungsFrame = new AnmeldungsFrame(this);
		anmeldungsFrame.setVisible(true);
		
	}
	
	public void neuerBenutzer(Benutzer benutzer) {
		try {
			if(lokal) {
				admin.benutzerEintragen(benutzer);
				System.out.println("Benutzer wurde angelegt");
				System.out.println(benutzer.toString());
			} else {
				co.benutzerEintragen(benutzer);
			}
			loginFrame = new LoginFrame(this);
			loginFrame.setVisible(true);
		} catch (BenutzerExistException e) {
			anmeldungsFrame = new AnmeldungsFrame();
			anmeldungsFrame.textFeld.setText("Neu-Anmeldung fehlgeschlagen.");
			anmeldungsFrame.setVisible(true);
		}
	}
	
	public void benutzerLoginLokal(Benutzer benutzer) {
		
		if(admin.benutzerOk(benutzer)) {
			anwendungsFrame = new AnwendungsFrame(this);
			anwendungsFrame.setVisible(true);
		} else {
			loginFrame = new LoginFrame(this);
			loginFrame.textField.setText("Noch nicht im System");
			loginFrame.setVisible(true);
		}
	}
	
	public void benutzerLoginRemote(Benutzer benutzer, InetAddress address) {
		
		co = new ClientOrb(address);
		if(co.benutzerOk(benutzer)) {
			anwendungsFrame = new AnwendungsFrame(this);
			anwendungsFrame.setVisible(true);
		} else {
			loginFrame = new LoginFrame(this);
			loginFrame.textField.setText("Noch nicht im System");
			loginFrame.setVisible(true);
		}
	}
	
	public static void main(String[] args) {
    try {
      Client cl = new Client();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
