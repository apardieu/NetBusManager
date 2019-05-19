package fr.utbm.gl52.netbusmanager.view.horaires;

import java.util.ArrayList;

import fr.utbm.gl52.netbusmanager.model.Stop;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class AutomaticHorairesLine extends StackPane {
	
	
	
	private Line mainLine;
	private ArrayList<Circle> stopCircles = new ArrayList<Circle>();
	private ArrayList<Label> stopNameLabels = new ArrayList<Label>();
	private ArrayList<Label> stopTravelDurationLabels = new ArrayList<Label>();
	private ArrayList<TextField> stopTravelDurationtextFields = new ArrayList<TextField>();
	
	
	
	
	
	//TODO peut être mettre une vraie ligne comme classe
	//TODO rajouter une un scrollpane pour gérer le cas avec beaucoup d'arrêts, sinon faire une adaptation de la taille de chacun des composants 
	public AutomaticHorairesLine() {
		super();
		ArrayList<Stop> travelStop = new ArrayList<>();
		travelStop.add(new Stop("Arret 1", 0.0, 10.0));
		travelStop.add(new Stop("Arret 2", 0.0, 20.0));
		travelStop.add(new Stop("Arret 3", 0.0, 30.0));

		
		this.mainLine = new Line(0,20,0,100*travelStop.size()+20);
		this.mainLine.setStrokeWidth(5);
		this.getChildren().add(mainLine);
		//TODO peut être nécessité d'adapter l'espace entre les points
		int spaceBetweenStop = 200;
		double circlePositionX = this.mainLine.getStartX();
		double circlePositionY = this.mainLine.getStartY();
			
		VBox componentsVBox = new VBox();
		componentsVBox.setSpacing(50);
		for(Stop s : travelStop) {
			System.out.println(s.toString());
			Circle stopCircle = new Circle(circlePositionX, circlePositionY, 10);
			System.out.println(stopCircle.toString());
			stopCircles.add(stopCircle);
			Label stopNameLabel = new Label(s.getName());
			HBox stopCircleHBox = new HBox();
			stopCircleHBox.setSpacing(5);
			stopCircleHBox.getChildren().addAll(stopCircle,stopNameLabel);
			


			
			TextField betweenStopDuration = new TextField();
			betweenStopDuration.setTranslateX(stopNameLabel.getTranslateX());
			betweenStopDuration.setTranslateY(circlePositionY+spaceBetweenStop/2);
			
			stopNameLabels.add(stopNameLabel);
			
			circlePositionY+=spaceBetweenStop;
			System.out.println(circlePositionY);
			
			this.getChildren().addAll(stopCircleHBox,betweenStopDuration);
			
			
			
		}
	}
	

}
