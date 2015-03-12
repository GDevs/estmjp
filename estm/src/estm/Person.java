package estm;

import java.util.ArrayList;
import java.util.List;

public class Person {

	private String name, vorname;
	private int ID;
	private List<Termin> termine;
	private String status;
	
	private String password; // Das Passwort muss bei einer neuen Person die alle Attribute wie in der Datenbank hat auch vorhanden sein -Ben
    private int rechte;      // Fehlten auchnoch	
							 // Georg: Das passwort darf hier NUR in der gehashten form vorliegen
    
	public Person(int ID, String name, String vorname, int rechte, String status ,String password){
		this.ID = ID;
		this.name = name;
		this.vorname = vorname;
		this.status = status;
		this.rechte = rechte;
		this.password = password;
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
