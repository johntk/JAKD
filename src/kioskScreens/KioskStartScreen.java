package kioskScreens;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.swing.*;

public class KioskStartScreen extends JFrame implements ActionListener
{
	private JFrame frame;
	private String srcPath;
	private JPanel content,header,footer;
	private ImageIcon cn,hp,gm,mu,dvd,sd,src,dl,logo;
	private JButton exit,con,headp,game,music,dvds,soundd,search,deals;
	private JLabel logoLabel;
	private JLabel empty1,empty2,empty3,empty4;

	public KioskStartScreen()
	{
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setLayout(new BorderLayout());
		frame.setSize(1000,600);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		srcPath = "src/kioskScreens/Resources/images/";

		cn = new ImageIcon(srcPath+"console.png");
		hp = new ImageIcon(srcPath+"headphones.png");
		gm = new ImageIcon(srcPath+"games.png");
		mu = new ImageIcon(srcPath+"music.png");
		dvd = new ImageIcon(srcPath+"dvd.png");
		sd = new ImageIcon(srcPath+"soundDock.png");
		src = new ImageIcon(srcPath+"search.png");
		dl = new ImageIcon(srcPath+"deals.png");
		logo = new ImageIcon(srcPath+"logo3.png");

		empty1 = new JLabel(" ");
		empty2 = new JLabel(" ");
		empty3 = new JLabel(" ");
		empty4 = new JLabel(" ");

		con = new JButton(cn);
		con.setBackground(Color.WHITE);
		con.setBorder(null);
		con.addActionListener(this);
		headp = new JButton(hp);
		headp.setBackground(Color.WHITE);
		headp.setBorder(null);
		headp.addActionListener(this);
		game = new JButton(gm);
		game.setBackground(Color.WHITE);
		game.setBorder(null);
		game.addActionListener(this);
		music = new JButton(mu);
		music.setBackground(Color.WHITE);
		music.setBorder(null);
		music.addActionListener(this);
		dvds = new JButton(dvd);
		dvds.setBackground(Color.WHITE);
		dvds.setBorder(null);
		dvds.addActionListener(this);
		soundd = new JButton(sd);
		soundd.setBackground(Color.WHITE);
		soundd.setBorder(null);
		soundd.addActionListener(this);
		search = new JButton(src);
		search.setBackground(Color.WHITE);
		search.setBorder(null);
		search.addActionListener(this);
		deals = new JButton(dl);
		deals.setBackground(Color.WHITE);
		deals.setBorder(null);
		deals.addActionListener(this);

		header = new JPanel(new BorderLayout());
		exit = new JButton("Close");
		exit.setBackground(new Color(238,238,238));
		exit.setPreferredSize(new Dimension(100,50));
		exit.addActionListener(this);
		exit.setBorder(null);
		header.add(exit,BorderLayout.EAST);
		frame.add(header,BorderLayout.NORTH);

		content = new JPanel(new GridLayout(2,6,0,5));
		content.setBackground(new Color(0,0,0,0));
		content.add(empty1);
		content.add(search);
		content.add(music);
		content.add(dvds);
		content.add(con);
		content.add(empty2);
		content.add(empty3);
		content.add(game);
		content.add(headp);
		content.add(soundd);
		content.add(deals);
		content.add(empty4);
		frame.add(content,BorderLayout.CENTER);

		footer = new JPanel();
		logoLabel = new JLabel(logo);
		footer.add(logoLabel);
		frame.add(footer,BorderLayout.SOUTH);

		frame.setVisible(true);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==exit)
		{
			System.exit(0);
		}
		if(e.getSource()==search)
		{
			KioskSearch ks = new KioskSearch();
		}
		if(e.getSource()==game)
		{
			KioskGameOptions kso = new KioskGameOptions();
		}
	}
	
	public static void main(String args[])
	{
		KioskStartScreen kss = new KioskStartScreen();
	}
}
