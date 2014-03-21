package model;

import java.sql.ResultSet;
import java.util.ArrayList;

import db.AdminOperations;

public class EmployeeList {

	private ArrayList<Employee> elist;
	private AdminOperations ao;
	private ResultSet rset;
	
	public EmployeeList(AdminOperations ao)
	{
		
		this.ao = ao;
		elist = new ArrayList<Employee>();

	}
	
	public void refreshList()
	{
		rset = ao.getEmployee();
		
		if(elist.size() > 0)
		{
			for(int i = elist.size()-1; i >=0; i--)
			{
				elist.remove(i);
			}
		}
		try{
			while(rset.next())
			{
				Employee e = new Employee(rset.getInt(1), rset.getString(2), rset.getString(3), 
						rset.getString(4), rset.getString(5), rset.getString(6), 
						rset.getString(7), rset.getString(8), rset.getInt(9), rset.getString(10));
				elist.add(e);
			}
		}catch(Exception ex){
			System.out.println(ex);
		}
	}
	
	public Employee getEmployee(int i) {
		refreshList();
		return elist.get(i);
	}
	
	public int getNumEmployee() {
		return elist.size();
	}
	
	public int removeEmployee(String name) {
		int num = 0;
		for (int i = 0; i < elist.size(); i++) {
			if (name.equals(elist.get(i).getfName())) {
				elist.remove(i);
//				ao.deleteContact(name);
				num++;
			}
		}
		return num;
	}
	public void updateEmployee(String houseNum, String name) {
		for (int i = 0; i < elist.size(); i++) {
			if (elist.get(i).getfName().equals(name))
				elist.get(i).setHouseNum(houseNum);
		}
		//co.updatephoneNumber(newnum,name);
	}
}
