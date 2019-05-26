/**
 * 
 */
package fr.utbm.gl52.netbusmanager.controller;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import fr.utbm.gl52.netbusmanager.graphicalcomponents.MapContainer;
import fr.utbm.gl52.netbusmanager.graphicalcomponents.StopCircle;
import fr.utbm.gl52.netbusmanager.model.Line;
import fr.utbm.gl52.netbusmanager.model.Stop;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * @author Arthur
 * Controller pour l'�diteur de lignes
 */
public class LineEditorController implements Initializable {
	
	@FXML
	private GridPane root;
	@FXML
	public Button createLineButton;
	@FXML
	public Button modifyLineButton;
	@FXML 
	public Button homeButton;
	
	
	

	
	
	
	//Pour faire un beau bouton fl�che gauche
	//style="-fx-shape:'M 4.54e-6,600.00002 703.12501,1068.75 l 0,-252.53904 496.87499,0 0,-432.42188 -496.87499,0 0,-252.53905 L 4.54e-6,600.00002 z'"
	
	//Pour faire celle � droitre
	//"style="-fx-shape:'M 1200,600.00002 -703.12499,-468.75 0,252.53905 -496.87500546,0 0,432.42188 496.87500546,0 0,252.53905 L 1200,600.00002 z'"
	@FXML
	public VBox modifyLineVBox;
	@FXML
	public Button modifyButton;
	@FXML
	public ComboBox<String> chooseLineModifyComboBox;
	
	
	
	
	
	@FXML
	private VBox lineSettingsVBox;
	@FXML 
	private TextField idLineTextField;
	@FXML
	private TextField nameLineTextField;
	@FXML
	private ColorPicker lineColorPicker;
	@FXML
	private VBox mapContainerVBox;
	@FXML
	public ListView<ListViewLineComponent> addedStopListView;
	@FXML
	public ListView<ListViewLineComponent> availableStopListView;
	@FXML
	public Button addStopToLineButton;
	@FXML
	public Button removeStopFromLineButton;
	@FXML
	public TextField searchStopTextField;
	@FXML
	public Button createButton;
	@FXML
	public Button annulateButton;
	
	
	
	
	//TODO A supprimer une fois que la r�cup�ration des stops dans la classe depuis la BDD est faite, je l'utilise juste pour faire des tests pour l'instant
	private ArrayList<Stop> tmpListOfStop;
	
	private boolean modifyLine=false;
	private Line lineToModify;
	private MapContainer mapContainer; 
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		//Permet l'update de la table des arr�ts disponible lors de la saisie d'un nouveau caract�re dans la barre 
		this.searchStopTextField.textProperty().addListener((obs,old,val)->{
			for(ListViewLineComponent elem : this.availableStopListView.getItems()) {
				if(elem.getAssociatedStop().getName().toLowerCase().contains(val.toLowerCase()))
					elem.setVisible(true);
				else
					elem.setVisible(false);
			}
			
		});
		
		
		
