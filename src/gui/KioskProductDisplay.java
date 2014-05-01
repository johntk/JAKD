package gui;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import model.KioskAudioPlayer;
import model.KioskSong;


/*
The Product Display class is called when the "View Product" button is pressed from the results list
It displays all the details of the selected product.
*/


public class KioskProductDisplay extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JPanel main,center,top,centerTop,footer,productInfo;
	private JScrollPane scrollPane;
	private JButton home,pb;
	private JLabel resultsHeading,logoLabel,name;
	private ImageIcon hm,logo,play,stop,img;
	private GridBagConstraints gc;
	private String srcPath;
	private ArrayList<KioskSong> songList;
	private ArrayList<JButton> playButtons;
	private ArrayList<JLabel> songNames;
	private KioskAudioPlayer ap;
	private JSlider volume;
	private DecimalFormat d;

	public KioskProductDisplay()
	{
		playButtons = new ArrayList<JButton>();
		songList = new ArrayList<KioskSong>();
		songNames = new ArrayList<JLabel>();
		play = new ImageIcon(this.getClass().getResource("/resources/kioskFiles/images/play.png"));
		stop = new ImageIcon(this.getClass().getResource("/resources/kioskFiles/images/stop.png"));
		ap = new KioskAudioPlayer();

		volume = new JSlider(JSlider.HORIZONTAL,0,100,90);
		volume.setMajorTickSpacing(25);
		volume.setPaintTicks(true);
		volume.setPaintLabels(true);
		volume.setPreferredSize(new Dimension(300,50));
		volume.setMinimumSize(new Dimension(300,50));
		volume.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ev){
				try{
					double gain = (float)volume.getValue()/100;
					float db = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
					ap.setVolume(db);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});

		srcPath = "/resources/kioskFiles/productImages/";
		hm = new ImageIcon(this.getClass().getResource("/resources/kioskFiles/images/home.png"));
		logo = new ImageIcon(this.getClass().getResource("/resources/kioskFiles/images/logo3.png"));
		gc = new GridBagConstraints();
		d = new DecimalFormat(" € #####.##");

		main = new JPanel(new BorderLayout());
		main.setBackground(Color.WHITE);

		top = new JPanel(new BorderLayout());
		top.setBackground(new Color(0,0,0,0));

		home = new JButton(hm);
		home.setBackground(Color.WHITE);
		home.setFont(new Font("Calibri",Font.BOLD,25));
		home.setVerticalTextPosition(SwingConstants.BOTTOM );
		home.setHorizontalTextPosition(SwingConstants.CENTER);
		home.setBorder(null);
		home.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		home.addActionListener(this);
		top.add(home, BorderLayout.WEST);
		main.add(top,BorderLayout.NORTH);

		center = new JPanel(new BorderLayout());
		center.setBackground(new Color(0,0,0,0));
		center.setBorder(BorderFactory.createEmptyBorder(0,60,20,60));
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
		center.add(productInfo,BorderLayout.CENTER);

		footer = new JPanel();
		logoLabel = new JLabel(logo);
		footer.add(logoLabel);
		main.add(footer,BorderLayout.SOUTH);

		main.requestFocus();
	}

	public void setHeading(String s)
	{
		resultsHeading.setText(resultsHeading.getText()+s);
	}

	public void displayConsole(String manufacturer,String model,String colour,int storage,String wifi,int numControllers,double salePrice,int currentStock)
	{
		// Add product image and information
		JPanel result = new JPanel(new GridLayout(9,1));

		try{
			img = new ImageIcon(this.getClass().getResource(srcPath+manufacturer+" "+model+".jpg"));
		}catch(Exception e)
		{
			img = new ImageIcon(this.getClass().getResource(srcPath+"NoPhoto.jpg"));
		}
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
		mod.setFont(new Font("Calibri",Font.PLAIN,30));
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

		JLabel sp = new JLabel("Price: "+d.format(salePrice));
		sp.setFont(new Font("Calibri",Font.BOLD,25));
		sp.setForeground(new Color(20,120,230));
		result.add(sp);

		gc.gridx =1;
		gc.gridy =0;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.NORTHWEST;
		result.setBorder(BorderFactory.createEmptyBorder(40,20,0,0));
		result.setPreferredSize(new Dimension(600,350));
		result.setMinimumSize(new Dimension(400,350));
		productInfo.add(result,gc);

		// Pad out product view screen with empty JPanels
		for(int j=0;j<20;j++)
		{
			JPanel empty1 = new JPanel();
			gc.gridx =j+2;
			gc.gridy =0;
			gc.weightx=1.0;
			gc.weighty=1.0;
			productInfo.add(empty1,gc);
		}
	}
	public void displayHeadphones(String manufacturer,String model,String colour,String overEar,String mic,String iPhoneCompatible,double salePrice,int currentStock)
	{
		// Add product image and information
		JPanel result = new JPanel(new GridLayout(9,1));

		try{
			img = new ImageIcon(this.getClass().getResource(srcPath+manufacturer+" "+model+".jpg"));
		}catch(Exception e)
		{
			img = new ImageIcon(this.getClass().getResource(srcPath+"NoPhoto.jpg"));
		}
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
		mod.setFont(new Font("Calibri",Font.PLAIN,30));
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

		JLabel sp = new JLabel("Price: "+d.format(salePrice));
		sp.setFont(new Font("Calibri",Font.BOLD,25));
		sp.setForeground(new Color(20,120,230));
		result.add(sp);

		gc.gridx =1;
		gc.gridy =0;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.NORTHWEST;
		result.setBorder(BorderFactory.createEmptyBorder(40,20,0,0));
		result.setPreferredSize(new Dimension(600,350));
		result.setMinimumSize(new Dimension(400,350));
		productInfo.add(result,gc);

		// Pad out product view screen with empty JPanels
		for(int j=0;j<20;j++)
		{
			JPanel empty1 = new JPanel();
			gc.gridx =j+2;
			gc.gridy =0;
			gc.weightx=1.0;
			gc.weighty=1.0;
			productInfo.add(empty1,gc);
		}
	}
	public void displaySoundDock(String manufacturer,String model,String colour,String wireless,int powerOutput,String digRadio,double salePrice,int currentStock)
	{
		// Add product image and information
		JPanel result = new JPanel(new GridLayout(9,1));

		try{
			img = new ImageIcon(this.getClass().getResource(srcPath+manufacturer+" "+model+".jpg"));
		}catch(Exception e)
		{
			img = new ImageIcon(this.getClass().getResource(srcPath+"NoPhoto.jpg"));
		}
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
		mod.setFont(new Font("Calibri",Font.PLAIN,30));
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

		JLabel sp = new JLabel("Price: "+d.format(salePrice));
		sp.setFont(new Font("Calibri",Font.BOLD,25));
		sp.setForeground(new Color(20,120,230));
		result.add(sp);

		gc.gridx =1;
		gc.gridy =0;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.NORTHWEST;
		result.setBorder(BorderFactory.createEmptyBorder(40,20,0,0));
		result.setPreferredSize(new Dimension(600,350));
		result.setMinimumSize(new Dimension(400,350));
		productInfo.add(result,gc);

		// Pad out product view screen with empty JPanels
		for(int j=0;j<20;j++)
		{
			JPanel empty1 = new JPanel();
			gc.gridx =j+2;
			gc.gridy =0;
			gc.weightx=1.0;
			gc.weighty=1.0;
			productInfo.add(empty1,gc);
		}
	}
	public void displayCD(String artist,String album, String genre, String recordCompany, String length, int rating, double salePrice, int currentStock, String prodID)
	{
		// Add product image and information
		int y = 1;

		try{
			img = new ImageIcon(this.getClass().getResource(srcPath+artist+" - "+album+".jpg"));
		}catch(Exception e)
		{
			img = new ImageIcon(this.getClass().getResource(srcPath+"NoPhoto.jpg"));
		}
		JLabel im = new JLabel(img);
		gc.gridx =0;
		gc.gridy =0;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.NORTHWEST;
		im.setBorder(BorderFactory.createEmptyBorder(40,40,0,0));
		productInfo.add(im,gc);


		JPanel result = new JPanel(new GridLayout(9,1));
		gc.gridx =1;
		gc.gridy =0;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.NORTHWEST;
		result.setBorder(BorderFactory.createEmptyBorder(40,20,0,0));
		result.setPreferredSize(new Dimension(600,350));
		result.setMinimumSize(new Dimension(400,350));
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

		JLabel sp = new JLabel("Price: "+d.format(salePrice));
		sp.setFont(new Font("Calibri",Font.BOLD,25));
		sp.setForeground(new Color(20,120,230));
		result.add(sp);

		// Add volume controls to album preview window
		JPanel volumeControl = new JPanel(new BorderLayout());
		volumeControl.setBorder(new CompoundBorder(
				BorderFactory.createEmptyBorder(350,20,0,0),
				BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY)));
		gc.gridx =1;
		gc.gridy =0;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.WEST;
		productInfo.add(volumeControl,gc);
		JLabel vc = new JLabel("Volume Control");
		vc.setFont(new Font("Calibri",Font.BOLD,20));
		vc.setForeground(new Color(20,120,230));
		volumeControl.add(vc,BorderLayout.NORTH);
		volumeControl.add(volume,BorderLayout.CENTER);

		// Add JPanel to display list of songs from CD
		JPanel songs = new JPanel(new GridBagLayout());
		songs.setBackground(Color.WHITE);
		gc.gridx =2;
		gc.gridy =0;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.NORTHWEST;
		songs.setBorder(new CompoundBorder(
				BorderFactory.createMatteBorder(40, 20, 0, 50, productInfo.getBackground()),
				BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY)));
		songs.setPreferredSize(new Dimension(750,650));
		songs.setMinimumSize(new Dimension(550,650));
		productInfo.add(songs,gc);

		JLabel title = new JLabel("Title:");
		title.setFont(new Font("Calibri",Font.BOLD,25));
		title.setForeground(new Color(20,120,230));
		gc.gridx =1;
		gc.gridy =0;
		gc.weightx=1.0;
		gc.weighty=1.0;
		songs.add(title,gc);

		JLabel lgth = new JLabel("Length:");
		lgth.setFont(new Font("Calibri",Font.BOLD,25));
		lgth.setForeground(new Color(20,120,230));
		gc.gridx =2;
		gc.gridy =0;
		gc.weightx=1.0;
		gc.weighty=1.0;
		songs.add(lgth,gc);

		// Add list of song titles to songsPanel
		for(int i=0; i<songList.size();i++)
		{
			gc.gridx =0;
			gc.gridy =y;
			gc.weightx=1.0;
			gc.weighty=1.0;
			songs.add(playButtons.get(i),gc);

			name = new JLabel(songList.get(i).getTitle());
			name.setFont(new Font("Calibri",Font.PLAIN,20));
			name.setForeground(Color.GRAY);
			songNames.add(name);
			gc.gridx =1;
			gc.gridy =y;
			gc.weightx=1.0;
			gc.weighty=1.0;
			songs.add(songNames.get(i),gc);


			JLabel l = new JLabel(songList.get(i).getLength());
			l.setFont(new Font("Calibri",Font.PLAIN,20));
			gc.gridx =2;
			gc.gridy =y;
			gc.weightx=1.0;
			gc.weighty=1.0;
			songs.add(l,gc);
			y++;
		}

		// Pad out product view screen with empty JPanels
		for(int j=0;j<8;j++)
		{
			JPanel empty1 = new JPanel();
			gc.gridx =j+3;
			gc.gridy =0;
			gc.weightx=1.0;
			gc.weighty=1.0;
			productInfo.add(empty1,gc);
		}
	}

	public void addSong(KioskSong s)
	{
		pb = new JButton();
		pb.setIcon(play);
		pb.setBorderPainted(false); 
		pb.setContentAreaFilled(false); 
		pb.setOpaque(false);
		pb.addActionListener(this);
		playButtons.add(pb);
		songList.add(s);
	}


	public void displayGame(String title,String genre,String company,String platform,int rating,double salePrice,int currentStock)
	{
		JPanel result = new JPanel(new GridLayout(8,1));

		try{
			img = new ImageIcon(this.getClass().getResource(srcPath+title+".jpg"));
		}catch(Exception e)
		{
			img = new ImageIcon(this.getClass().getResource(srcPath+"NoPhoto.jpg"));
		}
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


		JLabel sp = new JLabel("Price: "+d.format(salePrice));
		sp.setFont(new Font("Calibri",Font.BOLD,25));
		sp.setForeground(new Color(20,120,230));
		result.add(sp);

		gc.gridx =1;
		gc.gridy =0;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.NORTHWEST;
		result.setBorder(BorderFactory.createMatteBorder(40,20,0,0,productInfo.getBackground()));
		result.setPreferredSize(new Dimension(600,350));
		result.setMinimumSize(new Dimension(400,350));
		productInfo.add(result,gc);

		// Pad out product view screen with empty JPanels
		for(int j=0;j<20;j++)
		{
			JPanel empty1 = new JPanel();
			gc.gridx =j+2;
			gc.gridy =0;
			gc.weightx=1.0;
			gc.weighty=1.0;
			productInfo.add(empty1,gc);
		}
	}
	public void displayDVD(String title,String genre,String studio,int length,int rating,double salePrice,int currentStock)
	{
		JPanel result = new JPanel(new GridLayout(8,1));

		try{
			img = new ImageIcon(this.getClass().getResource(srcPath+title+".jpg"));
		}catch(Exception e)
		{
			img = new ImageIcon(this.getClass().getResource(srcPath+"NoPhoto.jpg"));
		}
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


		JLabel sp = new JLabel("Price: "+d.format(salePrice));
		sp.setFont(new Font("Calibri",Font.BOLD,25));
		sp.setForeground(new Color(20,120,230));
		result.add(sp);

		gc.gridx =1;
		gc.gridy =0;
		gc.weightx=1.0;
		gc.weighty=1.0;
		gc.anchor = GridBagConstraints.NORTHWEST;
		result.setBorder(BorderFactory.createMatteBorder(40,20,0,0,productInfo.getBackground()));
		result.setPreferredSize(new Dimension(600,350));
		result.setMinimumSize(new Dimension(400,350));
		productInfo.add(result,gc);

		// Pad out product view screen with empty JPanels
		for(int j=0;j<20;j++)
		{
			JPanel empty1 = new JPanel();
			gc.gridx =j+2;
			gc.gridy =0;
			gc.weightx=1.0;
			gc.weighty=1.0;
			productInfo.add(empty1,gc);
		}
	}

	public JPanel getPanel()
	{
		return main;
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==home)
		{
			KioskResultsScreen.switchToResultsPanel(getPanel());
			try{
				ap.stop();
			}
			catch(Exception ex)
			{

			}
		}
		if(e.getSource() instanceof JButton)
		{
			for(int i=0; i<playButtons.size(); i++)
			{
				try{
					if(((JButton)e.getSource()) == playButtons.get(i))
					{
						try{
							ap.stop();
						}
						catch(Exception ex){
						}

						for(int j=0; j<songNames.size(); j++)
						{
							if(songNames.get(j) != songNames.get(i))
							{
								songNames.get(j).setForeground(Color.GRAY);
								songNames.get(j).setFont(new Font("Calibri",Font.PLAIN,20));
								songNames.get(j).setIcon(null);
								playButtons.get(j).setIcon(play);
							}
							else
							{
								ImageIcon ind = new ImageIcon(this.getClass().getResource("/resources/kioskFiles/images/indicator.png"));
								songNames.get(j).setForeground(new Color(20,120,230));
								songNames.get(j).setFont(new Font("Calibri",Font.BOLD,20));
								songNames.get(j).setIcon(ind);
								playButtons.get(j).setIcon(stop);
							}
						}

						if(ap.getFilePath() == songList.get(i).getFilePath())
						{
							ap.stop();
							playButtons.get(i).setIcon(play);
							songNames.get(i).setIcon(null);
							ap.nullFile();
						}
						else ap.play(songList.get(i).getFilePath());
						volume.setValue(90);
					}
				}catch(Exception ex)
				{
					ex.getMessage();
				}
			}
		}
	}
}  
