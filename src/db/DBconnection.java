package db;

import java.awt.*;
import java.sql.*;

import javax.swing.*;

import kioskScreens.KioskResultsScreen;
import kioskScreens.ProductDisplay;
import oracle.jdbc.pool.OracleDataSource;

public class DBconnection
{
	private Connection conn;
	private Statement stmt;
	private ResultSet rset;
	private JLabel n,p;
	private JTextField name;
	private JPasswordField pswd;
	private JPanel panel;
	private DBconnection db;

	public DBconnection()
	{
		n = new JLabel("Enter your oracle user name");
		p = new JLabel("Enter your oracle password");
		name = new JTextField(20);
		pswd = new JPasswordField(20);
		panel = new JPanel(new GridLayout(4,1));
		panel.add(n);
		panel.add(name);
		panel.add(p);
		panel.add(pswd);
	}

	public void openDB()
	{
		try {
			OracleDataSource ods = new OracleDataSource();

			// Tallaght Database
			//ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");
			//ods.setUser("");
			//ods.setPassword("");

			name.requestFocusInWindow();
			ods.setURL("jdbc:oracle:thin:HR/@localhost:1521:XE");
			ods.setUser("project");
			ods.setPassword("project");
			conn = ods.getConnection();
		} catch (Exception e){
			try {
				OracleDataSource ods = new OracleDataSource();
				ods.setURL("jdbc:oracle:thin:HR/@localhost:1521:XE");
				JOptionPane.showMessageDialog(null, panel);
				ods.setUser(name.getText());
				ods.setPassword(pswd.getText());
				conn = ods.getConnection();
			}catch (Exception x)
			{
				JOptionPane.showMessageDialog(null, "Failed to Connect", "Failed to Connect", JOptionPane.INFORMATION_MESSAGE);
				System.out.print("Unable to load driver " + x);
			}
		}
	}

	public void queryAllProducts(String sTerm)
	{
		KioskResultsScreen krs = new KioskResultsScreen(db);
		krs.setHeading(sTerm.toUpperCase());
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
		krs.displayResult();
	}

	public void queryMusic()
	{
		KioskResultsScreen krs = new KioskResultsScreen(db);
		krs.setHeading("MUSIC");
		String description;
		String productThumb;
		String prodID;
		double salePrice;
		int y=0;
		try {
			stmt = conn.createStatement();
			String sqlStatement = "SELECT a.artist_name||' - '||c.album_name as description, c.cd_sale_price, p.prod_id as prodID FROM product p, artist a, cd_artist ca, cd c, digital_product d WHERE p.prod_id = d.prod_id AND d.dig_id = c.dig_id AND a.artist_id = ca.artist_id AND c.cd_id = ca.cd_id";
			rset = stmt.executeQuery(sqlStatement);
			while (rset.next())
			{
				description = rset.getString("description");
				salePrice = rset.getDouble("cd_sale_price");
				productThumb = rset.getString("description")+".jpg";
				prodID = rset.getString("prodID");
				krs.addResult(productThumb, description, y, salePrice, prodID);
				y++;
			}
		} catch (Exception ex)
		{
			System.out.println("ERROR: " + ex.getMessage());
		}
		krs.displayResult();
	}

	public void queryDVD()
	{
		KioskResultsScreen krs = new KioskResultsScreen(db);
		krs.setHeading("DVD");
		String description;
		double salePrice;
		String prodID;
		int y=0;
		try {
			stmt = conn.createStatement();
			String sqlStatement = "select d.dvd_name, d.dvd_sale_price, p.prod_id as prodID from dvd d, DIGITAL_PRODUCT dp, product p where p.prod_id = dp.prod_id and dp.dig_id = d.dig_id";
			rset = stmt.executeQuery(sqlStatement);
			while (rset.next())
			{
				description = rset.getString("dvd_name");
				salePrice = rset.getDouble("dvd_sale_price");
				prodID = rset.getString("prodID");
				krs.addResult(rset.getString("dvd_name")+".jpg", description, y, salePrice, prodID);
				y++;
			}
		} catch (Exception ex)
		{
			System.out.println("ERROR: " + ex.getMessage());
		}
		krs.displayResult();
	}

