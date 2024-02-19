package app;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import services.CountryAPI;
import services.CountryAPIService;
/**
 * JavaFX App
 */
public class App extends Application {
	
	//Stage
	static Stage primaryStage;
	
	static Scene mainScene;

	public static CountryAPIService mainCountryAPIService;
	
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
    	
        MainSceneCreator mainSceneCreator = new MainSceneCreator(1000, 550);
        mainScene = mainSceneCreator.createScene();
        
    	primaryStage.setTitle("CountryApp");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

	public static void main(String[] args) {
        mainCountryAPIService = CountryAPI.getCountryDBService();
        launch();
    }

}