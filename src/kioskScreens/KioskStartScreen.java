package kioskScreens;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.sql.SQLException;

import javax.swing.*;

import db.DBconnection;

public class KioskStartScreen extends JFrame implements ActionListener
{
	private JFrame frame;
	private String srcPath;
	private JPanel content,header,footer,pswd;
	private ImageIcon cn,hp,gm,mu,dvd,sd,src,dl,logo,close;
	private JButton exit,con,headp,game,music,dvds,soundd,search,deals;
	private JLabel logoLabel,pinLbl;
	private JPasswordField jpf;
	private GridBagConstraints gc;
	private DBconnection db;

	public KioskStartScreen()
	{
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setLayout(new BorderLayout());
		frame.setSize(1200,800);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

		srcPath = "src/resources/kioskFiles/images/";
		gc = new GridBagConstraints();

		cn = new ImageIcon(srcPath+"console.png");
		hp = new ImageIcon(srcPath+"headphones.png");
		gm = new ImageIcon(srcPath+"games.png");
		mu = new ImageIcon(srcPath+"music.png");
		dvd = new ImageIcon(srcPath+"dvd.png");
		sd = new ImageIcon(srcPath+"soundDock.png");
		src = new ImageIcon(srcPath+"search.png");
		dl = new ImageIcon(srcPath+"deals.png");
		logo = new ImageIcon(srcPath+"logo3.png");
		close = new ImageIcon(srcPath+"close.png");

		header = new JPanel(new BorderLayout());
		exit = new JButton("Close",close);
		exit.setBackground(new Color(238,238,238));
		exit.setPreferredSize(new Dimension(100,50));
		exit.addActionListener(this);
		exit.setBorder(null);
		header.add(exit,BorderLayout.EAST);
		frame.add(header,BorderLayout.NORTH);

		content = new JPanel(new GridBagLayout());
		content.setBackground(new Color(0,0,0,0));

		search = new JButton(src);
		search.setBackground(Color.WHITE);
		search.setBorder(null);
		search.addActionListener(this);
		gc.gridx = 0;
		gc.gridy = 1;
		search.setBorder(BorderFactory.createMatteBorder(20,20,20,20, frame.getContentPane().getBackground()));
		content.add(search,gc);

		music = new JButton(mu);
		music.setBackground(Color.WHITE);
		music.setBorder(null);
		music.addActionListener(this);
		gc.gridx = 1;
		gc.gridy = 1;
		music.setBorder(BorderFactory.createMatteBorder(20,20,20,20, frame.getContentPane().getBackground()));
		content.add(music,gc);

		dvds = new JButton(dvd);
		dvds.setBackground(Color.WHITE);
		dvds.setBorder(null);
		dvds.addActionListener(this);
		gc.gridx = 2;
		gc.gridy = 1;
		dvds.setBorder(BorderFactory.createMatteBorder(20,20,20,20, frame.getContentPane().getBackground()));
		content.add(dvds,gc);

		con = new JButton(cn);
		con.setBackground(Color.WHITE);
		con.setBorder(null);
		con.addActionListener(this);
		gc.gridx = 3;
		gc.gridy = 1;
		con.setBorder(BorderFactory.createMatteBorder(20,20,20,20, frame.getContentPane().getBackground()));
		content.add(con,gc);

		game = new JButton(gm);
		game.setBackground(Color.WHITE);
		game.setBorder(null);
		game.addActionListener(this);
		gc.gridx = 0;
		gc.gridy = 2;
		game.setBorder(BorderFactory.createMatteBorder(20,20,20,20, frame.getContentPane().getBackground()));
		content.add(game,gc);

		headp = new JButton(hp);
		headp.setBackground(Color.WHITE);
		headp.setBorder(null);
		headp.addActionListener(this);
		gc.gridx = 1;
		gc.gridy = 2;
		headp.setBorder(BorderFactory.createMatteBorder(20,20,20,20, frame.getContentPane().getBackground()));
		content.add(headp,gc);

		soundd = new JButton(sd);
		soundd.setBackground(Color.WHITE);
		soundd.setBorder(null);
		soundd.addActionListener(this);
		gc.gridx = 2;
		gc.gridy = 2;
		soundd.setBorder(BorderFactory.createMatteBorder(20,20,20,20, frame.getContentPane().getBackground()));
		content.add(soundd,gc);

		deals = new JButton(dl);
		deals.setBackground(Color.WHITE);
		deals.setBorder(null);
		deals.addActionListener(this);
		gc.gridx = 3;
		gc.gridy = 2;
		deals.setBorder(BorderFactory.createMatteBorder(20,20,20,20, frame.getContentPane().getBackground()));
		content.add(deals,gc);

		frame.add(content,BorderLayout.CENTER);

		footer = new JPanel();
		logoLabel = new JLabel(logo);
		footer.add(logoLabel);
		frame.add(footer,BorderLayout.SOUTH);

		frame.setVisible(true);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.requestFocus();

		db = new DBconnection();
		db.openDB();

		pswd = new JPanel(new GridLayout(1,2));
		pinLbl = new JLabel("PIN:");
		jpf = new JPasswordField(4);
		pswd.add(pinLbl);
		pswd.add(jpf);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==search){
			KioskSearch ks = new KioskSearch(db);
		}
		if(e.getSource()==game){
			KioskGameOptions kso = new KioskGameOptions();
		}
		if(e.getSource()==music){
			db.queryMusic();
		}
		if(e.getSource()==dvds){
			db.queryDVD();
		}
		if(e.getSource()==con){
			db.queryConsoles();
		}
		if(e.getSource()==headp){
			db.queryHeadphones();
		}
		if(e.getSource()==soundd){
			db.querySoundDocks();
		}
		if(e.getSource()==deals){
		}
		if(e.getSource()==exit)
		{
			/*int input = JOptionPane.showConfirmDialog(frame, pswd, "Enter your PIN:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if(input == 0000)
			{
				System.exit(0);
			}*/
			try
			{
				db.closeDB();
			} catch (Exception se){
				System.out.println("Could not close connection");
				//se.printStackTrace();
			}
			System.exit(0);
		}
	}
	
	//Inner class to display game platform options
	class KioskGameOptions extends JFrame implements ActionListener
	{
		private JFrame frame;
		private String srcPath;
		private JPanel main,center,top;
		private JLabel heading;
		private JButton home,xbox,ps4,wii;
		private ImageIcon hm,xb,ps,wi;
		private GridBagConstraints gc;
		
		public KioskGameOptions()
		{
			frame = new JFrame();
			frame.setLayout(new BorderLayout());
			frame.setSize(1000,600);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			frame.setUndecorated(true);
			
			srcPath = "src/resources/kioskFiles/images/";
			gc = new GridBagConstraints();
			hm = new ImageIcon(srcPath+"home.png");
			xb = new ImageIcon(srcPath+"xbox.png");
			ps = new ImageIcon(srcPath+"ps4.png");
			wi = new ImageIcon(srcPath+"wii.png");
			
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
			
			//add center JPanel to main frame
			center = new JPanel(new GridBagLayout());
			center.setBackground(new Color(0,0,0,0));
			main.add(center,BorderLayout.CENTER);
			
			//add buttons and heading to center panel
			heading = new JLabel("Choose a Platform...");
			heading.setBorder(BorderFactory.createEmptyBorder(0,0,50,0));
			heading.setFont(new Font("Calibri",Font.PLAIN,50));
			gc.gridx = 1;
			gc.gridy = 0;
			center.add(heading,gc);
			xbox = new JButton(xb);
			xbox.setBackground(Color.WHITE);
			xbox.setBorder(null);
			xbox.addActionListener(this);
			gc.gridx = 0;
			gc.gridy = 1;
			center.add(xbox,gc);
			ps4 = new JButton(ps);
			ps4.setBackground(Color.WHITE);
			ps4.setBorder(null);
			ps4.addActionListener(this);
			gc.gridx = 1;
			gc.gridy = 1;
			center.add(ps4,gc);
			wii = new JButton(wi);
			wii.setBackground(Color.WHITE);
			wii.setBorder(null);
			wii.addActionListener(this);
			gc.gridx = 2;
			gc.gridy = 1;
			center.add(wii,gc);
			
			frame.setVisible(true);
			frame.setExtendedState(Frame.MAXIMIZED_BOTH);
			frame.requestFocus();
		}
		
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==xbox)
			{
				db.queryGames("Xbox360");
			}
			if(e.getSource()==ps4)
			{
				db.queryGames("PS4");
			}
			if(e.getSource()==wii)
			{
				db.queryGames("Wii");
			}
			if(e.getSource()==home)
			{
				frame.dispose();
			}
		}
	}
	
	public static void main(String args[])
	{
		KioskStartScreen kss = new KioskStartScreen();
	}
}
