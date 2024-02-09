package model.restcountries;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

//TODO: Search for JsonInclude.Include.NON_NULL
public class CountryResult {
	
	@JsonProperty("name")
	public Name countryName;
	@JsonProperty("currencies")
	public Map<String, Currency> currencies;
	@JsonProperty("capital")
	public List<String> capitalName;
	@JsonProperty("population")
	public int population;
	@JsonProperty("continents")
	public List<String> continents;
	
	@Override
	public String toString(){
		String objString = String.format("Name: %s\nNative Name: %s\nCurrencies: %s\nCapital: %s"
				+ "\nPopulation: %d\nContinents: %s", countryName.toString(), countryName.getNativeNameString(), 
				getCurrenciesString(), capitalName.toString() ,population, continents.toString());
		return objString;
	}
	
	public String getCurrenciesString() {
		StringBuilder formattedString = new StringBuilder();
		// Iterate over the map entries and print each key-value pair
        for (Map.Entry<String, Currency> entry : currencies.entrySet()) {
        	formattedString.append(String.format("Key: %s, Value: %s\n", entry.getKey(), entry.getValue()));
        }
		return formattedString.toString();
	}
}
