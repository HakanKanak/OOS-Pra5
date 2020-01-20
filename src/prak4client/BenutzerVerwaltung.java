package prak4client;

import prak4gemklassen.*;

public interface BenutzerVerwaltung {
	
	void benutzerEintragen(Benutzer benutzer) throws BenutzerExistException;

  boolean benutzerOk(Benutzer benutzer);
  

  

}
