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

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(74, 36, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(94, 11, 46, 14);
		contentPane.add(lblName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(74, 103, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblVorname = new JLabel("Vorname:");
		lblVorname.setBounds(94, 88, 46, 14);
		contentPane.add(lblVorname);
		
		textField_2 = new JTextField();
		textField_2.setBounds(74, 181, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblKennwort = new JLabel("Kennwort");
		lblKennwort.setBounds(94, 156, 46, 14);
		contentPane.add(lblKennwort);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Button Clicked
			}
		});
		btnLogin.setBounds(72, 261, 89, 23);
		contentPane.add(btnLogin);
	}
}
