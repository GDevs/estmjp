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
import java.sql.ResultSetMetaData;
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
	public ESTM(Connection connection, Person user) {
		this.verbindung = verbindung;
		this.user = user;
		this.conn = connection;
	}
	
	/**
	 * Falls die angegebene Datenbank leer ist kann sie initialisiert werden.
	 */
	public void initialize(){
		BufferedReader br = null;
		String createDtb[] = new String[4];
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader("F:/source/estmjp/estm/src/estm/database/dtbInit.sql"));
			int i = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				createDtb[i] = sCurrentLine;
				i++;
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
		ResultSet rs;
		try {
			for (String q : createDtb){
				Statement stmt = conn.createStatement();
				stmt.execute(q);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		//return false;
    	
		ResultSetMetaData rsmd;
		try {
			rsmd = rs.getMetaData();
			if(rsmd.getTableName(0).equals("personen")){
				eltern.clear();
				lehrer.clear();
				Person person = new Person(rs.getString("name"),rs.getString("vorname"),rs.getInt("ID"),rs.getString("status"));
				if(person.getStatus().equals("L")){
					lehrer.add(person);
			    }else if(person.getStatus().equals("E")){
			    	eltern.add(person);
			    }
			}
			if(rsmd.getTableName(0).equals("termine")){
				Person pElter = null;
				int person1ID = rs.getInt("person1");
				Person pLehrer = null;
				int person2ID = rs.getInt("person2");
				for(Person p:eltern){
					if(p.getID()==person1ID||p.getID()==person2ID){
						if(p.getStatus().equals("E")){
							pElter=p;
						}else{
							pLehrer = p;
						}
					}
				}
				for(Person p:lehrer){
					if(p.getID()==person1ID||p.getID()==person2ID){
						if(p.getStatus().equals("E")){
							pElter=p;
						}else{
							pLehrer = p;
						}
					}
				}
				if(pElter==null || pLehrer== null){return false;}//Wenn eine der Personen nicht gefunden werden konnte, wird abgebrochen
				Termin termin = new Termin(rs.getInt("zeitpunkt"),pElter , pLehrer);
				pElter.addTermin(termin);
				pLehrer.addTermin(termin);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
    }
}
