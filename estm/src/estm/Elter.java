package estm;

import java.util.ArrayList;
import java.util.List;

public class Elter {
	private String name;
	private int ID;
	private List<Termin> termine;
	
	public Elter(String name, int ID){
		this.name = name;
		this.ID = ID;
		termine = new ArrayList<Termin>();
	}
	
	public String getName(){ return name;}
	
	public int getID(){return ID;}
	
	public List<Termin> getTermine(){return termine;}
	
	public void addTermin(){
		
	}
}
