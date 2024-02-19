package model;

import java.text.DecimalFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.restcountries.CountryResult;

public class CountryInfo {
	
	private static final Logger logger = LogManager.getLogger();
	
	private final String nameCommon;
	private final String nameOfficial;
	
	private final String capital;//concatenated if more than one, comma separated
	
	private final String currencies;//concatenated currency format: name(symbol),name(symbol)
	
	private final int population;
	
	private final String continent;
	
	public CountryInfo(String nameCommon,String nameOfficial, String capital, String currencies, int population, String continent) {
		super();
		this.nameCommon = nameCommon;
		this.nameOfficial = nameOfficial;
		this.capital = capital;
		this.currencies = currencies;
		this.population = population;
		this.continent = continent;
	}

	//Result Constructor
	public CountryInfo(CountryResult countryResult) {
		this.nameCommon = countryResult.getCountryName().getCommon();
		this.nameOfficial = countryResult.getCountryName().getOfficial();
		this.capital = countryResult.getCapitalNameString();
		this.currencies = countryResult.getCurrenciesInfoString();
		this.population = countryResult.getPopulation();
		this.continent = countryResult.getContinentString();
		
		logger.info("Country info retrieved: " + this.nameCommon);
	}
	
	public String getNameCommon() {
		return nameCommon;
	}

	public String getNameOfficial() {
		return nameOfficial;
	}

	public String getCapital() {
		return capital;
	}

	public String getCurrencies() {
		return currencies;
	}

	public int getPopulation() {
		return population;
	}

	public String getContinent() {
		return continent;
	}

	/*
	private String getFormattedInt(int population) {
		// Create a DecimalFormat with pattern "###,###"
        DecimalFormat formatter = new DecimalFormat("###,###");
        
        // Format the number using the DecimalFormat
        String formattedNumber = formatter.format(population);
        return formattedNumber;
	}*/
	
	//toString
	@Override
	public String toString()
	{
		String objString = String.format("Country: %s\nCapital: %s"
				+ "\nCurrency: %s\nPopulation: %d"
				+ "\nContinent: %s\n", nameCommon, capital, currencies, population, continent);
		return objString;
	}
}
