package fr.utbm.gl52.netbusmanager.controller;

import java.util.ArrayList;
import java.util.Optional;

import fr.utbm.gl52.netbusmanager.dao.StopDao;
import fr.utbm.gl52.netbusmanager.model.Stop;
import fr.utbm.gl52.netbusmanager.model.StopEditor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

public class StopEditorController {
	
	//TODO Rajouter un test sur les textfield pour pas rentrer n'importe quoi
	//TODO Rajouter l'implémentation avec la carte pour saisir les arrêts
	
	
	@FXML
	private GridPane root;
	@FXML
	private Button addStopButton;
	@FXML
	private Button modifyStopButton;
	
	
	/*Élements graphiques lors de l'ajout d'un arrêt*/
	@FXML
	private VBox addStopVBox;
	@FXML
	private TextField stopNameTextField;
	@FXML
	private TextField longitudeTextField;
	@FXML
	private TextField lattitudeTextField;
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
	

	private Stop stopToModify;
	
	public StopEditorController(){
		this.model = new StopEditor();
	}
	
	
	
	
	
	
	@FXML
	public void addStopToDataBase(ActionEvent e) {
		
		stopNameTextField.setStyle("-fx-border-color : transparent");
		longitudeTextField.setStyle("-fx-border-color : transparent");
		lattitudeTextField.setStyle("-fx-border-color : transparent");
		this.informationAddLabel.setVisible(false);

		
		if(stopNameTextField.getText().isEmpty()||longitudeTextField.getText().isEmpty()||lattitudeTextField.getText().isEmpty()) {
			this.informationAddLabel.setText("Un champ obligatoire (*) n'a pas été remplis");
			this.informationAddLabel.setVisible(true);
			if(stopNameTextField.getText().isEmpty())
				stopNameTextField.setStyle("-fx-border-color : red");
			if(longitudeTextField.getText().isEmpty())
				longitudeTextField.setStyle("-fx-border-color : red");
			if(lattitudeTextField.getText().isEmpty())
				lattitudeTextField.setStyle("-fx-border-color : red");
		}
		else {
			//Création d'un nouvel arrêt avec les valeurs de tous les champs
			Stop newStop = new Stop(stopNameTextField.getText(),Double.parseDouble(longitudeTextField.getText()),Double.parseDouble(lattitudeTextField.getText()),adressTextField.getText());
			if(model.isStopAlreadyExisting(newStop)&&this.stopToModify==null) {
				this.informationAddLabel.setText("Un arrêt portant le même nom ou se situant à la même position, existe déjà");
				this.informationAddLabel.setVisible(true);
			}
			else {
				/*AJOUT*/
				//Ajout d'arrêt dans la base de donnée
				StopDao stopDao = new StopDao();
				
		        if(stopDao.save(newStop)) {
		        		System.out.println("l'arret ajouté avec succes");
		        }
				
				//verification de l'insertion 
				for (Stop stop: stopDao.getAll()) {
	        		System.out.println(stop);
	        	}
				
				//this.model.getAvailableStopList().add(newStop);

				Alert alert = new Alert(AlertType.CONFIRMATION);
				
				ButtonType closeAddStopButtonType = new ButtonType("Retour à l'éditeur d'arrêts" ,ButtonData.CANCEL_CLOSE);
				ButtonType mainScreenButtonType = new ButtonType("Retour au menu principal",ButtonData.CANCEL_CLOSE);
			
				alert.getButtonTypes().setAll(closeAddStopButtonType, mainScreenButtonType);
				
				Optional<ButtonType> result;
				
				//Actualisation de la liste
				this.model = new StopEditor();
				
				if(this.stopToModify==null) {
					
					//Affichage du méssage de confirmation sur la fenêtre
					alert.setTitle("Ajout de l'arrêt réussi");
					alert.setHeaderText("Ajout de l'arrêt réussi");
					alert.setContentText("L'arrêt : "+newStop.getName()+" a bien été ajouté à la base de données de l'application ");
	
					ButtonType addNewStopButtonType = new ButtonType("Ajouter un nouvel arrêt",ButtonData.CANCEL_CLOSE);
	
					alert.getButtonTypes().add(0,addNewStopButtonType);
					
					 result = alert.showAndWait();
					if(result.get()==addNewStopButtonType) {
						this.clearAddStopField();
						
					}

				}else {
				
					//MODIFICATION
					alert.setTitle("Modification de l'arrêt réussie");
					alert.setHeaderText("Modification de l'arrêt réussie");
					alert.setContentText("L'arrêt : "+stopToModify.toString()+" a bien été modifié.");
					
					/*Ajouter le code pour la modification dans la base de donnée*/
					
					/*if(stopDao.update(stopToModify)) {
			        		System.out.println("l'arret ajouté avec succes");
			        }
					
					//verification de l'insertion 
					for (Stop stop: stopDao.getAll()) {
		        		System.out.println(stop);
		        	}*/
					
					
					//this.model.getAvailableStopList().remove(this.model.getStopFromName(this.stopToModify.getName()));
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
		this.lattitudeTextField.clear();
		this.longitudeTextField.clear();
		this.stopNameTextField.clear();
		this.communeComboBox.getSelectionModel().selectFirst();
	}
	
	//Pas bien faudrait faire une seule fonction pour remplir les deux combobox mais on verra plus tard
	@FXML
	public void fillModifyComboBox(ActionEvent e) {
		ArrayList<Stop> lis =  this.model.getAvailableStopList();
		
		//Si la liste est vierege on copie tout les éléments de la base
		if(modifyComboBox.getItems().isEmpty()) {
			for(Stop s : this.model.getAvailableStopList())
				modifyComboBox.getItems().add(s.getName());
		}
		else {
			// Dès qu'il y a un nouvel élément qui sajoute à la base on raffraichie la liste
			if(modifyComboBox.getItems().size() != lis.size()) {
				modifyComboBox = new ComboBox<String>();
				
				for(Stop s : this.model.getAvailableStopList())
					modifyComboBox.getItems().add(s.getName());
			}
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
			this.lattitudeTextField.setText(s.getLatitude().toString());
		if(s.getLongitude()!=null)
			this.longitudeTextField.setText(s.getLatitude().toString());
		if(s.getName()!=null)
			this.stopNameTextField.setText(s.getName());
		if(s.getAddress()!=null)
			this.adressTextField.setText(s.getAddress());
		//TODO Ajouter un champ pour mettre la commune
	}
	
	
	@FXML
	public void goBackToMainScreen() {
		
	}
}
