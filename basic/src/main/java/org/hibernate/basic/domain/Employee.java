package org.hibernate.basic.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {

	private long empId;
	private String deptId;
	private String name;
	private char gender;
	private boolean isInProvisionPeriod;
	private double salary;
	
	public Employee(){}
	
	public Employee(long empId, String deptId, String name, char gender,
			boolean isInProvisionPeriod, double salary) {
		super();
		this.empId = empId;
		this.deptId = deptId;
		this.name = name;
		this.gender = gender;
		this.isInProvisionPeriod = isInProvisionPeriod;
		this.salary = salary;
	}

	@Id
	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public boolean isInProvisionPeriod() {
		return isInProvisionPeriod;
	}

	public void setInProvisionPeriod(boolean isInProvisionPeriod) {
		this.isInProvisionPeriod = isInProvisionPeriod;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", deptId=" + deptId + ", name="
				+ name + ", gender=" + gender + ", isInProvisionPeriod="
				+ isInProvisionPeriod + ", salary=" + salary + "]";
	}
	
	
	
}
