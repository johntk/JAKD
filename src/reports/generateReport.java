package reports;

import java.awt.*;
import java.awt.event.*;

import java.sql.Connection;

import javax.swing.*;
public class generateReport extends JPanel implements ActionListener, ItemListener 
{
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private JButton genReportBtn;
	private GridLayout layout = new GridLayout();
	private GridBagConstraints gc = new GridBagConstraints();
	private ReportOperations ro;
	
	private JPanel cardPanel;

	public generateReport(Connection c)
	{
		conn = c;
		ro = new ReportOperations();
		ro.setDBconnection(conn);
		
		this.setLayout(layout);

		JPanel sideButtons = new JPanel();
		sideButtons.setLayout(new GridBagLayout());
		this.add(sideButtons, BorderLayout.WEST);


		// side button array
		JButton[] sideButtonsArray = {
				genReportBtn = new JButton("Select Report"),
						 };

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
		this.add(cardPanel, BorderLayout.EAST);
		this.setVisible(true);
	}

	public void itemStateChanged(ItemEvent arg0)
	{
		
	}
	public void actionPerformed(ActionEvent ae) 
	{
		if(ae.getSource() == genReportBtn)
		{
			new DialogBox(ro, this);
		}
	}
}