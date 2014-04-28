package operations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;


public class HomeScreenOperations 
{
	
	private Statement stmt;
	private ResultSet rset;
	private Connection conn;
	
	public HomeScreenOperations(Connection c)
	{
		conn = c;
	}
	public Boolean getStaffPin(String pin)
	{
		
		Boolean authenticate = false;
		try {

			stmt = conn.createStatement();
			String sqlStatement = "SELECT pin_num FROM EMPLOYEE";
			rset = stmt.executeQuery(sqlStatement);
			
			while (rset.next())
			{
				if(pin.equals(rset.getString(1)))
				{
					authenticate = true;
				}
			}	
		} catch (Exception ex)
		{
			ex.printStackTrace();
			System.out.println("ERROR: " + ex.getMessage());
		}
		finally {  
		    if(rset != null) {  
		        try {  
		        	rset.close();  
		        }  
		        catch (SQLException e) {}  
		    }  
		}
		return authenticate;
	}
	
	
	
	
	public Boolean getMangPin(String pin)
	{
		
		Boolean authenticate = false;
		try {

			stmt = conn.createStatement();
			String sqlStatement = "SELECT pin_num FROM EMPLOYEE WHERE manager =" + "'" + "Y" + "'";
			rset = stmt.executeQuery(sqlStatement);
			
			while (rset.next())
			{
				if(pin.equals(rset.getString(1)))
				{
					authenticate = true;
				}
			}	
		} catch (Exception ex)
		{
			ex.printStackTrace();
			System.out.println("ERROR: " + ex.getMessage());
		}
		finally {  
		    if(rset != null) {  
		        try {  
		        	rset.close();
		        }  
		        catch (SQLException e) {}  
		    }  
		}
		return authenticate;
	}
	
	public String getUserName(String pin)
	{
		String fName=null;
		String lName=null;
		try {

			stmt = conn.createStatement();
			String sqlStatement = "select f_name, l_name from employee where pin_num = '"+pin+"'";
			rset = stmt.executeQuery(sqlStatement);
			
			while (rset.next())
			{
				fName = rset.getString("f_name");
				lName = rset.getString("l_name");
			}	
		} catch (Exception ex)
		{
			ex.printStackTrace();
			System.out.println("ERROR: " + ex.getMessage());
		}
		finally {  
		    if(rset != null) {  
		        try {  
		        	rset.close();
		        }  
		        catch (SQLException e) {}  
		    }  
		}
		
		String name = fName+" "+lName;
		return name;
	}
	
}
