package app;


import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import exception.CountryAPIException;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.CountryInfo;
import services.CountryAPIService.SearchMode;

public class MainSceneCreator extends SceneCreator implements EventHandler<MouseEvent>{
	
	private static final Logger logger = LogManager.getLogger();
	
	Scene mainScene;

	FlowPane searchBtnsContainerFlowPane, searchBoxContainerPane,textContainerFlowPane;
	GridPane rootGridPane, searchGridPane;
	
	//Choose search parameter
	ChoiceBox<String> searchModesChoiceBox;
	
	//For user to type search and history selections dropdown
	ComboBox<String> searchBox;
	
	//Button for search
	Button searchBtn;
	
	//display results text
	Text resultMsg;
	
	//Table view
	TableView<CountryInfo> countryTableView;
	//List of countries
	List<CountryInfo> countryList;
	
	public MainSceneCreator(double width, double height) {
		super(width, height);
        
		//create a list that holds previous searches
		SearchItem.InitiateHistoryList();
		      
        initializeSearchBox();
        initializeModesChoiceBox();
        initializeSearchBtn();    
        createCountryTableView();    
        createSearchGridPane(); 
        createSearchBtnsContainerFlowPane();
        createTextContainerFlowPane();
        createRootGridPane();        
	}

	private void createSearchBtnsContainerFlowPane() {
		searchBtnsContainerFlowPane = new FlowPane();
        searchBtnsContainerFlowPane.getChildren().add(searchGridPane);
        searchBtnsContainerFlowPane.setAlignment(Pos.CENTER);
	}

	private void createTextContainerFlowPane() {
		
		resultMsg = new Text("Choose which category you want to search and type your search querry");
		
		textContainerFlowPane = new FlowPane();
        textContainerFlowPane.getChildren().add(resultMsg);
        textContainerFlowPane.setAlignment(Pos.CENTER);       
	}

	private void createRootGridPane() {
		rootGridPane = new GridPane();
		
        rootGridPane.setHgap(10); // Horizontal gap between columns
        rootGridPane.setVgap(10); // Vertical gap between rows
        rootGridPane.setPadding(new Insets(10, 10, 10, 20)); 
        
        rootGridPane.add(countryTableView, 0, 0);
        rootGridPane.add(textContainerFlowPane, 0, 1);
        rootGridPane.add(searchBtnsContainerFlowPane, 0, 2);	
	}

	//create gridPane to hold components related to making a search
	private void createSearchGridPane() {
		searchGridPane = new GridPane();
        searchGridPane.setHgap(10); // Horizontal gap between columns
        searchGridPane.setVgap(10); // Vertical gap between rows
        
        // Make searchBox span two columns
        // Add the searchBox to the grid pane
        // Add the label to row 0, column 0
        GridPane.setColumnSpan(searchBox, 2);
        searchGridPane.add(searchBox, 0, 0); 
        searchGridPane.add(searchModesChoiceBox, 0, 1); 
        searchGridPane.add(searchBtn, 1, 1); 
	}

	private void initializeSearchBtn() {
		searchBtn = new Button("Search");
        searchBtn.setOnMouseClicked(this);
        searchBtn.setMinSize(120, 30);
	}

	private void initializeModesChoiceBox() {
		searchModesChoiceBox = new ChoiceBox<String>();
        fillSearchChoiceBox();
        searchModesChoiceBox.setMinSize(120, 30);
	}

