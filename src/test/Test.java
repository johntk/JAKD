package test;

import java.sql.ResultSet;

import model.EmployeeList;
import db.AdminOperations;
import gui.AdminGUI;

public class Test {

	public static void main(String[] args) {
		
		
		AdminOperations ao = new AdminOperations();
		EmployeeList el = new EmployeeList(ao);
		AdminGUI ag = new AdminGUI(ao, el);
		

	}

}
