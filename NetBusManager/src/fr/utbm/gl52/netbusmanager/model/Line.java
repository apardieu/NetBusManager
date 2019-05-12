package fr.utbm.gl52.netbusmanager.model;

import java.util.ArrayList;

public class Line {
	
	ArrayList<Stop> listOfStops = new ArrayList<>();
	int id;
	String name;
	
	public Line(int id,String name){
		this.id = id;
		this.name = name;
	}
	
	public void addStopToLine(Stop s) {
		this.getListOfStops().add(s);
	}
	
	public void addStopsToLine(ArrayList<Stop> stops) {
		this.listOfStops.addAll(stops);
	}

	public ArrayList<Stop> getListOfStops() {
		return listOfStops;
	}

	public void setListOfStops(ArrayList<Stop> listOfStops) {
		this.listOfStops = listOfStops;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
