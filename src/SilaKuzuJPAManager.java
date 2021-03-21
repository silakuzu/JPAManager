import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SilaKuzuJPAManager {
	

	public static ArrayList<Country> readFromFile ( String filename ) 
	{
		ArrayList<Country> countries = new ArrayList<Country>();
		
		try 
		{
			FileReader reader = new FileReader(filename);
			BufferedReader bfr = new BufferedReader(reader);
			int id = 1;
			while(true)
			{
				String line = bfr.readLine();
				if (line == null)
				{
					break;
				}
				//System.out.println(line);
				String[] arr = line.split(",");
				int tableId = id;
				String name= arr[0];
				String cont= arr[1];
				String capit = arr[2];
				String popu = arr[3];
				int x = Integer.parseInt(popu);
				Country c = new Country(tableId, name, cont, capit, x);
				
				countries.add(c);
				id++;
			}
			reader.close();
		
		}
		catch (FileNotFoundException e) {
			System.out.println("no file");
			e.printStackTrace();
		}
		catch (IOException e) {
			System.out.println("no have rights to read that file");
			e.printStackTrace();
		}
		return countries;
	}
	
	
	public static void writeIntoTable ( ArrayList<Country> countries )
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cs310");
		EntityManager entityManager =emf.createEntityManager();
		
		try
		{
			entityManager.getTransaction().begin();
			for (Country c : countries)
			{
				Country newCntry= new Country(c.getID(), c.getName(), c.getContinent(), c.getCapital(), c.getPopulation());
				entityManager.persist(newCntry);
			}
			
			entityManager.getTransaction().commit();
			
			System.out.println("Data inserted!!!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public static Country getCountryByID (int countryID)
	{
		Country info = new Country();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cs310");
		EntityManager entityManager =emf.createEntityManager();
		try
		{
			entityManager.getTransaction().begin();

			Country found = entityManager.find(Country.class, countryID);
			info.setID(found.getID());
			info.setName(found.getName());
			info.setContinent(found.getContinent());
			info.setCapital(found.getCapital());
			info.setPopulation(found.getPopulation());
			
			entityManager.getTransaction().commit();

			System.out.println("Data is taken from the database!!!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}
	
	
	public static void deleteCountryByID (int countryID)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cs310");
		EntityManager entityManager =emf.createEntityManager();
		try
		{
			entityManager.getTransaction().begin();
			Country found = entityManager.find(Country.class, countryID);
			
			entityManager.remove(found);
			entityManager.getTransaction().commit();

			System.out.println("Data deleted!!!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public static void updateCountryPopulationByID (int countryID,int population_) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cs310");
		EntityManager entityManager = emf.createEntityManager();
		
		try
		{
			Country country = entityManager.find(Country.class, countryID);
			entityManager.getTransaction().begin();
			
			country.setPopulation(population_);
			entityManager.merge(country);

			entityManager.getTransaction().commit();

			System.out.println("Data updated!!!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
