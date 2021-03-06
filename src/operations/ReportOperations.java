package operations;
import java.sql.*;
import java.util.ArrayList;

public class ReportOperations
{
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rset;
	private String topDate;
	private String bottomDate;

	public ResultSet salesReportToFromDates()
	{
		String query = "select trans_id, TRANS_DATE,EMP_ID, sum(TOTAL_COST)from transaction WHERE TRANS_DATE BETWEEN '" + topDate + "' AND '" + bottomDate + "' AND TRANS_TYPE = 'S' group by trans_id,TRANS_DATE,EMP_ID order by trans_id";


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
		String query = "select trans_id, TRANS_DATE,EMP_ID, sum(TOTAL_COST)from transaction WHERE TRANS_DATE BETWEEN '" + topDate + "' AND '" + bottomDate + "' AND TRANS_TYPE = 'R' group by trans_id,TRANS_DATE,EMP_ID order by trans_id";


		
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

	/*
	 * Operations for the Quarterly Report Screen
	 */

	// Returns a list of all transaction years
	public ArrayList<String> getTransactionYears()
	{
		ArrayList<String> y = new ArrayList<String>();
		String query = "select UNIQUE(to_char(trans_date, 'YYYY')) as year from transaction";
		try
		{
			stmt = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rset = stmt.executeQuery();
			while (rset.next())
			{
				y.add(rset.getString("YEAR"));
			}
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
		return y;
	}

	public double getFirstQuarterRevenue(String year)
	{
		double total =0.0;
		String query = "select TRANS_ID,trans_date, total_cost FROM transaction "
				+"WHERE TRANS_TYPE = 'S' "
				+"AND to_char(trans_date, 'MON-YYYY') = 'JAN-"+year+"' "
				+"OR to_char(trans_date, 'MON-YYYY') = 'FEB-"+year+"' "
				+"OR to_char(trans_date, 'MON-YYYY') = 'MAR-"+year+"'";
		try
		{
			stmt = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rset = stmt.executeQuery();
			while (rset.next())
			{
				total +=(rset.getDouble("TOTAL_COST"));
			}
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
		return total;
	}
	public double getSecondQuarterRevenue(String year)
	{
		double total =0.0;
		String query = "select TRANS_ID,trans_date, total_cost FROM transaction "
				+"WHERE TRANS_TYPE = 'S' "
				+"AND to_char(trans_date, 'MON-YYYY') = 'APR-"+year+"' "
				+"OR to_char(trans_date, 'MON-YYYY') = 'MAY-"+year+"' "
				+"OR to_char(trans_date, 'MON-YYYY') = 'JUN-"+year+"'";
		try
		{
			stmt = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rset = stmt.executeQuery();
			while (rset.next())
			{
				total +=(rset.getDouble("TOTAL_COST"));
			}
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
		return total;
	}
	public double getThirdQuarterRevenue(String year)
	{
		double total =0.0;
		String query = "select TRANS_ID,trans_date, total_cost FROM transaction "
				+"WHERE TRANS_TYPE = 'S' "
				+"AND to_char(trans_date, 'MON-YYYY') = 'JUL-"+year+"' "
				+"OR to_char(trans_date, 'MON-YYYY') = 'AUG-"+year+"' "
				+"OR to_char(trans_date, 'MON-YYYY') = 'SEP-"+year+"'";
		try
		{
			stmt = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rset = stmt.executeQuery();
			while (rset.next())
			{
				total +=(rset.getDouble("TOTAL_COST"));
			}
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
		return total;
	}
	public double getFourthQuarterRevenue(String year)
	{
		double total =0.0;
		String query = "select TRANS_ID,trans_date, total_cost FROM transaction "
				+"WHERE TRANS_TYPE = 'S' "
				+"AND to_char(trans_date, 'MON-YYYY') = 'OCT-"+year+"' "
				+"OR to_char(trans_date, 'MON-YYYY') = 'NOV-"+year+"' "
				+"OR to_char(trans_date, 'MON-YYYY') = 'DEC-"+year+"'";
		try
		{
			stmt = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rset = stmt.executeQuery();
			while (rset.next())
			{
				total +=(rset.getDouble("TOTAL_COST"));
			}
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
		return total;
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
