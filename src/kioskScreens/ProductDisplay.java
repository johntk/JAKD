package kioskScreens;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.CompoundBorder;

import db.DBconnection;

public class ProductDisplay extends JFrame implements ActionListener
{
	private JFrame frame;
	private JPanel main,center,top,centerTop,footer,productInfo;
	private JScrollPane scrollPane;
	private JButton home;
	private JLabel resultsHeading,logoLabel;
	private ImageIcon hm,logo;
	private GridBagConstraints gc;
	private String srcPath;

	public ProductDisplay()
	{
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
	
	public void setHeading(String s)
	{
		resultsHeading.setText(resultsHeading.getText()+s);
	}

	public void displayConsole(String manufacturer,String model,String colour,int storage,String wifi,int numControllers,double salePrice,int currentStock)
	{
		// Add product image and information
		JPanel result = new JPanel(new GridLayout(9,1));

		ImageIcon img = new ImageIcon(srcPath+manufacturer+" "+model+".jpg");
		JLabel i = new JLabel(img);
		gc.gridx =0;
		gc.gridy =0;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.NORTHWEST;
		i.setBorder(BorderFactory.createEmptyBorder(40,40,0,0));
		productInfo.add(i,gc);

		JLabel man = new JLabel(manufacturer);
		man.setFont(new Font("Calibri",Font.BOLD,40));
		man.setForeground(new Color(20,120,230));
		result.add(man);

		JLabel mod = new JLabel(model);
		mod.setFont(new Font("Calibri",Font.BOLD,40));
		mod.setForeground(new Color(20,120,230));
		result.add(mod);

		JLabel empty = new JLabel(" ");
		result.add(empty);

		JLabel c = new JLabel("• Colour: "+colour);
		c.setFont(new Font("Calibri",Font.PLAIN,20));
		result.add(c);

		JLabel st = new JLabel("• Storage Size: "+storage+" GB");
		st.setFont(new Font("Calibri",Font.PLAIN,20));
		result.add(st);

		if(wifi.equals("Y"))
		{
			JLabel wf1 = new JLabel("• Wi-Fi: "+"Yes");
			wf1.setFont(new Font("Calibri",Font.PLAIN,20));
			result.add(wf1);
		}
		else
		{
			JLabel wf = new JLabel("• Wi-Fi: "+"No");
			wf.setFont(new Font("Calibri",Font.PLAIN,20));
			result.add(wf);
		}

		JLabel nc = new JLabel("• Number of Controllers: "+numControllers);
		nc.setFont(new Font("Calibri",Font.PLAIN,20));
		result.add(nc);

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
	}
	public void displayHeadphones(String manufacturer,String model,String colour,String overEar,String mic,String iPhoneCompatible,double salePrice,int currentStock)
	{
		// Add product image and information
		JPanel result = new JPanel(new GridLayout(9,1));

		ImageIcon img = new ImageIcon(srcPath+manufacturer+" "+model+".jpg");
		JLabel i = new JLabel(img);
		gc.gridx =0;
		gc.gridy =0;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.NORTHWEST;
		i.setBorder(BorderFactory.createEmptyBorder(40,40,0,0));
		productInfo.add(i,gc);

		JLabel man = new JLabel(manufacturer);
		man.setFont(new Font("Calibri",Font.BOLD,40));
		man.setForeground(new Color(20,120,230));
		result.add(man);

		JLabel mod = new JLabel(model);
		mod.setFont(new Font("Calibri",Font.BOLD,40));
		mod.setForeground(new Color(20,120,230));
		result.add(mod);

		JLabel empty = new JLabel(" ");
		result.add(empty);

		JLabel c = new JLabel("• Colour: "+colour);
		c.setFont(new Font("Calibri",Font.PLAIN,20));
		result.add(c);

		if(overEar.equals("Y"))
		{
			JLabel w = new JLabel("• Over Ear: "+"Yes");
			w.setFont(new Font("Calibri",Font.PLAIN,20));
			result.add(w);
		}
		else
		{
			JLabel w = new JLabel("• Over Ear: "+"No");
			w.setFont(new Font("Calibri",Font.PLAIN,20));
			result.add(w);
		}

		if(mic.equals("Y"))
		{
			JLabel m = new JLabel("• Microphone: "+"Yes");
			m.setFont(new Font("Calibri",Font.PLAIN,20));
			result.add(m);
		}
		else
		{
			JLabel m = new JLabel("• Microphone: "+"No");
			m.setFont(new Font("Calibri",Font.PLAIN,20));
			result.add(m);
		}

		if(iPhoneCompatible.equals("Y")){
			JLabel ipc = new JLabel("• iPhone Comaptible: "+"Yes");
			ipc.setFont(new Font("Calibri",Font.PLAIN,20));
			result.add(ipc);
		}
		else
		{
			JLabel ipc = new JLabel("• iPhone Comaptible: "+"No");
			ipc.setFont(new Font("Calibri",Font.PLAIN,20));
			result.add(ipc);
		}

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
	}
	public void displaySoundDock(String manufacturer,String model,String colour,String wireless,int powerOutput,String digRadio,double salePrice,int currentStock)
	{
		// Add product image and information
		JPanel result = new JPanel(new GridLayout(9,1));

		ImageIcon img = new ImageIcon(srcPath+manufacturer+" "+model+".jpg");
		JLabel i = new JLabel(img);
		gc.gridx =0;
		gc.gridy =0;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.NORTHWEST;
		i.setBorder(BorderFactory.createEmptyBorder(40,40,0,0));
		productInfo.add(i,gc);

		JLabel man = new JLabel(manufacturer);
		man.setFont(new Font("Calibri",Font.BOLD,40));
		man.setForeground(new Color(20,120,230));
		result.add(man);

		JLabel mod = new JLabel(model);
		mod.setFont(new Font("Calibri",Font.BOLD,40));
		mod.setForeground(new Color(20,120,230));
		result.add(mod);

		JLabel empty = new JLabel(" ");
		result.add(empty);

		JLabel c = new JLabel("• Colour: "+colour);
		c.setFont(new Font("Calibri",Font.PLAIN,20));
		result.add(c);

		if(wireless.equals("Y"))
		{
			JLabel w = new JLabel("• Wireless: "+"Yes");
			w.setFont(new Font("Calibri",Font.PLAIN,20));
			result.add(w);
		}
		else
		{
			JLabel w = new JLabel("• Wireless: "+"No");
			w.setFont(new Font("Calibri",Font.PLAIN,20));
			result.add(w);
		}


		JLabel po = new JLabel("• Output: "+powerOutput+" watts");
		po.setFont(new Font("Calibri",Font.PLAIN,20));
		result.add(po);

		if(digRadio.equals("Y")){
			JLabel dr = new JLabel("• Digital Radio: "+"Yes");
			dr.setFont(new Font("Calibri",Font.PLAIN,20));
			result.add(dr);
		}
		else
		{
			JLabel w = new JLabel("• Wireless: "+"No");
			w.setFont(new Font("Calibri",Font.PLAIN,20));
			result.add(w);
		}

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
	}
	public void displayCD(String artist,String album, String genre, String recordCompany, String length, int rating, double salePrice, int currentStock)
	{
		// Add product image and information
		ImageIcon img = new ImageIcon(srcPath+artist+" - "+album+".jpg");
		JLabel i = new JLabel(img);
		gc.gridx =0;
		gc.gridy =0;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.NORTHWEST;
		i.setBorder(BorderFactory.createEmptyBorder(40,40,0,0));
		productInfo.add(i,gc);
		
		JPanel result = new JPanel(new GridLayout(9,1));
		gc.gridx =1;
		gc.gridy =0;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.NORTHWEST;
		result.setBorder(BorderFactory.createEmptyBorder(40,20,0,0));
		result.setPreferredSize(new Dimension(450,350));
		productInfo.add(result,gc);

		JLabel a = new JLabel(artist);
		a.setFont(new Font("Calibri",Font.BOLD,40));
		a.setForeground(new Color(20,120,230));
		result.add(a);

		JLabel al = new JLabel(album);
		al.setFont(new Font("Calibri",Font.PLAIN,30));
		al.setForeground(new Color(20,120,230));
		result.add(al);

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

		// Add list of songs from CD
		JPanel songs = new JPanel();
		songs.setBackground(Color.WHITE);
		gc.gridx =2;
		gc.gridy =0;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.NORTHWEST;
		songs.setBorder(new CompoundBorder(
				//BorderFactory.createEmptyBorder(40,20,0,100),
				BorderFactory.createMatteBorder(40, 20, 0, 50, productInfo.getBackground()),
			    BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY)));
		songs.setPreferredSize(new Dimension(700,700));
		productInfo.add(songs,gc);

		JLabel title = new JLabel("Select a song to listen to a preview");
		title.setFont(new Font("Calibri",Font.PLAIN,30));
		title.setForeground(new Color(20,120,230));
		songs.add(title);
	}

	public void displayGame(String title,String genre,String company,String platform,int rating,double salePrice,int currentStock)
	{
		JPanel result = new JPanel(new GridLayout(8,1));

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
		
		JLabel empty = new JLabel(" ");
		result.add(empty);

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
		sp.setForeground(new Color(20,120,230));
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
	public void displayDVD(String title,String genre,String studio,int length,int rating,double salePrice,int currentStock)
	{
		JPanel result = new JPanel(new GridLayout(8,1));

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

		JLabel empty = new JLabel(" ");
		result.add(empty);
		
		JLabel st = new JLabel("• Studio: "+studio);
		st.setFont(new Font("Calibri",Font.PLAIN,20));
		result.add(st);

		JLabel l = new JLabel("• Length: "+length+" minutes");
		l.setFont(new Font("Calibri",Font.PLAIN,20));
		result.add(l);

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
		result.setBorder(BorderFactory.createMatteBorder(40,20,0,0,productInfo.getBackground()));
		result.setPreferredSize(new Dimension(1200,350));
		productInfo.add(result,gc);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==home)
		{
			frame.dispose();
		}
	}
}
