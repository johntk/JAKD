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
	
	public ResultSet queryProduct(String prodInput) throws SQLException
	{
		System.out.println("in query product");

		/*String sql[] = new String[6];
		sql[0] = "SELECT p.prod_id,d.dvd_name,d.dvd_sale_price FROM product p, DIGITAL_PRODUCT dp, dvd d where p.PROD_ID = dp.PROD_ID AND dp.DIG_ID = d.DIG_ID AND p.prod_id = '" + prodInput + "'";
		sql[1] = "SELECT p.prod_id,c.album_name,c.cd_sale_price FROM product p, DIGITAL_PRODUCT dp, cd c where p.PROD_ID = dp.PROD_ID AND dp.DIG_ID = c.DIG_ID AND p.prod_id = '" + prodInput + "'";
		sql[2] = "SELECT p.prod_id,g.game_name,g.game_sale_price FROM product p, DIGITAL_PRODUCT dp, game g where p.PROD_ID = dp.PROD_ID AND dp.DIG_ID = g.DIG_ID AND p.prod_id = '" + prodInput + "'";
		sql[3] = "SELECT p.prod_id,e.manufacturer||' - '||e.model,c.CONSOLE_SALE_PRICE FROM product p, electronic e, console c where p.PROD_ID = e.PROD_ID AND e.elec_id = c.elec_ID AND p.prod_id = '" + prodInput + "'";
		sql[4] = "SELECT p.prod_id,e.manufacturer||' - '||e.model,sd.sd_SALE_PRICE FROM product p, electronic e, sound_dock sd where p.PROD_ID = e.PROD_ID AND e.elec_id = sd.elec_ID AND p.prod_id = '" + prodInput + "'";
		sql[5] = "SELECT p.prod_id,e.manufacturer||' - '||e.model,hp.HEADPHONE_SALE_PRICE FROM product p, electronic e, headphones hp where p.PROD_ID = e.PROD_ID AND e.elec_id = hp.elec_ID AND p.prod_id = '" + prodInput + "'";
		*/
		String sql = "SELECT p.prod_id,d.dvd_name,d.dvd_sale_price FROM product p, DIGITAL_PRODUCT dp, dvd d where p.PROD_ID = dp.PROD_ID AND dp.DIG_ID = d.DIG_ID AND p.prod_id = '" + prodInput + "'" +
				"union  SELECT p.prod_id,c.album_name,c.cd_sale_price FROM product p, DIGITAL_PRODUCT dp, cd c where p.PROD_ID = dp.PROD_ID AND dp.DIG_ID = c.DIG_ID AND p.prod_id = '" + prodInput + "'" +
				"union  SELECT p.prod_id,g.game_name,g.game_sale_price FROM product p, DIGITAL_PRODUCT dp, game g where p.PROD_ID = dp.PROD_ID AND dp.DIG_ID = g.DIG_ID AND p.prod_id = '" + prodInput + "'" +
				"union  SELECT p.prod_id,e.manufacturer||' - '||e.model,c.CONSOLE_SALE_PRICE FROM product p, electronic e, console c where p.PROD_ID = e.PROD_ID AND e.elec_id = c.elec_ID AND p.prod_id = '" + prodInput + "'" +
				"union  SELECT p.prod_id,e.manufacturer||' - '||e.model,sd.sd_SALE_PRICE FROM product p, electronic e, sound_dock sd where p.PROD_ID = e.PROD_ID AND e.elec_id = sd.elec_ID AND p.prod_id = '" + prodInput + "'" +
				"union  SELECT p.prod_id,e.manufacturer||' - '||e.model,hp.HEADPHONE_SALE_PRICE FROM product p, electronic e, headphones hp where p.PROD_ID = e.PROD_ID AND e.elec_id = hp.elec_ID AND p.prod_id = '" + prodInput + "'";
		try 
		{
			System.out.println("in try");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
		}
		
		catch(SQLException e)
		{
			System.out.println("Couldn't find product");
		}
			
			
		return rset;
	}
	
	
	public String queryTransid()
	{
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

			}
		}
		

		
		catch(SQLException e)
		{
			System.out.println(e);
			System.out.println("no trans_id found");
		}
		

		return Integer.toString(trans_id);
		
	}
	public void insertTran()
	{
		//String s = "insert into TRANSACTION values(" + tranID + "," tranDate+ "," + transType + "," + totalCost+ "," + quantity + "," + empID + "," + prodID)"; 
	}

}
