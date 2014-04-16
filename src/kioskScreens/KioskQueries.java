package kioskScreens;

import java.sql.*;
import java.util.ArrayList;

import db.DBconnection;

public class KioskQueries
{
	private Connection conn;
	private Statement stmt;
	private ResultSet rset;
	
	public void queryAllProducts(String sTerm,KioskResultsScreen krs)
	{
		
		String description;
		String productThumb;
		String prodID;
		double salePrice;
		int y=0;
		try {
			stmt = conn.createStatement();
			String sqlStatement = "select d.dvd_name as description, d.dvd_sale_price as salePrice, p.prod_id as prodID from dvd d, product p, digital_product dp where UPPER(dvd_name) like UPPER('%"+sTerm+"%') and p.prod_id = dp.prod_id and d.dig_id = dp.dig_id "+
					"UNION select g.game_name, g.game_sale_price, p.prod_id as prodID from game g, product p, digital_product dp where UPPER(game_name) like UPPER('%"+sTerm+"%') and p.prod_id = dp.prod_id and g.dig_id = dp.dig_id "+
					"UNION select a.artist_name||' - '||c.album_name, c.cd_sale_price, p.prod_id as prodID from cd c, artist a, cd_artist ca, digital_product d, product p where c.cd_id = ca.cd_id and a.artist_id = ca.artist_id and c.dig_id = d.dig_id and p.prod_id = d.prod_id and UPPER(a.artist_name||' '||c.album_name) like UPPER('%"+sTerm+"%') "+
					"UNION select e.manufacturer||' '||e.model, h.headphone_sale_price, p.prod_id as prodID from electronic e, headphones h, product p where e.elec_id = h.elec_id and p.prod_id = e.prod_id and UPPER(e.manufacturer||' '||e.model) like UPPER('%"+sTerm+"%') "+
					"UNION select e.manufacturer||' '||e.model, s.sd_sale_price, p.prod_id as prodID from electronic e, sound_dock s, product p where e.elec_id = s.elec_id and p.prod_id = e.prod_id and UPPER(e.manufacturer||' '||e.model) like UPPER('%"+sTerm+"%') "+
					"UNION select e.manufacturer||' '||e.model, c.console_sale_price, p.prod_id as prodID from electronic e, console c, product p where e.elec_id = c.elec_id and p.prod_id = e.prod_id and UPPER(e.manufacturer||' '||e.model) like UPPER('%"+sTerm+"%') ";
			rset = stmt.executeQuery(sqlStatement);
			while (rset.next())
			{
				description = rset.getString("description");
				salePrice = rset.getDouble("saleprice");
				productThumb = rset.getString("description")+".jpg";
				prodID = rset.getString("prodID");
				krs.addResult(productThumb, description, y, salePrice, prodID);
				y++;
			}
		} catch (Exception ex)
		{
			System.out.println("ERROR: " + ex.getMessage());
		}
	}

