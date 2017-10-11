package org.test;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ConfigRepo {

	@PersistenceContext(unitName="primary")
	private EntityManager manager;
	
	public String getConfigVal(String key) {
		String val = "";
		try {
			String sql = "select c From Config c where c.key = :key";
			Query query = manager.createQuery(sql);
			query.setParameter("key", "prefix1");
			Config config = (Config) query.getSingleResult();
			val = config.getValue();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return val;
	}
}
