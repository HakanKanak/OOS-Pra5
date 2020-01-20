package prak4serv;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import prak4gemklassen.*;

public class ServerOrb {
	
	private BenutzerVerwaltungAdmin admin;
	
	public ServerOrb(BenutzerVerwaltungAdmin admin) {
		this.admin = admin;
		startServer();
	}
	
	private void startServer() {
		System.out.println("Starte Server...");
		
		
		try {
			
			// Anlegen eines ServerSockets
			ServerSocket server = new ServerSocket(4711);
			int ID;
			Benutzer ben = new Benutzer();
			// Warten auf Verbindungsaufnahme durch Client
			
			
			
			while(true) {
				Socket client = server.accept();
				// Verbindung annehmen und Streams erzeugen
				ObjectInputStream in = new ObjectInputStream(client.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
				System.out.println("Beide Streams annehmen...");
				// Lesen des Parameters
				ID = in.readInt();
				System.out.println("Lese MethodenID...");
				try {
					// Lesen des Objekts
					ben = (Benutzer)in.readObject();
					System.out.println("Benutzerobjekt angenommen...");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				// Dienstanbieterobjekt
				if(ID == 1) {
					try {
						admin.benutzerEintragen(ben);
						System.out.println("Benutzer erfolgreich eingetragen...");
						out.writeObject(ben);
						// Sicherstellen, dass Ergebnis transportiert wird
						out.flush();
					} catch (BenutzerExistException e) {
						out.writeObject(e);
						System.out.println("Exception wurde verschickt...");
					}
				} else if (ID == 2){
					out.writeBoolean(admin.benutzerOk(ben));
					out.flush();
					System.out.println("Boolean bei ID == 2 zurueckgeben...");
				}
				in.close();
				out.close();
				System.out.println("Stream geschlossen...");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
}

