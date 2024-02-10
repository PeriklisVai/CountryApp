package services;

import java.io.IOException;
import java.util.ArrayList;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
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
import model.CountryInfo;
import model.restcountries.CountryResult;
import model.restcountries.ErrorResponse;

public class CountryAPIService {
	private final String API_URL;

	public enum SearchMode {
		ALL, NAME, LANGUAGE, CURRENCY
	}

	public CountryAPIService(String aPI_URL) {
		super();
		API_URL = aPI_URL;
	}

	// 1. Ανάκτηση λίστας όλων των χωρών.
	// https://restcountries.com/v3.1/all

	// 2. Ανάκτηση πληροφοριών για μια συγκεκριμένη χώρα με χρήση του ονόματός της.
	// https://restcountries.com/v3.1/name/{name}

	// 3. Ανάκτηση λίστας χωρών που μιλούν μία συγκεκριμένη γλώσσα (π.χ. Ισπανικά).
	// https://restcountries.com/v3.1/lang/{language}

	// 4. Ανάκτηση λίστας χωρών που χρησιμοποιούν ένα συγκεκριμένο νόμισμα (π.χ.
	// ευρώ).
	// https://restcountries.com/v3.1/currency/{currency}

	public List<CountryInfo> countrySearch(SearchMode searchMode, String searchValue) throws CountryAPIException {
		String apiFunction = null;
		String returnedFields = "name,capital,currencies,continents,population";// fields to be returned

		List<CountryResult> countryResultList;

		// chooses which values to pass to the API call depending on Search Mode and
		// search parameter
		switch (searchMode) {
		case ALL:
			apiFunction = "all";
			searchValue = null;
			break;
		case NAME:
			apiFunction = "name";
			break;
		case LANGUAGE:
			apiFunction = "lang";
			break;
		case CURRENCY:
			apiFunction = "currency";
			break;
		default:
			// TODO: log4j print warning no searchMode identified
		}
		countryResultList = getAPIData(API_URL, apiFunction, searchValue, returnedFields);
		return getCountryListFromResult(countryResultList);

	}

	private List<CountryInfo> getCountryListFromResult(List<CountryResult> resultList) {
		List<CountryInfo> countryInfoList = new ArrayList<>(resultList.size());
		for (CountryResult countryResult : resultList) {
			countryInfoList.add(new CountryInfo(countryResult));
		}
		return countryInfoList;
	}

	// get API data
	private List<CountryResult> getAPIData(String API_URL, String searchKey, String searchValue, String parameterValues)
			throws CountryAPIException {

		try {
			final URIBuilder uriBuilder;

			if (searchValue != null)
				uriBuilder = new URIBuilder(API_URL).setPathSegments("v3.1", searchKey, searchValue);
			else
				uriBuilder = new URIBuilder(API_URL).setPathSegments("v3.1", searchKey);// searchKey is all

			if (parameterValues != null)
				uriBuilder.setParameter("fields", parameterValues);

			// Add query parameter for fields

			final URI url = uriBuilder.build();

			// HttpClient
			final HttpGet getRequest = new HttpGet(url);

			final CloseableHttpClient httpClient = HttpClients.createDefault();

			try (CloseableHttpResponse response = httpClient.execute(getRequest)) {
				final HttpEntity entity = response.getEntity();

				final ObjectMapper mapper = new ObjectMapper();

				if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
					ErrorResponse errorResponse = mapper.readValue(entity.getContent(), ErrorResponse.class);
					if (errorResponse.getStatusMessage() != null)
						throw new CountryAPIException(
								"Error occurred on API call: " + errorResponse.getStatusMessage());
				}

				List<CountryResult> countryResults = mapper.readValue(entity.getContent(),
						new TypeReference<List<CountryResult>>() {
						});
				return countryResults;
			} catch (IOException e) {
				throw new CountryAPIException("Error during the http request", e);
			}
		} catch (URISyntaxException e) {
			throw new CountryAPIException("Unable to create request URL", e);
		}
	}
}