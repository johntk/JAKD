package db;

import java.sql.*;

import model.CD;
import model.DigiProduct;
import model.Employee;


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
			
			String queryString = "SELECT p.prod_id, dp.dig_id, c.cd_id, a.artist_id, p.prod_type, c.album_name, "
					+ "c.cd_cost_price, c.cd_sale_price, "
					+ "p.current_stock, dp.age_rating, dp.genre, c.record_company, c.album_length, a.artist_name "
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
			
			String sqlStatement = "select p.prod_id, s.song_id, s.song_name, s.song_length "+
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

			stmt = conn.createStatement();
			rset = stmt.executeQuery(sqlStatement);
		} catch (Exception e) {
			System.out.println(e);
		}
		return rset;
	}
	
	
	
	public ResultSet getProductConsole() {
		try {
			
			String sqlStatement = "SELECT p.prod_id, p.prod_type, e.model,  c.console_cost_price, c.console_sale_price, "
					+ " p.current_stock, c.storage_size, c.wifi, c.num_controllers, e.manufacturer,  e.colour    "
					+"from product p, electronic e, console c "
					+"where e.prod_id = p.prod_id  "
					+"and e.elec_id = c.elec_id ";

			stmt = conn.createStatement();
			rset = stmt.executeQuery(sqlStatement);
		} catch (Exception e) {
			System.out.println(e);
		}
		return rset;
	}
	
	
	public ResultSet getProductDock() {
		try {
			
			String sqlStatement = "SELECT p.prod_id, p.prod_type, e.model,  sd.sd_cost_price, sd.sd_sale_price, "
					+ " p.current_stock, sd.digital_radio, sd.wireless, sd.power_ouput,  e.manufacturer,  e.colour    "
					+"from product p, electronic e, sound_dock sd "
					+"where e.prod_id = p.prod_id  "
					+"and e.elec_id = sd.elec_id ";

			stmt = conn.createStatement();
			rset = stmt.executeQuery(sqlStatement);
		} catch (Exception e) {
			System.out.println(e);
		}
		return rset;
	}
	
	
	
	
	
	

	public String getId() {
		String nextVal = "";
		try {
			String seq_val = "Select prod_id from Product ORDER BY prod_id";
			pstmt = conn.prepareStatement(seq_val,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rset = pstmt.executeQuery();

			rset.last();
			nextVal = rset.getString(1);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		String newID = "";
		String numZeros ="" ;
		int idLength = nextVal.length() - 1;
		int id = Integer.parseInt(nextVal.replaceAll("\\D", ""));
		id += 1;
		int zeros = idLength - String.valueOf(id).length();
		for(int i = 0; i < zeros; i++)
		{
			numZeros += "0";
		}
		newID = ("P"+ numZeros + id);
		
		return newID;
	}
	

	public void updateProdCD(DigiProduct p)
	{
		 
			try {
//				System.out.println(p.getAlbumName());
				String queryString1 = "UPDATE Product SET prod_id=?, prod_type=?, "
						+" current_stock=? "
						+"WHERE prod_id ="+ "'" + p.getProd_id() + "'";
				
				pstmt = conn.prepareStatement(queryString1);
				pstmt.setString(1, p.getProd_id() );
				pstmt.setString(2, p.getProd_type());
				pstmt.setInt(3, p.getCurrent_stock());
				pstmt.executeUpdate();
				
				
				String queryString2 = "UPDATE CD SET  album_name=?, cd_cost_price=?, cd_sale_price=?, "
						+" record_company=?, album_length=? "
						+"WHERE cd_id ="+ "'" + p.getCd_id() + "'";
				
				pstmt = conn.prepareStatement(queryString2);
				pstmt.setString(1, p.getAlbumName());
				pstmt.setDouble(2, p.getCostPrice());
				pstmt.setDouble(3, p.getSellPrice());
				pstmt.setString(4, p.getPublisher());
				pstmt.setDouble(5, p.getLength());
				pstmt.executeUpdate();
				
				
				String queryString3 = "UPDATE digital_product SET age_rating=?, genre=? "
						+"WHERE dig_id ="+ "'" + p.getDigi_id() + "'";

				pstmt = conn.prepareStatement(queryString3);
				pstmt.setString(1, p.getAge_rating());
				pstmt.setString(2, p.getGenre());
				pstmt.executeUpdate();
				
				
			updateAlbum(p);
				
				}catch (Exception ex) 
					{
					System.out.println(ex);
			}
		}
	
	public void updateAlbum(DigiProduct p)
	{
		
		for(int i =0; i < p.getAlbum().getSongList().size(); i++)
		{
		String queryString = "UPDATE Song SET song_length=?, song_name=? WHERE song_id ="+ "'" 
								+ p.getAlbum().getSongList().get(i).getProd_id() + "'";
		
		try
		{
		pstmt = conn.prepareStatement(queryString);

			pstmt.setString(1, p.getAlbum().getSongList().get(i).getSong_length());
			pstmt.setString(2, p.getAlbum().getSongList().get(i).getSong_name());
			pstmt.executeUpdate();
		}
		catch(Exception ex)
		{
			System.out.println("Problemsss" + p);
		}
		
		}
		
	}
	
	public void addEmployee(DigiProduct p) {
		try {
			
			System.out.println(p.getProd_type());
			
			String queryString1 = "INSERT INTO Product (prod_id, prod_type, current_stock) "
					+ "VALUES ('P'||to_char(prod_seq.nextVal,'FM0000000'),?,?) ";
			
			pstmt = conn.prepareStatement(queryString1);
			
			pstmt.setString(1, p.getProd_type());
			pstmt.setInt(2, p.getCurrent_stock());
			pstmt.executeUpdate();
			
			
			String queryString3 = "INSERT INTO digital_product (dig_id, age_rating, genre, prod_id) "
					+ "VALUES ('D'||to_char(digi_seq.nextVal,'FM0000000'),?,?,'P'||to_char(prod_seq.currVal,'FM0000000')) ";

			pstmt = conn.prepareStatement(queryString3);
			pstmt.setString(1, p.getAge_rating());
			pstmt.setString(2, p.getGenre());
			pstmt.executeUpdate();
			
			
			String queryString2 = "INSERT INTO CD (cd_id, album_name, cd_cost_price, cd_sale_price, "
					+" record_company, album_length, dig_id) "
					+ "VALUES ('C'||to_char(cd_seq.nextVal,'FM0000000'),?,?,?,?,?,'D'||to_char(digi_seq.currVal,'FM0000000')) ";
			
			pstmt = conn.prepareStatement(queryString2);
			pstmt.setString(1, p.getAlbumName());
			pstmt.setDouble(2, p.getCostPrice());
			pstmt.setDouble(3, p.getSellPrice());
			pstmt.setString(4, p.getPublisher());
			pstmt.setDouble(5, p.getLength());
			pstmt.executeUpdate();
			
			
			String queryString4 = "INSERT INTO Artist (artist_id, artist_name) "
					+ "VALUES ('A'||to_char(artist_seq.nextVal,'FM0000000'),?) ";
			
			pstmt = conn.prepareStatement(queryString4);
			pstmt.setString(1, p.getArtist());
			pstmt.executeUpdate();
			
			
			String queryString5 = "INSERT INTO cd_artist (cd_id, artist_id) "
					+ "VALUES ('C'||to_char(cd_seq.currVal,'FM0000000'),'A'||to_char(artist_seq.currVal,'FM0000000')) ";
			
			pstmt = conn.prepareStatement(queryString5);
			pstmt.executeUpdate();
			
			 addAlbum(p);
			
		} catch (Exception se) {
			System.out.println(se);
		}
	}
	
	
	public void addAlbum(DigiProduct p)
	{
		
		
		for(int i =0; i < p.getAlbum().getSongList().size(); i++)
		{
			System.out.println(p.getAlbum().getSongList().get(i).getSong_name());
			
			String queryString = "INSERT INTO Song (song_id, cd_id, song_length, song_name) "
					+ "VALUES ('S'||to_char(song_seq.nextVal,'FM0000000'),'C'||to_char(cd_seq.currVal,'FM0000000'),?,?) ";
		
		try
		{
		pstmt = conn.prepareStatement(queryString);

			pstmt.setString(1, p.getAlbum().getSongList().get(i).getSong_length());
			pstmt.setString(2, p.getAlbum().getSongList().get(i).getSong_name());
			pstmt.executeUpdate();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		
		}
		
	}
	
	public int deleteProd(String n) {
		int no = 0;
		try {
			String cmd = "DELETE FROM Product WHERE prod_id =" + "'" + n + "'";
			stmt = conn.createStatement();
			no = stmt.executeUpdate(cmd);
		} catch (Exception e) {
			System.out.println(e);
		}
		return no;

	}
	
	public ResultSet getLastRow() {
		String queryString = "SELECT p.prod_id, dp.dig_id, c.cd_id, a.artist_id, p.prod_type, c.album_name, "
				+ "c.cd_cost_price, c.cd_sale_price, "
				+ "p.current_stock, dp.age_rating, dp.genre, c.record_company, c.album_length, a.artist_name "
				+"FROM product p, digital_product dp, cd c, artist a, cd_artist ca "
				+"WHERE dp.prod_id = p.prod_id "
				+"AND dp.dig_id = c.dig_id "
				+"AND a.artist_id = ca.artist_id "
				+"AND c.cd_id = ca.cd_id "
				+"ORDER BY p.prod_id";
		
		
		try {
			pstmt = conn.prepareStatement(queryString,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rset = pstmt.executeQuery();
			rset.last();
			
		
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
	
			
			
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}

		return rset;
	}
	
	public ResultSet getLastRowAlbum(String id)
	{
		
		try {
			
			String sqlStatement = "select p.prod_id, s.song_id, s.song_name, s.song_length "
					+"from product p, digital_product dp, cd c, song s "
					+"where p.prod_id = dp.prod_id "
					+"and dp.dig_id = c.dig_id "
					+"and s.cd_id = c.cd_id "
					+"AND p.prod_id = "+ "'" + id + "'";
			
					stmt = conn.createStatement();
					rset = stmt.executeQuery(sqlStatement); 
		}catch (Exception e) {
			System.out.println(e);
		}
		return rset;
		
		
	}
}
