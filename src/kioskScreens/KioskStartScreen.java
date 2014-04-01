package kioskScreens;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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
	private KioskResultsScreen krs;
	private KioskSearch ks;
	private DBconnection db;

	public KioskStartScreen(DBconnection db)
	{
		this.db = db;
		db.openDB();
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setLayout(new BorderLayout());
		frame.setSize(1200,800);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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

		db.setDB(db);

		pswd = new JPanel(new GridLayout(1,2));
		pinLbl = new JLabel("PIN:");
		jpf = new JPasswordField(4);
		pswd.add(pinLbl);
		pswd.add(jpf);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==search){
			ks = new KioskSearch(db);
		}
		if(e.getSource()==game){
			krs = new KioskResultsScreen(db);
			krs.setHeading("Games");
			
			ArrayList<String> consoleList = db.queryPlatform();
			krs.displayGameOptions(consoleList);
			db.queryGames("",krs);
			
			krs.passKioskResultsScreenObject(krs);
		}
		if(e.getSource()==music){
			krs = new KioskResultsScreen(db);
			krs.setHeading("MUSIC");
			db.queryMusic(krs);
			krs.displayResult();
		}
		if(e.getSource()==dvds){
			krs = new KioskResultsScreen(db);
			db.queryDVD(krs);
			krs.setHeading("DVD");
			krs.displayResult();
		}
		if(e.getSource()==con){
			krs = new KioskResultsScreen(db);
			krs.setHeading("CONSOLES");
			db.queryConsoles(krs);
			krs.displayResult();
		}
		if(e.getSource()==headp){
			krs = new KioskResultsScreen(db);
			krs.setHeading("HEADPHONES");
			db.queryHeadphones(krs);
			krs.displayResult();
		}
		if(e.getSource()==soundd){
			krs = new KioskResultsScreen(db);
			krs.setHeading("SOUNDDOCKS");
			db.querySoundDocks(krs);
			krs.displayResult();
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
