package estm;

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
		Verbindung verbindung = new Verbindung();
		ResultSet rs = verbindung.query("SHOW TABLES");
		
		try {
			System.out.println(rs.getString(0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Person p = new Person("Peter", "Enis", 01, "SUPAADMIN");
		//ESTM estm = new ESTM(verbindung, p);

		//estm.initialize();
	}

}
