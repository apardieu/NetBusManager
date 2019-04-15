/**
 * 
 */
package fr.utbm.gl52.netbusmanager.model;

import java.util.Date;

/**
 * @author dbissari
 *
 */
public class StopTime {

	private Trip trip;
	private Stop stop;
	private Integer stopSequence;
	private Date arrivalTime;
	private Date departureTime;
	
	/**
	 * @return the trip
	 */
	public Trip getTrip() {
		return this.trip;
	}
	
	/**
	 * @param trip the trip to set
	 */
	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	
	/**
	 * @return the stop
	 */
	public Stop getStop() {
		return this.stop;
	}
	
	/**
	 * @param stop the stop to set
	 */
	public void setStop(Stop stop) {
		this.stop = stop;
	}
	
	/**
	 * @return the stopSequence
	 */
	public Integer getStopSequence() {
		return this.stopSequence;
	}
	
	/**
	 * @param stopSequence the stopSequence to set
	 */
	public void setStopSequence(Integer stopSequence) {
		this.stopSequence = stopSequence;
	}
	
	/**
	 * @return the arrivalTime
	 */
	public Date getArrivalTime() {
		return this.arrivalTime;
	}
	
	/**
	 * @param arrivalTime the arrivalTime to set
	 */
	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	/**
	 * @return the departureTime
	 */
	public Date getDepartureTime() {
		return this.departureTime;
	}
	
	/**
	 * @param departureTime the departureTime to set
	 */
	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}
	
}
