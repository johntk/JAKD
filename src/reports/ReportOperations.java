package reports;
import java.sql.*;
import java.util.ArrayList;

import oracle.jdbc.pool.OracleDataSource;
import reports.Results;

public class ReportOperations
{
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rset;
	private Results r;
	private String topDate;
	private String bottomDate;
	
	/*public ReportOperations(String topDate,String bottomDate)
	{
		this.topDate = topDate;
		this.bottomDate = bottomDate;
	}*/
	public void openDB() 
	{
		try {
			// Load the Oracle JDBC driver
			OracleDataSource ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:HR/@localhost:1521:XE");
			ods.setUser("davidprendergast");
			ods.setPassword("Ralph0886");

			// Tallaght Database
			//ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");
			//ods.setUser("x00058277");
			//ods.setPassword("db30Aug86");

			conn = ods.getConnection();
			System.out.println("connected.");
		} catch (Exception e) {
			System.out.print("Unable to load driver " + e);
			System.exit(1);
		}
	}
	public void closeDB() 
	{
		try 
		{
			stmt.close();
			rset.close();
			conn.close();
			System.out.print("Connection closed");
		}
		catch (SQLException e)
		{
			System.out.print("Could not close connection ");
			e.printStackTrace();
		}
	}
	/*public void queryDB()
	{
		ResultSet rset;
		String query = "SELECT Trans_ID,Trans_DATE,TRANS_TYPE,TOTAL_COST,QUANTITY,EMP_ID,PROD_ID FROM Transaction";
		try
		{
			stmt = conn.prepareStatement(query);
			rset = stmt.executeQuery();
			while(rset.next())
			{
				r = new Results(rset.getInt(1), rset.getDate(2), rset.getString(3), rset.getDouble(4),
										rset.getInt(5), rset.getInt(6), rset.getString(7));
				System.out.println(r);
				aList.add(r);
			}
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
	}*/
	
	public ResultSet salesReportToFromDates()
	{
		String query = "SELECT unique(trans_ID),trans_date,total_cost "
					 + "FROM TRANSACTION "
					 + "WHERE TRANS_DATE BETWEEN '"+ topDate +"' AND '"+ bottomDate +"' "
					 + "ORDER BY trans_ID";
		try
		{
			stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rset = stmt.executeQuery();
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
		return rset;
	}
	
	public ResultSet checkCurrentStock()
	{
		String query = 
		  "select p.prod_id, d.dvd_name, p.prod_type, p.current_stock, d.dvd_sale_price " 
		+ "from dvd d, product p, digital_product dp " 
		+ "where d.dig_id = dp.dig_id and dp.prod_id = p.prod_id " 
		+ "UNION "
		+ "select p.prod_id, a.artist_name ||' - ' || cd.album_name, p.prod_type, p.current_stock, cd.cd_sale_price "
		+ "from cd cd, product p, digital_product dp, artist a,cd_artist cda "
		+ "where a.artist_id = cda.artist_id and cda.cd_id = cd.cd_id and cd.dig_id = dp.dig_id and dp.prod_id = p.prod_id "
		+ "UNION " 
		+ "select p.prod_id, g.game_name, p.prod_type, p.current_stock, g.game_sale_price " 
		+ "from game g, product p, digital_product dp " 
		+ "where g.dig_id = dp.dig_id and dp.prod_id = p.prod_id " 
		+ "UNION " 
		+ "select p.prod_id, e.manufacturer ||' - ' || e.model, p.prod_type, p.current_stock, h.headphone_sale_price " 
		+ "from headphones h, product p, digital_product dp, electronic e "
		+ "where h.elec_id = e.elec_id and e.prod_id = p.prod_id "
		+ "UNION "
		+ "select p.prod_id, e.manufacturer ||' - ' || e.model, p.prod_type, p.current_stock, s.sd_sale_price " 
		+ "from sound_dock s, product p, digital_product dp, electronic e " 
		+ "where s.elec_id = e.elec_id and e.prod_id = p.prod_id "
		+ "UNION "
		+ "select p.prod_id, e.manufacturer ||' - ' || e.model, p.prod_type, p.current_stock, c.console_sale_price "
		+ "from console c, product p, digital_product dp, electronic e " 
		+ "where c.elec_id = e.elec_id and e.prod_id = p.prod_id "
		+ "order by current_stock asc";
		try
		{
			stmt = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rset = stmt.executeQuery();
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
		return rset;
	}
	public ResultSet lowStockReport()
	{
		String query =   "select p.prod_id as ProdID, p.prod_type as Type, dvd.DVD_NAME as Description, p.current_stock as CurrentStock "
						+"from product p, digital_product dp, dvd dvd "
						+"where dvd.DIG_ID = dp.DIG_ID and dp.PROD_ID = p.PROD_ID and p.current_stock < 10 "
						+"UNION "
						+"select p.prod_id, p.prod_type, a.artist_name ||' - ' || cd.album_name, p.current_stock "
						+"from cd cd, product p, digital_product dp, artist a,cd_artist cda "
						+"where a.artist_id = cda.artist_id and cda.cd_id = cd.cd_id and cd.dig_id = dp.dig_id and dp.prod_id = p.prod_id and p.current_stock < 10 "
						+"UNION "
						+"select p.prod_id,  p.prod_type, g.game_name, p.current_stock "
						+"from game g, product p, digital_product dp "
						+"where g.dig_id = dp.dig_id and dp.prod_id = p.prod_id and p.current_stock < 10 "
						+"UNION "
						+"select p.prod_id, p.prod_type, e.manufacturer ||' - ' || e.model, p.current_stock "
						+"from headphones h, product p, electronic e "
						+"where h.elec_id = e.elec_id and e.prod_id = p.prod_id and p.current_stock < 10 "
						+"UNION " 
						+"select p.prod_id, p.prod_type, e.manufacturer ||' - ' || e.model, p.current_stock "
						+"from sound_dock s, product p, electronic e "
						+"where s.elec_id = e.elec_id and e.prod_id = p.prod_id and p.current_stock < 10 "
						+"UNION "
						+"select p.prod_id, p.prod_type, e.manufacturer ||' - ' || e.model, p.current_stock "
						+"from console c, product p, electronic e "
						+"where c.elec_id = e.elec_id and e.prod_id = p.prod_id and p.current_stock < 10 ";
		try
		{
			stmt = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rset = stmt.executeQuery();
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
		return rset;
		
	}
	public ResultSet returnTrans()
	{
		String query = "select Trans_id, Trans_date, Trans_type, total_Cost,e.f_name || ' ' || e.L_NAME as Employee "
					  +"from TRANSACTION t, Employee e "
					  +"where e.EMP_ID = t.EMP_ID " 
					  +"and TRANS_TYPE = 'R' "
					  +"and TRANS_DATE BETWEEN '"+ topDate +"' AND '"+ bottomDate +"'";
		
		//System.out.printf("%10",rset.getString(0));
		try
		{
			stmt = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rset = stmt.executeQuery();
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
		return rset;
	}
	
	public void setTopDate(String td)
	{
		topDate = td;
	}
	public void setBottomDate(String bd)
	{
		bottomDate = bd;
	}
	
	public void setDBconnection(Connection conn)
	{
		this.conn = conn;
	}
}
