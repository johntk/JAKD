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
	
	
	

	
	public ResultSet getProductCD() {
		try {
			
			String queryString = "SELECT p.prod_id, p.prod_type, c.album_name, c.cd_cost_price, c.cd_sale_price, "
					+ "p.current_stock, dp.age_rating, dp.genre, c.record_company, c.album_length "
					+"FROM product p, digital_product dp, cd c, artist a, cd_artist ca "
					+"WHERE dp.prod_id = p.prod_id "
					+"AND dp.dig_id = c.dig_id "
					+"AND a.artist_id = ca.artist_id "
					+"AND c.cd_id = ca.cd_id ";


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
		}catch (Exception e) {
			System.out.println(e);
		}
		return rset;
	}
	
	
	
	
	
	public ResultSet getProductDVD() {
		try {
			
			String sqlStatement = "SELECT p.prod_id, p.prod_type, d.dvd_name, d.dvd_sale_price, d.dvd_cost_price, "
					+ "p.current_stock, dp.age_rating, dp.genre, d.studio, d.dvd_length "
					+"from product p, digital_product dp, dvd d "
					+"where dp.prod_id = p.prod_id "
					+"and dp.dig_id = d.dig_id ";


			stmt = conn.createStatement();
			rset = stmt.executeQuery(sqlStatement);
		} catch (Exception e) {
			System.out.println(e);
		}
		return rset;
	}
	
	
	public ResultSet getProductGame() {
		try {
			
			String sqlStatement = "SELECT p.prod_id, p.prod_type, g.game_name, g.game_sale_price, g.game_cost_price, "
					+ "p.current_stock, dp.age_rating, dp.genre, g.company, g.platform "
					+"from product p, digital_product dp, game g "
					+"where dp.prod_id = p.prod_id "
					+"and dp.dig_id = g.dig_id ";


			stmt = conn.createStatement();
			rset = stmt.executeQuery(sqlStatement);
		} catch (Exception e) {
			System.out.println(e);
		}
		return rset;
	}
	
	
	
	public ResultSet getProductHeadphone() {
		try {
			
			String sqlStatement = "SELECT p.prod_id, p.prod_type, e.model,  hp.headphone_cost_price, hp.headphone_sale_price, "
					+ " p.current_stock, hp.microphone, hp.over_ear, hp.iphone_compatible, e.manufacturer,  e.colour    "
					+"from product p, electronic e, headphones hp "
					+"where e.prod_id = p.prod_id  "
					+"and e.elec_id = hp.elec_id ";

			
//			String sqlStatement = "select p.current_stock, e.manufacturer, e.model, e.colour, hp.over_ear, hp.microphone, hp.iphone_compatible, hp.headphone_sale_price "+
//					"from product p, electronic e, headphones hp "+
//					"where e.prod_id = p.prod_id "+
//					"and e.elec_id = hp.elec_id "+
			

			stmt = conn.createStatement();
			rset = stmt.executeQuery(sqlStatement);
		} catch (Exception e) {
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
