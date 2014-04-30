package gui;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import operations.POSOperations;
import model.Transaction;



public class PosGui extends JPanel implements ActionListener 
{
	
	private static final long serialVersionUID = 1L;
	
	private JPanel posPanel,posTop, posMiddle,posRight, posBottom;
	private JLabel trans_id, dateField, totalPrice, enterProdid,blank,blank2,blank3,currentUser;
	private JButton complete, isReturn,isVoid,enter;
	private JScrollPane prodBox;
	private JTextField trans_idf,dateFieldf, enterProd,currentUserf;
	private JTextField totalPriceField;
	private final DateFormat df;
	private Calendar now;
	private POSOperations po;
	private ResultSet data;
	private ArrayList <Transaction> tranList;
	private Transaction tran;
	private Connection conn;
	
	private double totalCost = 0;
	private boolean quantity = false;
	private int quanPoint;
	private boolean prodExists;
	private int prodCount;
	private DecimalFormat decf = new DecimalFormat(" € #####.##");
	private DefaultTableModel dtm ;
	private String colNames[] = {"ID" , "Description", "Sale/Return","Quantity", "Price"};
	private JTable table;
	private boolean continueWithTran = true;
	private String empID, pin;
	private boolean voidd = false;
	private boolean returnn = false;
	private int numRows;
	
	
	///// cash pop up
	private JDialog jd;
	private JLabel enterAmount;
	private JTextField enterAmountf;
	private JButton enterAm;
	private double tempTotal;




	
	
	
	public PosGui(String pin,Connection c)
	{
		conn = c;
		this.pin = pin;
	
		po = new POSOperations(conn);
		tranList = new ArrayList<Transaction>();

		empID = po.getEmployeeID(pin);
	
		
		//Border declaration for use on east and west panels on main frame
		Border space = (Border) BorderFactory.createEmptyBorder(10, 10, 10, 10);
		
		
		
		

		this.setLayout(new BorderLayout());
		
		//Top panel
		posTop = new JPanel();
		posTop.setLayout(new GridLayout(1,3));
		this.add(posTop, BorderLayout.NORTH);
		
		JPanel tright, tcentre,tleft;
		tright = new JPanel();
		tcentre = new JPanel();
		tleft = new JPanel();
		
		posTop.add(tleft);
		posTop.add(tcentre);
		posTop.add(tright);
		
		trans_id = new JLabel("Transaction ID:");
		tright.add(trans_id);
		trans_idf = new JTextField(10);
		
		trans_idf.setText(po.queryTransid()); ///gets id from method
		
		
		trans_idf.setEditable(false);
		tright.add(trans_idf);
		


		///// set up current time
		dateField = new JLabel("Date");
		tcentre.add(dateField);
		
		dateFieldf = new JTextField(8);
		df = new SimpleDateFormat("ddMMMyy");
		now = Calendar.getInstance();
		dateFieldf.setText(df.format(now.getTime()));
		
		
		dateFieldf.setEditable(false);
		tcentre.add(dateFieldf);
		
		currentUser = new JLabel("User: ");
		tleft.add(currentUser);
		currentUserf = new JTextField(8);
		currentUserf.setText(po.getUserName(empID));
		currentUserf.setEditable(false);
		tleft.add(currentUserf);

		/////////////////////////////////center panel//////////////////////
		posMiddle = new JPanel();
		posMiddle.setLayout(new BorderLayout());
		posMiddle.setBackground(Color.white);
		this.add(posMiddle, BorderLayout.CENTER);
		
		
		//// table set up for products on transaction
		dtm= new DefaultTableModel(colNames,numRows); 
	    table = new JTable(dtm);
	    table.setShowGrid(false);
	    table.setShowVerticalLines(true);
		prodBox = new JScrollPane(table);
		prodBox.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//prodBox.getViewport().setBackground(Color.white); // product area background colour
		posMiddle.add(prodBox,BorderLayout.CENTER);
		
		
		//table.getParent().setBackground(Color.black);
		
		

		
		
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

		JLabel spacer = new JLabel("");
		gc.gridy = 1;
		gc.weighty = 5.0;
		posRight.add(spacer, gc);
		for(int i = 0; i < posButtons.length; i++)
        {
			gc.gridx = 0; 
			gc.gridy = i + 2; 
			gc.gridwidth = 1; 
			gc.gridheight = 1; 
			gc.weighty = 0.2; 
			gc.weightx = 0.0;
			posButtons[i].setIcon(new ImageIcon(this.getClass().getResource("/resources/blueButton.png")));
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
		
		enter = new JButton("Enter "); // set enter to be blue button
		enter.setIcon(new ImageIcon(this.getClass().getResource("/resources/blueButton.png")));
		enter.setFont(new Font("sansserif",Font.BOLD,12));
		enter.setPreferredSize(new Dimension(80,30 ));
		enter.setHorizontalTextPosition(JButton.CENTER);
		enter.setVerticalTextPosition(JButton.CENTER);
		enter.addActionListener(this);
		posBottom.add(enter);

		/////// allows enter key press "enter" in gui
		enter.registerKeyboardAction(enter.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
		
        enter.registerKeyboardAction(enter.getActionForKeyStroke(
                KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                JComponent.WHEN_FOCUSED);
		
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
	
	
	

	///////////////////////////////////////////////////////////////////////////////////////
	///////////////////////               action performed       ////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////
		
	
	public void actionPerformed(ActionEvent e) 
	{

		if(e.getSource() == isReturn)
		{
			voidd = false;
			returnn = true;
			enter.setText("Return");
			
			
		}
		else if(e.getSource() == isVoid)
		{
			returnn = false;
			voidd = true;
			enter.setText("Void  ");

		}
		
		
		else if(e.getSource() == complete) // completes a tranaction
		{
			if(tranList.size() > 0)
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
				enterAm.setIcon(new ImageIcon(this.getClass().getResource("/resources/blueButton.png")));
				enterAm.setFont(new Font("sansserif",Font.BOLD,12));
				enterAm.setPreferredSize(new Dimension(80,30 ));
				enterAm.setHorizontalTextPosition(JButton.CENTER);
				enterAm.setVerticalTextPosition(JButton.CENTER);
				enterAm.addActionListener(this);
				jd.add(enterAm);
			}
			
		}
		
		
		else if(e.getSource() == enterAm)
		{
			
			double cashEntered = Double.parseDouble(enterAmountf.getText());
			if( tempTotal > cashEntered)
			{
				//products.setText(products.getText() + "Cash\t\t " + cashEntered);
				totalPriceField.setText(decf.format((tempTotal - cashEntered)));
				tempTotal -= cashEntered;
				jd.setVisible(false);
				
			}
			else
			{
				totalPrice.setText("Change:");
				totalPriceField.setText(decf.format((cashEntered - tempTotal)));
				jd.setVisible(false);
				po.insertTran(tranList);
				po.updateCurrentStock(tranList);
								
				newTran(); // clears all data for a new transaction
				
				
				

				
			}
			

			
		}
		
			
	
		
		
	
		else if(e.getSource() == enter)
		{
			
			tran = new Transaction(conn);
			
			///////////////////// can't have return and sale of same product in same tran
			if (voidd == false)
			{
				for(int i = 0;i < tranList.size();i++) //check if product is in sale
				{
					if(returnn == false && enterProd.getText().equals(tranList.get(i).getProdID()))
					{
						if(tranList.get(i).getTransType().equals("R"))
						{
							JOptionPane.showMessageDialog(null,"Can't buy whats already returned","Invalid Input",JOptionPane.WARNING_MESSAGE);
							continueWithTran = false;
							enterProd.setText("");
						}
							
					}
					else if(returnn == true && enterProd.getText().equals(tranList.get(i).getProdID()))
					{
						if(tranList.get(i).getTransType().equals("S"))
						{
							JOptionPane.showMessageDialog(null,"Can't return whats already a sale","Invalid Input",JOptionPane.WARNING_MESSAGE);
							continueWithTran = false;
							enterProd.setText("");
						}
					}
	
				}
			}
			
			
			if(continueWithTran == true)
			{
				if (voidd == true)
				{
					
					System.out.println("in void product");
					for(int i = 0;i < tranList.size();i++) //check if product is in sale
					{
						if(enterProd.getText().equals(tranList.get(i).getProdID()))
						{
							prodExists = true;
							prodCount = i;
							
							
						}
					}
				
					if(prodExists == true)
					{
	
						
						if(tranList.get(prodCount).getQuantity() == 1) // void if there is a qty more than 1 in sale
						{
							if(tranList.get(prodCount).getTransType().equals("S"))
							{
								totalCost -=  tranList.get(prodCount).getTotalCost();
								tempTotal = totalCost;
								totalPriceField.setText(decf.format(totalCost));
							}
							else
							{
								totalCost +=  tranList.get(prodCount).getTotalCost();
								tempTotal = totalCost;
								totalPriceField.setText(decf.format(totalCost));
							}

							numRows-=1;
							
							
							tranList.remove(prodCount);
							
							
						}
						else
						{
							tranList.get(prodCount).setTotalCost(tranList.get(prodCount).getTotalCost() - (tranList.get(prodCount).getTotalCost()/tranList.get(prodCount).getQuantity()));
							tranList.get(prodCount).setQuantity(tranList.get(prodCount).getQuantity() - 1);
							
							totalCost -= tranList.get(prodCount).getTotalCost()/tranList.get(prodCount).getQuantity() ;
							tempTotal = totalCost;
							totalPriceField.setText(decf.format(totalCost));
	
						}
					
					displayProducts();
					enterProd.setText("");
					voidd = false;
					prodExists = false;
					enter.setText("Enter ");
							
	
					}
						
					else //if prodExists is not true
					{
						JOptionPane.showMessageDialog(null,"Product is not in this sale!","Invalid Input",JOptionPane.WARNING_MESSAGE);
	
					}
					voidd = false;
					prodExists = false;
					enter.setText("Enter ");
					
				}
				else if(returnn == true)
				{
					if(checkQty() == false)
					{
						try
						{
						
							data = po.displayProduct(enterProd.getText());
							data.next();
							
							tran.setEmpID(empID);
							tran.setDate(dateFieldf.getText());
							tran.setProdID(data.getString(1));
							tran.setDesc(data.getString(2));
							double prodCost = Double.parseDouble(data.getString(3));
							tran.setTransType("R");
							tran.setQuantity(1);
							tran.setTotalCost(prodCost);
							
							totalCost =  totalCost - prodCost;
							tempTotal = totalCost;
							
		
							totalPriceField.setText(decf.format(totalCost));
							enterProd.setText("");
							tranList.add(tran);
							numRows+=1;
							displayProducts();
				
							
						}
						catch(SQLException es)
						{
							
						}
					}
					returnn = false;
					enter.setText("Enter ");
				}
				
				else /////normal enter product
				{
					
					if(checkQty()== false)
					{
						
						try
							{
								
								ResultSet data;
								data = po.displayProduct(enterProd.getText());
								data.next();
								
								tran.setDate(dateFieldf.getText());
								
								tran.setEmpID(empID);
								tran.setProdID(data.getString(1));
								tran.setDesc(data.getString(2));
								double prodCost = Double.parseDouble(data.getString(3));
								tran.setTotalCost(prodCost);
								tran.setQuantity(1);

								totalCost = prodCost + totalCost;
								tempTotal = totalCost;
								tranList.add(tran);
								
								numRows+=1;
								displayProducts();

								
								
								totalPriceField.setText(decf.format(totalCost));
								enterProd.setText("");
								
								
								
								
								for(int i = 0; i < tranList.size(); i++) ///test arraylist
								{
									System.out.println(tranList.get(i).getProdID() + " " + tranList.get(i).getTransType());
								}
							
							}
						
							catch(SQLException sqle)
							{
								System.out.println(sqle);
								System.out.println("cant display product");
							}
					}
				}
			}
			continueWithTran = true;
				
		}
			

		
	
	
	}
	
	public void displayProducts() // prints out all products in sale/return to gui
	{
		
		dtm.setNumRows(numRows);
		for(int i = 0; i < (tranList.size());i++)
		{
			dtm.setValueAt("", i, 0);
			dtm.setValueAt("", i, 1);
			dtm.setValueAt("", i, 2);
			dtm.setValueAt("", i, 3);
			dtm.setValueAt("", i, 4);
		}
		
		for(int i = 0; i < tranList.size();i++)
		{
	
				dtm.setValueAt(tranList.get(i).getProdID(), i, 0);
				dtm.setValueAt(tranList.get(i).getDesc(), i, 1);
				dtm.setValueAt(tranList.get(i).getTransType(), i, 2);
				dtm.setValueAt(tranList.get(i).getQuantity(), i, 3);
				dtm.setValueAt(decf.format(tranList.get(i).getTotalCost()), i, 4);

		}
		
	}
	
	public boolean checkQty() // checks if the product is in the sale and adds quantity
	{
		for(int i = 0; i< tranList.size(); i++)
		{
			if (tranList.get(i).getProdID().equals(enterProd.getText()))
			{
				System.out.println("check qty method!!!!!!!");
				quantity = true;
				quanPoint = i;
			}
		}
		
		if (quantity == true)
		{
			int quan;
			
			
			
			if(tranList.get(quanPoint).getTransType().equals("S"))
			{
			

				
				double singleProdPrice = (tranList.get(quanPoint).getTotalCost()) / (tranList.get(quanPoint).getQuantity());
				tranList.get(quanPoint).setTotalCost((tranList.get(quanPoint).getTotalCost()) + singleProdPrice);
				totalCost = totalCost + singleProdPrice;
				tempTotal = totalCost;
				tranList.get(quanPoint).setQuantity(tranList.get(quanPoint).getQuantity() + 1);
				totalPriceField.setText(decf.format(totalCost));
				enterProd.setText("");
				

				
				displayProducts();
				
			}
			else
			{
				double singleProdPrice = (tranList.get(quanPoint).getTotalCost()) / (tranList.get(quanPoint).getQuantity());
				tranList.get(quanPoint).setTotalCost((tranList.get(quanPoint).getTotalCost()) + singleProdPrice);
				totalCost = totalCost - singleProdPrice;
				tempTotal = totalCost;
				totalPriceField.setText(decf.format(totalCost));
				
				quan = tranList.get(quanPoint).getQuantity() + 1;
				System.out.println("Quantity: " + quan);
				tranList.get(quanPoint).setQuantity(quan);
				displayProducts();
				
				
			}
		quantity = false;	
		return true;
			
			
			
			
		}
		else 
		{
			return false;
		}
	}
	
	public void newTran()
	{
		JOptionPane.showMessageDialog(null,"Click Ok For New Transaction","New Transaction",JOptionPane.WARNING_MESSAGE);

		trans_idf.setText(po.queryTransid());
		dateFieldf.setText(df.format(now.getTime()));
		totalCost = 0;
		tempTotal = 0;
		totalPrice.setText("Total:");
		totalPriceField.setText("");
		tranList.clear();
		numRows=0;
		
		
		for(int i = 0; i < dtm.getRowCount();i++)
		{
			dtm.setValueAt("", i, 0);
			dtm.setValueAt("", i, 1);
			dtm.setValueAt("", i, 2);
			dtm.setValueAt("", i, 3);
			dtm.setValueAt("", i, 4);
		}
		
		
	}
	

}






