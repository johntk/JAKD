
package kioskScreens;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import db.DBconnection;

public class KioskStartScreen extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	private String srcPath;
	private static JPanel main;
	private JPanel content,header,footer,pswd;
	private ImageIcon cn,hp,gm,mu,dvd,sd,src,dl,logo,close,frameIcon;
	private JButton exit,con,headp,game,music,dvds,soundd,search,deals;
	private JLabel logoLabel,pinLbl,msc,dv,gms,snd,srch,dls,cnsl,hdph;
	private Font font;
	private JPasswordField jpf;
	private GridBagConstraints gc;
	private KioskResultsScreen krs;
	private KioskSearch ks;
	private DBconnection db;
	private KioskQueries kq;

	public KioskStartScreen(DBconnection db)
	{
		this.db = db;
		kq = new KioskQueries();
		kq.setDBconnection(db.openDB());
		
		frameIcon = new ImageIcon(this.getClass().getResource("/resources/titleIcon.png"));
		Image i = frameIcon.getImage();
		
		frame = new JFrame();
		frame.setTitle("Kiosk Mode");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setLayout(new BorderLayout());
		frame.setSize(1280,1024);
		//frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		//frame.setUndecorated(true);
		frame.setIconImage(i);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		srcPath = "/resources/kioskFiles/images/";
		gc = new GridBagConstraints();
		font = new Font("Calibri",Font.BOLD,30);
		
		cn = new ImageIcon(this.getClass().getResource(srcPath+"console.png"));
		hp = new ImageIcon(this.getClass().getResource(srcPath+"headphones.png"));
		gm = new ImageIcon(this.getClass().getResource(srcPath+"games.png"));
		mu = new ImageIcon(this.getClass().getResource(srcPath+"music.png"));
		dvd = new ImageIcon(this.getClass().getResource(srcPath+"dvd.png"));
		sd = new ImageIcon(this.getClass().getResource(srcPath+"soundDock.png"));
		src = new ImageIcon(this.getClass().getResource(srcPath+"search.png"));
		dl = new ImageIcon(this.getClass().getResource(srcPath+"deals.png"));
		logo = new ImageIcon(this.getClass().getResource(srcPath+"logo3.png"));
		close = new ImageIcon(this.getClass().getResource(srcPath+"close.png"));
		
		main = new JPanel(new BorderLayout());
		main.setBackground(Color.WHITE);
		frame.add(main);

		header = new JPanel(new BorderLayout());
		exit = new JButton("Close",close);
		exit.setBackground(new Color(238,238,238));
		exit.setPreferredSize(new Dimension(100,50));
		exit.addActionListener(this);
		exit.setBorder(null);
		header.add(exit,BorderLayout.EAST);
		main.add(header,BorderLayout.NORTH);

		content = new JPanel(new GridBagLayout());
		content.setBackground(new Color(0,0,0,0));

		search = new JButton(src);
		search.setBackground(Color.WHITE);
		search.setBorder(null);
		search.addActionListener(this);
		gc.gridx = 0;
		gc.gridy = 1;
		search.setBorder(BorderFactory.createMatteBorder(20,20,0,20, frame.getContentPane().getBackground()));
		content.add(search,gc);
		srch = new JLabel("Search");
		srch.setFont(font);
		gc.gridx = 0;
		gc.gridy = 2;
		content.add(srch,gc);

		music = new JButton(mu);
		music.setBackground(Color.WHITE);
		music.setBorder(null);
		music.addActionListener(this);
		gc.gridx = 1;
		gc.gridy = 1;
		music.setBorder(BorderFactory.createMatteBorder(20,20,0,20, frame.getContentPane().getBackground()));
		content.add(music,gc);
		msc = new JLabel("Music");
		msc.setFont(font);
		gc.gridx = 1;
		gc.gridy = 2;
		content.add(msc,gc);

		dvds = new JButton(dvd);
		dvds.setBackground(Color.WHITE);
		dvds.setBorder(null);
		dvds.addActionListener(this);
		gc.gridx = 2;
		gc.gridy = 1;
		dvds.setBorder(BorderFactory.createMatteBorder(20,20,0,20, frame.getContentPane().getBackground()));
		content.add(dvds,gc);
		dv = new JLabel("DVD");
		dv.setFont(font);
		gc.gridx = 2;
		gc.gridy = 2;
		content.add(dv,gc);

		con = new JButton(cn);
		con.setBackground(Color.WHITE);
		con.setBorder(null);
		con.addActionListener(this);
		gc.gridx = 3;
		gc.gridy = 1;
		con.setBorder(BorderFactory.createMatteBorder(20,20,0,20, frame.getContentPane().getBackground()));
		content.add(con,gc);
		cnsl = new JLabel("Consoles");
		cnsl.setFont(font);
		gc.gridx = 3;
		gc.gridy = 2;
		content.add(cnsl,gc);

		game = new JButton(gm);
		game.setBackground(Color.WHITE);
		game.setBorder(null);
		game.addActionListener(this);
		gc.gridx = 0;
		gc.gridy = 3;
		game.setBorder(BorderFactory.createMatteBorder(20,20,0,20, frame.getContentPane().getBackground()));
		content.add(game,gc);
		gms = new JLabel("Games");
		gms.setFont(font);
		gc.gridx = 0;
		gc.gridy = 4;
		content.add(gms,gc);

		headp = new JButton(hp);
		headp.setBackground(Color.WHITE);
		headp.setBorder(null);
		headp.addActionListener(this);
		gc.gridx = 1;
		gc.gridy = 3;
		headp.setBorder(BorderFactory.createMatteBorder(20,20,0,20, frame.getContentPane().getBackground()));
		content.add(headp,gc);
		hdph = new JLabel("Headphones");
		hdph.setFont(font);
		gc.gridx = 1;
		gc.gridy = 4;
		content.add(hdph,gc);

		soundd = new JButton(sd);
		soundd.setBackground(Color.WHITE);
		soundd.setBorder(null);
		soundd.addActionListener(this);
		gc.gridx = 2;
		gc.gridy = 3;
		soundd.setBorder(BorderFactory.createMatteBorder(20,20,0,20, frame.getContentPane().getBackground()));
		content.add(soundd,gc);
		snd = new JLabel("SoundDocks");
		snd.setFont(font);
		gc.gridx = 2;
		gc.gridy = 4;
		content.add(snd,gc);

		deals = new JButton(dl);
		deals.setBackground(Color.WHITE);
		deals.setBorder(null);
		deals.addActionListener(this);
		gc.gridx = 3;
		gc.gridy = 3;
		deals.setBorder(BorderFactory.createMatteBorder(20,20,0,20, frame.getContentPane().getBackground()));
		content.add(deals,gc);
		dls = new JLabel("Deals");
		dls.setFont(font);
		gc.gridx = 3;
		gc.gridy = 4;
		content.add(dls,gc);

		main.add(content,BorderLayout.CENTER);

		footer = new JPanel();
		logoLabel = new JLabel(logo);
		footer.add(logoLabel);
		main.add(footer,BorderLayout.SOUTH);

		frame.setVisible(true);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.requestFocus();

		pswd = new JPanel(new GridLayout(1,2));
		pinLbl = new JLabel("PIN:");
		jpf = new JPasswordField(4);
		pswd.add(pinLbl);
		pswd.add(jpf);
	}
	
	public static void addPanel(JPanel panel)
	{
		frame.add(panel);
		frame.validate();
		frame.repaint();
	}
	
	public static void switchToMainPanel(JPanel panel)
	{
		if(main.isVisible()==true)
		{
			main.setVisible(false);
			frame.add(panel);
		}
		else
		{
			panel.setVisible(false);
			main.setVisible(true);
		}
		frame.validate();
		frame.repaint();
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==search){
			ks = new KioskSearch(kq);
			switchToMainPanel(ks.getPanel());
		}
		if(e.getSource()==game){
			krs = new KioskResultsScreen(kq);
			krs.setHeading("Games");
			
			ArrayList<String> consoleList = kq.queryPlatform();
			krs.displayGameOptions(consoleList);
			kq.queryAllCategories("Game",krs);
			krs.displayResult();
			switchToMainPanel(krs.getPanel());
			
			krs.passKioskResultsScreenObject(krs);
		}
		if(e.getSource()==music){
			krs = new KioskResultsScreen(kq);
			krs.setHeading("MUSIC");
			kq.queryAllCategories("CD",krs);
			krs.displayResult();
			switchToMainPanel(krs.getPanel());
		}
		if(e.getSource()==dvds){
			krs = new KioskResultsScreen(kq);
			kq.queryAllCategories("DVD",krs);
			krs.setHeading("DVD");
			krs.displayResult();
			switchToMainPanel(krs.getPanel());
		}
		if(e.getSource()==con){
			krs = new KioskResultsScreen(kq);
			krs.setHeading("CONSOLES");
			kq.queryAllCategories("Console",krs);
			krs.displayResult();
			switchToMainPanel(krs.getPanel());
		}
		if(e.getSource()==headp){
			krs = new KioskResultsScreen(kq);
			krs.setHeading("HEADPHONES");
			kq.queryAllCategories("Headphones",krs);
			krs.displayResult();
			switchToMainPanel(krs.getPanel());
		}
		if(e.getSource()==soundd){
			krs = new KioskResultsScreen(kq);
			krs.setHeading("SOUNDDOCKS");
			kq.queryAllCategories("Sounddock",krs);
			krs.displayResult();
			switchToMainPanel(krs.getPanel());
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
				se.printStackTrace();
			}
			frame.dispose();
		}
	}
}