	public void queryAllCategories(String productType,KioskResultsScreen krs)
	{
		
		String description;
		String productThumb;
		String prodID;
		double salePrice;
		int y=0;
		try {
			stmt = conn.createStatement();
			String sqlStatement = "select d.dvd_name as description, d.dvd_sale_price as salePrice, p.prod_id as prodID, p.prod_type from dvd d, product p, digital_product dp where UPPER(prod_type) like UPPER('%"+productType+"%') and p.prod_id = dp.prod_id and d.dig_id = dp.dig_id "+
					"UNION select g.game_name, g.game_sale_price, p.prod_id as prodID, p.prod_type from game g, product p, digital_product dp where UPPER(prod_type) like UPPER('%"+productType+"%') and p.prod_id = dp.prod_id and g.dig_id = dp.dig_id "+
					"UNION select a.artist_name||' - '||c.album_name, c.cd_sale_price, p.prod_id as prodID, p.prod_type from cd c, artist a, cd_artist ca, digital_product d, product p where c.cd_id = ca.cd_id and a.artist_id = ca.artist_id and c.dig_id = d.dig_id and p.prod_id = d.prod_id and UPPER(prod_type) like UPPER('%"+productType+"%') "+
					"UNION select e.manufacturer||' '||e.model, h.headphone_sale_price, p.prod_id as prodID, p.prod_type from electronic e, headphones h, product p where e.elec_id = h.elec_id and p.prod_id = e.prod_id and UPPER(prod_type) like UPPER('%"+productType+"%') "+
					"UNION select e.manufacturer||' '||e.model, s.sd_sale_price, p.prod_id as prodID, p.prod_type from electronic e, sound_dock s, product p where e.elec_id = s.elec_id and p.prod_id = e.prod_id and UPPER(prod_type) like UPPER('%"+productType+"%') "+
					"UNION select e.manufacturer||' '||e.model, c.console_sale_price, p.prod_id as prodID, p.prod_type from electronic e, console c, product p where e.elec_id = c.elec_id and p.prod_id = e.prod_id and UPPER(prod_type) like UPPER('%"+productType+"%') ";
			rset = stmt.executeQuery(sqlStatement);
			while (rset.next())
			{
				description = rset.getString("description");
				salePrice = rset.getDouble("saleprice");
				productThumb = rset.getString("description")+".jpg";
				prodID = rset.getString("prodID");
				krs.addResult(productThumb, description, y, salePrice, prodID);
				y++;
			}
		} catch (Exception ex)
		{
			System.out.println("ERROR: " + ex.getMessage());
		}
	}

	public ArrayList queryPlatform()
	{
		ArrayList<String> consoleList = new ArrayList<String>();
		String description;
		try {
			stmt = conn.createStatement();
			String sqlStatement = "select e.manufacturer||' '||e.model as description from product p, electronic e, console c where p.prod_id = e.prod_id and c.elec_id = e.elec_id";
			rset = stmt.executeQuery(sqlStatement);
			while (rset.next())
			{
				description = rset.getString("description");
				consoleList.add(description);
			}
		} catch (Exception ex)
		{
			System.out.println("ERROR: " + ex.getMessage());
		}
		return consoleList;
	}

	public void queryGames(String platform,KioskResultsScreen krs)
	{
		String description;
		double salePrice;
		String prodID;
		int y=0;
		try {
			stmt = conn.createStatement();
			String sqlStatement = "select g.game_name as description, g.game_sale_price, p.prod_id as prodID from product p, digital_product d, game g where p.prod_id = d.prod_id and g.dig_id = d.dig_id and g.platform like '%"+platform+"%'";
			rset = stmt.executeQuery(sqlStatement);
			while (rset.next())
			{
				description = rset.getString("description");
				salePrice = rset.getDouble("game_sale_price");
				prodID = rset.getString("prodID");
				krs.addResult(rset.getString("description")+".jpg", description, y, salePrice, prodID);
				y++;
			}
		} catch (Exception ex)
		{
			System.out.println("queryGames ERROR: " + ex.getMessage());
		}
	}

