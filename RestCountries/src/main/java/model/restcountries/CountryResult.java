package model.restcountries;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

//POJO class for json deserialization
public class CountryResult {
	private static final Logger logger = LogManager.getLogger();
	
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
	
	public String getCurrenciesInfoString() {
		StringBuilder formattedString = new StringBuilder();

		int size = currencies.size(); // Get the size of the map
		int index = 0; // Initialize the index counter
		
		// Iterate over the map entries and print concatenate value pair
        for (Map.Entry<String, Currency> entry : currencies.entrySet()) {
        	String formattedCurrency = String.format("%s(%s)",entry.getValue().getName(), 
        			entry.getValue().getSymbol());
        	formattedString.append(formattedCurrency);
        	
        	// If it's not the last entry, append a comma and space
            if (index < size - 1) {
                formattedString.append(", ");
            }

            // Increment the index
            index++;
        }
        logger.debug("Currency fromatted String: " + formattedString);
		return formattedString.toString();
	}
	
	//Returns capitals separated by comma if more than one
	public String getCapitalNameString()
	{
		String concatenatedCapitalNames = String.join(", ", this.capitalName);
		return concatenatedCapitalNames;
	}
	
	//Returns continents separated by comma if more than one
	public String getContinentString()
	{
		String concatenatedContinents = String.join(", ", this.continents);
		return concatenatedContinents;
	}
}
