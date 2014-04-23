package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import db.ProdOperations;
import model.CD;
import model.DigiProduct;
import model.ElecProdList;
import model.ElecProduct;


public class ElecProdPanel extends JPanel implements ActionListener, ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Font font = new Font("Verdana", Font.PLAIN, 20);
	private GridBagConstraints gc = new GridBagConstraints();
	
	private JPanel prodBtnsPanel, prodDetailsPanel, newProdBtnsPanel;
	private JButton addProd, editProd, removeProd, exit, previous, searchProd, next, updateProd, updateBtn, back;
	
	private JLabel prodDetails, detailsLB, titleLB, typeLB, idLB, cPriceLB, sPriceLB, stockLB,  
	manufLB, label1, label2, label3, colour;
	private JTextField  prodTitle, type, prodId, sellPrice, costPrice, currentStock,
	manuf,  bx1, bx2, bx3, colourbx ;
	
	private Border space = (Border) BorderFactory.createEmptyBorder(10, 10, 10,10);
	private Border line = (Border) BorderFactory.createLineBorder(Color.black);
	private Border border = BorderFactory.createCompoundBorder(space, line);

	private ImageIcon close;
	private Color  cl2, cl3;

	private JRadioButton phono = new JRadioButton("Headphones");
	private JRadioButton console = new JRadioButton("Console");
	private JRadioButton dock = new JRadioButton("Dock");
	private JRadioButton[] elecProdRadioBtns = new JRadioButton[] { phono,console, dock };
	
	private int counter = 0;
	private boolean newProd = false;
	
	JTextField[] elecProdDetailBx = { prodTitle = new JTextField(),
			type = new JTextField(), prodId = new JTextField(),
			costPrice = new JTextField(), sellPrice = new JTextField(),
			currentStock = new JTextField(),bx1 = new JTextField(), manuf = new JTextField(),
			bx2 = new JTextField(), bx3 = new JTextField(), colourbx = new JTextField()
			 };
	
	JLabel[] elecProdDetailLb = { titleLB = new JLabel(" Product title:"),
			typeLB = new JLabel(" Type:"),  idLB = new JLabel(" Product ID:"),
			cPriceLB = new JLabel(" Cost price:"), sPriceLB = new JLabel(" Selling price:"),
			stockLB = new JLabel(" Current stock:"), label1 = new JLabel(" Over Ear:"),
			manufLB = new JLabel(" Manufacturer:"),
			label2 = new JLabel(" Mic:"), label3 = new JLabel(" iPhone Ready:"), colour = new JLabel(" Colour:") };
	
	private JFrame frame;
	private ProdOperations prodOpertaion;
	private ElecProdList  elecProdList;
	
	public ElecProdPanel(JFrame frame, String prodType, ProdOperations po, ElecProdList pl) {

		
		this.frame = frame;
		this.setLayout(new BorderLayout());
		this.elecProdList = pl;
		this.prodOpertaion = po;
		cl2 = new Color(75,255,250);
		cl3 = new Color(255,230,0);
		prodDetails = new JLabel("Electric Product");
		

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
				if(elecProdDetailLb[i].getText().equals(" Product ID:"))
				{
					elecProdDetailBx[i].setBackground(cl3);
				}
				else
				{
					elecProdDetailBx[i].setBackground(cl2);
				}
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
		ElecProduct p = elecProdList.getProduct(pos);
		this.displayProduct(p);
	}
	
	
	
	public void setEditableOn() {
		for (int i = 0; i < elecProdDetailBx.length; i++)
			if (elecProdDetailLb[i].getText() !=" Product ID:")
				elecProdDetailBx[i].setEditable(true);
	}

	public void setEditableOff() {
		for (int i = 0; i < elecProdDetailBx.length; i++)
			elecProdDetailBx[i].setEditable(false);
	}
	
	public void addNew() {
		

		prodDetails.setText("Enter New Details");
		prodDetails.setBorder(new EmptyBorder(10, 390, 0, 110));
		updateBtn.setText("Add New Prod");
		for (int i = 0; i < elecProdDetailBx.length; i++) {
			prodId.setText(String.valueOf(prodOpertaion.getId()));
			prodId.setEditable(false);
			if (i != 2) {
				elecProdDetailBx[i].setText("");
				elecProdDetailBx[i].setEditable(true);
			}
		}
		
		phono.setEnabled(true);
		console.setEnabled(true);
		dock.setEnabled(true);
		prodBtnsPanel.setVisible(false);
		this.add(newProdBtnsPanel);
	}

