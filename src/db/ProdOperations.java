package db;

import java.sql.*;


public class ProdOperations {

	private Statement stmt;
	private ResultSet rset;
	private Connection conn;
	private PreparedStatement pstmt;
	
	public Connection setDBconnection(Connection conn) {
		return this.conn = conn;
	}
	
	
	
	public ResultSet getCD() {
		try {
			String queryString = "SELECT p.current_stock, dp.genre, dp.age_rating, a.artist_name, c.album_name, c.record_company, c.album_length, c.cd_sale_price "
					+ "FROM PRODUCT p, DIGITAL_PRODUCT dp,  CD c, SONG s, ARTIST a, CD_ARTIST ca"
					+ " WHERE p.PROD_ID = dp.PROD_ID AND dp.DIG_ID = c.DIG_ID AND c.CD_ID = ca.CD_ID "
					+ "AND ca.ARTIST_ID = a.ARTIST_ID AND ca.CD_ID = s.CD_ID"
					;

			stmt = conn.createStatement();
			rset = stmt.executeQuery(queryString);

		     /* ResultSetMetaData rsmd = rset.getMetaData();
		      int numberOfColumns = rsmd.getColumnCount();
			
			while (rset.next()) {
		        for (int i = 1; i <= numberOfColumns; i++) {
		          if (i > 1) System.out.print(",  ");
		          String columnValue = rset.getString(i);
		          System.out.print(columnValue);
		        }
		        System.out.println("");  
		      }*/
		} catch (Exception e) {
			System.out.println(e);
		}
		return rset;
	}
	
	public ResultSet getProduct() {
		try {
			String cd = "CD";
			String queryString = "SELECT p.prod_id, p.prod_type, c.album_name, c.cd_cost_price, c.cd_sale_price, "
					+ "p.current_stock, dp.age_rating, dp.genre, c.record_company, c.album_length "
					+"FROM product p, digital_product dp, cd c, artist a, cd_artist ca "
					+"WHERE dp.prod_id = p.prod_id "
					+"AND dp.dig_id = c.dig_id "
					+"AND a.artist_id = ca.artist_id "
					+"AND c.cd_id = ca.cd_id "
					+ "AND prod_type = '"+cd+"'";

			stmt = conn.createStatement();
			rset = stmt.executeQuery(queryString);
		} catch (Exception e) {
			System.out.println(e);
		}
		return rset;
	}

	public ResultSet getSongs() {
		try {
			
			String sqlStatement = "select p.prod_id, s.song_name, s.song_length "+
					"from product p, digital_product dp, cd c, song s "+
					"where p.prod_id = dp.prod_id "+
					"and dp.dig_id = c.dig_id "+
					"and s.cd_id = c.cd_id ";
			
					stmt = conn.createStatement();
					rset = stmt.executeQuery(sqlStatement); 
					
					
//					  ResultSetMetaData rsmd = rset.getMetaData();
//				      int numberOfColumns = rsmd.getColumnCount();
//					
//					while (rset.next()) {
//				        for (int i = 1; i <= numberOfColumns; i++) {
//				          if (i > 1) System.out.print(",  ");
//				          String columnValue = rset.getString(i);
//				          System.out.print(columnValue);
//				        }
//				        System.out.println("");  
//				      }
		}catch (Exception e) {
			System.out.println(e);
		}
		return rset;
	}
	
	public ResultSet getLastRow() {
		String queryString = "SELECT * FROM PRODUCT p, DIGITAL_PRODUCT dp,  CD c, SONG s, ARTIST a, CD_ARTIST ca"
				+ " WHERE p.PROD_ID = dp.PROD_ID AND dp.DIG_ID = c.DIG_ID AND c.CD_ID = ca.CD_ID "
				+ "AND ca.ARTIST_ID = a.ARTIST_ID AND ca.CD_ID = s.CD_ID"
				;
		try {
			pstmt = conn.prepareStatement(queryString,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rset = pstmt.executeQuery();
			rset.last();
			
			
			
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}

		return rset;
	}

}
