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
	
	public ResultSet queryProduct(String prodInput)
	{
		
		try {
			stmt = conn.createStatement();
			String sqlStatement = "select d.dvd_name as description, d.dvd_sale_price as salePrice, p.prod_id as prodID from dvd d, product p, digital_product dp where UPPER(dvd_name) like UPPER('%"+prodInput+"%') and p.prod_id = dp.prod_id and d.dig_id = dp.dig_id "+
					"UNION select g.game_name, g.game_sale_price, p.prod_id as prodID from game g, product p, digital_product dp where UPPER(game_name) like UPPER('%"+prodInput+"%') and p.prod_id = dp.prod_id and g.dig_id = dp.dig_id "+
					"UNION select c.album_name, c.cd_sale_price, p.prod_id as prodID from cd c, digital_product d, product p where c.dig_id = d.dig_id and p.prod_id = d.prod_id like UPPER('%"+prodInput+"%') "+
					"UNION select e.manufacturer||' '||e.model, h.headphone_sale_price, p.prod_id as prodID from electronic e, headphones h, product p where e.elec_id = h.elec_id and p.prod_id = e.prod_id and UPPER(e.manufacturer||' '||e.model) like UPPER('%"+prodInput+"%') "+
					"UNION select e.manufacturer||' '||e.model, s.sd_sale_price, p.prod_id as prodID from electronic e, sound_dock s, product p where e.elec_id = s.elec_id and p.prod_id = e.prod_id and UPPER(e.manufacturer||' '||e.model) like UPPER('%"+prodInput+"%') "+
					"UNION select e.manufacturer||' '||e.model, c.console_sale_price, p.prod_id as prodID from electronic e, console c, product p where e.elec_id = c.elec_id and p.prod_id = e.prod_id and UPPER(e.manufacturer||' '||e.model) like UPPER('%"+prodInput+"%') ";
			rset = stmt.executeQuery(sqlStatement);
		
		
		
		
		
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
