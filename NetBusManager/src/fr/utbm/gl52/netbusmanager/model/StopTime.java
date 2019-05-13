/**
 * 
 */
package fr.utbm.gl52.netbusmanager.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author dbissari
 *
 */
@Entity
public class StopTime implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "trip_id", nullable = false, referencedColumnName = "id")
	private Trip trip;
	
	@ManyToOne
    @JoinColumn(name = "stop_id", nullable = false, referencedColumnName = "id")
	private Stop stop;
	
	@Column(nullable = false)
	private Integer stopSequence;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIME)
	private Date arrivalTime;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIME)
	private Date departureTime;
	
	public StopTime() {
		
	}
	
	/**
	 * @param trip the trip
	 * @param stop the stop
	 * @param stopSequence the stopSequence
	 * @param arrivalTime the arrivalTime
	 * @param departureTime the departureTime
	 */
	public StopTime(Trip trip, Stop stop, Integer stopSequence, Date arrivalTime, Date departureTime) {
		this.trip = trip;
		this.stop = stop;
		this.stopSequence = stopSequence;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
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

	@Override
	public String toString() {
		return "StopTime [id=" + id + ", trip=" + trip + ", stop=" + stop + ", stopSequence=" + stopSequence
				+ ", arrivalTime=" + arrivalTime + ", departureTime=" + departureTime + "]";
	}
	
}
