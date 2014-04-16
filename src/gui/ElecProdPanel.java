package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;


import db.ProdOperations;
import model.ElecProdList;

import model.DigiProduct;
import Popups.ProdDialog;

public class ElecProdPanel extends JPanel implements ActionListener, ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Font font = new Font("Verdana", Font.PLAIN, 20);
	private GridBagConstraints gc = new GridBagConstraints();
	private JButton addProd, editProd, removeProd, exit, previous, searchProd,
			next, updateProd;
	private JLabel prodDetails, manufLB, detailsLB, titleLB, typeLB, idLB, cPriceLB,  sPriceLB, stockLB,  ratingLB, genreLB, pubLB, lengthLB;
	private JTextField ageRating, manuf, type, genre, currentStock, sellPrice, costPrice,
			prodTitle, prodId, publisher, length, details;
	private JPanel prodBtnsPanel, prodDetailsPanel;

	private Border space = (Border) BorderFactory.createEmptyBorder(10, 10, 10,10);
	private Border line = (Border) BorderFactory.createLineBorder(Color.black);
	private Border border = BorderFactory.createCompoundBorder(space, line);

	private ImageIcon close;
	private String prodType;
	private JCheckBox  phonoCB, consoleCB, dockCB;
	private JRadioButton phono = new JRadioButton("Headphones");
	private JRadioButton console = new JRadioButton("Console");
	private JRadioButton dock = new JRadioButton("Dock");
	private JRadioButton[] elecProdRadioBtns = new JRadioButton[] { phono,console, dock };
	
	private JFrame frame;
	private int counter = 0;
	
