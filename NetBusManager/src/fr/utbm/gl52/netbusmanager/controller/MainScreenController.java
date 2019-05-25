package fr.utbm.gl52.netbusmanager.controller;

import java.io.IOException;

import fr.utbm.gl52.netbusmanager.view.LineEditorView;
import fr.utbm.gl52.netbusmanager.view.NetworkView;
import fr.utbm.gl52.netbusmanager.view.ScheduleEditorView;
import fr.utbm.gl52.netbusmanager.view.StopEditorView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainScreenController {
	
	@FXML
	private VBox root;
	
	@FXML
	private  Button apercuButton;
	@FXML
	private Button editeurStopButton;	
	@FXML
	private Button editeurRouteButton;
	@FXML
	private Button editeurScheduleButton;
	


	@FXML 
	public void openEditeurStop() {
		
		try {
			App.getStage().setScene(new StopEditorView(FXMLLoader.load(getClass().getResource("StopEditor.fxml")))); //Permet de switch d'une scene 
																											//� l'autre en cr�eant une nouvelle scene avec le composant souhait� en root
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@FXML 
	public void openEditeurLineButton() {
		try {
			App.getStage().setScene(new LineEditorView(FXMLLoader.load(getClass().getResource("LineEditor.fxml"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void openScheduleEditor() {
		
		try {
			App.getStage().setScene(new ScheduleEditorView(FXMLLoader.load(getClass().getResource("ScheduleEditor.fxml"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@FXML
	public void openNetworkView(){
		try {
			App.getStage().setScene(new NetworkView(FXMLLoader.load(getClass().getResource("NetworkView.fxml"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
