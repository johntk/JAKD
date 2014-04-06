package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import kioskScreens.KioskStartScreen;
import model.EmployeeList;
import db.EmpOperations;
import db.DBconnection;
import db.ProdOperations;

public class HomeScreen extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private CardLayout cards;
	private static JFrame frame;
	private static final int FRAME_WIDTH = 1248;
	private static final int FRAME_HEIGHT = 700;
	private JButton button1, button2, button3, button4, digiProd, elecProd, closeBtn;
	private JLabel logo, logo2, welcome, spacer;

	private	JButton[] sideButtonsArray = { button1 = new JButton("POS"),
			button2 = new JButton("Admin"), 
			button3 = new JButton("Kiosk"),
			button4 = new JButton("Close"), };

	private JPanel homePanel, ProdSelect, center, posGUI, cardPanel, digiProdPanel, genReportPanel,
	userPanel, elecProdPanel;

	// Border declaration for use on east and west panels on main frame
	private Border space = (Border) BorderFactory.createEmptyBorder(10, 10, 10,10);
	private Border line = (Border) BorderFactory.createLineBorder(Color.black);
	private Border border = BorderFactory.createCompoundBorder(space, line);

	private BorderLayout layout = new BorderLayout();
	private GridBagConstraints gc = new GridBagConstraints();
	private Font font = new Font("Verdana", Font.PLAIN, 20);
	private Color cl1;

	private EmployeeList employeeList;
	private EmpOperations adminOperations;
	private ProdOperations prodOpertaion;

	private static SystemTray tray;
	private static TrayIcon trayIcon;
	private static Image img;
	private static PopupMenu popup;
	private static ImageIcon ti;
	private DBconnection db;

	public HomeScreen() {

		db = new DBconnection();
		ti = new ImageIcon(this.getClass().getResource("/resources/trayIcon.png"));

		EmpOperations ao = new EmpOperations();
		ProdOperations po = new ProdOperations();
		EmployeeList el = new EmployeeList(ao);
		ao.setDBconnection(db.openDB());
		po.setDBconnection(db.openDB());

		// Main frame declaration
		frame = new JFrame();
		frame.setLayout(layout);
		frame.setTitle("Home Screen");
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/resources/titleIcon.png"));
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.employeeList = el;
		this.adminOperations = ao;
		this.prodOpertaion = po;
		cl1 = new Color(240, 240, 240);

		// Left side buttons panel
		JPanel sideButtons = new JPanel();
		sideButtons.setBackground(cl1);
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
		closeBtn = new JButton("Close");
		closeBtn.addActionListener(this);
		genReportPanel.add(closeBtn);


		// Home panel
		homePanel = new JPanel();
		homePanel.setLayout(new BorderLayout());
		frame.add(homePanel, BorderLayout.CENTER);

		welcome = new JLabel("Welcome to JAKD!");
		welcome.setFont(font);
		logo2 = new JLabel("");
		logo2.setIcon(new ImageIcon("src/resources/logo.jpeg"));
		logo2.setPreferredSize(new Dimension(400, 120));
		homePanel.add(logo2);


		JButton[] prodSelect = {elecProd = new JButton("Electric Product"),
				digiProd = new JButton("Digital Product")};

		//Product select panel	
		ProdSelect = new JPanel();
		ProdSelect.setLayout(new GridBagLayout());

		for(int i =0; i < prodSelect.length; i++)
		{
			prodSelect[i].setIcon(new ImageIcon("src/resources/blueButton.png"));
			prodSelect[i].setFont(new Font("sansserif", Font.BOLD, 22));
			prodSelect[i].setPreferredSize(new Dimension(280, 100));
			prodSelect[i].setHorizontalTextPosition(JButton.CENTER);
			prodSelect[i].setVerticalTextPosition(JButton.CENTER);
			prodSelect[i].addActionListener(this);
			ProdSelect.add(prodSelect[i]);
		}


		//Center in Home Panel
		center = new JPanel();
		center.setLayout(new GridBagLayout());
		center.setBackground(new Color(0, 0, 0, 0));

		logo2.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		gc.gridx = 0;
		gc.gridy = 0;
		center.add(logo2, gc);
		welcome.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 40));
		gc.gridy = 1;
		center.add(welcome, gc);
		homePanel.add(center, BorderLayout.CENTER);

		// Main panel for displaying all the panels on action performed
		userPanel = new UserPanel(frame, adminOperations, employeeList);
		elecProdPanel = new ProdPanel(frame, "elec");
		digiProdPanel = new ProdPanel(frame, "digi");
		posGUI = new PosGui(frame);


		cards = new CardLayout();
		cardPanel.setLayout(cards);
		cardPanel.setBackground(cl1);
		cardPanel.add(homePanel, "homePanel");
		cardPanel.add(ProdSelect, "prodSelect");
		cardPanel.add(genReportPanel, "genReport");
		cardPanel.add(posGUI, "POSGui");
		cardPanel.add(userPanel, "editUser");
		cardPanel.add(elecProdPanel, "editElec");
		cardPanel.add(digiProdPanel, "editDigi");
		cardPanel.setBorder(border);
		cardPanel.setPreferredSize(new Dimension(900, 10));
		frame.add(cardPanel, BorderLayout.EAST);
		addSystemTray();

		frame.setVisible(true);

	}

	public static void addSystemTray()
	{
		if (!java.awt.SystemTray.isSupported())
		{  
			System.out.println("SystemTray is not supported");  
			return;  
		}
		popup = new PopupMenu();
		try{
			img = ti.getImage();
//			img =Toolkit.getDefaultToolkit().getImage("bin/resources/trayIcon.png");
			trayIcon = new TrayIcon(img,"JAKD - Business Management System");
			tray = SystemTray.getSystemTray();
			tray.add(trayIcon);
		}catch(AWTException ae){

		}
		MenuItem exitItem = new MenuItem("Exit");
		exitItem.addActionListener(new ActionListener()
		{  
			public void actionPerformed(ActionEvent e)
			{  
				System.exit(0); 
			}  
		});
		popup.add(exitItem);
		trayIcon.setPopupMenu(popup); 

		trayIcon.addActionListener(new ActionListener()
		{  
			public void actionPerformed(ActionEvent e)
			{  
				frame.requestFocus();
			}  
		});
		
		trayIcon.displayMessage("JAKD", "Right-Click Here For Options", TrayIcon.MessageType.NONE);
	}

	public void buttonSelect(JButton button) {

		if (button.equals(button1) && button1.getText().equals("Generate Report")) {
			cards.show(cardPanel, "genReport");
		}
		else if(button.equals(button1) && button1.getText().equals("POS"))
		{
			frame.setTitle("POS Screen");
			button4.setText("Logout");
			cards.show(cardPanel, "POSGui");
		}
		else if(button.equals(button2) && button2.getText().equals("Edit User"))
		{
			cards.show(cardPanel, "editUser");
		}
		else if (button.equals(button2) && button2.getText().equals("Admin"))
		{
			frame.setTitle("Admin Screen");
			button1.setText("Generate Report");
			button2.setText("Edit User");
			button3.setText("Edit Product");
			button4.setText("Logout");
			cards.show(cardPanel, "editUser");
		}
		else if(button.equals(button3) && button3.getText().equals("Edit Product"))
		{
			cards.show(cardPanel, "prodSelect");
		}
		else if(button.equals(button3) && button3.getText().equals("Kiosk"))
		{
			new KioskStartScreen(db);
			button4.setText("Logout");
		}
		else if (button.equals(button4) && button4.getText().equals("Logout")) {
			button1.setText("POS");
			button2.setText("Admin");
			button3.setText("Kiosk");
			button4.setText("Close");
			frame.setTitle("Home Screen");
			cards.show(cardPanel, "homePanel");
			cardPanel.repaint();
		}
		else
		{
			System.exit(0);
		}
	}
	//
	public void actionPerformed(ActionEvent e) {

		for (int i = 0; i < sideButtonsArray.length; i++) {
			if (e.getSource().equals(sideButtonsArray[i])) {
				buttonSelect(sideButtonsArray[i]);
			}
		}

		if (e.getSource().equals(elecProd)) {
			cards.show(cardPanel, "editElec");
		} 
		else if (e.getSource().equals(digiProd)) {
			cards.show(cardPanel, "editDigi");
		} 
		else if (e.getSource() == closeBtn) {
			prodOpertaion.getCD();
			//adminOperations.getId();
		}
	}

	public static void main(String args[]) {
		new HomeScreen();
	}
}
