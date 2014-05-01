package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import operations.ProdOperations;

public class ElecProdList {

	private ArrayList<ElecProduct> plist;
	private ProdOperations po;
	private ResultSet rset;
	private ResultSet rset2;
	
	public ElecProdList(ProdOperations po)
	{
		this.po = po;
		plist = new ArrayList<ElecProduct>();
	}
	
	//Removes products from the ArrayLists and re populates the ArrayLists from the resultsSet
	public void refreshList()
	{
		if (plist.size() > 0) {
			for (int i = plist.size() - 1; i >= 0; i--) {
				plist.remove(i);
			}
		}
		refreshListPhono();
		refreshListConsole();
		refreshListDock();
	}
	
	//Populates the ArrayList from the resultsSet
	public void refreshListPhono()
	{
		rset = po.getProductHeadphone();
		
			try {
				while (rset.next()) {
	
				ElecProduct	p = new ElecProduct(rset.getString(1), 
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
						rset.getString(12), 
						rset.getString(13));
				plist.add(p);
				
				}

			} catch (SQLException e) {
				e.printStackTrace();
				
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
	public void refreshListConsole()
	{
		rset = po.getProductConsole();
		
		try {
			while (rset.next()) {

				ElecProduct	p = new ElecProduct(rset.getString(1), 
						rset.getString(2),
						rset.getString(3),
						rset.getString(4),
						rset.getString(5), 
						rset.getDouble(6), 
						rset.getDouble(7), 
						rset.getInt(8),
						rset.getInt(9),
						rset.getString(10),
						rset.getInt(11), 
						rset.getString(12), 
						rset.getString(13));
				plist.add(p);
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
			
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
	public void refreshListDock()
	{
		rset = po.getProductDock();
		
		try {
			while (rset.next()) {

				ElecProduct	p = new ElecProduct(rset.getString(1), 
						rset.getString(2),
						rset.getString(3),
						rset.getString(4),
						rset.getString(5), 
						rset.getDouble(6), 
						rset.getDouble(7), 
						rset.getInt(8),
						rset.getString(9),
						rset.getString(10),
						rset.getInt(11), 
						rset.getString(12), 
						rset.getString(13));
				plist.add(p);
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
			
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
	
	//adds the last product added to the DB to the Arraylist
	public void addProduct() {
		
		rset2 = po.getLastRow();
		
		try {
			
			if(rset2.getString(1).equals("HEADPHONES"))
			{
			
			rset = po.getLastRowHeadphone();
			
			
			ElecProduct p = new ElecProduct(rset.getString(1), 
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
						rset.getString(12),
						rset.getString(13));
				plist.add(p);

			}
			else if(rset2.getString(1).equals("CONSOLE"))
			{
				
				rset = po.getLastRowConsole();
				ElecProduct p = new ElecProduct(rset.getString(1), 
						rset.getString(2),
						rset.getString(3),
						rset.getString(4), 
						rset.getString(5),
						rset.getDouble(6), 
						rset.getDouble(7), 
						rset.getInt(8),
						rset.getInt(9),
						rset.getString(10),
						rset.getInt(11), 
						rset.getString(12),
						rset.getString(13));
				plist.add(p);
			}
			else if(rset2.getString(1).equals("SOUND_DOCK"))
			{
	
				
				rset = po.getLastRowSounddock();
				ElecProduct p = new ElecProduct(rset.getString(1), 
						rset.getString(2),
						rset.getString(3),
						rset.getString(4), 
						rset.getString(5),
						rset.getDouble(6), 
						rset.getDouble(7), 
						rset.getInt(8),
						rset.getString(9),
						rset.getString(10),
						rset.getInt(11), 
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
	
	//Updates the product values associated with the passed in product ID
	public void updateProduct(ElecProduct p) {
		
		for (int i = 0; i < plist.size(); i++) {
			

			
			if (plist.get(i).getProd_id().equals(p.getProd_id()) && plist.get(i).getProd_type().equals("HEADPHONES")) {

				plist.get(i).setModel(p.getModel());
				plist.get(i).setProd_id(p.getProd_id());
				plist.get(i).setElec_id(p.getElec_id());
				plist.get(i).setHeadphone_id(p.getHeadphone_id());
				plist.get(i).setCostPrice(p.getCostPrice());
				plist.get(i).setSellPrice(p.getSellPrice());
				plist.get(i).setCurrent_stock(p.getCurrent_stock());
				plist.get(i).setMic(p.getMic());
				plist.get(i).setOverEar(p.getOverEar());
				plist.get(i).setIphoneComp(p.getIphoneComp());
				plist.get(i).setManufacturer(p.getManufacturer());
				plist.get(i).setColour(p.getColour());
				
				po.updateProduct(plist.get(i));
			}
			
			
			else if(plist.get(i).getProd_id().equals(p.getProd_id()) && plist.get(i).getProd_type().equals("CONSOLE"))
			{
				plist.get(i).setModel(p.getModel());
				plist.get(i).setProd_id(p.getProd_id());
				plist.get(i).setElec_id(p.getElec_id());
				plist.get(i).setConsole_id(p.getConsole_id());
				plist.get(i).setCostPrice(p.getCostPrice());
				plist.get(i).setSellPrice(p.getSellPrice());
				plist.get(i).setCurrent_stock(p.getCurrent_stock());
				plist.get(i).setStorageSize(p.getStorageSize());
				plist.get(i).setWifi(p.getWifi());
				plist.get(i).setNumPad(p.getNumPad());
				plist.get(i).setManufacturer(p.getManufacturer());
				plist.get(i).setColour(p.getColour());
			
				po.updateProduct(plist.get(i));
			}
			

			else if(plist.get(i).getProd_id().equals(p.getProd_id()))
			{
				
				plist.get(i).setModel(p.getModel());
				plist.get(i).setProd_id(p.getProd_id());
				plist.get(i).setElec_id(p.getElec_id());
				plist.get(i).setConsole_id(p.getSd_id());
				plist.get(i).setCostPrice(p.getCostPrice());
				plist.get(i).setSellPrice(p.getSellPrice());
				plist.get(i).setCurrent_stock(p.getCurrent_stock());
				plist.get(i).setDigiRadio(p.getDigiRadio());
				plist.get(i).setWireless(p.getWireless());
				plist.get(i).setPwrOut(p.getPwrOut());
				plist.get(i).setManufacturer(p.getManufacturer());
				plist.get(i).setColour(p.getColour());
			
				po.updateProduct(plist.get(i));
			}
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
	public ElecProduct getProd(int i) {
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

	// Returns the arraylist of products, size
	public int getNumProduct() {
		return plist.size();
	}
	
	
	//Returns a product from the ArrayList based on the value i passed in
	public ElecProduct getProduct(int i) {
		
		refreshList(); 
		return plist.get(i);
		
		
	}
}
