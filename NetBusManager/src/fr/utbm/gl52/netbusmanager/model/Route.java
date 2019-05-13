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

/**
 * @author dbissari
 *
 */
@Entity
public class Route implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String description;
	
	public Route() {
		
	}
	
	/**
	 * @param description the description
	 */
	public Route(String description) {
		this.description = description;
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
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Route [id=" + id + ", description=" + description + "]";
	}
	
}
