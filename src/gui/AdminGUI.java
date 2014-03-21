package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;


import model.Employee;
import model.EmployeeList;
import db.AdminOperations;

public class AdminGUI extends JFrame implements ActionListener, ItemListener {

	private static final long serialVersionUID = 1L;
	private CardLayout cards;
	private JFrame frame;
	private static final int FRAME_WIDTH = 1148;
	private static final int FRAME_HEIGHT = 827;
	private JButton genReportBtn, editUserBtn, editProdBtn, financialManagBtn;
	private JRadioButton elcProdRB, digiProdRB;

	// Border declaration for use on east and west panels on main frame
	private	Border space = (Border) BorderFactory.createEmptyBorder(10, 10, 10, 10);
	private	Border line = (Border) BorderFactory.createLineBorder(Color.black);
	private	Border border = BorderFactory.createCompoundBorder(space, line);
	
	private JLabel logo, spacer;

	private JPanel cardPanel, digiProdPanel, genReportPanel, userPanel,
			elecProdPanel, financialPanel;

	private BorderLayout layout = new BorderLayout();
	private GridBagConstraints gc = new GridBagConstraints();
	private Color cl;
	
	private int counter = 0;
	private EmployeeList employeeList;
	private AdminOperations adminOperations;
	
	public AdminGUI(AdminOperations ao, EmployeeList el) {
		// Main frame declaration
		frame = new JFrame();
		frame.setLayout(layout);
		frame.setTitle("Admin Screen");
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.employeeList = el;
		this.adminOperations = ao;
		cl = new Color(240, 240, 240);

		// Left side buttons panel
		JPanel sideButtons = new JPanel();
		sideButtons.setBackground(cl);
		sideButtons.setLayout(new GridBagLayout());
		sideButtons.setBorder(border);
		frame.add(sideButtons, BorderLayout.WEST);

		// Logo and buttons added to left side panel
		logo = new JLabel("");
		logo.setIcon(new ImageIcon("src/resources/logo.jpeg"));
		logo.setPreferredSize(new Dimension(295, 120));
		gc.gridx = 0; // col
		gc.gridy = 0; // row
		gc.gridwidth = 1; // set gridwidth
		gc.gridheight = 1; // set gridheight
		gc.weighty = 0.0;// amount of space to allocate vertically
		gc.weightx = 0.0;// amount of space to allocate horizontally
		sideButtons.add(logo, gc);

		// space between logo and buttons, would rather use a "spacer" here,
		// more research needed
		spacer = new JLabel("");

		gc.gridx = 0;
		gc.gridy = 1;
		gc.gridwidth = 1;
		gc.gridheight = 1;
		gc.weighty = 10.0;
		gc.weightx = 0.0;
		sideButtons.add(spacer, gc);

		// side button array
		JButton[] sideButtonsArray = {
				genReportBtn = new JButton("Genarate Report"),
				editUserBtn = new JButton("Edit User"),
				editProdBtn = new JButton("Edit Product"),
				financialManagBtn = new JButton("Financial Managment") };

		// Adding side buttons to side panel
		for (int i = 0; i < sideButtonsArray.length; i++) {
			gc.gridx = 0;
			gc.gridy = i + 2;
			gc.gridwidth = 1;
			gc.gridheight = 1;
			gc.weighty = 0.2;
			gc.weightx = 0.0;
			sideButtonsArray[i].setIcon(new ImageIcon("src/resources/blueButton.png"));
			sideButtonsArray[i].setFont(new Font("sansserif", Font.BOLD, 22));
			sideButtonsArray[i].setPreferredSize(new Dimension(280, 100));
			sideButtonsArray[i].setHorizontalTextPosition(JButton.CENTER);
			sideButtonsArray[i].setVerticalTextPosition(JButton.CENTER);
			sideButtonsArray[i].addActionListener(this);
			sideButtons.add(sideButtonsArray[i], gc);
		}

		// Different panels for action performed events on the side buttons
		cardPanel = new JPanel();

		// Generate report panel
		genReportPanel = new JPanel();
		genReportPanel.setBackground(Color.WHITE);
		JButton test = new JButton("Test");
		test.addActionListener(this);
		genReportPanel.add(test);

		userPanel = new UserPanel(frame, adminOperations, employeeList);
		elecProdPanel = new ElecProdPanel(frame);
		digiProdPanel = new DigiProdPanel(frame);

		// Financial panel
		financialPanel = new JPanel();
		financialPanel.setBackground(Color.BLUE);
		JButton test4 = new JButton("Test4");
		test4.addActionListener(this);
		financialPanel.add(test4);

		// Main panel for displaying all the  panels on action performed
		cards = new CardLayout();

		cardPanel.setLayout(cards);
		cardPanel.add(genReportPanel, "genReport");
		cardPanel.add(userPanel, "editUser");
		cardPanel.add(elecProdPanel, "editElec");
		cardPanel.add(digiProdPanel, "editDigi");
		cardPanel.add(financialPanel, "financial");
		cardPanel.setBorder(border);
		cardPanel.setPreferredSize(new Dimension(820, 10));
		frame.add(cardPanel, BorderLayout.EAST);

		frame.setVisible(true);

	}
	
	
	
	private int empID;
	private String fName;
	private String lName;
	private String houseNum;
	private String street;
	private String town;
	private String city;
	private String PPS;
	private int pin;
	private String manager;
	
	
	
	public void prodSelect() {
		JPanel prodSelect = new JPanel();
		elcProdRB = new JRadioButton("Electronic product");
		digiProdRB = new JRadioButton("Digital Product");
		prodSelect.add(elcProdRB);
		prodSelect.add(digiProdRB);
		JOptionPane.showOptionDialog(null, prodSelect, "Select Product Type",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				null, null);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == genReportBtn) {
			cards.show(cardPanel, "genReport");
		} else if (e.getSource() == editUserBtn) {
			cards.show(cardPanel, "editUser");
			String nameSearch = JOptionPane.showInputDialog(null,
					"Enter the name of person you wish to edit");

			
			
		} else if (e.getSource() == editProdBtn) {
			prodSelect();
			String prodSearch;
			if (elcProdRB.isSelected()) {
				cards.show(cardPanel, "editElec");
				prodSearch = JOptionPane.showInputDialog(null,
						"Enter the name of product you wish to edit");
			} else if (digiProdRB.isSelected()) {
				cards.show(cardPanel, "editDigi");
				 prodSearch = JOptionPane.showInputDialog(null,
						"Enter the name of product you wish to edit");
			} else {

			}
		} else if (e.getSource() == financialManagBtn) {
			cards.show(cardPanel, "financial");
		}
	}

	public void itemStateChanged(ItemEvent it) {

	}

}
