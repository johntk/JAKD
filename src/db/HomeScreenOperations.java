package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class HomeScreenOperations 
{
	
	private Statement stmt;
	private ResultSet rset;
	private Connection conn;
	private DBconnection db;
	
	public HomeScreenOperations()
	{
		db = new DBconnection();
		db.openDB();
		conn =  db.getCon();
		
		
	}
	
	
	public Boolean getStaffPin(String pin)
	{
		
		System.out.println(pin);
		Boolean authenticate = false;
		try {

			stmt = conn.createStatement();
			String sqlStatement = "SELECT pin_num FROM EMPLOYEE";
			rset = stmt.executeQuery(sqlStatement);
			
			while(rset.next())
			{
				if(pin.equals(rset.getString(1)))
				{
					authenticate = true;
					System.out.println("true pin ");
				}
			}
			
		} catch (Exception ex)
		{
			ex.printStackTrace();
			System.out.println("ERROR: " + ex.getMessage());
		}
		return authenticate;
	}

}
