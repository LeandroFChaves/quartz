package br.com.quartz.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {

	public static EntityManagerFactory factory = null;
	private EntityManager em;

	public ConnectionFactory() {

		if (factory != null) {
			if (factory.isOpen()) {
				em = factory.createEntityManager();
			} else {
				factory = Persistence.createEntityManagerFactory("quartz");
				em = factory.createEntityManager();
			}
		} else {
			factory = Persistence.createEntityManagerFactory("quartz");
			em = factory.createEntityManager();
		}

	}

	public EntityManager getConnection() {
		return em;
	}

	public void close(){
		this.em.close();
		factory.close();
		factory = null;
	}
}