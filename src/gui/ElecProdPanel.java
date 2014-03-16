package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

public class ElecProdPanel extends JPanel implements ActionListener,
		ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Font font = new Font("Verdana", Font.PLAIN, 20);
	private GridBagConstraints gc = new GridBagConstraints();
	private JButton addProd, editProd, removeProd;
	private JLabel elecProdDetails;
	private JTextField other, supplierID, currentStock, sellPrice, costPrice,
			prodTitle, type, prodId;
	private JPanel elecProdDetailsPanel, editElecProdBtnsPanel;

	private Border space = (Border) BorderFactory.createEmptyBorder(10, 10, 10,10);
	private Border line = (Border) BorderFactory.createLineBorder(Color.black);
	private Border border = BorderFactory.createCompoundBorder(space, line);

	private JRadioButton phono = new JRadioButton("Headphones");
	private JRadioButton console = new JRadioButton("Console");
	private JRadioButton dock = new JRadioButton("Dock");
	private JRadioButton[] elecProdRadioBtns = new JRadioButton[] { phono,console, dock };
	private JFrame jFrame;
	
	
	public ElecProdPanel(JFrame jFrame) {

		this.jFrame = jFrame;
		
		
		this.setLayout(new BorderLayout());
		elecProdDetails = new JLabel("Electronic Product");
		elecProdDetails.setBorder(new EmptyBorder(10, 500, 0, 0));
		elecProdDetails.setFont(font);
		this.add(elecProdDetails, BorderLayout.NORTH);

		// product detail panel, inside the Edit product panel
		elecProdDetailsPanel = new JPanel();
		elecProdDetailsPanel.setLayout(new GridBagLayout());
		elecProdDetailsPanel.setPreferredSize(new Dimension(550, 600));
		elecProdDetailsPanel.setBorder(border);

		// Adding labels and textbox to the product details panel
		JTextField[] elecProdDetailBx = { prodTitle = new JTextField(),
				type = new JTextField(), prodId = new JTextField(),
				costPrice = new JTextField(), sellPrice = new JTextField(),
				currentStock = new JTextField(), supplierID = new JTextField(),
				other = new JTextField(), };

		JLabel[] elecProdDetailLb = { new JLabel(" Product title"),
				new JLabel(" Type"), new JLabel(" Product ID"),
				new JLabel(" Cost price"), new JLabel(" Selling price"),
				new JLabel(" Current stock"), new JLabel(" Supplier ID"),
				new JLabel(" Other") };

		ButtonGroup group = new ButtonGroup();
		group.add(phono);
		group.add(console);
		group.add(dock);

		for (int i = 0; i < elecProdDetailLb.length; i++) {
			gc.gridx = 0;
			gc.gridy = i;
			gc.gridwidth = 1;
			gc.gridheight = 1;
			gc.weighty = 0.1;
			gc.weightx = 10.0;
			gc.anchor = GridBagConstraints.WEST;
			elecProdDetailLb[i].setFont(font);
			elecProdDetailsPanel.add(elecProdDetailLb[i], gc);

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
						elecProdRadioBtns[j].setFont(font);
						elecProdRadioBtns[j].addItemListener(this);
						elecProdDetailsPanel.add(elecProdRadioBtns[j], gc);
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
				elecProdDetailBx[i].setPreferredSize(new Dimension(300, 30));
				elecProdDetailsPanel.add(elecProdDetailBx[i], gc);
			}
		}

		this.add(elecProdDetailsPanel, BorderLayout.EAST);

		// button panel inside edit product panel
		editElecProdBtnsPanel = new JPanel();
		editElecProdBtnsPanel.setLayout(new GridBagLayout());
		editElecProdBtnsPanel.setPreferredSize(new Dimension(250, 50));

		// Adding buttons to the button panel inside the edit product panel
		JButton[] editElecProdBtnsArray = {
				addProd = new JButton("Add Product"),
				removeProd = new JButton("Remove Product"),
				editProd = new JButton("Update Product"),

		};

		for (int i = 0; i < editElecProdBtnsArray.length; i++) {
			gc.gridx = 0;
			gc.gridy = i;
			gc.gridwidth = 1;
			gc.gridheight = 1;
			gc.weighty = 0.0;
			gc.weightx = 0.0;
			gc.insets = new Insets(10, 0, 0, 0);
			editElecProdBtnsArray[i].setIcon(new ImageIcon("src/resources/blueButton.png"));
			editElecProdBtnsArray[i].setFont(new Font("sansserif", Font.BOLD,16));
			editElecProdBtnsArray[i].setPreferredSize(new Dimension(180, 50));
			editElecProdBtnsArray[i].setHorizontalTextPosition(JButton.CENTER);
			editElecProdBtnsArray[i].setVerticalTextPosition(JButton.CENTER);
			editElecProdBtnsArray[i].addActionListener(this);
			editElecProdBtnsPanel.add(editElecProdBtnsArray[i], gc);
		}
		this.add(editElecProdBtnsPanel, BorderLayout.WEST);

	}

	public void actionPerformed(ActionEvent e) {

	}

	public void itemStateChanged(ItemEvent e) {

		String[] elecPopup = { "phono", "console", "dock" };

		for (int i = 0; i < elecProdRadioBtns.length; i++) {
			if (elecProdRadioBtns[i].isSelected()) {
				
				ProdDialog el = new	ProdDialog(elecPopup[i], jFrame);

			}

		}

	}

}
