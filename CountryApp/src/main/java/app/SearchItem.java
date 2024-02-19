package app;

import services.CountryAPIService;
import services.CountryAPIService.SearchMode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.regex.Pattern;

public class SearchItem {
	
	private CountryAPIService.SearchMode mode;
	private String value;
	
	final private static int historyMaxSize = 5;
	private static List<SearchItem> historyList;
	
	public SearchItem(SearchMode mode, String value) {
		super();
		this.value = value;
		this.mode = mode;
	}
	
	public static List<SearchItem> getHistoryList() {
		return historyList;
	}
	
	public static void InitiateHistoryList() {
		SearchItem.historyList = new ArrayList<>(historyMaxSize);
	}
	
	//used to add an item to the beginning of history list
	public static void addItemToHistory(SearchItem item)
	{
		historyList.add(0, item);
		if(historyList.size() > historyMaxSize)
		{
			historyList.subList(historyMaxSize, historyList.size()).clear();
		}
	}
	
	public CountryAPIService.SearchMode getMode() {
		return mode;
	}

	public void setMode(CountryAPIService.SearchMode mode) {
		this.mode = mode;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	//check user's input
	public Boolean isSearchValueValid(String searchValue) {
		
		if(searchValue.trim().isEmpty() || containsSymbolsOrNumbers(searchValue))
		{
			return false;
		}
		else
			return true;
	}
	
	public static boolean containsSymbolsOrNumbers(String str) {
        // Regular expression to match symbols or numbers
        String regex = "[^a-zA-Z]";
        
        // Create a Pattern object
        Pattern pattern = Pattern.compile(regex);
        
        // Use the pattern to create a Matcher object
        return pattern.matcher(str).find();
    }
}
