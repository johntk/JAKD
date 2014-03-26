package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import db.POSOperations;

public class Transaction 
{
	private int quantity;
	//private final DateFormat df;
	private char transType;
	private double totalCost;
	private String transID,empID,prodID;
	private ArrayList<String> inserts;
	private POSOperations po;
	private ResultSet data;

	
	public Transaction()
	{
		
		po = new POSOperations();
		po.openDB();
		
		transType = 'S';
		transID = po.queryTransid();
		inserts = new ArrayList<String>();
	}
	
	
	public String displayProduct(String prodInput)throws SQLException
	{
		
		data = po.queryProduct(prodInput);
		data.next();
		prodID =  data.getString(1);
		String desc =  data.getString(2);
		double price = Double.parseDouble(data.getString(3));
		totalCost +=  price;
		
		return prodID + "\t" + desc + "\t" + price + "\t" + transType;
		
	}
	
	public void voidProduct(String prodInput)throws SQLException
	{
		data = po.queryProduct(prodInput);
		data.next();
		double price = Double.parseDouble(data.getString(3));
		transType = 'R';
		totalCost -=price;
	}
	
	
	
	public String getTransID()
	{
		return transID;
	}

	public double getTotalCost() {
		return totalCost;
	}


	public char getTransType() {
		return transType;
	}


	public void setTransType(char transType) {
		this.transType = transType;
	}




}
