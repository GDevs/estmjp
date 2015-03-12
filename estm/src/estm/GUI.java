package estm;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JFrame;


public class GUI extends JFrame implements ActionListener {

	
	private Verbindung verbindung;
	private Person user;
	private ESTM estm;
	private Login_frame login;
	private Elter_Interface eFace;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/** Legt beim erstellen ein Login_frame
	 * 
	 *  Allgemein legt es an der gegebenen Stelle das jeweilige Fesnter an und löscht es nach Benutzung.
	 * 
	 */
	public GUI() {
		verbindung = new Verbindung();
        login = new Login_frame(verbindung);
		login.setVisible(true);
		login.getButton().addActionListener(this);
		
        estm = new ESTM(verbindung, user);
	}
	
	public void prepareUserInterface(String name,String vorname){
		
		eFace = new Elter_Interface();
		eFace.setUsername("Herr/ Frau   "+ vorname +" "+ name+"  ihre Termine:");
		eFace.setVisible(true);
		eFace.getSpeichern().addActionListener(this);
		eFace.getUpdate().addActionListener(this);
		
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == login.getButton()){
			String password = null;
			String name = null;
			String vorname = null;
			try{
				password = Hash.hash(new String(login.getPassword().getPassword()));
				name = login.getLocName().getText();
				vorname = login.getVorname().getText();
				ResultSet rs = verbindung
						.query("SELECT * FROM personen WHERE name='"
								+ name + "' AND vorname='"
								+ vorname + "' AND kennwort='"
								+ password + "'");
		//		System.out.println(name+" "+vorname+" "+password);
				if(!rs.first()){//KEIN ERGEBNISS AUS DER DATENBANK, PASSWORT ODER DER NAME FALSCH
					login.getError().setText("Bitte füllen sie alle Felder aus");
				}else{
					System.out.println("suc");
					login.dispose();
					prepareUserInterface(name, vorname);   //----------------------------------------------------
				}
			}catch(Exception ex){
				
			}
			
			
		}else if(eFace != null && e.getSource() == eFace.getSpeichern()){
			/**
			 * Hier müssen die choices von Elter_Interface abgerufen und in der Datenbank gespeichert werden,
			 * und die Eingaben auf ihrer Einzigartigkeit überprüft werden. 
			 * 
			 * 
			 * 
			 */
		}else if(eFace != null && e.getSource() == eFace.getUpdate()){
			/**
			 * Hier müssen die choices mit den bereits gewählten Terminen geupdated werden.
			 * 
			 * 
			 * 
			 * 
			 */
		}
		
	}

	
}
