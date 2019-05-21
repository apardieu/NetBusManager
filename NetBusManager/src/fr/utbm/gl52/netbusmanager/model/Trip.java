/**
 * 
 */
package fr.utbm.gl52.netbusmanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author dbissari
 *
 */
@Entity
public class Trip implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
//	@ManyToOne
    @JoinColumn(name = "route_id", nullable = false, referencedColumnName = "id")
	private Route route;
	
	@Column(nullable = false)
	private String direction;
	
	public Trip() {
		
	}
	
	/**
	 * @param route the route
	 * @param direction the direction
	 */
	public Trip(Route route, String direction) {
		this.route = route;
		this.direction = direction;
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
	 * @return the route
	 */
	public Route getRoute() {
		return this.route;
	}
	
	/**
	 * @param route the route to set
	 */
	public void setRoute(Route route) {
		this.route = route;
	}
	
	/**
	 * @return the direction
	 */
	public String getDirection() {
		return this.direction;
	}
	
	/**
	 * @param direction the direction to set
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {
		return "Trip [id=" + id + ", route=" + route + ", direction=" + direction + "]";
	}
	
}
