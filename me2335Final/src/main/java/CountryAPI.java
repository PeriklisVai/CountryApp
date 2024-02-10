import services.CountryAPIService;

public class CountryAPI {
	
	public static CountryAPIService getCountryDBService() {
		return new CountryAPIService("https://restcountries.com");
	}
}
