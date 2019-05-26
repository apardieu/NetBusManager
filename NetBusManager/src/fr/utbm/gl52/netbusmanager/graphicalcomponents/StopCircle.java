package fr.utbm.gl52.netbusmanager.graphicalcomponents;

import fr.utbm.gl52.netbusmanager.model.Stop;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class StopCircle extends Circle {
	
	
	private SimpleBooleanProperty selectableProperty = new SimpleBooleanProperty();
	private SimpleBooleanProperty selectedProperty = new SimpleBooleanProperty();
	
	
	public StopCircle(Stop stop) {
		super();
		this.setRadius(5);
		this.setCenterX(stop.getLatitude());
		this.setCenterY(stop.getLongitude());
		Tooltip nameToolTip = new Tooltip(stop.getName());
		Tooltip.install(this, nameToolTip);
		this.setFill(Color.RED);
		
		this.selectedProperty.addListener((obs,old,val)->{
			if(val) {
				this.setRadius(8);
				this.setStyle("-fx-stroke:yellow; -fx-stroke-width:3px;");
				this.setFill(Color.BLUE);
			}else {
				this.setRadius(5);
				this.setStyle("-fx-stroke:transparent; -fx-stroke-width:0px;");
				this.setFill(Color.RED);
			}
				
				
		});
		
	}
	
	public StopCircle(double x, double y) {
		super();
		this.setRadius(5);
		System.out.println("XY de stopcirlce "+x + " "+y);
		this.setCenterX(x);
		this.setCenterY(y);
		this.setFill(Color.GREEN);
	}
	
	public void setSelected(boolean selected) {
		if(selectableProperty.get()) {
			if(selected) {
				this.selectedProperty.set(true);
			}else {
				this.selectedProperty.set(false);
				
			}
		}
	}
	
	public void hideCircle(boolean hide) {
		if(hide) {
			System.out.println("hiding circle");
			this.setRadius(0);
		}
			
		else {
			this.setRadius(5);
			this.setFill(Color.RED);
		}
	}

	public final SimpleBooleanProperty selectablePropertyProperty() {
		return this.selectableProperty;
	}
	

	public final boolean isSelectableProperty() {
		return this.selectablePropertyProperty().get();
	}
	

	public final void setSelectableProperty(final boolean selectableProperty) {
		this.selectablePropertyProperty().set(selectableProperty);
	}

	public final SimpleBooleanProperty selectedPropertyProperty() {
		return this.selectedProperty;
	}
	

	public final boolean isSelectedProperty() {
		return this.selectedPropertyProperty().get();
	}
	

	public final void setSelectedProperty(final boolean selectedProperty) {
		this.selectedPropertyProperty().set(selectedProperty);
	}
	
	
	
	
	
	
	

}
