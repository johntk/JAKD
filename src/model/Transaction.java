package model;



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
		//empID = pin;
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
	
	public String getDate()
	{
		return date;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}



}
