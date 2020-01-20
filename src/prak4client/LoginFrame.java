package prak4client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import prak4gemklassen.*;

public class LoginFrame extends JFrame {

  private JPanel contentPane;
  JTextField textField;
  private JTextField txtLocalhost;
  private JPasswordField pwdBox;
  
  // my atrributes:
  private boolean lokal = true;

  private boolean neuAnmeldung = false;

  private InetAddress address;

  private Client client;

  public LoginFrame(Client client) {
    this();
    this.client = client;
  }

 
  public LoginFrame() {
    setTitle("LoginFrame");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 398, 439);
    contentPane = new JPanel();
    setContentPane(contentPane);
    contentPane.setLayout(null);

    JLabel lblLoginBenutzerverwaltung = new JLabel("Login Benutzerverwaltung");
    lblLoginBenutzerverwaltung.setFont(new Font("Dialog", Font.BOLD, 18));
    lblLoginBenutzerverwaltung.setBounds(58, 25, 276, 59);
    contentPane.add(lblLoginBenutzerverwaltung);

    JLabel lblUserid = new JLabel("User-ID");
    lblUserid.setBounds(36, 95, 70, 15);
    contentPane.add(lblUserid);

    JLabel lblPasswort = new JLabel("Passwort");
    lblPasswort.setBounds(36, 160, 70, 15);
    contentPane.add(lblPasswort);

    JCheckBox chckbxRemote = new JCheckBox("remote?");
    chckbxRemote.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent evt) {
        JCheckBox jchkbox = (JCheckBox) evt.getSource();
        lokal = !jchkbox.isSelected();
        System.out.println("Wert von lokal: " + lokal);
        // remote-Zugriff: IP muss akzeptiert werden
        txtLocalhost.setEnabled(!lokal);
      }
    });
    chckbxRemote.setBounds(58, 230, 89, 23);
    contentPane.add(chckbxRemote);

    JLabel lblIp = new JLabel("IP:");
    lblIp.setBounds(154, 230, 120, 23);
    contentPane.add(lblIp);

    JCheckBox chckbxNeuanmeldung = new JCheckBox("Neu-Anmeldung?");
    chckbxNeuanmeldung.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent evt) {
        JCheckBox jchkbox = (JCheckBox) evt.getSource();
        neuAnmeldung = jchkbox.isSelected();
        System.out.println("Wert von neuAnmeldung: " + neuAnmeldung);
      }
    });
    chckbxNeuanmeldung.setBounds(120, 306, 154, 23);
    contentPane.add(chckbxNeuanmeldung);

    JButton btnNewButton = new JButton("Ausfuehren");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        try {
          address = InetAddress.getByName(txtLocalhost.getText());
        } catch (UnknownHostException e) {
          System.out.println("\r\nUnknown Host!\r\n");
        }
        Benutzer ben = new Benutzer(textField.getText(), pwdBox.getText());
        dispose();
        // Fallunterscheidung, ob der Benutzer neu angemeldet werden soll oder
        // nicht, hierzu wird die Kontrolle an den Client uebergeben
        if (neuAnmeldung) {
          if (lokal) {
          client.neuAnmeldungLokal();
          }
          else {
            client.neuAnmeldungRemote(address);
          }
        }
        else {
          if (lokal) {
            client.benutzerLoginLokal(ben);
          }
          else {
            client.benutzerLoginRemote(ben, address);
          }
        }
      }
    });
    btnNewButton.setBounds(120, 364, 117, 36);
    contentPane.add(btnNewButton);

    textField = new JTextField();
    textField.setBounds(124, 88, 177, 30);
    contentPane.add(textField);
    textField.setColumns(10);
    
    pwdBox = new JPasswordField();
    pwdBox.setBounds(124, 153, 177, 30);
    contentPane.add(pwdBox);

    txtLocalhost = new JTextField();
    txtLocalhost.setText("localhost");
    txtLocalhost.setColumns(10);
    txtLocalhost.setBounds(176, 230, 177, 30);
    txtLocalhost.setEnabled(false);
    contentPane.add(txtLocalhost);

    
  }

 
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          LoginFrame frame = new LoginFrame();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
}