//	
	JCheckBox[] elecProdCheck = { phonoCB = new JCheckBox(), consoleCB = new JCheckBox(), dockCB = new JCheckBox()};
	
	JTextField[] elecProdDetailBx = { prodTitle = new JTextField(),
			type = new JTextField(), prodId = new JTextField(),
			costPrice = new JTextField(), sellPrice = new JTextField(),
			currentStock = new JTextField(),ageRating = new JTextField(), manuf = new JTextField(),
			genre = new JTextField(), publisher = new JTextField(), length = new JTextField()
			 };
	//Remove label name when finished
	JLabel[] elecProdDetailLb = { titleLB = new JLabel(" Product title"),
			typeLB = new JLabel(" Type"),  idLB = new JLabel(" Product ID"),
			cPriceLB = new JLabel(" Cost price"), sPriceLB = new JLabel(" Selling price"),
			stockLB = new JLabel(" Current stock"), ratingLB = new JLabel(" Over Ear"),
			manufLB = new JLabel(" Manufacturer"),
			genreLB = new JLabel(" Mic"), pubLB = new JLabel(" iPhone Ready"), lengthLB = new JLabel(" Colour") };
	
	private ProdOperations prodOpertaion;
	private ElecProdList  elecProductList;
	
	public ElecProdPanel(JFrame frame, String prodType, ProdOperations po, ElecProdList pl) {

		
		this.frame = frame;

		this.setLayout(new BorderLayout());
		this.elecProductList = pl;
		this.prodOpertaion = po;
			
		prodDetails = new JLabel("Electric Product");
		

		JPanel top = new JPanel();
		top.setLayout(new FlowLayout());
		close = new ImageIcon("src/resources/kioskFiles/images/close.png");
		exit = new JButton("Close", close);
		exit.setBackground(new Color(238, 238, 238));
		exit.setPreferredSize(new Dimension(100, 50));
		exit.addActionListener(this);
		exit.setBorder(new EmptyBorder(10, 0, 0, 0));

		prodDetails.setBorder(new EmptyBorder(10, 410, 0, 110));
		prodDetails.setFont(font);

		top.add(prodDetails);
		top.add(exit);
		this.add(top, BorderLayout.NORTH);

		// product detail panel, inside the Edit product panel
		prodDetailsPanel = new JPanel();
		prodDetailsPanel.setLayout(new GridBagLayout());
		prodDetailsPanel.setPreferredSize(new Dimension(550, 600));
		prodDetailsPanel.setBorder(border);


		ButtonGroup elecGroup = new ButtonGroup();
		elecGroup.add(phono);
		elecGroup.add(console);
		elecGroup.add(dock);

		for (int i = 0; i < elecProdDetailLb.length; i++) {
			gc.gridx = 0;
			gc.gridy = i;
			gc.gridwidth = 1;
			gc.gridheight = 1;
			gc.weighty = 0.1;
			gc.weightx = 10.0;
			gc.anchor = GridBagConstraints.WEST;
			elecProdDetailLb[i].setFont(font);
			prodDetailsPanel.add(elecProdDetailLb[i], gc);

			// Adds the radio buttons
			if (i == 1) {
				int count = 0;
				while (count < 3) {
					
					for (int j = 0; j < elecProdRadioBtns.length; j++) {
						gc.gridx = j + 1;
						gc.gridy = 1;
						gc.gridwidth = 1;
						gc.gridheight = 1;
						gc.weighty = 0.1;
						gc.weightx = 10.0;
						if (prodType.equals("digi")) {
							elecProdRadioBtns[j].setFont(font);
							prodDetailsPanel.add(elecProdRadioBtns[j], gc);
							elecProdRadioBtns[j].addItemListener(this);
						} else {
							elecProdRadioBtns[j].setFont(font);
							elecProdRadioBtns[j].addItemListener(this);
							prodDetailsPanel.add(elecProdRadioBtns[j], gc);
						}
						count++;
					}
				}
			}
			if (i == 1 ) {

			} 
			
			else {
				gc.gridx = 1;
				gc.gridy = i;
				gc.gridwidth = 1;
				gc.gridheight = 1;
				gc.weighty = 0.2;
				gc.weightx = 10.0;
				gc.gridwidth = 3;
				elecProdDetailBx[i].setPreferredSize(new Dimension(300, 30));
				prodDetailsPanel.add(elecProdDetailBx[i], gc);
			}
			
			
		}

		this.add(prodDetailsPanel, BorderLayout.EAST);

		// button panels inside edit user panel
		prodBtnsPanel = new JPanel();
		prodBtnsPanel.setLayout(new GridBagLayout());
		prodBtnsPanel.setPreferredSize(new Dimension(250, 50));

		// Adding buttons to the button panel inside the edit user panel
		JButton[] prodButtons = { searchProd = new JButton("Search Prod"),
				addProd = new JButton("Add Prod"),
				removeProd = new JButton("Remove Prod"),
				updateProd = new JButton("Edit Prod"),
				previous = new JButton("<"), next = new JButton(">")

		};

		for (int i = 0; i < prodButtons.length; i++) {

			if (i < prodButtons.length - 2) {
				gc.gridx = 0;
				gc.gridy = i;
				gc.gridwidth = prodButtons.length;
				gc.gridheight = 1;
				gc.weighty = 0.0;
				gc.weightx = 0.0;
				gc.insets = new Insets(10, 0, 0, 0);
				prodButtons[i].setPreferredSize(new Dimension(150, 40));
			} else {
				gc.gridx = i;
				gc.gridy = prodButtons.length - 2;
				gc.gridwidth = 1;
				if (i == prodButtons.length - 1) {
					gc.insets = new Insets(10, 30, 0, 0);
				}
				prodButtons[i].setPreferredSize(new Dimension(60, 50));
			}

			prodButtons[i].setIcon(new ImageIcon("src/resources/blueButton.png"));
			prodButtons[i].setFont(new Font("sansserif", Font.BOLD, 16));
			prodButtons[i].setHorizontalTextPosition(JButton.CENTER);
			prodButtons[i].setVerticalTextPosition(JButton.CENTER);
			prodButtons[i].addActionListener(this);
			prodBtnsPanel.add(prodButtons[i], gc);

		}

		this.add(prodBtnsPanel, BorderLayout.WEST);
//		setFirst();
	}
	
	public void setFirst() {
		int pos = 0;
		counter = 0;
		DigiProduct p = elecProductList.getProduct(pos);
		this.displayProduct(p);
	}
	
	public DigiProduct displayProduct(DigiProduct p) {
		
		
		if(p.getProd_type().equals("CONSOLE"))
		{
//			prodId.setText(p.getProd_id());
//			prodTitle.setText(p.getAlbumName());
//			costPrice.setText(Double.toString(p.getCostPrice()));
//			sellPrice.setText(Double.toString(p.getSellPrice()));
//			currentStock.setText(Integer.toString(p.getCurrent_stock()));
//			ageRating.setText(p.getAge_rating());
//			genre.setText(p.getGenre());
//			publisher.setText(p.getPublisher());
//			length.setText(Double.toString(p.getLength()));	
//			cd.setSelected(true);
//			cdCB.setVisible(true);
		}
		else if(p.getProd_type().equals("HEADPHONES"))
		{
			
		}
		else if(p.getProd_type().equals("SOUNDDOCK"))
		{
			
		}
		return p;
	}

	public void actionPerformed(ActionEvent e) {

		int totalContacts = elecProductList.getNumProduct();
		
		if (e.getSource() == exit) {
			System.exit(0);
		}
		
		else if (e.getSource().equals(next)) {
			if ((counter + 1) < totalContacts) {
				counter++;
				this.displayProduct(elecProductList.getProduct(counter));
			}
		} 
		else if (e.getSource().equals(previous)) {
			if ((counter - 1) >= 0) {
				counter--;
				this.displayProduct(elecProductList.getProduct(counter));
			}
		}
	}

	public void itemStateChanged(ItemEvent e) {

		String[] digiPopup = { "cd", "dvd", "game" };
		String[] elecPopup = { "phono", "console", "dock" };
		for (int i = 0; i < elecProdRadioBtns.length; i++) {
			if (elecProdCheck[i].isSelected()) {

				if (digiPopup[i].equals("game")) {
					elecProdDetailBx[9].setText("N/A");
					elecProdDetailBx[9].setEditable(false);
				} else {
					elecProdDetailBx[9].setText("");
					elecProdDetailBx[9].setEditable(true);
				}
				ProdDialog a = new ProdDialog(digiPopup[i], frame, elecProductList.getProduct(counter));
			} else if (elecProdRadioBtns[i].isSelected()) {
				
			}
		}
	}
}
