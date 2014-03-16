package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class DVDPopup extends JPanel{

	
	
	private JPanel cd;
	private JLabel name, address, phone, email;
	private JTextField nameTBox, addressTBox, phoneTBox, emailTBox;
	private JButton ok;
	
	private JDialog addD;
	
	
	
	public DVDPopup()
	{
		
		this.setLayout(new BorderLayout());
		

		JPanel top = new JPanel(new GridLayout(0,2));
		top.setBorder(new TitledBorder("DVD"));
		this.add(top, BorderLayout.NORTH);
		
		name = new JLabel("bob:");
		top.add(name);
		nameTBox = new JTextField(5);
		nameTBox.setPreferredSize(new Dimension(200,30));
		top.add(nameTBox);
		
		address = new JLabel("Address:");
		top.add(address);
		addressTBox = new JTextField(5);
		top.add(addressTBox);
		
		phone = new JLabel("Phone:");
		top.add(phone);
		phoneTBox = new JTextField(5);
		top.add(phoneTBox);
		
		email = new JLabel("Email:");
		top.add(email);
		emailTBox = new JTextField(5);
		top.add(emailTBox);
		
		JPanel bottom = new JPanel(new FlowLayout());
		this.add(bottom, BorderLayout.SOUTH);
		
		ok = new JButton("OK");
		ok.setPreferredSize(new Dimension(60,30));
		bottom.add(ok);
		
		
		this.setVisible(true);
		
	}
}