	public void queryProductInfo(String prodID,ProductDisplay pd)
	{
		String prodType =null;
		try
		{
			stmt = conn.createStatement();
			String sqlStatement = "select prod_type from product where prod_id = '"+prodID+"'";
			rset = stmt.executeQuery(sqlStatement);
			while (rset.next())
			{
				prodType = rset.getString("PROD_TYPE");
			}
		}catch (Exception ex)
		{
			System.out.println("ERROR: " + ex.getMessage());
		}

		if(prodType.equals("GAME"))
		{
			String title =null;
			String genre =null;
			String company =null;
			String platform =null;
			int rating =0;
			double salePrice =0;
			int currentStock =0;
			try {
				stmt = conn.createStatement();
				String sqlStatement = "select p.current_stock, dp.genre, dp.age_rating, g.company, g.platform, g.game_name, g.game_sale_price "+
						"from product p, digital_product dp, game g "+
						"where dp.prod_id = p.prod_id "+
						"and dp.dig_id = g.dig_id "+
						"and p.prod_id = '"+prodID+"'";
				rset = stmt.executeQuery(sqlStatement);
				while (rset.next())
				{
					title = rset.getString("game_name");
					genre = rset.getString("genre");
					company = rset.getString("company");
					platform = rset.getString("platform");
					rating = rset.getInt("age_rating");
					salePrice = rset.getDouble("game_sale_price");
					currentStock = rset.getInt("current_stock");

					pd.displayGame(title,genre,company,platform,rating,salePrice,currentStock);
					pd.setHeading(title);
				}
			} catch (Exception ex)
			{
				System.out.println("ERROR: " + ex.getMessage());
			}
		}
		else if(prodType.equals("CD"))
		{
			String artist =null;
			String album =null;
			String genre =null;
			String recordCompany =null;
			String length =null;
			int rating =0;
			double salePrice =0;
			int currentStock =0;
			try {
				stmt = conn.createStatement();
				String sqlStatement = "select p.current_stock, dp.genre, dp.age_rating, a.artist_name, c.album_name, c.record_company, c.album_length, c.cd_sale_price "+
						"from product p, digital_product dp, cd c, artist a, cd_artist ca "+
						"where dp.prod_id = p.prod_id "+
						"and dp.dig_id = c.dig_id "+
						"and a.artist_id = ca.artist_id "+
						"and c.cd_id = ca.cd_id "+
						"and p.prod_id = '"+prodID+"'";
				rset = stmt.executeQuery(sqlStatement);
				while (rset.next())
				{
					artist = rset.getString("artist_name");
					album = rset.getString("album_name");
					genre = rset.getString("genre");
					recordCompany = rset.getString("record_company");
					length = rset.getString("album_length");
					rating = rset.getInt("age_rating");
					salePrice = rset.getDouble("cd_sale_price");
					currentStock = rset.getInt("current_stock");
				}
			} catch (Exception ex)
			{
				System.out.println("ERROR: " + ex.getMessage());
			}

			String songID =null;
			String songName =null;
			String songLength =null;
			int songNum =1;

			try {
				stmt = conn.createStatement();
				String sqlStatement = "select s.song_id, s.song_name, s.song_length "+
						"from product p, digital_product dp, cd c, song s "+
						"where p.prod_id = dp.prod_id "+
						"and dp.dig_id = c.dig_id "+
						"and s.cd_id = c.cd_id "+
						"and p.prod_id = '"+prodID+"'";
						rset = stmt.executeQuery(sqlStatement);
				while (rset.next())
				{
					songID = rset.getString("song_id");
					songName = rset.getString("song_name");
					songLength = rset.getString("song_length");

					Song s = new Song(songID,songName,songLength,artist,album,songNum);
					pd.addSong(s);
					songNum++;
				}
			} catch (Exception ex)
			{
				System.out.println("ERROR: " + ex.getMessage());
			}
			pd.displayCD(artist,album,genre,recordCompany,length,rating,salePrice,currentStock,prodID);
			pd.setHeading(artist+" - "+album);
		}
		if(prodType.equals("DVD"))
		{
			String title =null;
			String genre =null;
			int length =0;
			String studio =null;
			int rating =0;
			double salePrice =0;
			int currentStock =0;
			try {
				stmt = conn.createStatement();
				String sqlStatement = "select p.current_stock, dp.genre, dp.age_rating, d.studio, d.dvd_length, d.dvd_name, d.dvd_sale_price "+
						"from product p, digital_product dp, dvd d "+
						"where dp.prod_id = p.prod_id "+
						"and dp.dig_id = d.dig_id "+
						"and p.prod_id = '"+prodID+"'";
				rset = stmt.executeQuery(sqlStatement);
				while (rset.next())
				{
					title = rset.getString("dvd_name");
					genre = rset.getString("genre");
					studio = rset.getString("studio");
					length = rset.getInt("dvd_length");
					rating = rset.getInt("age_rating");
					salePrice = rset.getDouble("dvd_sale_price");
					currentStock = rset.getInt("current_stock");

					pd.displayDVD(title,genre,studio,length,rating,salePrice,currentStock);
					pd.setHeading(title);
				}
			} catch (Exception ex)
			{
				System.out.println("ERROR: " + ex.getMessage());
			}
		}
		else if(prodType.equals("SOUNDDOCK"))
		{
			String manufacturer =null;
			String model =null;
			String colour =null;
			String wireless =null;
			int powerOutput = 0;
			String digRadio =null;
			double salePrice =0;
			int currentStock =0;
			try {
				stmt = conn.createStatement();
				String sqlStatement = "select p.current_stock, e.manufacturer, e.model, e.colour, sd.wireless, sd.power_ouput, sd.digital_radio, sd.sd_sale_price "+
						"from product p, electronic e, sound_dock sd "+
						"where e.prod_id = p.prod_id "+
						"and e.elec_id = sd.elec_id "+
						"and p.prod_id = '"+prodID+"'";
				rset = stmt.executeQuery(sqlStatement);
				while (rset.next())
				{
					manufacturer = rset.getString("manufacturer");
					model = rset.getString("model");
					colour = rset.getString("colour");
					wireless = rset.getString("wireless");
					powerOutput = rset.getInt("power_ouput");
					digRadio = rset.getString("digital_radio");
					salePrice = rset.getDouble("sd_sale_price");
					currentStock = rset.getInt("current_stock");

					pd.displaySoundDock(manufacturer,model,colour,wireless,powerOutput,digRadio,salePrice,currentStock);
					pd.setHeading(manufacturer+" "+model);
				}
			} catch (Exception ex)
			{
				System.out.println("ERROR: " + ex.getMessage());
			}
		}
		else if(prodType.equals("HEADPHONES"))
		{
			String manufacturer =null;
			String model =null;
			String colour =null;
			String overEar =null;
			String mic =null;
			String iPhoneCompatible =null;
			double salePrice =0;
			int currentStock =0;
			try {
				stmt = conn.createStatement();
				String sqlStatement = "select p.current_stock, e.manufacturer, e.model, e.colour, hp.over_ear, hp.microphone, hp.iphone_compatible, hp.headphone_sale_price "+
						"from product p, electronic e, headphones hp "+
						"where e.prod_id = p.prod_id "+
						"and e.elec_id = hp.elec_id "+
						"and p.prod_id = '"+prodID+"'";
				rset = stmt.executeQuery(sqlStatement);
				while (rset.next())
				{
					manufacturer = rset.getString("manufacturer");
					model = rset.getString("model");
					colour = rset.getString("colour");
					overEar = rset.getString("over_ear");
					mic = rset.getString("microphone");
					iPhoneCompatible = rset.getString("iphone_compatible");
					salePrice = rset.getDouble("headphone_sale_price");
					currentStock = rset.getInt("current_stock");

					pd.displayHeadphones(manufacturer,model,colour,overEar,mic,iPhoneCompatible,salePrice,currentStock);
					pd.setHeading(manufacturer+" "+model);
				}
			} catch (Exception ex)
			{
				System.out.println("ERROR: " + ex.getMessage());
			}
		}
		else if(prodType.equals("CONSOLE"))
		{
			String manufacturer =null;
			String model =null;
			String colour =null;
			int storage =0;
			String wifi =null;
			int numControllers =0;
			double salePrice =0;
			int currentStock =0;
			try {
				stmt = conn.createStatement();
				String sqlStatement = "select p.current_stock, e.manufacturer, e.model, e.colour, c.storage_size, c.wifi, c.num_controllers, c.console_sale_price "+
						"from product p, electronic e, console c "+
						"where e.prod_id = p.prod_id "+
						"and e.elec_id = c.elec_id "+
						"and p.prod_id = '"+prodID+"'";
				rset = stmt.executeQuery(sqlStatement);
				while (rset.next())
				{
					manufacturer = rset.getString("manufacturer");
					model = rset.getString("model");
					colour = rset.getString("colour");
					storage = rset.getInt("storage_size");
					wifi = rset.getString("wifi");
					numControllers = rset.getInt("num_controllers");
					salePrice = rset.getDouble("console_sale_price");
					currentStock = rset.getInt("current_stock");

					pd.displayConsole(manufacturer,model,colour,storage,wifi,numControllers,salePrice,currentStock);
					pd.setHeading(manufacturer+" "+model);
				}
			} catch (Exception ex)
			{
				System.out.println("ERROR: " + ex.getMessage());
			}
		}
	}
	
	public void setDBconnection(Connection conn)
	{
		this.conn = conn;
	}
}
