package model;

import java.util.ArrayList;

public class ElecProdList {

	private ArrayList<DigiProduct> plist;
	
	
	public void refreshList()
	{
		
			if (plist.size() > 0) {
				for (int i = plist.size() - 1; i >= 0; i--) {
					plist.remove(i);
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
