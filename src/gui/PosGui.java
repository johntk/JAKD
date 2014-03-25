package gui;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.*;

import db.POSOperations;


//////test message


@SuppressWarnings("serial")
public class PosGui extends JPanel implements ActionListener 
{
	
	////////////////// pos panel variables //////////////////////////////////
	
	private JPanel posPanel,posTop, posMiddle,posRight, posBottom;
	private JLabel trans_id, dateField, totalPrice, enterProdid,blank,blank2,blank3;
	private JTextArea products;
	private JButton complete, isReturn,exit,isVoid,enter;
	private JScrollPane prodBox;
	private JTextField trans_idf,dateFieldf, enterProd;
	private JTextField totalPriceField;
	private ImageIcon close;
	private final DateFormat df;
	private POSOperations po;
	private ResultSet data;
	
	/*private Connection conn;
	private Statement stmt;
	private ResultSet rset;
	private PreparedStatement pstmt;*/
	
	
	
	
	
	
	
	
	
	
	public PosGui()
	{
		

		po = new POSOperations();
		

		
		
		///////////////////////////////////////////////////////////////////////////
//////////////////////////////////////     POS Panel     /////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////
		
		//Border declaration for use on east and west panels on main frame
		Border space = (Border) BorderFactory.createEmptyBorder(10, 10, 10, 10);
		Border line = (Border) BorderFactory.createLineBorder(Color.black);
		Border border = BorderFactory.createCompoundBorder(space, line);
		
		posPanel = new JPanel();
		posPanel.setBorder(border);
		posPanel.setLayout(new BorderLayout());
		
		//Top panel
		posTop = new JPanel();
		posTop.setLayout(new FlowLayout());
		posPanel.add(posTop, BorderLayout.NORTH);
		
		
		
		
		trans_id = new JLabel("Transaction ID:");
		posTop.add(trans_id);
		trans_idf = new JTextField(10);
		
		trans_idf.setText(po.queryTransid()); ///gets id from method
		
		
		trans_idf.setEditable(false);
		posTop.add(trans_idf);
		
		


		
		
		
		
		////////insert space
		blank = new JLabel("                                                                         ");
		posTop.add(blank);
		
		
		dateField = new JLabel("Date");
		posTop.add(dateField);
		
		dateFieldf = new JTextField(8);
		

		df = new SimpleDateFormat("dd/MM/yy HH:mm");
		Calendar now = Calendar.getInstance();
		dateFieldf.setText(df.format(now.getTime()));
		
		dateFieldf.setEditable(false);
		posTop.add(dateFieldf);
		
		/////insert space
		blank2 = new JLabel("                              ");
		posTop.add(blank2);
		
		close = new ImageIcon("src/resources/kioskFiles/images/close.png");
		exit = new JButton("Close",close);
		exit.setBackground(new Color(238,238,238));
		exit.setPreferredSize(new Dimension(100,50));
		exit.addActionListener(this);
		exit.setBorder(null);
		posTop.add(exit);
		
		
		
		
		
		
		//center panel
		posMiddle = new JPanel();
		posPanel.add(posMiddle, BorderLayout.CENTER);
		
		
		
		products = new JTextArea(30,60);
		products.setEditable(false);
		prodBox = new JScrollPane(products);
		prodBox.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		posMiddle.add(prodBox);
		
		
		//right panel

		posRight = new JPanel();
		posRight.setLayout(new GridBagLayout());
		posRight.setBorder(space);
		GridBagConstraints gc = new GridBagConstraints();
		posPanel.add(posRight,BorderLayout.EAST);
		JButton [] posButtons = {
				isReturn = new JButton("Return"),
				isVoid = new JButton("Void"),
				complete = new JButton("Complete Sale")
			};
		
		for(int i = 0; i < posButtons.length; i++)
        {
			gc.gridx = 0; 
			gc.gridy = i + 2; 
			gc.gridwidth = 1; 
			gc.gridheight = 1; 
			gc.weighty = 0.001; 
			gc.weightx = 0.0;
			posButtons[i].setIcon(new ImageIcon("src/resources/blueButton.png"));
			posButtons[i].setFont(new Font("sansserif",Font.BOLD,22));
			posButtons[i].setPreferredSize(new Dimension(250, 100));
			posButtons[i].setHorizontalTextPosition(JButton.CENTER);
			posButtons[i].setVerticalTextPosition(JButton.CENTER);
			posButtons[i].addActionListener(this);
			posRight.add(posButtons[i],gc);
        }
		
		
		
		//bottom panel
		
		posBottom = new JPanel();
		posBottom.setLayout(new FlowLayout());
		posPanel.add(posBottom,BorderLayout.SOUTH);

		enterProdid = new JLabel("Enter Product ID:");
		enterProdid.setFont(new Font("sansserif",Font.BOLD,22));
		posBottom.add(enterProdid);
		
		enterProd = new JTextField(10);
		posBottom.add(enterProd);
		
		enter = new JButton("Enter");
		enter.addActionListener(this);
		posBottom.add(enter);
		
		////insert space
		blank3 = new JLabel("                                                                         ");
		posBottom.add(blank3);
		
		
		totalPrice = new JLabel("Total:");
		totalPrice.setFont(new Font("sansserif",Font.BOLD,22));
		posBottom.add(totalPrice);
		
		totalPriceField = new JTextField(10);
		totalPriceField.setEditable(false);
		Color c2 = new Color(75,255,250);
		totalPriceField.setBackground(c2);
		posBottom.add(totalPriceField);     
		

		/////fill in panel info
		
		
		
		
		
		posPanel.setVisible(true);

		
		
	}
	public JPanel getPanel()
	{
		return posPanel;
	}
		
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == isReturn)
		{
			
		}
		else if(e.getSource() == isVoid)
		{
			
		}
		else if(e.getSource() == complete)
		{
			
		}
		else if(e.getSource() == enter)
		{
				po.openDB();
				
				data = po.queryProduct(enterProd.getText());
				try
				{
					data.next();
					products.setText(products.getText() + data.getString(2));
				}
				catch(SQLException es)
				{
					System.out.println(es);
					System.out.println("ahhhhhhhhhhhhhh");
				}

		}
		else 
		{
			po.closeDB();
			posPanel.setVisible(false);
		}
		
	}
}




