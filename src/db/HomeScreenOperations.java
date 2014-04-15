package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import oracle.jdbc.pool.OracleDataSource;

public class HomeScreenOperations 
{
	
	private Statement stmt;
	private ResultSet rset;
	private Connection conn;
	private PreparedStatement pstmt;
	

	
	public void openDB()
	{
		try
		{
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
						String name = JOptionPane.showInputDialog(null, "Enter your orcale user name");
						String pswd = JOptionPane.showInputDialog(null, "Enter your password");
						 ods.setUser(name);
						 ods.setPassword(pswd);
						}
				}

//			 Tallaght Database
//			 ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");
//			 ods.setUser("");
//			 ods.setPassword("");

			conn = ods.getConnection();
			System.out.println("Connection Established.\n");
		}
		catch(Exception e)
		{
			System.out.println("Unable to find driver " + e);
			System.exit(1);
		}
		
	}
	
	public void closeDB()
	{
		try
		{
			stmt.close();
			conn.close();

			System.out.print("\nConnection closed");
		} 
		catch (SQLException e)
		{
			System.out.print("\nCould not close connection ");
			e.printStackTrace();
		}
	}
	
	
	public Boolean getStaffPin(String pin)
	{
		System.out.println(pin);
		openDB();
		
		Boolean authenticate = false;
		try {

			stmt = conn.createStatement();
			String sqlStatement = "SELECT pin_num FROM EMPLOYEE";
			rset = stmt.executeQuery(sqlStatement);
			
			rset.next();
			
			if(pin.equals(rset.getString(1)))
			{
				authenticate = true;
				System.out.println("true pin ");
			}
			
			
		} catch (Exception ex)
		{
			ex.printStackTrace();
			System.out.println("ERROR: " + ex.getMessage());
		}
		return authenticate;
	}

}
