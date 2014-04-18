package Popups;

import gui.DigiProdPanel;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import model.DigiProduct;
import model.DigiProductList;

public class ProdDialog extends JDialog {

	private JPanel cd;
	private JFrame jframe;
	private JDialog cdDialog;
	private DigiProduct p;
	private Border space = (Border) BorderFactory.createEmptyBorder(10, 10, 10,10);
	private Border line = (Border) BorderFactory.createLineBorder(Color.black);
	private Border border = BorderFactory.createCompoundBorder(space, line);
	private JTextField[] getDigiProdDetailBx;
	private JTextField[] getDigiProdDetailBx2;
	
	private DigiProductList digiProductList;

	public ProdDialog(String popUp, JFrame jFrame, DigiProduct p, DigiProductList digiProductList) {

		
		this.digiProductList = digiProductList;
		this.p = p;
		this.jframe = jFrame;
		
		JPanel popupPanels[] = new JPanel[] { cd = new CDPopup(p, digiProductList)};

		cdDialog = new JDialog(jFrame, true);
		cdDialog.setLocationRelativeTo(null);
		
		
//		for (Component c : cdDialog.getContentPane().getComponents()) {
//		    if (c instanceof CDPopup) {
//		 
//		    	getDigiProdDetailBx = ((CDPopup) c).getDigiProdDetailBx();
//		    	getDigiProdDetailBx2 = ((CDPopup) c).getDigiProdDetailBx2();
//		    }
//		}
		
	
		
		
		for (int i = 0; i < popupPanels.length; i++) {
			if (popUp.equals(popupPanels[i].getName())) {
				cdDialog.add(popupPanels[i]);
				cdDialog.setSize(popupPanels[i].getSize());
			}
		}
		cdDialog.setVisible(true);
	}
	
	
	
//	public JTextField[] getValue()
//	{
//		return getDigiProdDetailBx;
//	}
}
