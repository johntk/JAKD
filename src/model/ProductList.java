package model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import db.ProdOperations;

public class ProductList {

	private ArrayList<Product> plist;
	private ArrayList<Song> slist;
	private ArrayList<Song> alist;
	private ProdOperations po;
	private ResultSet rset;
	private ResultSet rset2;
	
	public ProductList(ProdOperations po)
	{
		this.po = po;
		plist = new ArrayList<Product>();
		slist = new ArrayList<Song>();
		
	}
	
	

	public void refreshList() {
		songs();
		rset = po.getProduct();
		
		if (plist.size() > 0) {
			for (int i = plist.size() - 1; i >= 0; i--) {
				plist.remove(i);
			}
		}
		
		try {
			while (rset.next()) {
			alist = new ArrayList<Song>();
			for(int i = 0; i < slist.size(); i++)
			{
				if(slist.get(i).getSong_id().equals(rset.getString(1)))
				{
					alist.add(slist.get(i));
				}	
			}
			System.out.println(alist.size());
			
			CD c = new CD(alist);
			
				Product p = new Product(rset.getString(1), 
						rset.getString(2),
						rset.getString(3), 
						rset.getDouble(4), 
						rset.getDouble(5), 
						rset.getInt(6),
						rset.getString(7),
						rset.getString(8),
						rset.getString(9), 
						rset.getDouble(10), c);
				plist.add(p);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	
	public void songs()
	{
		rset2 = po.getSongs();
		try {
			while (rset2.next())
			{
				Song s = new Song(rset2.getString(1),
						rset2.getString(2), 
						rset2.getString(3));
				slist.add(s);
			}
			System.out.println(slist.size());
		} 
		catch (Exception ea) {
			System.out.println(ea);
		}
	}
	

//	
//	public void addProduct() {
//		rset = po.getLastRow();
//		try {
//			{
//				Product p = new Product(rset.getString(1), 
//						rset.getString(2),
//						rset.getString(3), 
//						rset.getDouble(4), 
//						rset.getDouble(5), 
//						rset.getInt(6),
//						rset.getString(7),
//						rset.getString(8),
//						rset.getString(9), 
//						rset.getDouble(10), c);
//				plist.add(p);
//			}
//
//		} catch (Exception ex) {
//			System.out.println(ex);
//		}
//		
//		
//	}
	
	public int getNumProduct() {
		return plist.size();
	}
	
	public Product getProduct(int i) {
		refreshList();
		return plist.get(i);
	}
}
