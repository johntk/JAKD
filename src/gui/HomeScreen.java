
package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.*;

import operations.DBconnection;
import operations.EmpOperations;
import operations.HomeScreenOperations;
import operations.ProdOperations;
import model.ElecProdList;
import model.EmployeeList;
import model.DigiProdList;

public class HomeScreen extends JFrame implements ActionListener, ItemListener{


	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private static final int FRAME_WIDTH = 1248;
	private static final int FRAME_HEIGHT = 700;
	private JButton button1, button2, button3, button4, digiProd, elecProd, connect, dbjOK;
	private JLabel logo, logo2, welcome, spacer, uName, pass, dbHeading;

	private JRadioButton tu,lt,cu;
	private ArrayList<JRadioButton> dbButtons;
	private ArrayList<JTextField> dbTextFields;
	private ButtonGroup bg;

	private JFrame dbj;
	private JTextField urlt,urll,urlc,user;
	private JPasswordField password;

	private	JButton[] sideButtonsArray = { button1 = new JButton("POS"),
			button2 = new JButton("Admin"), 
			button3 = new JButton("Kiosk"),
			button4 = new JButton("Close"), };

	private JPanel homePanel, ProdSelect, center, posGUI, cardPanel, digiProdPanel, genReportPanel,
	userPanel, elecProdPanel, dbPanel, dbOptionsPanel, connButtonPanel, innerDBPanel;

	// Border declaration for use on east and west panels on main frame
	private Border space = (Border) BorderFactory.createEmptyBorder(10, 10, 10,10);
	private Border line = (Border) BorderFactory.createLineBorder(Color.black);
	private Border border = BorderFactory.createCompoundBorder(space, line);

	//	Border for the database selection panel
	private Border emptyB = (Border) BorderFactory.createEmptyBorder(10,50,10,50);
	private Border line2 = (Border) BorderFactory.createLineBorder(Color.LIGHT_GRAY);
	private Border dbBorder = BorderFactory.createCompoundBorder(emptyB, line2);

	private BorderLayout layout = new BorderLayout();
	private GridBagConstraints gc = new GridBagConstraints();

	/*	Added a second GridBagConstraints to resolve an issue between
	 *	the Homescreen and JOptionPane interfering with each other
	 * when using the same GridBagConstraints variable
	 */
	private GridBagConstraints gc2 = new GridBagConstraints();
	private Font font = new Font("Verdana", Font.PLAIN, 20);
	private Color cl1;

	private CardLayout cards;
	private EmployeeList employeeList;
	private EmpOperations empOperations;
	private ProdOperations prodOpertaion;
	private DigiProdList  digiProductList;
	private ElecProdList  elecProductList;


	private SystemTray tray;
	public static TrayIcon trayIcon;
	private Image img,im;
	private PopupMenu popup;
	private ImageIcon frameIcon;
	private ImageIcon ti;
	private DBconnection db;
	private Connection conn;
	private int index =0;
	private boolean manager;

	private DateFormat dateFormat;
	private static final String log = "UserLog.txt";
	private Date date;
	private String section;
	private String userNameLog;

	////////////////     log in variables //////////////////////////////////
	private JDialog logIn; 
	private JLabel enterPassword;
	private JPasswordField jpf;
	private JButton enterPButton;
	private String pin;
	private HomeScreenOperations ho;


