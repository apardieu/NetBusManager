/**
 * 
 */
package fr.utbm.gl52.netbusmanager.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author dbissari
 *
 */
public class EntityManagerUtil {
	private static final EntityManagerFactory entityManagerFactory;
	
  	static {
	    try {
	    	entityManagerFactory = Persistence.createEntityManagerFactory("netbusmanager");
	    } 
	    catch (Throwable ex) {
	    	System.err.println("Initial SessionFactory creation failed." + ex);
	    	throw new ExceptionInInitializerError(ex);
	    }
  	}
	
  	/**
  	 * @return a new entity manager
  	 */
  	public static EntityManager getEntityManager() {
	  	return entityManagerFactory.createEntityManager();
  	}
}
