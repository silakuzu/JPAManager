import java.util.ArrayList;

public class MainClass {

	public static void main(String[] args) 
	{
		//from JDBC
		ArrayList<Country> allCountries = SilaKuzuJPAManager.readFromFile("world.txt");

		SilaKuzuJPAManager.writeIntoTable(allCountries);
		
		System.out.println(SilaKuzuJPAManager.getCountryByID(181).getName());
		
		SilaKuzuJPAManager.deleteCountryByID(8);
		
		SilaKuzuJPAManager.updateCountryPopulationByID(4, 35);
	}
}
