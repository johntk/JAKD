package gui;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.*;

import model.Transaction;

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
	private ArrayList <Transaction> tranList;
	private Transaction tran;
	private Frame frame;
	boolean voidd = false;
	
	
	///// cash pop up
	private JDialog jd;
	private JLabel enterAmount;
	private JTextField enterAmountf;
	private JButton enterAm;


	
	
	
	/*private Connection conn;
	private Statement stmt;
	private ResultSet rset;
	private PreparedStatement pstmt;*/
	
	
	
	
	
	
	
	
	
	
	public PosGui(Frame frame)
	{
		
		this.frame = frame;
		po = new POSOperations();
		tranList = new ArrayList<Transaction>();
		tran = new Transaction();

		
		
		///////////////////////////////////////////////////////////////////////////
//////////////////////////////////////     POS Panel     /////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////
		
		//Border declaration for use on east and west panels on main frame
		Border space = (Border) BorderFactory.createEmptyBorder(10, 10, 10, 10);
		Border line = (Border) BorderFactory.createLineBorder(Color.black);
		Border border = BorderFactory.createCompoundBorder(space, line);
		
		
//		this.setBorder(border);
		this.setLayout(new BorderLayout());
		
		//Top panel
		posTop = new JPanel();
		posTop.setLayout(new FlowLayout());
		this.add(posTop, BorderLayout.NORTH);
		
		
		
		
		trans_id = new JLabel("Transaction ID:");
		posTop.add(trans_id);
		trans_idf = new JTextField(10);
		
		trans_idf.setText(tran.getTransID()); ///gets id from method
		
		
		trans_idf.setEditable(false);
		posTop.add(trans_idf);
		
		


		
		
		
		
		////////insert space
		blank = new JLabel("                                                                         ");
		posTop.add(blank);
		
		
		dateField = new JLabel("Date");
		posTop.add(dateField);
		
		dateFieldf = new JTextField(8);
		

		df = new SimpleDateFormat("ddMMMyy HH:mm");
		Calendar now = Calendar.getInstance();
		dateFieldf.setText(df.format(now.getTime()));
		
		dateFieldf.setEditable(false);
		posTop.add(dateFieldf);
		tran.setDate(dateFieldf.getText());
		
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
		this.add(posMiddle, BorderLayout.CENTER);
		
		
		String headings[] = {"ID" , "Description", "Sale/Return", "Price"};
		products = new JTextArea(30,45);
		products.setEditable(false);
		prodBox = new JScrollPane(products);
		prodBox.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		posMiddle.add(prodBox);
		
		
		//right panel

		posRight = new JPanel();
		posRight.setLayout(new GridBagLayout());
		posRight.setBorder(space);
		GridBagConstraints gc = new GridBagConstraints();
		this.add(posRight,BorderLayout.EAST);
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
		this.add(posBottom,BorderLayout.SOUTH);

		enterProdid = new JLabel("Enter Product ID:");
		enterProdid.setFont(new Font("sansserif",Font.BOLD,22));
		posBottom.add(enterProdid);
		
		enterProd = new JTextField(10);
		posBottom.add(enterProd);
		
		enter = new JButton("Enter ");
		enter.addActionListener(this);
		posBottom.add(enter);
		
		////insert space
		blank3 = new JLabel("                                                                    ");
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
		
		
		
		
		
		this.setVisible(true);

		
		
	}
	public JPanel getPanel()
	{
		return posPanel;
	}
		
	
	public void actionPerformed(ActionEvent e) 
	{

		if(e.getSource() == isReturn)
		{
			if(tran.getTransType() == 'S')
			{
				tran.setTransType('R');
				enter.setText("Return");
				voidd = false; ////stops a return that is void
				
				try
				{
				products.setText(products.getText()+ "\n" +tran.displayProduct(enterProd.getText()));
				}
				catch(SQLException er)
				{
					
				}
			}

			
		}
		else if(e.getSource() == isVoid)
		{
			voidd = true;
			enter.setText("Void  ");

		}
		
		
		else if(e.getSource() == complete)
		{
			jd = new JDialog();
			jd.setTitle("Complete Sale");
			jd.setVisible(true);
			jd.setResizable(false);
			jd.setLocationRelativeTo(null);
			jd.setSize(160,130);
			jd.setLayout(new FlowLayout());
			
			enterAmount = new JLabel("Enter Cash:");
			jd.add(enterAmount);
			enterAmountf = new JTextField(10);
			jd.add(enterAmountf);
			enterAm = new JButton("Enter");
			enterAm.addActionListener(this);
			jd.add(enterAm);
			
			
		}
		else if(e.getSource() == enterAm)
		{
			
			
				double enteredAmount = Double.parseDouble(enterAmountf.getText());
				try
				{
				if(enteredAmount > 0 && enteredAmount > tran.getTotalCost())
				{
					totalPrice.setText("Change");
					totalPriceField.setText(Double.toString(enteredAmount - tran.getTotalCost()));
				}
			}
			catch(NumberFormatException ne)
			{
				System.out.println("number entered into enterAmount not right");
			}
			jd.setVisible(false);
			
		}
		
			
	
		
		
	
		else if(e.getSource() == enter)
		{
			if(voidd == false)
			{
				try
					{
						products.setText(products.getText()+ "\n" +tran.displayProduct(enterProd.getText()));
						totalPriceField.setText("€" + Double.toString(tran.getTotalCost()));
						enterProd.setText("");
						
						tranList.add(tran);
						for(int i = 0; i < tranList.size(); i++)
						{
							System.out.println(tranList.get(i).getTransID());
						}
					}
					catch(SQLException sqle)
					{
						System.out.println(sqle);
						System.out.println("cant display product");
					}

			}

			else
			{
				try
				{
					enter.setText("Enter");
					tran.voidProduct(enterProd.getText());
					totalPriceField.setText("€" + Double.toString(tran.getTotalCost()));
					enterProd.setText("");
				
					
				}
				catch(SQLException es)
				{
						
				}
			}
	

		}
		else if (e.getSource() == exit) {
			frame.setVisible(false);
			frame.dispose();
		}
		else
		{
			posPanel.setVisible(false);
		}
	}
}




