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
			
//			 ResultSetMetaData rsmd = rset.getMetaData();
//		      int numberOfColumns = rsmd.getColumnCount();
//			
//			while (rset.next()) {
//		        for (int i = 1; i <= numberOfColumns; i++) {
//		          if (i > 1) System.out.print(",  ");
//		          String columnValue = rset.getString(i);
//		          System.out.print(columnValue);
//		        }
//		        System.out.println("");  
//		      }
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
	


}
