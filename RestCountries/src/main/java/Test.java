import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.restcountries.*;
import com.fasterxml.jackson.core.type.TypeReference;

public class Test {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		String jsonString = "[{\"name\":{\"common\":\"SaintBarthélemy\",\"official\":\"CollectivityofSaintBarthélemy\",\"nativeName\":{\"fra\":{\"official\":\"CollectivitédeSaint-Barthélemy\",\"common\":\"Saint-Barthélemy\"}}},\"currencies\":{\"EUR\":{\"name\":\"Euro\",\"symbol\":\"€\"}},\"capital\":[\"Gustavia\"],\"population\":4255,\"continents\":[\"NorthAmerica\"]},{\"name\":{\"common\":\"Monaco\",\"official\":\"PrincipalityofMonaco\",\"nativeName\":{\"fra\":{\"official\":\"PrincipautédeMonaco\",\"common\":\"Monaco\"}}},\"currencies\":{\"EUR\":{\"name\":\"Euro\",\"symbol\":\"€\"}},\"capital\":[\"Monaco\"],\"population\":39244,\"continents\":[\"Europe\"]}]";
				
		ObjectMapper mapper = new ObjectMapper();
		List<CountryResult> countryResults = mapper.readValue(jsonString, new TypeReference<List<CountryResult>>(){});
		
		//System.out.println(countryResults.get(0).toString());
	}

}
