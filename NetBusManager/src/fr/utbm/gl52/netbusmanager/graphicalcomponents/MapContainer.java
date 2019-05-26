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
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
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
	private ArrayList<StopCircle> stopRepresentations = new ArrayList<>();
	private ArrayList<Polyline> lineRepresentations = new ArrayList<>();
	
	
	private SimpleBooleanProperty drawStopProperty = new SimpleBooleanProperty();
	private SimpleBooleanProperty drawLineProperty = new SimpleBooleanProperty();
	
	
	
	
	private ArrayList<Point2D> drawingPointsCurrentLine = new ArrayList<>();
	private Polyline currendDrawedLine = new Polyline();
	private StopCircle currentDrawedStopCircle;
	
	
	public MapContainer (File mapFile,ArrayList<Stop> stopList) {
		super();
		
		
		
		
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
		
		
		
		this.setOnKeyPressed(e->{
			System.out.println("Keypressed in mapcontainer");
			
			if(e.getCode().equals(KeyCode.DELETE)) {
				System.out.println("test delete");
				updateCurrentLineDrawing(this.drawingPointsCurrentLine.get(this.drawingPointsCurrentLine.size()), false);
			}
			
		});
		this.setOnMousePressed(e->{

			if(drawStopProperty.get()) {
				moveCurrentDrawnedStopCircle(e.getX(), e.getY());
			}
			
			if(drawLineProperty.get()) {
				if(e.isPrimaryButtonDown())
					updateCurrentLineDrawing(new Point2D(e.getX(),e.getY()), true);
				if(e.isSecondaryButtonDown())
					updateCurrentLineDrawing(this.drawingPointsCurrentLine.get(this.drawingPointsCurrentLine.size()-1), false);

				
			}
	
		});
		
	}
	
	
	public void moveCurrentDrawnedStopCircle(double x, double y) {
		this.currentDrawedStopCircle.setVisible(true);
		this.currentDrawedStopCircle.setCenterX(x);
		this.currentDrawedStopCircle.setCenterY(y);
	}
	
	
	
	
	public void updateCurrentLineDrawing(Point2D point,boolean add) {
		
		this.currendDrawedLine.setVisible(false);
		this.getChildren().remove(this.currendDrawedLine);
		this.currendDrawedLine = new Polyline();

		this.currendDrawedLine.setStrokeWidth(4);
		
		if(add)
			this.drawingPointsCurrentLine.add(point);
		else
			if(!this.drawingPointsCurrentLine.isEmpty())
				this.drawingPointsCurrentLine.remove(this.drawingPointsCurrentLine.size()-1);
		
		for(Point2D p : this.drawingPointsCurrentLine )
			this.currendDrawedLine.getPoints().addAll(p.getX(),p.getY());
		
		this.getChildren().add(this.currendDrawedLine);

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
	
	public void makeAllStopSelectable() {
		for(StopCircle s : this.stopRepresentations) {
			s.setSelectableProperty(true);
		}
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


	public ArrayList<StopCircle> getStopRepresentations() {
		return stopRepresentations;
	}


	public void setStopRepresentations(ArrayList<StopCircle> stopRepresentations) {
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


	public Polyline getCurrendDrawedLine() {
		return currendDrawedLine;
	}


	public void setCurrendDrawedLine(Polyline currendDrawedLine) {
		this.currendDrawedLine = currendDrawedLine;
	}
	
	
	

	

}
