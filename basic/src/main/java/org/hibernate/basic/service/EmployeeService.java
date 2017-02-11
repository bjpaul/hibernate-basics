package org.hibernate.basic.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.basic.domain.Employee;
import org.hibernate.basic.factory.SessionBuilder;

public class EmployeeService {

	public static void main(String[] args) throws Exception {
		// create a couple of employees...
		Employee emp1 = new Employee(1909, "JVM", "Newer 1", 'M', false, 10000);
		Employee emp2 = new Employee(1919, "JVM", "Newer 2", 'F', true, 12000);
		addEmployee(emp1);
		addEmployee(emp2);
		
		// now lets pull employees from the database and list them
		list();
	}
	
	public static void addEmployee(Employee employee) throws Exception {
		
		Session session = SessionBuilder.getSession();
		session.beginTransaction();
		session.save(employee);
		session.getTransaction().commit();
		session.close();	
		
	}
	
	public static void list() throws Exception {
		Session session = SessionBuilder.getSession();
//        session.beginTransaction();
        @SuppressWarnings({ "unchecked", "deprecation" })
		List<Employee> result = session.createQuery( "from Employee" ).list();
		for ( Employee employee : (List<Employee>) result ) {
			System.out.println( employee );
		}
//        session.getTransaction().commit();
        session.close();
	}
}
