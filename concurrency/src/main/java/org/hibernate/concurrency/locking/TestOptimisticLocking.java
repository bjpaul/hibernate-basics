package org.hibernate.concurrency.locking;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.concurrency.domain.Address;
import org.hibernate.concurrency.factory.SessionBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hibernate.concurrency.locking.Test.*; 

public class TestOptimisticLocking {

	private static Logger logger = LoggerFactory.getLogger(TestOptimisticLocking.class);
	
	public static void main(String args[]) throws InterruptedException{
		
		execute(() -> update("Writer 1", 10000));		
		Thread.sleep(2000);
		execute(() -> update("Writer 2", 0));
	}
	
	private static void update(String statement, long sleepTime) {
		SessionFactory sessionFactory = SessionBuilder.factory();
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		logger.info(statement+": begin transaction ");
		
		logger.info(statement+": fetching address");
		Address address = session.get(Address.class, 1l);
		logger.info(statement+": address => "+address);
		
		if(sleepTime > 0){
			logger.info(statement+": sleep for "+sleepTime+" millis");
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		}
		logger.info(statement+": update address.city = 'changed' ");
		address.setCity("changed ");
		session.save(address);
		
		session.getTransaction().commit();
		logger.info(statement+": address => "+address);
		logger.info(statement+": closing transaction");
		
	}
		
}
