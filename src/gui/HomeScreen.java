package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import kioskScreens.KioskStartScreen;
import db.*;


public class HomeScreen extends JFrame implements ActionListener 
{

	
	private JFrame frame;
	private final int FRAME_WIDTH = 1148;
	private final int FRAME_HEIGHT = 700;
	private JButton pos, admin, kiosk;
	private JLabel logo,logo2, spacer, welcome;
	private ImageIcon jakd;
	private JTextField prodInput;
	private JPanel sideButtons, homePanel;
	private GridBagLayout layout, layout2;
	private GridBagConstraints gc;
	private Color cl;
	private DBconnection db;
	private Font font = new Font("Verdana", Font.PLAIN, 20);
	

	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	public HomeScreen()
	{
		db = new DBconnection();
		
		////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////        Frame     /////////////////////////
		////////////////////////////////////////////////////////////////////////////
		frame = new JFrame();
		frame.setLayout(new BorderLayout());

		frame.setTitle("JAKD");
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		cl = new Color(240, 240, 240);
		
		//Set the Frame icon
		ImageIcon titleIcon = new ImageIcon("src/resources/titleIcon.png");
		frame.setIconImage(titleIcon.getImage());
		
		
		////////////////////////////////////////////////////////////////////////////////////
		///////////////////////    left panel for buttons   ////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////
		
		
		
		//Border declaration for use on east and west panels on main frame
		Border space = (Border) BorderFactory.createEmptyBorder(10, 10, 10, 10);
		Border line = (Border) BorderFactory.createLineBorder(Color.black);
		Border border = BorderFactory.createCompoundBorder(space, line);
		
		
		//Left side buttons panel
		sideButtons = new JPanel();
		sideButtons.setBackground(cl);
		sideButtons.setLayout(new GridBagLayout());
		sideButtons.setBorder(border);
		frame.add(sideButtons, BorderLayout.WEST);
		
		//Logo and buttons added to left side panel
		
		layout = new GridBagLayout();
		gc = new GridBagConstraints();
		
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
		
		//space between logo and buttons, would rather use a "spacer" here, more research needed
		spacer = new JLabel("");
		
		gc.gridx = 0; 
		gc.gridy = 1; 
		gc.gridwidth = 1; 
		gc.gridheight = 1; 
		gc.weighty = 10.0; 
		gc.weightx = 0.0;
		sideButtons.add(spacer, gc); 
		
		//side button array
		JButton [] sideButtonsArray = {
										pos = new JButton("POS"),
										admin = new JButton("Admin"),
										kiosk = new JButton("Kiosk")
		        					};
		
		//Adding side buttons to side panel
		for(int i = 0; i < sideButtonsArray.length; i++)
        {
			gc.gridx = 0; 
			gc.gridy = i + 2; 
			gc.gridwidth = 1; 
			gc.gridheight = 1; 
			gc.weighty = 0.2; 
			gc.weightx = 0.0;
			sideButtonsArray[i].setIcon(new ImageIcon("src/resources/blueButton.png"));
			sideButtonsArray[i].setFont(new Font("sansserif",Font.BOLD,22));
			sideButtonsArray[i].setPreferredSize(new Dimension(280, 100));
			sideButtonsArray[i].setHorizontalTextPosition(JButton.CENTER);
			sideButtonsArray[i].setVerticalTextPosition(JButton.CENTER);
			sideButtonsArray[i].addActionListener(this);
			sideButtons.add(sideButtonsArray[i],gc);
        }
		
		
		
		
		
		
		
		
		///////////////////////////////////////////////////////////////////////////
//////////////////////////////////////     Home     /////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////
		
		
		
		
		
		
		
		
		
		
		
		homePanel = new JPanel();
		homePanel.setBorder(border);
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

		
		frame.setVisible(true);
		
		
		
	}
	public JPanel getHomePanel()
	{
		return homePanel;
	}
	

	
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()== pos)
		{
			JPanel jp = new JPanel();
			
			frame.setTitle("POS MODE");
			PosGui pg = new PosGui();
			jp = pg.getPanel();
			homePanel.setVisible(false);
			frame.add(jp);
			frame.setVisible(true);

			
			
		}
		else if(e.getSource() == admin)
		{
			frame.setTitle("ADMIN MODE");
			
		}
		else if(e.getSource() == kiosk)
		{
			KioskStartScreen kss = new KioskStartScreen(db);
		}
		else 
		{
			homePanel.setVisible(false);
		}
		
	}



	public static void main (String args[])
	{
		HomeScreen a = new HomeScreen();
	}
}

