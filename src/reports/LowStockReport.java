package reports;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import reports.ReportOperations;

public class LowStockReport extends JPanel
{
	private ReportOperations ro;
	private JFrame frame;
	private JPanel topPanel, mainPanel, resultsPanel;
	private JLabel title;
	private DefaultTableModel dtm;
	public ResultSet rset;
	private JTable table;
	private JScrollPane scrollPane;
	private Font f;

	public LowStockReport(ReportOperations r) throws SQLException
	{
		ro = r;

//		frame = new JFrame();
//		frame.setTitle("Report");
//		frame.setSize(600, 700);
//		frame.setLocationRelativeTo(null);
//		frame.setResizable(false);
//		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());

		f = new Font("Helvetica", Font.ITALIC, 40);

		String[] colNames = {"Product ID","Product Type", "Description","Current Stock"};

		topPanel = new JPanel(new FlowLayout());
		topPanel.setSize(600, 140);
		this.add(topPanel, BorderLayout.NORTH);

		title = new JLabel("Low Stock Levels");
		title.setFont(f);
		topPanel.add(title);

		mainPanel = new JPanel(new BorderLayout());
		this.add(mainPanel, BorderLayout.CENTER);

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
		}
		for(int i = 0;i < rowCount;i++)
		{
			if(rset.next())
			{
				table.setValueAt(rset.getString(1), i, 0);
				table.setValueAt(rset.getString(2), i, 1);
				table.setValueAt(rset.getString(3), i, 2);
				table.setValueAt(rset.getInt   (4), i, 3);
			}
		}
		resultsPanel.add(scrollPane, BorderLayout.CENTER);
		mainPanel.add(resultsPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}
}
