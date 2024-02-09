package services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

import exception.CountryAPIException;
import model.restcountries.CountryResult;
import model.restcountries.ErrorResponse;

public class CountryAPIService {
	private final String API_URL;

	public CountryAPIService(String aPI_URL) {
		super();
		API_URL = aPI_URL;
	}
	
	//1. Ανάκτηση λίστας όλων των χωρών.
	//https://restcountries.com/v3.1/all
	
	//2. Ανάκτηση πληροφοριών για μια συγκεκριμένη χώρα με χρήση του ονόματός της.
	//https://restcountries.com/v3.1/name/{name}
	
	//3. Ανάκτηση λίστας χωρών που μιλούν μία συγκεκριμένη γλώσσα (π.χ. Ισπανικά).
	//https://restcountries.com/v3.1/lang/{language}
	
	//4. Ανάκτηση λίστας χωρών που χρησιμοποιούν ένα συγκεκριμένο νόμισμα (π.χ. ευρώ).
	//https://restcountries.com/v3.1/currency/{currency}
	
	
	//get API data
	private CountryResult getAPIData(String API_URL, String searchKey, String searchValue)throws CountryAPIException{
		
		try 
		{
			final URIBuilder uriBuilder = new URIBuilder(API_URL).setPathSegments("v3.1", searchKey, searchValue);
			final URI url = uriBuilder.build();
			
			//HttpClient
			final HttpGet getRequest = new HttpGet(url);
			
			final CloseableHttpClient httpClient = HttpClients.createDefault();
			
			try(CloseableHttpResponse response = httpClient.execute(getRequest)){
				final HttpEntity entity = response.getEntity();
				
				final ObjectMapper mapper = new ObjectMapper();
				
				if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
					ErrorResponse errorResponse = mapper.readValue(entity.getContent(), ErrorResponse.class);
					if(errorResponse.getStatusMessage() != null)
						throw new CountryAPIException("Error occurred on API call: " + errorResponse.getStatusMessage());
				}
			}
			catch(IOException e) {
				throw new CountryAPIException("Error during the http request", e);
			}
		}
		catch(URISyntaxException e) 
		{
			throw new CountryAPIException("Unable to create request URL", e);
		}
		return null;//TODO: ChANGE
	}
}