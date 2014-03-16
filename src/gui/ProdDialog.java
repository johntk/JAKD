package gui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;




public class ProdDialog extends JDialog  {


	
	private JPanel cardPanel,  cdPopup, dvdPopup, gamePopup, phonoPopup, dockPopup, consolePopup;
	private String[] popup = {"cd", "dvd", "game", "phono", "console", "dock"};
	
	private CardLayout cards;
	
	private JDialog cd;
	
	private Border space = (Border) BorderFactory.createEmptyBorder(10, 10, 10, 10);
	private Border line = (Border) BorderFactory.createLineBorder(Color.black);
	private Border border = BorderFactory.createCompoundBorder(space, line);
	private BorderLayout layout = new BorderLayout();
	
	public ProdDialog(String popUp)
	{
		
		

		cdPopup = new CDPopup();
		dvdPopup = new DVDPopup();
		gamePopup = new CDPopup();
		phonoPopup = new DVDPopup();
		dockPopup = new CDPopup();
		consolePopup = new DVDPopup();
		
		
		cardPanel = new JPanel();
		cards = new CardLayout();

		cd = new JDialog();
		
		cd.setLocationRelativeTo(null);


		cardPanel.setLayout(cards);
		cardPanel.add(cdPopup, "cd");
		cardPanel.add(dvdPopup, "dvd");
		cardPanel.add(gamePopup, "game");
		cardPanel.add(phonoPopup, "phono");
		cardPanel.add(dockPopup, "dock");
		cardPanel.add(consolePopup, "console");
		cardPanel.setBorder(border);
		
		cd.add(cardPanel, BorderLayout.EAST);
		

		for(int i =0; i < 6; i++)
		{
			if(popUp == popup[i])
			{
				cards.show(cardPanel, popup[i]);
				cd.pack();
			}
			
		}

		cd.setVisible(true);

	}
}
