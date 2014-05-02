

package operations;

import gui.HomeScreen;

import java.awt.*;
import java.sql.*;

import javax.swing.*;

import oracle.jdbc.pool.OracleDataSource;

public class DBconnection
{
	private Connection conn;
	private JLabel n,p;
	private JTextField name;
	private JPasswordField pswd;
	private JPanel panel;
	private String pass;

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

	public Connection openDB(String URL,String uName,String psw)
	{
		try {
			OracleDataSource ods = new OracleDataSource();

			ods.setURL(URL);
			ods.setUser(uName);
			ods.setPassword(psw);
			conn = ods.getConnection();
			HomeScreen.trayIcon.displayMessage("JAKD", "Database Connected", TrayIcon.MessageType.INFO);
		} 
		catch (Exception e)
		{
			HomeScreen.trayIcon.displayMessage("JAKD", "Could not connect to Database", TrayIcon.MessageType.ERROR);
		}
		return conn;
	}

	public void closeDB()
	{
		try
		{
			if (conn != null) conn.close();
			System.out.println("Connection closed");
		} catch (SQLException e)
		{
			System.out.println("Could not close connection");
			e.printStackTrace();
		}
	}
}

