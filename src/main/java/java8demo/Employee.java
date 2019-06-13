package java8demo;

public class Employee {
	
	
	public Employee(String empNo,String empName,String city,int age){
		this.empName=empName;
		this.empNo=empNo;
		this.city=city;
		this.age=age;
	}
	
	
	@Override
	public String toString() {
		return "Employee [empNo=" + empNo + ", empName=" + empName + ", city=" + city + ", age=" + age + "]";
	}


	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}


	String empNo;
	String empName;
	String city;
	int age;
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

}
