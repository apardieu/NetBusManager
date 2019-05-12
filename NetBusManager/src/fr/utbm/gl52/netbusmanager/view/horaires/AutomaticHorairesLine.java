package fr.utbm.gl52.netbusmanager.view.horaires;

import java.util.ArrayList;

import fr.utbm.gl52.netbusmanager.model.Stop;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class AutomaticHorairesLine extends StackPane {
	
	
	
	private Line mainLine;
	private ArrayList<Circle> stopCircles;
	private ArrayList<Label> stopNameLabels;
	private ArrayList<Label> stopTravelDurationLabels;
	private ArrayList<TextField> stopTravelDurationtextFields;
	
	
	
	
	
	//TODO peut être mettre une vraie ligne comme classe
	//TODO rajouter une un scrollpane pour gérer le cas avec beaucoup d'arrêts, sinon faire une adaptation de la taille de chacun des composants 
	public AutomaticHorairesLine() {
		super();
		ArrayList<Stop> travelStop = new ArrayList<>();
		travelStop.add(new Stop("Arret 1", 0.0, 10.0));
		travelStop.add(new Stop("Arret 2", 0.0, 20.0));
		travelStop.add(new Stop("Arret 3", 0.0, 30.0));

		
		this.mainLine = new Line(0,0,0,100*travelStop.size());
		this.getChildren().add(mainLine);
		//TODO peut être nécessité d'adapter l'espace entre les points
		int spaceBetweenStop = 100;
		double circlePositionX = this.mainLine.getStartX();
		double circlePositionY = this.mainLine.getStartY();
			
		
		for(Stop s : travelStop) {
			Circle stopCircle = new Circle(circlePositionX, circlePositionY, 30);
			stopCircles.add(stopCircle);
			Label stopNameLabel = new Label(s.getName());
			stopNameLabel.setTranslateX(circlePositionX+30);
			stopNameLabel.setTranslateY(circlePositionY);
			
			TextField betweenStopDuration = new TextField();
			betweenStopDuration.setTranslateX(stopNameLabel.getTranslateX());
			betweenStopDuration.setTranslateY(circlePositionY+spaceBetweenStop/2);
			
			stopNameLabels.add(stopNameLabel);
			
			circlePositionY+=spaceBetweenStop;
			
			
			
			
			
		}
	}
	

}
