
package reports;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

class DialogBox
{
	private JPanel reportSelect;
	private JRadioButton qReport,toFromSalesReport, returnsTrans, currentStock, lowStock;
	private Calender c;
	private CurrentStockReport csr;
	private LowStockReport lsr;
	private ReportOperations ro;
	private  generateReport grp;
	private QuartReports qr;

	public DialogBox(ReportOperations r, generateReport gr)
	{
		ro = r;
		this.grp = gr;
		JPanel reportSelect = new JPanel(new GridLayout(5,1));
		reportSelect.setPreferredSize(new Dimension(240,150));
		qReport = new JRadioButton("Quarterly Revenue Report");
		reportSelect.add(qReport);
		toFromSalesReport = new JRadioButton("Sales Transactions");
		reportSelect.add(toFromSalesReport,BorderLayout.EAST);
		returnsTrans = new JRadioButton("Returns Transactions");
		reportSelect.add(returnsTrans);
		currentStock = new JRadioButton("Current Stock Report");
		reportSelect.add(currentStock);
		lowStock = new JRadioButton("Low Stock Report");
		reportSelect.add(lowStock);

		reportSelect.setVisible(true);

		JOptionPane.showOptionDialog(null, reportSelect, "Select Report Type",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				null, null);

		if(qReport.isSelected())
		{
			qr = new QuartReports(ro);
			grp.removeAll();
			grp.add(qr);
			grp.revalidate();
			
		}
		if(toFromSalesReport.isSelected())
		{
			c = new Calender(ro, grp);
		}
		if(returnsTrans.isSelected())
		{
			c = new Calender(ro, grp);
		}
		if(currentStock.isSelected())
		{
			try 
			{
				csr = new CurrentStockReport(ro);

				grp.removeAll();
				grp.add(csr);
				grp.revalidate();

			} 
			catch (SQLException e) 
			{	
				e.printStackTrace();
			}
		}
		if(lowStock.isSelected())
		{
			try 
			{
				lsr = new LowStockReport(ro);
				grp.removeAll();
				grp.add(lsr);
				grp.revalidate();
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}


	}
	public class Calender extends JDialog implements ActionListener
	{ 
		private JDialog frame;
		private ReturnsReport rr;
		private CurrentStockReport csr;
		private ReportDesignToFrom rd;
		private ReportOperations ro;
		private generateReport gr;
		private JRadioButton totalSales, lowStock, reportType4, reportType5;
		private JComboBox<String> topDate1, topDate2, topDate3, bottomDate1, bottomDate2, bottomDate3;
		private JLabel to, from;
		private JButton ok, cancel;
		private GridBagConstraints gc;
		private Calender c;
		private DialogBox d;
		String topDate;
		String bottomDate;
		private Border space = (Border) BorderFactory.createEmptyBorder(3, 3, 3, 3);
		private  generateReport grp;

		public Calender(ReportOperations r, generateReport gr)
		{
			ro = r;
			this.grp = gr;
			gc = new GridBagConstraints();
			frame = new JDialog();
			frame.setTitle("Select Date");
			frame.setLayout(new GridBagLayout());
			frame.setSize(420, 210);
			frame.setLocationRelativeTo(null);
			//			frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

			String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
			String[] days31 = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15"
					,"16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
			String[] years = {"13","14"};

			to = new JLabel("To");
			gc.gridx = 1;
			gc.gridy = 0;
			gc.anchor = GridBagConstraints.NORTH;
			frame.add(to,gc);

			topDate1 = new JComboBox<String>(months);
			topDate1.setBorder(space);
			gc.gridx = 0;
			gc.gridy = 1;
			topDate1.setEditable(true);
			frame.add(topDate1,gc);
			topDate1.addActionListener(this);

			topDate2 = new JComboBox<String>(days31);
			topDate2.setBorder(space);
			gc.gridx = 1;
			gc.gridy = 1;
			topDate2.setEditable(true);
			frame.add(topDate2,gc);
			topDate2.addActionListener(this);

			topDate3 = new JComboBox<String>(years);
			topDate3.setBorder(space);
			gc.gridx = 2;
			gc.gridy = 1;
			topDate3.setEditable(true);
			frame.add(topDate3,gc);
			topDate3.addActionListener(this);
			/////////////////////////////
			///		Bottom Section	  ///
			/////////////////////////////
			from = new JLabel("From");
			gc.gridx = 1;
			gc.gridy = 2;
			frame.add(from,gc);

			bottomDate1 = new JComboBox<String>(months);
			bottomDate1.setBorder(space);
			gc.gridx = 0;
			gc.gridy = 3;
			bottomDate1.setEditable(true);
			frame.add(bottomDate1,gc);
			bottomDate1.addActionListener(this);

			bottomDate2 = new JComboBox<String>(days31);
			bottomDate2.setBorder(space);
			gc.gridx = 1;
			gc.gridy = 3;
			bottomDate2.setEditable(true);
			frame.add(bottomDate2,gc);
			bottomDate2.addActionListener(this);

			bottomDate3 = new JComboBox<String>(years);
			bottomDate3.setBorder(space);
			gc.gridx = 2;
			gc.gridy = 3;
			bottomDate3.setEditable(true);
			frame.add(bottomDate3,gc);
			bottomDate3.addActionListener(this);

			ok = new JButton("OK");
			gc.gridx = 1;
			gc.gridy = 4;
			gc.anchor = GridBagConstraints.WEST;
			ok.setBorder(space);
			frame.add(ok,gc);
			ok.addActionListener(this);

			cancel = new JButton("Close");
			gc.gridx = 1;
			gc.gridy = 4;
			gc.anchor = GridBagConstraints.EAST;
			cancel.setBorder(space);
			frame.add(cancel,gc);
			cancel.addActionListener(this);

			frame.setVisible(true);


		}
		public void actionPerformed(ActionEvent ae)
		{
			if(ae.getSource() == cancel)
			{
				frame.dispose();
			}
			if(ae.getSource() == ok && toFromSalesReport.isSelected())
			{
				topDate = topDate2.getSelectedItem()+"-"+topDate1.getSelectedItem()+"-"+topDate3.getSelectedItem();
				bottomDate = bottomDate2.getSelectedItem()+"-"+bottomDate1.getSelectedItem()+"-"+bottomDate3.getSelectedItem();
				//				ro = new ReportOperations(topDate,bottomDate);
				//				ro.openDB();

				ro.setTopDate(topDate);
				ro.setBottomDate(bottomDate);
				ro.salesReportToFromDates();
				try 
				{

					rd = new ReportDesignToFrom(topDate,bottomDate,ro);
					grp.removeAll();
					grp.add(rd);
					grp.revalidate();

					//				
				} 
				catch (SQLException e) 
				{	
					e.printStackTrace();
				}
			}
			else if(ae.getSource() == ok && returnsTrans.isSelected())
			{
				topDate = topDate2.getSelectedItem()+"-"+topDate1.getSelectedItem()+"-"+topDate3.getSelectedItem();
				bottomDate = bottomDate2.getSelectedItem()+"-"+bottomDate1.getSelectedItem()+"-"+bottomDate3.getSelectedItem();
				//				ro = new ReportOperations(topDate,bottomDate);
				//				ro.openDB();

				ro.setTopDate(topDate);
				ro.setBottomDate(bottomDate);
				ro.returnTrans();
				try 
				{
					rr = new ReturnsReport(topDate,bottomDate,ro);
					grp.removeAll();
					grp.add(rr);
					grp.revalidate();
				} 
				catch (SQLException e) 
				{	
					e.printStackTrace();
				}
			}
		}
	}
}
/*public Calender()
	{
		gc = new GridBagConstraints();
		frame = new JFrame();
		frame.setTitle("Select Date");
		frame.setLayout(new GridBagLayout());
		frame.setSize(420, 210);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

		String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		String[] days31 = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15"
				,"16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
		String[] years = {"13","14"};

		to = new JLabel("To");
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.NORTH;
		frame.add(to,gc);

		topDate1 = new JComboBox<String>(months);
		topDate1.setBorder(space);
		gc.gridx = 0;
		gc.gridy = 1;
		topDate1.setEditable(true);
		frame.add(topDate1,gc);
		topDate1.addActionListener(this);

		topDate2 = new JComboBox<String>(days31);
		topDate2.setBorder(space);
		gc.gridx = 1;
		gc.gridy = 1;
		topDate2.setEditable(true);
		frame.add(topDate2,gc);
		topDate2.addActionListener(this);

		topDate3 = new JComboBox<String>(years);
		topDate3.setBorder(space);
		gc.gridx = 2;
		gc.gridy = 1;
		topDate3.setEditable(true);
		frame.add(topDate3,gc);
		topDate3.addActionListener(this);
		/////////////////////////////
		///		Bottom Section	  ///
		/////////////////////////////
		from = new JLabel("From");
		gc.gridx = 1;
		gc.gridy = 2;
		frame.add(from,gc);

		bottomDate1 = new JComboBox<String>(months);
		bottomDate1.setBorder(space);
		gc.gridx = 0;
		gc.gridy = 3;
		bottomDate1.setEditable(true);
		frame.add(bottomDate1,gc);
		bottomDate1.addActionListener(this);

		bottomDate2 = new JComboBox<String>(days31);
		bottomDate2.setBorder(space);
		gc.gridx = 1;
		gc.gridy = 3;
		bottomDate2.setEditable(true);
		frame.add(bottomDate2,gc);
		bottomDate2.addActionListener(this);

		bottomDate3 = new JComboBox<String>(years);
		bottomDate3.setBorder(space);
		gc.gridx = 2;
		gc.gridy = 3;
		bottomDate3.setEditable(true);
		frame.add(bottomDate3,gc);
		bottomDate3.addActionListener(this);

		ok = new JButton("OK");
		gc.gridx = 1;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.WEST;
		ok.setBorder(space);
		frame.add(ok,gc);
		ok.addActionListener(this);

		cancel = new JButton("Cancel");
		gc.gridx = 1;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.EAST;
		cancel.setBorder(space);
		frame.add(cancel,gc);
		cancel.addActionListener(this);

		frame.setVisible(true);

	}*/

/*public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == cancel)
		{
			System.exit(0);
		}
		if(ae.getSource() == ok && d.toFromSalesReport.isSelected())
		{
			topDate = topDate2.getSelectedItem()+"-"+topDate1.getSelectedItem()+"-"+topDate3.getSelectedItem();
			bottomDate = bottomDate2.getSelectedItem()+"-"+bottomDate1.getSelectedItem()+"-"+bottomDate3.getSelectedItem();
			ro = new ReportOperations(topDate,bottomDate);
			ro.openDB();
			ro.salesReportToFromDates();
			try 
			{
				rd = new ReportDesignToFrom(topDate,bottomDate,ro);
			} 
			catch (SQLException e) 
			{	
				e.printStackTrace();
			}
		}
		if(ae.getSource() == ok && gr.getReturnsTrans().isSelected())
		{
			topDate = topDate2.getSelectedItem()+"-"+topDate1.getSelectedItem()+"-"+topDate3.getSelectedItem();
			bottomDate = bottomDate2.getSelectedItem()+"-"+bottomDate1.getSelectedItem()+"-"+bottomDate3.getSelectedItem();
			ro = new ReportOperations(topDate,bottomDate);
			ro.openDB();
			//ro.reportToFromDates();
		}
	}
	/*public void createReport() throws SQLException
	{
		JPanel reportSelect = new JPanel();
		toFromSalesReport = new JRadioButton("Sales Transactions");
		reportSelect.add(toFromSalesReport);
		lowStock = new JRadioButton("Low Stock Report");
		reportSelect.add(lowStock);
		returnsTrans = new JRadioButton("Returns Transactions");
		reportSelect.add(returnsTrans);

		JOptionPane.showOptionDialog(null, reportSelect, "Select Report Type",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				null, null);

		/*if(toFromSalesReport.isSelected())
		{
			rd = new ReportDesignToFrom(bottomDate, bottomDate, ro);
		}
		if(lowStock.isSelected())
		{
			csr = new CurrentStockReport();
		}
		if(returnsTrans.isSelected())
		{
			//c = new Calender();
		}
	}
	public String getBottomDate() {
		return bottomDate;
	}

	public void setBottomDate(String bottomDate) {
		this.bottomDate = bottomDate;
	}

	public String getTopDate() 
	{
		return topDate;
	}
	public void setTopDate(String topDate) {
		this.topDate = topDate;
	}

	public static void main(String[] args)
	{
		Calender a = new Calender();
	}*/



//String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};