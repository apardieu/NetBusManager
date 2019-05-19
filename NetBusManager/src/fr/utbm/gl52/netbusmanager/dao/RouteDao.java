/**
 * 
 */
package fr.utbm.gl52.netbusmanager.dao;

import java.util.List;

import fr.utbm.gl52.netbusmanager.model.Route;


/**
 * @author dbissari
 *
 */
public class RouteDao extends AbstractDao<Route> {
	
	/**
	 * @return all saved routes
	 */
	public List<Route> getAll() {
		return entityManager.createQuery("select r from Route r").getResultList();
	}
	
	/**
	 * @param id the id of the route to find
	 * @return the searched stop
	 */
	public Route getById(Integer id) {
		return entityManager.find(Route.class, id);
	}
	
}