public ElecProduct newProduct() {
		
	
		ElecProduct p =null;
		if(phono.isSelected())
		{
			if(bx1.getText().equals("Y") ||  bx1.getText().equals("N"))
			{
				if(bx2.getText().equals("Y") ||  bx2.getText().equals("N") )
				{
					if(bx3.getText().equals("Y") ||  bx3.getText().equals("N" ))
					{
						p = new ElecProduct(prodId.getText(),
								"",
								"",
								"HEADPHONES",
								prodTitle.getText(),
								Double.parseDouble(costPrice.getText()),
								Double.parseDouble(sellPrice.getText()),
								Integer.parseInt(currentStock.getText()),
								bx3.getText(),
								bx1.getText(),
								bx2.getText(),
								manuf.getText(),
								colourbx.getText()
								);
					}
				}
			}
			else
			{
				
			
		}
		}
		else if(console.isSelected())
		{
			if(bx3.getText().equals("Y") ||  bx3.getText().equals("N"))
			{
			p = new ElecProduct(prodId.getText(),
					"",
					"",
					"CONSOLE",
					prodTitle.getText(),
					Double.parseDouble(costPrice.getText()),
					Double.parseDouble(sellPrice.getText()),
					Integer.parseInt(currentStock.getText()),
					Integer.parseInt(bx1.getText()),
					bx3.getText(),
					Integer.parseInt(bx2.getText()),
					manuf.getText(),
					colourbx.getText()
					);
			}
			else
			{
				
			}
		}
		else if(dock.isSelected())
		{
			if(bx1.getText().equals("Y") ||  bx1.getText().equals("N"))
			{
				if(bx2.getText().equals("Y") ||  bx2.getText().equals("N") )
				{
			p = new ElecProduct(prodId.getText(),
					"",
					"",
					"SOUNDDOCK",
					prodTitle.getText(),
					Double.parseDouble(costPrice.getText()),
					Double.parseDouble(sellPrice.getText()),
					Integer.parseInt(currentStock.getText()),
					bx1.getText(),
					bx2.getText(),
					Integer.parseInt(bx3.getText()),
					manuf.getText(),
					colourbx.getText()
					);
				}
			}
			else
			{
				
			}
		}
		return p;
	}
	
	public void updateProd() {
		updateBtn.setText("Update Product");
		prodDetails.setText("Update Product Details");
		prodDetails.setBorder(new EmptyBorder(10, 372, 0, 110));
		prodBtnsPanel.setVisible(false);
		this.add(newProdBtnsPanel);
	}

	public void updateProduct() {
		

		
		if(phono.isSelected())
		{		
			ElecProduct p = new ElecProduct(
			prodId.getText(),
			elecProdList.getProduct(counter).getElec_id(),
			elecProdList.getProduct(counter).getHeadphone_id(),
			"HEADPHONES",
			prodTitle.getText(),
			Double.parseDouble(costPrice.getText()),
			Double.parseDouble(sellPrice.getText()),
			Integer.parseInt(currentStock.getText()),
			bx3.getText(),
			bx1.getText(),
			bx2.getText(),
			manuf.getText(),
			colourbx.getText()
		);
		
		elecProdList.updateProduct(p);
		JOptionPane.showMessageDialog(null, "Headphone " + prodTitle.getText()
				+ " Updated");
		prodDetails.setText("Product Details");
		}
		else if(console.isSelected())
		{

		
			ElecProduct p = new ElecProduct(
			prodId.getText(),
			elecProdList.getProduct(counter).getElec_id(),
			elecProdList.getProduct(counter).getConsole_id(),
			"CONSOLE",
			prodTitle.getText(),
			Double.parseDouble(costPrice.getText()),
			Double.parseDouble(sellPrice.getText()),
			Integer.parseInt(currentStock.getText()),
			Integer.parseInt(bx1.getText()),
			bx3.getText(),
			Integer.parseInt(bx2.getText()),
			manuf.getText(),
			colourbx.getText()
			);
			
			elecProdList.updateProduct(p);
			JOptionPane.showMessageDialog(null, "Console " + prodTitle.getText()
					+ " Updated");
			prodDetails.setText("Product Details");
		}
		else if(dock.isSelected())
		{
			
			ElecProduct p = new ElecProduct(
			prodId.getText(),
			elecProdList.getProduct(counter).getElec_id(),
			elecProdList.getProduct(counter).getSd_id(),
			"DOCK",
			prodTitle.getText(),
			Double.parseDouble(costPrice.getText()),
			Double.parseDouble(sellPrice.getText()),
			Integer.parseInt(currentStock.getText()),
			bx1.getText(),
			bx2.getText(),
			Integer.parseInt(bx3.getText()),
			manuf.getText(),
			colourbx.getText()
			);
				
			elecProdList.updateProduct(p);
			JOptionPane.showMessageDialog(null, "SoundDock " + prodTitle.getText()
					+ " Updated");
			prodDetails.setText("Product Details");
		}
	}
	public void searchProd() {
		String prodID = JOptionPane.showInputDialog(null,
				"Enter the Product ID: ",
				"", JOptionPane.QUESTION_MESSAGE);
		int index = elecProdList.findProd(prodID);
		if (index != -1) 
			displayProduct(elecProdList.getProd(index));
		 else 
			JOptionPane.showMessageDialog(null, " Product not found");
	}
	
	public void deleteProd() {
		int numberOfDeleted = elecProdList.removeProd(prodId.getText());
		JOptionPane.showMessageDialog(null, numberOfDeleted
				+ " Record(s) deleted.");
		setFirst();
	}
	
	public ElecProduct displayProduct(ElecProduct p) {
		setEditableOff();
		
		if(p.getProd_type().equals("CONSOLE"))
		{
			
			label1.setText(" Storage Size:");
			label2.setText(" Controllers:");
			label3.setText(" Wifi:");
			prodId.setText(p.getProd_id());
			prodTitle.setText(p.getModel());
			costPrice.setText(Double.toString(p.getCostPrice()));
			sellPrice.setText(Double.toString(p.getSellPrice()));
			currentStock.setText(Integer.toString(p.getCurrent_stock()));
			manuf.setText(p.getManufacturer());
			bx1.setText(Integer.toString(p.getStorageSize()));
			bx2.setText(Integer.toString(p.getNumPad()));
			bx3.setText(p.getWifi());
			colourbx.setText(p.getColour());
			console.setSelected(true);
			dock.setEnabled(false);
			phono.setEnabled(false);
			console.setEnabled(true);
		}
		else if(p.getProd_type().equals("HEADPHONES"))
		{
			label1.setText(" iPhone Comp:");
			label2.setText(" Mic:");
			label3.setText(" Over Ear:");
			prodId.setText(p.getProd_id());
			prodTitle.setText(p.getModel());
			costPrice.setText(Double.toString(p.getCostPrice()));
			sellPrice.setText(Double.toString(p.getSellPrice()));
			currentStock.setText(Integer.toString(p.getCurrent_stock()));
			manuf.setText(p.getManufacturer());
			bx2.setText(p.getIphoneComp());
			bx3.setText(p.getMic());
			bx1.setText(p.getOverEar());
			colourbx.setText(p.getColour());
			phono.setSelected(true);
			dock.setEnabled(false);
			phono.setEnabled(true);
			console.setEnabled(false);
		}
		else if(p.getProd_type().equals("SOUNDDOCK"))
		{
			label1.setText(" Digi Radio:");
			label2.setText(" Wireless:");
			label3.setText(" Pwr Output:");
			prodId.setText(p.getProd_id());
			prodTitle.setText(p.getModel());
			costPrice.setText(Double.toString(p.getCostPrice()));
			sellPrice.setText(Double.toString(p.getSellPrice()));
			currentStock.setText(Integer.toString(p.getCurrent_stock()));
			manuf.setText(p.getManufacturer());
			bx3.setText(Integer.toString(p.getPwrOut()));
			bx1.setText(p.getDigiRadio());
			bx2.setText(p.getWireless());
			colourbx.setText(p.getColour());
			dock.setSelected(true);
			dock.setEnabled(true);
			phono.setEnabled(false);
			console.setEnabled(false);
		}
		return p;
	}

	public void actionPerformed(ActionEvent e) {

		int totalContacts = elecProdList.getNumProduct();
		
		if (e.getSource() == exit) {
			System.exit(0);
		}
		
		else if (e.getSource().equals(next)) {
			if ((counter + 1) < totalContacts) {
				counter++;
				this.displayProduct(elecProdList.getProduct(counter));
			}
		} 
		else if (e.getSource().equals(previous)) {
			if ((counter - 1) >= 0) {
				counter--;
				this.displayProduct(elecProdList.getProduct(counter));
			}
		}
		else if (e.getSource().equals(updateBtn)
				&& updateBtn.getText().equals("Add New Prod")) {
			try
			{
				if(newProduct() != null)
				{
				prodOpertaion.addNewProd(newProduct());
				elecProdList.addProduct();
				prodBtnsPanel.setVisible(true);
				newProdBtnsPanel.setVisible(false);
				JOptionPane.showMessageDialog(null, prodTitle.getText() + " Saved");
				prodBtnsPanel.setVisible(true);
				setEditableOff();
				setFirst();
				newProd = false;
				}
				else
				{
					JOptionPane.showMessageDialog(null, "iPhone Comp:\nMic:\nOver Ear:\nUpper case Y for yes.\nUpper case N for no.");
				}
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(null, "Cost price:\nSelling price:\nCurrent stock:\nControllers:"
						+ "\nStorage size:\nPwr Output:\n Must contain numbers."  );
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
		else if (e.getSource().equals(addProd)) {
			elecProdDetailBx[0].requestFocus();
			elecProdDetailBx[0].getCaret().setBlinkRate(600);
			newProdBtnsPanel.setVisible(true);
			addNew();
			newProd = true;
		} 
		else if (e.getSource().equals(updateProd)) {
			elecProdDetailBx[0].requestFocus();
			elecProdDetailBx[0].getCaret().setBlinkRate(600);
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

		
		for (int i = 0; i < elecProdRadioBtns.length; i++) {
			
		}
		if(phono.isSelected())
		{
			label1.setText(" iPhone Comp:");
			label2.setText(" Mic:");
			label3.setText(" Over Ear:");
		}
		else if(console.isSelected())
		{
			label1.setText(" Storage Size:");
			label2.setText(" Controllers:");
			label3.setText(" Wifi:");
		}
		else if(dock.isSelected())
		{
			label1.setText(" Digi Radio:");
			label2.setText(" Wireless:");
			label3.setText(" Pwr Output:");
		
		}
	
	}
}
