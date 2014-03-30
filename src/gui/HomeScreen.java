package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import kioskScreens.KioskStartScreen;
import model.Employee;
import model.EmployeeList;
import db.AdminOperations;
import db.DBconnection;

public class HomeScreen extends JFrame implements ActionListener, ItemListener {

	private static final long serialVersionUID = 1L;
	private CardLayout cards;
	private JFrame frame;
	private static final int FRAME_WIDTH = 1148;
	private static final int FRAME_HEIGHT = 827;
	private JButton button1, button2, button3, button4, test;
	private JRadioButton elcProdRB, digiProdRB;
	private DBconnection db;
	// Border declaration for use on east and west panels on main frame
	private	Border space = (Border) BorderFactory.createEmptyBorder(10, 10, 10, 10);
	private	Border line = (Border) BorderFactory.createLineBorder(Color.black);
	private	Border border = BorderFactory.createCompoundBorder(space, line);
	
	private JLabel logo1,logo2, spacer1, welcome;
	private JLabel logo, spacer;
	private Font font = new Font("Verdana", Font.PLAIN, 20);

	private JPanel homePanel, posGUI, cardPanel, digiProdPanel, genReportPanel, userPanel, elecProdPanel;

	private BorderLayout layout = new BorderLayout();
	private GridBagConstraints gc = new GridBagConstraints();
	private Color cl;
	
	private EmployeeList employeeList;
	private AdminOperations adminOperations;
	
	public HomeScreen() {
		
		db = new DBconnection();
		AdminOperations ao = new AdminOperations();
		EmployeeList el = new EmployeeList(ao);
		
		// Main frame declaration
		frame = new JFrame();
		frame.setLayout(layout);
		frame.setTitle("Home Screen");
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
		gc.gridy = 1;
		gc.weighty = 10.0;
		sideButtons.add(spacer, gc);

		// side button array
		JButton[] sideButtonsArray = {
				button1 = new JButton("POS"),
				button2 = new JButton("Admin"),
				button3 = new JButton("Kiosk"),
				button4 = new JButton("Close"),
				};

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
		test = new JButton("Close");
		test.addActionListener(this);
		genReportPanel.add(test);

		
//		genReportPanel = new AdminPanel(frame, adminOperations, employeeList);
		

		homePanel = new JPanel();
		
		homePanel.setLayout(new BorderLayout());
		frame.add(homePanel, BorderLayout.CENTER);
		
		welcome = new JLabel("Welcome to JAKD!");
		welcome.setFont(font);
		logo2 = new JLabel("");
		logo2.setIcon(new ImageIcon("src/resources/logo.jpeg"));
		logo2.setPreferredSize(new Dimension(400, 120));
		

		homePanel.add(logo2);
		
		

		JPanel center = new JPanel();
		homePanel.add(center, BorderLayout.CENTER);
		center.setLayout(new GridBagLayout());
		center.setBackground(new Color(0,0,0,0));

		//add logo and label to page center

		logo2.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
		gc.gridx = 0;
		gc.gridy = 0;
		center.add(logo2,gc);


		welcome.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
		gc.gridx = 0;
		gc.gridy = 1;
		center.add(welcome,gc);

		
		
		
		// Main panel for displaying all the  panels on action performed
		
		userPanel = new UserPanel(frame, adminOperations, employeeList);
		elecProdPanel = new ElecProdPanel(frame);
		digiProdPanel = new DigiProdPanel(frame);
		posGUI = new PosGui(frame);
		cards = new CardLayout();

		cardPanel.setLayout(cards);
		cardPanel.add(homePanel, "homePanel");
		cardPanel.add(genReportPanel, "genReport");
		cardPanel.add(posGUI, "POSGui");
		cardPanel.add(userPanel, "editUser");
		cardPanel.add(elecProdPanel, "editElec");
		cardPanel.add(digiProdPanel, "editDigi");
		cardPanel.setBorder(border);
		cardPanel.setPreferredSize(new Dimension(820, 10));
		frame.add(cardPanel, BorderLayout.EAST);

		frame.setVisible(true);

	}

	
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
		if (e.getSource().equals(button1) && button1.getText().equals("POS")) {
			frame.setTitle("POS Screen");
			cards.show(cardPanel, "POSGui");

		} else if (e.getSource().equals(button2)
				&& button2.getText().equals("Admin")) {
			frame.setTitle("Admin Screen");
			button1.setText("Generate Report");
			button2.setText("Edit User");
			button3.setText("Edit Product");
			button4.setText("Back");
			cards.show(cardPanel, "editUser");
		} else if (e.getSource().equals(button3)
				&& button3.getText().equals("Kiosk")) {
			KioskStartScreen kss = new KioskStartScreen(db);
		}

		else if (e.getSource().equals(button1)
				&& button1.getText().equals("Generate Report")) {

			cards.show(cardPanel, "genReport");
		} else if (e.getSource().equals(button2)
				&& button2.getText().equals("Edit User")) {
			cards.show(cardPanel, "editUser");
		} else if (e.getSource() == test) {
			frame.setVisible(false);
			frame.dispose();
			System.out.println("hu");
		} else if (e.getSource().equals(button3)
				&& button3.getText().equals("Edit Product")) {
			prodSelect();

			if (elcProdRB.isSelected()) {
				cards.show(cardPanel, "editElec");

			} else if (digiProdRB.isSelected()) {
				cards.show(cardPanel, "editDigi");

			}
		}

		else if (e.getSource().equals(button4)&& button4.getText().equals("Back")) {
			button1.setText("POS");
			button2.setText("Admin");
			button3.setText("Kiosk");
			button4.setText("Close");
			frame.setTitle("Home Screen");
			cards.show(cardPanel, "homePanel");
			cardPanel.repaint();
			System.out.println("hi");
		} else if (e.getSource().equals(button4)
				&& button4.getText().equals("Close")) {
			System.exit(0);
		}
	}

	public void itemStateChanged(ItemEvent it) {

	}
	
	
	public static void main (String args[])
	{
		HomeScreen ag = new HomeScreen();
	}
}
