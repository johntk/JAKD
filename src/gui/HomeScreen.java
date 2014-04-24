

package gui;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;

import javax.swing.*;
import javax.swing.border.*;

import operations.DBconnection;
import operations.EmpOperations;
import operations.HomeScreenOperations;
import operations.ProdOperations;
import reports.generateReport;
import model.ElecProdList;
import model.EmployeeList;
import model.DigiProductList;

public class HomeScreen extends JFrame implements ActionListener{

	
	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
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

	private CardLayout cards;
	private EmployeeList employeeList;
	private EmpOperations adminOperations;
	private ProdOperations prodOpertaion;
	private DigiProductList  digiProductList;
	private ElecProdList  elecProductList;

	
	private SystemTray tray;
	public static TrayIcon trayIcon;
	private Image img;
	private PopupMenu popup;
	private ImageIcon frameIcon;
	private ImageIcon ti;
	private DBconnection db;
	private Connection conn;
	private int index =0;
	private boolean manager;
	
	////////////////     log in variables //////////////////////////////////
	JDialog logIn; 
	JLabel enterPassword;
	JPasswordField jpf;
	JButton enterPButton;
	String pin;
	HomeScreenOperations ho;
	
	

	public HomeScreen() {
		ti = new ImageIcon(this.getClass().getResource("/resources/trayIcon.png"));
		frameIcon = new ImageIcon(this.getClass().getResource("/resources/titleIcon.png"));
		Image im = frameIcon.getImage();
		
		addSystemTray();

		db = new DBconnection();
		conn = db.openDB();
		
		EmpOperations ao = new EmpOperations();
		ProdOperations po = new ProdOperations();
		DigiProductList dpl = new DigiProductList(po);
		ElecProdList epl = new ElecProdList(po);
		EmployeeList el = new EmployeeList(ao);
		ao.setDBconnection(conn);
		po.setDBconnection(conn);

		// Main frame declaration
		frame = new JFrame();
		frame.setLayout(layout);
		frame.setTitle("Home Screen");
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(im);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.employeeList = el;
		this.adminOperations = ao;
		this.digiProductList = dpl;
		this.elecProductList = epl;
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
		logo.setIcon(new ImageIcon(this.getClass().getResource("/resources/logo.jpeg")));
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
			sideButtonsArray[i].setIcon(new ImageIcon(this.getClass().getResource("/resources/blueButton.png")));
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
		logo2.setIcon(new ImageIcon(this.getClass().getResource("/resources/logo.jpeg")));
		logo2.setPreferredSize(new Dimension(400, 120));
		homePanel.add(logo2);


		JButton[] prodSelect = {elecProd = new JButton("Electric Product"),
				digiProd = new JButton("Digital Product")};

		//Product select panel	
		ProdSelect = new JPanel();
		ProdSelect.setLayout(new GridBagLayout());

		for(int i =0; i < prodSelect.length; i++)
		{
			prodSelect[i].setIcon(new ImageIcon(this.getClass().getResource("/resources/blueButton.png")));
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
		elecProdPanel = new ElecProdPanel(frame, "elec", prodOpertaion, elecProductList);
		digiProdPanel = new DigiProdPanel(frame, "digi", prodOpertaion, digiProductList);
		


		cards = new CardLayout();
		cardPanel.setLayout(cards);
		cardPanel.setBackground(cl1);
		cardPanel.add(homePanel, "homePanel");
		cardPanel.add(ProdSelect, "prodSelect");
		
		cardPanel.add(userPanel, "editUser");
		cardPanel.add(elecProdPanel, "editElec");
		cardPanel.add(digiProdPanel, "editDigi");
		cardPanel.setBorder(border);
		cardPanel.setPreferredSize(new Dimension(900, 10));
		frame.add(cardPanel, BorderLayout.EAST);

		frame.setVisible(true);

	}

	public void addSystemTray()
	{
		if (!java.awt.SystemTray.isSupported())
		{  
			System.out.println("SystemTray is not supported");  
			return;  
		}
		popup = new PopupMenu();
		try{
			img = ti.getImage();
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
		
//		trayIcon.displayMessage("JAKD", "Right-Click Here For Options", TrayIcon.MessageType.NONE);
	}

	public void buttonSelect(JButton button, boolean pinCheck) {

		if (button.equals(button1) && button1.getText().equals("Generate Report")) {
			genReportPanel = new generateReport(conn);
			cardPanel.add(genReportPanel, "genReport");
			cards.show(cardPanel, "genReport");

		}
		else if(button.equals(button1) && button1.getText().equals("POS")) ///////////////////////////////// pos //////////////////////
		{
			if(pinCheck == true)
			{
				posGUI = new PosGui(pin);
				cardPanel.add(posGUI, "POSGui");
				frame.setTitle("POS Screen");
				button4.setText("Logout");
				cards.show(cardPanel, "POSGui");
				logIn.dispose();	
			}
			else
			{
				manager =false;
				logIn();
			}
		}
		else if(button.equals(button2) && button2.getText().equals("Edit User"))
		{
			cards.show(cardPanel, "editUser");
		}
		else if (button.equals(button2) && button2.getText().equals("Admin"))
		{	
			if(pinCheck == true)
			{
				frame.setTitle("Admin Screen");
				button1.setText("Generate Report");
				button2.setText("Edit User");
				button3.setText("Edit Product");
				button4.setText("Logout");
				cards.show(cardPanel, "editUser");
				logIn.dispose();	
			}
			else
			{
				manager =true;
				logIn();
			}
		
		}
		else if(button.equals(button3) && button3.getText().equals("Edit Product"))
		{
			cards.show(cardPanel, "prodSelect");
		}
		else if(button.equals(button3) && button3.getText().equals("Kiosk"))
		{
			new KioskHomeScreen(conn);
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
			db.closeDB();
			System.exit(0);
		}
	}

	public void actionPerformed(ActionEvent e) {

		
		for (int i = 0; i < sideButtonsArray.length; i++) {
			if (e.getSource().equals(sideButtonsArray[i])) {
				buttonSelect(sideButtonsArray[i], false);
				index = i;
			}
		}

		if (e.getSource().equals(elecProd)) {
			cards.show(cardPanel, "editElec");
		} 
		else if (e.getSource().equals(digiProd)) {
			cards.show(cardPanel, "editDigi");
		} 
		else if (e.getSource() == closeBtn) {
			
		}
		else if (e.getSource() == enterPButton)
		{

			if(check() == true){	
				buttonSelect(sideButtonsArray[index], true);}
			else {
				JOptionPane.showMessageDialog(null,"Pin Incorrect","Invalid User",JOptionPane.WARNING_MESSAGE);
				
			}
		}
	}
	
	
	
	public void logIn()
	{
		
		ho = new HomeScreenOperations();
		
		logIn = new JDialog();
		logIn.setTitle("Log In");
		logIn.setVisible(true);
		logIn.setResizable(false);
		logIn.setLocationRelativeTo(null);
		logIn.setSize(160,130);
		logIn.setLayout(new FlowLayout());
		
		enterPassword = new JLabel("Enter Pin:");
		logIn.add(enterPassword);
		jpf = new JPasswordField(10);
		logIn.add(jpf);
		enterPButton = new JButton("Log In");
		enterPButton.addActionListener(this);
		logIn.add(enterPButton);
		
	}
	
	public boolean check()
	{
		boolean go = false;
		
		if(manager != true)
		{
			pin = new String(jpf.getPassword());
			if(ho.getStaffPin(pin)==true){
				go = true;	
			}
			else{
				go = false;
		}	
		}
		else
		{
			pin = new String(jpf.getPassword());
			if(ho.getMangPin(pin)==true){
				go = true;	
			}
			else{
				go = false;
		}	
		}
		return go;
	}

	public static void main(String args[]) {
		new HomeScreen();
	}
}



