package org.hibernate.concurrency.locking;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.concurrency.domain.Employee;
import org.hibernate.concurrency.factory.SessionBuilder;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestVersion {
	private static Logger logger = LoggerFactory.getLogger(TestVersion.class);
	
	public static void main(String[] args) {
		selectEmployee("No-Change ", null);
//		selectEmployee("No-Change ", "New Value");
	}
	
	private static void selectEmployee(String statement, String changes) {
		SessionFactory sessionFactory = SessionBuilder.factory();
		Session session = sessionFactory.openSession();
		
		Query<Employee> query = session.createQuery(
			    "select e " +
			    "from Employee e " +
			    "where e.deptId = :deptId", Employee.class)
			.setParameter("deptId", "JVM");
		
		session.beginTransaction();
		logger.info(statement+": begin transaction and fetching employee ");
		
		Employee employee = query.getSingleResult();
		logger.info(statement+": employee result => "+employee);
		
		logger.info(statement+": updating employee ");
		if(changes != null && !changes.isEmpty()){
			employee.setName(changes);	
		}
		session.save(employee);
		
		session.getTransaction().commit();
		logger.info(statement+": closing transaction");
		
		logger.info(statement+": employee result => "+employee);
	}
	
}
