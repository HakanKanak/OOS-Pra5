package prak4serv;

import prak4gemklassen.*;
/**
* <p>Ueberschrift: Benutzerverwaltung </p>
* <p>Beschreibung:	Diese Klasse definiert die Benutzerverwaltung als Schnittstelle
* 									zur Klasse BenutzerVerwaltungAdmin.
* @version 1.0
*/


public interface BenutzerVerwaltung {
	
	void benutzerEintragen(Benutzer benutzer) throws BenutzerExistException;

  boolean benutzerOk(Benutzer benutzer);
  

  

}
