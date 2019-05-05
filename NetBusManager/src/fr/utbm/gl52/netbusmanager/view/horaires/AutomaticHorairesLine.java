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
	public AutomaticHorairesLine(ArrayList<Stop> travelStop) {
		super();
		this.mainLine = new Line(0,0,0,100*travelStop.size());
		this.getChildren().add(mainLine);
		
		for(Stop s : travelStop) {
			
		}
	}
	

}
