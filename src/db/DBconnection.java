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

	//int numResults = queryNumResults("SELECT COUNT(*) FROM ");
	public int queryNumResults(String s)
	{
		int numResults =0;
		try {
			stmt = conn.createStatement();
			String sqlStatement = s;
			rset = stmt.executeQuery(sqlStatement);
			while (rset.next())
			{
				numResults++;
				System.out.println(numResults);
			}
		} catch (Exception ex)
		{
			System.out.println("ERROR: " + ex.getMessage());
		}
		return numResults;
	}


	public void queryMusic()
	{
		KioskResultsScreen krs = new KioskResultsScreen();
		krs.setHeading("MUSIC");
		String description;
		double salePrice;
		int y=0;
		try {
			stmt = conn.createStatement();
			String sqlStatement = "SELECT a.artist_name, c.album_name, c.cd_sale_price FROM product p, artist a, cd_artist ca, cd c, digital_product d WHERE p.prod_id = d.prod_id AND d.dig_id = c.dig_id AND a.artist_id = ca.artist_id AND c.cd_id = ca.cd_id";
			rset = stmt.executeQuery(sqlStatement);
			while (rset.next())
			{
				description = rset.getString("artist_name")+" - "+rset.getString("album_name");
				salePrice = rset.getDouble("cd_sale_price");
				krs.addResult(rset.getString("album_name")+".jpg", description, y, salePrice);
				y++;
			}
		} catch (Exception ex)
		{
			System.out.println("ERROR: " + ex.getMessage());
		}
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
