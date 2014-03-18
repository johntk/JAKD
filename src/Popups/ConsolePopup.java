package Popups;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;


public class ConsolePopup extends JPanel {

	private JLabel hddSize, wifi, controllers;
	private JTextField hddSizeTBox, wifiTBox, controllersTBox;
	private JButton add;
//	private Font font = new Font("Verdana", Font.PLAIN, 20);
//	private GridBagConstraints gc = new GridBagConstraints();

	public ConsolePopup(String type) {

		this.setSize(240, 200);
		this.setName("console");
		this.setLayout(new BorderLayout());
		JPanel details = new JPanel(new GridLayout(0,2));
		details.setBorder(new TitledBorder("Console"));
		this.add(details, BorderLayout.NORTH);

/*		JTextField[] digiProdDetailBx = 
			{ genreTBox = new JTextField(),
				ratingTBox = new JTextField(), 
				lengthTBox = new JTextField(),
				publisherTBox = new JTextField() };

		JLabel[] digiProdDetailLb = { 
				new JLabel(" Genre"),
				new JLabel(" Rating"), 
				new JLabel(" Length"),
				new JLabel(" Publisher"), };

		for (int i = 0; i < digiProdDetailLb.length; i++) {

			gc.gridx = 0;
			gc.gridy = i;
			gc.gridwidth = 1;
			gc.gridheight = 1;
			gc.weighty = 0.0;
			gc.weightx = 2.0;
			gc.anchor = GridBagConstraints.WEST;

			digiProdDetailLb[i].setFont(font);
			details.add(digiProdDetailLb[i], gc);

			gc.gridx = 1;
			gc.gridy = i;
			gc.gridwidth = 1;
			gc.gridheight = 1;
			gc.weighty = 0.0;
			gc.weightx = 2.0;
			gc.anchor = GridBagConstraints.WEST;
			digiProdDetailBx[i].setFont(font);
			digiProdDetailBx[i].setPreferredSize(new Dimension(100, 20));
			details.add(digiProdDetailBx[i], gc);
		}
		;
*/
		 hddSize = new JLabel("HDD Size");
		 details.add(hddSize);
		 hddSizeTBox = new JTextField(5);
		 hddSizeTBox.setPreferredSize(new Dimension(50,20));
		 details.add(hddSizeTBox);
		
		 wifi = new JLabel("Wifi:");
		 details.add(wifi);
		 wifiTBox = new JTextField(5);
		 details.add(wifiTBox);
		
		 controllers = new JLabel("Controllers");
		 details.add(controllers);
		 controllersTBox = new JTextField(5);
		 details.add(controllersTBox);
		
		
		 JPanel bottom = new JPanel(new FlowLayout());
		 
		 add = new JButton("Add");
		 add.setPreferredSize(new Dimension(60,30));
		 bottom.add(add);
		 this.add(bottom, BorderLayout.SOUTH);

		this.setVisible(true);

	}

}
