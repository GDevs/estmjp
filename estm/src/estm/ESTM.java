/**		
 * 
 */
package estm;

import java.util.List;

/**
 * @author Adiran Hinrichs, Ben Müller, Georg Dorndorf
 *
 */
public class ESTM {
	private Verbindung verbindung;
	
	private List<Elter> eltern;
	private List<Lehrer> lehrer;
	
	
	/**
	 * 
	 */
	public ESTM() {
		
	}
	
	/**
	 * Baut eine Verbindung zu einer Datenbank auf
	 * @return Ob die Verbindung erfolgreich war
	 */
	public boolean connect(String ip, String user, String password){
		verbindung = new Verbindung();
		
		return verbindung.verbunden(10);
	}

}