		//Permet de cr�er un TextField qui ne va prendre que des chiffres en entr�e
		this.idLineTextField.textProperty().addListener((obs,old,val)->{
			if(!val.matches("\\d*"))
				this.idLineTextField.setText(old);
		});
		
		
		this.lineColorPicker.valueProperty().addListener((obs,old,val)->{
			this.mapContainer.getCurrendDrawedLine().setStroke(val);
		});

	}
	
	public LineEditorController() {
		
		//TODO supprimer la TMP list of stop pour remplacer par la liste des stops dispo issus de la base de donn�es 
		
		this.tmpListOfStop = new ArrayList<Stop>();
		this.tmpListOfStop.add(new Stop("Abc", 100.0, 100.0));
		this.tmpListOfStop.add(new Stop("Adc", 200.0, 200.0));
		this.tmpListOfStop.add(new Stop("gfff", 300.0, 200.0));
		
		
		try {
			this.mapContainer = new MapContainer(Paths.get(this.getClass().getResource("testmap.png").toURI()).toFile(),null);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.mapContainer.setDrawLineProperty(true);
		


		
		

	}
	
	
	
	
	//Classe interne repr�sentant les composants des listView 
	public class ListViewLineComponent extends HBox{
		
		public CheckBox checkBox;
		public Label stopName;
		public Stop associatedStop;
		public StopCircle associatedStopCircle;
		
		
		
		public ListViewLineComponent(Stop stop) {
			super();
			this.checkBox = new CheckBox();
			this.associatedStop = stop;
			this.stopName = new Label(this.associatedStop.getName());
			this.associatedStopCircle = new StopCircle(associatedStop);
			
			mapContainer.getChildren().add(this.associatedStopCircle);
			this.getChildren().addAll(checkBox,stopName);
			
			
			
			Tooltip.install(this, new Tooltip(this.associatedStop.toString()));
			
			
			this.setOnMousePressed(e->{
				this.setStyle("-fx-background-color:green");
				this.stopName.setTextFill(Color.WHITE);
				if(this.checkBox.isSelected()==false)
					this.checkBox.setSelected(true);
				else
					this.checkBox.setSelected(false);
				
			});
			this.setOnMouseReleased(e->{
				this.setStyle("-fx-background-color:transparent");
				this.stopName.setTextFill(Color.BLACK);
			});
			
			
			Bindings.bindBidirectional(this.checkBox.selectedProperty(), this.associatedStopCircle.selectedPropertyProperty());

			
		}
		
		
		
		public CheckBox getCheckBox() {
			return checkBox;
		}
		public void setCheckBox(CheckBox checkBox) {
			this.checkBox = checkBox;
		}
		public Label getStopName() {
			return stopName;
		}
		public void setStopName(Label stopName) {
			this.stopName = stopName;
		}
		public Stop getAssociatedStop() {
			return associatedStop;
		}
		public void setAssociatedStop(Stop associatedStop) {
			this.associatedStop = associatedStop;
		}



		public StopCircle getAssociatedStopCircle() {
			return associatedStopCircle;
		}



		public void setAssociatedStopCircle(StopCircle associatedStopCircle) {
			this.associatedStopCircle = associatedStopCircle;
		}
		
		
		
		
		
	}
	
	
	/**
	 * Permet l'ouverture du menu permettant la gestion des diff�rents �l�ments de la ligne 
	 */
	@FXML
	public void openAddConfigurationMenu() {
		this.mapContainer.visibleProperty().bind(this.lineSettingsVBox.visibleProperty());
		this.mapContainer.getCurrendDrawedLine().fillProperty().bind(this.lineColorPicker.valueProperty());

			

		
		if(!this.lineSettingsVBox.isVisible()) {
			this.lineSettingsVBox.setVisible(true);
			this.createButton.setText("Cr�er la ligne");
			this.modifyLineVBox.setVisible(false);
			//TODO r�cup�rer tous les stops de la BDD, en rempla�ant tmpListOfStop par la liste issue de la BDD
			ArrayList<Stop> availableStops = tmpListOfStop;
			
			if(modifyLine) {
				
				this.createButton.setText("Modifier la ligne");

				this.nameLineTextField.setText(lineToModify.getName());
				this.idLineTextField.setText(Integer.toString(lineToModify.getId()));
				
				
				for(Stop s : lineToModify.getListOfStops()) {
					ListViewLineComponent newListViewComponent = new ListViewLineComponent(s);
					addedStopListView.getItems().add(newListViewComponent);
					//TODO si il arrive pas � remove c'est parce que le stop dans la ligne est pas la m�me instance que celui de la BDD
					// Sinon faut faire une recherche par nom de la liste des available stop, c'est moins beau mais �a devrait marcher
					availableStops.remove(s);
				}	
			}
			
			
			addAllAvailableStopsToListView(availableStops);
			this.mapContainerVBox.getChildren().add(mapContainer);
		}else {
			clearAddLineField();
			this.lineSettingsVBox.setVisible(false);
		}
	
	}
	
	/**
	 * Permet l'ouverture du menu de s�leciton de la ligne � modifier
	 */
	@FXML
	public void openModifyConfigurationMenu() {
		if(!this.modifyLineVBox.isVisible()) {
			
			
			//TODO � modifier pour faire un for each qui va devoir remplir la combobox avec les noms de toutes les lignes pr�sentes dans la bdd 
			this.chooseLineModifyComboBox.getItems().add(lineToModify.getId()+" - "+lineToModify.getName());
			this.modifyLineVBox.setVisible(true);
			this.lineSettingsVBox.setVisible(false);
		}else {
			this.modifyLineVBox.setVisible(false);
		}
	}
	
	@FXML
	public void modifyButtonHandler() {
		this.modifyLine=true;
		
		//TODO modifier pour r�cup�rer la Line dans la BDD � l'aide du nom s�lectionn� dans la comboBox
		// Pour l'instant on va travailler en stockant la ligne dans la selected line 
		//		this.lineToModify = this.chooseLineModifyComboBox.getSelectionModel().getSelectedItem();
		openAddConfigurationMenu();
	}
	
	/**
	 * Ajoute tous les arr�ts disponibles depuis la BDD dans la listView
	 */
	public void addAllAvailableStopsToListView(ArrayList<Stop> stopList) {
		
		for(Stop s : stopList) {
			ListViewLineComponent newViewComponent = new ListViewLineComponent(s);
			newViewComponent.getAssociatedStopCircle().setSelectableProperty(true);
			
			this.availableStopListView.getItems().add(newViewComponent);
		}
		//TODO
		//Doit r�cup�rer tous les arr�ts de la BDD pour les add � la listeView si possible par ordre alphab�tique
		
	}
	
	/**
	 * Permet l'ajout de l'arr�t de la liste des arr�ts disponibles vers la liste des arr�ts ajout�s
	 */
	@FXML
	public void addStopsFromAvailableToAdded() {
		ArrayList<ListViewLineComponent> toRemove = new ArrayList<>();
		for(ListViewLineComponent elem : this.availableStopListView.getItems()) {
			
			if(elem.getCheckBox().isSelected()) {
				this.addedStopListView.getItems().add(elem);
				elem.getCheckBox().setSelected(false);
				toRemove.add(elem);
			}
		}
		for(ListViewLineComponent e : toRemove)
			this.availableStopListView.getItems().remove(e);
	}
	
	/**
	 * Handler du bouton permettant la cr�ation de la ligne
	 */
	public void createLineButtonHandler() {
		
		this.idLineTextField.setStyle("-fx-border-color:transparent");
		this.nameLineTextField.setStyle("-fx-border-color:transparent");
		this.addedStopListView.setStyle("-fx-border-color:transparent");
		
		if(this.idLineTextField.getText().isEmpty() || this.nameLineTextField.getText().isEmpty() ||this.addedStopListView.getItems().isEmpty()) {
			if(this.idLineTextField.getText().isEmpty())
				this.idLineTextField.setStyle("-fx-border-color:red");
			if(this.nameLineTextField.getText().isEmpty())
				this.nameLineTextField.setStyle("-fx-border-color:red");
			if(this.addedStopListView.getItems().isEmpty())
				this.addedStopListView.setStyle("-fx-border-color:red");
		}else {
			Line newLine = new Line(Integer.parseInt(this.idLineTextField.getText()),this.nameLineTextField.getText());
			
			

			for(ListViewLineComponent l : this.addedStopListView.getItems())
				newLine.addStop(l.getAssociatedStop());
			Alert alert = new Alert(AlertType.CONFIRMATION);
			
			ButtonType closeAddStopButtonType = new ButtonType("Retour � l'�diteur de ligne" ,ButtonData.CANCEL_CLOSE);
			ButtonType mainScreenButtonType = new ButtonType("Retour au menu principal",ButtonData.CANCEL_CLOSE);
		
			alert.getButtonTypes().setAll(closeAddStopButtonType, mainScreenButtonType);
			
			Optional<ButtonType> result;

			
			if(this.lineToModify==null) {
				//TODO � remplacer par un ajout � la base de donn�e, la lineToModify n'est utilis� que lors du choix de la ligne � modifier via la comboBox du menu de modification
				this.lineToModify = newLine;
			alert.setTitle("Ajout de la ligne r�ussi");
			alert.setHeaderText("Ajout de la ligne r�ussi");
			alert.setContentText("La ligne: "+newLine.toString()+" a bien �t� ajout�e � la base de donn�es de l'application ");

			ButtonType addNewStopButtonType = new ButtonType("Ajouter une nouvelle ligne",ButtonData.CANCEL_CLOSE);

			alert.getButtonTypes().add(0,addNewStopButtonType);
			
			 result = alert.showAndWait();
				if(result.get()==addNewStopButtonType) {
					this.clearAddLineField();
					//TODO changer poour mettre la liste d'arr�ts issue de la bdd
					this.addAllAvailableStopsToListView(tmpListOfStop);
				}

			}else {
				alert.setTitle("Modification de la ligne r�ussie");
				alert.setHeaderText("Modification de la ligne r�ussie");
				alert.setContentText("La ligne : "+lineToModify.toString()+" a bien �t� modifi�.");
				
				
				//TODO ajouter le fait que la nouvelle ligne cr�er remplace l'actuelle linetomodify dans la BDD pour �viter la redondance
				
				
				result = alert.showAndWait();

				
			}
			if(result.get()==closeAddStopButtonType) {
				this.clearAddLineField();
				this.createLineButton.fire();
			}
			if(result.get()==mainScreenButtonType) {
				
				this.goBackToMainScreen();
				
			}
		}
		
	}
	
	/**
	 * Permet l'ajout de l'arr�t de la liste des arr�ts ajout�s vers la liste des arr�ts disponibles
	 */
	@FXML
	public void addStopsFromAddedToAvailable() {
		ArrayList<ListViewLineComponent> toRemove = new ArrayList<>();
		for(ListViewLineComponent elem : this.addedStopListView.getItems()) {
			if(elem.getCheckBox().isSelected()) {
				this.availableStopListView.getItems().add(elem);
				elem.getCheckBox().setSelected(false);
				toRemove.add(elem);
			}
		}
		for(ListViewLineComponent e : toRemove)
			this.addedStopListView.getItems().remove(e);
	}
	
	public void clearAddLineField() {
		this.addedStopListView.getItems().clear();
		this.availableStopListView.getItems().clear();
		this.idLineTextField.clear();
		this.nameLineTextField.clear();
		
	}
	
	
	@FXML
	public void goBackToMainScreen() {
		App.getStage().setScene(App.getMainScene());
	}



	

}
