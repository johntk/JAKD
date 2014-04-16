package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import db.ProdOperations;
import model.DigiProduct;
import model.DigiProductList;
import Popups.ProdDialog;

public class DigiProdPanel extends JPanel implements ActionListener, ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Font font = new Font("Verdana", Font.PLAIN, 20);
	private GridBagConstraints gc = new GridBagConstraints();
	private JButton addProd, editProd, removeProd, exit, previous, searchProd,
	next, updateProd;
	private JLabel prodDetails, detailsLB, titleLB, typeLB, idLB, cPriceLB, sPriceLB, 
	stockLB, label1, label2, label3, label4;
	private JTextField details, prodTitle, type, prodId, sellPrice, costPrice,
	currentStock, bx1, bx2, bx3, bx4;
	
	private JPanel prodBtnsPanel, prodDetailsPanel;

	private Border space = (Border) BorderFactory.createEmptyBorder(10, 10, 10,10);
	private Border line = (Border) BorderFactory.createLineBorder(Color.black);
	private Border border = BorderFactory.createCompoundBorder(space, line);

	private ImageIcon close;
	private String prodType;
	private JCheckBox cdCB, dvdCB, gameCB;
	private JRadioButton cd = new JRadioButton("CD");
	private JRadioButton dvd = new JRadioButton("DVD");
	private JRadioButton game = new JRadioButton("Game");

	private JRadioButton[] digiProdRadioBtns = new JRadioButton[] { cd, dvd,game };

	
	private JFrame frame;
	private int counter = 0;
	
	JCheckBox[] digiProdCheck = { cdCB = new JCheckBox(), dvdCB = new JCheckBox(), gameCB = new JCheckBox()};

	
	JTextField[] digiProdDetailBx = { prodTitle = new JTextField(),
			type = new JTextField(), details = new JTextField(),prodId = new JTextField(),
			costPrice = new JTextField(), sellPrice = new JTextField(),
			currentStock = new JTextField(),bx3 = new JTextField(), 
			bx4 = new JTextField(), bx1 = new JTextField(), bx2 = new JTextField()
			 };
	//Remove label name when finished
	JLabel[] digiProdDetailLb = { titleLB = new JLabel(" Product title"),
			typeLB = new JLabel(" Type"), detailsLB = new JLabel(" Songs"), idLB = new JLabel(" Product ID"),
			cPriceLB = new JLabel(" Cost price"), sPriceLB = new JLabel(" Selling price"),
			stockLB = new JLabel(" Current stock"), label1 = new JLabel(" Age Rating"),
			label2 = new JLabel(" Genre"), label3 = new JLabel(" Publisher"), label4 = new JLabel(" length") };
	
	private ProdOperations prodOpertaion;
	private DigiProductList  productList;
	
	public DigiProdPanel(JFrame frame, String prodType, ProdOperations po, DigiProductList pl) {

		
		this.frame = frame;

		this.setLayout(new BorderLayout());
		this.productList = pl;
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
			prodDetailsPanel.add(digiProdDetailLb[i], gc);

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
						if (prodType.equals("digi")) {
							digiProdRadioBtns[j].setFont(font);
							prodDetailsPanel.add(digiProdRadioBtns[j], gc);
							digiProdRadioBtns[j].addItemListener(this);
						}
						count++;
					}
				}
			}
			if (i == 2 ) {
				int count = 0;
				while (count < 1) {
					
					for (int j = 0; j < 1; j++) {
						gc.gridx = j + 1;
						gc.gridy = 2;
						gc.gridwidth = 1;
						gc.gridheight = 1;
						gc.weighty = 0.1;
						gc.weightx = 10.0;
						if (prodType.equals("digi")) {
							digiProdCheck[j].setFont(font);
							prodDetailsPanel.add(digiProdCheck[j], gc);
							digiProdCheck[j].addItemListener(this);
						} 
						count++;
					}
				}
			}
			if (i == 1 || i == 2) {

			} 
			
			else {
				gc.gridx = 1;
				gc.gridy = i;
				gc.gridwidth = 1;
				gc.gridheight = 1;
				gc.weighty = 0.2;
				gc.weightx = 10.0;
				gc.gridwidth = 3;
				digiProdDetailBx[i].setPreferredSize(new Dimension(300, 30));
				prodDetailsPanel.add(digiProdDetailBx[i], gc);
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
		setFirst();
	}
	
	public void setFirst() {
		int pos = 0;
		counter = 0;
		DigiProduct p = productList.getProduct(pos);
		this.displayProduct(p);
	}
	
	public DigiProduct displayProduct(DigiProduct p) {
		
		if(p.getProd_type().equals("CD"))
		{
		prodId.setText(p.getProd_id());
		prodTitle.setText(p.getAlbumName());
		costPrice.setText(Double.toString(p.getCostPrice()));
		sellPrice.setText(Double.toString(p.getSellPrice()));
		currentStock.setText(Integer.toString(p.getCurrent_stock()));
		bx3.setText(p.getAge_rating());
		bx4.setText(p.getGenre());
		bx1.setText(p.getPublisher());
		bx2.setText(Double.toString(p.getLength()));	
		cd.setSelected(true);
		cdCB.setVisible(true);
		dvd.setEnabled(false);
		cd.setEnabled(true);
		game.setEnabled(false);
		detailsLB.setText(" Songs");
		}
		else if(p.getProd_type().equals("GAME"))
		{
			prodId.setText(p.getProd_id());
			prodTitle.setText(p.getGame_name());
			costPrice.setText(Double.toString(p.getCostPrice()));
			sellPrice.setText(Double.toString(p.getSellPrice()));
			currentStock.setText(Integer.toString(p.getCurrent_stock()));
			bx3.setText(p.getAge_rating());
			bx4.setText(p.getGenre());
			bx1.setText(p.getStudio());
			bx2.setText(p.getPlatform());	
			game.setSelected(true);
			cdCB.setVisible(false);
			dvd.setEnabled(false);
			game.setEnabled(true);
			cd.setEnabled(false);
			detailsLB.setText("");
		}
		else if(p.getProd_type().equals("DVD"))
		{
			prodId.setText(p.getProd_id());
			prodTitle.setText(p.getDvd_name());
			costPrice.setText(Double.toString(p.getCostPrice()));
			sellPrice.setText(Double.toString(p.getSellPrice()));
			currentStock.setText(Integer.toString(p.getCurrent_stock()));
			bx3.setText(p.getAge_rating());
			bx4.setText(p.getGenre());
			bx1.setText(p.getStudio());
			bx2.setText(Double.toString(p.getLength()));	
			dvd.setSelected(true);
			cdCB.setVisible(false);
			detailsLB.setText("");
			dvd.setEnabled(true);
			game.setEnabled(false);
			cd.setEnabled(false);
		}
		else if(p.getProd_type().equals("CONSOLE"))
		{
			
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

		int totalContacts = productList.getNumProduct();
		
		if (e.getSource() == exit) {
			System.exit(0);
		}
		
		else if (e.getSource().equals(next)) {
			if ((counter + 1) < totalContacts) {
				counter++;
				this.displayProduct(productList.getProduct(counter));
			}
		} 
		else if (e.getSource().equals(previous)) {
			if ((counter - 1) >= 0) {
				counter--;
				this.displayProduct(productList.getProduct(counter));
			}
		}
	}

	public void itemStateChanged(ItemEvent e) {

		String[] digiPopup = { "cd", "dvd", "game" };
		for (int i = 0; i < digiProdRadioBtns.length; i++) {
			if (digiProdCheck[i].isSelected()) {

				ProdDialog a = new ProdDialog(digiPopup[i], frame, productList.getProduct(counter));
			} 
			
			if(cd.isSelected())
			{
				
			}
		}
	}
}
