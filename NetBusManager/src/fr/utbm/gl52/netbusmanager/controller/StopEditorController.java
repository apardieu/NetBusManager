package fr.utbm.gl52.netbusmanager.controller;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ResourceBundle;

import fr.utbm.gl52.netbusmanager.graphicalcomponents.MapContainer;
import fr.utbm.gl52.netbusmanager.model.Stop;
import fr.utbm.gl52.netbusmanager.model.StopEditor;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

public class StopEditorController implements Initializable {
	
	//TODO Rajouter un test sur les textfield pour pas rentrer n'importe quoi
	//TODO Rajouter l'implémentation avec la carte pour saisir les arrêts
	
	
	@FXML
	private GridPane root;
	@FXML
	private Button addStopButton;
	@FXML
	private Button modifyStopButton;
	@FXML
	private Button homeButton;
	
	
	/*Élements graphiques lors de l'ajout d'un arrêt*/
	@FXML
	private VBox addStopVBox;
	@FXML
	private TextField stopNameTextField;
	@FXML
	private TextField longitudeTextField;
	@FXML
	private TextField latitudeTextField;
	@FXML
	private TextField adressTextField;
	@FXML
	private ComboBox<String> communeComboBox;
	@FXML
	private Button validateButton;
	@FXML
	private Label informationAddLabel;

	
	private StopEditor model;
	
	/*Éléments graphiques lors de la modification d'un arrêt*/
	@FXML
	private VBox modifyStopVBox;
	@FXML
	private ComboBox<String> modifyComboBox;
	@FXML
	private Button modifyButton;
	
	

