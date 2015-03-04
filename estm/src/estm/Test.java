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
            conn = DriverManager.getConnection("jdbc:mysql://localhost/estm", "root", "toor");
        } catch (SQLException ex) {
            System.out.println("Fehler beim Aufbau der Verbindung!");
        }
		Person p = new Person(0, "Peter", "Enis", 1, "L", "default");
		ESTM estm = new ESTM(new Verbindung(), p);

		//estm.initialize();
		estm.parseLpSol(2);
		estm.addUsrFromFile("src/estm/data/usrs.csv");
	}

}
