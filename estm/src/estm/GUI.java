package estm;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class GUI {

	
	private Verbindung verbindung;
	private Person user;
	private ESTM estm;
	private Login login;
	private Elter_Interface eFace;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new GUI();
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
        login = new Login(this,verbindung);
		login.setVisible(true);
		System.out.println("geht");
		
		
        //estm = new ESTM(verbindung, user);
	}
	
	public void prepareUserInterface(String name,String vorname){
		
		eFace = new Elter_Interface();
		eFace.setUsername("Herr/ Frau   "+ vorname +" "+ name+"  ihre Termine:");
		eFace.setVisible(true);
		
	}
	
	
		
	
	
	public void setPerson(Person p){ user = p;}
	
	

	
}
