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
	private ImageIcon hm,logo;
	private GridBagConstraints gc;
	private String srcPath;
	
	public ProductDisplay()
	{
		this.prodID = prodID;
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setLayout(new BorderLayout());
		frame.setSize(1200,800);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		srcPath = "src/resources/kioskFiles/productImages/";
		hm = new ImageIcon("src/resources/kioskFiles/images/home.png");
		logo = new ImageIcon("src/resources/kioskFiles/images/logo3.png");
		gc = new GridBagConstraints();
		
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
		
		//Add panel to display product details
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
	
	public void displayConsole()
	{
		
	}
	public void displayHeadphones()
	{
		
	}
	public void displaySoundDock()
	{
		
	}
	public void displayCD(String title, String genre, String recordCompany, String length, int rating, double salePrice, int currentStock)
	{
		// Add CD artwork and information
		JPanel result = new JPanel(new GridLayout(8,1));
		
		ImageIcon img = new ImageIcon(srcPath+title+".jpg");
		JLabel i = new JLabel(img);
		gc.gridx =0;
		gc.gridy =0;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.NORTHWEST;
		i.setBorder(BorderFactory.createEmptyBorder(40,40,0,0));
		productInfo.add(i,gc);
		
		JLabel t = new JLabel(title);
		t.setFont(new Font("Calibri",Font.BOLD,40));
		t.setForeground(new Color(20,120,230));
		result.add(t);
		
		JLabel empty = new JLabel(" ");
		result.add(empty);
		
		JLabel c = new JLabel("• Record Company: "+recordCompany);
		c.setFont(new Font("Calibri",Font.PLAIN,20));
		//c.setForeground(new Color(110,110,110));
		result.add(c);
		
		JLabel p = new JLabel("• Length: "+length+" minutes");
		p.setFont(new Font("Calibri",Font.PLAIN,20));
		result.add(p);
		
		JLabel g = new JLabel("• Genre: "+genre);
		g.setFont(new Font("Calibri",Font.PLAIN,20));
		result.add(g);
		
		JLabel r = new JLabel("• Age Rating: "+rating);
		r.setFont(new Font("Calibri",Font.PLAIN,20));
		result.add(r);
		
		JLabel cs = new JLabel("• In-Stock: "+currentStock);
		cs.setFont(new Font("Calibri",Font.PLAIN,20));
		result.add(cs);
		
		JLabel sp = new JLabel("Price: €"+salePrice);
		sp.setFont(new Font("Calibri",Font.BOLD,25));
		sp.setForeground(new Color(20,120,230));
		result.add(sp);
		
		gc.gridx =1;
		gc.gridy =0;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.NORTHWEST;
		result.setBorder(BorderFactory.createEmptyBorder(40,20,0,0));
		result.setPreferredSize(new Dimension(1200,350));
		productInfo.add(result,gc);
		
		// Add list of songs from CD
		JPanel songs = new JPanel();
		
	}
	
	public void displayGame(String title,String genre,String company,String platform,int rating,double salePrice,int currentStock)
	{
		JPanel result = new JPanel(new GridLayout(7,1));
		
		ImageIcon img = new ImageIcon(srcPath+title+".jpg");
		JLabel i = new JLabel(img);
		gc.gridx =0;
		gc.gridy =0;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.NORTHWEST;
		i.setBorder(BorderFactory.createMatteBorder(40,40,0,0,productInfo.getBackground()));
		productInfo.add(i,gc);
		
		JLabel t = new JLabel(title);
		t.setFont(new Font("Calibri",Font.BOLD,40));
		t.setForeground(new Color(20,120,230));
		result.add(t);
		
		JLabel c = new JLabel("• Company: "+company);
		c.setFont(new Font("Calibri",Font.PLAIN,20));
		//c.setForeground(new Color(110,110,110));
		result.add(c);
		
		JLabel p = new JLabel("• Platform: "+platform);
		p.setFont(new Font("Calibri",Font.PLAIN,20));
		result.add(p);
		
		JLabel g = new JLabel("• Genre: "+genre);
		g.setFont(new Font("Calibri",Font.PLAIN,20));
		result.add(g);
		
		JLabel r = new JLabel("• Age Rating: "+rating);
		r.setFont(new Font("Calibri",Font.PLAIN,20));
		result.add(r);
		
		JLabel cs = new JLabel("• In-Stock: "+currentStock);
		cs.setFont(new Font("Calibri",Font.PLAIN,20));
		result.add(cs);

		
		JLabel sp = new JLabel("Price: €"+salePrice);
		sp.setFont(new Font("Calibri",Font.BOLD,25));
		result.add(sp);
		
		gc.gridx =1;
		gc.gridy =0;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.NORTHWEST;
		result.setBorder(BorderFactory.createMatteBorder(40,20,0,0,productInfo.getBackground()));
		result.setPreferredSize(new Dimension(1200,350));
		productInfo.add(result,gc);
	}
	public void displayDVD()
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
