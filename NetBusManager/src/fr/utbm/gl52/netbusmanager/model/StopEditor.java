package fr.utbm.gl52.netbusmanager.model;

import java.util.ArrayList;

public class StopEditor {
	
	
	private ArrayList<Stop> availableStopList;
	
	// A changer pour mettre une classe dédiée aux villes 
	private ArrayList<String> availableCitiesList;
	
	
	
	public StopEditor() {
		this.availableStopList = new ArrayList<Stop>();
		this.availableStopList.add(new Stop("Savoureuse",0.0,0.0));
		this.availableStopList.add(new Stop("Chemin des cuicuis",5.0,5.0));
		this.availableStopList.add(new Stop("Foret enchantee",10.0,10.0));	
		
		this.availableCitiesList = new ArrayList<String>();
		this.availableCitiesList.add("Belfort");
	}

	public ArrayList<Stop> getAvailableStopList() {
		return availableStopList;
	}

	public void setAvailableStopList(ArrayList<Stop> availableStopList) {
		this.availableStopList = availableStopList;
	}

	public ArrayList<String> getAvailableCitiesList() {
		return availableCitiesList;
	}

	public void setAvailableCitiesList(ArrayList<String> availeCitiesList) {
		this.availableCitiesList = availeCitiesList;
	}
	
	
	public boolean isStopAlreadyExisting(Stop stop) {
		for(Stop s : this.availableStopList) {
			System.out.println(s.getName());
			if(s.getName().equals(stop.getName())||((s.getLatitude().equals(stop.getLatitude())&&(s.getLongitude().equals(stop.getLatitude())))))
				return true;
		}	
		return false;
	}
	
	public void addStopToDataBase(Stop stop) {
		this.availableStopList.add(stop);
	}
	
	public Stop getStopFromName(String name) {
		
		for(Stop s : this.availableStopList)
			if(s.getName().equals(name))
				return s;
		return null;
	}

}
