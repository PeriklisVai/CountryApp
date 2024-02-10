import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import exception.CountryAPIException;
import model.CountryInfo;
import services.CountryAPIService;
import services.CountryAPIService.SearchMode;

public class CountryAPITest {
	
	private SearchMode modeALL = SearchMode.ALL;
	private SearchMode modeName = SearchMode.NAME;
	private SearchMode modeLanguage = SearchMode.LANGUAGE;
	private SearchMode modeCurrency = SearchMode.CURRENCY;
	
	//TODO: check for nothing found error
	@Test
	public void testSearchMode() throws CountryAPIException{
		final CountryAPIService countryService = CountryAPI.getCountryDBService();
		final List<CountryInfo> results = countryService.countrySearch(SearchMode.NAME, "Russia");//try different mode and values
		Assert.assertFalse(results.isEmpty());
		results.forEach(System.out::println);
	}

}
