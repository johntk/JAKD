package Popups;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

import model.CD;
import model.DigiProduct;
import model.DigiProductList;
import model.Song;

public class CDPopup extends JPanel implements ActionListener {

	private JLabel artist, songName, songLength, songNumber;
	private JTextField artistTBox;
	private JButton add, update;
	private DigiProduct p;
	private Border space = (Border) BorderFactory.createEmptyBorder(10, 10, 10,10);
	private Border line = (Border) BorderFactory.createLineBorder(Color.black);
	private Border border = BorderFactory.createCompoundBorder(space, line);

	private Font font = new Font("Verdana", Font.PLAIN, 20);
	private GridBagConstraints gc = new GridBagConstraints();
	
	private JTextField song9, song10, song11, song12, song13, song8, song7, song6, song5, song4, song1, song2, song3,
	length9, length10, length11, length12, length13, length8, length7, length6, length5, length4, length1, length2,
	length3;
	
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
	
	private JLabel[] digiProdDetailLb;
	
	
	
	private DigiProductList digiProductList;
	
	private ArrayList<Song> alist = new  ArrayList<Song>();
	


	public CDPopup(DigiProduct p, DigiProductList digiProductList) {
		
		
		this.digiProductList = digiProductList;
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
		
//		JTextField[] digiProdDetailBx = new JTextField[p.getAlbum().getSongList().size()];
//		JTextField[] digiProdDetailBx2 = new JTextField[p.getAlbum().getSongList().size()];
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
		add.addActionListener(this);
		bottom.add(add);
		
		update = new JButton("Update");
		update.setPreferredSize(new Dimension(60, 30));
		update.addActionListener(this);
		bottom.add(update);

		this.setVisible(true);
		
		
		
}

	
	
	public void updateEmployee() {
		

		
			for(int i = 0 ; i < p.getAlbum().getSongList().size(); i++)
			{
			
				Song s = new Song(p.getProd_id(),p.getAlbum().getSongList().get(i).getProd_id(), 
						digiProdDetailBx[i].getText(), digiProdDetailBx2[i].getText());
				alist.add(s);
			}
			
			
//			for(int i = 0 ; i < alist.size(); i++)
//			{
//			System.out.println(digiProdDetailBx[i].getText());
//			}
			
			CD c = new CD(alist);
			
			
			
			DigiProduct cd = new DigiProduct(p.getProd_id(),
					p.getProd_type(),
					p.getDigi_id(),
					p.getCd_id(),
					p.getArtist_id(),
					p.getAlbumName(),
					p.getCostPrice(),
					p.getSellPrice(),
					p.getCurrent_stock(),
					p.getAge_rating(),
					p.getGenre(),
					p.getPublisher(),
					p.getLength(),
					p.getArtist(),
					c
					);
			
		
		
		
		digiProductList.updateEmployee(cd);
		JOptionPane.showMessageDialog(null, "Album " + p.getAlbumName()
				+ " Updated");
		
}

	

	
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(update))
		{
			
			updateEmployee();
		}
		
		
	}

}
