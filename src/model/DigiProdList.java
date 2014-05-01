package model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import operations.ProdOperations;

public class DigiProdList {

	private ArrayList<DigiProduct> plist;
	private ArrayList<Song> slist;
	private ArrayList<Song> alist;
	private ArrayList<Song> nlist = new ArrayList<Song>();;
	private ProdOperations po;
	private ResultSet rset;
	private ResultSet rset2;
	private ResultSet rset3;
	private int count =0;
	
	public DigiProdList(ProdOperations po)
	{
		this.po = po;
		plist = new ArrayList<DigiProduct>();
		slist = new ArrayList<Song>();
	}
	
	
	
	//Removes products, songs from the ArrayLists and re populates the ArrayLists from the resultsSet
	public void refreshList()
	{

			if (plist.size() > 0) {
				for (int i = plist.size() - 1; i >= 0; i--) {
					plist.remove(i);
				}
			}
			if (slist.size() > 0) {
				for (int i = slist.size() - 1; i >= 0; i--) {
					slist.remove(i);
				}
			}
				songs();
				refreshListCD();
				refreshListDVD();
				refreshListGame();
			}
	
	
	//Populates the ArrayList from the resultsSet
	public void refreshListCD() {
		
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
		finally {  
		    if(rset != null) {  
		        try {  
		        	rset.close();  
		        	po.closeReultSets();
		        }  
		        catch (SQLException e) {}  
		    }  
		}
	}
	
