package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import operations.EmpOperations;

public class EmployeeList {

	private ArrayList<Employee> elist;
	private EmpOperations ao;
	private ResultSet rset;

	public EmployeeList(EmpOperations ao) {

		this.ao = ao;
		elist = new ArrayList<Employee>();
	}

	
	//Removes employees from the ArrayList and re populates the ArrayList from the resultsSet
	public void refreshList() {
		rset = ao.getEmployee();

		if (elist.size() > 0) {
			for (int i = elist.size() - 1; i >= 0; i--) {
				elist.remove(i);
			}
		}
		try {
			while (rset.next()) {
				Employee e = new Employee(rset.getInt(1), rset.getString(2),
						rset.getString(3), rset.getString(4),
						rset.getString(5), rset.getString(6),
						rset.getString(7), rset.getString(8), rset.getInt(9),
						rset.getString(10));
				elist.add(e);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		finally {  
		    if(rset != null) {  
		        try {  
		        	rset.close();  
		        	ao.closeReultSets();
		        }  
		        catch (SQLException e) {}  
		    }  
		}
	}

	
	//adds the last employee added to the DB to the Arraylist
	public void addContact() {
		rset = ao.getLastRow();
		try {
			{
				Employee e = new Employee(rset.getInt(1), rset.getString(2),
						rset.getString(3), rset.getString(4),
						rset.getString(5), rset.getString(6),
						rset.getString(7), rset.getString(8), rset.getInt(9),
						rset.getString(10));
				elist.add(e);
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
		finally {  
		    if(rset != null) {  
		        try {  
		        	rset.close();  
		        	ao.closeReultSets();
		        }  
		        catch (SQLException e) {}  
		    }  
		}
	}

	//Returns an employee from the ArrayList based on the value i passed in
	public Employee getEmployee(int i) {
		refreshList();
		return elist.get(i);
	}

	//returns the size of the ArrayList
	public int getNumEmployee() {
		return elist.size();
	}

	
	//Deletes an employee from the ArrayList and from the DB
	public int removeEmployee(int id) {
		int num = 0;
		
		for (int i = 0; i < elist.size(); i++) {
			
			if (id == elist.get(i).getEmpID()) {
				elist.remove(i);
				ao.deleteContact(id);
				num++;
			}
		}
		return num;
	}

	//Searches the ArrayList for and employee and returns the position in the array if found
	public int findEmployee(String name) {
		int index = -1;
		for (int i = 0; i < elist.size(); i++) {
			if (elist.get(i).getfName().equals(name)) {
				index = i;
			}
		}
		return index;
	}

	//Updates the Employee values associated with the passed in employee ID
	public void updateEmployee(Employee e) {
		for (int i = 0; i < elist.size(); i++) {
			if (elist.get(i).getEmpID() == (e.getEmpID())) {
				elist.get(i).setfName(e.getfName());
				elist.get(i).setlName(e.getlName());
				elist.get(i).setHouseNum(e.getHouseNum());
				elist.get(i).setCity(e.getCity());
				elist.get(i).setStreet(e.getStreet());
				elist.get(i).setPin(e.getPin());
				elist.get(i).setPPS(e.getPPS());
				elist.get(i).setManager(e.getManager());
				elist.get(i).setHouseNum(e.getHouseNum());

				ao.updateEmployee(elist.get(i));
				
				ao.closeReultSets();
			}
		}

	}
}
