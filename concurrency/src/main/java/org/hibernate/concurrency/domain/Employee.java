package org.hibernate.concurrency.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private long empId;
	private String deptId;
	private String name;
	private char gender;
//	@Version
	private Date version;
	/*	
 	@Temporal(TemporalType.DATE)
	private Date version;
	*/
	
	public Employee(){}
	
	public Employee(long empId, String deptId, String name, char gender) {
		super();
		this.empId = empId;
		this.deptId = deptId;
		this.name = name;
		this.gender = gender;
	}

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

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", deptId=" + deptId + ", name="
				+ name + ", gender=" + gender + ", version=" + version + "]";
	}
	
}
