package model;

public class Employee {

	private int empID;
	private String fName;
	private String lName;
	private String houseNum;
	private String street;
	private String town;
	private String city;
	private String PPS;
	private int pin;
	private String manager;
	//
	public Employee(int empID, String fName, String lName, String houseNum, String street,  String town, String city, String PPS, int pin, String manager)
	{
		this.empID = empID;
		this.fName = fName;
		this.lName = lName;
		this.houseNum = houseNum;
		this.street = street;
		this.town = town;
		this.city = city;
		this.PPS = PPS;
		this.pin = pin;
		this.manager = manager;
		
	}

	public int getEmpID() {
		return empID;
	}


	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPPS() {
		return PPS;
	}

	public void setPPS(String pPS) {
		PPS = pPS;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
	
}
