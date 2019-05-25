package fr.utbm.gl52.netbusmanager.controller;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fr.utbm.gl52.netbusmanager.graphicalcomponents.MapContainer;
import fr.utbm.gl52.netbusmanager.graphicalcomponents.StopCircle;
import fr.utbm.gl52.netbusmanager.model.Line;
import fr.utbm.gl52.netbusmanager.model.Stop;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NetworkViewController implements Initializable  {
	
	
	@FXML
	private VBox root;
	@FXML
	private CheckBox hideStopCheckBox;
	@FXML
	private CheckBox hideLineCheckBox;
	@FXML
	private ComboBox<ComboBoxComponent> availableStopComboBox;
	@FXML
	private ComboBox<ComboBoxComponent> availableLineComboBox;
	
	
	private ArrayList<Stop> stopList;
	private ArrayList<Line> lineList;
	private MapContainer mapContainer;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.stopList = new ArrayList<Stop>();
		this.lineList = new ArrayList<Line>();
	
		
		//TODO remplir les deux listes précédentes avec tous les stops et les lignes issues de la BDD
		//Et supprimer celles que j'ai mises là pour faire des tests
		this.stopList.add(new Stop("stop1", 400.0, 400.0));
		this.stopList.add(new Stop("stop2",300.0,300.0));
		this.stopList.add(new Stop("stop3",500.0,400.0));
		
		try {
			this.mapContainer = new MapContainer(Paths.get(this.getClass().getResource("testmap.png").toURI()).toFile(),this.stopList);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		for (Stop s : this.stopList) {
			
			StopCircle associatedStopCircle = this.mapContainer.addStopToMap(s);
			this.availableStopComboBox.getItems().add(new ComboBoxComponent(s, associatedStopCircle));
			
		}
		
		

		//Handler de la checkbox pour cacher tous les stops affichés sur la carte
		this.hideStopCheckBox.setOnAction(e->{
			if(this.hideStopCheckBox.isSelected()) {
				for(ComboBoxComponent comp : this.availableStopComboBox.getItems()) {
					comp.getAssociatedStopCircle().setVisible(false);
				}
			}else {
				for(ComboBoxComponent comp : this.availableStopComboBox.getItems()) {
					comp.getAssociatedStopCircle().setVisible(true);
				}
			}		
		});
		
		
		//Faire la même chose pour les lignes
		this.root.getChildren().add(this.mapContainer);
				
	}
	
	
	public NetworkViewController() {
	
		
	}
	
	
	
	
	public class ComboBoxComponent extends HBox{
		
		public CheckBox checkBox;
		public Label nameLabel;
		public Stop associatedStop = null;
		public StopCircle associatedStopCircle;
		public Line associatedLine = null;
		
		public ComboBoxComponent(Stop stop,StopCircle stopCircle) {
			super();
			this.checkBox = new CheckBox();
			this.associatedStop = stop;
			this.associatedStopCircle = stopCircle;
			this.nameLabel = new Label(this.associatedStop.getName());
			this.getChildren().addAll(checkBox,nameLabel);
			
			
			Tooltip.install(this, new Tooltip(this.associatedStop.toString()));
			
			this.associatedStopCircle.setOnMouseClicked(e->{
				
				//Faire le handler quand on clique sur le stop ça set son style avec une bordercolor et une strokewidt et ça affiche son nom dans le label selectedStopLabel
				
				
				
			});
			
			
			

		}
		
		



		public ComboBoxComponent(Line line) {
			super();
			this.checkBox = new CheckBox();
			this.associatedLine = line;
			this.nameLabel = new Label(this.associatedLine.getId() + " - "+ this.associatedLine.getName());
			this.getChildren().addAll(checkBox,nameLabel);
			
			
			
			Tooltip.install(this, new Tooltip(this.associatedLine.toString()));
			// Faire aussi la selection de la line on verra ça quand le composant graphique qui la représente sera bien fait 
			
			

		}
		public CheckBox getCheckBox() {
			return checkBox;
		}
		public void setCheckBox(CheckBox checkBox) {
			this.checkBox = checkBox;
		}
		public Label getNameLabel() {
			return nameLabel;
		}
		public void setNameLabel(Label nameLabel) {
			this.nameLabel = nameLabel;
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





		
		

		
		
		
	

}
