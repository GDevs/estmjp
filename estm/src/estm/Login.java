package estm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;

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
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Login prüfen und ESTM starten
			}
		});
		btnLogin.setBounds(45, 261, 150, 28);
		contentPane.add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(47, 181, 148, 20);
		contentPane.add(passwordField);
		
		JCheckBox chckbxSpezifiziereLogin = new JCheckBox("Default Server");
		chckbxSpezifiziereLogin.setSelected(true);
		chckbxSpezifiziereLogin.setBounds(45, 217, 150, 23);
		contentPane.add(chckbxSpezifiziereLogin);
	}
}
