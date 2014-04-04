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
	private String transType, desc;
	private double totalCost;
	private String transID,empID,prodID,date;
	private POSOperations po;


	
	public Transaction()
	{
		
		
		po = new POSOperations();
		po.openDB();
		transType = "S";
		transID = po.queryTransid();

		
	}
	
	public String getTransID()
	{
		return transID;
	}

	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double cost)
	{
		totalCost = cost;
	}


	public String getTransType() {
		return transType;
	}


	public void setTransType(String transType) {
		this.transType = transType;
	}


	public void setDate(String date) {
		this.date = date;
	}
	
	public String getProdID()
	{
		return prodID;
	}
	public void setProdID(String id)
	{
		prodID = id;
	}
	public void setDesc(String desc)
	{
		this.desc = desc;
	}
	public String getDesc()
	{
		return desc;
	}



}
