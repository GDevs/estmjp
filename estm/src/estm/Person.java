package estm;

import java.util.ArrayList;
import java.util.List;

public class Person {

	private String name, vorname;
	private int ID;
	private List<Termin> termine;
	private String status;
	
	private String password; 
    private int rechte;      
    
    
	public Person(int ID, String name, String vorname, int rechte, String status ,String passwort){
		this.ID = ID;
		this.name = name;
		this.vorname = vorname;
		this.status = status;
		this.rechte = rechte;
		this.password = passwort;
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
	
	public String getPassword(){return password;}
	
	public int getRechte(){return rechte;}
	
}
