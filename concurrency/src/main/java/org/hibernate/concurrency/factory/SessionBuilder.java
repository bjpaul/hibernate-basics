package org.hibernate.concurrency.factory;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * A SessionFactory is set up once for an application!
 * 
 * */
public class SessionBuilder {

	private static SessionFactory sessionFactory;
    
	public static void setUp(){
		
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
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
	
	public static SessionFactory factory(){
		while(sessionFactory == null){
			synchronized (SessionBuilder.class) {
				if(sessionFactory == null){
					setUp();
				}
			}
		}
		return sessionFactory;
	}

}
