package model;

import java.sql.Date;

public class Results 
{
	
	private int transID;
	private Date date;
	private String tranType;
	private double cost;
	private int quantity;
	private int empID;
	private String prodID;
	
	public Results(int transID, Date date, String tranType, double cost,
			int quantity, int empID, String prodID) 
	{
		this.transID = transID;
		this.date = date;
		this.tranType = tranType;
		this.cost = cost;
		this.quantity = quantity;
		this.empID = empID;
		this.prodID = prodID;
	}
	public String toString()
	{
		return "TransID\t\t"+transID+"\n"+"Date\t\t"+date+"\n"+"Tran Type\t"+tranType+"\n"+"Cost\t\t"+
				cost+"\n"+"Quan\t\t"+quantity+"\n"+"Emp ID\t\t"+empID+"\n"+"Prod ID\t\t"+prodID+"\n"+"\n";
	}
	public int getTransID() 
	{
		return transID;
	}
	public void setTransID(int transID) {
		this.transID = transID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTranType() {
		return tranType;
	}
	public void setTranType(String tranType) {
		this.tranType = tranType;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getEmpID() {
		return empID;
	}
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	public String getProdID() {
		return prodID;
	}
	public void setProdID(String prodID) {
		this.prodID = prodID;
	}
}
