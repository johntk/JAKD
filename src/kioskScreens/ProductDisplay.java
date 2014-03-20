package kioskScreens;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import db.DBconnection;

public class ProductDisplay extends JFrame implements ActionListener
{
	private JFrame frame;
	private String prodID;
	private JPanel main,center,top,centerTop,footer,productInfo;
	private JScrollPane scrollPane;
	private JButton home;
	private JLabel resultsHeading,logoLabel;
	private ImageIcon hm,logo,img;
	private GridBagConstraints gc;
	private DBconnection db;
	
	public ProductDisplay(String prodID, DBconnection db)
	{
		this.prodID = prodID;
		this.db = db;
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setLayout(new BorderLayout());
		frame.setSize(1200,800);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		hm = new ImageIcon("src/resources/kioskFiles/images/home.png");
		logo = new ImageIcon("src/resources/kioskFiles/images/logo3.png");
		
		main = new JPanel(new BorderLayout());
		main.setBackground(Color.WHITE);
		main.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		frame.add(main);
		
		top = new JPanel(new BorderLayout());
		top.setBackground(new Color(0,0,0,0));
		home = new JButton(hm);
		home.setBackground(Color.WHITE);
		home.setFont(new Font("Calibri",Font.BOLD,25));
		home.setVerticalTextPosition(SwingConstants.BOTTOM );
		home.setHorizontalTextPosition(SwingConstants.CENTER);
		home.setBorder(null);
		home.addActionListener(this);
		top.add(home, BorderLayout.WEST);
		main.add(top,BorderLayout.NORTH);
		
		center = new JPanel(new BorderLayout());
		center.setBackground(new Color(0,0,0,0));
		center.setBorder(BorderFactory.createEmptyBorder(0,120,0,120));
		main.add(center,BorderLayout.CENTER);

		centerTop = new JPanel(new GridBagLayout());
		centerTop.setBackground(new Color(0,0,0,0));
		resultsHeading = new JLabel("Displaying Results for... ");
		resultsHeading.setFont(new Font("Calibri",Font.PLAIN,40));
		centerTop.add(resultsHeading);
		center.add(centerTop,BorderLayout.NORTH);
		
		//Add panel to display all product details
		//db.queryProductInfo(prodID);
		productInfo = new JPanel();
		productInfo.setLayout(new GridBagLayout());
		scrollPane = new JScrollPane(productInfo);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		center.add(scrollPane,BorderLayout.CENTER);
		
		footer = new JPanel();
		logoLabel = new JLabel(logo);
		footer.add(logoLabel);
		frame.add(footer,BorderLayout.SOUTH);
		
		frame.setVisible(true);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.requestFocus();
	}
	
	public void addElectronicProduct()
	{
		
	}
	public void addDigitalProduct()
	{
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==home)
		{
			frame.dispose();
		}
	}
}
