package estm;

import java.util.ArrayList;
import java.util.List;

public class Person {

	private String name, vorname;
	private int ID;
	private List<Termin> termine;
	private String status;
	
	public Person(String name, String vorname, int ID, String status){
		this.name = name;
		this.vorname = vorname;
		this.ID = ID;
		this.status = status;
		termine = new ArrayList<Termin>();
	}
	
	public String getName(){ return name;}
	
	public String getVorname(){return vorname;}
	
	public int getID(){return ID;}
	
	public List<Termin> getTermine(){return termine;}
	
	public void addTermin(Termin t){
		termine.add(t);
	}
	
	public String getStatus(){return status;}
}
