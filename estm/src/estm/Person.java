package estm;

import java.util.ArrayList;
import java.util.List;

public class Person {

	private String name;
	private int ID;
	private List<Termin> termine;
	private String status;
	
	public Person(String name, int ID, String status){
		this.name = name;
		this.ID = ID;
		this.status = status;
		termine = new ArrayList<Termin>();
	}
	
	public String getName(){ return name;}
	
	public int getID(){return ID;}
	
	public List<Termin> getTermine(){return termine;}
	
	public void addTermin(){
		
	}
	
	public String getStatus(){return status;}
}
