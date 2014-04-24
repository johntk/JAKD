package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

import operations.ProdOperations;
import model.CD;
import model.DigiProduct;
import model.DigiProductList;
import model.Employee;
import model.Song;
import Popups.ProdDialog;

public class DigiProdPanel extends JPanel implements ActionListener, ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Font font = new Font("Verdana", Font.PLAIN, 20);
	private GridBagConstraints gc = new GridBagConstraints();
	
	private JPanel prodBtnsPanel, prodDetailsPanel, newProdBtnsPanel;
	private JButton addProd, editProd, removeProd, exit, previous, searchProd, next, updateProd, updateBtn, back;
	private JLabel prodDetails, detailsLB, titleLB, typeLB, idLB, cPriceLB, sPriceLB, 
	stockLB, label1, label2, label3, label4;
	private JTextField details, prodTitle, type, prodId, sellPrice, costPrice,
	currentStock, bx1, bx2, bx3, bx4;
	private ProdDialog a;
	
	private ArrayList<Song> slist = new ArrayList<Song>();
	
	private Border space = (Border) BorderFactory.createEmptyBorder(10, 10, 10,10);
	private Border line = (Border) BorderFactory.createLineBorder(Color.black);
	private Border border = BorderFactory.createCompoundBorder(space, line);

	private Color  cl2, cl3;
	private int size = 0;
	private int counter = 0;
	private String prodType;
	private boolean go = false;
	private boolean newProd = false;
	
	private JCheckBox cdCB =  new JCheckBox();
	private JRadioButton cd = new JRadioButton("CD");
	private JRadioButton dvd = new JRadioButton("DVD");
	private JRadioButton game = new JRadioButton("Game");

	private JRadioButton[] digiProdRadioBtns = new JRadioButton[] { cd, dvd,game };

	JTextField[] digiProdDetailBx = { prodTitle = new JTextField(),
			type = new JTextField(), details = new JTextField(),prodId = new JTextField(),
			costPrice = new JTextField(), sellPrice = new JTextField(),
			currentStock = new JTextField(),bx3 = new JTextField(), 
			bx4 = new JTextField(), bx1 = new JTextField(), bx2 = new JTextField()
			 };
	//Remove label name when finished
	JLabel[] digiProdDetailLb = { titleLB = new JLabel(" Product title:"),
			typeLB = new JLabel(" Type:"), detailsLB = new JLabel(" Songs:"), idLB = new JLabel(" Product ID:"),
			cPriceLB = new JLabel(" Cost price:"), sPriceLB = new JLabel(" Selling price:"),
			stockLB = new JLabel(" Current stock:"), label1 = new JLabel(" Age Rating:"),
			label2 = new JLabel(" Genre:"), label3 = new JLabel(" Publisher:"), label4 = new JLabel(" length:") };
	
	private JFrame frame;
	private ProdOperations prodOpertaion;
	private DigiProductList  digiProductList;
	private DigiProdPanel  digiPanel;
	
	public DigiProdPanel(JFrame frame, String prodType, ProdOperations po, DigiProductList pl) {

		
		this.frame = frame;
		this.setLayout(new BorderLayout());
		this.digiProductList = pl;
		this.prodOpertaion = po;
		cl2 = new Color(75,255,250);
		cl3 = new Color(255,230,0);
		
		prodDetails = new JLabel("Digital Product");
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout());
		prodDetails.setBorder(new EmptyBorder(10, 410, 0, 110));
		prodDetails.setFont(font);
		top.add(prodDetails);
		
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
							cdCB.setFont(font);
							prodDetailsPanel.add(cdCB, gc);
							cdCB.addItemListener(this);
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
				if(digiProdDetailLb[i].getText().equals(" Product ID:"))
				{
					digiProdDetailBx[i].setBackground(cl3);
				}
				else
				{
					digiProdDetailBx[i].setBackground(cl2);
				}
				
				prodDetailsPanel.add(digiProdDetailBx[i], gc);
			}
		}

		this.add(prodDetailsPanel, BorderLayout.EAST);

		// button panels inside edit prod panel
		prodBtnsPanel = new JPanel();
		prodBtnsPanel.setLayout(new GridBagLayout());
		prodBtnsPanel.setPreferredSize(new Dimension(250, 50));

		// Adding buttons to the button panel inside the edit prod panel
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
		
		newProdBtnsPanel = new JPanel();
		newProdBtnsPanel.setLayout(new GridBagLayout());
		newProdBtnsPanel.setPreferredSize(new Dimension(250, 50));

		JButton[] addUserBtnsArray = { updateBtn = new JButton(""),
				back = new JButton("Back") };

		for (int i = 0; i < addUserBtnsArray.length; i++) {
			gc.gridx = 0;
			gc.gridy = i;
			gc.gridwidth = 1;
			gc.gridheight = 1;
			gc.weighty = 0.0;
			gc.weightx = 0.0;
			gc.insets = new Insets(10, 0, 0, 0);
			addUserBtnsArray[i].setPreferredSize(new Dimension(150, 40));
			addUserBtnsArray[i].setIcon(new ImageIcon("src/resources/blueButton.png"));
			addUserBtnsArray[i].setFont(new Font("sansserif", Font.BOLD, 16));
			addUserBtnsArray[i].setHorizontalTextPosition(JButton.CENTER);
			addUserBtnsArray[i].setVerticalTextPosition(JButton.CENTER);
			addUserBtnsArray[i].addActionListener(this);
			newProdBtnsPanel.add(addUserBtnsArray[i], gc);
		}

		
		setFirst();
	}
	
	public void setFirst() {
		int pos = 0;
		counter = 0;
		DigiProduct p = digiProductList.getProduct(pos);
		this.displayProduct(p);
	}

	
	public void setEditableOn() {
		for (int i = 0; i < digiProdDetailBx.length; i++)
			if (digiProdDetailLb[i].getText() !=" Product ID:")
			{
				digiProdDetailBx[i].setEditable(true);
			}
	}

	public void setEditableOff() {
		for (int i = 0; i < digiProdDetailBx.length; i++)
			digiProdDetailBx[i].setEditable(false);
	}
	
	
	public void addNew() {
		
		if (slist.size() > 0) {
			for (int i = slist.size() - 1; i >= 0; i--) {
				slist.remove(i);
			}
		}
		prodDetails.setText("Enter New Details");
		prodDetails.setBorder(new EmptyBorder(10, 390, 0, 110));
		updateBtn.setText("Add New Prod");
		for (int i = 0; i < digiProdDetailBx.length; i++) {
			prodId.setText(String.valueOf(prodOpertaion.getId()));
			prodId.setEditable(false);
			if (i != 3) {
				digiProdDetailBx[i].setText("");
				digiProdDetailBx[i].setEditable(true);
			}
		}
		cd.setVisible(true);
		cd.setEnabled(true);
		dvd.setEnabled(true);
		game.setEnabled(true);
		prodBtnsPanel.setVisible(false);
		this.add(newProdBtnsPanel);
	}

	public DigiProduct newProduct() {
		
		DigiProduct p =null;
		if(cd.isSelected())
		{
			
			CD c = new CD(slist);		
			p = new DigiProduct(prodId.getText(),
			"",
			"",
			"",
			"CD",
			prodTitle.getText(),
			Double.parseDouble(costPrice.getText()),
			Double.parseDouble(sellPrice.getText()),
			Integer.parseInt(currentStock.getText()),
			bx3.getText(),
			bx4.getText(),
			bx1.getText(),
			Double.parseDouble(bx2.getText()),
			"p",
			c
			);
		}
		else if(dvd.isSelected())
		{
			
			p = new DigiProduct(prodId.getText(),
					"",
					"",
					"DVD",
					prodTitle.getText(),
					Double.parseDouble(costPrice.getText()),
					Double.parseDouble(sellPrice.getText()),
					Integer.parseInt(currentStock.getText()),
					bx3.getText(),
					bx4.getText(),
					bx1.getText(),
					Double.parseDouble(bx2.getText())
					);
		}
		else if(game.isSelected())
		{
			
			p = new DigiProduct(prodId.getText(),
					"",
					"",
					"GAME",
					prodTitle.getText(),
					Double.parseDouble(costPrice.getText()),
					Double.parseDouble(sellPrice.getText()),
					Integer.parseInt(currentStock.getText()),
					bx3.getText(),
					bx4.getText(),
					bx1.getText(),
					bx2.getText()
					);
		}
		return p;
	}
	
	
	public void newAlbum(ArrayList<Song> slist)
	{
		this.slist = slist;
	}
	
	public void updateProd() {
		updateBtn.setText("Update Product");
		prodDetails.setText("Update Product Details");
		prodDetails.setBorder(new EmptyBorder(10, 372, 0, 110));
		prodBtnsPanel.setVisible(false);
		this.add(newProdBtnsPanel);
	}
	
	
	public void updateProduct() {
		
		if(cd.isSelected())
		{
		
		CD c = digiProductList.getProduct(counter).getAlbum();		
		DigiProduct p = new DigiProduct(prodId.getText(),
		digiProductList.getProduct(counter).getDigi_id(),
		digiProductList.getProduct(counter).getCd_id(),
		digiProductList.getProduct(counter).getArtist_id(),
		"CD",
		prodTitle.getText(),
		Double.parseDouble(costPrice.getText()),
		Double.parseDouble(sellPrice.getText()),
		Integer.parseInt(currentStock.getText()),
		bx3.getText(),
		bx4.getText(),
		bx1.getText(),
		Double.parseDouble(bx2.getText()),
		digiProductList.getProduct(counter).getArtist(),
		c
		);
		
		digiProductList.updateProduct(p);
		JOptionPane.showMessageDialog(null, "CD " + prodTitle.getText()
				+ " Updated");
		prodDetails.setText("Product Details");
		}
		else if(dvd.isSelected())
		{
			
			DigiProduct p = new DigiProduct(prodId.getText(),
					digiProductList.getProduct(counter).getDigi_id(),
					digiProductList.getProduct(counter).getDvd_id(),
					"DVD",
					prodTitle.getText(),
					Double.parseDouble(costPrice.getText()),
					Double.parseDouble(sellPrice.getText()),
					Integer.parseInt(currentStock.getText()),
					bx3.getText(),
					bx4.getText(),
					bx1.getText(),
					Double.parseDouble(bx2.getText())
					);
					
					digiProductList.updateProduct(p);
					JOptionPane.showMessageDialog(null, "DVD " + prodTitle.getText()
							+ " Updated");
					prodDetails.setText("Product Details");
		}
		else if(game.isSelected())
		{
		
			DigiProduct p = new DigiProduct(prodId.getText(),
					digiProductList.getProduct(counter).getDigi_id(),
					digiProductList.getProduct(counter).getGame_id(),
					"GAME",
					prodTitle.getText(),
					Double.parseDouble(costPrice.getText()),
					Double.parseDouble(sellPrice.getText()),
					Integer.parseInt(currentStock.getText()),
					bx3.getText(),
					bx4.getText(),
					bx1.getText(),
					bx2.getText()
					);
					
					digiProductList.updateProduct(p);
					JOptionPane.showMessageDialog(null, "Game " + prodTitle.getText()
							+ " Updated");
					prodDetails.setText("Product Details");
		}
	}
	
	public void searchProd() {
		String prodID = JOptionPane.showInputDialog(null,
				"Enter the Product ID: ",
				"", JOptionPane.QUESTION_MESSAGE);
		int index = digiProductList.findProd(prodID);
		if (index != -1) 
			displayProduct(digiProductList.getProd(index));
		 else 
			JOptionPane.showMessageDialog(null, " Product not found");
	}
	
	public void deleteProd() {
		int numberOfDeleted = digiProductList.removeProd(prodId.getText());
		JOptionPane.showMessageDialog(null, numberOfDeleted
				+ " Record(s) deleted.");
		setFirst();
	}
	
	public void newAlbum()
	{
		if(newProd == true){
			try{
			if(slist.size() > 0){
				size = Integer.parseInt(JOptionPane.showInputDialog(null, 
						"Songs you already entered will be delted!\nHow many songs on this album?: "));
				go = true;
				if(size > 19){
					JOptionPane.showMessageDialog(null, "Album cannot have more than 19 songs: ");
					go = false;
				}
				else if(size == 0){
					JOptionPane.showMessageDialog(null, "Album cannot have 0 songs: ");
					go = false;
				}
			}
			else{
				size = Integer.parseInt(JOptionPane.showInputDialog(null, "How many songs on this album?: "));
				go = true;
				if(size > 19){
					JOptionPane.showMessageDialog(null, "Album cannot have more than 19 songs: ");
					go = false;
				}
				else if(size == 0){
					JOptionPane.showMessageDialog(null, "Album cannot have 0 songs: ");
					go = false;
				}
			}
			}
			catch(NumberFormatException ea){
				go = false;
			}	
		}
		else{
			go=true;
			size = 0;
		}		
		if(go==true){
			ProdDialog a = new ProdDialog(frame, digiProductList.getProduct(counter), digiProductList, this, size);
		}
	}
	
	public DigiProduct displayProduct(DigiProduct p) {
		
		setEditableOff();
		
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
		
		return p;
	}

	public void actionPerformed(ActionEvent e) {

		int totalContacts = digiProductList.getNumProduct();
		
		if (e.getSource() == exit) {
			System.exit(0);
		}
		
		else if (e.getSource().equals(next)) {
			if ((counter + 1) < totalContacts) {
				counter++;
				this.displayProduct(digiProductList.getProduct(counter));
			}
		} 
		else if (e.getSource().equals(previous)) {
			if ((counter - 1) >= 0) {
				counter--;
				this.displayProduct(digiProductList.getProduct(counter));
			}
		}
		else if (e.getSource().equals(addProd)) {
			digiProdDetailBx[0].requestFocus();
			digiProdDetailBx[0].getCaret().setBlinkRate(600);
			newProdBtnsPanel.setVisible(true);
			addNew();
			newProd = true;
		} 
		else if (e.getSource().equals(updateBtn)
				&& updateBtn.getText().equals("Add New Prod")) {
			
			if(cd.isSelected() && slist.size() == 0)
			{
				JOptionPane.showMessageDialog(null, "You must enter songs for a CD product\nClick the Song check box to enter songs ");
			}
			else
			{
				try{
			prodOpertaion.addNewProd(newProduct());
			digiProductList.addProduct();
			prodBtnsPanel.setVisible(true);
			newProdBtnsPanel.setVisible(false);
			JOptionPane.showMessageDialog(null, prodTitle.getText() + " Saved");
			prodBtnsPanel.setVisible(true);
			setEditableOff();
			setFirst();
			newProd = false;
				}
				catch(NumberFormatException ex)
				{
					JOptionPane.showMessageDialog(null, "Cost price:\nSelling price:\nCurrent stock:"
							+ "\nLength:\nMust contain numbers."  );
				}
			}
		} 
		else if (e.getSource().equals(back)) {
			prodDetails.setText("Product Details");
			prodDetails.setBorder(new EmptyBorder(10, 450, 0, 110));
			prodBtnsPanel.setVisible(true);
			newProdBtnsPanel.setVisible(false);
			newProdBtnsPanel.repaint();
			setEditableOff();
			setFirst();
			newProd = false;
		} 
		else if (e.getSource().equals(updateProd)) {
			digiProdDetailBx[0].requestFocus();
			digiProdDetailBx[0].getCaret().setBlinkRate(600);
			newProdBtnsPanel.setVisible(true);
			setEditableOn();
			updateProd();
		} 
		else if (e.getSource().equals(updateBtn)
				&& updateBtn.getText().equals("Update Product")) {
			updateProduct();
			prodBtnsPanel.setVisible(true);
			newProdBtnsPanel.setVisible(false);
			prodBtnsPanel.repaint();
			setEditableOff();
		}  
		else if (e.getSource().equals(searchProd)) {
			searchProd();
		}
		else if (e.getSource().equals(removeProd)) {
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to remove\n" + "        "
					+ prodTitle.getText() + " from the system",
					"Warning", dialogButton);
			if (dialogResult == JOptionPane.YES_OPTION) {
				deleteProd();
				setFirst();
			}
		}
	}

	public void itemStateChanged(ItemEvent e) {

		
			if (cdCB.isSelected()) {
				newAlbum();
			} 
			
			if(cd.isSelected()){
				label4.setText(" Length:");
				label3.setText(" Label:");
				cdCB.setVisible(true);
				detailsLB.setText(" Songs");
			}
			else if(dvd.isSelected()){
				label3.setText(" Studio:");
				label4.setText(" Length:");
				cdCB.setVisible(false);
				detailsLB.setText("");
			}
			else if(game.isSelected()){
				label3.setText(" Studio:");
				label4.setText(" Platform:");
				cdCB.setVisible(false);
				detailsLB.setText("");
			}
	}
}
