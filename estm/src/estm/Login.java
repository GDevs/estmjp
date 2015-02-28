package estm;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JCheckBox chckbxSpezifiziereLogin;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(47, 36, 148, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(47, 11, 148, 14);
		contentPane.add(lblName);

		textField_1 = new JTextField();
		textField_1.setBounds(47, 103, 148, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblVorname = new JLabel("Vorname:");
		lblVorname.setBounds(47, 88, 148, 14);
		contentPane.add(lblVorname);

		JLabel lblKennwort = new JLabel("Kennwort");
		lblKennwort.setBounds(47, 156, 148, 14);
		contentPane.add(lblKennwort);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(this);
		btnLogin.setBounds(45, 261, 150, 28);
		contentPane.add(btnLogin);

		passwordField = new JPasswordField();
		passwordField.setBounds(47, 181, 148, 20);
		contentPane.add(passwordField);

		chckbxSpezifiziereLogin = new JCheckBox("Default Server");
		chckbxSpezifiziereLogin.setSelected(true);
		chckbxSpezifiziereLogin.setBounds(45, 217, 150, 23);
		contentPane.add(chckbxSpezifiziereLogin);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			Verbindung verbindung = new Verbindung();
			String password = new String(passwordField.getPassword());
			password = hash(password);
			ResultSet rs = verbindung
					.query("SELECT * FROM personen WHERE name='"
							+ textField.getText() + "' AND vorname='"
							+ textField_1.getText() + "' AND kennwort='"
							+ password + "'");
			if(!rs.first()){//KEIN ERGEBNISS AUS DER DATENBANK, PASSWORT ODER DER NAME FALSCH
				//TODO Meldung über den Fehler
				System.out.println("NAME ODER PASSWORT FALSCH!");
				return;
			}
			Person person = new Person(rs.getString("name"),
					rs.getString("vorname"), rs.getInt("ID"),
					rs.getString("status"));
			System.out.println("ERFOLG!");
			
			this.setVisible(false);
			ESTM estm = new ESTM(verbindung.conn, person); 	//ESTM WIRD ERZEUGT
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private String hash(String passwordToHash) {
		String generatedPassword = "";
		//Übernommen von http://howtodoinjava.com/2013/07/22/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
	}
}
