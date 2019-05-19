/**
 * 
 */
package fr.utbm.gl52.netbusmanager.controller;

import fr.utbm.gl52.netbusmanager.model.Line;
import fr.utbm.gl52.netbusmanager.model.Stop;
import fr.utbm.gl52.netbusmanager.view.horaires.AutomaticHorairesLine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * @author Arthur
 *
 */
public class ScheduleEditorController {
	
	
	@FXML
	private GridPane root;
	@FXML
	public Button generateAutomaticSchedule;
	@FXML
	public Button modifyLineSchedule;
	@FXML 
	public Button homeButton;
	
	@FXML
	public Button modifyButton;
	
	
	@FXML
	public VBox chooseVBox;
	@FXML
	public Button chooseLineButton;
	
	@FXML
	public ComboBox<String> lineComboBox;
	
	
	
	//TODO à virer c'est juste pour faire des tests rapides
	public Stop stop1 = new Stop("Stop1", 10.0, 10.0);
	public Stop stop2 = new Stop("Stop2", 20.0, 20.0);
	public Stop stop3 = new Stop("Stop3", 30.0, 30.0);

	
	public boolean openAuto = false;
	
	
	
	
	@FXML
	public void openAutomaticLineSchedule() {
		openAuto = true;
		openChooseLineVBOX();
		
	}
	
	@FXML
	public void openChooseLineVBOX() {
		
		//TODO là aussi faut virer c'est jute pour faire les tests graphiques 
		Line line = new Line(0, "Ligne 0");
		line.addStopToLine(stop1);
		line.addStopToLine(stop2);
		line.addStopToLine(stop3);
		
		
		
		this.lineComboBox.getItems().add(line.getName());
		this.chooseVBox.setVisible(true);
//		this.lineComboBox.setVisible(true);
//		this.modifyButton.setVisible(true);
	}
	
	@FXML
	public void openModifyLineSchedule() {
		
	}
	
	
	@FXML
	public void goBackToMainScreen() {
		App.getStage().setScene(App.getMainScene());
	}
	
	@FXML
	public void onModifyButtonPressed(ActionEvent e){
		this.chooseVBox.setVisible(false);
		this.root.add(new AutomaticHorairesLine(), 1, 0);
		
	}

}
