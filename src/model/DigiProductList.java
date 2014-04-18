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
						rset.getString(4),
						rset.getString(5), 
						rset.getString(6),
						rset.getDouble(7), 
						rset.getDouble(8), 
						rset.getInt(9),
						rset.getString(10),
						rset.getString(11),
						rset.getString(12), 
						rset.getDouble(13),
						rset.getString(14), 
						c);
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
						rset2.getString(3), 
						rset2.getString(4));
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
		
//		System.out.println(plist.size());
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
		
//		System.out.println(plist.size());
		}
		
	public int findProd(String id) {
		int index = -1;
		for (int i = 0; i < plist.size(); i++) {
			if (plist.get(i).getProd_id().equals(id)) {
				index = i;
			}
		}
		return index;
	}

	public DigiProduct getProd(int i) {
		refreshList();
		return plist.get(i);
	}
	
	
	public int removeProd(String id) {
		int num = 0;
		for (int i = 0; i < plist.size(); i++) {
			if (id.equals(plist.get(i).getProd_id())) {
				plist.remove(i);
				po.deleteProd(id);
				num++;
			}
		}
		return num;
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
	
	
	
	
	public void updateEmployee(DigiProduct p) {
		
		for (int i = 0; i < plist.size(); i++) {
			
			
			
			if (plist.get(i).getProd_id().equals(p.getProd_id())) {
				plist.get(i).setAlbumName(p.getAlbumName());;
				plist.get(i).setProd_id(p.getProd_id());
				plist.get(i).setDigi_id(p.getDigi_id());
				plist.get(i).setCd_id(p.getCd_id());
				plist.get(i).setArtist_id(p.getArtist_id());
				plist.get(i).setCostPrice(p.getCostPrice());;
				plist.get(i).setSellPrice(p.getSellPrice());;
				plist.get(i).setCurrent_stock(p.getCurrent_stock());;
				plist.get(i).setAge_rating(p.getAge_rating());;
				plist.get(i).setGenre(p.getGenre());;
				plist.get(i).setPublisher(p.getPublisher());;
				plist.get(i).setLength(p.getLength());;
				plist.get(i).setArtist(p.getArtist());
				plist.get(i).setAlbum(p.getAlbum());;

				po.updateProdCD(plist.get(i));
			}
		}

	}
	
	public int getNumProduct() {
		return plist.size();
	}
	
	public DigiProduct getProduct(int i) {
		refreshList();
		return plist.get(i);
	}
}
