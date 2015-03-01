package estm;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LPTest {
	private static List<Person> personen;
	private static List<Termin> wünsche;
	private static List<Integer> termine; 
	private static int tz = 50;
	private static int lz = 60;
	private static int ez = 900;
	private static int wnpe = 2;
	
	public LPTest() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String args[]){

		
		personen = new ArrayList<Person>();
		wünsche = new ArrayList<Termin>();
		termine = new ArrayList<Integer>();
		String name;
		String vorname;
		int ID;
		String status;
		int i;
		for (int j = 1; j <= tz; j++){
			termine.add(j);
		}
		
		for (i = 1; i <= lz; i++){
			name = "Lnach" + i;
			vorname = "Lvor" + i;
			ID = i;
			status = "L";
			personen.add(new Person( ID, name, vorname, 1,status,"default"));
		}
		for (i = lz + 1; i <= ez+lz; i++){
			name = "Enach" + i;
			vorname = "Evor" + i;
			ID = i;
			status = "E";
			personen.add(new Person(ID, name, vorname, 0, status, "default"));
		}

		for (Person j : personen){
			if (j.getStatus().equals("E")){
				for (int k = 1; k <= wnpe; k++){
					wünsche.add(new Termin(getRandomTermin(), j, getRandomLehrer()));
				}
			}
		}
		
		writeToDat();
		writeUsrsToDat();
	}

	private static int getRandomTermin() {
		Random random = new Random();
		return random.nextInt(tz)+1;
	}

	private static void writeToDat() {
		try {
			PrintWriter writer = new PrintWriter("F:/source/estmjp/estm/src/estm/modell/test3.dat", "ASCII");
			for (Person i : personen){
				
				if(i.getStatus().equals("E")){
					writer.println("Elter: E"+ i.getID());
					System.out.println("Elter: Elter"+ i.getID());
				} else {
					writer.println("Lehrer: L"+ i.getID());
					System.out.println("Lehrer: "+ i.getID());
				}
				
			}
			for (int i : termine){
				writer.println("Termin: " + i);
			}
			for (Termin i : wünsche){
				writer.println("Wunsch: " + i.getLehrer().getID() + " " + i.getElter().getID() + " " + i.getZeitschiene());
			}
			writer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//writer.println("The first line");
		
		System.out.println("DONE");
	}

	private static Person getRandomLehrer() {
		Random random = new Random();
		int rn = random.nextInt((lz-0)) + 0;
		
		return personen.get(rn);
	}
	
	private static void writeUsrsToDat(){
		try {
			PrintWriter writer = new PrintWriter("F:/source/estmjp/estm/src/estm/usrs.csv", "ASCII");
			for (Person i : personen){
				writer.println(i.getID() + ";" + i.getName() + ";" + i.getVorname() + ";" + i.getRechte() + ";" + i.getStatus() + ";" + i.getPassword());
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		System.out.println("DONE");
	}

}
