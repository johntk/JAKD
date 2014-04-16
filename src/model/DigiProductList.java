package model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import db.ProdOperations;

public class DigiProductList {

	private ArrayList<DigiProduct> plist;
	private ArrayList<Song> slist;
	private ArrayList<Song> alist;
	private ProdOperations po;
	private ResultSet rset;
	private ResultSet rset2;
	private int count =0;
	
	public DigiProductList(ProdOperations po)
	{
		this.po = po;
		plist = new ArrayList<DigiProduct>();
		slist = new ArrayList<Song>();
	}
	
	
	public void refreshList()
	{
		if(count == 0)
			{
			if (plist.size() > 0) {
				for (int i = plist.size() - 1; i >= 0; i--) {
					plist.remove(i);
				}
			}
				refreshListCD();
				refreshListDVD();
				refreshListGame();
			}
	}
	
	public void refreshListCD() {
		
		songs();
		rset = po.getProductCD();
		
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
			CD c = new CD(alist);
			
				DigiProduct p = new DigiProduct(rset.getString(1), 
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
		if(count == 0)
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
		} 
		catch (Exception ea) {
			System.out.println(ea);
		}
		}
		count++;
	}
	
	
	public void refreshListDVD()
	{
		rset = po.getProductDVD();

		try {
			while (rset.next()) {

				DigiProduct p = new DigiProduct(rset.getString(1), 
						rset.getString(2),
						rset.getString(3), 
						rset.getDouble(4), 
						rset.getDouble(5), 
						rset.getInt(6),
						rset.getString(7),
						rset.getString(8),
						rset.getString(9), 
						rset.getDouble(10));
				plist.add(p);
				
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
		System.out.println(plist.size());
		}
	
		
	public void refreshListGame()
	{
		rset = po.getProductGame();

		try {
			while (rset.next()) {

				DigiProduct p = new DigiProduct(rset.getString(1), 
						rset.getString(2),
						rset.getString(3), 
						rset.getDouble(4), 
						rset.getDouble(5), 
						rset.getInt(6),
						rset.getString(7),
						rset.getString(8),
						rset.getString(9), 
						rset.getString(10));
				plist.add(p);
	
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
		System.out.println(plist.size());
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
	
	public DigiProduct getProduct(int i) {
		refreshList();
		return plist.get(i);
	}
}
