package gui;

import java.awt.*;
import java.sql.Connection;

import javax.swing.*;

import operations.ReportOperations;
public class generateReport extends JPanel
{
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private BorderLayout layout = new BorderLayout();
	private ReportOperations ro;


	public generateReport(Connection c)
	{
		conn = c;
		ro = new ReportOperations();
		ro.setDBconnection(conn);

		this.setLayout(layout);

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		this.add(panel, BorderLayout.CENTER);

		this.setVisible(true);
		
		new DialogBox(ro, this);
	}
}