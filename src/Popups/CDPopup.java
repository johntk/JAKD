package Popups;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

import model.Product;

public class CDPopup extends JPanel implements ActionListener {

	private JLabel artist, genre, rating, length, label, songName, songLength,
			cd, songNumber;
	private JTextField artistTBox, ratingTBox, lengthTBox, labelTBox,
			genreTBox;
	private JButton add;
	private Product p;
	private Border space = (Border) BorderFactory.createEmptyBorder(10, 10, 10,10);
	private Border line = (Border) BorderFactory.createLineBorder(Color.black);
	private Border border = BorderFactory.createCompoundBorder(space, line);

	private JTextField song9, song10, song11, song12, song13, song8, song7, song6, song5, song4, song1, song2, song3,
			length9, length10, length11, length12, length13, length8, length7, length6, length5, length4, length1, length2,
			length3;

	private Font font = new Font("Verdana", Font.PLAIN, 20);
	private GridBagConstraints gc = new GridBagConstraints();

	JTextField[] digiProdDetailBx = { song1 = new JTextField(),
			song2 = new JTextField(), song3 = new JTextField(),
			song4 = new JTextField(), song5 = new JTextField(),
			song6 = new JTextField(), song7 = new JTextField(),
			song8 = new JTextField(), song9 = new JTextField(),
			song10 = new JTextField(), song11 = new JTextField(),
			song12 = new JTextField(), song13 = new JTextField()};

	JTextField[] digiProdDetailBx2 = { length1 = new JTextField(),
			length2 = new JTextField(), length3 = new JTextField(),
			length4 = new JTextField(), length5 = new JTextField(),
			length6 = new JTextField(), length7 = new JTextField(),
			length8 = new JTextField(), length9 = new JTextField(),
			length10 = new JTextField(), length11 = new JTextField(),
			length12 = new JTextField(), length13 = new JTextField()};

	JLabel[] digiProdDetailLb = { new JLabel(" 1"), new JLabel(" 2"),
			new JLabel(" 3"), new JLabel(" 4"), new JLabel(" 5"),
			new JLabel(" 6"), new JLabel(" 7"), new JLabel(" 8"), 
			new JLabel(" 9"), new JLabel(" 10"), new JLabel(" 11"), 
			new JLabel(" 12"), new JLabel(" 13") };

	public CDPopup(Product p) {

		this.setName("cd");
		this.p = p;
		this.setLayout(new BorderLayout());
		this.setSize(540, 500);
		JPanel top = new JPanel(new GridBagLayout());
		this.setBorder(new TitledBorder("CD"));
		// cd = new JLabel("CD");
		// top.add(cd);
		// this.add(top, BorderLayout.NORTH);

		JPanel detailsPanel = new JPanel(new BorderLayout());
		detailsPanel.setPreferredSize(new Dimension(200, 80));
		this.add(detailsPanel, BorderLayout.WEST);

		JPanel details = new JPanel(new GridLayout(0, 2));
		details.setBorder(border);
		details.setPreferredSize(new Dimension(200, 130));
		detailsPanel.add(details, BorderLayout.NORTH);

		JPanel spacer = new JPanel();
		spacer.setPreferredSize(new Dimension(200, 100));
		detailsPanel.add(spacer, BorderLayout.SOUTH);

		artist = new JLabel(" Artist");
		details.add(artist);
		artistTBox = new JTextField();
		details.add(artistTBox);

		rating = new JLabel(" Rating");
		details.add(rating);
		ratingTBox = new JTextField();
		details.add(ratingTBox);

		genre = new JLabel(" Genre");
		details.add(genre);
		genreTBox = new JTextField();
		details.add(genreTBox);

		length = new JLabel(" Length");
		details.add(length);
		lengthTBox = new JTextField();
		details.add(lengthTBox);

		label = new JLabel(" Label");
		details.add(label);
		labelTBox = new JTextField();
		details.add(labelTBox);

		JPanel songs = new JPanel(new GridBagLayout());
		songs.setPreferredSize(new Dimension(300, 200));
		songs.setBorder(border);

		songNumber = new JLabel(" No.");
		songName = new JLabel("Song");
		songLength = new JLabel("Length");

		for (int i = 0; i < digiProdDetailLb.length; i++) {

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
			digiProdDetailBx[i].setPreferredSize(new Dimension(100, 20));
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

		
		displayAlbum();
		this.setVisible(true);
		
	}

	
	public void displayAlbum()
	{
		System.out.println(p.getAlbum().getSongList().size());
		for(int i = 0; i < digiProdDetailBx.length; i++)
		{
			digiProdDetailBx[i].setText(p.getAlbum().getSongList().get(i).getSong_name());
			System.out.println(p.getAlbum().getSongList().get(i).getSong_name());
		}
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		
	}

}
