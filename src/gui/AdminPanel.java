package gui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

import model.EmployeeList;
import db.AdminOperations;


public class AdminPanel extends JPanel implements ActionListener{

	private CardLayout cards;
	private JFrame frame;
	private static final int FRAME_WIDTH = 1148;
	private static final int FRAME_HEIGHT = 827;
	private JButton genReportBtn, editUserBtn, editProdBtn,  test;
	private JRadioButton elcProdRB, digiProdRB;

	// Border declaration for use on east and west panels on main frame
	private	Border space = (Border) BorderFactory.createEmptyBorder(10, 10, 10, 10);
	private	Border line = (Border) BorderFactory.createLineBorder(Color.black);
	private	Border border = BorderFactory.createCompoundBorder(space, line);
	
	private JLabel logo, spacer;

	private JPanel cardPanel, digiProdPanel, genReportPanel, userPanel, elecProdPanel;

	private BorderLayout layout = new BorderLayout();
	private GridBagConstraints gc = new GridBagConstraints();
	private Color cl;
	
	private EmployeeList employeeList;
	private AdminOperations adminOperations;
	

	public AdminPanel(JFrame frame, AdminOperations ao, EmployeeList el)
	{
		
		this.frame = frame;
		
		// Main frame declaration
		
		this.setLayout(layout);
//		this.setTitle("Admin Screen");
//		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
//		this.setLocationRelativeTo(null);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.employeeList = el;
		this.adminOperations = ao;
		cl = new Color(240, 240, 240);

		// Left side buttons panel
		JPanel sideButtons = new JPanel();
		sideButtons.setBackground(cl);
		sideButtons.setLayout(new GridBagLayout());
		sideButtons.setBorder(border);
		this.add(sideButtons, BorderLayout.WEST);

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
				genReportBtn = new JButton("Genarate Report"),
				editUserBtn = new JButton("Edit User"),
				editProdBtn = new JButton("Edit Product"),
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

		userPanel = new UserPanel(frame, adminOperations, employeeList);
//		elecProdPanel = new ElecProdPanel(frame);
//		digiProdPanel = new DigiProdPanel(frame);

		

		// Main panel for displaying all the  panels on action performed
		cards = new CardLayout();

		cardPanel.setLayout(cards);
		cardPanel.add(genReportPanel, "genReport");
		cardPanel.add(userPanel, "editUser");
		cardPanel.add(elecProdPanel, "editElec");
		cardPanel.add(digiProdPanel, "editDigi");
		cardPanel.setBorder(border);
		cardPanel.setPreferredSize(new Dimension(820, 10));
		this.add(cardPanel, BorderLayout.EAST);

//		this.setVisible(true);
	}
	

	
	/*public void prodSelect() {
		JPanel prodSelect = new JPanel();
		elcProdRB = new JRadioButton("Electronic product");
		digiProdRB = new JRadioButton("Digital Product");
		prodSelect.add(elcProdRB);
		prodSelect.add(digiProdRB);
		JOptionPane.showOptionDialog(null, prodSelect, "Select Product Type",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				null, null);
	}*/
	

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == genReportBtn) {
			cards.show(cardPanel, "genReport");
		} else if (e.getSource() == editUserBtn) {
			cards.show(cardPanel, "editUser");	
		} 
		else if(e.getSource() == test)
		{
			frame.setVisible(false); 
			frame.dispose();
			System.out.println("hu");
		}
		else if (e.getSource() == editProdBtn) {
//			prodSelect();
			
			if (elcProdRB.isSelected()) {
				cards.show(cardPanel, "editElec");

			} else if (digiProdRB.isSelected()) {
				cards.show(cardPanel, "editDigi");

			} else {

			}
		}
	}
	
	
	
	
	
	
	
}
