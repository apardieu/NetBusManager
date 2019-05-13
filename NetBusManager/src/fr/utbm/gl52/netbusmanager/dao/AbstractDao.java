/**
 * 
 */
package fr.utbm.gl52.netbusmanager.dao;

import javax.persistence.EntityManager;

import fr.utbm.gl52.netbusmanager.util.EntityManagerUtil;

/**
 * @author dbissari
 *
 */
abstract class AbstractDao<T> {
	
	protected EntityManager entityManager;
	
	public AbstractDao() {
		entityManager = EntityManagerUtil.getEntityManager();
	}
	
	/**
	 * @param route the object to save
	 * @return true if saved successfuly
	 */
	public boolean save(T t) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(t);
			entityManager.getTransaction().commit();
			return true;
		}
		catch (Exception e) {
			System.err.println(e);
			return false;
		}
	}
	
}
