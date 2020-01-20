package prak4client;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import prak4gemklassen.*;



public class AnmeldungsFrame extends JFrame {
	
	
	private JPanel contentPane;
	JTextField textFeld;
	private JPasswordField passwordFeld;
	private JPasswordField passwordFeld1;
	private Client client;
	
	 public AnmeldungsFrame(Client client) {
	    this();
	    this.client = client;
	  }

	// erstellt Frame
	public AnmeldungsFrame() {
		setTitle("AnmeldungsFrame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 456, 325);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblAnmeldung = new JLabel("Anmeldung");
    lblAnmeldung.setFont(new Font("Dialog", Font.BOLD, 18));
    lblAnmeldung.setBounds(40, 10, 140, 40);
    contentPane.add(lblAnmeldung);

    JLabel lblUserid = new JLabel("User-ID");
    lblUserid.setBounds(12, 86, 70, 15);
    contentPane.add(lblUserid);

    textFeld = new JTextField();
    textFeld.setBounds(120, 79, 204, 29);
    contentPane.add(textFeld);
    textFeld.setColumns(10);

    JLabel lblPasswort = new JLabel("Passwort");
    lblPasswort.setBounds(12, 144, 70, 15);
    contentPane.add(lblPasswort);

    JLabel lblWiederholung = new JLabel("Wiederholung");
    lblWiederholung.setBounds(12, 201, 106, 15);
    contentPane.add(lblWiederholung);
		
    final JLabel lblStatusText = new JLabel("");
    lblStatusText.setVerticalAlignment(SwingConstants.TOP);
    lblStatusText.setHorizontalAlignment(SwingConstants.LEFT);
    lblStatusText.setFont(new Font("Dialog", Font.BOLD, 13));
    lblStatusText.setBounds(233, 246, 176, 23);
    contentPane.add(lblStatusText);

    JButton btnNewButton = new JButton("Ausfuehren");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        if (new String(passwordFeld.getPassword()).equals(new String(passwordFeld1.getPassword()))
            && !new String(passwordFeld.getPassword()).equals("")
            && !new String(passwordFeld1.getPassword()).equals("")) {
          Benutzer ben = new Benutzer(textFeld.getText(), passwordFeld.getPassword());
          dispose();
          client.neuerBenutzer(ben);
        } else {
          lblStatusText.setForeground(Color.red);
          lblStatusText.setText("PW's stimmen nicht!");
        }
      }
    });
    btnNewButton.setBounds(87, 246, 117, 40);
    contentPane.add(btnNewButton);

    passwordFeld = new JPasswordField();
    passwordFeld.setBounds(120, 137, 204, 29);
    contentPane.add(passwordFeld);

    passwordFeld1 = new JPasswordField();
    passwordFeld1.setBounds(120, 194, 204, 29);
    contentPane.add(passwordFeld1);
  }


	// Startet Application
	public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          AnmeldungsFrame frame = new AnmeldungsFrame();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
	

}
