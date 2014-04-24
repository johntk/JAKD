package reports;
import java.awt.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import operations.ReportOperations;

public class ReportDesignToFrom extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	public ReportDesignToFrom(String topDate, String bottomDate, ReportOperations ro) throws SQLException
	{
		layout = new GridBagLayout();
		gc = new GridBagConstraints();
		this.setLayout(new BorderLayout());
		f = new Font("Helvetica", Font.ITALIC, 20);
		String[] colNames = {"Transaction ID","Transaction Date","Total"};

		topPanel = new JPanel(new GridLayout());
		this.add(topPanel, BorderLayout.NORTH);

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
		this.add(mainPanel, BorderLayout.CENTER);

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
		
		this.rset = ro.salesReportToFromDates();
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
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		for(int i = 0;i < rowCount;i++)
		{
			if(rset.next())
			{
				table.setValueAt(rset.getInt(1), i, 0);
				Date date = rset.getDate(2); 
				String d = new SimpleDateFormat("dd-MM-YYYY").format(date);
				table.setValueAt(d, i, 1);
				table.setValueAt(rset.getString(3), i, 2);
			}
		}
		resultsPanel.add(scrollPane, BorderLayout.CENTER);
		mainPanel.add(resultsPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}
}