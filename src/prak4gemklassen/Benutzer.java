package prak4gemklassen;
import java.io.Serializable;



public class Benutzer implements Serializable{
	
	boolean lokal;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4371924976942811550L;

	/**
   * Attribut zur Speicherung der UserID:
   */
	private String userId;
	
	/**
   * Attribut zur Speicherung des passWorts:
   */
  char[] passWort;

  /**
   * Defaultkonstruktor
   */
  public Benutzer() {
    this.setUserId("");
    this.passWort = null;
  }
  
  /**
   * Konstruktor, der beide Attribute initialisiert
   * @param userId Wahl eines UserIDs
   * @param passWort Wahl des Passworts char
   */
  public Benutzer(String userId, char[] passWort) {
    this.passWort = passWort;
    this.setUserId(userId);
  }

  /**
   * Konstruktor, der beide Attribute initialisiert
   * @param userId Wahl eines UserIDs
   * @param passWort Wahl des Passworts String
   */
  public Benutzer(String userId, String passWort) {
  		this.passWort = passWort.toCharArray();
    this.setUserId(userId);
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
        && this.getUserId().equals(((Benutzer) obj).getUserId()));
  }

  /**
   * Standardmethode, die die Inhalte der beiden Attribute in der folgenden Form ausgibt: userID/passWort
   * @return liefert String-Ausgabe
   */
  public String toString() {
    return ("UserID: " + this.getUserId() + ", Passwort: " + String.copyValueOf(this.passWort));
  }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	public boolean getLokal() {
		return lokal;
	}
}
