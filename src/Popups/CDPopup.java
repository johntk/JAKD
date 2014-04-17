package Popups;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

import model.DigiProduct;

public class CDPopup extends JPanel implements ActionListener {

	private JLabel artist, songName, songLength, songNumber;
	private JTextField artistTBox;
	private JButton add;
	private DigiProduct p;
	private Border space = (Border) BorderFactory.createEmptyBorder(10, 10, 10,10);
	private Border line = (Border) BorderFactory.createLineBorder(Color.black);
	private Border border = BorderFactory.createCompoundBorder(space, line);

	private Font font = new Font("Verdana", Font.PLAIN, 20);
	private GridBagConstraints gc = new GridBagConstraints();
	
	private JTextField[] digiProdDetailBx;
	private JTextField[] digiProdDetailBx2;
	private JLabel[] digiProdDetailLb;
	
	
	public CDPopup(DigiProduct p) {
		
		this.setName("cd");
		this.p = p;
		this.setLayout(new BorderLayout());
		if(p.getAlbum().getSongList().size() >= 15)
		{
			this.setSize(690, 600);
		}
		else if(p.getAlbum().getSongList().size() < 11)
		{
			this.setSize(690, 410);
		}
		else
		{
			this.setSize(690, 490);
		}
		JPanel top = new JPanel(new GridBagLayout());
		this.setBorder(new TitledBorder("CD"));


		JPanel detailsPanel = new JPanel(new BorderLayout());
		detailsPanel.setPreferredSize(new Dimension(240, 80));
		this.add(detailsPanel, BorderLayout.WEST);

		JPanel details = new JPanel(new GridBagLayout());
		details.setBorder(border);
		details.setPreferredSize(new Dimension(150, 130));
		detailsPanel.add(details, BorderLayout.NORTH);

		JPanel spacer = new JPanel();
		spacer.setPreferredSize(new Dimension(200, 100));
		detailsPanel.add(spacer, BorderLayout.SOUTH);

		
		gc.gridx = 0;
		gc.gridy = 0;
		artist = new JLabel(" Artist");
		details.add(artist, gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		artistTBox = new JTextField(10);
		
		details.add(artistTBox, gc);

		
		JPanel songs = new JPanel(new GridBagLayout());
		songs.setPreferredSize(new Dimension(380, 200));
		songs.setBorder(border);

		songNumber = new JLabel(" No.");
		songName = new JLabel("Song");
		songLength = new JLabel("Length");
		
		JTextField[] digiProdDetailBx = new JTextField[p.getAlbum().getSongList().size()];
		JTextField[] digiProdDetailBx2 = new JTextField[p.getAlbum().getSongList().size()];
		JLabel[] digiProdDetailLb = new JLabel[p.getAlbum().getSongList().size()];
		

		for (int i = 0; i < p.getAlbum().getSongList().size(); i++) {

			int num = i +1;
			
			if (i == 0) {
				gc.gridx = 0;
				gc.gridy = 0;
				gc.anchor = GridBagConstraints.WEST;
				songs.add(songNumber, gc);
			}

			gc.gridx = 0;
			gc.gridy = i + 1;
			gc.gridwidth = 1;
			gc.gridheight = 1;
			gc.weighty = 0.0;
			gc.weightx = 2.0;
			gc.anchor = GridBagConstraints.WEST;
			digiProdDetailLb[i] = new JLabel(" " + num);
			digiProdDetailLb[i].setFont(font);
			songs.add(digiProdDetailLb[i], gc);

			if (i == 0) {
				gc.gridx = 1;
				gc.gridy = 0;
				songs.add(songName, gc);
			}
			gc.gridx = 1;
			gc.gridy = i + 1;
			gc.gridwidth = 1;
			gc.gridheight = 1;
			gc.weighty = 0.0;
			gc.weightx = 2.0;
			gc.gridwidth = 2;
			digiProdDetailBx[i] = new JTextField(p.getAlbum().getSongList().get(i).getSong_name());
			digiProdDetailBx[i].setPreferredSize(new Dimension(250, 20));
			songs.add(digiProdDetailBx[i], gc);

			if (i == 0) {
				gc.gridx = 3;
				gc.gridy = 0;
				songs.add(songLength, gc);
			}
			gc.gridx = 3;
			gc.gridy = i + 1;
			gc.gridwidth = 1;
			gc.gridheight = 1;
			gc.weighty = 0.0;
			gc.weightx = 2.0;
			gc.gridwidth = 2;
			digiProdDetailBx2[i] = new JTextField(p.getAlbum().getSongList().get(i).getSong_length());
			digiProdDetailBx2[i].setPreferredSize(new Dimension(50, 20));
			songs.add(digiProdDetailBx2[i], gc);

		}
		;

		this.add(songs, BorderLayout.EAST);

		JPanel bottom = new JPanel(new FlowLayout());
		this.add(bottom, BorderLayout.SOUTH);

		add = new JButton("Add");
		add.setPreferredSize(new Dimension(60, 30));
		bottom.add(add);

		this.setVisible(true);
		
	}

	public void actionPerformed(ActionEvent arg0) {
		
		
	}

}
