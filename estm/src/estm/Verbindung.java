package estm;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

public class Verbindung {
	java.sql.Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private String datenbankAdresse;
	private String datenbankName;
	private String datenbankNutzer;
	private String datenbankKennwort;

	public Verbindung() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			System.out.println("Fehler beim Erzeugen einer neuen Instanz!");
			System.out.println(ex);
		}
		try {
			File f = new File("settings.ini");
			//if (!f.exists()) {
			    System.out.println("connect öffne connect!");
				Connect connect = new Connect();
				System.out.println("connect geschlossen!");
			//}
			try {
				Ini datei = new Ini(new File("settings.ini"));
				Ini.Section sektion = datei.get("datenbank");
			} catch (InvalidFileFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn = DriverManager.getConnection("jdbc:mysql://"
					+ datenbankAdresse + "/" + datenbankName, datenbankNutzer,
					datenbankKennwort);
		} catch (SQLException ex) {
			System.out.println("Fehler beim Aufbau der Verbindung!" + ex);
		}
	}

	public ResultSet query(String pSelect) {
		rs = null;
		// System.out.println("SQL Befehl:"+pSelect);
		try {
			stmt = conn.createStatement();
			stmt.execute(pSelect);
			rs = stmt.getResultSet();/*
									 * ResultSetMetaData rsmd =
									 * rs.getMetaData(); int spalten =
									 * rsmd.getColumnCount(); while (rs.next()){
									 * for (int i = 1; i <= spalten; i++){
									 * temp=temp+rs.getString(i); } }
									 */
			// rs.close();
			// stmt.close();
		} catch (SQLException ex) {
			System.out.println("Fehler beim Statement!");
		}
		// System.out.println("SQL Ergebnis: "+temp);
		return rs;
	}

	public void closeStatement() {
		try {
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Prüft den Zustand der Verbindung
	 * 
	 * @param timeout
	 * @return Ob die Verbindung aufgebaut ist.
	 */
	public boolean verbunden(int timeout) {
		try {
			return conn.isValid(timeout);
		} catch (SQLException e) {
			// e.printStackTrace();
			return false;
		}
	}
}