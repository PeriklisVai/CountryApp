package model.restcountries;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Name {
	@JsonProperty("official")
	public String official;
	@JsonProperty("common")
	public String common;
	@JsonProperty("nativeName")
	private Map<String, Name> nativeName;
	
	@Override
	public String toString()
	{
		String returnString = String.format("Common: %s, Official: %s", common, official);
		return returnString;
	}
	
	public String getNativeNameString()
	{
		StringBuilder formattedString = new StringBuilder();
		try {	
			// Iterate over the map entries and print each key-value pair
	        for (Map.Entry<String, Name> entry : nativeName.entrySet()) {
	        	formattedString.append(String.format("Key: %s, Value: %s\n", entry.getKey(), entry.getValue()));
	        }
		}
		catch (NullPointerException e)
		{
			return null;
		}
		return formattedString.toString();
	}
}
