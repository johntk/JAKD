

package db;

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

	public Connection openDB()
	{
		try {
			OracleDataSource ods = new OracleDataSource();

			name.requestFocusInWindow();
			ods.setURL("jdbc:oracle:thin:HR/@localhost:1521:XE");
			ods.setUser("project");
			ods.setPassword("project");
			conn = ods.getConnection();
			HomeScreen.trayIcon.displayMessage("JAKD", "Database Connected", TrayIcon.MessageType.INFO);
		} 
		catch (Exception e)
		{
			try
			{
				OracleDataSource ods = new OracleDataSource();
				name.requestFocusInWindow();
				ods.setURL("jdbc:oracle:thin:HR/@localhost:1521:XE");
				ods.setUser("johntk86");
				ods.setPassword("FuckYou");
				conn = ods.getConnection();
				HomeScreen.trayIcon.displayMessage("JAKD", "Database Connected", TrayIcon.MessageType.INFO);
			}
			catch(Exception ex)
			{
				try 
				{
					OracleDataSource ods = new OracleDataSource();
					ods.setURL("jdbc:oracle:thin:HR/@localhost:1521:XE");
					JOptionPane.showMessageDialog(null, panel, "Local DB", JOptionPane.INFORMATION_MESSAGE);
					ods.setUser(name.getText());
					pass = new String(pswd.getPassword());
					ods.setPassword(pass);
					conn = ods.getConnection();
					HomeScreen.trayIcon.displayMessage("JAKD", "Database Connected", TrayIcon.MessageType.INFO);
				}
				catch (Exception x)
				{
					try{
						// Tallaght Database
						OracleDataSource ods = new OracleDataSource();
						ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");
						JOptionPane.showMessageDialog(null, panel, "Tallght DB", JOptionPane.INFORMATION_MESSAGE);
						ods.setUser(name.getText());
						pass = new String(pswd.getPassword());
						ods.setPassword(pass);
						conn = ods.getConnection();
						HomeScreen.trayIcon.displayMessage("JAKD", "Database Connected", TrayIcon.MessageType.INFO);

					}catch (Exception exc)
					{
						System.out.print("Unable to load driver " + exc);
						System.exit(0);
					}
				}
			}
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
	public Connection getCon()
	{
		return conn;
	}
}

