package fr.utbm.gl52.netbusmanager.graphicalcomponents;

import fr.utbm.gl52.netbusmanager.model.Stop;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class StopCircle extends Circle {
	
	
	public StopCircle(Stop stop) {
		super();
		this.setRadius(5);
		this.setCenterX(stop.getLatitude());
		this.setCenterY(stop.getLongitude());
		Tooltip nameToolTip = new Tooltip(stop.getName());
		Tooltip.install(this, nameToolTip);
		this.setFill(Color.RED);
		
	}
	
	public StopCircle(double x, double y) {
		super();
		this.setRadius(5);
		System.out.println("XY de stopcirlce "+x + " "+y);
		this.setCenterX(x);
		this.setCenterY(y);
		this.setFill(Color.GREEN);
	}

}
