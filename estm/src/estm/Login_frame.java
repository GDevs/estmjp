package estm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login_frame extends JFrame implements ActionListener {
	private Verbindung verbindung;
	private JTextField textField_Name;
	private JTextField textField_Vorname;
	private JPasswordField passwordField_Password;
	private JButton btn_Login;
	private JLabel error;
	private boolean success = false;

	public Login_frame(Verbindung verbindung) {
		setResizable(false);
		
		this.setBounds(100,100,363,165);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 11, 133, 14);
		getContentPane().add(lblName);
		
		textField_Name = new JTextField();
		textField_Name.setBounds(10, 29, 133, 20);
		getContentPane().add(textField_Name);
		textField_Name.setColumns(10);
		
		JLabel lblVorname = new JLabel("Vorname");
		lblVorname.setBounds(203, 11, 133, 14);
		getContentPane().add(lblVorname);
		
		textField_Vorname = new JTextField();
		textField_Vorname.setBounds(203, 29, 133, 20);
		getContentPane().add(textField_Vorname);
		textField_Vorname.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Passwort");
		lblNewLabel.setBounds(10, 60, 133, 14);
		getContentPane().add(lblNewLabel);
		
		passwordField_Password = new JPasswordField();
		passwordField_Password.setBounds(10, 78, 133, 20);
		getContentPane().add(passwordField_Password);
		
		btn_Login = new JButton("Login");
		btn_Login.setBounds(203, 77, 133, 23);
		getContentPane().add(btn_Login);
		
		error = new JLabel("");
		error.setBounds(10, 109, 133, 14);
		getContentPane().add(error);
		
		btn_Login.addActionListener(this);
		this.setVisible(true);
	}
	
//	public JButton getButton(){ return btn_Login;}
//	public JLabel  getError(){ return error;}
//	public JTextField getLocName(){ return textField_Name;} 
//	public JTextField getVorname(){ return textField_Vorname;} 
//	public JPasswordField getPassword(){ return passwordField_Password;}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_Login){
			String password = null;
			String name = null;
			String vorname = null;
			try{
				password = Hash.hash(new String(passwordField_Password.getPassword()));
				name = textField_Name.getText();
				vorname = textField_Vorname.getText();
				ResultSet rs = verbindung
						.query("SELECT * FROM personen WHERE name='"
								+ name + "' AND vorname='"
								+ vorname + "' AND kennwort='"
								+ password + "'");
		//		System.out.println(name+" "+vorname+" "+password);
				if(!rs.first()){//KEIN ERGEBNISS AUS DER DATENBANK, PASSWORT ODER DER NAME FALSCH
					error.setText("Bitte füllen sie alle Felder aus");
				}else{
					System.out.println("suc");
					this.setVisible(false);
				    this.dispose();
				}
			}catch(Exception ex){
				
			}
		
	} 
	
	
}
