/**
 * 
 */
package fr.utbm.gl52.netbusmanager.dao;

import java.util.List;

import javax.persistence.Query;

import fr.utbm.gl52.netbusmanager.model.Stop;
import fr.utbm.gl52.netbusmanager.model.StopTime;
import fr.utbm.gl52.netbusmanager.model.Trip;

/**
 * @author bright
 *
 */
public class StopTimeDao extends AbstractDao<StopTime> {
	
	/**
	 * @param trip the to find stop times
	 * @return all saved stop time for the trip
	 */
	public List<StopTime> getAllByTrip(Trip trip) {
		Query q = entityManager.createQuery("select st from StopTime st where st.trip = :trip");
		q.setParameter("trip", trip);
		
		return q.getResultList();
	}
	
	/**
	 * @param stop the to find stop times
	 * @return all saved stop time for the stop
	 */
	public List<StopTime> getAllByStop(Stop stop) {
		Query q = entityManager.createQuery("select st from StopTime st where st.stop = :stop");
		q.setParameter("stop", stop);
		
		return q.getResultList();
	}
	
	/**
	 * @param stop the to find stop times
	 * @param trip the to find stop times
	 * @return stop time for the stop and the trip
	 */
	public StopTime getByStopAndTrip(Stop stop, Trip trip) {
		Query q = entityManager.createQuery("select st from StopTime st where st.stop = :stop and st.trip = :trip");
		q.setParameter("stop", stop);
		q.setParameter("trip", trip);
		
		return (StopTime)q.getSingleResult();
	}
	
	/**
	 * @param id the id of the stop time to find
	 * @return the searched stop time
	 */
	public StopTime getById(Integer id) {
		return entityManager.find(StopTime.class, id);
	}
	
}
