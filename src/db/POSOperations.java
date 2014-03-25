package db;
import java.sql.*;

import oracle.jdbc.pool.OracleDataSource;

public class POSOperations 
{
	
	private Statement stmt;
	private ResultSet rset;
	private Connection conn;
	private PreparedStatement pstmt;
	

	public POSOperations() 
	{
		
	}
	
	public void openDB()
	{
		try
		{
			// Load the Oracle JDBC driver
			OracleDataSource ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:HR/@localhost:1521:XE");
			ods.setUser("project");
			ods.setPassword("project"); ////

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
	
	public ResultSet queryProduct(int prodInput)
	{
		
		String prodID;
		String description;
		double salePrice;
		
		try {
			stmt = conn.createStatement();
			
			String sqlStatement  = "SELECT p.prod_id,d.dvd_name,d.dvd_sale_price FROM product p, DIGITAL_PRODUCT dp, dvd d where p.PROD_ID = dp.PROD_ID AND dp.DIG_ID = d.DIG_ID AND p.prod_id = '" + prodInput + "'";
			rset = stmt.executeQuery(sqlStatement);
			if (rset == null)
			{
				sqlStatement = "SELECT p.prod_id,c.album_name,c.cd_sale_price FROM product p, DIGITAL_PRODUCT dp, cd c where p.PROD_ID = dp.PROD_ID AND dp.DIG_ID = c.DIG_ID AND p.prod_id = '" + prodInput + "'";
				rset = stmt.executeQuery(sqlStatement);
			
			}
					
			
		
		
		
		
		
		try
		{
			String queryProduct = "SELECT * FROM PRODUCT WHERE prod_id = '" + prodInput + "'";
		
			stmt = conn.createStatement();
			rset = stmt.executeQuery(queryProduct);
		}
		catch (SQLException e)
		{
			System.out.println("Couldnt find product");
		}
		return rset;
	}
	
	
	public String queryTransid()
	{
		openDB();
		int trans_id = 0;
		
		try
		{
			
			String queryTransid = "SELECT trans_id FROM TRANSACTION GROUP BY trans_id";

			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			rset = stmt.executeQuery(queryTransid);
			
			rset.afterLast();
			System.out.println("below print");
			
			if (rset.previous()) 
			{
				  trans_id = Integer.parseInt(rset.getString(1)) + 1;
				  System.out.println("trans_id");
			}
		}
		
		catch(SQLException e)
		{
			System.out.println(e);
			System.out.println("no trans_id found");
		}
		
		closeDB();
		return Integer.toString(trans_id);
		
	}
	

}