	private void initializeSearchBox() {
		searchBox = new ComboBox<String>(); 
        searchBox.setEditable(true);
        
        // Set prompt text
        searchBox.setPromptText("Search");
        
        // Set a placeholder hint
        Label placeholderHint = new Label("No previous searches yet");
        searchBox.setPlaceholder(placeholderHint);
        searchBox.setPrefSize(250, 30);
        
        // Add a listener to the showing property of the ComboBox popup
        searchBox.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue && searchBox.getItems().isEmpty()) {
            	searchBox.setPlaceholder(placeholderHint);
            } else {
            	searchBox.setPlaceholder(null); // Remove the placeholder when the popup is not showing
            }
        });	
	}

	private void createCountryTableView() {
		
		countryTableView = new TableView<CountryInfo>();
        countryTableView.setPrefWidth(950);
        countryTableView.setPrefHeight(400);
		
		//create column for country's name
		TableColumn<CountryInfo, String> countryNameColumn = new TableColumn<>("Country");
        countryNameColumn.setCellValueFactory(new PropertyValueFactory<>("nameCommon"));
        countryTableView.getColumns().add(countryNameColumn);
        
        //create column for capital
        TableColumn<CountryInfo, String> countryCapitalColumn = new TableColumn<>("Capital");
        countryCapitalColumn.setCellValueFactory(new PropertyValueFactory<>("capital"));
        countryTableView.getColumns().add(countryCapitalColumn);
        
        //create column for currencies
        TableColumn<CountryInfo, String> countryCurrenciesColumn = new TableColumn<>("Currencies");
        countryCurrenciesColumn.setCellValueFactory(new PropertyValueFactory<>("currencies"));
        countryTableView.getColumns().add(countryCurrenciesColumn);
        
        //create column for population
        TableColumn<CountryInfo, Integer> countryPopulationColumn = new TableColumn<>("Population");
        countryPopulationColumn.setCellValueFactory(new PropertyValueFactory<>("population"));
        countryTableView.getColumns().add(countryPopulationColumn);
                
        //create column for continents
        TableColumn<CountryInfo, String> countryContinentColumn = new TableColumn<>("Continent");
        countryContinentColumn.setCellValueFactory(new PropertyValueFactory<>("continent"));
        countryTableView.getColumns().add(countryContinentColumn);
        
        //set width of each column to 1/5 of table
        countryCurrenciesColumn.prefWidthProperty().bind(countryTableView.widthProperty().divide(5));
        countryCapitalColumn.prefWidthProperty().bind(countryTableView.widthProperty().divide(5));
        countryPopulationColumn.prefWidthProperty().bind(countryTableView.widthProperty().divide(5));
        countryContinentColumn.prefWidthProperty().bind(countryTableView.widthProperty().divide(5));
        countryNameColumn.prefWidthProperty().bind(countryTableView.widthProperty().divide(5));
	}

	//Set searchModes as choices
    private void fillSearchChoiceBox() {	
    	searchModesChoiceBox.getItems().add("All");
    	searchModesChoiceBox.getItems().add("Name");
    	searchModesChoiceBox.getItems().add("Language");
    	searchModesChoiceBox.getItems().add("Currency");
    	
    	searchModesChoiceBox.setValue("Name");//default value
	}
	
	@Override
	Scene createScene() {	
		return new Scene(rootGridPane, width, height);
	}

	@Override
	public void handle(MouseEvent event) {
		if(event.getSource()==searchBtn)
		{
			logger.debug("Search Button Clicked!");
					
			//get API's Search mode Enum from choicebox 
			SearchMode searchMode = SearchMode.valueOf(searchModesChoiceBox.getValue().toUpperCase());
			//read user's text
			String searchValue = searchBox.getEditor().getText().trim();
			
			SearchItem newSearch = new SearchItem(searchMode, searchValue);
				
			if(searchMode == SearchMode.ALL )
			{
				searchBox.getEditor().setText("");
			}
			else if(!newSearch.isSearchValueValid(newSearch.getValue()))
			{
				showWrongInputAlert();
				displayResultMsg("Invalid Input!");
				return;
			}
			try {
				countryList = App.mainCountryAPIService.countrySearch(searchMode, searchValue);
				tableSync();
				displayResultMsg();
				
				//if user is searching all countries no reason to save
				if(newSearch.getMode() != SearchMode.ALL)
				{
					SearchItem.addItemToHistory(newSearch);
					searchBoxSync();
				}	
			} catch (CountryAPIException e) {
				countryTableView.getItems().clear();
				displayResultMsg();
				countryTableView.setPlaceholder(new Label(e.getMessage()));
				e.printStackTrace();
			}
		}
	}

	public void tableSync() {
		List<CountryInfo> items = countryTableView.getItems();
		items.clear();

		for (CountryInfo country : countryList) {
			if (country instanceof CountryInfo) {
				items.add(country);
			}
		}
	}
	
	private void searchBoxSync() {
		
		//temporary store text value of search box
		String tempText = searchBox.getEditor().getText();
		
		//clear items so that search items are not added again
		searchBox.getItems().clear();
		
		//set again the value that got cleared(only want the dropdown items to clear)
		searchBox.getEditor().setText(tempText);
		
		// Add items to the ComboBox
        for(SearchItem searchItem: SearchItem.getHistoryList())
        {
        	searchBox.getItems().add(searchItem.getValue());
        }
	}
	
	private void displayResultMsg(String text) {
		resultMsg.setText(text);
	}
	
	private void displayResultMsg() {
		int resultsReturnedCount = countryTableView.getItems().size();
		
		if(resultsReturnedCount > 1)
		{
			resultMsg.setText("Returned "+ resultsReturnedCount + " results");
		}
		else if(resultsReturnedCount == 0)
		{
			resultMsg.setText("No results returned");
		}
		else if(resultsReturnedCount == 1)
		{
			resultMsg.setText("Returned 1 result");
		}
	}
	
	private void showWrongInputAlert()
	{
		// Create an alert
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.setContentText("You can't make a search with blank field, numbers, symbols or no latin characters");

        // Display the alert
        alert.showAndWait();
	}
}
