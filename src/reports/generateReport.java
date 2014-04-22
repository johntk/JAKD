package reports;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormatSymbols;

import javax.swing.*;
import javax.swing.border.Border;


import reports.CurrentStockReport;
import reports.DialogBox.Calender;
//import Reports.Calender;
import db.DBconnection;
public class generateReport extends JFrame implements ActionListener, ItemListener 
{
	private DialogBox d;
	private CurrentStockReport lsr;
	private static final long serialVersionUID = 1L;
	private DBconnection db;
	private CardLayout cards;
	private JFrame frame;
	private static final int FRAME_WIDTH = 1148;
	private static final int FRAME_HEIGHT = 827;
	private JButton genReportBtn, editUserBtn, editProdBtn, financialManagBtn;
	private JRadioButton toFromSalesReport, returnsTrans, totalSales, lowStock, reportType4, reportType5;
	private JPanel report1;
	private JScrollPane scrollPane;
	private JComboBox date1, date2, date3;
	private BorderLayout layout = new BorderLayout();
	private GridBagConstraints gc = new GridBagConstraints();
	private Color cl;
	//Border declaration for use on east and west panels on main frame
	private	Border space = (Border) BorderFactory.createEmptyBorder(10, 10, 10, 10);
	private	Border line = (Border) BorderFactory.createLineBorder(Color.black);
	private	Border border = BorderFactory.createCompoundBorder(space, line);

	private JLabel logo, spacer, dateTo, dateFrom;
	private JTextField textDateTo, textDateFrom;
	
	private JPanel cardPanel, digiProdPanel, genReportPanel, userPanel,
	elecProdPanel, financialPanel, topPanel;

	public generateReport()
	{
		this.db = db;
		frame = new JFrame();
		frame.setLayout(layout);
		frame.setTitle("Report Screen");
		frame.setSize(1000,500);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		cards = new CardLayout();
		cl = new Color(240, 240, 240);

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
				genReportBtn = new JButton("Generate Report"),
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
		genReportPanel = new JPanel();
		topPanel = new JPanel(new GridLayout(0,2));
		dateFrom = new JLabel("Date From");
		topPanel.add(dateFrom);
		dateTo = new JLabel("Date To");
		topPanel.add(dateTo);
		//genReportPanel.add();
		genReportPanel.setLayout(new FlowLayout());
		genReportPanel.add(topPanel);
		
		
		
		scrollPane = new JScrollPane(genReportPanel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		cardPanel.add(scrollPane,BorderLayout.CENTER);
		
		JButton test = new JButton("Test");
		test.addActionListener(this);
		genReportPanel.add(test);
		
		cards = new CardLayout();
		cardPanel.setLayout(cards);
		cardPanel.add(genReportPanel, "genReport");
		//cardPanel.add(userPanel, "editUser");
		//cardPanel.add(elecProdPanel, "editElec");
		//cardPanel.add(digiProdPanel, "editDigi");
		//cardPanel.add(financialPanel, "financial");
		cardPanel.setBorder(border);
		cardPanel.setPreferredSize(new Dimension(820, 10));
		frame.add(cardPanel, BorderLayout.EAST);
		frame.setVisible(true);
	}

	public void itemStateChanged(ItemEvent arg0)
	{

	}
	public void actionPerformed(ActionEvent ae) 
	{
		if(ae.getSource() == genReportBtn)
		{
			cardPanel.add(genReportPanel);
			DialogBox d = new DialogBox();
			
		}

	}
	/*public static void main(String[] args) 
	{
		generateReport a = new generateReport();

	}*/
}
