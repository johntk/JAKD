
package db;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;

import kioskScreens.*;
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

	public Connection openDB()
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
		return conn;
	}

	public void closeDB()
	{
		try
		{
			if (stmt != null) stmt.close();
			if (rset != null) rset.close();
			if (conn != null) conn.close();
			System.out.println("Connection closed");
		} catch (SQLException e)
		{
			System.out.println("Could not close connection");
			e.printStackTrace();
		}
	}
}
