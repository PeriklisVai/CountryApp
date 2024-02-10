package model.restcountries;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Name {
	@JsonProperty("official")
	private String official;
	@JsonProperty("common")
	private String common;
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
	        	formattedString.append(String.format("%s: %s\n", entry.getKey(), entry.getValue()));
	        }
		}
		catch (NullPointerException e)
		{
			return null;
		}
		return formattedString.toString();
	}

	@JsonProperty("official")
	public String getOfficial() {
		return official;
	}

	@JsonProperty("official")
	public void setOfficial(String official) {
		this.official = official;
	}

	@JsonProperty("common")
	public String getCommon() {
		return common;
	}

	@JsonProperty("common")
	public void setCommon(String common) {
		this.common = common;
	}

	@JsonProperty("nativeName")
	public Map<String, Name> getNativeName() {
		return nativeName;
	}

	@JsonProperty("nativeName")
	public void setNativeName(Map<String, Name> nativeName) {
		this.nativeName = nativeName;
	}
}