	public void queryConsoles()
	{
		KioskResultsScreen krs = new KioskResultsScreen(db);
		krs.setHeading("CONSOLES");
		String description;
		double salePrice;
		String prodID;
		int y=0;
		try {
			stmt = conn.createStatement();
			String sqlStatement = "select e.manufacturer||' '||e.model as description, c.console_sale_price, p.prod_id as prodID from product p, electronic e, console c where p.prod_id = e.prod_id and c.elec_id = e.elec_id";
			rset = stmt.executeQuery(sqlStatement);
			while (rset.next())
			{
				description = rset.getString("description");
				salePrice = rset.getDouble("console_sale_price");
				prodID = rset.getString("prodID");
				krs.addResult(rset.getString("description")+".jpg", description, y, salePrice, prodID);
				y++;
			}
		} catch (Exception ex)
		{
			System.out.println("ERROR: " + ex.getMessage());
		}
		krs.displayResult();
	}

	public void queryHeadphones()
	{
		KioskResultsScreen krs = new KioskResultsScreen(db);
		krs.setHeading("HEADPHONES");
		String description;
		double salePrice;
		String prodID;
		int y=0;
		try {
			stmt = conn.createStatement();
			String sqlStatement = "select e.manufacturer||' '||e.model as description, h.headphone_sale_price, p.prod_id as prodID from product p, electronic e, headphones h where p.prod_id = e.prod_id and h.elec_id = e.elec_id";
			rset = stmt.executeQuery(sqlStatement);
			while (rset.next())
			{
				description = rset.getString("description");
				salePrice = rset.getDouble("headphone_sale_price");
				prodID = rset.getString("prodID");
				krs.addResult(rset.getString("description")+".jpg", description, y, salePrice, prodID);
				y++;
			}
		} catch (Exception ex)
		{
			System.out.println("ERROR: " + ex.getMessage());
		}
		krs.displayResult();
	}

	public void querySoundDocks()
	{
		KioskResultsScreen krs = new KioskResultsScreen(db);
		krs.setHeading("SOUNDDOCKS");
		String description;
		double salePrice;
		String prodID;
		int y=0;
		try {
			stmt = conn.createStatement();
			String sqlStatement = "select e.manufacturer||' '||e.model as description, s.sd_sale_price, p.prod_id as prodID from product p, electronic e, SOUND_DOCK s where p.prod_id = e.prod_id and s.elec_id = e.elec_id";
			rset = stmt.executeQuery(sqlStatement);
			while (rset.next())
			{
				description = rset.getString("description");
				salePrice = rset.getDouble("sd_sale_price");
				prodID = rset.getString("prodID");
				krs.addResult(rset.getString("description")+".jpg", description, y, salePrice, prodID);
				y++;
			}
		} catch (Exception ex)
		{
			System.out.println("ERROR: " + ex.getMessage());
		}
		krs.displayResult();
	}


	public void queryGames(String platform)
	{
		KioskResultsScreen krs = new KioskResultsScreen(db);
		krs.setHeading(platform);
		String description;
		double salePrice;
		String prodID;
		int y=0;
		try {
			stmt = conn.createStatement();
			String sqlStatement = "select g.game_name as description, g.game_sale_price, p.prod_id as prodID from product p, digital_product d, game g where p.prod_id = d.prod_id and g.dig_id = d.dig_id and g.platform = '"+platform+"'";
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
			System.out.println("ERROR: " + ex.getMessage());
		}
		krs.displayResult();
	}

	public void queryProductInfo(String prodID)
	{
		String PID = prodID;
		ProductDisplay pd = new ProductDisplay();
		String title =null;
		String genre =null;
		String company =null;
		String platform =null;
		int rating =0;
		double salePrice =0;
		int currentStock =0;
		String prodType =null;
		try
		{
			stmt = conn.createStatement();
			String sqlStatement = "select prod_type from product where prod_id = '"+PID+"'";
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
			try {
				stmt = conn.createStatement();
				String sqlStatement = "select p.current_stock, dp.genre, dp.age_rating, g.company, g.platform, g.game_name, g.game_sale_price "+
						"from product p, digital_product dp, game g "+
						"where dp.prod_id = p.prod_id "+
						"and dp.dig_id = g.dig_id "+
						"and p.prod_id = '"+PID+"'";
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
				}
			} catch (Exception ex)
			{
				System.out.println("ERROR: " + ex.getMessage());
			}
		}
	}
	
	public void setDB(DBconnection db)
	{
		this.db = db;
	}
	
	public void closeDB()
	{
		try
		{
			stmt.close();
			rset.close();
			conn.close();
			System.out.println("Connection closed");
		} catch (SQLException e)
		{
			System.out.println("Could not close connection");
			e.printStackTrace();
		}
	}
}
