package model.restcountries;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

//TODO: Search for JsonInclude.Include.NON_NULL
public class CountryResult {
	
	@JsonProperty("name")
	private Name countryName;
	@JsonProperty("currencies")
	private Map<String, Currency> currencies;
	@JsonProperty("capital")
	private List<String> capitalName;
	@JsonProperty("population")
	private int population;
	@JsonProperty("continents")
	private List<String> continents;
	
	
	@JsonProperty("name")
	public Name getCountryName() {
		return countryName;
	}

	@JsonProperty("name")
	public void setCountryName(Name countryName) {
		this.countryName = countryName;
	}

	@JsonProperty("currencies")
	public Map<String, Currency> getCurrencies() {
		return currencies;
	}

	@JsonProperty("currencies")
	public void setCurrencies(Map<String, Currency> currencies) {
		this.currencies = currencies;
	}

	@JsonProperty("capital")
	public List<String> getCapitalName() {
		return capitalName;
	}

	@JsonProperty("capital")
	public void setCapitalName(List<String> capitalName) {
		this.capitalName = capitalName;
	}

	@JsonProperty("continents")
	public List<String> getContinents() {
		return continents;
	}

	@JsonProperty("continents")
	public void setContinents(List<String> continents) {
		this.continents = continents;
	}

	@JsonProperty("population")
	public int getPopulation() {
		return population;
	}

	@JsonProperty("population")
	public void setPopulation(int population) {
		this.population = population;
	}
	
	public String getCurrenciesString() {
		StringBuilder formattedString = new StringBuilder();
		// Iterate over the map entries and print each key-value pair
        for (Map.Entry<String, Currency> entry : currencies.entrySet()) {
        	formattedString.append(String.format("Key: %s, Value: %s\n", entry.getKey(), entry.getValue()));
        }
		return formattedString.toString();
	}
	
	@Override
	public String toString(){
		String objString = String.format("Name: %s\nNative Name: %s\nCurrencies: %s\nCapital: %s"
				+ "\nPopulation: %d\nContinents: %s", countryName.toString(), countryName.getNativeNameString(), 
				getCurrenciesString(), capitalName.toString() ,population, continents.toString());
		return objString;
	}
}
