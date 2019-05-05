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
	
	// A CHANGER POUR FAIRE UNE CLASSE ADRESSE JE PENSE
	private String address;
	private Double latitude;
	private Double longitude;
	
	
	
	
	public Stop(String name, Double latitude, Double longitude) {
		super();
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
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
		return new String(this.name+" "+this.latitude+" "+this.longitude);
		
	}
	
	
	
}
