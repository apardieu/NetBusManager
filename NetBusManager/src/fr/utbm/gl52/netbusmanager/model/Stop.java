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
public class Stop implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private Double latitude;
	
	@Column(nullable = false)
	private Double longitude;
	
	public Stop() {
		
	}
	
	/**
	 * @param name the name
	 * @param latitude the latitude
	 * @param longitude the longitude
	 */
	public Stop(String name, Double latitude, Double longitude) {
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	/**
	 * @param name the name
	 * @param latitude the latitude
	 * @param longitude the longitude
	 * @param address the address
	 */
	public Stop(String name, Double latitude, Double longitude, String address) {
		this(name, latitude, longitude);
		this.address = address;
	}

	/**
	 * @return the id
	 */
    public Integer getId() {
        return id;
    }

    /**
	 * @param id the id to set
	 */
    public void setId(Integer id) {
        this.id = id;
    }

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return this.latitude;
	}
	
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return this.longitude;
	}
	
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Stop [id=" + id + ", name=" + name + ", address=" + address + ", latitude=" + latitude + ", longitude="
				+ longitude + "]";
	}
	
}
