package estm;

import java.util.List;

public class Lehrer {
private String name;
private String id;
private List<Termin> termine;
	public Lehrer(String pName,String pID) {
		name = pName;
		id = pID;
	}

	public String getName(){return  name;}
	
	public String getId(){return id;}
	
	public List<Termin> getTermine(){return termine;}
	
	public void addTermin(Termin termin){
		// Wenn !null muss termin in die Liste eingefügt werden
	}
}