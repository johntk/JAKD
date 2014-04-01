package Popups;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

public class DVDPopup extends JPanel{

	private JLabel genre, rating, length, publisher;
	private JTextField genreTBox, ratingTBox, lengthTBox, publisherTBox;
	private JButton add;

	public DVDPopup()
	{
		
		this.setName("dvd");
		this.setLayout(new BorderLayout());
		this.setSize(240, 200);
		
		JPanel details = new JPanel(new GridLayout(0,2));
		details.setBorder(new TitledBorder("DVD"));
		this.add(details, BorderLayout.NORTH);
		
		genre = new JLabel("Genre");
		details.add(genre);
		genreTBox = new JTextField();
		genreTBox.setPreferredSize(new Dimension(10,20));
		details.add(genreTBox);
		
		rating = new JLabel("Rating:");
		details.add(rating);
		ratingTBox = new JTextField();
		details.add(ratingTBox);
		
		length = new JLabel("Length");
		details.add(length);
		lengthTBox = new JTextField();
		details.add(lengthTBox);
		
		publisher = new JLabel("Publisher");
		details.add(publisher);
		publisherTBox = new JTextField();
		details.add(publisherTBox);
		
		JPanel bottom = new JPanel(new FlowLayout());
		this.add(bottom, BorderLayout.SOUTH);
		
		add = new JButton("Add");
		add.setPreferredSize(new Dimension(60,30));
		bottom.add(add);
		
		
		this.setVisible(true);
		
	}
}
