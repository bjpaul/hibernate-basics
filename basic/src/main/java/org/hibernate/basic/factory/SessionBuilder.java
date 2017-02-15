package org.hibernate.basic.factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.resource.transaction.spi.TransactionStatus;

/**
 * A SessionFactory is set up once for an application!
 * 
 * */
public class SessionBuilder {

	private static SessionFactory sessionFactory;
    
	public static void setUp() throws Exception {
		
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.loadProperties("org/hibernate/cfg/properties/hibernate-db.properties") // not required. configures settings from *.properties, default hibernate.properties
				.configure() // here it required only to map entities
				.build();
		try {
			sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
		}
		catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
			// so destroy it manually.
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy( registry );
		}
	}
	
	public static Session curentSession() throws Exception{
		while(sessionFactory == null){
			synchronized (SessionBuilder.class) {
				if(sessionFactory == null){
					setUp();
				}
			}
		}
		Session session = sessionFactory.getCurrentSession();
		if(session.getTransaction().getStatus() == TransactionStatus.NOT_ACTIVE){
			session.beginTransaction();
		}
		return session;
	}

}
