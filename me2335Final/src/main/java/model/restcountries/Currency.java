package model.restcountries;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Currency {
	@JsonProperty("name")
	public String name;
	@JsonProperty("symbol")
	public String symbol;
	
	@Override
	public String toString()
	{
		String returnString = String.format("Currency \nName: %s, Symbol: %s", name, symbol);
		return returnString;
	}
	
}
