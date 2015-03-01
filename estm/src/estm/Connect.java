package estm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

public class Connect extends JDialog implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JLabel lblDatenbankname;
	private JLabel lblBenutzer;
	private JLabel lblPasswort;
	private JButton btnLogin;
	public String datenbankAdresse;
	public String datenbankName;
	public String datenbankNutzer;
	public String datenbankKennwort;
	
/**________________________________________________________________________________________________________________
 * ________________________________________________________________________________________________________________*/
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connect frame = new Connect();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
/**________________________________________________________________________________________________________________
 * ________________________________________________________________________________________________________________*/
	/**
	 * Create the frame. LOL
	 */
	public Connect() {
		setTitle("Verbinde");
		setModal(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 250, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIpurl = new JLabel("IP oder URL:");
		lblIpurl.setBounds(39, 11, 156, 24);
		contentPane.add(lblIpurl);
		
		textField = new JTextField();
		textField.setBounds(39, 46, 156, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(39, 116, 156, 24);
		contentPane.add(textField_1);
	
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(39, 186, 156, 24);
		contentPane.add(textField_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(38, 251, 158, 24);
		contentPane.add(passwordField);
		
		lblDatenbankname = new JLabel("Datenbankname:");
		lblDatenbankname.setBounds(39, 81, 156, 24);
		contentPane.add(lblDatenbankname);
		
		lblBenutzer = new JLabel("Benutzer");
		lblBenutzer.setBounds(39, 151, 156, 24);
		contentPane.add(lblBenutzer);
		
		lblPasswort = new JLabel("Passwort");
		lblPasswort.setBounds(39, 221, 156, 24);
		contentPane.add(lblPasswort);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(this);
		btnLogin.setBounds(72, 327, 89, 23);
		contentPane.add(btnLogin);
		
		JCheckBox chckbxAlsStandard = new JCheckBox("Als Standard");
		chckbxAlsStandard.setBounds(72, 282, 97, 23);
		contentPane.add(chckbxAlsStandard);

		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(btnLogin)){
		datenbankAdresse = textField.getText();
		datenbankName = textField_1.getText();
		datenbankNutzer = textField_2.getText();
		datenbankKennwort = new String(passwordField.getPassword());
		try {
			File f = new File("settings.ini");
			if(!f.exists()){
				f.createNewFile();
			}
			Ini datei = new Ini(new File(
				"settings.ini"));
			Ini.Section sektion = datei.get("datenbank");
			if(sektion == null){
				datei.add("datenbank");
				sektion = datei.get("datenbank");
			}
			sektion.put("adresse", datenbankAdresse);
			sektion.put("name", datenbankName);
			sektion.put("nutzer", datenbankNutzer);
			sektion.put("kennwort", datenbankKennwort);
			datei.store();
			//System.out.println("DATEN GESCHRIEBEN");
		} catch (InvalidFileFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    setVisible(false);
	    dispose();
		}
	}
}