	//Populates the ArrayList from the resultsSet
	public void songs()
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
		finally {  
		    if(rset != null) {  
		        try {   
		        	rset2.close();
		        	po.closeReultSets();
		        }  
		        catch (SQLException e) {}  
		    }  
		}
		}

	
	//Populates the ArrayList from the resultsSet
	public void refreshListDVD()
	{
		rset = po.getProductDVD();

		try {
			while (rset.next()) {

				DigiProduct p = new DigiProduct(rset.getString(1), 
						rset.getString(2),
						rset.getString(3), 
						rset.getString(4),
						rset.getString(5), 
						rset.getDouble(6), 
						rset.getDouble(7), 
						rset.getInt(8),
						rset.getString(9),
						rset.getString(10),
						rset.getString(11), 
						rset.getDouble(12));
				plist.add(p);
				
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		finally {  
		    if(rset != null) {  
		        try {  
		        	rset.close(); 
		        	po.closeReultSets();
		        }  
		        catch (SQLException e) {}  
		    }  
		}
		
	}
	
	//Populates the ArrayList from the resultsSet	
	public void refreshListGame()
	{
		rset = po.getProductGame();

		try {
			while (rset.next()) {
				DigiProduct p = new DigiProduct(rset.getString(1), 
						rset.getString(2),
						rset.getString(3), 
						rset.getString(4),
						rset.getString(5),
						rset.getDouble(6), 
						rset.getDouble(7), 
						rset.getInt(8),
						rset.getString(9),
						rset.getString(10),
						rset.getString(11), 
						rset.getString(12));
				plist.add(p);
	
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
	}
	//Searches the ArrayList for a product and returns the position in the array if found
	public int findProd(String id) {
		int index = -1;
		for (int i = 0; i < plist.size(); i++) {
			if (plist.get(i).getProd_id().equals(id)) {
				index = i;
			}
		}
		return index;
	}

	//Returns a product from the ArrayList based on the value i passed in
	public DigiProduct getProd(int i) {
		refreshList();
		return plist.get(i);
	}
	
	//Deletes a product from the ArrayList and from the DB
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

	
	//adds the last product added to the DB to the Arraylist
	public void addProduct() {
	
		rset2 = po.getLastRow();
		
		
		try {
			
			if(rset2.getString(1).equals("CD"))
			{
			
			addSongs(rset2.getString(1));
			rset = po.getLastRowCD();
			CD c = new CD(nlist);
			
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
						rset.getString(14), c);
				plist.add(p);

			}
			else if(rset2.getString(1).equals("DVD"))
			{
				
				rset = po.getLastRowDVD();
				DigiProduct p = new DigiProduct(rset.getString(1), 
						rset.getString(2),
						rset.getString(3),
						rset.getString(4), 
						rset.getString(5), 
						rset.getDouble(7), 
						rset.getDouble(8), 
						rset.getInt(9),
						rset.getString(10),
						rset.getString(11),
						rset.getString(12), 
						rset.getDouble(13));
				plist.add(p);
			}
			else if(rset2.getString(1).equals("GAME"))
			{
	
				rset = po.getLastRowGAME();
				DigiProduct p = new DigiProduct(rset.getString(1), 
						rset.getString(2),
						rset.getString(3),
						rset.getString(4), 
						rset.getString(5), 
						rset.getDouble(7), 
						rset.getDouble(8), 
						rset.getInt(9),
						rset.getString(10),
						rset.getString(11),
						rset.getString(12),
						rset.getString(13));
				plist.add(p);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		finally {  
		    if(rset != null) {  
		        try {  
		        	rset.close();  
		        	rset2.close();
		        	po.closeReultSets();
		        }  
		        catch (SQLException e) {}  
		    }  
		}
		
	}
	
	//adds the songs in the DB based on the ID passed in, to the Arraylist
	public void addSongs(String id)
	{
		
		rset3 = po.getLastRowAlbum(id);
		try {
			while (rset3.next())
			{
				System.out.println(rset3.getString(3));
				Song s = new Song(rset3.getString(1),
						rset3.getString(2),
						rset3.getString(3), 
						rset3.getString(4));
				nlist.add(s);
			}
		} 
		catch (Exception ea) {
			System.out.println(ea);
		}
		finally {  
		    if(rset != null) {  
		        try {  
		        	rset3.close();  
		        	po.closeReultSets();
		        }  
		        catch (SQLException e) {}  
		    }  
		}
	}
	//Updates the product values associated with the passed in product ID
	public void updateProduct(DigiProduct p) {
		
		for (int i = 0; i < plist.size(); i++) {
			
			
			if (plist.get(i).getProd_id().equals(p.getProd_id()) && plist.get(i).getProd_type().equals("CD")) {
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

				po.updateProduct(plist.get(i));
			}
			

			
			else if(plist.get(i).getProd_id().equals(p.getProd_id()) && plist.get(i).getProd_type().equals("DVD"))
			{
				plist.get(i).setDvd_name(p.getDvd_name());
				plist.get(i).setProd_id(p.getProd_id());
				plist.get(i).setDigi_id(p.getDigi_id());
				plist.get(i).setDvd_id(p.getDvd_id());
				plist.get(i).setProd_type(p.getProd_type());
				plist.get(i).setCostPrice(p.getCostPrice());;
				plist.get(i).setSellPrice(p.getSellPrice());;
				plist.get(i).setCurrent_stock(p.getCurrent_stock());;
				plist.get(i).setAge_rating(p.getAge_rating());;
				plist.get(i).setGenre(p.getGenre());;
				plist.get(i).setStudio(p.getStudio());
				plist.get(i).setLength(p.getLength());;
			
				po.updateProduct(plist.get(i));
			}
			

			else if(plist.get(i).getProd_id().equals(p.getProd_id()))
			{
				plist.get(i).setGame_name(p.getGame_name());
				plist.get(i).setProd_id(p.getProd_id());
				plist.get(i).setDigi_id(p.getDigi_id());
				plist.get(i).setGame_id(p.getGame_id());
				plist.get(i).setProd_type(p.getProd_type());
				plist.get(i).setCostPrice(p.getCostPrice());;
				plist.get(i).setSellPrice(p.getSellPrice());;
				plist.get(i).setCurrent_stock(p.getCurrent_stock());;
				plist.get(i).setAge_rating(p.getAge_rating());;
				plist.get(i).setGenre(p.getGenre());;
				plist.get(i).setStudio(p.getStudio());
				plist.get(i).setPlatform(p.getPlatform());
			
				po.updateProduct(plist.get(i));
			}
		}

	}
	
	// Returns the arraylist of products, size
	public int getNumProduct() {
		return plist.size();
	}
	
	//Returns a product from the ArrayList based on the value i passed in
	public DigiProduct getProduct(int i) {
		
		refreshList();
		return plist.get(i);
	}
}