	public HomeScreen() {
		ti = new ImageIcon(this.getClass().getResource("/resources/trayIcon.png"));
		addSystemTray();
		
		frameIcon = new ImageIcon(this.getClass().getResource("/resources/titleIcon.png"));
		im = frameIcon.getImage();

		db = new DBconnection();

		/*
		 *   Create JFrame for options to connect to a database
		 */
		dbPanel = new JPanel(new BorderLayout());

		dbHeading = new JLabel("Select a Database Connection:");
		dbHeading.setFont(new Font("Calibri",Font.BOLD,25));
		dbHeading.setForeground(new Color(20,120,230));
		dbHeading.setBorder(BorderFactory.createEmptyBorder(5,0,10,0));
		dbHeading.setHorizontalAlignment(SwingConstants.CENTER);
		dbPanel.add(dbHeading,BorderLayout.NORTH);

		innerDBPanel = new JPanel(new GridBagLayout());
		dbPanel.add(innerDBPanel,BorderLayout.CENTER);

		dbOptionsPanel = new JPanel(new GridBagLayout());

		// Create radio buttons to select a database URL to connect to
		tu = new JRadioButton("Tallaght Database");
		tu.addItemListener(this);
		lt = new JRadioButton("Local Database");
		lt.addItemListener(this);
		cu = new JRadioButton("Custom URL");
		cu.addItemListener(this);

		bg = new ButtonGroup();
		bg.add(tu);
		bg.add(lt);
		bg.add(cu);

		dbButtons = new ArrayList<JRadioButton>();
		dbButtons.add(tu);
		dbButtons.add(lt);
		dbButtons.add(cu);

		for(int i=0; i<dbButtons.size();i++)
		{
			gc2.gridx =0;
			gc2.gridy = i;
			gc2.weightx=1.0;
			gc2.weighty=1.0;
			gc2.anchor = GridBagConstraints.NORTHWEST;
			dbOptionsPanel.add(dbButtons.get(i),gc2);
		}

		//		Create text fields for displaying database URLs
		urlt = new JTextField("jdbc:oracle:thin:@//10.10.2.7:1521/global1");
		urlt.setMinimumSize(new Dimension(200,18));
		urlt.setEditable(false);
		urll = new JTextField("jdbc:oracle:thin:HR/@localhost:1521:XE");
		urll.setEditable(false);
		urlc = new JTextField();

		dbTextFields = new ArrayList<JTextField>();
		dbTextFields.add(urlt);
		dbTextFields.add(urll);
		dbTextFields.add(urlc);

		for(int i=0;i<dbTextFields.size();i++)
		{
			gc2.gridx =1;
			gc2.gridy = i;
			gc2.weightx=1.0;
			gc2.weighty=1.0;
			gc2.fill = GridBagConstraints.HORIZONTAL;
			gc2.anchor = GridBagConstraints.NORTHWEST;
			dbOptionsPanel.add(dbTextFields.get(i),gc2);
		}

		JLabel empty1 = new JLabel(" ");
		gc2.gridx =0;
		gc2.gridy = 4;
		gc2.weightx=1.0;
		gc2.weighty=1.0;
		dbOptionsPanel.add(empty1,gc2);

		uName = new JLabel("        Username:");
		gc2.gridx =0;
		gc2.gridy = 5;
		gc2.weightx=1.0;
		gc2.weighty=1.0;
		gc2.anchor = GridBagConstraints.NORTHWEST;
		dbOptionsPanel.add(uName,gc2);

		pass = new JLabel("        Password:");
		gc2.gridx =0;
		gc2.gridy = 6;
		gc2.weightx=1.0;
		gc2.weighty=1.0;
		gc2.anchor = GridBagConstraints.NORTHWEST;
		dbOptionsPanel.add(pass,gc2);

		user = new JTextField();
		gc2.gridx =1;
		gc2.gridy = 5;
		gc2.weightx=1.0;
		gc2.weighty=1.0;
		gc2.fill = GridBagConstraints.HORIZONTAL;
		gc2.anchor = GridBagConstraints.NORTHWEST;
		dbOptionsPanel.add(user,gc2);

		password = new JPasswordField();
		gc2.gridx =1;
		gc2.gridy = 6;
		gc2.weightx=1.0;
		gc2.weighty=1.0;
		gc2.gridwidth = 2;
		gc2.fill = GridBagConstraints.HORIZONTAL;
		gc2.anchor = GridBagConstraints.NORTHWEST;
		dbOptionsPanel.add(password,gc2);

		connButtonPanel = new JPanel();
		connect = new JButton("Connect");
		connect.setBackground(Color.GRAY);
		connect.setForeground(Color.WHITE);
		connect.addActionListener(this);
		connButtonPanel.add(connect);
		gc2.gridx =0;
		gc2.gridy = 7;
		gc2.weightx=1.0;
		gc2.weighty=1.0;
		dbOptionsPanel.add(connButtonPanel,gc2);
		
		dbjOK = new JButton("OK");
		dbjOK.addActionListener(this);
		gc2.gridx =0;
		gc2.gridy = 8;
		gc2.weightx=1.0;
		gc2.weighty=1.0;
		dbOptionsPanel.add(dbjOK,gc2);
		dbjOK.setEnabled(false);
		
		innerDBPanel.add(dbOptionsPanel);

		dbPanel.setBorder(dbBorder);

		dbj = new JFrame();
		dbj.setDefaultCloseOperation(EXIT_ON_CLOSE);
		dbj.setTitle("Connect to Database");
		dbj.setVisible(true);
		dbj.setResizable(false);
		dbj.setSize(600,300);
		dbj.setIconImage(im);
		dbj.setLocationRelativeTo(null);
		dbj.add(dbPanel);
	}
	
