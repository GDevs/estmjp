package estm;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Login extends JDialog implements ActionListener {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JCheckBox chckbxSpezifiziereLogin;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel error;
	private JButton btnLogin;
	private GUI gui;
	private Verbindung verbindung;

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
	public Login(GUI gui, Verbindung v) {
		
		this.gui = gui;
		this.verbindung = v;
		
		setTitle("Login");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 250, 362);
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

		btnLogin = new JButton("Login");
		btnLogin.addActionListener(this);
		btnLogin.setBounds(45, 261, 150, 28);
		contentPane.add(btnLogin);

		passwordField = new JPasswordField();
		passwordField.setBounds(47, 181, 148, 20);
		contentPane.add(passwordField);

		chckbxSpezifiziereLogin = new JCheckBox("Default Server");
		chckbxSpezifiziereLogin.setSelected(true);
		chckbxSpezifiziereLogin.setBounds(45, 217, 200, 23);
		contentPane.add(chckbxSpezifiziereLogin);
		
		error = new JLabel("");
		error.setBounds(10, 300, 190, 14);
		contentPane.add(error);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLogin){
		try {
			String password = new String(passwordField.getPassword());
			password = Hash.hash(password);
			ResultSet rs = verbindung
					.query("SELECT * FROM person WHERE Name='"
							+ textField.getText() + "' AND Vorname='"
							+ textField_1.getText() + "' AND Kennwort='"
							+ password + "'");
			if(rs == null) System.out.println("null");
			if(rs == null || !rs.first()){//KEIN ERGEBNISS AUS DER DATENBANK, PASSWORT ODER DER NAME FALSCH
				//TODO Meldung über den Fehler
				error.setText("Name oder Passwort falsch!");
				
			}else{
			Person person = new Person(rs.getInt("ID"), rs.getString("name"),
					rs.getString("vorname"), rs.getInt("rechte"),
					rs.getString("status"), rs.getString("kennwort"));
			
			gui.setPerson(person);
			
			System.out.println("ERFOLG!");
			
			this.setVisible(false);
			verbindung.closeStatement();
			gui.prepareUserInterface(textField.getText(), textField_1.getText());
			
			}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
