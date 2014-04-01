
package db;

import java.sql.*;
import java.awt.*;

import javax.swing.*;

import model.Employee;
import oracle.jdbc.pool.OracleDataSource;

public class AdminOperations {

	private Statement stmt;
	private ResultSet rset;
	private Connection conn;
	private PreparedStatement pstmt;

	public AdminOperations() {
		conn = openDB();
	}

	public Connection openDB() {

		try {
			
			// Tallaght Database
			// ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");
			// ods.setUser("x00097736");
			// ods.setPassword("db02Jan86");
			
			// Load the Oracle JDBC driver
			OracleDataSource ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:HR/@localhost:1521:XE");
			
			try{
				//Add a try for your own username and password if your sick of changing this all the time like I am.
				//Please don't change the details below.
				ods.setUser("johntk86");
				ods.setPassword("FuckYou");
				conn = ods.getConnection();
			}
			
			 catch (Exception e) {
				 try{
						ods.setUser("project");
						ods.setPassword("project");
						conn = ods.getConnection();
					}
					
					 catch (Exception ex) {
						 System.out.println("Unable to find driver " + ex);
							System.exit(1);
						}
				}

			conn = ods.getConnection();
			System.out.println("Connected.");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to Connect", "Failed to Connect", JOptionPane.INFORMATION_MESSAGE);
			System.out.print("Unable to load driver " + e);
			System.exit(1);
		}
		return conn;
	}

	public ResultSet queryEmployee() {
		String sqlStatement = "SELECT * FROM Employee";
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

	public void addEmployee(Employee e) {
		try {
			String sql = "INSERT INTO Employee(emp_id, f_name, l_name, house_number, street, town,"
					+ " city, pps_num, pin_num, manager) "
					+ "VALUES (empId_seq.nextVal,?,?,?,?,?,?,?,?,?) ";

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
		} catch (Exception se) {
			System.out.println(se);
		}
	}
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

	public int deleteContact(String n) {
		int no = 0;
		try {
			String cmd = "DELETE FROM Employee WHERE f_name =" + "'" + n + "'";
			stmt = conn.createStatement();
			no = stmt.executeUpdate(cmd);
		} catch (Exception e) {
			System.out.println(e);
		}
		return no;

	}

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

	public void closeDB() {
		try {
			conn.close();
			System.out.print("Connection closed");
		} catch (SQLException e) {
			System.out.print("Could not close connection ");
			e.printStackTrace();
		}
	}
}

