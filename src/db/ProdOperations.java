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
			String queryString = "SELECT * FROM PRODUCT p, DIGITAL_PRODUCT dp,  CD c, SONG s, ARTIST a, CD_ARTIST ca"
					+ "WHERE p.PROD_ID = dp.PROD_ID AND dp.DIG_ID = c.DIG_ID AND c.CD_ID = ca.CD_ID "
					+ "AND ca.ARTIST_ID = a.ARTIST_ID AND ca.CD_ID = s.CD_ID"
					+ "GROUP BY PROD_ID ";

			stmt = conn.createStatement();
			rset = stmt.executeQuery(queryString);
			
			 
		} catch (Exception e) {
			System.out.println(e);
		}
		return rset;
	}
	
	
	public ResultSet getLastRow() {
		String queryString = "SELECT * FROM PRODUCT p, DIGITAL_PRODUCT dp,  CD c, SONG s, ARTIST a, CD_ARTIST ca"
				+ "WHERE p.PROD_ID = dp.PROD_ID AND dp.DIG_ID = c.DIG_ID AND c.CD_ID = ca.CD_ID "
				+ "AND ca.ARTIST_ID = a.ARTIST_ID AND ca.CD_ID = s.CD_ID"
				+ "ORDER BY PROD_ID ";
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
