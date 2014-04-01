package Popups;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;


public class GamePopup extends JPanel{

	
	private JLabel genre, rating,  publisher;
	private JTextField genreTBox, ratingTBox,  publisherTBox;
	private JButton add;
	
	
	public GamePopup()
	{
		this.setName("game");
		this.setLayout(new BorderLayout());
		this.setSize(240, 200);
		
		JPanel details = new JPanel(new GridLayout(0,2));
		details.setBorder(new TitledBorder("Game"));

		this.add(details, BorderLayout.NORTH);
		
		genre = new JLabel("Genre");
		details.add(genre);
		genreTBox = new JTextField(5);
		genreTBox.setPreferredSize(new Dimension(200,30));
		details.add(genreTBox);
		
		rating = new JLabel("Rating:");
		details.add(rating);
		ratingTBox = new JTextField(5);
		details.add(ratingTBox);
		
		
		publisher = new JLabel("Publisher");
		details.add(publisher);
		publisherTBox = new JTextField(5);
		details.add(publisherTBox);
		
		JPanel bottom = new JPanel(new FlowLayout());
		this.add(bottom, BorderLayout.SOUTH);
		
		add = new JButton("Add");
		add.setPreferredSize(new Dimension(60,30));
		bottom.add(add);
		
		
		this.setVisible(true);
		
	}
	
}
