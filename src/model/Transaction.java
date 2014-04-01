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
	private char transType;
	private double totalCost;
	private String transID,empID,prodID,date;
	private POSOperations po;


	
	public Transaction()
	{
		
		
		po = new POSOperations();
		po.openDB();
		transType = 'S';
		transID = po.queryTransid();

		
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


	public void setDate(String date) {
		this.date = date;
	}
	
	public String getProdID()
	{
		return prodID;
	}



}
