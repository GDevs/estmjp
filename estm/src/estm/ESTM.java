/**		
 * 
 */
package estm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author Adiran Hinrichs, Ben Müller, Georg Dorndorf
 *
 */
public class ESTM {
	private Verbindung verbindung;
	private Connection conn;
	
	private List<Person> eltern;
	private List<Person> lehrer;
	
	private Person user;
	
	/**
	 * 
	 */
	public ESTM(Verbindung verbindung, Person user) {
		this.verbindung = verbindung;
		this.user = user;
	}
	
	/**
	 * Falls die angegebene Datenbank leer ist kann sie initialisiert werden.
	 */
	public void initialize(){
		BufferedReader br = null;
		String createDtb = "";
		try {
 
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader("F:/source/estmjp/estm/src/estm/database/dtbInit.sql"));
 
			while ((sCurrentLine = br.readLine()) != null) {
				createDtb += sCurrentLine;
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		ResultSet rs = verbindung.query(createDtb);
		System.out.println(rs.toString());
		
	}
	
	/**
	 * Baut eine Verbindung zu einer Datenbank auf
	 * @return Ob die Verbindung erfolgreich war
	 */
	public boolean connect(String ip, String user, String password){
         try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            System.out.println("Fehler beim Erzeugen einer neuen Instanz!");
            System.out.println(ex);
        }
        try{
         Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/estm", "root", "toor");
         return conn.isValid(5);
        } catch (SQLException ex) {
            System.out.println("Fehler beim Aufbau der Verbindung!");
        }
        return false;
    }
    
    
    /**
     * 
     * @param SQL query
     * @return boolean query erfolgreich
     */
    public boolean query(String query)
    {
        ResultSet rs = null;
      //    System.out.println("SQL Befehl:"+pSelect);
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            rs = stmt.getResultSet();
            if (process(rs)){
            	rs.close();
                stmt.close();
            	return true;
            }           
        } catch (SQLException ex) {
        	//System.out.println("Fehler beim Statement!");
        }
        return false;
    }

    /**
     * 
     * @param rs Querry ergebniss
     * @return boolean process erfolgreich
     */
    private boolean process(ResultSet rs){
    	// wenn daten zum parsen sind dann parse(rs)
    	// wenn daten kennwortAbfrage o.ä. dann weiter processen
    	return false;
    }
    
    /**
     * 
     * @return
     */
    private boolean parse(ResultSet rs){
		return false;
    	// HIER bitte den PARSER Adrian :-)
    	// darfst gerne das system mit dem processer und dem parser anpassen ;-) :D
    }
}
