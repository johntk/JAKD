package db;

import java.awt.*;
import java.sql.*;

import javax.swing.*;

import kioskScreens.KioskResultsScreen;
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
			JOptionPane.showMessageDialog(null, panel);
			ods.setURL("jdbc:oracle:thin:HR/@localhost:1521:XE");
			ods.setUser(name.getText());
			ods.setPassword(pswd.getText());

			conn = ods.getConnection();
			JOptionPane.showMessageDialog(null, "Connection Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Failed to Connect", "Failed to Connect", JOptionPane.INFORMATION_MESSAGE);
			System.out.print("Unable to load driver " + e);
		}
	}

	public void queryAllProducts(String sTerm)
	{
		KioskResultsScreen krs = new KioskResultsScreen();
		krs.setHeading(sTerm.toUpperCase());
		String description;
		String productThumb;
		double salePrice;
		int y=0;
		try {
			stmt = conn.createStatement();
			String sqlStatement = "select dvd_name as description, dvd_sale_price as salePrice from dvd where UPPER(dvd_name) like UPPER('%"+sTerm+"%') "+
					"UNION select game_name, game_sale_price from game where UPPER(game_name) like UPPER('%"+sTerm+"%') "+
					"UNION select a.artist_name||' - '||c.album_name, c.cd_sale_price from cd c, artist a, cd_artist ca, digital_product d where c.cd_id = ca.cd_id and a.artist_id = ca.artist_id and c.dig_id = d.dig_id and UPPER(a.artist_name||' '||c.album_name) like UPPER('%"+sTerm+"%') "+
					"UNION select e.manufacturer||' '||e.model, h.headphone_sale_price from electronic e, headphones h where e.elec_id = h.elec_id and UPPER(e.manufacturer||' '||e.model) like UPPER('%"+sTerm+"%') "+
					"UNION select e.manufacturer||' '||e.model, s.sd_sale_price from electronic e, sound_dock s where e.elec_id = s.elec_id and UPPER(e.manufacturer||' '||e.model) like UPPER('%"+sTerm+"%') "+
					"UNION select e.manufacturer||' '||e.model, c.console_sale_price from electronic e, console c where e.elec_id = c.elec_id and UPPER(e.manufacturer||' '||e.model) like UPPER('%"+sTerm+"%')";
			rset = stmt.executeQuery(sqlStatement);
			while (rset.next())
			{
				description = rset.getString("description");
				salePrice = rset.getDouble("saleprice");
				productThumb = rset.getString("description")+".jpg";
				krs.addResult(productThumb, description, y, salePrice);
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
		KioskResultsScreen krs = new KioskResultsScreen();
		krs.setHeading("MUSIC");
		String description;
		String productThumb;
		double salePrice;
		int y=0;
		try {
			stmt = conn.createStatement();
			String sqlStatement = "SELECT a.artist_name||' - '||c.album_name as description, c.cd_sale_price FROM product p, artist a, cd_artist ca, cd c, digital_product d WHERE p.prod_id = d.prod_id AND d.dig_id = c.dig_id AND a.artist_id = ca.artist_id AND c.cd_id = ca.cd_id";
			rset = stmt.executeQuery(sqlStatement);
			while (rset.next())
			{
				description = rset.getString("description");
				salePrice = rset.getDouble("cd_sale_price");
				productThumb = rset.getString("description")+".jpg";
				krs.addResult(productThumb, description, y, salePrice);
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
		KioskResultsScreen krs = new KioskResultsScreen();
		krs.setHeading("DVD");
		String description;
		double salePrice;
		int y=0;
		try {
			stmt = conn.createStatement();
			String sqlStatement = "select d.dvd_name, d.dvd_sale_price from dvd d, DIGITAL_PRODUCT dp, product p where p.prod_id = dp.prod_id and dp.dig_id = d.dig_id";
			rset = stmt.executeQuery(sqlStatement);
			while (rset.next())
			{
				description = rset.getString("dvd_name");
				salePrice = rset.getDouble("dvd_sale_price");
				krs.addResult(rset.getString("dvd_name")+".jpg", description, y, salePrice);
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
		KioskResultsScreen krs = new KioskResultsScreen();
		krs.setHeading("CONSOLES");
		String description;
		double salePrice;
		int y=0;
		try {
			stmt = conn.createStatement();
			String sqlStatement = "select e.manufacturer||' '||e.model as description, c.console_sale_price from product p, electronic e, console c where p.prod_id = e.prod_id and c.elec_id = e.elec_id";
			rset = stmt.executeQuery(sqlStatement);
			while (rset.next())
			{
				description = rset.getString("description");
				salePrice = rset.getDouble("console_sale_price");
				krs.addResult(rset.getString("description")+".jpg", description, y, salePrice);
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
		KioskResultsScreen krs = new KioskResultsScreen();
		krs.setHeading("HEADPHONES");
		String description;
		double salePrice;
		int y=0;
		try {
			stmt = conn.createStatement();
			String sqlStatement = "select e.manufacturer||' '||e.model as description, h.headphone_sale_price from product p, electronic e, headphones h where p.prod_id = e.prod_id and h.elec_id = e.elec_id";
			rset = stmt.executeQuery(sqlStatement);
			while (rset.next())
			{
				description = rset.getString("description");
				salePrice = rset.getDouble("headphone_sale_price");
				krs.addResult(rset.getString("description")+".jpg", description, y, salePrice);
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
		KioskResultsScreen krs = new KioskResultsScreen();
		krs.setHeading("SOUNDDOCKS");
		String description;
		double salePrice;
		int y=0;
		try {
			stmt = conn.createStatement();
			String sqlStatement = "select e.manufacturer||' '||e.model as description, s.sd_sale_price from product p, electronic e, SOUND_DOCK s where p.prod_id = e.prod_id and s.elec_id = e.elec_id";
			rset = stmt.executeQuery(sqlStatement);
			while (rset.next())
			{
				description = rset.getString("description");
				salePrice = rset.getDouble("sd_sale_price");
				krs.addResult(rset.getString("description")+".jpg", description, y, salePrice);
				y++;
			}
		} catch (Exception ex)
		{
			System.out.println("ERROR: " + ex.getMessage());
		}
		krs.displayResult();
	}

	public void queryDeals()
	{
		KioskResultsScreen krs = new KioskResultsScreen();
		krs.setHeading("SOUNDDOCKS");
		String description;
		double salePrice;
		int y=0;
		try {
			stmt = conn.createStatement();
			String sqlStatement = "select e.manufacturer||' '||e.model as description, s.sd_sale_price from product p, electronic e, SOUND_DOCK s where p.prod_id = e.prod_id and s.elec_id = e.elec_id";
			rset = stmt.executeQuery(sqlStatement);
			while (rset.next())
			{
				description = rset.getString("description");
				salePrice = rset.getDouble("sd_sale_price");
				krs.addResult(rset.getString("description")+".jpg", description, y, salePrice);
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
		KioskResultsScreen krs = new KioskResultsScreen();
		krs.setHeading(platform);
		String description;
		double salePrice;
		int y=0;
		try {
			stmt = conn.createStatement();
			String sqlStatement = "select g.game_name as description, g.game_sale_price from product p, digital_product d, game g where p.prod_id = d.prod_id and g.dig_id = d.dig_id and g.platform = '"+platform+"'";
			rset = stmt.executeQuery(sqlStatement);
			while (rset.next())
			{
				description = rset.getString("description");
				salePrice = rset.getDouble("game_sale_price");
				krs.addResult(rset.getString("description")+".jpg", description, y, salePrice);
				y++;
			}
		} catch (Exception ex)
		{
			System.out.println("ERROR: " + ex.getMessage());
		}
		krs.displayResult();
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
