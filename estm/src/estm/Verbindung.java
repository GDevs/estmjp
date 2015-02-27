package estm;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Verbindung
{
    java.sql.Connection conn;
    
    public Verbindung()
    {
         try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            System.out.println("Fehler beim Erzeugen einer neuen Instanz!");
        }
        try{
            conn = DriverManager.getConnection("jdbc:mysql://172.16.0.61/est", "root", "toor");
        } catch (SQLException ex) {
            System.out.println("Fehler beim Aufbau der Verbindung!");
        }
    }
    
    
    
    public String query(String pSelect)
    {
        String temp="";
      //    System.out.println("SQL Befehl:"+pSelect);
        try{
            Statement stmt = conn.createStatement();
            stmt.execute(pSelect);
            ResultSet rs = stmt.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();
            int spalten = rsmd.getColumnCount();
            while (rs.next()){
                for (int i = 1; i <= spalten; i++){
                    temp=temp+rs.getString(i);
                                    }
            }
            rs.close();
            stmt.close();            
        } catch (SQLException ex) {
            System.out.println("Fehler beim Statement!");
        }
     //   System.out.println("SQL Ergebnis: "+temp);
        return temp;
    }


/**
 * Prüft den Zustand der Verbindung
 * @param timeout
 * @return Ob die Verbindung aufgebaut ist.
 */
	public boolean verbunden(int timeout) {
		return conn.isValid(timeout);
	}
}