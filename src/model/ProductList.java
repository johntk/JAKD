package model;

import java.sql.ResultSet;
import java.util.ArrayList;




import db.ProdOperations;

public class ProductList {

	
	private ArrayList<Product> plist;
	private ProdOperations po;
	private ResultSet rset;
	
	public ProductList(ProdOperations po)
	{
		this.po = po;
		plist = new ArrayList<Product>();
		
		
		
	}
	public void addProduct() {
		rset = po.getLastRow();
		try {
			{
//				Product p = new Product(rset.getInt(1), rset.getString(2),
//						rset.getString(3), rset.getString(4),
//						rset.getString(5), rset.getString(6),
//						rset.getString(7), rset.getString(8), rset.getInt(9),
//						rset.getString(10));
//				plist.add(e);
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}
