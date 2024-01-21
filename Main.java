import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		File zachary1=new File("karate_club_network.txt");
		File facebook1=new File("facebook_social_network.txt");
		Graph zachary=new Graph(3000);
		try {
			Scanner scan=new Scanner(zachary1);
			while(scan.hasNextLine()) {
				String[] reader=scan.nextLine().split(" ");
				zachary.addEdge(reader[0], reader[1]);;
			}
			scan.close();		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
		Graph facebook=new Graph(2000);
		try {
			Scanner scan=new Scanner(facebook1);
			while(scan.hasNextLine()) {
				String[] reader=scan.nextLine().split(" ");
				facebook.addEdge(reader[0], reader[1]);;
			}
			scan.close();		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		zachary.mainfunction("Zachary");
		facebook.mainfunction("Facebook");
		
	}

	
}
