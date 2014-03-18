package kioskScreens;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import db.DBconnection;

public class KioskSearch extends JFrame implements ActionListener
{
	private JFrame frame;
	private String srcPath;
	private JPanel main,center,top;
	private JLabel logoLabel,srchText,empty1;
	private JTextField search;
	private JButton home,searchBtn;
	private Font f1;
	private ImageIcon hm,logo;
	private GridBagConstraints gc;
	private DBconnection db;

	public KioskSearch()
	{
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(1000,600);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setUndecorated(true);
		f1=new Font("Calibri",Font.PLAIN,20);

		srcPath = "src/resources/kioskFiles/images/";
		gc = new GridBagConstraints();
		hm = new ImageIcon(srcPath+"home.png");
		logo = new ImageIcon(srcPath+"logo.png");

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

		center = new JPanel();
		center.setLayout(new GridBagLayout());
		center.setBackground(new Color(0,0,0,0));

		//add logo and label to page center
		logoLabel = new JLabel(logo);
		logoLabel.setBorder(BorderFactory.createEmptyBorder(0,20,20,20));
		gc.gridx = 0;
		gc.gridy = 0;
		center.add(logoLabel,gc);
		srchText = new JLabel("Search Engine");
		srchText.setFont(new Font("Dotum",Font.ITALIC,25));
		srchText.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
		gc.gridx = 0;
		gc.gridy = 1;
		center.add(srchText,gc);

		//add search bar under logo
		search = new JTextField(50);
		search.setText("Search for a Product...");
		search.setForeground(Color.LIGHT_GRAY);
		search.setPreferredSize(new Dimension(1,35));
		search.setFont(f1);
		search.setBorder(BorderFactory.createLineBorder(new Color(102,178,255),1));
		gc.gridx = 0;
		gc.gridy = 2;
		center.add(search,gc);

		//add search button below search bar
		empty1 = new JLabel(" ");
		gc.gridx = 0;
		gc.gridy = 3;
		center.add(empty1,gc);
		searchBtn = new JButton("Search");
		searchBtn.addActionListener(this);
		searchBtn.setPreferredSize(new Dimension(160, 40));
		searchBtn.setFont(new Font("Calibri",Font.BOLD,25));
		searchBtn.setBackground(new Color(102,178,255));
		searchBtn.setForeground(Color.white);
		gc.gridx = 0;
		gc.gridy = 4;
		center.add(searchBtn,gc);
		main.add(center,BorderLayout.CENTER);

		search.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if(search.getText().trim().equals("Search for a Product..."))
				{
					search.setForeground(Color.BLACK);
					search.setText("");
				}
			}
			public void focusLost(FocusEvent e)
			{
				if(search.getText().equals(""))
				{
					search.setText("Search for a Product...");
					search.setForeground(Color.LIGHT_GRAY);
				}
			}
		});

		search.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e)
		{
			int key = e.getKeyCode();

			if (key == KeyEvent.VK_ENTER)
			{
				if(search.getText().equals("Search for a Product...")){

				}else
				{
					String searchTerm = search.getText();
					db.queryAllProducts(searchTerm);
				}
		    }
		}
			public void keyReleased(KeyEvent e) {
			}
			public void keyTyped(KeyEvent e) {
			}
		});
		
		frame.setVisible(true);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.requestFocus();

		db = new DBconnection();
		db.openDB();
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==searchBtn)
		{
			if(search.getText().equals("Search for a Product...")){

			}else
			{
				String searchTerm = search.getText();
				db.queryAllProducts(searchTerm);
			}
		}
		if(e.getSource()==home)
		{
			frame.dispose();
			try
			{
				db.closeDB();
			} catch (Exception se){
				System.out.println("Could not close connection");
				se.printStackTrace();
			}
		}
	}
}
