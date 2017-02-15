package org.hibernate.basic.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.basic.domain.Employee;
import org.hibernate.basic.factory.SessionBuilder;

public class EmployeeService {

	public static void main(String[] args) throws Exception {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// create a couple of employees...
				Employee emp1 = new Employee(1909, "JVM", "Newer 1", 'M', false, 10000);
				Employee emp2 = new Employee(1919, "JVM", "Newer 2", 'F', true, 12000);
				Session session = null;
				Transaction transaction = null;
				try {
					session = SessionBuilder.curentSession();
					transaction = session.getTransaction();
					session.save(emp1);
					session.save(emp2);
					session.getTransaction().commit();
				} catch (Exception e) {
					if(transaction != null && transaction.getStatus().canRollback()){
						transaction.rollback();
					}
					e.printStackTrace();
				}
			}
		});
		thread.start();
		thread.join();
		// now lets pull employees from the database and list them
		list();
		SessionBuilder.curentSession().close();
	}
	
	public static void list() throws Exception {
		Session session = SessionBuilder.curentSession();
        @SuppressWarnings({ "unchecked", "deprecation" })
		List<Employee> result = session.createQuery( "from Employee" ).list();
		for ( Employee employee : (List<Employee>) result ) {
			employee.setName(employee.getName()+1);
			System.out.println( employee );
		}
	}
}
