package model;

public class CountryInfo {
	
	private final String name;
	
	private final String capital;
	
	private final String currency;
	
	private final int population;
	
	private final String continent;
	
	public CountryInfo(String name, String capital, String currency, int population, String continent) {
		super();
		this.name = name;
		this.capital = capital;
		this.currency = currency;
		this.population = population;
		this.continent = continent;
	}


	//Result Constructor


	public String getName() {
		return name;
	}


	public String getCapital() {
		return capital;
	}


	public String getCurrency() {
		return currency;
	}


	public int getPopulation() {
		return population;
	}


	public String getContinent() {
		return continent;
	}
	
	//toString
	@Override
	public String toString()
	{
		String objString = String.format("Country: %s,\nCapital: %s"
				+ "\nCurrency: %s\nPopulation: %d"
				+ "\nContinent: %s", name, capital, currency, population, continent);
		return objString;
	}
}
