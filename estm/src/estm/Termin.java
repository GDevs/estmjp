package estm;

/**
 * @author ACHinrichs
 *
 */
package estm;

public class Termin {

	private int zeitschiene;
	private Elter elter;
	private Lehrer lehrer;
		
	public Termin(int zeitschiene,Elter elter, Lehrer lehrer){
		this.zeitschiene = zeitschiene;
		this.elter = elter;
		this.lehrer = lehrer; 
	}
		
	public int getZeitschiene(){
		return zeitschiene
	}
	
	public Elter getElter(){
		return elter;
	}
	
	public Lehrer getLehrer(){
		return lehrer;
	}
}
