package org.hibernate.concurrency.locking;


import javax.persistence.LockModeType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.concurrency.domain.Employee;
import org.hibernate.concurrency.factory.SessionBuilder;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.hibernate.concurrency.locking.Test.*; 

public class TestPessimisticLocking {

	private static Logger logger = LoggerFactory.getLogger(TestPessimisticLocking.class);
	
	public static void main(String args[]) throws InterruptedException{

		execute(() -> selectEmployee("Reader 1", LockModeType.PESSIMISTIC_READ, 20000));		
		Thread.sleep(2000);
		execute(() -> selectEmployee("Reader 2", LockModeType.PESSIMISTIC_READ, 0));

//		execute(() -> selectEmployee("Reader", LockModeType.PESSIMISTIC_WRITE, 10000));		
//		Thread.sleep(2000);
//		execute(() -> updateEmployee("Writer ", LockModeType.PESSIMISTIC_WRITE));
		
//		execute(() -> selectEmployee("Reader", LockModeType.PESSIMISTIC_READ, 10000));		
//		Thread.sleep(2000);
//		execute(() -> updateEmployee("Writer ", LockModeType.PESSIMISTIC_WRITE));

//		execute(() -> selectEmployee("version_increment ", LockModeType.PESSIMISTIC_FORCE_INCREMENT, 0));
	}
	
	private static void selectEmployee(String statement, LockModeType lockModeType, long sleepTime ) {
		SessionFactory sessionFactory = SessionBuilder.factory();
		Session session = sessionFactory.openSession();
		
		Query<Employee> query = session.createQuery(
			    "select e " +
			    "from Employee e " +
			    "where e.deptId = :deptId", Employee.class)
			.setParameter("deptId", "JVM")
			.setLockMode(lockModeType); 
		
		session.beginTransaction();
		logger.info(statement+": begin transaction");
		
		Employee employee = query.getSingleResult();
		logger.info(statement+": employee result => "+employee);
		
		logger.info(statement+": sleep for "+sleepTime+" millis");
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
		
		session.getTransaction().commit();
		logger.info(statement+": closing transaction");
	}
	
	private static void updateEmployee(String statement, LockModeType lockModeType) {
		SessionFactory sessionFactory = SessionBuilder.factory();
		Session session = sessionFactory.openSession();
		
		Query<Employee> query = session.createQuery(
			    "select e " +
			    "from Employee e " +
			    "where e.deptId = :deptId", Employee.class)
//			    "where e.gender = :gender", Employee.class)
			.setParameter("deptId", "JVM")
//			.setParameter("gender", 'F')
//			.setParameter("gender", 'M')
			.setLockMode(lockModeType); 
		
		session.beginTransaction();
		logger.info(statement+": begin transaction");
		
		Employee employee = query.getSingleResult();
		logger.info(statement+": employee result => "+employee);
		
		logger.info(statement+": update employee.name= 'changed'");
		employee.setName("changed");
		session.save(employee);
		
		session.getTransaction().commit();
		logger.info(statement+": employee result => "+employee);
		logger.info(statement+": closing transaction");
	}
	
	
}
