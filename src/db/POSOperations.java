package db;
import java.sql.*;
import java.util.ArrayList;

import model.Transaction;

import javax.swing.JOptionPane;

import com.sun.org.apache.bcel.internal.classfile.PMGClass;

import oracle.jdbc.pool.OracleDataSource;

public class POSOperations 
{
	
	private Statement stmt;
	private ResultSet rset;
	private Connection conn;
	private PreparedStatement pstmt;
	

	public POSOperations() 
	{
		
	}
	
	public void openDB()
	{
		try
		{
			// Load the Oracle JDBC driver
			OracleDataSource ods = new OracleDataSource();
			ods.setURL("jdbc:oracle:thin:HR/@localhost:1521:XE");
			try{
				//Add a try for your own username and password if your sick of changing this all the time like I am.
				//Please don't change the details below.
				ods.setUser("johntk86");
				ods.setPassword("FuckYou");
				conn = ods.getConnection();
			}
			
			 catch (Exception e) {
				 try{
						ods.setUser("project");
						ods.setPassword("project");
						conn = ods.getConnection();
					}
					
					 catch (Exception ex) {
						String name = JOptionPane.showInputDialog(null, "Enter your orcale user name");
						String pswd = JOptionPane.showInputDialog(null, "Enter your password");
						 ods.setUser(name);
						 ods.setPassword(pswd);
						}
				}

//			 Tallaght Database
//			 ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");
//			 ods.setUser("");
//			 ods.setPassword("");

			conn = ods.getConnection();
			System.out.println("Connection Established.\n");
		}
		catch(Exception e)
		{
			System.out.println("Unable to find driver " + e);
			System.exit(1);
		}
		
	}
	
	public void closeDB()
	{
		try
		{
			stmt.close();
			conn.close();

			System.out.print("\nConnection closed");
		} 
		catch (SQLException e)
		{
			System.out.print("\nCould not close connection ");
			e.printStackTrace();
		}
	}
	
	public ResultSet queryProduct(String prodInput) throws SQLException
	{
		System.out.println("in query product");


		String sql = "SELECT p.prod_id,d.dvd_name,d.dvd_sale_price FROM product p, DIGITAL_PRODUCT dp, dvd d where p.PROD_ID = dp.PROD_ID AND dp.DIG_ID = d.DIG_ID AND p.prod_id = '" + prodInput + "'" +
				"union  SELECT p.prod_id,c.album_name,c.cd_sale_price FROM product p, DIGITAL_PRODUCT dp, cd c where p.PROD_ID = dp.PROD_ID AND dp.DIG_ID = c.DIG_ID AND p.prod_id = '" + prodInput + "'" +
				"union  SELECT p.prod_id,g.game_name,g.game_sale_price FROM product p, DIGITAL_PRODUCT dp, game g where p.PROD_ID = dp.PROD_ID AND dp.DIG_ID = g.DIG_ID AND p.prod_id = '" + prodInput + "'" +
				"union  SELECT p.prod_id,e.manufacturer||' - '||e.model,c.CONSOLE_SALE_PRICE FROM product p, electronic e, console c where p.PROD_ID = e.PROD_ID AND e.elec_id = c.elec_ID AND p.prod_id = '" + prodInput + "'" +
				"union  SELECT p.prod_id,e.manufacturer||' - '||e.model,sd.sd_SALE_PRICE FROM product p, electronic e, sound_dock sd where p.PROD_ID = e.PROD_ID AND e.elec_id = sd.elec_ID AND p.prod_id = '" + prodInput + "'" +
				"union  SELECT p.prod_id,e.manufacturer||' - '||e.model,hp.HEADPHONE_SALE_PRICE FROM product p, electronic e, headphones hp where p.PROD_ID = e.PROD_ID AND e.elec_id = hp.elec_ID AND p.prod_id = '" + prodInput + "'";
		try 
		{
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
		}
		
		catch(SQLException e)
		{
			System.out.println("Couldn't find product");
		}
			
			
		return rset;
	}
	
	
	public String queryTransid()
	{
		int trans_id = 0;
		
		try
		{
			
			String queryTransid = "SELECT trans_id FROM TRANSACTION GROUP BY trans_id";

			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			rset = stmt.executeQuery(queryTransid);
			
			rset.afterLast();
			
			if (rset.previous()) 
			{
				  trans_id = Integer.parseInt(rset.getString(1)) + 1;

			}
		}
		

		
		catch(SQLException e)
		{
			System.out.println(e);
			System.out.println("no trans_id found");
		}
		

		return Integer.toString(trans_id);
		
	}
	
	public ResultSet displayProduct(String prodInput)throws SQLException
	{
		
		
		queryProduct(prodInput);
		return rset;
		
	}

	public void voidProduct(String prodInput, ArrayList<Transaction> t)throws SQLException
	{
		for (int i = 0;i < t.size(); i++)
		{
			if(prodInput == t.get(i).getTransID())
			{
				t.remove(i);

				System.out.println(t.get(i).getTransID());
				
			}
			
			
		}
	}
	
	public void insertTran(ArrayList<Transaction> t)
	{
		//trans_id NUMBER NOT NULL, trans_date DATE, trans_type VARCHAR2(1) CHECK(trans_type IN('S','R')), total_cost NUMBER(30,2))"
		String insert = "INSERT INTO transaction(trans_id, trans_date, trans_type, total_cost, quantity, emp_id, prod_id) VALUES(?,?,?,?,?,?,?)"; 	
		try
		{
			pstmt = conn.prepareStatement(insert);
			
			for(int i = 0; i< t.size();i++)
			{
				pstmt.setString(1, t.get(i).getTransID());
				pstmt.setString(2, t.get(i).getDate());
				pstmt.setString(3, t.get(i).getTransType());
				pstmt.setDouble(4, t.get(i).getTotalCost());
				pstmt.setInt(5, t.get(i).getQuantity());
				pstmt.setString(6,"1234");
				pstmt.setString(7,t.get(i).getProdID());
				
				pstmt.execute();
				System.out.println("insert" + (i+1) +" sucessfull :)");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("cant do insert");
		}
	}
	
	public void updateCurrentStock(ArrayList<Transaction> t)
	{
		for(int i = 0; i < t.size(); i++)
		{
			String s = "SELECT prod_id, current_stock FROM  product WHERE prod_id = '" + t.get(i).getProdID() + "'";	
		

			try
			{
				pstmt = conn.prepareStatement(s,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				rset = pstmt.executeQuery();
				rset.next();
				int stock = rset.getInt(2);
				
				//if return +1 , if sale -1
				if(t.get(i).getTransType().equals("R"))
				{
					stock += 1;
				}
				else
				{
					stock -= 1;
				}
				
				
			
				rset.updateInt(2,stock);
				rset.updateRow();
		
				System.out.println("updated current stock ");
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				System.out.println(e);
				System.out.println("error with updating stock");
			}
		}
	}

}
