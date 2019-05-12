package fr.utbm.gl52.netbusmanager.controller;
import fr.utbm.gl52.netbusmanager.view.MainScreenView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
	
//	public static void main(String args[]) {
//		Stage 		
//		
//	}
	
    private static Stage guiStage;
    private static Scene mainScene;

    public static Stage getStage() {
        return guiStage;
    }
    
    public static Scene getMainScene() {
    	return mainScene;
    }
    
    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		guiStage = primaryStage;
		primaryStage.setTitle("NetBusManager");
		Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
		Scene mainScreen = new MainScreenView(root);
		mainScene = mainScreen;
		
        primaryStage.setScene(mainScreen);
        primaryStage.show();
		
		
	}

}
