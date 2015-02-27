package estm;

/**
 * @author ACHinrichs
 *
 */


public class Termin {

	private int zeitschiene;
	private Person elter;
	private Person lehrer;
		
	public Termin(int zeitschiene,Person elter, Person lehrer){
		this.zeitschiene = zeitschiene;
		this.elter = elter;
		this.lehrer = lehrer; 
	}
		
	public int getZeitschiene(){
		return zeitschiene;
	}
	
	public Person getElter(){
		return elter;
	}
	
	public Person getLehrer(){
		return lehrer;
	}
}
