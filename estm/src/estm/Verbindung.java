package estm;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Verbindung {
	java.sql.Connection conn;
	private Statement stmt;
	private ResultSet rs;

	public Verbindung() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			System.out.println("Fehler beim Erzeugen einer neuen Instanz!");
			System.out.println(ex);
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/estm",
					"root", "toor");
		} catch (SQLException ex) {
			System.out.println("Fehler beim Aufbau der Verbindung!" + ex);
		}
	}

	public ResultSet query(String pSelect) {
		ResultSet rs = null;
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