	private MapContainer mapContainer;
	private Stop stopToModify;
	
	
	
	
	public StopEditorController(){
		this.model = new StopEditor();

		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	
	
	
	
	
	@FXML
	public void addStopToDataBase(ActionEvent e) {
		

		stopNameTextField.setStyle("-fx-border-color : transparent");
		longitudeTextField.setStyle("-fx-border-color : transparent");
		latitudeTextField.setStyle("-fx-border-color : transparent");
		this.informationAddLabel.setVisible(false);

		
		if(stopNameTextField.getText().isEmpty()||longitudeTextField.getText().isEmpty()||latitudeTextField.getText().isEmpty()) {
			this.informationAddLabel.setText("Un champ obligatoire (*) n'a pas été remplis");
			this.informationAddLabel.setVisible(true);
			if(stopNameTextField.getText().isEmpty())
				stopNameTextField.setStyle("-fx-border-color : red");
			if(longitudeTextField.getText().isEmpty())
				longitudeTextField.setStyle("-fx-border-color : red");
			if(latitudeTextField.getText().isEmpty())
				latitudeTextField.setStyle("-fx-border-color : red");
		}
		else {
			Stop newStop = new Stop(stopNameTextField.getText(),Double.parseDouble(longitudeTextField.getText()),Double.parseDouble(latitudeTextField.getText()));
			if(model.isStopAlreadyExisting(newStop)&&this.stopToModify==null) {
				this.informationAddLabel.setText("Un arrêt portant le même nom ou se situant à la même position, existe déjà");
				this.informationAddLabel.setVisible(true);
			}
			else {
				this.model.getAvailableStopList().add(newStop);

				Alert alert = new Alert(AlertType.CONFIRMATION);
				
				ButtonType closeAddStopButtonType = new ButtonType("Retour à l'éditeur d'arrêts" ,ButtonData.CANCEL_CLOSE);
				ButtonType mainScreenButtonType = new ButtonType("Retour au menu principal",ButtonData.CANCEL_CLOSE);
			
				alert.getButtonTypes().setAll(closeAddStopButtonType, mainScreenButtonType);
				
				Optional<ButtonType> result;

				
				if(this.stopToModify==null) {
				alert.setTitle("Ajout de l'arrêt réussi");
				alert.setHeaderText("Ajout de l'arrêt réussi");
				alert.setContentText("L'arrêt : "+newStop.toString()+" a bien été ajouté à la base de données de l'application ");

				ButtonType addNewStopButtonType = new ButtonType("Ajouter un nouvel arrêt",ButtonData.CANCEL_CLOSE);

				alert.getButtonTypes().add(0,addNewStopButtonType);
				
				 result = alert.showAndWait();
				if(result.get()==addNewStopButtonType) {
					this.clearAddStopField();
				}

				}else {
					alert.setTitle("Modification de l'arrêt réussie");
					alert.setHeaderText("Modification de l'arrêt réussie");
					alert.setContentText("L'arrêt : "+stopToModify.toString()+" a bien été modifié.");
					
					
					this.model.getAvailableStopList().remove(this.model.getStopFromName(this.stopToModify.getName()));
					result = alert.showAndWait();

					
				}
				if(result.get()==closeAddStopButtonType) {
					this.clearAddStopField();
					this.addStopButton.fire();
				}
				if(result.get()==mainScreenButtonType) {
					
					this.goBackToMainScreen();
					
				}

				
			}

		}
	}
	
	
	@FXML
	public void openAddConfigurationMenu(ActionEvent e) {
		
		//TODO à modifier en cas d'ajouts de commande ou de nécessité de nettoyer les champs lors de la fermeture

		if(!this.addStopVBox.isVisible()) {
			if(this.modifyStopVBox.isVisible())
				this.modifyStopVBox.setVisible(false);
			this.addStopVBox.setVisible(true);
			//TODO rajouter la file par défaut
			

			try {
				
				//TODO changer pour mettre le null le get de tous les stops disponibles dans la bdd pour faire l'affichage 
				this.mapContainer = new MapContainer(Paths.get(this.getClass().getResource("testmap.png").toURI()).toFile(),null);
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			createFieldBindings();
			mapContainer.setDrawStopProperty(true);
			this.addStopVBox.getChildren().add(mapContainer);
			fillCityComboBox(e);
		}
		else
		{
			this.addStopVBox.setVisible(false);
		}
	}

	@FXML
	public void openModifyConfigurationMenu(ActionEvent e) {
		
		if(!this.modifyStopVBox.isVisible()) {
			if(this.addStopVBox.isVisible())
				this.addStopVBox.setVisible(false);
			this.modifyStopVBox.setVisible(true);
			fillModifyComboBox(e);
		}
		else
		{
			this.modifyStopVBox.setVisible(false);
		}
	}
	
	
	@FXML
	public void fillCityComboBox(ActionEvent e) {
		if(communeComboBox.getItems().isEmpty()) {
			for(String s : this.model.getAvailableCitiesList())
				communeComboBox.getItems().add(s);
		}
	}
	
	@FXML
	public void clearAddStopField() {
		this.adressTextField.clear();
		this.latitudeTextField.clear();
		this.longitudeTextField.clear();
		this.stopNameTextField.clear();
		this.communeComboBox.getSelectionModel().selectFirst();
	}
	
	//Pas bien faudrait faire une seule fonction pour remplir les deux combobox mais on verra plus tard
	@FXML
	public void fillModifyComboBox(ActionEvent e) {
		if(modifyComboBox.getItems().isEmpty()) {
			for(Stop s : this.model.getAvailableStopList())
				modifyComboBox.getItems().add(s.getName());
		}
	}
	
	@FXML
	public void modifySelectedStop() {
		
		if(!this.modifyComboBox.getSelectionModel().getSelectedItem().isEmpty()) {
			this.openAddConfigurationMenu(new ActionEvent());
			Stop stopToModify = this.model.getStopFromName(this.modifyComboBox.getSelectionModel().getSelectedItem());
			fillAddStopFieldForModification(stopToModify);
		}
		
	}
	
	public void fillAddStopFieldForModification(Stop s) {
		this.stopToModify=s;
		if(s.getLatitude()!=null)
			this.latitudeTextField.setText(s.getLatitude().toString());
		if(s.getLongitude()!=null)
			this.longitudeTextField.setText(s.getLatitude().toString());
		if(s.getName()!=null)
			this.stopNameTextField.setText(s.getName());
		if(s.getAddress()!=null)
			this.adressTextField.setText(s.getAddress());
		//TODO Ajouter un champ pour mettre la commune
	}
	
	public void createFieldBindings() {
		if(this.mapContainer!=null) {
			
			
			Bindings.bindBidirectional(this.longitudeTextField.textProperty(), this.mapContainer.getCurrentDrawedStopCircle().centerXProperty(),new NumberStringConverter());
			Bindings.bindBidirectional(this.latitudeTextField.textProperty(), this.mapContainer.getCurrentDrawedStopCircle().centerYProperty(),new NumberStringConverter());

			
		}
	}
	
	
	@FXML
	public void goBackToMainScreen() {
		App.getStage().setScene(App.getMainScene());
	}







}
