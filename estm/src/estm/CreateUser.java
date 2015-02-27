package estm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class CreateUser extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JTextField textField_2;
	private JLabel lblPasswort;
	private JLabel lblBesttigePasswort;
	private JButton btnErstelleBenutzer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateUser frame = new CreateUser();
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
	public CreateUser() {
		setTitle("Benutzererstellung");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(54, 23, 125, 14);
		contentPane.add(lblName);
		
		JLabel lblVorname = new JLabel("Vorname:");
		lblVorname.setBounds(54, 63, 125, 14);
		contentPane.add(lblVorname);
		
		textField = new JTextField();
		textField.setBounds(54, 36, 125, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(54, 76, 125, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JCheckBox chckbxLehrer = new JCheckBox("Lehrer");
		chckbxLehrer.setBounds(54, 103, 125, 23);
		contentPane.add(chckbxLehrer);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(54, 146, 125, 20);
		contentPane.add(passwordField);
		
		textField_2 = new JTextField();
		textField_2.setBounds(54, 192, 125, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		lblPasswort = new JLabel("Passwort:");
		lblPasswort.setBounds(54, 133, 125, 14);
		contentPane.add(lblPasswort);
		
		lblBesttigePasswort = new JLabel("Best\u00E4tige Passwort:");
		lblBesttigePasswort.setBounds(54, 177, 125, 14);
		contentPane.add(lblBesttigePasswort);
		
		btnErstelleBenutzer = new JButton("Erstelle Benutzer");
		btnErstelleBenutzer.setBounds(17, 251, 200, 50);
		contentPane.add(btnErstelleBenutzer);
	}
}
