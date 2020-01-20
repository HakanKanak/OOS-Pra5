package prak4client;
/**
* <p> Benutzer Verwaltung Admin </p>
* <p>Beschreibung:	Diese Klasse definiert die Benutzerverwaltung als Admin.
* 									Der Admin kann einen Benutzer eintragen, sowie einen Benutzer löschen.
* 									Die Methode benutzerOk prüft, ob dieser Benutzer existiert .</p>
* @version 1.0
*/

import java.util.*;
import java.io.*;
import prak4gemklassen.*;

public class BenutzerVerwaltungAdmin implements BenutzerVerwaltung, Serializable {

	private static final long serialVersionUID = 1L;
  private List<Benutzer> list = new ArrayList<Benutzer>();

  private String lokal = "lokal.txt";
  private String remote = "remote.txt";
  boolean bol;
	/**
   * Methode, mit dem ein neuer Benutzer in die Liste eingetragen werden kann
   * @param benutzer Benutzer mit Attributen wird angelegt
   * @throws BenutzerExistException Exception wird geworfen, wenn dieser Benutzer schon existiert
   */
  public void benutzerEintragen(Benutzer benutzer) throws BenutzerExistException {
		dbDeserialisieren();
  if (benutzerOk(benutzer)) {
    throw new BenutzerExistException("Der Benutzer " + benutzer.getUserId()
        + " kann nicht hinzugefuegt werden!");
  } else {
    list.add(benutzer);
    dbSerialisieren();
  }
}

	/**
	 * Methode überprüft, ob die Liste diesen Benutzer beinhaltet
	 * @param benutzer der zu überprüfende Benutzer
	 * @return true, wenn die Liste den Benutzer enthält
   *
   */
  public boolean benutzerOk(Benutzer benutzer) {
  		dbDeserialisieren();
    return (list.contains(benutzer));
  }

  
  /**
   * Methode, mit dem ein Benutzer geloescht werden kann
   * @param benutzer Benutzer, der geloescht werden soll
   * @throws BenutzerDoesntExistException Exception wird ausgeworfen, falls dieser Benutzer nicht existiert
   */
  public void benutzerLoeschen(Benutzer benutzer) throws BenutzerDoesntExistException {
	 
  		dbDeserialisieren();
    if (benutzerOk(benutzer)) {
    		list.remove(benutzer);
    		dbSerialisieren();
    } 
    else throw new BenutzerDoesntExistException("Der Benutzer " + benutzer.getUserId() + " existiert nicht!");
  }
  
  
  
  /**
   * Anlegen eines neuen leeren Objektes in der Datenstruktur
   */
  public void dbInitialisieren() {
  		
	    try {

	  		this.list = new ArrayList<Benutzer>();
	        FileOutputStream fs;
	      fs = new FileOutputStream(lokal);
	    	ObjectOutputStream os = new ObjectOutputStream(fs);				// Serialisierung (schreibend)
	      os.writeObject(this.list);
	      os.close();
	    } catch (IOException e) {
	      	e.printStackTrace();
	    }
  	    try {

  	  		this.list = new ArrayList<Benutzer>();
  	        FileOutputStream fs;
  	      fs = new FileOutputStream(remote);
  	    	ObjectOutputStream os = new ObjectOutputStream(fs);				// Serialisierung (schreibend)
  	      os.writeObject(this.list);
  	      os.close();
  	    } catch (IOException e) {
  	      	e.printStackTrace();
  	    }

  	  }
  
  /**
	  * Serialisieren: Objekte persistent machen um spaeter wiederhezustellen
	  * schreiben
	  */
  private void dbSerialisieren() {
    try {
    	
        FileOutputStream fs = new FileOutputStream(lokal);
    
    	ObjectOutputStream os = new ObjectOutputStream(fs);				// Serialisierung (schreibend)
      os.writeObject(this.list);
      os.close();
    } catch (IOException e) {
      	e.printStackTrace();
    }
  }

 
  /**
	  * Deserialisieren: Wiederherstellen der persistent gemachten Objekte
	  * lesen
	  */
 
	@SuppressWarnings("unchecked")
	private void dbDeserialisieren() {
  try {
  		// neues Objekt der zu deserialisierenden Klasse wird angelegt
  	FileInputStream fs = new FileInputStream(lokal);
    ObjectInputStream is = new ObjectInputStream(fs);				// Deserialisierung (lesend)
    this.list = (List<Benutzer>) is.readObject();
    System.out.println(list);
    is.close();
  } catch (ClassNotFoundException e) {
    		e.printStackTrace();
  } catch (IOException e) {
  		e.printStackTrace();
  }
}
}
