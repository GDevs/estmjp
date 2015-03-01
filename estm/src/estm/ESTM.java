/**		
 * 
 */
package estm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adiran Hinrichs, Ben Müller, Georg Dorndorf
 * 
 */
public class ESTM {
	//private Verbindung verbindung;
	private Connection conn;

	private List<Person> eltern;
	private List<Person> lehrer;
	private List<Termin> wuensche;

	private Person user;

	/**
	 * 
	 */
	public ESTM(Connection connection, Person user) {
		//this.verbindung = verbindung;
		this.user = user;
		this.conn = connection;
	}

	/**
	 * Falls die angegebene Datenbank leer ist kann sie initialisiert werden.
	 */
	public void initialize() {
		BufferedReader br = null;
		String createDtb[] = new String[4];
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(
					"F:/source/estmjp/estm/src/estm/database/dtbInit.sql"));
			int i = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				createDtb[i] = sCurrentLine;
				i++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		ResultSet rs;
		try {
			for (String q : createDtb) {
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
	 * @param SQL
	 *            query
	 * @return boolean query erfolgreich
	 */
	public boolean query(String query) {
		ResultSet rs = null;
		// System.out.println("SQL Befehl:"+pSelect);
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(query);
			rs = stmt.getResultSet();
			if (process(rs)) {
				rs.close();
				stmt.close();
				return true;
			}
		} catch (SQLException ex) {
			// System.out.println("Fehler beim Statement!");
		}
		return false;
	}

	/**
	 * 
	 * @param rs
	 *            Querry ergebniss
	 * @return boolean process erfolgreich
	 */
	private boolean process(ResultSet rs) {
		// wenn daten zum parsen sind dann parse(rs)
		// wenn daten kennwortAbfrage o.ä. dann weiter processen
		return false;
	}

	/**
	 * 
	 * @return
	 */
	private boolean mapping(ResultSet rs) {
		// return false;

		ResultSetMetaData rsmd;
		try {
			rsmd = rs.getMetaData();
			if (rsmd.getTableName(0).equals("personen")) {
				eltern.clear();
				lehrer.clear();
				Person person = new Person(rs.getString("name"),
						rs.getString("vorname"), rs.getInt("ID"),
						rs.getString("status"));
				if (person.getStatus().equals("L")) {
					lehrer.add(person);
				} else if (person.getStatus().equals("E")) {
					eltern.add(person);
				}
			} else if (rsmd.getTableName(0).equals("termine")) {
				Person pElter = null;
				int person1ID = rs.getInt("person1");
				Person pLehrer = null;
				int person2ID = rs.getInt("person2");
				for (Person p : eltern) {
					if (p.getID() == person1ID || p.getID() == person2ID) {
						if (p.getStatus().equals("E")) {
							pElter = p;
						} else {
							pLehrer = p;
						}
					}
				}
				for (Person p : lehrer) {
					if (p.getID() == person1ID || p.getID() == person2ID) {
						if (p.getStatus().equals("E")) {
							pElter = p;
						} else {
							pLehrer = p;
						}
					}
				}
				if (pElter == null || pLehrer == null) {
					return false;
				}// Wenn eine der Personen nicht gefunden werden konnte, wird
					// abgebrochen
				Termin termin = new Termin(rs.getInt("zeitpunkt"), pElter,
						pLehrer);
				pElter.addTermin(termin);
				pLehrer.addTermin(termin);
			} else if (rsmd.getTableName(0).equals("terminwunsch")) {
				Person pElter = null;
				int person1ID = rs.getInt("person1");
				Person pLehrer = null;
				int person2ID = rs.getInt("person2");
				for (Person p : eltern) {
					if (p.getID() == person1ID || p.getID() == person2ID) {
						if (p.getStatus().equals("E")) {
							pElter = p;
						} else {
							pLehrer = p;
						}
					}
				}
				for (Person p : lehrer) {
					if (p.getID() == person1ID || p.getID() == person2ID) {
						if (p.getStatus().equals("E")) {
							pElter = p;
						} else {
							pLehrer = p;
						}
					}
				}
				if (pElter == null || pLehrer == null) {
					return false;
				}// Wenn eine der Personen nicht gefunden werden konnte, wird
					// abgebrochen
				Termin termin = new Termin(rs.getInt("zeitpunkt"), pElter,
						pLehrer);
				wuensche.add(termin);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Übersetzt die ausgabe Datei des LP solvers in die relationale Datenbankstruktur
	 */
	public void parseLpSol(int version){
		try {
			Statement stmt = conn.createStatement();
			stmt.execute("DELETE FROM `termine` WHERE `version`=" + version);
			
			List<String> termine = new ArrayList<String>(); 
			BufferedReader br;
			br = new BufferedReader(new FileReader(
					"F:/source/estmjp/estm/src/estm/modell/solution.dat"));
			
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				termine.add(sCurrentLine);
			}
			
			for (String termin : termine){
				String t[];
				//System.out.println(termin);
				t = termin.split("\\$|#| ");
				System.out.println(" Termin: " + t[1] + " Lehrer: " +  t[2] + " Elter: " + t[3]);
				if(t[0].equals("x")){
					String q = "INSERT INTO termine(person1, person2, zeitpunkt, version) "
							+ "VALUES(" + t[2] + "," + t[3] + "," + t[1] + "," + version + ");";
					System.out.println(q);
					System.out.println("sql query erfolgreich");
					//Statement stmt = conn.createStatement();
					stmt.execute(q);
				}
			}
			
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * @param pPerson
	 */
	public void insertPersonIntoDatabase(Person pPerson){
		try{
		Statement stmt = conn.createStatement();
		String request = "INSERT INTO person(Name, Vorname, Rechte, Status, Kennwort, ID) "
				         + "VALUES("+pPerson.getName()+","+pPerson.getVorname()+","+pPerson.getRechte()+","
				                    +pPerson.getStatus()+","+pPerson.getPassword()+","+pPerson.getID()+");";
		stmt.execute(request);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * @param pTermin
	 */
	public void insertTerminwunschIntoDatabase(Termin pTermin){
		try{
		Statement stmt = conn.createStatement();
		String request = "INSERT INTO terminwunsch(Person1, Person2, Zeitschiene) "
				         + "VALUES("+pTermin.getLehrer()+","+pTermin.getElter()+","+pTermin.getZeitschiene()+ ");";
		stmt.execute(request);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void addUsrFromFile(String path){
		try {
			List<String> personen = new ArrayList<String>(); 
			BufferedReader br;
			br = new BufferedReader(new FileReader(
					"F:/source/estmjp/estm/src/estm/modell/solution.dat"));
			
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				personen.add(sCurrentLine);
			}
			Statement stmt = conn.createStatement();
			
			for (String person : personen){
				String t[];
				//System.out.println(termin);
				t = person.split(";");
				//System.out.println(" Termin: " + t[1] + " Lehrer: " +  t[2] + " Elter: " + t[3]);
				if(!t[0].equals("#")){
					String q = "INSERT INTO personen(ID, name, vorname, rechte, status, kennwort) "
							+ "VALUES(" + t[0] + "," + t[1] + "," + t[2] + "," + t[3] + "," + 
							t[4] + "," + t[5] +");";
					System.out.println(q);
					System.out.println("sql query erfolgreich");		
					stmt.execute(q);
				}
			}
			
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
