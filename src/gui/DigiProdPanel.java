package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import Popups.ProdDialog;

public class DigiProdPanel extends JPanel implements ActionListener,
		ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Font font = new Font("Verdana", Font.PLAIN, 20);
	private GridBagConstraints gc = new GridBagConstraints();
	private JButton addProd, editProd, removeProd, exit;
	private JLabel digiProdDetails;
	private JTextField other, supplierID, currentStock, sellPrice, costPrice,
			prodTitle, type, prodId;
	private JPanel editDigiProdBtnsPanel, digiProdDetailsPanel;
	
	private Border space = (Border) BorderFactory.createEmptyBorder(10, 10, 10,10);
	private Border line = (Border) BorderFactory.createLineBorder(Color.black);
	private Border border = BorderFactory.createCompoundBorder(space, line);

	private ImageIcon close;
	
	private JRadioButton cd = new JRadioButton("CD");
	private JRadioButton dvd = new JRadioButton("DVD");
	private JRadioButton game = new JRadioButton("Game");
	private JRadioButton[] digiProdRadioBtns = new JRadioButton[] { cd, dvd, game };
	private JFrame frame;
	
	
	public DigiProdPanel(JFrame frame) {

		
		this.frame = frame;
		
		this.setLayout(new BorderLayout());
		
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout());
		close = new ImageIcon("src/resources/kioskFiles/images/close.png");
		exit = new JButton("Close",close);
		exit.setBackground(new Color(238,238,238));
		exit.setPreferredSize(new Dimension(100,50));
		exit.addActionListener(this);
		exit.setBorder(new EmptyBorder(10, 0, 0, 0));
		
		digiProdDetails = new JLabel("Digital Product");
		digiProdDetails.setBorder(new EmptyBorder(10, 410, 0, 110));
		digiProdDetails.setFont(font);
		
		top.add(digiProdDetails);
		top.add(exit);
		this.add(top, BorderLayout.NORTH);

		// product detail panel, inside the Edit product panel
		digiProdDetailsPanel = new JPanel();
		digiProdDetailsPanel.setLayout(new GridBagLayout());
		digiProdDetailsPanel.setPreferredSize(new Dimension(550, 600));
		digiProdDetailsPanel.setBorder(border);

		// Adding labels and textbox to the product details panel
		JTextField[] digiProdDetailBx = { prodTitle = new JTextField(),
				type = new JTextField(), prodId = new JTextField(),
				costPrice = new JTextField(), sellPrice = new JTextField(),
				currentStock = new JTextField(), supplierID = new JTextField(),
				other = new JTextField(), };

		JLabel[] digiProdDetailLb = { new JLabel(" Product title"),
				new JLabel(" Type"), new JLabel(" Product ID"),
				new JLabel(" Cost price"), new JLabel(" Selling price"),
				new JLabel(" Current stock"), new JLabel(" Supplier ID"),
				new JLabel(" Other") };

		ButtonGroup digiGroup = new ButtonGroup();
		digiGroup.add(cd);
		digiGroup.add(dvd);
		digiGroup.add(game);

		for (int i = 0; i < digiProdDetailLb.length; i++) {
			gc.gridx = 0;
			gc.gridy = i;
			gc.gridwidth = 1;
			gc.gridheight = 1;
			gc.weighty = 0.1;
			gc.weightx = 10.0;
			gc.anchor = GridBagConstraints.WEST;
			digiProdDetailLb[i].setFont(font);
			digiProdDetailsPanel.add(digiProdDetailLb[i], gc);

			// Adds the radio buttons
			if (i == 1) {
				int count = 0;
				while (count < 3) {

					for (int j = 0; j < digiProdRadioBtns.length; j++) {
						gc.gridx = j + 1;
						gc.gridy = 1;
						gc.gridwidth = 1;
						gc.gridheight = 1;
						gc.weighty = 0.1;
						gc.weightx = 10.0;
						digiProdRadioBtns[j].setFont(font);
						digiProdDetailsPanel.add(digiProdRadioBtns[j], gc);
						digiProdRadioBtns[j].addItemListener(this);
						count++;
					}
				}
			}
			if (i == 1) {

			} else {
				gc.gridx = 1;
				gc.gridy = i;
				gc.gridwidth = 1;
				gc.gridheight = 1;
				gc.weighty = 0.2;
				gc.weightx = 10.0;
				gc.gridwidth = 3;
				digiProdDetailBx[i].setPreferredSize(new Dimension(300, 30));
				digiProdDetailsPanel.add(digiProdDetailBx[i], gc);
			}
		}

		this.add(digiProdDetailsPanel, BorderLayout.EAST);

		// button panel inside edit product panel
		editDigiProdBtnsPanel = new JPanel();
		editDigiProdBtnsPanel.setLayout(new GridBagLayout());
		
		editDigiProdBtnsPanel.setPreferredSize(new Dimension(250, 50));

		// Adding buttons to the button panel inside the edit product panel
		JButton[] editDigiProdBtnsArray = {
				addProd = new JButton("Add Product"),
				removeProd = new JButton("Remove Product"),
				editProd = new JButton("Update Product"),

		};

		for (int i = 0; i < editDigiProdBtnsArray.length; i++) {
			gc.gridx = 0;
			gc.gridy = i;
			gc.gridwidth = 1;
			gc.gridheight = 1;
			gc.weighty = 0.0;
			gc.weightx = 0.0;
			gc.insets = new Insets(10, 0, 0, 0);
			editDigiProdBtnsArray[i].setIcon(new ImageIcon("src/resources/blueButton.png"));
			editDigiProdBtnsArray[i].setFont(new Font("sansserif", Font.BOLD,16));
			editDigiProdBtnsArray[i].setPreferredSize(new Dimension(180, 50));
			editDigiProdBtnsArray[i].setHorizontalTextPosition(JButton.CENTER);
			editDigiProdBtnsArray[i].setVerticalTextPosition(JButton.CENTER);
			editDigiProdBtnsArray[i].addActionListener(this);
			
			editDigiProdBtnsPanel.add(editDigiProdBtnsArray[i], gc);
		}
		this.add(editDigiProdBtnsPanel, BorderLayout.WEST);

	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == exit)
		{
			frame.setVisible(false); 
			frame.dispose();
		}
	}

	public void itemStateChanged(ItemEvent e) {

		String[] digiPopup = { "cd", "dvd", "game" };

		for (int i = 0; i < digiProdRadioBtns.length; i++) {
			if (digiProdRadioBtns[i].isSelected()) {
				
				ProdDialog a = new	ProdDialog(digiPopup[i], frame);

			}
		}
	}
}
