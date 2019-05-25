/**
 * 
 */
package fr.utbm.gl52.netbusmanager.graphicalcomponents;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import fr.utbm.gl52.netbusmanager.model.Stop;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;

/**
 * @author Arthur
 *
 */
public class MapContainer extends Pane {
	
	
	private File mapFile;
	private ImageView mapImageView;
	private ArrayList<Circle> stopRepresentations = new ArrayList<>();
	private ArrayList<Polyline> lineRepresentations = new ArrayList<>();
	
	
	private SimpleBooleanProperty drawStopProperty = new SimpleBooleanProperty();
	private SimpleBooleanProperty drawLineProperty = new SimpleBooleanProperty();
	
	private StopCircle currentDrawedStopCircle;
	
	
	public MapContainer (File mapFile,ArrayList<Stop> stopList) {
		super();
		
		
		//TODO Changer pour mettre la taille souhaitée, aka taille de l'image de la map
		
		
		this.setPrefSize(800, 800);
		this.currentDrawedStopCircle = new StopCircle(0,0);
		this.currentDrawedStopCircle.setVisible(false);
	
		System.out.println(mapFile.toURI().toString());
		String str = mapFile.toURI().toString();
		str = str.substring(5, str.length());
		str = "file://"+str;
		System.out.println(str);
		try {
			this.mapImageView = new ImageView(new Image(new URL(str).toString()));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.getChildren().add(this.mapImageView);
		if(stopList!=null)
			this.addAllAvailableStopToMap(stopList);
		
		this.getChildren().add(this.currentDrawedStopCircle);
		
		
		
		
		this.setOnKeyPressed(e->{
			
		});
		
		
		this.setOnMousePressed(e->{

			if(drawStopProperty.get()) {
				moveCurrentDrawnedStopCircle(e.getX(), e.getY());
			}
	
		});
		
		
		
	}
	
	
	public void moveCurrentDrawnedStopCircle(double x, double y) {
		this.currentDrawedStopCircle.setVisible(true);
		this.currentDrawedStopCircle.setCenterX(x);
		this.currentDrawedStopCircle.setCenterY(y);
	}
	
	
	public StopCircle addStopToMap(Stop s) {
		
		StopCircle newStopCircle = new StopCircle(s);
		this.getChildren().add(newStopCircle);
		return newStopCircle;
		
	}
	
	
	public void addAllAvailableStopToMap(ArrayList<Stop> stopList) {
		
		for(Stop s : stopList) 
			this.getChildren().add(new StopCircle(s));
		
		
	}
	
	
	public File getMapFile() {
		return mapFile;
	}


	public void setMapFile(File mapFile) {
		this.mapFile = mapFile;
	}


	public ImageView getMapImageView() {
		return mapImageView;
	}


	public void setMapImageView(ImageView mapImageView) {
		this.mapImageView = mapImageView;
	}


	public ArrayList<Circle> getStopRepresentations() {
		return stopRepresentations;
	}


	public void setStopRepresentations(ArrayList<Circle> stopRepresentations) {
		this.stopRepresentations = stopRepresentations;
	}


	public ArrayList<Polyline> getLineRepresentations() {
		return lineRepresentations;
	}


	public void setLineRepresentations(ArrayList<Polyline> lineRepresentations) {
		this.lineRepresentations = lineRepresentations;
	}


	public StopCircle getCurrentDrawedStopCircle() {
		return currentDrawedStopCircle;
	}


	public void setCurrentDrawedStopCircle(StopCircle currentDrawedStopCircle) {
		this.currentDrawedStopCircle = currentDrawedStopCircle;
	}




	public final SimpleBooleanProperty drawStopPropertyProperty() {
		return this.drawStopProperty;
	}
	




	public final boolean isDrawStopProperty() {
		return this.drawStopPropertyProperty().get();
	}
	




	public final void setDrawStopProperty(final boolean drawStopProperty) {
		this.drawStopPropertyProperty().set(drawStopProperty);
	}
	




	public final SimpleBooleanProperty drawLinePropertyProperty() {
		return this.drawLineProperty;
	}
	




	public final boolean isDrawLineProperty() {
		return this.drawLinePropertyProperty().get();
	}
	




	public final void setDrawLineProperty(final boolean drawLineProperty) {
		this.drawLinePropertyProperty().set(drawLineProperty);
	}
	
	

	

}
