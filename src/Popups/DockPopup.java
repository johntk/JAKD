package Popups;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class DockPopup extends JPanel{

	private JLabel wirelless, powerType, digitalRadio;
	private JTextField wirellessTBox, powerTypeTBox, digitalRadioTBox;
	private JButton add;


	public DockPopup()
	{
		
		this.setLayout(new BorderLayout());
		this.setSize(240, 240);
		this.setName("dock");

		JPanel details = new JPanel(new GridLayout(0,2));
		details.setBorder(new TitledBorder("Dock"));
		this.add(details, BorderLayout.NORTH);
		
		wirelless = new JLabel("Genre");
		details.add(wirelless);
		wirellessTBox = new JTextField(5);
		wirellessTBox.setPreferredSize(new Dimension(100,30));
		details.add(wirellessTBox);
		
		powerType = new JLabel("Rating:");
		details.add(powerType);
		powerTypeTBox = new JTextField(5);
		details.add(powerTypeTBox);
		
		digitalRadio = new JLabel("Length");
		details.add(digitalRadio);
		digitalRadioTBox = new JTextField(5);
		details.add(digitalRadioTBox);
		
		JPanel bottom = new JPanel(new FlowLayout());
		this.add(bottom, BorderLayout.SOUTH);
		
		add = new JButton("Add");
		add.setPreferredSize(new Dimension(60,30));
		bottom.add(add);
		
		this.setVisible(true);
		
		
	}
}
