package Popups;


import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class HeadphonePopup extends JPanel{

	private JLabel overEar, microphone, iphoneComaptible;
	private JTextField overEarTBox, microphoneTBox, iphoneComaptibleTBox;
	private JButton add;


	public HeadphonePopup()
	{
		this.setName("phono");
		this.setLayout(new BorderLayout());
		this.setSize(240, 240);
		
		JPanel details = new JPanel(new GridLayout(0,2));
		details.setBorder(new TitledBorder("Headphone"));
		this.add(details, BorderLayout.NORTH);
		
		overEar = new JLabel("Over ear");
		details.add(overEar);
		overEarTBox = new JTextField(5);
		overEarTBox.setPreferredSize(new Dimension(200,30));
		details.add(overEarTBox);
		
		microphone = new JLabel("Rating:");
		details.add(microphone);
		microphoneTBox = new JTextField(5);
		details.add(microphoneTBox);
		
		iphoneComaptible = new JLabel("Length");
		details.add(iphoneComaptible);
		iphoneComaptibleTBox = new JTextField(5);
		details.add(iphoneComaptibleTBox);
		
		JPanel bottom = new JPanel(new FlowLayout());
		this.add(bottom, BorderLayout.SOUTH);
		
		add = new JButton("Add");
		add.setPreferredSize(new Dimension(60,30));
		bottom.add(add);
		
		this.setVisible(true);
	}
	
}
