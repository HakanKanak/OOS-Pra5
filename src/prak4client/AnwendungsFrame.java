package prak4client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AnwendungsFrame extends JFrame {

  private JPanel contentPane;
  private Client client;

  public AnwendungsFrame() {
    setTitle("AnwendungsFrame");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 300);
    contentPane = new JPanel();
    contentPane.setLayout(null);
    setContentPane(contentPane);
    
    JLabel lblSieKnnenJetzt = new JLabel("Sie koennen jetzt loslegen!");
    lblSieKnnenJetzt.setFont(new Font("Dialog", Font.BOLD, 18));
    lblSieKnnenJetzt.setBounds(66, 29, 323, 92);
    contentPane.add(lblSieKnnenJetzt);
    
    JButton btnNewButton = new JButton("Abbrechen");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        System.out.println("Anwendungsframe wird geschlossen...");
        System.exit(ABORT);
      }
    });
    btnNewButton.setBounds(79, 137, 170, 24);
    contentPane.add(btnNewButton);
  }

  public AnwendungsFrame(Client client) {
    this();
    this.client = client;
  }
  
  
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          AnwendungsFrame frame = new AnwendungsFrame();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
}