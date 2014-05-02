
package operations;

import java.sql.*;

import javax.swing.JOptionPane;

import model.Employee;


public class EmpOperations {

	private Statement stmt;
	private ResultSet rset;
	private Connection conn;
	private PreparedStatement pstmt;

	
	public void setDBconnection(Connection conn) {
		this.conn = conn;
	}
	
	public ResultSet queryEmployee() {
		String sqlStatement = "SELECT pin_num FROM Employee";
		try {
			pstmt = conn.prepareStatement(sqlStatement,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rset = pstmt.executeQuery();
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}

		return rset;
	}

	//returns all employees from the DB sorted by ID
	public ResultSet getEmployee() {
		try {
			String queryString = "SELECT * FROM Employee ORDER BY emp_id";

			stmt = conn.createStatement();
			rset = stmt.executeQuery(queryString);
		} catch (Exception e) {
			System.out.println(e);
		}
		return rset;
	}

	//Adds an employee to the DB
	public int addEmployee(Employee e) {
		
		int newPin = -1;
		boolean go = true;
		try {
			String sql = "INSERT INTO Employee(emp_id, f_name, l_name, house_number, street, town,"
					+ " city, pps_num, pin_num, manager) "
					+ "VALUES (empId_seq.nextVal,?,?,?,?,?,?,?,?,?) ";

			//checks if the pin is already used
			rset = queryEmployee();
					while(rset.next())
					{
						if(rset.getString(1).equals(String.valueOf(e.getPin())))
						{	
							go = false;
						}
						
					}
					
				if(go == true)
				{
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, e.getfName());
					pstmt.setString(2, e.getlName());
					pstmt.setString(3, e.getHouseNum());
					pstmt.setString(4, e.getStreet());
					pstmt.setString(5, e.getTown());
					pstmt.setString(6, e.getCity());
					pstmt.setString(7, e.getPPS());
					pstmt.setInt(8, e.getPin());
					pstmt.setString(9, e.getManager());
					
					pstmt.executeUpdate();
					newPin = 0;
				}
				else
				{
					JOptionPane.showMessageDialog(null, e.getPin() + " is already in use\nEnter a different pin");
				}
				
		} catch (Exception se) {
			System.out.println(se);
		}
		return newPin;
	}
	
	//Returns the value of the last employee ID and adds one on to it
	// this is only for displaying purposes, a sequence is used for inputing
	// the ID into the database.
	public int getId() {
		int nextVal = 0;
		try {
			String seq_val = "Select emp_id from Employee ORDER BY emp_id";
			pstmt = conn.prepareStatement(seq_val,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rset = pstmt.executeQuery();

			rset.last();
			nextVal = rset.getInt(1);
			
			/*String sql2 = "Select empId_seq.nextVal from  Employee";
			pstmt = conn.prepareStatement(sql2,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			 
				rset = pstmt.executeQuery();
				
				if(rset.last())
					nextVal = rset.getInt(1) +1;
				System.out.println(nextVal);*/
			
		} catch (Exception e) {
			System.out.println(e);
		}

		return nextVal + 1;
	}

	//Deletes an employee from the DB
	public int deleteContact(int n) {
		int no = 0;
		try {
			String cmd = "DELETE FROM Employee WHERE emp_id =" + "'" + n + "'";
			stmt = conn.createStatement();
			no = stmt.executeUpdate(cmd);
		} catch (Exception e) {
			System.out.println(e);
		}
		return no;

	}

	//Updates and existing employee in the DB
	public void updateEmployee(Employee e) {
		try {

			String sql = "UPDATE Employee SET f_name=?, l_name=?, house_number=?, street=?, town=?,"
					+ " city=?, pps_num=?, pin_num=?, manager=?  WHERE emp_id ="
					+ "'" + e.getEmpID() + "'";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, e.getfName());
			pstmt.setString(2, e.getlName());
			pstmt.setString(3, e.getHouseNum());
			pstmt.setString(4, e.getStreet());
			pstmt.setString(5, e.getTown());
			pstmt.setString(6, e.getCity());
			pstmt.setString(7, e.getPPS());
			pstmt.setInt(8, e.getPin());
			pstmt.setString(9, e.getManager());
			pstmt.executeUpdate();

		} catch (Exception ex) {
			System.out.println("Problemsss" + e);
		}
	}

	
	//Gets the last row inserted into the DB.
	public ResultSet getLastRow() {
		String sqlStatement = "SELECT * FROM Employee ORDER BY emp_id";
		try {
			pstmt = conn.prepareStatement(sqlStatement,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rset = pstmt.executeQuery();
			rset.last();
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}

		return rset;
	}

	//Closes the DB.
	public void closeDB() {
		try {
			conn.close();
			System.out.print("Connection closed");
		} catch (SQLException e) {
			System.out.print("Could not close connection ");
			e.printStackTrace();
		}
	}
	//close ResultsSets, Prepared Statements and Statements
	public void closeReultSets()
	{
		try
		{
			if (rset != null) rset.close();
			if (pstmt != null) pstmt.close();
			if (stmt != null) stmt.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

