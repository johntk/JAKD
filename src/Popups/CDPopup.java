package Popups;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;



public class CDPopup extends JPanel{

	
	private JLabel artist, album, length, label, songName, songLength, cd, songNumber;
	private JTextField artistTBox, albumTBox, lengthTBox, labelTBox;
	private JButton ok;
	
	private	Border space = (Border) BorderFactory.createEmptyBorder(10, 10, 10, 10);
	private	Border line = (Border) BorderFactory.createLineBorder(Color.black);
	private	Border border = BorderFactory.createCompoundBorder(space, line);
	
	private JTextField other, supplierID, currentStock, sellPrice, costPrice,
	prodTitle, type, prodId, other2, supplierID2, currentStock2, sellPrice2, costPrice2,
	prodTitle2, type2, prodId2;
	private Font font = new Font("Verdana", Font.PLAIN, 20);
	private GridBagConstraints gc = new GridBagConstraints();
	
	JTextField[] digiProdDetailBx = { prodTitle = new JTextField(),
			type = new JTextField(), prodId = new JTextField(),
			costPrice = new JTextField(), sellPrice = new JTextField(),
			currentStock = new JTextField(), supplierID = new JTextField(),
			other = new JTextField(), };
	
	JTextField[] digiProdDetailBx2 = { prodTitle2 = new JTextField(),
			type2 = new JTextField(), prodId2 = new JTextField(),
			costPrice2 = new JTextField(), sellPrice2 = new JTextField(),
			currentStock2 = new JTextField(), supplierID2 = new JTextField(),
			other2 = new JTextField(), };
	
	JLabel[] digiProdDetailLb = { new JLabel(" 1"),
			new JLabel(" 2"), new JLabel(" 3"),
			new JLabel(" 4"), new JLabel(" 5"),
			new JLabel(" 6"), new JLabel(" 7"),
			new JLabel(" 8") };
	
	public CDPopup()
	{
		
		
		this.setLayout(new BorderLayout());
		this.setSize(600, 400);
		JPanel top = new JPanel(new GridLayout(0,2));
		
		cd = new JLabel("CD");
		top.add(cd);
		this.add(top, BorderLayout.NORTH);

		JPanel details = new JPanel(new GridLayout(0,2));
		details.setBorder(border);
		details.setPreferredSize(new Dimension(200,80));
		this.add(details, BorderLayout.WEST);
		
		
		artist = new JLabel("Artist");
		details.add(artist);
		artistTBox = new JTextField();
		details.add(artistTBox);
		
		album = new JLabel("Album");
		details.add(album);
		albumTBox = new JTextField();
		details.add(albumTBox);
		
		length = new JLabel("Length");
		details.add(length);
		lengthTBox = new JTextField();
		details.add(lengthTBox);
		
		label = new JLabel("Label");
		details.add(label);
		labelTBox = new JTextField();
		details.add(labelTBox);

		JPanel songs = new JPanel(new GridBagLayout());
		songs.setPreferredSize(new Dimension(300, 200));
		songs.setBorder(border);
		
		JPanel spacer = new JPanel();
		
		spacer.setPreferredSize(new Dimension(200,80));
		
		
		songNumber = new JLabel(" No.");
		songName = new JLabel("Song");
		songLength = new JLabel("Length");
		
		
		
		
		for (int i = 0; i < digiProdDetailLb.length; i++) {
			
			if(i == 0)
			{
				gc.gridx = 0;
				gc.gridy = 0;
				gc.anchor = GridBagConstraints.WEST;
				songs.add(songNumber, gc);
			}
			
			gc.gridx = 0;
			gc.gridy = i+1;
			gc.gridwidth = 1;
			gc.gridheight = 1;
			gc.weighty = 0.0;
			gc.weightx = 2.0;
			gc.anchor = GridBagConstraints.WEST;
			digiProdDetailLb[i].setFont(font);
			songs.add(digiProdDetailLb[i], gc);

			
			if(i == 0)
			{
				gc.gridx = 1;
				gc.gridy = 0;
				songs.add(songName, gc);
			}
				gc.gridx = 1;
				gc.gridy = i+1;
				gc.gridwidth = 1;
				gc.gridheight = 1;
				gc.weighty = 0.0;
				gc.weightx = 2.0;
				gc.gridwidth = 2;
				digiProdDetailBx[i].setPreferredSize(new Dimension(100, 20));
				songs.add(digiProdDetailBx[i], gc);
				
				
				if(i == 0)
				{
					gc.gridx = 3;
					gc.gridy = 0;
					songs.add(songLength, gc);
				}
				gc.gridx = 3;
				gc.gridy = i+1;
				gc.gridwidth = 1;
				gc.gridheight = 1;
				gc.weighty = 0.0;
				gc.weightx = 2.0;
				gc.gridwidth = 2;
				digiProdDetailBx2[i].setPreferredSize(new Dimension(50, 20));
				songs.add(digiProdDetailBx2[i], gc);
				
			};
		

			
		
			this.add(songs, BorderLayout.EAST);
			
			
		JPanel bottom = new JPanel(new FlowLayout());
		this.add(bottom, BorderLayout.SOUTH);
		
		ok = new JButton("OK");
		ok.setPreferredSize(new Dimension(60,30));
		bottom.add(ok);
		
		
		this.setVisible(true);
		
	}
	
}
