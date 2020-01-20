package prak4serv;


public class Server {
	
	private BenutzerVerwaltungAdmin admin;
	private ServerOrb so;
	
	public Server() {
		System.out.println("Initialisierung Server...");
		admin = new BenutzerVerwaltungAdmin();
		so = new ServerOrb(admin);
	}
	
	 public static void main(String[] args) {
	    Server myServer = new Server();
	 }

}
