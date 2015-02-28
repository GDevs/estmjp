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
		for (int j = 1; j <= 50; j++){
			termine.add(j);
		}
		
		for (i = 1; i <= 50; i++){
			name = "Lnach" + i;
			vorname = "Lvor" + i;
			ID = i;
			status = "L";
			personen.add(new Person(name, vorname, ID, status));
		}
		for (i = 51; i <= 950; i++){
			name = "Enach" + i;
			vorname = "Evor" + i;
			ID = i;
			status = "E";
			personen.add(new Person(name, vorname, ID, status));
		}
		Random random = new Random();
		for (Person j : personen){
			if (j.getStatus().equals("E")){
				for (int k = 0; k <= 2; k++){
					wünsche.add(new Termin(random.nextInt(4) + 1, j, getRandomLehrer()));
				}
			}
		}
		
		writeToDat();
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
				writer.println("Wunsch: L" + i.getLehrer().getID() + " E" + i.getElter().getID() + " " + i.getZeitschiene());
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
		int rn = random.nextInt((5-0)) + 0;
		
		return personen.get(rn);
	}

}
