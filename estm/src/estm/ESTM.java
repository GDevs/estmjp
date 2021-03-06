/**		
 * 
 */
package estm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adiran Hinrichs, Ben M�ller, Georg Dorndorf
 * 
 */
public class ESTM {
	private Verbindung verbindung;

	private List<Person> personen;
	//private List<Termin> wuensche;

	private Person user;

	/**
	 * 
	 */
	public ESTM(Verbindung verbindung, Person user) {
		this.verbindung = verbindung;
		this.user = user;
		personen = new ArrayList<Person>();

		ResultSet rs = verbindung.query("SELECT * FROM personen");
		mapping(rs);
		verbindung.closeStatement();/*
		rs = verbindung.query("SELECT * FROM terminwunsch");
    	mapping(rs);
		verbindung.closeStatement();*/
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
			for (String q : createDtb) {
				verbindung.query(q);
				verbindung.closeStatement();
			}
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
			if (rsmd.getTableName(1).equals("personen")) {
				personen.clear();
				if (rs.first()) {
					do {
						Person person = new Person(rs.getInt("ID"),
								rs.getString("name"), rs.getString("vorname"),
								rs.getInt("rechte"), rs.getString("status"),
								rs.getString("kennwort"));
						personen.add(person);
					} while (rs.next());
				}
			} else if (rsmd.getTableName(0).equals("termine")) {
				if (rs.first()) {
					do {
						Person pElter = null;
						int person1ID = rs.getInt("person1");
						Person pLehrer = null;
						int person2ID = rs.getInt("person2");
						for (Person p : personen) {
							if (p.getID() == person1ID
									|| p.getID() == person2ID) {
								if (p.getStatus().equals("E")) {
									pElter = p;
								} else {
									pLehrer = p;
								}
							}
						}
						if (pElter == null || pLehrer == null) {
							return false;
						}// Wenn eine der Personen nicht gefunden werden konnte,
							// wird
							// abgebrochen
						Termin termin = new Termin(rs.getInt("zeitpunkt"),
								pElter, pLehrer);
						pElter.addTermin(termin);
						pLehrer.addTermin(termin);
					} while (rs.next());
				}
			} else if (rsmd.getTableName(0).equals("terminwunsch")) {
				if (rs.first()) {
					Person pElter = null;
					int person1ID = rs.getInt("person1");
					Person pLehrer = null;
					int person2ID = rs.getInt("person2");
					for (Person p : personen) {
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
					}// Wenn eine der Personen nicht gefunden werden konnte,
						// wird
						// abgebrochen
					Termin termin = new Termin(rs.getInt("zeitpunkt"), pElter,
							pLehrer);
					//wuensche.add(termin);
				}
				while (rs.next())
					;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * �bersetzt die ausgabe Datei des LP solvers in die relationale
	 * Datenbankstruktur
	 */
	public void parseLpSol(int version) {
		try {
			verbindung.query("DELETE FROM `termine` WHERE `version`=" + version);
			//verbindung.closeStatement();

			List<String> termine = new ArrayList<String>();
			BufferedReader br;
			br = new BufferedReader(new FileReader(
					"src/estm/modell/solution.dat"));

			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				termine.add(sCurrentLine);
			}

			for (String termin : termine) {
				String t[];
				// System.out.println(termin);
				t = termin.split("\\$|#| ");
				System.out.println(" Termin: " + t[1] + " Lehrer: " + t[2]
						+ " Elter: " + t[3]);
				if (t[0].equals("x")) {
					String q = "INSERT INTO termine(person1, person2, zeitpunkt, version) "
							+ "VALUES("
							+ t[2]
							+ ","
							+ t[3]
							+ ","
							+ t[1]
							+ ","
							+ version + ");";
					System.out.println(q);
					System.out.println("sql query erfolgreich");
					// Statement stmt = conn.createStatement();
					verbindung.query(q);
					//verbindung.closeStatement();
				}
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param pPerson
	 */
	public boolean insertPersonIntoDatabase(Person person) {
		try {
			verbindung.query("INSERT INTO personen(`ID`, `name`, vorname, rechte, `status`, kennwort) "
					+ "VALUES("
					+ person.getID()
					+ ",\""
					+ person.getName()
					+ "\",\""
					+ person.getVorname()
					+ "\","
					+ person.getRechte()
					+ ",\""
					+ person.getStatus()
					+ "\",\""
					+ person.getPassword() + "\");");
			//verbindung.closeStatement();
			return true;
		} catch (Exception e) {
			e.printStackTrace(); // boolean f�rs error handling usw.
			return false;
		}
	}

	/**
	 * @param pTermin
	 */
	public void insertTerminwunschIntoDatabase(Termin pTermin) {
		try {
			verbindung.query("INSERT INTO terminwunsch(Person1, Person2, Zeitschiene) "
					+ "VALUES("
					+ pTermin.getLehrer()
					+ ","
					+ pTermin.getElter()
					+ ","
					+ pTermin.getZeitschiene()
					+ ");");
			//verbindung.closeStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Liest Benutzer aus einer csv Datei ein
	 * 
	 * @param path
	 *            Pfad zur csv Datei
	 */
	public boolean addUsrFromFile(String path) {
		try {
			List<String> personen = new ArrayList<String>();
			BufferedReader br;
			br = new BufferedReader(new FileReader(path));

			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				personen.add(sCurrentLine);
			}
			// Statement stmt = conn.createStatement();

			for (String person : personen) {
				String t[];
				t = person.split(";| ");
				if (!t[0].equals("#")) {
					Person p = new Person(Integer.parseInt(t[0]), t[1], t[2],
							Integer.parseInt(t[3]), t[4], t[5]);
					insertPersonIntoDatabase(p);
					// String q =
					// "INSERT INTO personen(`ID`, `name`, vorname, rechte, `status`, kennwort) "
					// + "VALUES(" + t[0] + ",\"" + t[1] + "\",\"" + t[2] +
					// "\"," + t[3] + ",\"" +
					// t[4] + "\",\"" + t[5] +"\");";
					// System.out.println(q);
					// System.out.println("sql query erfolgreich");
					// stmt.execute(q);
				}
			}
			br.close();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return false;
		}
	}

	
	private void writeUsrsToDat(){ 
		try { PrintWriter writer = new
			PrintWriter("F:/source/estmjp/estm/src/estm/usrs.csv", "ASCII"); for
			(Person i : personen){ writer.println(i.getID() + ";" + i.getName() + ";"
			+ i.getVorname() + ";" + i.getRechte() + ";" + i.getStatus() + ";" +
			  i.getPassword()); } writer.close(); 
	  } catch (Exception e) {
		  e.printStackTrace(); } System.out.println("DONE"); 
	}
	
	public Person getLehrer(int lehrerID){
		Person result = null;
		for(Person p:personen){
			if(p.getID()==lehrerID){
				result = p;
			}
		}
		return result;
	}
	
	private Termin[] getTermine(String ID) {
		
		ResultSet rs = verbindung.query("SELECT Zeitschiene "
				+ "FROM Terminwunsch "
				+ "WHERE Terminwunsch.ID = Person.ID");
		/**
		 * 
		 * muss noch zuende geschrieben werden!!
		 * 
		 * datenbank tabele terminwunsch soll zu nem array zusammengefasst werden welches alle 
		 * termine der Person der ID beinhalted
		 * 
		 * DATENBANKSTRUCKTUR �BERARBEITEN!!!
		 */
		
		
	
	}
}
