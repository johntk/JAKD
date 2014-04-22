package reports;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.border.Border;

import reports.ReportOperations;


public class ReturnsReport
{
	private JFrame frame;
	private JPanel topPanel, mainPanel, topLeftPanel, topRightPanel, headingsPanel, resultsPanel;
	private JLabel dateTo, dateFrom, tranID, saleDate,amount;
	private JTextField textDateTo, textDateFrom;
	private DefaultTableModel dtm;
	private ResultSet rset;
	private JTable table;
	private JScrollPane scrollPane;
	private Font f;
	private GridBagLayout layout;
	private GridBagConstraints gc;
	private	Border space = (Border) BorderFactory.createEmptyBorder(10, 10, 10, 10);
	private	Border line = (Border) BorderFactory.createLineBorder(Color.LIGHT_GRAY);
	private	Border border = BorderFactory.createCompoundBorder(space, line);

	public ReturnsReport(String topDate, String bottomDate, ReportOperations ro) throws SQLException
	{
		layout = new GridBagLayout();
		gc = new GridBagConstraints();
		frame = new JFrame();
		frame.setTitle("Report");
		frame.setSize(600, 700);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		f = new Font("Helvetica", Font.ITALIC, 20);
		String[] colNames = {"Transaction ID","Transaction Date", "Transaction Type","Total Returned", "Employee"};

		topPanel = new JPanel(new GridLayout());
		frame.add(topPanel, BorderLayout.NORTH);

		topLeftPanel = new JPanel(layout);
		topLeftPanel.setBackground(Color.white);
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 1;
		gc.gridheight = 2;
		gc.anchor = GridBagConstraints.CENTER;
		topPanel.add(topLeftPanel,gc);

		dateFrom = new JLabel("Date From");
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 1;
		gc.gridheight = 1;
		dateFrom.setFont(f);
		topLeftPanel.add(dateFrom,gc);

		textDateFrom = new JTextField(12);
		textDateFrom.setBackground(Color.white);
		textDateFrom.setLayout(new BorderLayout());
		gc.gridx = 0;
		gc.gridy = 1;
		gc.gridwidth = 1;
		gc.gridheight = 1;
		textDateFrom.setText(topDate);
		textDateFrom.setHorizontalAlignment(JTextField.CENTER);
		textDateFrom.setBorder(border);
		textDateFrom.setEditable(false);
		topLeftPanel.add(textDateFrom,gc);

		topRightPanel = new JPanel(layout);
		topRightPanel.setBackground(Color.white);
		gc.gridx = 1;
		gc.gridy = 0;
		gc.gridwidth = 1;
		gc.gridheight = 2;
		gc.anchor = GridBagConstraints.CENTER;
		topPanel.add(topRightPanel,gc);

		dateTo = new JLabel("Date To");
		gc.gridx = 1;
		gc.gridy = 0;
		gc.gridwidth = 1;
		gc.gridheight = 1;;
		dateTo.setFont(f);
		topRightPanel.add(dateTo,gc);

		textDateTo = new JTextField(12);
		textDateTo.setBackground(Color.white);
		gc.gridx = 1;
		gc.gridy = 1;
		gc.gridwidth = 1;
		gc.gridheight = 1;
		textDateTo.setText(bottomDate);
		textDateTo.setHorizontalAlignment(JTextField.CENTER);
		textDateTo.setBorder(border);
		textDateTo.setEditable(false);
		topRightPanel.add(textDateTo,gc);



		mainPanel = new JPanel(new BorderLayout());
		frame.add(mainPanel, BorderLayout.CENTER);

		headingsPanel = new JPanel(new GridLayout(0,3));
		headingsPanel.setSize(600, 60);
		headingsPanel.setBackground(Color.red);

		JPanel left = new JPanel(layout);
		tranID = new JLabel("Transaction ID");
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 1;
		gc.gridheight = 1;
		gc.anchor = GridBagConstraints.CENTER;
		left.add(tranID,gc);
		headingsPanel.add(left);

		JPanel middle = new JPanel(layout);
		saleDate = new JLabel("Sale Date");
		middle.setBackground(Color.white);
		gc.gridx = 1;
		gc.gridy = 0;
		gc.gridwidth = 1;
		gc.gridheight = 1;
		gc.anchor = GridBagConstraints.CENTER;
		middle.add(saleDate,gc);
		headingsPanel.add(middle);

		JPanel right = new JPanel(layout);
		amount = new JLabel("Amount");
		gc.gridx = 2;
		gc.gridy = 0;
		gc.gridwidth = 1;
		gc.gridheight = 1;
		gc.anchor = GridBagConstraints.CENTER;
		right.add(amount,gc);
		headingsPanel.add(right);

		resultsPanel = new JPanel(new BorderLayout());

		this.rset = ro.returnTrans();
		int rowCount = 0;
		if(rset.last())
		{
			rowCount = rset.getRow();
			rset.beforeFirst();
			System.out.println(rowCount);
		}
		dtm = new DefaultTableModel(colNames, rowCount );
		table = new JTable(dtm);
		scrollPane = new JScrollPane(table);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER );
		for(int i = 0;i<colNames.length;i++)
		{
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		for(int i = 0;i < rowCount;i++)
		{
			if(rset.next())
			{
				table.setValueAt(rset.getInt(1), i, 0);
				
				Date date = rset.getDate(2); 
				String d = new SimpleDateFormat("dd-MM-YYYY").format(date);
				table.setValueAt(d, i, 1);
				
				table.setValueAt(rset.getString(3), i, 2);
				table.setValueAt(rset.getDouble(4), i, 3);
				table.setValueAt(rset.getString(5), i, 4);
			}
		}
		resultsPanel.add(scrollPane, BorderLayout.CENTER);
		mainPanel.add(resultsPanel, BorderLayout.CENTER);
		frame.setVisible(true);
	}
}













