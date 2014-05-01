package operations;

import java.sql.*;

import model.CD;
import model.DigiProduct;
import model.ElecProduct;
import model.Employee;


public class ProdOperations {

	private Statement stmt;
	private ResultSet rset;
	private Connection conn;
	private PreparedStatement pstmt;
	
	public void setDBconnection(Connection conn) {
		this.conn = conn;
	}

	//Returns all CD products from the DB
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

	//Returns all songs from the DB
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
	
	//Returns all DVD products from the DB
	public ResultSet getProductDVD() {
		try {
			
			String sqlStatement = "SELECT p.prod_id, dp.dig_id, d.dvd_id, p.prod_type, d.dvd_name, d.dvd_sale_price, d.dvd_cost_price, "
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
	
	//Returns all Game products from the DB
	public ResultSet getProductGame() {
		try {
			
			String sqlStatement = "SELECT p.prod_id, dp.dig_id, g.game_id, p.prod_type, g.game_name, g.game_sale_price, g.game_cost_price, "
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
	
	
	//Returns all Headphone products from the DB
	public ResultSet getProductHeadphone() {
		try {
			
			String sqlStatement = "SELECT p.prod_id, e.elec_id, hp.headphone_id, p.prod_type, e.model,  hp.headphone_cost_price, hp.headphone_sale_price, "
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
	
	
	//Returns all Console products from the DB
	public ResultSet getProductConsole() {
		try {
			
			String sqlStatement = "SELECT p.prod_id, e.elec_id, console_id, p.prod_type, e.model,  c.console_cost_price, c.console_sale_price, "
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
	
	//Returns all Sounddock products from the DB
	public ResultSet getProductDock() {
		try {
			
			String sqlStatement = "SELECT p.prod_id, e.elec_id, sd_id, p.prod_type, e.model,  sd.sd_cost_price, sd.sd_sale_price, "
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
	

	//Returns the next Product ID, this is only for Display, the product is input into the DB using a sequence.
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
		//Removes the leading letter and Zeros on the product ID,
		int id = Integer.parseInt(nextVal.replaceAll("\\D", ""));
		//adds 1 onto the remaining value.
		id += 1;
		//keeps track of of the number of leading zeros,
		int zeros = idLength - String.valueOf(id).length();
		for(int i = 0; i < zeros; i++)
		{
			numZeros += "0";
		}
		//Returns the new ID by adding the leading letter and zeros onto the new ID value
		newID = ("P"+ numZeros + id);
		
		return newID;
	}
	

	//Updates the product associated with the passed in product
	public void updateProduct(DigiProduct p)
	{
		 
			try {
				if(p.getProd_type().equals("CD"))
				{
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
				
				
				String queryString4 = "UPDATE artist SET artist_name=? "
						+"WHERE artist_id ="+ "'" + p.getArtist_id() + "'";

				pstmt = conn.prepareStatement(queryString4);
				pstmt.setString(1, p.getArtist());
				pstmt.executeUpdate();
				
			updateAlbum(p);
				}
				else if(p.getProd_type().equals("DVD"))
				{
					String queryString1 = "UPDATE Product SET prod_id=?, prod_type=?, "
							+" current_stock=? "
							+"WHERE prod_id ="+ "'" + p.getProd_id() + "'";
					
					pstmt = conn.prepareStatement(queryString1);
					pstmt.setString(1, p.getProd_id() );
					pstmt.setString(2, p.getProd_type());
					pstmt.setInt(3, p.getCurrent_stock());
					pstmt.executeUpdate();
					
					
					String queryString2 = "UPDATE DVD SET  dvd_name=?, dvd_cost_price=?, dvd_sale_price=?, "
							+" studio=?, dvd_length=? "
							+"WHERE dvd_id ="+ "'" + p.getDvd_id() + "'";
					
					pstmt = conn.prepareStatement(queryString2);
					pstmt.setString(1, p.getDvd_name());
					pstmt.setDouble(2, p.getCostPrice());
					pstmt.setDouble(3, p.getSellPrice());
					pstmt.setString(4, p.getStudio());
					pstmt.setDouble(5, p.getLength());
					pstmt.executeUpdate();
					
					
					String queryString3 = "UPDATE digital_product SET age_rating=?, genre=? "
							+"WHERE dig_id ="+ "'" + p.getDigi_id() + "'";

					pstmt = conn.prepareStatement(queryString3);
					pstmt.setString(1, p.getAge_rating());
					pstmt.setString(2, p.getGenre());
					pstmt.executeUpdate();
				}
				else if(p.getProd_type().equals("GAME"))
				{
					String queryString1 = "UPDATE Product SET prod_id=?, prod_type=?, "
							+" current_stock=? "
							+"WHERE prod_id ="+ "'" + p.getProd_id() + "'";
					
					pstmt = conn.prepareStatement(queryString1);
					pstmt.setString(1, p.getProd_id() );
					pstmt.setString(2, p.getProd_type());
					pstmt.setInt(3, p.getCurrent_stock());
					pstmt.executeUpdate();
					
					
					String queryString2 = "UPDATE GAME SET  game_name=?, game_cost_price=?, game_sale_price=?, "
							+" company=?, platform=? "
							+"WHERE game_id ="+ "'" + p.getGame_id() + "'";
				
					
					pstmt = conn.prepareStatement(queryString2);
					pstmt.setString(1, p.getGame_name());
					pstmt.setDouble(2, p.getCostPrice());
					pstmt.setDouble(3, p.getSellPrice());
					pstmt.setString(4, p.getStudio());
					pstmt.setString(5, p.getPlatform());
					pstmt.executeUpdate();
					
					
					String queryString3 = "UPDATE digital_product SET age_rating=?, genre=? "
							+"WHERE dig_id ="+ "'" + p.getDigi_id() + "'";

					
					pstmt = conn.prepareStatement(queryString3);
					pstmt.setString(1, p.getAge_rating());
					pstmt.setString(2, p.getGenre());
					pstmt.executeUpdate();
				}
				}catch (Exception ex) 
					{
					System.out.println(ex);
			}
		}
	//Updates the product associated with the passed in product
	public void updateProduct(ElecProduct p)
	{
		 
			try {
				if(p.getProd_type().equals("HEADPHONES"))
				{

				String queryString1 = "UPDATE Product SET prod_id=?, prod_type=?, "
						+" current_stock=? "
						+"WHERE prod_id ="+ "'" + p.getProd_id() + "'";
				
				pstmt = conn.prepareStatement(queryString1);
				pstmt.setString(1, p.getProd_id() );
				pstmt.setString(2, p.getProd_type());
				pstmt.setInt(3, p.getCurrent_stock());
				pstmt.executeUpdate();
				
				//I'm an easter egg, you win life!
				String queryString2 = "UPDATE Headphones SET over_ear=?, headphone_cost_price=?, headphone_sale_price=?, "
						+" iphone_compatible=?, microphone=? "
						+"WHERE headphone_id ="+ "'" + p.getHeadphone_id() + "'";

				pstmt = conn.prepareStatement(queryString2);
				pstmt.setString(1, p.getOverEar());
				pstmt.setDouble(2, p.getCostPrice());
				pstmt.setDouble(3, p.getSellPrice());
				pstmt.setString(4, p.getIphoneComp());
				pstmt.setString(5, p.getMic());
				pstmt.executeUpdate();
				
				
				String queryString3 = "UPDATE electronic SET manufacturer=?, model=?, colour=? "
						+"WHERE elec_id ="+ "'" + p.getElec_id() + "'";

				pstmt = conn.prepareStatement(queryString3);
				pstmt.setString(1, p.getManufacturer());
				pstmt.setString(2, p.getModel());
				pstmt.setString(3, p.getColour());
				pstmt.executeUpdate();
				
				
			
				}
				else if(p.getProd_type().equals("CONSOLE"))
				{
					String queryString1 = "UPDATE Product SET prod_id=?, prod_type=?, "
							+" current_stock=? "
							+"WHERE prod_id ="+ "'" + p.getProd_id() + "'";
					
					pstmt = conn.prepareStatement(queryString1);
					pstmt.setString(1, p.getProd_id() );
					pstmt.setString(2, p.getProd_type());
					pstmt.setInt(3, p.getCurrent_stock());
					pstmt.executeUpdate();
					
					
					String queryString2 = "UPDATE Console SET storage_size=?, console_cost_price=?, console_sale_price=?, "
							+" wifi=?, num_controllers=? "
							+"WHERE console_id ="+ "'" + p.getConsole_id() + "'";

					pstmt = conn.prepareStatement(queryString2);
					pstmt.setInt(1, p.getStorageSize());
					pstmt.setDouble(2, p.getCostPrice());
					pstmt.setDouble(3, p.getSellPrice());
					pstmt.setString(4, p.getWifi());
					pstmt.setInt(5, p.getNumPad());
					pstmt.executeUpdate();
					
					
					String queryString3 = "UPDATE electronic SET manufacturer=?, model=?, colour=? "
							+"WHERE elec_id ="+ "'" + p.getElec_id() + "'";

					pstmt = conn.prepareStatement(queryString3);
					pstmt.setString(1, p.getManufacturer());
					pstmt.setString(2, p.getModel());
					pstmt.setString(3, p.getColour());
					pstmt.executeUpdate();
				}
				else if(p.getProd_type().equals("SOUNDDOCK"))
				{
					String queryString1 = "UPDATE Product SET prod_id=?, prod_type=?, "
							+" current_stock=? "
							+"WHERE prod_id ="+ "'" + p.getProd_id() + "'";
					
					pstmt = conn.prepareStatement(queryString1);
					pstmt.setString(1, p.getProd_id() );
					pstmt.setString(2, p.getProd_type());
					pstmt.setInt(3, p.getCurrent_stock());
					pstmt.executeUpdate();
					
					
					String queryString2 = "UPDATE Sound_Dock SET  wireless=?, sd_cost_price=?, sd_sale_price=?, "
							+" POWER_OUPUT=?, digital_radio=? "
							+"WHERE sd_id ="+ "'" + p.getSd_id() + "'";
				
					pstmt = conn.prepareStatement(queryString2);
					pstmt.setString(1, p.getWireless());
					pstmt.setDouble(2, p.getCostPrice());
					pstmt.setDouble(3, p.getSellPrice());
					pstmt.setInt(4, p.getPwrOut());
					pstmt.setString(5, p.getDigiRadio());
					pstmt.executeUpdate();
					
					
					String queryString3 = "UPDATE electronic SET manufacturer=?, model=?, colour=? "
							+"WHERE elec_id ="+ "'" + p.getElec_id() + "'";

					pstmt = conn.prepareStatement(queryString3);
					pstmt.setString(1, p.getManufacturer());
					pstmt.setString(2, p.getModel());
					pstmt.setString(3, p.getColour());
					pstmt.executeUpdate();
				}
				}catch (Exception ex) 
					{
					System.out.println(ex);
			}
		}
	
	//Updates the song associated with the passed in product
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
	
	//Adds the passed in product to the DB
	public void addNewProd(DigiProduct p) {
		try {
			
			if(p.getProd_type().equals("CD"))
			{
			
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
			}
			else if(p.getProd_type().equals("DVD"))
			{
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
				
				
				String queryString2 = "INSERT INTO DVD (dvd_id, dvd_name, dvd_cost_price, dvd_sale_price, "
						+" studio, dvd_length, dig_id) "
						+ "VALUES ('D'||to_char(dvd_seq.nextVal,'FM0000000'),?,?,?,?,?,'D'||to_char(digi_seq.currVal,'FM0000000')) ";
				
				pstmt = conn.prepareStatement(queryString2);
				pstmt.setString(1, p.getDvd_name());
				pstmt.setDouble(2, p.getCostPrice());
				pstmt.setDouble(3, p.getSellPrice());
				pstmt.setString(4, p.getStudio());
				pstmt.setDouble(5, p.getLength());
				pstmt.executeUpdate();
			}
			else if(p.getProd_type().equals("GAME"))
			{
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
				
				
				String queryString2 = "INSERT INTO GAME (game_id, game_name, game_cost_price, game_sale_price, "
						+" company, platform, dig_id) "
						+ "VALUES ('G'||to_char(game_seq.nextVal,'FM0000000'),?,?,?,?,?,'D'||to_char(digi_seq.currVal,'FM0000000')) ";
				
				pstmt = conn.prepareStatement(queryString2);
				pstmt.setString(1, p.getGame_name());
				pstmt.setDouble(2, p.getCostPrice());
				pstmt.setDouble(3, p.getSellPrice());
				pstmt.setString(4, p.getStudio());
				pstmt.setString(5, p.getPlatform());
				pstmt.executeUpdate();
			}
			
		} catch (Exception se) {
			System.out.println(se);
		}
	}
	//Adds the passed in product to the DB
	public void addNewProd(ElecProduct p) {
		try {
			
			if(p.getProd_type().equals("HEADPHONES"))
			{
			
				String queryString1 = "INSERT INTO Product (prod_id, prod_type, current_stock) "
						+ "VALUES ('P'||to_char(prod_seq.nextVal,'FM0000000'),?,?) ";
				
				pstmt = conn.prepareStatement(queryString1);
				
				pstmt.setString(1, p.getProd_type());
				pstmt.setInt(2, p.getCurrent_stock());
				pstmt.executeUpdate();
				
				
				String queryString3 = "INSERT INTO electronic (elec_id, manufacturer, model, colour, prod_id) "
						+ "VALUES ('E'||to_char(elec_seq.nextVal,'FM0000000'),?,?,?,'P'||to_char(prod_seq.currVal,'FM0000000')) ";
	
				pstmt = conn.prepareStatement(queryString3);
				pstmt.setString(1, p.getManufacturer());
				pstmt.setString(2, p.getModel());
				pstmt.setString(3, p.getColour());
				pstmt.executeUpdate();
				
				
				String queryString2 = "INSERT INTO HEADPHONES (HEADPHONE_id, over_ear, headphone_cost_price, headphone_sale_price, "
						+" microphone, iphone_compatible, elec_id) "
						+ "VALUES ('H'||to_char(HEADPHONES_seq.nextVal,'FM0000000'),?,?,?,?,?,'E'||to_char(elec_seq.currVal,'FM0000000')) ";
				
				pstmt = conn.prepareStatement(queryString2);
				pstmt.setString(1, p.getOverEar());
				pstmt.setDouble(2, p.getCostPrice());
				pstmt.setDouble(3, p.getSellPrice());
				pstmt.setString(4, p.getMic());
				pstmt.setString(5, p.getIphoneComp());
				pstmt.executeUpdate();
			
			
			
			}
			else if(p.getProd_type().equals("CONSOLE"))
			{
				String queryString1 = "INSERT INTO Product (prod_id, prod_type, current_stock) "
						+ "VALUES ('P'||to_char(prod_seq.nextVal,'FM0000000'),?,?) ";
				
				pstmt = conn.prepareStatement(queryString1);
				
				pstmt.setString(1, p.getProd_type());
				pstmt.setInt(2, p.getCurrent_stock());
				pstmt.executeUpdate();
				
				
				String queryString3 = "INSERT INTO electronic (elec_id, manufacturer, model, colour, prod_id) "
						+ "VALUES ('E'||to_char(elec_seq.nextVal,'FM0000000'),?,?,?,'P'||to_char(prod_seq.currVal,'FM0000000')) ";

				pstmt = conn.prepareStatement(queryString3);
				pstmt.setString(1, p.getManufacturer());
				pstmt.setString(2, p.getModel());
				pstmt.setString(3, p.getColour());
				pstmt.executeUpdate();
				
				
				String queryString2 = "INSERT INTO CONSOLE (CONSOLE_id, storage_size, CONSOLE_cost_price, CONSOLE_sale_price, "
						+" wifi, num_controllers, elec_id) "
						+ "VALUES ('L'||to_char(CONSOLE_seq.nextVal,'FM0000000'),?,?,?,?,?,'E'||to_char(elec_seq.currVal,'FM0000000')) ";
				
				pstmt = conn.prepareStatement(queryString2);
				pstmt.setString(1, p.getOverEar());
				pstmt.setDouble(2, p.getCostPrice());
				pstmt.setDouble(3, p.getSellPrice());
				pstmt.setString(4, p.getMic());
				pstmt.setString(5, p.getIphoneComp());
				pstmt.executeUpdate();
				
				
			}
			else if(p.getProd_type().equals("SOUNDDOCK"))
			{
				String queryString1 = "INSERT INTO Product (prod_id, prod_type, current_stock) "
						+ "VALUES ('P'||to_char(prod_seq.nextVal,'FM0000000'),?,?) ";
				
				pstmt = conn.prepareStatement(queryString1);
				
				pstmt.setString(1, p.getProd_type());
				pstmt.setInt(2, p.getCurrent_stock());
				pstmt.executeUpdate();
				
				
				String queryString3 = "INSERT INTO electronic (elec_id, manufacturer, model, colour, prod_id) "
						+ "VALUES ('E'||to_char(elec_seq.nextVal,'FM0000000'),?,?,?,'P'||to_char(prod_seq.currVal,'FM0000000')) ";

				pstmt = conn.prepareStatement(queryString3);
				pstmt.setString(1, p.getManufacturer());
				pstmt.setString(2, p.getModel());
				pstmt.setString(3, p.getColour());
				pstmt.executeUpdate();
				
				
				String queryString2 = "INSERT INTO SOUND_DOCK (sd_id, WIRELESS, sd_cost_price, sd_SALE_PRICE, "
						+" POWER_OUPUT, DIGITAL_RADIO, elec_id) "
						+ "VALUES ('S'||to_char(SOUND_DOCK_seq.nextVal,'FM0000000'),?,?,?,?,?,'E'||to_char(elec_seq.currVal,'FM0000000')) ";
				
				pstmt = conn.prepareStatement(queryString2);
				pstmt.setString(1, p.getWireless());
				pstmt.setDouble(2, p.getCostPrice());
				pstmt.setDouble(3, p.getSellPrice());
				pstmt.setInt(4, p.getPwrOut());
				pstmt.setString(5, p.getDigiRadio());
				pstmt.executeUpdate();
				
				
			}
			
		} catch (Exception se) {
			System.out.println(se);
		}
	}
	//Adds the passed in product to the DB
	public void addAlbum(DigiProduct p)
	{
		
		for(int i =0; i < p.getAlbum().getSongList().size(); i++)
		{
			
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
	
	//deletes the product associated with passed in ID
	public int deleteProd(String id) {
		int no = 0;
		try {
			String cmd = "DELETE FROM Product WHERE prod_id =" + "'" + id + "'";
			stmt = conn.createStatement();
			no = stmt.executeUpdate(cmd);
		} catch (Exception e) {
			System.out.println(e);
		}
		return no;

	}
	
	//Gets the last inserted product in the DB
	public ResultSet getLastRow() {
		String queryString = "SELECT* "
				+"FROM product "
				+"ORDER BY prod_id";
		
		
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
	
	//Gets the last inserted CD in the DB
	public ResultSet getLastRowCD() {
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
			
		} catch (Exception ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}

		return rset;
	}
	//Gets the last inserted DVD in the DB
	public ResultSet getLastRowDVD() {
		String queryString = "SELECT p.prod_id, dp.dig_id, d.dvd_id, p.prod_type, d.dvd_name, d.dvd_sale_price, d.dvd_cost_price, "
				+ "p.current_stock, dp.age_rating, dp.genre, d.studio, d.dvd_length "
				+"from product p, digital_product dp, dvd d "
				+"where dp.prod_id = p.prod_id "
				+"and dp.dig_id = d.dig_id ";
		
		
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
	//Gets the last inserted Game in the DB
	public ResultSet getLastRowGAME() {
		String queryString = "SELECT p.prod_id, dp.dig_id, g.game_id, p.prod_type, g.game_name, g.game_sale_price, g.game_cost_price, "
				+ "p.current_stock, dp.age_rating, dp.genre, g.company, g.platform "
				+"from product p, digital_product dp, game g "
				+"where dp.prod_id = p.prod_id "
				+"and dp.dig_id = g.dig_id ";
		
		
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
	//Gets the last inserted songs in the DB, associated with the passed in ID
	public ResultSet getLastRowAlbum(String id)
	{
		
		try {
			
			String queryString = "select p.prod_id, s.song_id, s.song_name, s.song_length "
					+"from product p, digital_product dp, cd c, song s "
					+"where p.prod_id = dp.prod_id "
					+"and dp.dig_id = c.dig_id "
					+"and s.cd_id = c.cd_id "
					+"AND p.prod_id = "+ "'" + id + "'";
			

			pstmt = conn.prepareStatement(queryString,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
					rset = pstmt.executeQuery(); 

		}catch (Exception e) {
			System.out.println(e);
		}
		return rset;
		
		
	}
	//Gets the last inserted Headphone in the DB
	public ResultSet getLastRowHeadphone() {
		String queryString = "SELECT p.prod_id, e.elec_id, hp.headphone_id, p.prod_type, e.model,  hp.headphone_cost_price, hp.headphone_sale_price, "
				+ " p.current_stock, hp.microphone, hp.over_ear, hp.iphone_compatible, e.manufacturer,  e.colour "
				+"from product p, electronic e, headphones hp "
				+"where e.prod_id = p.prod_id  "
				+"and e.elec_id = hp.elec_id ";
		
		
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
	//Gets the last inserted Console in the DB
		public ResultSet getLastRowConsole() {
			String queryString = "SELECT p.prod_id, e.elec_id, console_id, p.prod_type, e.model,  c.console_cost_price, c.console_sale_price, "
					+ " p.current_stock, c.storage_size, c.wifi, c.num_controllers, e.manufacturer,  e.colour "
					+"from product p, electronic e, console c "
					+"where e.prod_id = p.prod_id  "
					+"and e.elec_id = c.elec_id ";
			
			
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
		//Gets the last inserted Sounddock in the DB
		public ResultSet getLastRowSounddock() {
			String queryString = "SELECT p.prod_id, e.elec_id, sd_id, p.prod_type, e.model,  sd.sd_cost_price, sd.sd_sale_price, "
					+ " p.current_stock, sd.digital_radio, sd.wireless, sd.power_ouput,  e.manufacturer,  e.colour "
					+"from product p, electronic e, sound_dock sd "
					+"where e.prod_id = p.prod_id  "
					+"and e.elec_id = sd.elec_id ";
			
			
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
		
		//close ResultsSets, Prepared Statements and Statements
		public void closeReultSets()
		{
			try
			{
			if (rset != null) rset.close();
			if (pstmt != null) pstmt.close();
			if (stmt != null) stmt.close();
//			if (conn != null) conn.close();
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	
}
