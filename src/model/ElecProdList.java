package model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import db.ProdOperations;

public class ElecProdList {

	private ArrayList<ElecProduct> plist;
	private ProdOperations po;
	private ResultSet rset;
	
	
	public ElecProdList(ProdOperations po)
	{
		this.po = po;
		plist = new ArrayList<ElecProduct>();
	}
	
	public void refreshList()
	{
		rset = po.getProductHeadphone();
		
		
			if (plist.size() > 0) {
				for (int i = plist.size() - 1; i >= 0; i--) {
					plist.remove(i);
				}
			}
			
			
			try {
				while (rset.next()) {
	
				ElecProduct	p = new ElecProduct(rset.getString(1), 
						rset.getString(2),
						rset.getString(3), 
						rset.getDouble(4), 
						rset.getDouble(5), 
						rset.getInt(6),
						rset.getString(7),
						rset.getString(8),
						rset.getString(9), 
						rset.getString(10), 
						rset.getString(11));
				plist.add(p);
				
				}

			} catch (SQLException e) {
				e.printStackTrace();
				
			}
			
			
	}
	
	
	public int getNumProduct() {
		return plist.size();
	}
	
	
	public ElecProduct getProduct(int i) {
		refreshList(); 
		return plist.get(i);
	}
}
