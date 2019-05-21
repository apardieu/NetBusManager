/**
 * 
 */
package fr.utbm.gl52.netbusmanager.dao;

import java.util.List;

import fr.utbm.gl52.netbusmanager.model.Stop;

/**
 * @author dbissari
 *
 */
public class StopDao extends AbstractDao<Stop> {
	
	/**
	 * @return all saved stops
	 */
	public List<Stop> getAll() {
		return entityManager.createQuery("select s from Stop s").getResultList();
	}
	
	/**
	 * @param id the id of the stop to find
	 * @return the searched stop
	 */
	public Stop getById(Integer id) {
		return entityManager.find(Stop.class, id);
	}
	
}
