package gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;




public class ProdDialog extends JDialog  {


	private JButton ok;
	private JPanel cardPanel,  cdPopup, dvdPopup, GamePopup, phonoPopup, dockPopup, consolePopup;
	private CardLayout cards;
	
	private JDialog cd;
	
	private Border space = (Border) BorderFactory.createEmptyBorder(10, 10, 10, 10);
	private Border line = (Border) BorderFactory.createLineBorder(Color.black);
	private Border border = BorderFactory.createCompoundBorder(space, line);
	private BorderLayout layout = new BorderLayout();
	
	private static final int FRAME_WIDTH = 840;
	private static final int FRAME_HEIGHT = 500;
	
	public ProdDialog(String popUp)
	{
		

		cdPopup = new CDPopup();
		dvdPopup = new DVDPopup();
		cardPanel = new JPanel();
		cards = new CardLayout();

		cd = new JDialog();
		
		cd.setLocationRelativeTo(null);


		cardPanel.setLayout(cards);
		cardPanel.add(cdPopup, "cd");
		cardPanel.add(dvdPopup, "dvd");
//		cardPanel.add(GamePopup, "game");
//		cardPanel.add(phonoPopup, "phono");
//		cardPanel.add(dockPopup, "dock");
//		cardPanel.add(consolePopup, "console");
		cardPanel.setBorder(border);
		
		cd.add(cardPanel, BorderLayout.EAST);
		

		if(popUp == "cdDialog")
		{
			cards.show(cardPanel, "cd");
			cd.setSize(250,250);
			
		}
		else if(popUp == "dvdDialog")
		{
			cards.show(cardPanel, "dvd");
			cd.setSize(500,250);
		}
		
		cd.setVisible(true);
		
		System.out.println(popUp);
		
	}
}
