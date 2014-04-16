package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import db.EmpOperations;
import db.ProdOperations;
import model.Employee;
import model.EmployeeList;
import model.Product;
import model.ProductList;
import Popups.ProdDialog;

public class ProdPanel extends JPanel implements ActionListener, ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Font font = new Font("Verdana", Font.PLAIN, 20);
	private GridBagConstraints gc = new GridBagConstraints();
	private JButton addProd, editProd, removeProd, exit, previous, searchProd,
			next, updateProd;
	private JLabel prodDetails, detailsLB, titleLB, typeLB, idLB, cPriceLB,  sPriceLB, stockLB,  ratingLB, genreLB, pubLB, lengthLB;
	private JTextField ageRating, type, genre, currentStock, sellPrice, costPrice,
			prodTitle, prodId, publisher, length, details;
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
	private JRadioButton phono = new JRadioButton("Headphones");
	private JRadioButton console = new JRadioButton("Console");
	private JRadioButton dock = new JRadioButton("Dock");
	private JRadioButton[] digiProdRadioBtns = new JRadioButton[] { cd, dvd,game };
	private JRadioButton[] elecProdRadioBtns = new JRadioButton[] { phono,console, dock };
	
	private JFrame frame;
	private int counter = 0;
	
	JCheckBox[] digiProdCheck = { cdCB = new JCheckBox(),
	dvdCB = new JCheckBox(), gameCB = new JCheckBox()
	};
	
	
	JTextField[] digiProdDetailBx = { prodTitle = new JTextField(),
			type = new JTextField(), details = new JTextField(),prodId = new JTextField(),
			costPrice = new JTextField(), sellPrice = new JTextField(),
			currentStock = new JTextField(),ageRating = new JTextField(), 
			genre = new JTextField(), publisher = new JTextField(), length = new JTextField()
			 };
	//Remove label name when finished
	JLabel[] digiProdDetailLb = { titleLB = new JLabel(" Product title"),
			typeLB = new JLabel(" Type"), detailsLB = new JLabel(" Details"), idLB = new JLabel(" Product ID"),
			cPriceLB = new JLabel(" Cost price"), sPriceLB = new JLabel(" Selling price"),
			stockLB = new JLabel(" Current stock"), ratingLB = new JLabel(" Age Rating"),
			genreLB = new JLabel(" Genre"), pubLB = new JLabel(" Publisher"), lengthLB = new JLabel(" length") };
	
	private ProdOperations prodOpertaion;
	private ProductList  productList;
	
	public ProdPanel(JFrame frame, String prodType, ProdOperations po, ProductList pl) {

		
		this.frame = frame;

		this.setLayout(new BorderLayout());
		this.productList = pl;
		this.prodOpertaion = po;
		
		if (prodType.equals("digi")) {
			prodDetails = new JLabel("Digital Product");
			cd.setText("CD");
			dvd.setText("DVD");
			game.setText("Game");
		} else {
			cd.setText("Headphones");
			dvd.setText("Console");
			game.setText("Dock");
			prodDetails = new JLabel("Electric Product");
		}

		
		
		
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

		// Adding labels and textbox to the product details panel
		

		
//		JLabel[] digiProdDetailLb = { new JLabel(" Product title"),
//				new JLabel(" Type"), new JLabel(" Product ID"),
//				new JLabel(" Cost price"), new JLabel(" Selling price"),
//				new JLabel(" Current stock"), new JLabel(" Age Rating"),
//				new JLabel(" Genre"), new JLabel(" Publisher"), new JLabel(" length") };
		
		
		ButtonGroup digiGroup = new ButtonGroup();
		digiGroup.add(cd);
		digiGroup.add(dvd);
		digiGroup.add(game);

		ButtonGroup elecGroup = new ButtonGroup();
		elecGroup.add(phono);
		elecGroup.add(console);
		elecGroup.add(dock);

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
						} else {
							elecProdRadioBtns[j].setFont(font);
							elecProdRadioBtns[j].addItemListener(this);
							prodDetailsPanel.add(elecProdRadioBtns[j], gc);
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
						} else {
							elecProdRadioBtns[j].setFont(font);
							elecProdRadioBtns[j].addItemListener(this);
							prodDetailsPanel.add(elecProdRadioBtns[j], gc);
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
		Product p = productList.getProduct(pos);
		this.displayProduct(p);
	}
	
	public Product displayProduct(Product p) {
		prodId.setText(p.getProd_id());
		prodTitle.setText(p.getAlbumName());
		costPrice.setText(Double.toString(p.getCostPrice()));
		sellPrice.setText(Double.toString(p.getSellPrice()));
		currentStock.setText(Integer.toString(p.getCurrent_stock()));
		ageRating.setText(p.getAge_rating());
		genre.setText(p.getGenre());
		publisher.setText(p.getPublisher());
		length.setText(Double.toString(p.getLength()));
		
		
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
		String[] elecPopup = { "phono", "console", "dock" };
		for (int i = 0; i < digiProdRadioBtns.length; i++) {
			if (digiProdCheck[i].isSelected()) {

				if (digiPopup[i].equals("game")) {
					digiProdDetailBx[9].setText("N/A");
					digiProdDetailBx[9].setEditable(false);
				} else {
					digiProdDetailBx[9].setText("");
					digiProdDetailBx[9].setEditable(true);
				}
				ProdDialog a = new ProdDialog(digiPopup[i], frame, productList.getProduct(counter));
			} else if (elecProdRadioBtns[i].isSelected()) {
				ProdDialog a = new ProdDialog(elecPopup[i], frame,  productList.getProduct(counter));
			}
		}
	}
}
