import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Country {

	@Id
	private int id;
	private String name;
	private String continent;
	private String capital;
	private int population;
	
	
	public Country() {
		this.id = 0;
		this.name = "nowhere";
		this.continent = "no continent";
		this.capital = "no capital";
		this.population = 0;
	}
	
	public Country (int tableId, String cntry, String cont, String capit, int popu) {
		this.id = tableId;
		this.name = cntry;
		this.continent = cont;
		this.capital = capit;
		this.population = popu;
	}
	
	//getters
	public int getID() {
		return id;
	}
	
	public String getName () {
		return name;
	}
	
	public String getContinent() {
		return continent;
	}
	
	public String getCapital() {
		return capital;
	}
	
	public int getPopulation() {
		return population;
	}
	
	
	//setters
	public void setID(int newId) {
		this.id = newId;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public void setContinent(String newCont) {
		this.continent = newCont;
	}
	
	public void setCapital(String newCapital) {
		this.capital = newCapital;
	}
	
	public void setPopulation(int newPopu) {
		this.population = newPopu;
	}
}
