package Popups;

import gui.DigiProdPanel;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

import model.DigiProduct;
import model.DigiProductList;
import model.Song;

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
	private int size;
	
	private DigiProductList digiProductList;
	private DigiProdPanel digiPanel;

	public ProdDialog(JFrame jFrame, DigiProduct p, DigiProductList digiProductList, DigiProdPanel digiPanel, int size) {

		this.digiPanel = digiPanel;
		this.digiProductList = digiProductList;
		this.p = p;
		this.jframe = jFrame;
		this.size =size;
		
		cd = new CDPopup(p, digiProductList, this, size);
		cdDialog = new JDialog(jFrame, true);
		cdDialog.setLocationRelativeTo(null);
		cdDialog.add(cd);
		cdDialog.setSize(cd.getSize());
		cdDialog.setVisible(true);
		
		
		for (Component c : cdDialog.getContentPane().getComponents()) {
		    if (c instanceof JPanel) {
		 
		    	getDigiProdDetailBx = ((CDPopup) c).getDigiProdDetailBx();
		    	getDigiProdDetailBx2 = ((CDPopup) c).getDigiProdDetailBx2();
		    }
		}
			
	}
	
	public void addSongs(ArrayList<Song> slist)
	{
			digiPanel.newAlbum(slist);
			cdDialog.dispose();
	}
	
}
