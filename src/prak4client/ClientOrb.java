package prak4client;

import prak4gemklassen.*;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.InetAddress;


public class ClientOrb implements BenutzerVerwaltung {
	
	
	private InetAddress address;
	
	public ClientOrb(InetAddress address) {
		this.address = address;
	}
	@Override
	public void benutzerEintragen(Benutzer benutzer) throws BenutzerExistException {
		
		System.out.println("Zugang zu ClientOrb");
		
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		Socket server = null;
		
		try {
			
			// Verbindung ueber Socket initalisieren
			server = new Socket(address, 4711);
			System.out.println("Socket geoeffnet...");

			// Streams initalisieren
			out = new ObjectOutputStream(server.getOutputStream());
			in = new ObjectInputStream(server.getInputStream());
			System.out.println("Beide Streams vom Server hergestellt...");
			
			//MethodenID und Parameter senden
			out.writeInt(1);
			out.writeObject(benutzer);
			out.flush(); // schickt noch gepufferte Daten rüber, mindestens nach letztem gesendetem Datum
			System.out.println("Objekt geschrieben...");
			
			try {
				Object obj = in.readObject();
				System.out.println("Lese Objekt und pruefe, ob es Benutzerobjekt oder Exception ist");
				if(obj instanceof BenutzerExistException) {
					System.out.println("Exception!!!");
					throw new BenutzerExistException("FEHLER!");
				} 
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			System.out.println("Benutzer remote erfolgreich eingetragen");
		} catch(IOException e) {
			e.printStackTrace();
		} 
		// Streams und Socket schliessen
		finally {
			try {
				if(in != null && out != null && server != null) {
					in.close();
					out.close();
					server.close();
					System.out.println("Alles geschlossen...");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public boolean benutzerOk(Benutzer benutzer) {
		
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		Socket server = null;
		Boolean userOk = false;
		
		try {
				// Verbindung über Socket initalisieren
				server = new Socket(address, 4711);
				System.out.println("Socket geoeffnet...");

				// Streams initalisieren
				out = new ObjectOutputStream(server.getOutputStream());
				in = new ObjectInputStream(server.getInputStream());
				System.out.println("Beide Streams vom Server hergestellt...");
					
				//Methodenkennung und Parameter senden
				out.writeInt(2);
				out.writeObject(benutzer);
				System.out.println("Objekt geschrieben...");	
				userOk = in.readBoolean();
				System.out.println("Lese Booleanvalue vom Server...");	
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(in != null && out != null && server != null) {
					in.close();
					out.close();
					server.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return userOk;
	}

}
