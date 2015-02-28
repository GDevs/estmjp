package estm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 */

/**
 * @author georg
 *
 */
public class Test {

	/**
	 * 
	 */
	public Test() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            System.out.println("Fehler beim Erzeugen einer neuen Instanz!");
            System.out.println(ex);
        }
        Connection conn = null;
		try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/mysql", "root", "toor");
        } catch (SQLException ex) {
            System.out.println("Fehler beim Aufbau der Verbindung!");
        }
		Person p = new Person("Peter", "Enis", 01, "SUPAADMIN");
		ESTM estm = new ESTM(conn, p);

		//estm.initialize();
		estm.parseLpSol(1);
	}

}
