/**
 * 
 */
package fr.utbm.gl52.netbusmanager.model;

/**
 * @author dbissari
 *
 */
public class Stop {

	private String name;
	private Double latitude;
	private Double longitude;
	
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
	
}