	public void showJFrame()
	{
		if(conn != null)
		{
			// Main frame declaration
			frame = new JFrame();
			frame.setLayout(layout);
			frame.setTitle("Home Screen");
			frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
			frame.setLocationRelativeTo(null);
			frame.setIconImage(im);
			frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

			EmpOperations ao = new EmpOperations();
			ProdOperations po = new ProdOperations();
			DigiProdList dpl = new DigiProdList(po);
			ElecProdList epl = new ElecProdList(po);
			EmployeeList el = new EmployeeList(ao);
			ao.setDBconnection(conn);
			po.setDBconnection(conn);

			this.employeeList = el;
			this.empOperations = ao;
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
			logo.setIcon(new ImageIcon(this.getClass().getResource("/resources/logo.png")));
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

			// Home panel
			homePanel = new JPanel();
			homePanel.setLayout(new BorderLayout());
			frame.add(homePanel, BorderLayout.CENTER);

			welcome = new JLabel("Welcome to JAKD!");
			welcome.setFont(font);
			logo2 = new JLabel("");
			logo2.setIcon(new ImageIcon(this.getClass().getResource("/resources/logo2.png")));
			//		logo2.setPreferredSize(new Dimension(400, 120));
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
			cards = new CardLayout();
			cardPanel.setLayout(cards);
			cardPanel.setBackground(cl1);
			cardPanel.add(homePanel, "homePanel");
			cardPanel.setBorder(border);
			cardPanel.setPreferredSize(new Dimension(900, 10));
			frame.add(cardPanel, BorderLayout.CENTER);

			frame.setVisible(true);

			dateFormat = new SimpleDateFormat("HH:mm:ss - dd/MM/yyyy");
		}
		else{System.exit(0);}
	}

	
	//Adds System tray icon in windows 
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
	}

	
	// Creates the login Dialog and home screen operations
	public void logIn()
	{

		ho = new HomeScreenOperations(conn);

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
		enterPButton.setIcon(new ImageIcon(this.getClass().getResource("/resources/blueButton.png")));
		enterPButton.setFont(new Font("sansserif",Font.BOLD,12));
		enterPButton.setPreferredSize(new Dimension(80,30 ));
		enterPButton.setHorizontalTextPosition(JButton.CENTER);
		enterPButton.setVerticalTextPosition(JButton.CENTER);
		enterPButton.addActionListener(this);
		logIn.add(enterPButton);

		logIn.add(enterPButton); 

		/////// allows enter key press "enter" in gui
		enterPButton.registerKeyboardAction(enterPButton.getActionForKeyStroke(
				KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		enterPButton.registerKeyboardAction(enterPButton.getActionForKeyStroke(
				KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
				JComponent.WHEN_FOCUSED);

	}

	
	//Checks if the pin entered is correct,based on the panel being accessed 
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

	
	//Checks the access level of the user inputed pin
	public void privilegeCheck()
	{
		if(check() == true){	
			buttonSelect(sideButtonsArray[index], true);
			
			// FileWriter to log users who log into POS or Admin and record the time and date of login
			try(FileWriter output = new FileWriter(log,true))
			{
				userNameLog = ho.getUserName(pin);
				date = new Date();
				output.write(userNameLog+" logged into "+ section +" @ "+ dateFormat.format(date)+"\n");
			}catch(IOException ioe)
			{
				System.out.println("Error: "+ioe.getMessage());
			}
		}
		else {

			manager = false;
			if(check() != true)
			{
				logIn.dispose();
				JOptionPane.showMessageDialog(null,"Pin Incorrect","",JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				logIn.dispose();
				JOptionPane.showMessageDialog(null,"You do not have sufficent privliges","", JOptionPane.WARNING_MESSAGE);

			}
		}
	}
	
	
	//Changes/creates, buttons and panels based on user input
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
				posGUI = new PosGui(pin,conn);
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
			userPanel = new UserPanel(frame, empOperations, employeeList);
			cardPanel.add(userPanel, "editUser");
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
				welcome.setText("Admin");
				cards.show(cardPanel, "homePanel");
				cardPanel.repaint();
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
			elecProdPanel = new ElecProdPanel(frame, "elec", prodOpertaion, elecProductList);
			digiProdPanel = new DigiProdPanel(frame, "digi", prodOpertaion, digiProductList);
			cardPanel.add(ProdSelect, "prodSelect");
			cardPanel.add(elecProdPanel, "editElec");
			cardPanel.add(digiProdPanel, "editDigi");
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
			welcome.setText("Welcome to JAKD!");
			cards.show(cardPanel, "homePanel");
			cardPanel.repaint();
		}
		else
		{
			db.closeDB();
			System.exit(0);
		}
	}

	
	//Shows the DB username and password Dialog, if no DB connection
	public void dbConnection()
	{
		Boolean select = false;
		String p = new String(password.getPassword());

		for(int i=0;i<dbButtons.size();i++)
		{
			if(dbButtons.get(i).isSelected())
			{
				select = true;
			}
		}
		if(select==false)
		{
			JOptionPane.showConfirmDialog(frame, "Select a Database to connect", "DB Connection Error",JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
			return;
		}

		if(!user.getText().equals("") && !p.equals(""))
		{
			if(cu.isSelected() && urlc.getText().equals(""))
			{
				JOptionPane.showConfirmDialog(frame, "Enter a URL", "DB Connection Error",JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
			}
			else
				for(int i=0;i<dbButtons.size();i++)
				{
					if(dbButtons.get(i).isSelected())
					{
						conn = db.openDB(dbTextFields.get(i).getText(),user.getText(),p);
					}
				}
		}
		else
		{
			JOptionPane.showConfirmDialog(frame, "Enter a Username & Password", "DB Connection Error",JOptionPane.CLOSED_OPTION,JOptionPane.ERROR_MESSAGE);
		}
		if(conn != null)
		{
			dbjOK.setEnabled(true);
		}
	}
	
	public void actionPerformed(ActionEvent e) {


		for (int i = 0; i < sideButtonsArray.length; i++) {
			if (e.getSource().equals(sideButtonsArray[i])) {
				buttonSelect(sideButtonsArray[i], false);
				index = i;
				section = sideButtonsArray[i].getText();
			}
		}

		if (e.getSource().equals(elecProd)) {
			cards.show(cardPanel, "editElec");
		} 
		else if (e.getSource().equals(digiProd)) {
			cards.show(cardPanel, "editDigi");
		} 
		else if (e.getSource() == enterPButton)
		{
			privilegeCheck();
		}

		if(e.getSource()==connect)
		{
			dbConnection();
		}
		
		if(e.getSource() == dbjOK)
		{
			showJFrame();
			dbj.dispose();
		}
	}
	
	public void itemStateChanged(ItemEvent ie)
	{	
		for(int i=0;i<dbButtons.size();i++)
		{
			if(dbButtons.get(i).isSelected())
			{
				dbTextFields.get(i).setForeground(new Color(20,120,230));
			}
			else
			{
				dbTextFields.get(i).setForeground(Color.black);
			}
		}
	}

}