/*ro = new ReportOperations(null,null);
ro.openDB();


frame = new JFrame();
frame.setTitle("Report");
frame.setSize(600, 700);
frame.setLocationRelativeTo(null);
frame.setResizable(false);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setLayout(new BorderLayout());

f = new Font("Helvetica", Font.ITALIC, 40);

String[] colNames = {"Transaction ID","Transaction Date", "Transaction Type","Total Returned", "Employee"};

topPanel = new JPanel(new FlowLayout());
topPanel.setSize(600, 140);
frame.add(topPanel, BorderLayout.NORTH);

title = new JLabel("Low Stock Levels");
title.setFont(f);
topPanel.add(title);

mainPanel = new JPanel(new BorderLayout());
frame.add(mainPanel, BorderLayout.CENTER);

resultsPanel = new JPanel(new BorderLayout());

rset = ro.lowStockReport();
int rowCount = 0;
if(rset.last())
{
	rowCount = rset.getRow();
	rset.beforeFirst();
	System.out.println(rowCount);
}
dtm = new DefaultTableModel(colNames, rowCount);
table = new JTable(dtm);
//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
scrollPane = new JScrollPane(table);
DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
centerRenderer.setHorizontalAlignment(JLabel.CENTER);
for(int i = 0;i<colNames.length;i++)
{
	table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
	table.getColumnModel().getColumn(0).setPreferredWidth(120);
	table.getColumnModel().getColumn(1).setPreferredWidth(150);
	table.getColumnModel().getColumn(2).setPreferredWidth(230);
	table.getColumnModel().getColumn(3).setPreferredWidth(100);
	table.getColumnModel().getColumn(4).setPreferredWidth(100);
}
for(int i = 0;i < rowCount;i++)
{
	if(rset.next())
	{
		table.setValueAt(rset.getInt   (1), i, 0);
		table.setValueAt(rset.getString(2), i, 1);
		table.setValueAt(rset.getString(3), i, 2);
		table.setValueAt(rset.getDouble(4), i, 3);
		table.setValueAt(rset.getString(5), i, 4);
	}
}
resultsPanel.add(scrollPane, BorderLayout.CENTER);
mainPanel.add(resultsPanel, BorderLayout.CENTER);
frame.setVisible(true);
}
}*/

