package application;
import java.io.Serializable;

/**
* <p>Ueberschrift: Struktur von Benutzern </p>
* <p>Beschreibung:	Diese Klasse definiert die grundlegende Struktur von Benutzern.
* 									Die Struktur eines Benutzers setzt sich zusammen aus:
* 									- der UserId und
* 									- dem Passwort
* 									Da es nur um eine Struktur geht, werden lediglich die beiden 
* 									Standardmethoden equals und toString implementiert.</p>
* @version 1.0
*/


public class Benutzer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4371924976942811550L;

	/**
   * Attribut zur Speicherung der UserID:
   */
	String userId;
	
	/**
   * Attribut zur Speicherung des passWorts:
   */
  char[] passWort;

  /**
   * Defaultkonstruktor
   */
  public Benutzer() {
    this.userId = "";
    this.passWort = null;
  }
  
  /**
   * Konstruktor, der beide Attribute initialisiert
   * @param userId Wahl eines UserIDs
   * @param passWort Wahl des Passworts char
   */
  public Benutzer(String userId, char[] passWort) {
    this.passWort = passWort;
    this.userId = userId;
  }

  /**
   * Konstruktor, der beide Attribute initialisiert
   * @param userId Wahl eines UserIDs
   * @param passWort Wahl des Passworts String
   */
  public Benutzer(String userId, String passWort) {
  		this.passWort = passWort.toCharArray();
    this.userId = userId;
	}

	/**
   * Standardmethode
   * @param obj	liefert das Objekt, dessen Inhalte mit denen des aktuellen Objekts
   * 						verglichen werden sollen
   * @return			true, wenn obj gleich aktuelles Objekt ist
   */
  public boolean equals(Object obj) {
    return (obj != null && obj instanceof Benutzer
        && String.copyValueOf(this.passWort).equals(String.copyValueOf(((Benutzer) obj).passWort))
        && this.userId.equals(((Benutzer) obj).userId));
  }

  /**
   * Standardmethode, die die Inhalte der beiden Attribute in der folgenden Form ausgibt: userID/passWort
   * @return liefert String-Ausgabe
   */
  public String toString() {
    return ("UserID: " + this.userId + ", Passwort: " + String.copyValueOf(this.passWort));
  }
}
