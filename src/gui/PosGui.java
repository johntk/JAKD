package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class PosGui extends JFrame implements ActionListener 
{

	
	//private CardLayout cards;
	private JFrame frame;

	private JButton pos, admin, kiosk;
	private JLabel logo, spacer;
	private JTextField prodInput;
	private JPanel sideButtons, posPanel;
	private GridBagLayout layout;
	private GridBagConstraints gc;
	private Color cl;
	private Font font;
	
	public PosGui()
	{
		////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////        Frame     /////////////////////////
		////////////////////////////////////////////////////////////////////////////
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setTitle("POS Screen");
		frame.setSize(1000,500);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		cl = new Color(240, 240, 240);
		font = new Font("Verdana", Font.PLAIN, 20);
		
		
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
		logo.setIcon(new ImageIcon("src/gui/resources/logo.jpeg"));
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
			sideButtonsArray[i].setIcon(new ImageIcon("src/gui/resources/blueButton.png"));
			sideButtonsArray[i].setFont(new Font("sansserif",Font.BOLD,22));
			sideButtonsArray[i].setPreferredSize(new Dimension(280, 100));
			sideButtonsArray[i].setHorizontalTextPosition(JButton.CENTER);
			sideButtonsArray[i].setVerticalTextPosition(JButton.CENTER);
			sideButtonsArray[i].addActionListener(this);
			sideButtons.add(sideButtonsArray[i],gc);
        }
		
		
		
		
		///////////////////////////////////////////////////////////////////////////
//////////////////////////////////////     POS Panel     /////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////
		
		posPanel = new JPanel();
		posPanel.setBorder(border);
		frame.add(posPanel, BorderLayout.CENTER);
		
		
		frame.setVisible(true);
		
		
		
	}
	
	
	public void actionPerformed(ActionEvent e) 
	{
		
		
	}



	public static void main (String args[])
	{
		PosGui a = new PosGui();
	}
}
