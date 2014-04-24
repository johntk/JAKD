package reports;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import operations.ReportOperations;

public class CurrentStockReport extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ReportOperations ro;
	private JPanel topPanel, mainPanel, resultsPanel;
	private JLabel title;
	private DefaultTableModel dtm;
	public ResultSet rset;
	private JTable table;
	private JScrollPane scrollPane;
	private Font f;

	public CurrentStockReport(ReportOperations r) throws SQLException
	{
		ro = r;
		this.setLayout(new BorderLayout());
		
		f = new Font("Helvetica", Font.ITALIC, 40);
		
		String[] colNames = {"Product ID","Description","Product Type","Current Stock","Sale Price"};

		topPanel = new JPanel(new FlowLayout());
		this.add(topPanel, BorderLayout.NORTH);
		
		title = new JLabel("Current Stock");
		title.setFont(f);
		topPanel.add(title);

		mainPanel = new JPanel(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);
		
		resultsPanel = new JPanel(new BorderLayout());
		
		rset = ro.checkCurrentStock();
		int rowCount = 0;
		if(rset.last())
		{
			rowCount = rset.getRow();
			rset.beforeFirst();
			System.out.println(rowCount);
		}
		dtm = new DefaultTableModel(colNames, rowCount);
		table = new JTable(dtm);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane = new JScrollPane(table);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for(int i = 0;i<colNames.length;i++)
		{
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			table.getColumnModel().getColumn(0).setPreferredWidth(180);
			table.getColumnModel().getColumn(1).setPreferredWidth(250);
			table.getColumnModel().getColumn(2).setPreferredWidth(230);
			table.getColumnModel().getColumn(3).setPreferredWidth(100);
			table.getColumnModel().getColumn(4).setPreferredWidth(115);
			
		}
		for(int i = 0;i < rowCount;i++)
		{
			if(rset.next())
			{
				table.setValueAt(rset.getString(1), i, 0);
				table.setValueAt(rset.getString(2), i, 1);
				table.setValueAt(rset.getString(3), i, 2);
				table.setValueAt(rset.getInt   (4), i, 3);
				table.setValueAt(rset.getDouble(5), i, 4);
			}
		}
		resultsPanel.add(scrollPane, BorderLayout.CENTER);
		mainPanel.add(resultsPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}
}
